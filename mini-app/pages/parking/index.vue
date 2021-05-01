<template>
	<view>
		<ParkingMap/>
		我是我要停车页面
	</view>
</template>

<script>
import ParkingMap from './parking-map/index.vue';
export default{
	name:'Parking',
	components:{
		ParkingMap,
	},
}
</script>

<style>
</style>
