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
				<view style="height: 1000rpx">
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
				<view style="height: 1000rpx">
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
				<view style="height: 1000rpx">
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
				privText: "本小程序尊重并保护所有使用服务用户的个人隐私权。为了给您提供更准确、更有个性化的服务，本小程序收集的有关您的信息和资料将保存在本小程序及（或）其关联的服务器上，这些信息和资料可能传送至您所在地区被访问、存储和展示。但本小程序将以高度的勤勉、审慎义务对待这些信息。在未征得您事先许可的情况下，本小程序不会将这些信息对外披露或向第三方提供。本小程序会不时更新本隐私权政策。",
				protTitle: "服务协议",
				protText: "你在使用本产品过程中，需要授予小程序部分权限，请保持这些权限始终开放，以便我们向你提供及时有效的帮助，或更好地为你提供服务。若你关闭这部分权限，则可能无法使用本服务或在使用过程中受到限制。你理解并同意，为了向你提供服务，需要在法律允许的范围内向小程序提供这些权限。",
				
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
		top:850rpx;
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
