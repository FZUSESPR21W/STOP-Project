<template>
	<view class="wrap">
		<view class="item-logo">
			<u-image width="300rpx" height="300rpx" :src="src"></u-image>
		</view>
		<view class="item-text-t">STOP</view>
		<view class="item-text-n">Smart Transportation Of Parking</view>
		<view class="item-text-v">Version 0.0.1</view>
		<u-cell-group>
			<u-cell-item  title="功能介绍" @click="showFunc" arrow-direction="right"></u-cell-item>
			<u-cell-item  title="隐私政策" @click="showPriv" arrow-direction="right"></u-cell-item>
			<u-cell-item  title="服务协议" @click="showProt" arrow-direction="right"></u-cell-item>
		</u-cell-group>
		<view class="item-text-c">Copyright 2021 发际线与我作队</view>
		<view class="item-text-a">All Rights Reserved</view>
		
		<view>
			<u-popup border-radius="10" v-model="funcShow"
				@close="close" @open="open" :mode="mode" 
				length="80%" :mask="mask"
				:closeable="closeable"
				:close-icon-pos="closeIconPos"
			>
				<view style="height: 800rpx">
					<view class="info-title">{{funcTitle}}</view>
					<view class="info-text">{{funcText}}</view>
					<view class="close-btn">
						<u-button @click="funcShow = false;" :custom-style="customStyle" :ripple="true" ripple-bg-color="#A55F91" size="medium">关闭</u-button>
					</view>
				</view>
			</u-popup>
			
			<u-popup border-radius="10" v-model="privShow"
				@close="close" @open="open" :mode="mode" 
				length="80%" :mask="mask"
				:closeable="closeable"
				:close-icon-pos="closeIconPos"
			>
				<view style="height: 800rpx">
					<view class="info-title">{{privTitle}}</view>
					<view class="info-text">{{privText}}</view>
					<view class="close-btn">
						<u-button @click="privShow = false;" :custom-style="customStyle" :ripple="true" ripple-bg-color="#A55F91" size="medium">关闭</u-button>
					</view>
				</view>
			</u-popup>
			
			<u-popup border-radius="10" v-model="protShow"
				@close="close" @open="open" :mode="mode" 
				length="80%" :mask="mask"
				:closeable="closeable"
				:close-icon-pos="closeIconPos"
			>
				<view style="height: 800rpx">
					<view class="info-title">{{protTitle}}</view>
					<view class="info-text">{{protText}}</view>
					<view class="close-btn">
						<u-button @click="protShow = false;" :custom-style="customStyle" :ripple="true" ripple-bg-color="#A55F91" size="medium">关闭</u-button>
					</view>
				</view>
			</u-popup>
		</view>
		
	</view>
</template>

<script>
	export default{
		data() {
			return{
				subTitle: '',
				showLine: false,
				fontSize: '40',
				color: '#808080',
				funcShow: false,
				privShow: false,
				protShow: false,
				mode: 'center',
				mask: true, // 是否显示遮罩
				closeable: true,
				closeIconPos: 'top-right',
				funcTitle: "功能介绍",
				funcText: "本软件能快速准确识别指定区域内电动车数量，让用户了解目的地周边停车区域车位占用状况、拥挤程度，对停车乱象可以进行拍照投诉；同时提供可视化数据，方便电动车管理员进行统筹管理、发布公告、接收意见反馈。最终达到缓解用户找车位难，停车难问题的效果。",
				privTitle: "隐私政策",
				privText: "隐私政策隐私政策隐私政策隐私政策隐私政策隐私政策",
				protTitle: "服务协议",
				protText: "服务协议服务协议服务协议服务协议服务协议服务协议",
				customStyle: {
					color: "#A55F91",
					width: "240rpx",
					height: "80rpx",
					fontStyle: 'bold'
				},
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
