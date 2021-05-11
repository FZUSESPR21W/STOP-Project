<template>
	<!-- aboutus页面容器开始 -->
	<view class="wrap">
		<!-- 小程序logo -->
		<view class="item-logo">
			<u-image width="300rpx" height="300rpx" :src="src"></u-image>
		</view>
		
		<!-- 小程序名称等信息 -->
		<view class="item-text-t">STOP</view>
		<view class="item-text-n">Smart Transportation Of Parking</view>
		<view class="item-text-v">Version 0.0.1</view>
		
		<!-- 小程序信息组件 -->
		<u-cell-group>
			<u-cell-item title="功能介绍" @click="showFunc" arrow-direction="right"></u-cell-item>
			<u-cell-item title="隐私政策" @click="showPriv" arrow-direction="right"></u-cell-item>
			<u-cell-item title="服务协议" @click="showProt" arrow-direction="right"></u-cell-item>
		</u-cell-group>
		
		<!-- 版权信息 -->
		<view class="item-text-c">Copyright © 2021 发际线与我作队</view>
		<view class="item-text-a">All Rights Reserved</view>
		
		<!-- 弹窗容器组件开始 -->
		<view>
			<!-- 查看功能介绍弹窗 -->
			<u-popup border-radius="10" v-model="funcShow"
				@close="close" @open="open" :mode="mode" 
				length="80%" :mask="mask"
				:closeable="closeable"
				:close-icon-pos="closeIconPos"
			>
				<!-- 文字与关闭按钮 -->
				<view style="height: 800rpx">
					<view class="info-title">{{funcTitle}}</view>
					<view class="info-text">{{funcText}}</view>
					<view class="close-btn">
						<u-button @click="funcShow = false;" :custom-style="customStyle" :ripple="true" ripple-bg-color="#A55F91" size="medium">关闭</u-button>
					</view>
				</view>
			</u-popup>
			
			<!-- 查看隐私政策弹窗 -->
			<u-popup border-radius="10" v-model="privShow"
				@close="close" @open="open" :mode="mode" 
				length="80%" :mask="mask"
				:closeable="closeable"
				:close-icon-pos="closeIconPos"
			>
				<!-- 文字与关闭按钮 -->
				<view style="height: 800rpx">
					<view class="info-title">{{privTitle}}</view>
					<view class="info-text">{{privText}}</view>
					<view class="close-btn">
						<u-button @click="privShow = false;" :custom-style="customStyle" :ripple="true" ripple-bg-color="#A55F91" size="medium">关闭</u-button>
					</view>
				</view>
			</u-popup>
			
			<!-- 查看服务协议弹窗 -->
			<u-popup border-radius="10" v-model="protShow"
				@close="close" @open="open" :mode="mode" 
				length="80%" :mask="mask"
				:closeable="closeable"
				:close-icon-pos="closeIconPos"
			>
				<!-- 文字与关闭按钮 -->
				<view style="height: 800rpx">
					<view class="info-title">{{protTitle}}</view>
					<view class="info-text">{{protText}}</view>
					<view class="close-btn">
						<u-button @click="protShow = false;" :custom-style="customStyle" :ripple="true" ripple-bg-color="#A55F91" size="medium">关闭</u-button>
					</view>
				</view>
			</u-popup>
		</view>
		<!-- 弹窗容器组件结束 -->
	</view>
	<!-- aboutus页面容器结束 -->
</template>

<script>
	export default{
		data() {
			return{
				//弹窗状态
				funcShow: false,
				privShow: false,
				protShow: false,
				
				//弹窗样式
				mode: 'center',
				mask: true, // 是否显示遮罩
				closeable: true,
				closeIconPos: 'top-right',
				
				//弹窗文本
				funcTitle: "功能介绍",
				funcText: "本软件能快速准确识别指定区域内电动车数量，让用户了解目的地周边停车区域车位占用状况、拥挤程度，对停车乱象可以进行拍照投诉；同时提供可视化数据，方便电动车管理员进行统筹管理、发布公告、接收意见反馈。最终达到缓解用户找车位难，停车难问题的效果。",
				privTitle: "隐私政策",
				privText: "隐私政策隐私政策隐私政策隐私政策隐私政策隐私政策",
				protTitle: "服务协议",
				protText: "服务协议服务协议服务协议服务协议服务协议服务协议",
				
				//关闭按钮样式
				customStyle: {
					color: "#A55F91",
					width: "240rpx",
					height: "80rpx",
					fontStyle: 'bold'
				},
				
				//小程序logo
				src: '/static/stopLogo.png'
			};
		},
		methods: {
			close() {
				// console.log('close');
			},
			open() {
				// console.log('open');
			},
			//打开弹窗事件
			showFunc() {
				this.funcShow = true;
			},
			showPriv() {
				this.privShow = true;
			},
			showProt() {
				this.protShow = true;
			},
		}
	}
</script>

<style>
	.wrap {
		padding: 35rpx;
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
	.item-logo {
		margin: 50rpx auto;
		width: 45%;
	}
	.item-text-t {
		font: bold 50rpx arial,sans-serif;
		margin-top: 0rpx;
		text-align: center;
	}
	.item-text-n {
		font-size: 35rpx;
		color: #a1a1a1;
		margin: 15rpx;
		text-align: center;
	}
	.item-text-v {
		font-size: 32rpx;
		color: #616161;
		margin: 30rpx;
		text-align: center;
	}
	.item-text-c {
		font-size: 22rpx;
		color: #a1a1a1;
		margin-top: 180rpx;
		text-align: center;
	}
	.item-text-a {
		font-size: 22rpx;
		color: #a1a1a1;
		text-align: center;
	}
</style>
