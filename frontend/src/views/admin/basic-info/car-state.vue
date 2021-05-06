<template>
  <div class="car-state-container">
    <div id="car" class="graph"/>
  </div>
</template>

<script>
export default {
  name: "car-state",
  data() {
    return {
      graph: {
        car: {
          xAxis: {
            type: 'category',
            data: ['一区', '二区', '三区', '四区', '五区', '研究生公寓',
              '紫荆', '玫瑰', '丁香', '京元', '西三', '西二', '西一', '中楼',
              '东一', '东二', '文楼']
          },
          yAxis: {
            type: 'value'
          },
          series: [{
            data: [500, 420, 490, 380, 356, 343, 332,
              330, 327, 304, 278, 267, 213, 142, 102, 102, 55, 55],
            type: 'bar',
            itemStyle: {
              normal: {
                color: function (params) {
                  return ['#7CB5EC', '#434348', '#90ED7D', '#F7A35C',
                    '#8085E9', '#F15C80', '#E4D354', '#2B908F', '#F45B5B'
                    , '#91E8E1', '#7CB5EC', '#434348', '#90ED7D', '#F7A35C',
                    '#8085E9', '#F15C80', '#E4D354', '#2B908F', '#F45B5B'
                    , '#91E8E1'][params.dataIndex]
                }
              }
            }
          }]
        }
      }
    }
  },
  beforeMount() {
    // 获取停车情况
    // this.getCarState()
  },
  mounted() {
    // 绘制车辆信息图表
    this.paintCarState()
  },
  methods: {
    // 获取停车情况
    getCarState() {
    },
    // 绘制车辆信息图表
    paintCarState() {
      let domContainer = document.getElementById('car')
      let chart = this.$echarts.init(domContainer)
      chart.setOption(this.graph.car)
    }
  }
}
</script>

<style lang="scss" scoped>
.car-state-container {
  height: 100%;
  width: 100%;

  .graph {
    width: 100%;
    height: 50%;
  }
}
</style>