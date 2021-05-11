<template>
	<view >
		<!-- map组件开始 -->
		<view>
			<ParkingMap @hideList="hideList"/>
		</view>
		<!-- map组件结束 -->
		<!-- 推荐地址框容器开始 -->
		<uni-transition custom-class="location-box" :show="showLocationBox" ref="locationBox" catchtouchmove="noScrolling" >
			<!-- 向上导航条容器开始 -->
			<view style="width: 100%;padding-top:10px; padding-bottom: 4px;" @click="showList">
				<uni-transition mode-class="fade" custom-class="exhale-bar" :show="showExhaleBar" />
			</view>
			<!-- 向上导航条容器结束 -->
			<!-- 搜索框容器开始 -->
			<view class="input-box-border" :style="{border:inputBorderColor}" @click="clickInputBox">
				<input
					type="text"
					class="input-box uni-input"
					placeholder="查询想要停车的地点"
					maxlength="40"
					:focus="isFocus"
					:disabled="inputDisable"
					:adjust-position="false"
					confirm-type="search"
					@blur="inputBlur"
				/>
				<u-icon style="position: absolute;top: 6rpx;" slot="icon" custom-prefix="custom-icon" color="#A35C8F" size="32px" name="search"></u-icon>
			</view>
			<!-- 搜索框容器结束 -->
			<view class="place-list" v-show="showPlaceList">
				<view>
					<view class="place-list-title" style="">最近适合停车的地点</view>
					<view class="place-list-title-bar"></view>
					<view class="place-list-item" v-for="(placeItem,index) in placeList.slice(0,showNum)" :key="index">
						<u-icon slot="icon" custom-prefix="custom-icon" color="#A35C8F" size="90rpx" name="position-icon"></u-icon>
						<view class="place-list-item-title">{{placeItem.title}}</view>
						<view class="place-list-item-parking">{{placeItem.parking}}</view>
						<view class="place-list-item-distance">{{placeItem.distance}}m</view>
						<u-icon slot="icon" custom-prefix="custom-icon" color="#A35C8F"
										size="60rpx" name="navigation" style="float: right;margin-right: 4px;"></u-icon>
					</view>
					
					<view style="width: fit-content;margin: 0 auto;">
						<view @click="clickMore">
							<u-icon slot="icon" custom-prefix="custom-icon" color="#A35C8F"
											size="40rpx" name="more"
											style="padding: 30rpx 60rpx 20rpx 60rpx;"></u-icon>
						</view>
					</view>
				</view>

			</view>
		</uni-transition>
		<!-- 推荐地址框容器结束 -->
	</view>
</template>

