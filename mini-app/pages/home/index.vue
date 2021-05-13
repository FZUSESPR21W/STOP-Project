<template>
	<view>
		<!-- 条件渲染if -->
		<view v-if="!hasUserInfo" style="margin-top: 50rpx;">
		  <u-button v-if="canIUseGetUserProfile" :custom-style="customStyle" :ripple="true" ripple-bg-color="#A55F91" @click="getUserProfile"> 获取个人信息 </u-button>
		</view>
		<!-- 条件渲染else -->
		<view v-else class="u-flex user-box u-p-l-30 u-p-r-20 u-p-b-30" >
			<view class="u-m-r-10">
				<u-avatar :src="userInfo.avatarUrl" mode="circle" size="140"></u-avatar>
			</view>
			<view class="u-flex-1">
				<view class="u-font-18 u-p-b-20">Hi, {{userInfo.nickName}}</view>
				<view class="u-font-14 u-tips-color">城市：{{userInfo.city}}</view>
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
				userInfo: {},
				hasUserInfo: false,
				canIUseGetUserProfile: false,
				
				//提交按钮定制样式
				customStyle: {
					color: "#A55F91",
					width: "240rpx",
					height: "80rpx",
					fontStyle: 'bold'
				},
			}
		},
		onLoad(){
			if (uni.getUserProfile) {
			  this.canIUseGetUserProfile = true
			};
		},
		methods: {
			
			//跳转到个人信息页面
			goMyInfo(){
				uni.navigateTo({
					url:'../home/my-info'
				})
			},
			
			//跳转到车流量统计页面
			goCarCount(){
				uni.navigateTo({
					url:'../home/car-count'
				})
			},
			
			//跳转到关于页面
			goAboutUs(){
				uni.navigateTo({
					url:'../home/about-us'
				})
			},
			
			getUserProfile(e) {
			  // 推荐使用wx.getUserProfile获取用户信息，开发者每次通过该接口获取用户个人信息均需用户确认
			  // 开发者妥善保管用户快速填写的头像昵称，避免重复弹窗
			  uni.getUserProfile({
			    desc: '用于完善用户资料', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
			    success: (res) => {
			      this.userInfo = res.userInfo,
			      this.hasUserInfo = true
				  //将数据存储在本地缓存中指定的 key 中，会覆盖掉原来该 key 对应的内容，这是一个异步接口。
				  uni.setStorage({
					key:'user-info_key',
				  	data:this.userInfo,
				  	success: function () {
						console.log('success2');
						},
					})
				  },
				})
			},
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
