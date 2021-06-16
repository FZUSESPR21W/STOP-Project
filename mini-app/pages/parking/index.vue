<template>
	<view>
		<!-- map组件开始 -->
		<view>
			<ParkingMap @hideList="hideList" @clickCovers="clickCovers" @placeList="getPlaceList" :moveItem="itemId"/>
		</view>
		<!-- map组件结束 -->
		<!-- 推荐地址框容器开始 -->
		<view @touchmove.stop.prevent="noScrolling">
			<uni-transition custom-class="location-box" :show="showLocationBox" ref="locationBox" duration="0"
				:styles="{top:locationBoxTop*100+'vh !important',height:(1-locationBoxTop)*100+'vh !important'}">
				<!-- 向上导航条容器开始 -->
				<view style="width: 100%;padding-top:10px; padding-bottom: 4px;" @click="showList"
					@touchmove="touchLocationBox" @touchend="locationBoxReset">
					<uni-transition mode-class="fade" custom-class="exhale-bar" :show="showExhaleBar" />
				</view>
				<!-- 向上导航条容器结束 -->
				<!-- 搜索框容器开始 -->
				<view class="input-box-border" :style="{border:inputBorderColor}" @click="clickInputBox">
					<input type="text" class="input-box uni-input" placeholder="查询想要停车的地点" maxlength="40"
						:focus="isFocus" :disabled="inputDisable" :adjust-position="false" confirm-type="search"
						@blur="inputBlur" v-model="searchWord" @input="inputSearch" />
					<u-icon style="position: absolute;top: 6rpx;" slot="icon" custom-prefix="custom-icon"
						color="#A35C8F" size="32px" name="search()" @click="searchList"></u-icon>
				</view>
				<view style="height: fit-content;">
					<scroll-view scroll-y :style="{height:scrollHeight}" @scrolltolower="addShowNum"
						enable-back-to-top="true">
						<!-- 搜索框容器结束 -->
						<view class="place-list" v-show="showPlaceList">
							<!-- 数据为空时容器 -->
							<view style="display:flex; align-items:center;flex-direction:column;margin-top: 40rpx;"
								v-if="placeList.length==0">
								<u-empty text="数据获取失败,请尝试" font-size="32"></u-empty>
								<u-button :custom-style="customStyle" :ripple="true" ripple-bg-color="#A55F91"
									@click="refresh" size="medium"><span style="font-size: 30rpx;">刷新</span></u-button>
							</view>
							<view v-if="placeList.length!=0">
								<view v-show="showPlaceListTitle">
									<view class="place-list-title" style="">最近适合停车的地点</view>
									<view class="place-list-title-bar"></view>
								</view>
								<u-empty text="没有搜索结果" mode="search" v-if="showSearchPlaceList.length==0"
									margin-top="200"></u-empty>
								<view class="place-list-item"
									v-for="(placeItem,index) in showSearchPlaceList.slice(0,showNum)" :key="index"
									@click="clickItem(index)">
									<u-icon slot="icon" custom-prefix="custom-icon" color="#A35C8F" size="90rpx"
										name="position-icon"></u-icon>
									<view class="place-list-item-title">{{placeItem.name}}</view>
									<view class="place-list-item-parking" :style="{color:placeItem.surplusColor}">{{placeItem.surplus}}</view>
									<view class="place-list-item-distance">{{placeItem.distance}}</view>
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
			style="overflow: hidden;" @close="closePopup" :mask="true" :mask-close-able="true">
			<view style="font-size: 50rpx;margin: 0 auto;text-align: center;margin-top: 14rpx;">{{popupItem.name}}
			</view>
			<view>
				<view class="charts-box">
					<qiun-data-charts type="gauge"
						:opts="{title:{name: popupItem.surplus,color: popupItem.surplusColor,fontSize: 25,offsetY:50},subtitle: {name: popupItem.distance,color: '#666666',fontSize: 20,offsetY:-50}}"
						:chartData="chartData" background="none" :reshow="popupShow" v-show="popupShow"
						canvasId="hB5sgbkN4V9efHRjubKUqCAgGpFHaSky" :canvas2d="true" />
				</view>
				<u-button @click="navigateToPlace" :custom-style="customStyle" :ripple="true" ripple-bg-color="#A55F91"
					size="medium" style="position: absolute;left: 50%; transform: translateX(-50%);bottom: 30rpx;">导航
				</u-button>
			</view>
		</u-popup>
		<!-- 地点详细弹窗结束 -->
		<!-- 用户引导容器开始 -->
		<u-mask :show="isTiptrue" @click="closeThis">
			<view class='page-cont'>
				<view class='top'>
					<view class='p_one'>如何快速上手“我要停车”功能？</view>
				</view>
				<view class='cont'>
					<view class='cont-p'>
						<view class='text'><text>1</text></view>
						<view>拖出底部菜单或点击查询</view>
					</view>
					<view class='cont-p'>
						<view class='text'><text>2</text></view>
						<view>搜索或点击选中一个停车点，</view>
					</view>
					<view class='cont-p-lib'>
						<view class='text' style='opacity:0;'><text>3</text></view>
						<view>查看指定区域车位情况</view>
					</view>
					<view class='cont-p'>
						<view class='text'><text>3</text></view>
						<view>点击“导航”按钮，我们出发！</view>
					</view>
				</view>
			</view>
		</u-mask>
		<!-- 用户引导容器结束 -->
	</view>
