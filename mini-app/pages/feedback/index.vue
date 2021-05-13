<template>
	<!-- feedback页面容器开始 -->
	<view class="wrap">
		<!-- 轮播图、公告组件容器开始 -->
		<view>
			<!-- 轮播图组件 -->
			<view>
				<u-swiper :height="300" :border-radius="10" :list="swiper" :title="title" :indicator-pos="indicatorPos"
					:mode="mode" :interval="3000"></u-swiper>
			</view>
			<!-- 公告组件 -->
			<view>
				<u-notice-bar mode="vertical" :list="notice" :indicator-pos="indicatorPos" :interval="3000" :disable-touch="false" @click="changeNoticeIndex($event)"></u-notice-bar>
			</view>
			<!-- 详细公告 -->
			<u-popup 
				v-model="noticeShow"
				mode="center" 
				border-radius="10" 
				length="80%" 
				:mask="mask"
				:closeable="closeable"
				:close-icon-pos="closeIconPos">
					<!-- 文字与关闭按钮 -->
					<view style="height: 800rpx">
						<view class="info-title">公告</view>
						<view class="info-text">{{ notice[noticeIndex] }}</view>
						<view class="close-btn">
							<u-button @click="noticeShow = false;" :custom-style="customStyle" :ripple="true" ripple-bg-color="#A55F91" size="medium">关闭</u-button>
						</view>
					</view>
			</u-popup>
		</view>
		<!-- 轮播图、公告组件容器结束 -->
		
		<!-- 表单组件容器开始 -->
		<view>
			<!-- 表单填写组件 -->
			<u-form :model="form" @submit="submit" :rules="rules" ref="uForm" :errorType="errorType">
				
				<!-- 问题和意见组件 -->
				<u-form-item
					:label-position="labelPosition"
					:label-style="labelStyle" 
					label="问题和意见"
					:required="true"
					prop="feedback"
				>
					<!-- 文字输入框 -->
					<u-input type="textarea"
						@input="sumChar"
						:auto-height="false"
						:border="border"
						maxlength="200" 
						placeholder="请填写5个字以上的问题描述,以使我们为您提供更好的帮助"
						v-model="form.feedback"
					/>
				</u-form-item>
				
				<!-- 问题配图组件 -->
				<u-form-item
					class="upload-btn"
					:label-position="labelPosition"
					:label-style="labelStyle"
					label="图片(选填,提供照片或截图)"
					prop="photo"
				>
					<!-- 图片带参上传组件 -->
					<u-upload
						@on-list-change="onListChange"
						@on-Success="onSuccess"
						@on-Remove="onRemove"
						ref="uUpload"
						:custom-btn="true"
						:auto-upload="true"
						:action="action"
						:file-list="fileList"
						:max-size="5 * 1024 * 1024"
						max-count="5"
						width="160"
						height="160"
						upload-text="选取照片"
						del-color="#ffffff"
						del-bg-color="#A55F91"
					>
						<!-- 添加图片按钮 -->
						<view slot="addBtn" class="slot-btn" hover-class="slot-btn__hover" hover-stay-time="150">
							<u-icon name="plus" size="35" :color="$u.color['darkColor']"></u-icon>
						</view>
					</u-upload>
				</u-form-item>
			</u-form>
			<!-- 表单填写组件结束 -->
			
			<!-- 已输入字数统计 -->
			<view class="char-num">{{charNum}}/200</view>
			<!-- 已选择图片数统计 -->
			<view class="photo-num">{{photoNum}}/5</view>
			<!-- 表单提交按钮 -->
			<view class="submit-btn">
				<u-button @click="submit" :custom-style="customStyle" :ripple="true" ripple-bg-color="#A55F91" size="medium">提交</u-button>
			</view>
		</view>
		<!-- 表单组件容器结束 -->
		
		<!-- 提交成功提示 -->
		<u-toast ref="uToast" />
	</view>
	<!-- feedback页面容器结束 -->
</template>

