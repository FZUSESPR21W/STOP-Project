export default  {
    title: {
        text: 'STOP小程序端日访问量',
        left: 'center'
    },
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data: ['访问量(PV)', '访问用户(UV)'],
        left: 'left'
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    toolbox: {
        feature: {
            saveAsImage: {}
        }
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: ['2021-5-1', '2021-5-2', '2021-5-3', '2021-5-4', '2021-5-5', '2021-5-6', '2021-5-7']
    },
    yAxis: {
        type: 'value'
    },
    series: [
        {
            name: '访问量(PV)',
            type: 'line',
            data: [120, 132, 101, 134, 90, 230, 210]
        },
        {
            name: '访问用户(UV)',
            type: 'line',
            data: [220, 182, 191, 234, 290, 330, 310]
        }
    ]
}