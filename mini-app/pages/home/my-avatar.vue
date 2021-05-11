<template>
	<view>
		<u-upload ref="uUpload" :action="action" :auto-upload="false" ></u-upload>
		<u-button :custom-style="customStyle" @click="submit">提交</u-button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				action: '',
				filesArr: [],
				customStyle: {
					color: "#A55F91",
					fontStyle: 'bold'
				},
				
			}
		},
		methods: {
			submit() {
				let files = [];
				// 通过filter，筛选出上传进度为100的文件(因为某些上传失败的文件，进度值不为100，这个是可选的操作)
				files = this.$refs.uUpload.lists.filter(val => {
					return val.progress == 100;
				})
				// 如果您不需要进行太多的处理，直接如下即可
				// files = this.$refs.uUpload.lists;
				console.log(files)
			}
		}
	}
</script>