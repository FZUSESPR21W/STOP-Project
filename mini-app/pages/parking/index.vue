<template>
	<view >
		<ParkingMap
			@hideList="hideList"
		/>
		<view class="location-box" :class="{hideListStart:status0,showListStart:status1}"
		 :style="{height:locationBoxHeight,Top:locationBoxTop}"
			>
			<view style="width: 100%;padding-top:10px; padding-bottom: 4px;" @click="showList"><view class="exhale-bar" v-show="showExhaleBar"></view></view>
			<view class="input-box-border">
				<input
					type="text"
					class="input-box"
					placeholder="查询想要停车的地点"
					maxlength="40"
				/>
			</view>
			<view class="place-list" v-show="showPlaceList">这里推荐停车地点</view>
		</view>
	</view>
</template>

<script>
import ParkingMap from './parking-map/index.vue';
export default{
	name:'Parking',
	data(){
		return{
			status0:false,
			status1:false,
			status2:false,
			showExhaleBar:false,
			showPlaceList:true,
			locationBoxHeight:'44vh',
			locationBoxTop:'56vh',
		}
	},
	components:{
		ParkingMap,
	},
	methods:{
		
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
			var _this=this;
			if(status === 0){
				_this.showExhaleBar=true;
				_this.showPlaceList=false;
				_this.status0=true;
				_this.status1=false;
				_this.locationBoxHeight='14vh';
				_this.locationBoxTop='86vh';
			}
			else if(status === 1){
				_this.status0=false;
				_this.status1=true;
				_this.showExhaleBar=false;
				_this.showPlaceList=true; 
				_this.locationBoxHeight='44vh';
				_this.locationBoxTop='56vh';
			}
			else if(status === 2){
				console.log('全屏尚未完成');
			}
		}
	}
}
</script>

<style scoped>
	/* 隐藏推荐地点框的动画 */
	@keyframes hideList {
	  from {height: 44vh;top:56vh}
	  to {height: 14vh;top: 86vh;}
	}
	/* 显示推荐地点框的动画 */
	@keyframes showList {
	  from {height: 14vh;top:86vh}
	  to {height: 44vh;top: 56vh;}
	}
	/* 地点框 */
	.location-box{
		background-color: white;
		width: 100%;
		border-radius: 10px 10px 0 0;
		position: fixed;
		border-top: 1px solid rgba(191, 191, 191, 0.4);
	}
	/* 开始隐藏动画 */
	.hideListStart{
		 animation-name: hideList;
		 animation-duration: 0.4s;
	}
	/* 开始展示动画 */
	.showListStart{
		animation-name: showList;
		animation-duration: 0.4s;
	}
	/* 呼出条 */
	.exhale-bar{
		width: 20%;
		height: 1vh;
		margin: 0 auto;
		/* margin-top: 10px; */
		border-radius: 50px;
		background-color: rgba(89,89,89,0.7);
	}
	/* 输入框外框 */
	.input-box-border{
		height: fit-content;
		border: 1px solid black;
		background-color: #f5f5f5;
		margin: 0 auto;
		margin-top: 10px;
		width: 86%;
		padding: 4px;
		border-radius: 10px ;
	}
	/* 输入框 */
	.input-box{
		width: 100%;
		height: 30px;
		background-color: #f5f5f5;
		border: 1px solid red;
		display: block;
		font-size: 18px;
	}
	/* 推荐地点列表 */
	.place-list{
		margin-top: 10px;
		background-color: white;
		height: 100%;
	}
</style>
