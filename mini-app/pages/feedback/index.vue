<template>
	<view class="wrap">
		<view>
			<view>
				<u-swiper :height="300" :border-radius="10" :list="swiper" :title="title" :indicator-pos="indicatorPos"
					:mode="mode" :interval="3000"></u-swiper>
			</view>
			<view>
				<u-notice-bar mode="horizontal" :list="notice" :indicator-pos="indicatorPos" :interval="3000"></u-notice-bar>
			</view>
		</view>
		<view>
			<u-form :model="model" :rules="rules" ref="uForm" :errorType="errorType">
				<u-form-item :label-position="labelPosition" :label-style="labelStyle" label="问题和意见" :required="true" prop="feedback">
					<u-input type="textarea" @input="sumChar" :auto-height="false" :border="border" maxlength="200" 
					placeholder="请填写5个字以上的问题描述,以使我们为您提供更好的帮助" v-model="model.feedback" />
				</u-form-item>
				<u-form-item class="upload-btn" :label-position="labelPosition" :label-style="labelStyle" label="图片(选填,提供照片或截图)" prop="photo">
					<u-upload @on-list-change="onListChange" ref="uUpload" :custom-btn="true" :auto-upload="false" :action="action" :max-size="5 * 1024 * 1024"
					 max-count="5" width="160" height="160" upload-text="选取照片" del-color="#ffffff" del-bg-color="#A55F91" v-model="model.photo">
						<view slot="addBtn" class="slot-btn" hover-class="slot-btn__hover" hover-stay-time="150">
							<u-icon name="plus" size="35" :color="$u.color['darkColor']"></u-icon>
						</view>
					</u-upload>
				</u-form-item>
			</u-form>
			<view class="char-num">{{charNum}}/200</view>
			<view class="photo-num">{{photoNum}}/5</view>
			<view class="submit-btn">
				<u-button @click="submit" :custom-style="customStyle" :ripple="true" ripple-bg-color="#A55F91" size="medium">提交</u-button>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				action: '',
				model: {
					feedback: '',
					photo: ''
				},
				notice: [
					'西三日常拥堵',
					'东三日常拥堵',
					'到处都在拥堵'
				],

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
			};
		},
		onLoad() {
		
		},
		onReady() {
			this.$refs.uForm.setRules(this.rules);
		},
		methods: {
			
			//已输入字数统计
			sumChar(){
				this.charNum = this.model.feedback.length;
			},
			
			//已选择图片数统计
			onListChange(lists, name) {
				this.photoNum=lists.length;
			},
			
			submit() {
				this.$refs.uUpload.upload();
			},

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
