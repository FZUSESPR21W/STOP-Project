<template>
	<view class="u-m-t-20">
		<u-cell-group>
			<u-cell-item  title="头像" :arrow="false">
				<view class="u-m-r-10">
					<u-avatar :src="pic" size="140" ></u-avatar>
				</view>
			</u-cell-item>
			<u-cell-item  title="昵称" :arrow="false">
				<view class="u-flex-1">
					<view class="u-font-18 u-p-b-20">嘉然</view>
				</view>
			</u-cell-item>
			<u-cell-item  title="地区" :arrow="false">
				<view class="u-flex-1">
					<view class="u-font-14 u-tips-color">福建福州</view>
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
			
		}
	}
</script>

<style>
</style>
