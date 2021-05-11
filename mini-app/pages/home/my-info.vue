<template>
	<view class="u-m-t-20">
		<u-cell-group>
			<u-cell-item  title="头像" @click="goMyAvatar" arrow-direction="right">
				<view class="u-m-r-10">
					<u-avatar :src="pic" size="140"></u-avatar>
				</view>
			</u-cell-item>
			<u-cell-item  title="昵称" @click="goMyNickname" arrow-direction="right">
				<view class="u-flex-1">
					<view class="u-font-18 u-p-b-20">嘉然</view>
				</view>
			</u-cell-item>
			<u-cell-item  title="手机" @click="goMyTelNumber" arrow-direction="right">
				<view class="u-flex-1">
					<view class="u-font-14 u-tips-color">tel:111</view>
				</view>
			</u-cell-item>
		</u-cell-group>
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
			goMyAvatar(){
				uni.navigateTo({
					url:'../home/my-avatar'
				})
			},
			goMyNickname(){
				uni.navigateTo({
					url:'../home/my-nickname'
				})
			},
			goMyTelNumber(){
				uni.navigateTo({
					url:'../home/my-telnumber'
				})
			}
		}
	}
</script>

<style>
</style>
