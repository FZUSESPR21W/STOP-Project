<template>
	<view>
		<!-- map组件开始 -->
		<view>
			<!-- <ParkingMap @hideList="hideList" /> -->
		</view>
		<!-- map组件结束 -->
		<!-- 推荐地址框容器开始 -->
		<view @touchmove.stop.prevent="noScrolling">
			<uni-transition custom-class="location-box" :show="showLocationBox" ref="locationBox">

				<!-- 向上导航条容器开始 -->
				<view style="width: 100%;padding-top:10px; padding-bottom: 4px;" @click="showList">
					<uni-transition mode-class="fade" custom-class="exhale-bar" :show="showExhaleBar" />
				</view>
				<!-- 向上导航条容器结束 -->
				<!-- 搜索框容器开始 -->
				<view class="input-box-border" :style="{border:inputBorderColor}" @click="clickInputBox">
					<input type="text" class="input-box uni-input" placeholder="查询想要停车的地点" maxlength="40"
						:focus="isFocus" :disabled="inputDisable" :adjust-position="false" confirm-type="search"
						@blur="inputBlur" />
					<u-icon style="position: absolute;top: 6rpx;" slot="icon" custom-prefix="custom-icon"
						color="#A35C8F" size="32px" name="search()"></u-icon>
				</view>
				<view style="height: fit-content;">
					<scroll-view scroll-y :style="{height:scrollHeight}" @scrolltolower="addShowNum"
						enable-back-to-top="true">
						<!-- 搜索框容器结束 -->
						<view class="place-list" v-show="showPlaceList">
							<view>
								<view class="place-list-title" style="">最近适合停车的地点</view>
								<view class="place-list-title-bar"></view>
								<view class="place-list-item" v-for="(placeItem,index) in placeList.slice(0,showNum)"
									:key="index" @click="clickItem">
									<u-icon slot="icon" custom-prefix="custom-icon" color="#A35C8F" size="90rpx"
										name="position-icon"></u-icon>
									<view class="place-list-item-title">{{placeItem.title}}</view>
									<view class="place-list-item-parking">{{placeItem.parking}}</view>
									<view class="place-list-item-distance">{{placeItem.distance}}m</view>
									<u-icon slot="icon" custom-prefix="custom-icon" color="#A35C8F" size="60rpx"
										name="navigation" style="float: right;margin-right: 4px;"></u-icon>
								</view>

								<view style="width: fit-content;margin: 0 auto;" v-show="showMore">
									<view @click="clickMore">
										<u-icon slot="icon" custom-prefix="custom-icon" color="#A35C8F" size="40rpx"
											name="more" style="padding: 30rpx 60rpx 20rpx 60rpx;"></u-icon>
									</view>
								</view>
							</view>
						</view>
					</scroll-view>
				</view>
			</uni-transition>
		</view>
		<!-- 推荐地址框容器结束 -->
		<!-- 地点详细弹窗开始 -->
		<u-popup v-model="popupShow" mode="bottom" border-radius=10 height="44%" :safe-area-inset-bottom="false"
			@close="closePopup"
			:mask="true" :mask-close-able="true">
			<view style="font-size: 50rpx;margin: 0 auto;text-align: center;margin-top: 14rpx;">福州大学-东三</view>
			<view style="height: 34vh; position: absolute;bottom:10rpx;left: 13vh;">
			<u-circle-progress active-color="#ac358f" :percent="80" duration="1000" :width="400" :show="showCircle">
				<view class="u-progress-content">
					<text class='u-progress-info'>车位空闲</text>
					<u-button @click="submit" :custom-style="customStyle" :ripple="true" ripple-bg-color="#A55F91" size="medium">导航</u-button>
				</view>
			</u-circle-progress>
			</view>
		</u-popup>
		<!-- 地点详细弹窗结束 -->
	</view>
</template>

