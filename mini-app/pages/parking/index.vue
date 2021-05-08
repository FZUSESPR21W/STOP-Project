<template>
	<view >
		<ParkingMap
			@hideList="hideList"
		/>
		<uni-transition custom-class="location-box" :show="showLocationBox" ref="locationBox"
			>
			<view style="width: 100%;padding-top:10px; padding-bottom: 4px;" @click="showList">
				<uni-transition mode-class="fade" custom-class="exhale-bar" :show="showExhaleBar" />
			</view>
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
			<view class="place-list" v-show="showPlaceList">这里推荐停车地点</view>
		</uni-transition>
	</view>
</template>

<script>
import ParkingMap from './parking-map/index.vue';
export default{
	name:'Parking',
	data(){
		return{
			isFocus:false,
			inputDisable:true,
			showExhaleBar:false,
			showPlaceList:true,
			showLocationBox:true,
			locationBoxHeight:'44vh',
			locationBoxTop:'56vh',
			inputBorderColor:'1px solid #bfbfbf',
		}
	},
	components:{
		ParkingMap,
	},
	
	methods:{
		
		//底栏动画
		runStatus0(){
			var _this=this;
			_this.inputDisable=true;
			_this.$refs.locationBox.step({
				height:'14vh',
				top:'86vh'
			})
			_this.$refs.locationBox.run(()=>{
				//console.log('执行完毕')
			});
		},
		
		//半屏动画
		runStatus1(){
			var _this=this;
			_this.inputDisable=true;
			_this.$refs.locationBox.step({
				height:'44vh',
				top:'56vh'
			})
			_this.$refs.locationBox.run(()=>{
				//_this.isFocus=false;
				//console.log('执行完毕')
			});
		},
		
		//全屏动画
		runStatus2(){
			var _this=this;
			_this.$refs.locationBox.step({
				height:'90vh',
				top:'10vh'
			})
			_this.$refs.locationBox.run(()=>{
				//_this.isFocus=true;
				setTimeout(() => {
					_this.isFocus = true;
				}, 50)
				_this.inputDisable=false;
				//console.log('执行完毕')
			});
		},
		
		//输入框失去焦点
		inputBlur(res){
			var _this=this;
			_this.inputBorderColor='1px solid #bfbfbf';
		},
		
		//点击输入框
		clickInputBox(){
			var _this=this;
			//获取焦点时修改边框颜色，因:style不支持绑定border-color,所以绑定了整个border
			_this.inputBorderColor='1px solid #a35c8f';
			_this.changeLocationBox(2);
		},
		
		//显示推荐地点列表
		showList(){
			this.changeLocationBox(1);
		},
		
		//收到map组件传回来的值，显示呼出条、隐藏地点列表
		hideList(res){
			var _this=this;
			_this.changeLocationBox(0);
		},
		
		//更改地点框状态，0：底栏(默认)；1：半屏；2：全屏；
		changeLocationBox(status){
			var _this=this;
			if(status === 0){
				_this.isFocus=false;
				_this.showExhaleBar=true;
				_this.showPlaceList=false;
				_this.runStatus0();
			}
			else if(status === 1){
				_this.isFocus=false;
				_this.runStatus1();
				_this.showExhaleBar=false;
				_this.showPlaceList=true; 
			}
			else if(status === 2){
				_this.runStatus2();
			}
		}
	}
}
</script>

<style scoped lang="scss">

	
	/* 地点框 */
	/deep/.location-box{
		background-color: white;
		width: 100%;
		border-radius: 10px 10px 0 0;
		border-top: 1px solid rgba(191, 191, 191, 0.4);
		position: fixed !important;
		height: 44vh;
		top:56vh

	}

	/* 呼出条 */
	/deep/.exhale-bar{
		width: 20%;
		height: 1vh;
		margin: 0 auto;
		/* margin-top: 10px; */
		border-radius: 50px;
		background-color: rgba(89,89,89,0.7);
	}
	/* 输入框外框 */
	.input-box-border{
		height: 40px;
		margin: 0 auto;
		margin-top: 10px;
		width: 86%;
		border-radius: 10px ;
		position: relative;
	}
	/* 输入框 */
	.input-box{
		width: 84%;
		height: 100%;
		/* border: 1px solid pink; */
		
		margin:4rpx 2vh 0 10rpx;
		display: inline-block;
		font-size: 18px;
	}
	/* 推荐地点列表 */
	.place-list{
		margin-top: 10px;
		background-color: white;
		height: 100%;
	}
</style>
