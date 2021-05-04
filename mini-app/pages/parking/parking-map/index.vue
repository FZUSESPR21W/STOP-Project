<template>
	<view>
		<map 
			:latitude="latitude" 
			:longitude="longitude" 
			id="parkingMap" 
			:markers="covers"
			:controls="control"
			:show-location="true"
			@markertap="clickMarker"
			@regionchange="clickMap"
			style="width: 100%;"
			:style="{height:mapHeight}">
		</map>
	</view>
</template>

<script>
export default{
	name:'ParkingMap',
	data(){
		return{
			latitude:'',
			longitude:'',
			covers:[],
			mapHeight:'90vh',
			control:[],
		}
	},
	methods: {
		
		//地图点击事件
		clickMap(res){
			var _this=this;
			_this.mapHeight='90vh';
			_this.$emit('hideList',true);
		},
		
		//标记点击事件
		clickMarker(res){
			console.log(res);
		},
		
		//获取用户位置
		getLocation(){
			var _this=this;
			uni.showLoading({
				title:'正在获取定位',
			});
			uni.getLocation({
				type:'gcj02',
				timeout:'2000',
				
				success(res) {
					uni.hideLoading();
					_this.longitude=res.longitude;
					_this.latitude=res.latitude;
					console.log(res);
					console.log(_this.longitude);
					console.log(_this.latitude);
					_this.covers[0]={
						latitude:res.latitude+0.00001,
						longitude:res.longitude+0.00001,
						width:40,
						height:40,
						iconPath:'../../../static/map-icon/我的位置.png'
					};
					_this.control[0]={
						iconPath:'../../../static/map-icon/我的位置.png',
						clickable:true,
						position:{
							width:40,
							height:30,
						},
					};
				},
				fail(err) {
					uni.hideLoading();
					uni.showModal({
						title:'提示',
						content:'位置信息获取失败（请检查定位功能是否打开）',
						showCancel:false
					})
				}
			})
		},
	},
	created() {
		var _this=this;
		_this.getLocation();
	}
}
</script>

<style scoped>
#parkingMap{
	width: 100%;
}
</style>