<script>
	import ParkingMap from './parking-map/index.vue';
	export default {
		name: 'Parking',
		data() {
			return {
				//显示圆形进度条
				showCircle:false,
				//显示弹窗
				popupShow: false,
				//从点击输入框添加显示数目
				addShowNumFromInput: false,
				//是否显示更多按钮
				showMore: true,
				//scroll-view高度
				scrollHeight: '',
				//推荐的地点展示的条数
				showNum: 2,
				//输入框是否自动聚焦
				isFocus: false,
				//是否禁用输入框
				inputDisable: true,
				//是否显示向上呼出导航条
				showExhaleBar: false,
				//是否显示推荐地址列表
				showPlaceList: true,
				//是否显示地址框
				showLocationBox: true,
				//绑定输入框外框颜色
				inputBorderColor: '1px solid #bfbfbf',
				//地点列表
				placeList: [],
				//圆形进度条点的颜色
				progressDot:'#000000',
			}
		},
		components: {
			ParkingMap,
		},

		methods: {
			//弹出层关闭
			closePopup(){
				console.log('test');
				this.showCircle=false;
			},

			clickItem() {
				console.log('1');
				//this.$refs.popup.open('top');
				this.popupShow = true;
				setTimeout(()=>{
					this.showCircle=true;
				},700);

			},

			//禁止滚动
			//该方法解决IOS下点击地点框会层级穿透至map组件内的BUG
			noScrolling() {
				return false;
			},

			//点击更多按钮
			clickMore() {
				this.changeLocationBox(2);
				this.addShowNum();
				setTimeout(() => {
					this.inputDisable = true;
				}, 300);

			},

			//新增显示数量
			addShowNum() {
				//新增数目步长7
				const step = 7;
				let showNum = this.showNum;
				const num = this.placeList.length;
				if (showNum >= num) {
					//如果已显示数已经少于总数就直接返回，不增加
					return;
				} else {
					showNum += step;
					if (showNum >= num) {
						this.showMore = false;
					}
				}
				this.showNum = showNum;
			},

			//底栏动画
			runStatus0() {
				this.inputDisable = true;
				this.$refs.locationBox.step({
					height: '14vh',
					top: '86vh'
				})
				this.$refs.locationBox.run(() => {
					//console.log('执行完毕')
					this.addShowNumFromInput = false;
				});
			},

			//半屏动画
			runStatus1() {
				this.showNum = 2;
				this.showMore = true;
				this.inputDisable = true;
				this.$refs.locationBox.step({
					height: '44vh',
					top: '56vh'
				})
				this.$refs.locationBox.run(() => {
					//_this.isFocus=false;
					this.addShowNumFromInput = false;
					//console.log('执行完毕')
				});
			},

			//全屏动画
			runStatus2() {
				this.$refs.locationBox.step({
					height: '90vh',
					top: '10vh'
				})
				this.$refs.locationBox.run(() => {
					setTimeout(() => {
						this.isFocus = true;
					}, 50);
					this.inputDisable = false;
					//console.log('执行完毕')
				});
			},

			//输入框失去焦点
			inputBlur(res) {
				this.inputBorderColor = '1px solid #bfbfbf';
			},

			//点击输入框
			clickInputBox() {
				this.showPlaceList = true;
				//获取焦点时修改边框颜色，因:style不支持绑定border-color,所以绑定了整个border
				this.inputBorderColor = '1px solid #a35c8f';
				this.changeLocationBox(2);
				if (!this.addShowNumFromInput) {
					this.addShowNum();
					this.addShowNumFromInput = true;
				}
			},

			//显示推荐地点列表
			showList() {
				this.changeLocationBox(1);
			},

			//收到map组件传回来的值，显示呼出条、隐藏地点列表
			hideList(res) {
				this.changeLocationBox(0);
			},

			//更改地点框状态，0：底栏(默认)；1：半屏；2：全屏；
			changeLocationBox(status) {
				if (status === 0) {
					this.isFocus = false;
					this.showExhaleBar = true;
					this.scrollHeight = '';
					this.showPlaceList = false;
					this.runStatus0();
				} else if (status === 1) {
					this.isFocus = false;
					this.runStatus1();
					this.scrollHeight = '';
					this.showExhaleBar = false;
					this.showPlaceList = true;
				} else if (status === 2) {
					this.showExhaleBar = true;
					this.scrollHeight = '78vh'
					this.runStatus2();
				}
			},

			//获取tarbar高度
			getTarbarHeight() {
				const info = wx.getSystemInfo();
				console.log(wx.getSystemInfo());
				const tabbarHeight = (info.screenHeight - info.windowHeight - info.statusBarHeight) * info.pixelRatio;
				return tabbarHeight;
			}
		},
		created() {
			this.placeList = [{
				title: '福州大学-东三',
				parking: '车位空余',
				distance: '200',
			}];
			this.placeList[1] = this.placeList[0];
			this.placeList[2] = this.placeList[0];
			this.placeList[3] = this.placeList[0];
			this.placeList[4] = this.placeList[0];
			this.placeList[5] = this.placeList[0];
			this.placeList[6] = this.placeList[0];
			this.placeList[7] = this.placeList[0];
			this.placeList[8] = this.placeList[0];
			this.placeList[9] = this.placeList[0];
			this.placeList[10] = this.placeList[0];
			this.placeList[11] = this.placeList[0];
		},
	}
