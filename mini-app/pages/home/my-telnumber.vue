<template>
	<view class="">
		<u-form :model="form" ref="uForm">
			<u-form-item label="手机" prop="telnumber" >
				<u-input v-model="form.telnumber" />
			</u-form-item>
		</u-form>
		<u-button :custom-style="customStyle" @click="uni.setStorage({
			
		})">确定修改</u-button>
	</view>
</template>

<script>

	export default {
		data() {
			return {
				form: {
					telnumber: '',
				},
				customStyle: {
					color: "#A55F91",
					fontStyle: 'bold'
				},
			};
		},
		methods: {
			handleSummit() {
							uni.switchTab({
								url:"index"
							})
						},
		},
		// 必须要在onReady生命周期，因为onLoad生命周期组件可能尚未创建完毕
		onReady() {
			this.$refs.uForm.setRules(this.rules);
		},
	};
	
</script>

<style lang="scss">

</style>