<script>
	export default {
		onLaunch: function() {
			console.log('App Launch')
			uni.login({
				provider: 'weixin',
				success: (res) => {
					//console.log(res)
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
			
		},
		onShow: function() {
			//console.log('App Show')
		},
		onHide: function() {
			//console.log('App Hide')
		}
	}
</script>

<style lang="scss">
	/*每个页面公共css */
	@import "./static/iconfont.css";
	@import "uview-ui/index.scss";
</style>