</script>

<style scoped lang="scss">
	// 地点列表
	.place-list {

		// 地点列表标题
		.place-list-title {
			margin: 4px 0 2px 10px;
		}

		// 地点列表标题条
		.place-list-title-bar {
			background-color: rgba($color: #a35c8f, $alpha: 0.8);
			height: 1.6px;
			width: 160px;
			margin-bottom: 8px;
		}

		// 地点列表标题子项
		.place-list-item {
			background-color: white;
			margin-left: 20px;
			margin-right: 20px;
			margin-bottom: 14px;
			margin: 0 20px 14px 20px;
			position: relative;
			padding: 2px;
			border-bottom: 1px solid rgba($color: #8c8c8c, $alpha: 0.8);

			// 子项标题
			.place-list-item-title {
				color: rgba($color: #595959, $alpha: 1.0);
				font-size: 40rpx;
				display: inline-block;
				position: absolute;
				overflow: hidden;
				text-overflow: ellipsis;
				white-space: nowrap;
				width: 12em;
			}

			// 子项剩余车量
			.place-list-item-parking {
				display: inline-block;
				color: rgba($color: #7DCC76, $alpha: 1);
			}

			// 子项距离
			.place-list-item-distance {
				display: inline-block;
				position: absolute;
				right: 0;
				bottom: 0;
				margin-right: 4px;
				color: #8c8c8c;
			}
		}

	}

	// 地点框
	/deep/.location-box {
		z-index: 2;
		background-color: white;
		width: 100%;
		border-radius: 10px 10px 0 0;
		border-top: 1px solid rgba(191, 191, 191, 0.4);
		position: fixed !important;
		height: 44vh;
		top: 56vh
	}

	// 呼出条
	/deep/.exhale-bar {
		width: 20%;
		height: 1vh;
		margin: 0 auto;
		border-radius: 50px;
		background-color: rgba(89, 89, 89, 0.7);
	}

	// 输入框外框
	.input-box-border {
		height: 40px;
		margin: 0 auto;
		margin-top: 10px;
		margin-bottom: 4px;
		width: 86%;
		border-radius: 10px;
		position: relative;
	}

	// 输入框
	.input-box {
		width: 84%;
		height: 100%;
		margin: 4rpx 2vh 0 10rpx;
		display: inline-block;
		font-size: 18px;
	}

	// 推荐地点列表
	.place-list {
		margin-top: 10px;
		background-color: white;
		height: 100%;
	}

.u-progress-content {
		position: absolute;
		align-items: center;
		justify-content: center;
	}
	
	
	.u-progress-info {
		margin-bottom: 10rpx;
		display: block;
		font-size: 50rpx;
		padding-left: 16rpx;
		letter-spacing: 2rpx
	}
</style>
