<template>
	<view>
		 <view class="u-flex user-box u-p-l-30 u-p-r-20 u-p-b-30">
			<view class="u-m-r-10">
				<u-avatar :src="pic" mode="circle" size="140"></u-avatar>
			</view>
			<view class="u-flex-1">
				<view class="u-font-18 u-p-b-20">Hi, 欢迎使用STOP</view>
				<view class="u-font-14 u-tips-color">城市：福建福州</view>
			</view>
			<view class="u-m-l-10 u-p-10">
				<image style="margin-top: 60rpx; width: 150rpx;height: 150rpx;" src="../../static/stopLogo.png"></image>
			</view>
		</view>
		
		
		<view class="u-m-t-20">
			<u-cell-group>
				<u-cell-item  title="个人信息" @click="goMyInfo" arrow-direction="right">
					<u-icon slot="icon" custom-prefix="custom-icon" color="#A55F91" size="32" name="gerenxinxi"></u-icon>
				</u-cell-item>
				<u-cell-item  title="车流量统计" @click="goCarCount" arrow-direction="right">
					<u-icon slot="icon" custom-prefix="custom-icon" color="#A55F91" size="32" name="tongji"></u-icon>
				</u-cell-item>
				<u-cell-item  title="关于STOP" @click="goAboutUs" arrow-direction="right">
					<u-icon slot="icon" custom-prefix="custom-icon" color="#A55F91" size="32" name="guanyu"></u-icon>
				</u-cell-item>
			</u-cell-group>
		</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				pic:'https://uviewui.com/common/logo.png',
				show:true
			}
		},
		onLoad() {
			
		},
		methods: {
			goMyInfo(){
				uni.navigateTo({
					url:'../home/my-info'
				})
			},
			goCarCount(){
				uni.navigateTo({
					url:'../home/car-count'
				})
			},
			goAboutUs(){
				uni.navigateTo({
					url:'../home/about-us'
				})
			}
		}
	}
</script>

<style lang="scss">
page{
	background-image: url("../../static/mycenter-bg.png");
	background-repeat: no-repeat;
}

.user-box {
    margin: 100rpx auto -20rpx;
    width: 90%;
	height: 200rpx;
    background-color: #fff;
    border-style: hidden hidden solid;
    border-bottom-color: #9E9E9E;
    border-radius: 10rpx;
}
.u-flex-1{
	margin-top: auto;
}
.u-m-t-20 {
	margin-top: 0 !important;
    position: absolute;
    width: -webkit-fill-available;
    z-index: -1;
}
.u-m-r-10{
	margin-top: inherit;
    position: absolute;
	left: 50%;
	top: 50%;
	transform: translate3d(-50%,-50%,0);
    top: -1rpx;
}

</style>
