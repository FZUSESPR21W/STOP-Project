<script>
	export default {
		onLaunch: function() {
			console.log('App Launch')
			uni.login({
				provider: 'weixin',
				success: (res) => {
					console.log(res)
					this.$api.User.login(res.code).then(
						(res) => {
							// console.log(res)
							uni.setStorage({
								key: 'tokenKey',
								data: res.data.data.tokenKey
							})
							uni.setStorage({
								key: 'tokenValue',
								data: res.data.data.tokenValue
							})
						})
				}
			})
			uni.getSetting({
				success(res) {
					if (!res.authSetting['scope.userLocation'] || !res.authSetting[
							'scope.userLocationBackground']) {
						// 未授权
						uni.authorize({
							scope: 'scope.userLocation',
							success() { //1.1 允许授权
								uni.getLocation({
									success(res) {
										console.log(res)
									}
								})
							},
							fail() { //1.2 拒绝授权
								console.log("你拒绝了授权，无法正常使用小程序部分功能")
							}
						})
						// uni.authorize({
						// 	scope: 'scope.userLocationBackground',
						// 	success() { //1.1 允许授权
						// 		uni.getLocation({
						// 			success(res) {
						// 				console.log(res)
						// 			}
						// 		})
						// 	},
						// 	fail() { //1.2 拒绝授权
						// 		console.log("你拒绝了授权，无法正常使用小程序部分功能2")
						// 	}
						// })
					} else {
						// 已授权 ..(获取位置信息)
						uni.getLocation({
							success(res) {
								console.log(res)
							}
						})
					}
				}
			})
			
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style lang="scss">
	/*每个页面公共css */
	@import "./static/iconfont.css";
	@import "uview-ui/index.scss";
</style>
