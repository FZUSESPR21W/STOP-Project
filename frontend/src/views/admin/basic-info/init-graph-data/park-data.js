export default {
    title: {
        left: 'center',
        text: '福州大学教学停车情况',
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: ['16:00', '16:20', '16:40', '17:00', '17:20', '17:40', '18:00']
    },
    yAxis: {
        type: 'value',
        name: '电动车/辆',
        nameLocation: 'middle',
        nameTextStyle: {
            padding: [0, 0, 30, 0]
        }
    },
    series: [
        {
            name: '东三',
            data: [360, 230, 250, 240, 270, 250, 70],
            type: 'line',
            areaStyle: {}
        },
        {
            name: '西三',
            data: [300, 280, 230, 240, 250, 150, 30],
            type: 'line',
            areaStyle: {}
        }
    ],
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'cross',
            label: {
                backgroundColor: '#6a7985'
            }
        }
    }
}