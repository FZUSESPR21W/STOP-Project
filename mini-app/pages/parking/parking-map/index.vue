<template>
	<view>
		<view>
			<map :latitude="latitude" :longitude="longitude" id="parkingMap" :markers="covers" :controls="control"
				:show-location="true" @markertap="clickMarker" @regionchange="clickMap" :polygons="polygons" style="width: 100%;" 
				:style="{height:mapHeight}">
			</map>
		</view>
		
		<cover-view @click="moveToCenter()" class="viewlittle" style="position: absolute;top: 67%;left: 87%;">
			<cover-view style="background: #f1f1f1;border-radius: 5px;width: 40px;height: 40px;">
				<cover-image src="../../../static/map-icon/miaozhun.png" style="width: 30px;height: 30px;margin: 0 auto;padding-top: 5px;"></cover-image>
			</cover-view>
		</cover-view>
	</view>
</template>

<script>
	export default {
		name: 'ParkingMap',
		data() {
			return {
				latitude: '',
				longitude: '',
				covers: [],
				mapHeight: '90vh',
				control: [],
				polygons: '',
				marker:[],
				coverViewStyle:''
			}
		},
		
		methods: {

			//地图点击事件
			clickMap(res) {
				var _this = this;
				_this.mapHeight = '90vh';
				_this.$emit('hideList', true);
				
			},

			//标记点击事件
			clickMarker(res) {
				console.log(res);
				console.log(this.covers)
				console.log(this.covers[res.target.markerId].latitude)
				console.log(this.covers[res.target.markerId].longitude)
			},
			//返回中心点
			moveToCenter() {
				var mapCtx = uni.createMapContext('parkingMap', this)
				mapCtx.moveToLocation()
			},
			//获取用户位置
			getLocation() {
				var _this = this;
				uni.showLoading({
					title: '正在获取定位',
				});
				uni.getLocation({
					type: 'gcj02',
					timeout: '2000',

					success(res) {
						uni.hideLoading();
						_this.longitude = res.longitude;
						_this.latitude = res.latitude;
						console.log(res);
						console.log(_this.longitude);
						console.log(_this.latitude);
						_this.covers = [{
							id:0,
							iconPath: '../../../static/map-icon/我的位置.png',
							latitude: res.latitude + 0.00001,
							longitude: res.longitude + 0.00001,
							width: 40,
							height: 40
						},{
							id:1,
							name:'test',
							iconPath:'../../../static/map-icon/location.png',
							latitude: 26.060929253238374,
							longitude: 119.1981588742523,
							width:50,
							length:50,
						}];
						var left
						var top
						uni.getSystemInfo({
							success(res) {
								left = res.windowWidth * 0.8
								top = res.windowHeight * 0.2
							}
						})
						
						_this.control[0] = {
							iconPath: 'https://7.dusays.com/2021/05/06/f4741c6b7097d.jpeg',
							clickable: true,
							position: {
								left: left,
								top: top,
								width: 40,
								height: 40,
							},
						};
					},
					fail(err) {
						uni.hideLoading();
						uni.showModal({
							title: '提示',
							content: '位置信息获取失败（请检查定位功能是否打开）',
							showCancel: false
						})
					}
				})
			},
		},
		created() {
			var _this = this;
			_this.getLocation();
			
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
			_this.polygons = polygons
		},

	}
</script>

<style scoped>
	#parkingMap {
		width: 100%;
	}
</style>