<script>
import ParkingMap from './parking-map/index.vue';
export default{
	name:'Parking',
	data(){
		return{
			//推荐的地点展示的条数
			showNum:2,
			//输入框是否自动聚焦
			isFocus:false,
			//是否禁用输入框
			inputDisable:true,
			//是否显示向上呼出导航条
			showExhaleBar:false,
			//是否显示推荐地址列表
			showPlaceList:true,
			//是否显示地址框
			showLocationBox:true,
			//绑定输入框外框颜色
			inputBorderColor:'1px solid #bfbfbf',
			//地点列表
			placeList:[],
		}
	},
	components:{
		ParkingMap,
	},
	
	methods:{
		
		//禁止滚动
		//该方法解决IOS下点击地点框会层级穿透至map组件内的BUG，方法为空
		noScrolling(){
			
		},
		
		//点击更多按钮
		clickMore(){
			this.changeLocationBox(2);
			setTimeout(()=>{
					this.inputDisable=true;
			},300);
		},
		
		//底栏动画
		runStatus0(){
			this.inputDisable=true;
			this.$refs.locationBox.step({
				height:'14vh',
				top:'86vh'
			})
			this.$refs.locationBox.run(()=>{
				//console.log('执行完毕')
			});
		},
		
		//半屏动画
		runStatus1(){
			this.inputDisable=true;
			this.$refs.locationBox.step({
				height:'44vh',
				top:'56vh'
			})
			this.$refs.locationBox.run(()=>{
				//_this.isFocus=false;
				//console.log('执行完毕')
			});
		},
		
		//全屏动画
		runStatus2(){
			this.$refs.locationBox.step({
				height:'90vh',
				top:'10vh'
			})
			this.$refs.locationBox.run(()=>{
				setTimeout(() => {
					this.isFocus = true;
				}, 50);
				this.inputDisable=false;
				this.showNum=3;
				//console.log('执行完毕')
			});
		},
		
		//输入框失去焦点
		inputBlur(res){
			this.inputBorderColor='1px solid #bfbfbf';
		},
		
		//点击输入框
		clickInputBox(){
			//获取焦点时修改边框颜色，因:style不支持绑定border-color,所以绑定了整个border
			this.inputBorderColor='1px solid #a35c8f';
			this.changeLocationBox(2);
		},
		
		//显示推荐地点列表
		showList(){
			this.changeLocationBox(1);
		},
		
		//收到map组件传回来的值，显示呼出条、隐藏地点列表
		hideList(res){
			this.changeLocationBox(0);
		},
		
		//更改地点框状态，0：底栏(默认)；1：半屏；2：全屏；
		changeLocationBox(status){
			if(status === 0){
				this.isFocus=false;
				this.showExhaleBar=true;
				this.showPlaceList=false;
				this.runStatus0();
			}
			else if(status === 1){
				this.isFocus=false;
				this.runStatus1();
				this.showExhaleBar=false;
				this.showPlaceList=true; 
			}
			else if(status === 2){
				this.showExhaleBar=true;
				this.runStatus2();
			}
		}
	},
	created() {
		this.placeList=[{
			title:'福州大学-东三',
			parking:'车位空余',
			distance:'200',
		}];
		this.placeList[1]=this.placeList[0];
		this.placeList[2]=this.placeList[0];
	}
}
</script>

<style scoped lang="scss">

	// 地点列表
	.place-list{
		// 地点列表标题
		.place-list-title{
			margin:4px 0 2px 10px;
		}
		// 地点列表标题条
		.place-list-title-bar{
			background-color: rgba($color: #a35c8f, $alpha: 0.8);
			height: 1.6px;
			width: 160px;
			margin-bottom: 8px;
		}
		// 地点列表标题子项
		.place-list-item{
			background-color: white;
			margin-left:20px ;
			margin-right: 20px;
			margin-bottom: 14px;
			margin: 0 20px 14px 20px;
			position: relative;
			padding: 2px;
			border-bottom: 1px solid rgba($color: #8c8c8c, $alpha: 0.8);
			// 子项标题
			.place-list-item-title{
				color: rgba($color: #595959, $alpha: 1.0);
				font-size: 40rpx;
				display: inline-block;
				position: absolute;
				overflow: hidden;
				text-overflow:ellipsis;
				white-space: nowrap;
				width: 12em;
			}
			// 子项剩余车量
			.place-list-item-parking{
				display: inline-block;
				color: rgba($color: #7DCC76, $alpha: 1);
			}
			// 子项距离
			.place-list-item-distance{
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
	/deep/.location-box{
		z-index: 9999;
		background-color: white;
		width: 100%;
		border-radius: 10px 10px 0 0;
		border-top: 1px solid rgba(191, 191, 191, 0.4);
		position: fixed !important;
		height: 44vh;
		top:56vh

	}

	// 呼出条
	/deep/.exhale-bar{
		width: 20%;
		height: 1vh;
		margin: 0 auto;
		border-radius: 50px;
		background-color: rgba(89,89,89,0.7);
	}
	// 输入框外框
	.input-box-border{
		height: 40px;
		margin: 0 auto;
		margin-top: 10px;
		width: 86%;
		border-radius: 10px ;
		position: relative;
	}
	// 输入框
	.input-box{
		width: 84%;
		height: 100%;		
		margin:4rpx 2vh 0 10rpx;
		display: inline-block;
		font-size: 18px;
	}
	// 推荐地点列表
	.place-list{
		margin-top: 10px;
		background-color: white;
		height: 100%;
	}
</style>
