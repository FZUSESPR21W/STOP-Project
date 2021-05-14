<template>
	<!-- 个人信息页面 -->
	<view class="user-info">
		<u-cell-group style="width: -webkit-fill-available;">
			<u-cell-item  title="头像" :arrow="false">
				<view class="u-m-r-10">
					<open-data type="userAvatarUrl"></open-data>
				</view>
			</u-cell-item>
			<u-cell-item  title="昵称" :arrow="false">
				<view class="u-flex-1">
					<view class="u-font-18 u-p-b-20"><open-data type="userNickName"></open-data></view>
				</view>
			</u-cell-item>
			<u-cell-item  title="城市" :arrow="false">
				<view class="u-flex-1">
					<open-data type="userCity"></open-data>
				</view>
			</u-cell-item>
		</u-cell-group>
	</view>
</template>

<script>
	export default {
		data() {
			return {
			}
		},
		onLoad() {
		},
		methods: {
			
		}
	}
</script>

<style>
	.u-m-r-10{
		width: 110rpx;
		height: 110rpx;
	    position: absolute;
	    left: 100%;
	    transform: translate3d(-130%,-50%,0);
		overflow: hidden;
		border-radius: 50%;
	}
</style>
