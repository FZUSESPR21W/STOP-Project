export default {
    chart: {
        type: 'areaspline'
    },
    title: {
        left: 'center',
        text: '福州大学教学停车情况',
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        categories: ['16:00', '16:20', '16:40', '17:00', '17:20', '17:40', '18:00']
    },
    yAxis: {
        title: {
            text: '电动车/辆'
        }
    },
    plotOptions: {
        areaspline: {
            fillOpacity: 0.5
        }
    },
    series: [],
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