<template>
	<!-- 个人信息页面 -->
	<view class="user-info">
		<!-- 条件渲染 -->
		<view style="margin-top: 50rpx;"  v-if="!hasUserInfo">
		  <u-button v-if="canIUseGetUserProfile" :custom-style="customStyle" :ripple="true" ripple-bg-color="#A55F91" @click="getUserProfile"> 获取个人信息 </u-button>
		</view>
		<u-cell-group style="width: -webkit-fill-available;" v-else>
			<u-cell-item  title="头像" :arrow="false">
				<view class="u-m-r-10">
					<u-avatar :src="userInfo.avatarUrl" size="140" ></u-avatar>
				</view>
			</u-cell-item>
			<u-cell-item  title="昵称" :arrow="false">
				<view class="u-flex-1">
					<view class="u-font-18 u-p-b-20">{{userInfo.nickName}}</view>
				</view>
			</u-cell-item>
			<u-cell-item  title="城市" :arrow="false">
				<view class="u-flex-1">
					<view class="u-font-14 u-tips-color">{{userInfo.city}}</view>
				</view>
			</u-cell-item>
		</u-cell-group>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				userInfo: {},
				hasUserInfo: false,
				canIUseGetUserProfile: false,
				//获取个人信息按钮样式
				customStyle: {
					color: "#A55F91",
					width: "240rpx",
					height: "80rpx",
					fontStyle: 'bold'
				},
			}
		},
		onLoad() {
			if (wx.getUserProfile) {
			  this.canIUseGetUserProfile=true
			}
		},
		methods: {
			getUserProfile(e) {
			  // 使用wx.getUserProfile获取用户信息，开发者每次通过该接口获取用户个人信息均需用户确认
			  wx.getUserProfile({
			    desc: '用于完善会员资料', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
			    success: (res) => {
			      this.userInfo = res.userInfo,
			      this.hasUserInfo = true
			    }
			  })
			},
		}
	}
</script>

<style>
</style>
