<template>
    <view class="charts-box">
	今日车流趋势
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
				        "7~9",
				        "9~11",
				        "11~13",
				        "13~15",
				        "15~17",
				        "17~19",
						"19~21"
				    ],
				    "series": [
				        {
				            "name": "生活一区",
				            "data": [
				                350,
				                36,
				                31,
				                33,
				                13,
				                34,
								12,
				            ]
				        },
				        {
				            "name": "生活三区",
				            "data": [
				                180,
				                270,
				                210,
				                240,
				                60,
				                28,
								20,
				            ]
				        },
						{
						    "name": "研究生公寓",
						    "data": [
						        270,
						        210,
						        240,
						        60,
						        28,
								20,
								554,
						    ]
						},
						{
						    "name": "玫瑰园",
						    "data": [
						        210,
						        240,
						        60,
						        28,
								20,
								180,
								400,
						    ]
						},
						{
						    "name": "丁香园",
						    "data": [
						        240,
						        60,
						        28,
								20,
								180,
								270,
								210,
						    ]
						}
				    ]
				},
            }
        },
        onLoad() {
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