</template>

<script>
	import ParkingMap from './parking-map/index.vue'
	import uCharts from '@/components/u-charts/u-charts-v2.0.0.js'
	var _self
	var canvaColumn = null
	export default {
		name: 'Parking',
		data() {
			return {
				//地点框距离顶部高度
				locationBoxTop: 0.86,
				//用户窗口宽度
				windowWidth: 0,
				//用户窗口高度
				windowHeight: 0,
				//是否搜索
				isSearch: false,
				//是否显示列表标题
				showPlaceListTitle: true,
				//搜索关键词
				searchWord: '',
				//显示圆形进度条
				showCircle: false,
				//显示弹窗
				popupShow: false,
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
				showExhaleBar: true,
				//是否显示推荐地址列表
				showPlaceList: false,
				//是否显示地址框
				showLocationBox: true,
				//绑定输入框外框颜色
				inputBorderColor: '1px solid #bfbfbf',
				//地点列表
				placeList: [],
				//显示的地点列表
				showSearchPlaceList: [],
				//弹窗子项信息
				popupItem: {
					name: '未知设备',
					surplus: '车位未知',
					surplusColor: '#000000',
				},
				//仪表盘数据
				chartData: {
					"categories": [{
							"value": 0.2,
							"color": "#1890ff"
						},
						{
							"value": 0.8,
							"color": "#2fc25b"
						},
						{
							"value": 1,
							"color": "#f04864"
						}
					],
					"series": [{
						"name": "完成率",
						"data": 0.1
					}],
				},
				//是否显示用户引导
				isTiptrue: false,
				//点击子项时
				itemId:0,
			}
		},
		components: {
			ParkingMap,
		},

		methods: {
			//刷新页面
			refresh() {
				uni.reLaunch({
					url: '/pages/parking/index'
				})
			},

			//拖动结束将地点框复位
			locationBoxReset(res) {
				let top = this.locationBoxTop
				if (top < 0.4) {
					this.changeLocationBox(2)
				} else if (top > 0.7) {
					this.changeLocationBox(0)
				} else {
					this.changeLocationBox(1)
				}
			},

			//在用户输入时搜索
			inputSearch(res) {
				let searchWord = res.detail.value
				this.search(searchWord)

			},
			//弹出层关闭
			closePopup() {
				this.showCircle = false
			},

			///监听触摸滑动事件
			touchLocationBox(res) {
				let top = res.changedTouches[0].pageY / this.windowHeight
				//防止顶部过度拖拽
				if (top < 0.1) {
					top = 0.1
				}
				//防止底部过度拖拽
				else if (top > 0.85) {
					top = 0.85
				}
				this.$refs.locationBox.step({
					top: top * 100 + 'vh',
					height: (1 - top) * 100 + 'vh',
				}, {
					duration: 0,
				})
				this.$refs.locationBox.run(() => {
				})
				this.locationBoxTop = top
			},

			//搜索
			search(searchWord) {
				let result = this.fuzzyQuery(this.placeList, this.searchWord)
				this.showPlaceListTitle = false
				this.showSearchPlaceList = result
				this.showMore = false
				this.isSearch = true
			},
			//点击搜索
			searchList() {
				this.search(this.searchWord)
			},

			//模糊搜索
			fuzzyQuery(list, keyWord) {
				let arr = []
				for (let i = 0; i < list.length; i++) {
					if (list[i].name.match(keyWord) != null) {
						arr.push(list[i])
					}
				}
				return arr
			},

			//导航按钮点击事件
			navigateToPlace() {
				this.navigateTo(this.popupItem.latitude, this.popupItem.longitude, this.popupItem.name)
			},

			//获取地点列表
			getPlaceList(res) {
				this.placeList = res
				this.showSearchPlaceList = this.placeList
			},

			//点击covers
			clickCovers(res) {
				this.chartData.series[0].data = res.capacity
				this.popupItem = res
				if(!this.deviceFault()){
					return ;
				}
				this.popupShow = true
			},

			//点击列表项
			clickItem(index) {
				this.popupItem = this.placeList[index]
				this.itemId=this.popupItem.id
				this.chartData.series[0].data = this.popupItem.capacity
				if(!this.deviceFault()){
					return ;
				}
				this.popupShow = true
				setTimeout(() => {
					this.showCircle = true
				}, 100)
			},

			//设备故障时不弹窗
			deviceFault(){
				//出故障不弹出
				if(this.popupItem.fault){
					uni.showToast({
						title: '设备故障，请稍后再尝试',
						icon:'none',
					})
					return false
				}
				return true
			},
			
			//禁止滚动
			//该方法解决IOS下点击地点框会层级穿透至map组件内的BUG
			noScrolling() {
				return false
			},

			//点击更多按钮
			clickMore() {
				if (this.locationBoxTop != 0.1) {
					this.changeLocationBox(2)
					setTimeout(() => {
						this.inputDisable = true
					}, 300)
				}
			},

			//新增显示数量
			addShowNum() {
				//新增数目步长7
				const step = 7
				let showNum = this.showNum
				const num = this.placeList.length
				if (showNum >= num) {
					this.showMore = false
					//如果已显示数已经少于总数就直接返回，不增加
					return;
				} else {
					showNum += step
					if (showNum >= num) {
						this.showMore = false
					}
				}
				this.showNum = showNum
			},

			//底栏动画
			runStatus0() {
				if (this.isSearch) {
					this.showSearchPlaceList = this.placeList
					this.isSearch = false
					this.searchWord = ''
				}
				this.inputDisable = true
				this.$refs.locationBox.step({
					height: '14vh',
					top: '86vh'
				}, {
					duration: 300,
				})
				this.$refs.locationBox.run(() => {
					this.locationBoxTop = 0.86
				})
			},

			//根据经纬度导航至目的地
			navigateTo(nLatitude, nLongitude, nName) {
				let plugin = requirePlugin('routePlan')
				//使用在腾讯位置服务申请的key
				let key = 'FIJBZ-GKBCS-UYLO5-66F56-MLB5J-OPFO7' 
				//调用插件的app的名称
				let referer = 'STOP' 
				let endPoint = JSON.stringify({ //终点
					'name': nName,
					'latitude': nLatitude,
					'longitude': nLongitude
				})
				uni.navigateTo({
					url: 'plugin://routePlan/index?key=' + key + '&referer=' + referer + '&endPoint=' + endPoint
				})
			},

			//半屏动画
			runStatus1() {
				if (this.isSearch) {
					this.showSearchPlaceList = this.placeList
					this.isSearch = false
					this.searchWord = ''
				}
				this.showPlaceListTitle = true
				this.showNum = 2
				this.showMore = true
				this.inputDisable = true
				this.$refs.locationBox.step({
					top: '56vh',
					height: '44vh',
				}, {
					duration: 300,
				})
				this.$refs.locationBox.run(() => {
					this.locationBoxTop = 0.56
				})
			},

			//全屏动画
			runStatus2() {
				this.$refs.locationBox.step({
					height: '90vh',
					top: '10vh'
				}, {
					duration: 300,
				})
				this.$refs.locationBox.run(() => {
					this.locationBoxTop = 0.1
				})
			},

			//输入框失去焦点
			inputBlur(res) {
				this.inputBorderColor = '1px solid #bfbfbf'
			},

			//点击输入框
			clickInputBox() {
				//获取焦点时修改边框颜色，因:style不支持绑定border-color,所以绑定了整个border
				this.inputBorderColor = '1px solid #a35c8f'
				this.changeLocationBox(2)
				setTimeout(() => {
					setTimeout(() => {
						this.isFocus = true
					}, 50)
					this.inputDisable = false
				}, 300)
			},

			//显示推荐地点列表
			showList() {
				this.changeLocationBox(1)
			},

			//收到map组件传回来的值，显示呼出条、隐藏地点列表
			hideList(res) {
				if(this.popupShow){
					return ;
				}
				this.changeLocationBox(0)
			},

			//更改地点框状态，0：底栏(默认)；1：半屏；2：全屏；
			changeLocationBox(status) {
				if (status === 0) {
					this.isFocus = false
					this.scrollHeight = ''
					this.showPlaceList = false
					this.runStatus0()
				} else if (status === 1) {
					this.isFocus = false
					this.runStatus1()
					this.scrollHeight = ''
					this.showPlaceList = true
				} else if (status === 2) {
					this.showPlaceList = true
					this.addShowNum()
					this.scrollHeight = '78vh'
					this.runStatus2()
				}
			},
			
			//关闭用户引导页，设置缓存，下一次打开不再显示
			closeThis() {
				wx.setStorage({
					key: 'loadOpen',
					data: 'OpenTwice'
				})
				this.isTiptrue = false
			},
			

		},
		created() {
			let _this = this
			//获取用户系统尺寸
			uni.getSystemInfo({
				success: function(res) {
					_this.windowWidth = res.windowWidth
					_this.windowHeight = res.windowHeight
				}
			})
		},
		onLoad() {
			let firstOpen = wx.getStorageSync("loadOpen")
			if (typeof(firstOpen) == undefined || firstOpen == '') { //根据缓存周期决定是否显示新手引导
				this.isTiptrue = true
			} else {
				this.isTiptrue = false
			}
			
			uni.getSetting({
				success(res) {
					//如果用户没有授权
					//console.log(res.authSetting['scope.userLocation']);
					if (res.authSetting['scope.userLocation'] == false) {
						uni.authorize({
							scope: 'scope.userLocation',
							success() { //1.1 允许授权
								uni.getLocation({
									success(res) {
										//console.log(res)
									}
								})
							},
							fail() { //1.2 拒绝授权
								//console.log("你拒绝了授权，无法正常使用小程序部分功能");
								uni.showModal({
									title: '提示',
									content: '您已拒绝授权，是否去设置中的打开？',
									confirmText: '确定',
									cancelText: '取消',
									success(res) {
										//console.log('同意');
										if (res.confirm) {
											uni.openSetting({
												success(res) {
													res.authSetting['scope.userLocation'] =
														true
												}
											}).then(res => {
												uni.reLaunch({
													url: '/pages/parking/index'
												})
											})
										}
									}
								})
							}
						})
					}
				}
			})
		}
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
		top: 56vh;
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

	.charts-box {
		width: 56vh;
		height: 34vh;
		position: absolute;
		left: 50%;
		transform: translateX(-50%);
	}
	
	.page-cont{
		position: fixed;
		width: 100%;
		height: 100%;
		left: 0;
		right: 0;
		bottom: 0;
		z-index: 99999;
		background-color: rgba(22, 23, 24, 0.5);
	}
	
	.page-cont .top {
		margin-top: 160rpx;
		width: 93%;
		display: flex;
		flex-direction: column;
	}
	
	.page-cont .top image {
		width: 32px;
		height: 41px;
		margin: 4% 0 4% 77%;
	}
	
	.page-cont .top .p_one {
		float: right;
		width: 80%;
		font-size: 28rpx;
		line-height: 72rpx;
		color: #fff;
		background: #27C084;
		text-align: center;
		border-radius: 34rpx;
		margin: 0 0 0 15%;
	}
	
	.page-cont .cont {
		width: 100%;
		margin-top: 30rpx;
		display: flex;
		flex-direction: column;
	}
	
	.page-cont .cont .cont-p {
		width: 80%;
		margin-left: 15%;
		margin-top: 30rpx;
		display: flex;
		color: #fff;
		font-size: 30rpx;
		line-height: 72rpx;
	}
	
	.page-cont .cont .cont-p .text {
		color: #fff;
		font-size: 30rpx;
		line-height: 40rpx;
		width: 40rpx;
		height: 40rpx;
		text-align: center;
		border-radius: 50%;
		background: #27C084;
		margin-right: 20rpx;
		margin-top: 16rpx;
		display: flex;
		flex-direction: column;
	}
	
	.page-cont .cont .cont-p image {
		width: 41px;
		height: 28px;
		margin: 8rpx 0 0 20rpx;
		border: 1px dashed #fff;
	}
	
	.page-cont .cont .cont-p-lib {
		width: 90%;
		margin-left: 15%;
		display: flex;
		color: #fff;
		font-size: 30rpx;
	}
	
	.page-cont .cont .cont-p-lib .text {
		color: #fff;
		font-size: 30rpx;
		line-height: 40rpx;
		width: 40rpx;
		height: 40rpx;
		text-align: center;
		border-radius: 50%;
		background: #27C084;
		margin-right: 20rpx;
		margin-top: 16rpx;
		display: flex;
		flex-direction: column;
	}
	
	.page-cont .bottom {
		width: 60%;
		font-size: 30rpx;
		line-height: 72rpx;
		text-align: center;
		border-radius: 44rpx;
		border: 1px solid #fff;
		margin: 10% auto 0 auto;	
	}
</style>
