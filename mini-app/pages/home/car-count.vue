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
				        },
				        {
				            "name": "生活三区",
				            "data": []
				        },
						{
						    "name": "研究生公寓",
						    "data": []
						},
				    ]
					},
				}
			},
			onLoad() {
				this.$api.Statistics.getStopStatusHourly().then(res => {
					this.chartData.series[0].data = res.data.data.stopStatusHourly[0].values;
					this.chartData.series[1].data = res.data.data.stopStatusHourly[1].values;
					this.chartData.series[2].data = res.data.data.stopStatusHourly[2].values;
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