<script>
	export default {
		data() {
			return {
				//服务器地址
				action: 'https://api.shawnxixi.icu/api/upload_pic',
				//表格上传所带参数
				form:{
					feedback: "",
					pictureUrl: "",
				},
				
				//图片文件列表
				fileList: [],
				imageList :[],
				
				//公告列表
				noticeList: [],
				
				//公告内容
				notice: ['公告1','公告2','公告3',],

				//轮播图内容
				swiper: [{
						image: '/static/swiper-img/sp-img-1.jpg',
						title: 'image1',
					},
					{
						image: '/static/swiper-img/sp-img-2.jpg',
						title: 'image2',
					},
					{
						image: '/static/swiper-img/sp-img-3.jpg',
						title: 'image3',
					},
					{
						image: '/static/swiper-img/sp-img-4.jpg',
						title: 'image4',
					}
				],
				
				rules: {
					//问题反馈的props，此项为必填，最少5个字符，最多200个字符。
					feedback: [
						{
							required: true,
							//message: '请填写问题',
							trigger: 'blur' ,
						},
						{
							min: 5,
							max: 200,
							//message: '问题长度在5到200个字符',
							trigger: ['change','blur'],
						}
					],
				},
				
				//提交按钮定制样式
				customStyle: {
					color: "#A55F91",
					width: "240rpx",
					height: "80rpx",
					fontStyle: 'bold'
				},
				
				//表单标签定制样式
				labelStyle:{
					color: "#808080",
				},
				
				charNum: 0,//已输入字符数
				photoNum: 0,//已选择图片数
				
				//轮播图样式
				title: false,
				mode: 'rect',
				indicatorPos: 'bottomCenter',
				
				//表单样式
				labelPosition: 'top',
				border: true,				
				errorType: ['border'],//违法输入以红色边框形式进行提示
				
				//弹窗样式
				mask: true, // 是否显示遮罩
				closeable: true,
				closeIconPos: 'top-right',
				noticeIndex: 0,
				noticeShow: false,
				
			};
		},
		
		onLoad() {
			this.$api.Notice.getNoticeList().then(res => {
				this.noticeList = res.data.data.noticeList;
				this.notice[0] = this.noticeList[0].content;
				this.notice[1] = this.noticeList[1].content;
				this.notice[2] = this.noticeList[2].content;
			})
		},
		
		onReady() {
			//加载表单填写验证规则
			this.$refs.uForm.setRules(this.rules);
		},
		
		methods: {
			
			//已输入字数统计
			sumChar(){
				this.charNum=this.form.feedback.length;
			},
			
			//已选择图片数统计
			onListChange(lists, name) {
				this.photoNum=lists.length;
			},
			
			onSuccess(data, index, lists){
				//页面上定义的临时存放图片的对象，提示也保存后台返回的图片名称
				let url = data.data.pictureUrl;
				//成功上传一个图片就往fileList里面添加一个图片对象
				this.imageList.push(url);
				//this.form.pictureUrl为后台图片字段来保存字符串类型的图片集合
				this.form.pictureUrl = JSON.stringify(this.imageList)
				console.log("打印图片List：onSuccess", this.imageList);
			},
			
			onRemove(index, lists){
				//获得删除指定选择图片后的新文件列表
				this.imageList = [...this.imageList.slice(0, index) , ...this.imageList.slice(index + 1)]
				this.form.pictureUrl = JSON.stringify(this.imageList)
				console.log("打印图片List：onRemove", this.imageList);
			},
			
			//提交按钮点击事件
			submit() {
				this.$refs.uForm.validate(valid => {
					if (valid) {
						console.log('验证通过');
						let text = `${this.form.feedback}[imageList]${this.form.pictureUrl}`
						let picture = ''
						console.log(JSON.stringify(text,picture));
						this.$api.User.feedback(text,picture).then(res => {
							this.$refs.uToast.show({
									title: '提交成功',
									type: 'success',
							})
							//this.$refs.uUpload.upload();
							//重置表单
							this.form=this.$options.data().form;
							this.charNum=0;
							this.$refs.uUpload.clear();
							this.fileList = [];
						}).catch(err => {
							// 失败提示信息
							this.$refs.uToast.show({
									title: '提交失败',
									type: 'fail',
							})
						})
					} else {
						this.$refs.uToast.show({
								title: '验证失败',
								type: 'fail',
						})
					}
				});
			},
			
			changeNoticeIndex(index) {
				this.noticeIndex = index
				this.noticeShow = true
			}

		}
	};
</script>

<style lang="scss" scoped>
	view,
	text {
		box-sizing: border-box;
	}

	.wrap {
		padding: 35rpx;
	}
	
	.char-num{
		position: absolute;
		top:444rpx;
		right:60rpx;
		color: $uni-text-color-placeholder;
	}
	
	.photo-num{
		position: absolute;
		top:886rpx;
		right:60rpx;
		color: $uni-text-color-placeholder;
	}
	
	.info-title {
		padding-top: 70rpx;
		font: bold 50rpx arial,sans-serif;
		text-align: center;
	}
	
	.info-text {
		padding: 40rpx 40rpx;
		font-size: 35rpx;
	}
	
	.close-btn {
		position: absolute;
		top:650rpx;
		right:180rpx;
		text-align: center;
	}

	.upload-btn {
		margin: 0rpx 0;
		text-align: center;
	}
	
	.submit-btn {
		margin: 90rpx 0;
		text-align: center;
	}	
	
	.slot-btn {
		width: 160rpx;
		height: 160rpx;
		border: 1.2px solid #e7e7db;
		display: flex;
		justify-content: center;
		align-items: center;
		background: $uni-bg-color;
		border-radius: $uni-border-radius-lg;
	}
	
	.slot-btn__hover {
		background-color: $uni-bg-color-hover;
	}
	
</style>
