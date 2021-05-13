<template>
	<!-- 个人信息页面 -->
	<view class="user-info">
		<u-cell-group style="width: -webkit-fill-available;">
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
			}
		},
		onLoad() {
			// 从本地缓存异步获取指定key对应内容
			uni.getStorage({
			    key: 'user-info_key',
			    success: (res) => {
					this.userInfo = res.data;
			    }
			});
		},
		methods: {
			
		}
	}
</script>

<style>
</style>
