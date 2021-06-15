<template>
	<!-- 每个块级元素开始和结束时都要加注释, 单行只需要加开始 -->
	<!-- 示例：map页面容器开始 -->
	<view>
		<!-- 无网络提示组件 -->
		<u-no-network tips="哎呀,信号走丢了"></u-no-network>
		<!-- 地图组件容器开始 -->
		<view>
			<!-- 地图组件-->
			<map :latitude="latitude" :longitude="longitude" id="parkingMap" :markers="covers" :show-location="true"
				@markertap="clickMarker" @regionchange="clickMap" :polygons="polygons" style="width: 100%;"
				:style="{height:mapHeight}" :min-scale="minScale" :max-scale="maxScale"/>
		</view>
		<!-- 地图组件容器结束 -->
		<view style="position: absolute;top: 40%;left: 87%;">
			<!-- 刷新按钮开始 -->
			<view @click="refresh()">
				<view style="background: rgba(255,255,255,0.8);border-radius:5px 5px 0 0;width: 40px;height: 40px;">
					<image src="../../../static/map-icon/refresh.png"
						style="width: 30px;height: 30px;margin: 0 auto;padding-top: 5px;padding-left: 5px;" />
				</view>
			</view>
			<!-- 刷新按钮结束 -->
			<view @click="moveToCenter()">
				<view
					style="background: rgba(255,255,255,0.8);border-radius:0 0 5px 5px;border-top: 0.8px solid rgba(0,0,0,0.4);width: 40px;height: 40px;">
					<image src="../../../static/map-icon/miaozhun.png"
						style="width: 30px;height: 30px;margin: 0 auto;padding-top: 5px;padding-left: 5px;" />
				</view>
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
				latitude: '26.05064261000777',
				longitude: '119.19206586536791',
				//marker标记
				covers: [],
				//地图高度
				mapHeight: '90vh',
				//绘图用的多边形
				polygons: '',
				//用于存放数据的map
				markerMap: '',
				stop: '',
				//用户最小缩放级别
				minScale:'14',
				//用户最大缩放级别
				maxScale:'20',
			}
		},

		methods: {
			
			//刷新按钮点击事件
			refresh(){
				console.log('刷新');
				uni.reLaunch({
					url:'/pages/parking/index'
				});
			},
			//计算经纬度距离 返回米
			getDistance(lat1, lng1, lat2, lng2) {
				if((!lat1 && typeof(lat1)!="undefined" && lat1!=0) || 
					(!lng1 && typeof(lng1)!="undefined" && lng1!=0)){
					//当目的经纬度为null时说明错误，返回-1
					return -1;
				}
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
					let tempDistance = this.getDistance(placeList[i].latitude, placeList[i].longitude, this.latitude, this
						.longitude)
					if (tempDistance > 999) {
						tempDistance = Math.round(tempDistance / 1000)
						placeList[i].distance = tempDistance + 'km'
					} else {
						placeList[i].distance = tempDistance + 'm'
					}
					if (placeList[i].capacity >=0 && placeList[i].capacity <= 0.5) {
						placeList[i].surplus = '车位空闲'
						placeList[i].surplusColor = '#2fc25b'
					} else if (placeList[i].capacity > 0.5 && placeList[i].capacity < 0.7) {
						placeList[i].surplus = '车位紧张'
						placeList[i].surplusColor = '#1890ff'
					} else if (isNaN(placeList[i].capacity)|| placeList[i].capacity > 1 || 
						placeList[i].capacity < 0 || tempDistance===-1){
						placeList[i].surplus = '设备故障'
						placeList[i].surplusColor = '#b3b2ab'
						placeList[i].fault=true;
					} else {
						placeList[i].surplus = '车位不足'
						placeList[i].surplusColor = '#f04864'
					}
				}
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
						fail: err => rejected(err)
					})
				}).then(res => {
					this.longitude = res.longitude
					this.latitude = res.latitude
					console.log(res.longitude);
					console.log(res.latitude);
				}).catch(err => {
					uni.showToast({
						title: '位置信息获取失败（请检查定位功能是否打开）',
						icon:'none',
					})
				})
			},
			//封装获取设备信息方法
			getAllDevice(){
				let deviceMap = new Map()
				let stopMap = new Map()
				this.$api.Statistics.getAllDevice().then(
					(res) => {
						this.$api.Statistics.getAllParkingValue().then(
							(r) => {
								if(null == res.data.data.deviceList || null == r.data.data.stopStatusList) {
									this.markerMap = []
									setTimeout(() => {
										console.log('获取设备为空')
										this.$emit('placeList', [])
									}, 500);
								} else {
									let stop = r.data.data.stopStatusList
									for (let j = 0; j < stop.length; j++) {
										stopMap.set(parseInt(stop[j].id), stop[j].value)
									}
									this.stop = stopMap
									let array = res.data.data.deviceList//kong
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
												capacity: num / data.maxCarsNumber,
												num:num,
												maxCarsNumber:data.maxCarsNumber,
												//是否故障
												fault:false
											}
											deviceMap.set(data.id, index)
											index++
										}
									}
									this.markerMap = deviceMap
									setTimeout(() => {
										this.covers = this.transformPlaceList(coversTemp);
										console.log('处理了数据')
										this.$emit('placeList', this.covers)
									}, 500)
								}
							}
						)
				
					}
				)
			},
			//获取停车场数据
			getAllParkingArea() {
				this.$api.Statistics.getPoints().then(
					(res) => {
						let areaList = res.data.data.DeviceAndPointsInfo
						if (null == areaList) {
							console.log('停车场数据获取失败')
						} else {
							let tempPy = []
							let strokeColor = ''
							let fillColor = ''
							for (let i = 0;i<areaList.length;i++) {
								let index = parseInt(areaList[i].value) / parseInt(areaList[i].maxValue)
								if (index <= 0.5 && index >=0 && areaList[i].online == true) {
									strokeColor = '#266339'
									fillColor = '#7FFFAA'
								} else if (index > 0.5 && index <0.7  && areaList[i].online == true) {
									strokeColor = '#194063'
									fillColor = '#70aeff'
								} else if (index < 0 || areaList[i].online == false){
									strokeColor = '#adadad',
									fillColor = '#d1d1d1'
								} else {
									strokeColor = '#ff5c10'
									fillColor = '#ff5f54'
								}
								tempPy[i] = {
									points: areaList[i].points,
									strokeColor: strokeColor,
									fillColor: fillColor,
									strokeWidth: 3
								}
							}
							this.polygons = tempPy
						}
				})
			}
		},
		created() {
			this.getLocation()
			this.getAllDevice()
			//console.log(this.stop)
			this.getAllParkingArea()
			// var polygons = [{
			// 		strokeWidth: 3,
			// 		strokeColor: '#266339',
			// 		fillColor: '#7FFFAA30',
			// 		points: [{
			// 				latitude: "26.060929253238374",
			// 				longitude: "119.1981588742523"
			// 			},
			// 			{
			// 				latitude: "26.060890701203803",
			// 				longitude: "119.19825543377684"
			// 			},
			// 			{
			// 				latitude: "26.060442532871022",
			// 				longitude: "119.19805695030973"
			// 			},
			// 			{
			// 				latitude: "26.06045698994078",
			// 				longitude: "119.19802476380156"
			// 			},
			// 		]
			// 	}, {
			// 		strokeWidth: 3,
			// 		strokeColor: '#266339',
			// 		fillColor: '#7FFFAA30',
			// 		points: [{
			// 				latitude: "26.060500361139948",
			// 				longitude: "119.19812668774412"
			// 			},
			// 			{
			// 				latitude: "26.060423256776",
			// 				longitude: "119.19808377239988"
			// 			},
			// 			{
			// 				latitude: "26.06031723819272",
			// 				longitude: "119.19838417980955"
			// 			},
			// 			{
			// 				latitude: "26.060384704575174",
			// 				longitude: "119.19842173073576"
			// 			},
			// 		]
			// 	}


			// ]
			// this.polygons = polygons
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
