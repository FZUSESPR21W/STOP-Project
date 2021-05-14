<template>
	<!-- 每个块级元素开始和结束时都要加注释, 单行只需要加开始 -->
	<!-- 示例：map页面容器开始 -->
	<view>
		<!-- 地图组件容器开始 -->
		<view>
			<!-- 地图组件-->
			<map :latitude="latitude" :longitude="longitude" id="parkingMap" :markers="covers" :show-location="true"
				@markertap="clickMarker" @regionchange="clickMap" :polygons="polygons" style="width: 100%;"
				:style="{height:mapHeight}" />
		</view>
		<!-- 地图组件容器结束 -->

		<view @click="moveToCenter()" class="viewlittle" style="position: absolute;top: 47%;left: 87%;">
			<view style="background: #f1f1f1;border-radius: 5px;width: 40px;height: 40px;">
				<image src="../../../static/map-icon/miaozhun.png"
					style="width: 30px;height: 30px;margin: 0 auto;padding-top: 5px;padding-left: 5px;" />
			</view>
		</view>
	</view>
	<!-- map页面容器结束 -->
</template>

<script>
	export default {
		name: 'ParkingMap',
		data() {
			return {
				// 数据请加注释
				//经纬度
				latitude: '',
				longitude: '',
				//marker标记
				covers: [],
				//地图高度
				mapHeight: '90vh',
				//绘图用的多边形
				polygons: '',
				//用于存放数据的map
				markerMap: '',
				stop: ''
			}
		},

		methods: {
			//计算经纬度距离 返回米
			getDistance(lat1, lng1, lat2, lng2) {
				var radLat1 = lat1 * Math.PI / 180.0;
				var radLat2 = lat2 * Math.PI / 180.0;
				var a = radLat1 - radLat2;
				var b = lng1 * Math.PI / 180.0 - lng2 * Math.PI / 180.0;
				var s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
					Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
				s = s * 6378.137; // EARTH_RADIUS;
				s = Math.round(Math.round(s * 10000) / 10000 * 1000);
				return s;
			},
			//转换地点列表
			transformPlaceList(placeList) {
				for (let i = 0; i < placeList.length; i++) {
					let tempDistance = this.getDistance(placeList[i].latitude, placeList[i].longitude, this.latitude, this.longitude)
					if (tempDistance > 999) {
						tempDistance = Math.round(tempDistance / 1000)
						placeList[i].distance = tempDistance + 'km'
					} else {
						placeList[i].distance = tempDistance + 'm'
					}
					if (placeList[i].capacity <= 0.5) {
						placeList[i].surplus = '车位空闲';
						placeList[i].surplusColor = '#2fc25b';
					} else if (placeList[i].capacity > 0.5 && placeList[i].capacity < 0.7) {
						placeList[i].surplus = '车位紧张';
						placeList[i].surplusColor = '#1890ff';
					} else {
						placeList[i].surplus = '车位不足'
						placeList[i].surplusColor = '#f04864';
					}
				}
				console.log(placeList)
				return placeList
			},

			//地图点击事件
			clickMap(res) {
				this.mapHeight = '90vh';
				this.$emit('hideList', true);
			},
			//标记点击事件
			clickMarker(res) {

				//console.log();
				let data = this.covers[this.markerMap.get(res.target.markerId)]
				this.$emit('clickCovers', data);
			},
			//返回中心点
			moveToCenter() {
				let mapCtx = uni.createMapContext('parkingMap', this)
				mapCtx.moveToLocation()
			},
			//获取用户位置
			getLocation() {
				// 使用Promise包装uni.getLocation, 增加可读性（有回调函数的方法都可以这样做）
				new Promise((resolve, rejected) => {
					uni.getLocation({
						type: 'gcj02',
						timeout: '2000',
						success: res => resolve(res),
						fail: err => rejected(res)
					})
				}).then(res => {
					this.longitude = res.longitude
					this.latitude = res.latitude
					let left
					let top
					uni.getSystemInfo({
						success(res) {
							left = res.windowWidth * 0.8
							top = res.windowHeight * 0.2
						}
					})
				}).catch(err => {
					uni.showModal({
						title: '提示',
						content: '位置信息获取失败（请检查定位功能是否打开）',
						showCancel: false
					})
				})
			},
			
		},
		created() {
			let deviceMap = new Map()
			let stopMap = new Map()
			this.getLocation();
			this.$api.Statistics.getAllDevice().then(
				(res) => {
					this.$api.Statistics.getAllParkingValue().then(
						(r) => {
							//console.log(r)
							let stop = r.data.data.stopStatusList
							for (let j = 0; j < stop.length; j++) {
								stopMap.set(parseInt(stop[j].id), stop[j].value)
							}
							this.stop = stopMap
							let array = res.data.data.deviceList
							let index = 0
							let coversTemp = []
							for (let i = 0; i < array.length; i++) {
								let data = array[i].deviceDO
								if (array[i].online == true) {
									let num = stopMap.get(data.id)
									//console.log(num)
									coversTemp[index] = {
										id: data.id,
										name: data.name,
										latitude: data.latitude,
										longitude: data.longitude,
										capacity: num / data.maxCarsNumber
									}
									deviceMap.set(data.id, index)
									index++
								}
							}
							this.markerMap = deviceMap
							this.covers = this.transformPlaceList(coversTemp);
							console.log(this.covers)
							this.$emit('placeList', this.covers)
							// let polygons = []
							// this.$api.Statistics.getPoints().then(
							// 	(res) => {
							// 		console.log(res)
							// 	}
							// )
						}
					)

				}
			)
			//console.log(this.stop)

			var polygons = [{
					strokeWidth: 3,
					strokeColor: '#266339',
					fillColor: '#7FFFAA30',
					points: [{
							latitude: "26.060929253238374",
							longitude: "119.1981588742523"
						},
						{
							latitude: "26.060890701203803",
							longitude: "119.19825543377684"
						},
						{
							latitude: "26.060442532871022",
							longitude: "119.19805695030973"
						},
						{
							latitude: "26.06045698994078",
							longitude: "119.19802476380156"
						},
					]
				}, {
					strokeWidth: 3,
					strokeColor: '#266339',
					fillColor: '#7FFFAA30',
					points: [{
							latitude: "26.060500361139948",
							longitude: "119.19812668774412"
						},
						{
							latitude: "26.060423256776",
							longitude: "119.19808377239988"
						},
						{
							latitude: "26.06031723819272",
							longitude: "119.19838417980955"
						},
						{
							latitude: "26.060384704575174",
							longitude: "119.19842173073576"
						},
					]
				}


			]
			this.polygons = polygons
		},
		onShow() {
			let deviceMap = new Map()

		},

	}
</script>

<style scoped>
	#parkingMap {
		width: 100%;
	}
</style>
