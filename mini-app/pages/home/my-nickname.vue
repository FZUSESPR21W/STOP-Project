<template>
	<view class="">
		<u-form :model="form" ref="uForm">
			<u-form-item label="昵称" prop="name" >
				<u-input v-model="form.name" />
			</u-form-item>
		</u-form>
		<u-button :custom-style="customStyle" @click="handleSummit">确定修改</u-button>
	</view>
</template>

<script>
export default {
	data() {
		return {
			form: {
				name: '',
			},
			rules: {
				name: [
					{ 
						required: true, 
						message: '昵称不能为空', 
						// 可以单个或者同时写两个触发验证方式 
						trigger: ['change','blur'],
					}
				],
			},
			customStyle: {
				color: "#A55F91",
				fontStyle: 'bold'
			},
		};
	},
	methods: {
		handleSummit() {
			this.$refs.uForm.validate(valid => {
				if (valid) {
					uni.switchTab({
						url:"index"
					})
				}
			});
		}
	},
	// 必须要在onReady生命周期，因为onLoad生命周期组件可能尚未创建完毕
	onReady() {
		this.$refs.uForm.setRules(this.rules);
	}
};
</script>
