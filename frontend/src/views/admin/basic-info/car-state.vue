<template>
  <!-- 车辆信息图表容器开始 -->
  <div class="car-state-container">
    <!-- 车辆信息图表 -->
    <div id="car" class="graph"/>
  </div>
  <!-- 车辆信息图表容器结束 -->
</template>

<script>
export default {
  name: "car-state",
  data() {
    return {
      //图表数据
      graph: {
        //车辆信息图表数据
        car: {
          // 标题
          title: {
            text: '福州大学各处电动车停车昨日停车峰值',
            left: 'center'
          },
          xAxis: {
            type: 'category',
            data: []
          },
          yAxis: {
            type: 'value'
          },
          series: [{
            data: [],
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
    // 更新面包屑路径
    this.$store.commit('setPageLocations', ['基础', '车辆情况'])
  },
  mounted() {
    // 获取停车情况并绘制车辆信息图表
    this.getCarStateAndPaint()
  },
  methods: {
    // 获取停车情况
    getCarStateAndPaint() {
      this.$api.Statistics.getStopStatusDaily().then(res => {
        res.data.data.stopStatusDaily.forEach(item => {
          this.graph.car.xAxis.data.push(item.name)
          this.graph.car.series[0].data.push(parseInt(item.value))
        })

        this.paintCarState()

      })
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
    padding-top: 20px;
    width: 100%;
    height: 50%;
  }
}
</style>