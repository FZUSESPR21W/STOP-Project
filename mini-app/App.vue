<script>
	export default {
		onLaunch: function() {
			console.log('App Launch')
			uni.login({
				provider: 'weixin',
				success: (res) => {
					this.$api.User.login(res.code).then(
						(res) => {
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
			
		},
		onShow: function() {
		},
		onHide: function() {
		}
	}
</script>

<style lang="scss">
	/*每个页面公共css */
	@import "./static/iconfont.css";
	@import "uview-ui/index.scss";
</style>
