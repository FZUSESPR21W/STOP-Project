<template>
    <view class="charts-box">
	<text>近两小时车流趋势</text>
      <qiun-data-charts
        type="line"
        :chartData="chartData"
        background="none"
        :animation="false"
      />
    </view>
</template>
<script>
    import uCharts from '@/components/u-charts/u-charts-v2.0.0.js';
    var _self;
    var canvaColumn=null;
    export default {
        data() {
            return {
                cWidth:'',
                cHeight:'',
                pixelRatio:1,
                serverData:'',
				chartData:
				{
				    "categories": [
				        "0~20",
				        "20~40",
								"40~60",
				        "60~80",
				        "80~100",
				        "100~120",
				    ],
				    "series": [
				        {
				            "name": "生活一区",
				            "data": []
				        },]
					},
				}
			},
			onLoad() {
				this.$api.Statistics.getStopStatusHourly().then(res => {
					let arr=res.data.data.stopStatusHourly
					for (var i = 0; i < arr.length; i++) {
						arr[i].data=arr[i].values
					}
					this.chartData.series=arr
				})
				},
				
			methods: {
			}
		}
	</script>

	<style>
		.charts-box{
			font-size: x-large;
			margin: 30rpx 0;
			width: 100%;
			height:600rpx;
		}
	</style>