<template>
  <!-- 基本情况页面容器开始 -->
  <div class="base-state-container">
    <!-- 用户登录记录开始 -->
    <div class="graph-container">
      <span class="title">过去一个月用户画像相关数据</span>
      <div class="graph-container-main">
        <!-- 用户年龄 -->
        <div id="ages" class="graph"/>
        <!-- 平台 -->
        <div id="platforms" class="graph"/>
      </div>
    </div>
    <!-- 用户登录记录结束 -->
    <!-- 数据可视化(echarts图表）开始 -->
    <div class="graph-container-two" >
      <span class="title">数据可视化</span>
      <!-- 图表主体开始 -->
      <div class="graph-container-main">
        <!-- 福州大学教学楼停车情况 -->
        <div id="park" class="graph"/>
        <!-- STOP小程序端日访问量 -->
        <div id="visit" class="graph"/>
      </div>
      <!-- 图表主题结束 -->
    </div>
    <!-- 数据可视化(echarts图表）结束 -->
  </div>
  <!-- 基本情况页面容器结束 -->
</template>

<script>
import initParkData from './init-graph-data/park-data'
import initVisitData from './init-graph-data/visit-data'
import initPeiChartData from './init-graph-data/pie-chart'

export default {
  name: "base-state",
  data() {
    return {
      // 数据可视化(echarts图表）对象
      graph: {
        // 福州大学教学楼停车情况数据 深拷贝
        parkData: JSON.parse(JSON.stringify(initParkData)),
        // STOP小程序端日访问量数据 深拷贝
        visitData: JSON.parse(JSON.stringify(initVisitData)),
        // 用户年龄图表
        agesData: JSON.parse(JSON.stringify(initPeiChartData)),
        // 平台图表
        platformsData: JSON.parse(JSON.stringify(initPeiChartData)),
      }
    }
  },
  beforeMount() {
    // 更新面包屑路径
    this.$store.commit('setPageLocations', ['基础','基本情况'])
  },
  mounted() {
    this.getStopStatusAndPaint()
    this.getVisitNumberAndPaint()
    this.getUserLoginDataAndPaint()
  },
  methods: {
    //获取用户登录数据
    getUserLoginDataAndPaint() {
      // 调用用户登录数据接口
      this.$api.Statistics.getLoginList().then(res => {
        this.graph.agesData.series[0].data = res.data.data.data.visit_uv.ages
        this.graph.agesData.title.text = '年龄'
        this.graph.platformsData.series[0].data = res.data.data.data.visit_uv.platforms
        this.graph.platformsData.title.text = '平台'
        //绘制图表
        this.paintAges()
        this.paintPlatforms()
      })

    },
    // 获取福州大学教学楼停车情况并绘制
    getStopStatusAndPaint() {
      this.$api.Statistics.getStopStatusHourly().then(res => {
        let stopStatusHourly = res.data.data.stopStatusHourly
        // 往本地图表数据添加服务器获取到的数据
        stopStatusHourly.forEach(item => {
          this.graph.parkData.series.push({
            name: item.name,
            data: item.values
          })
        })

        // 清空图表x轴
        this.graph.parkData.xAxis.categories = []
        // 获取当前时间
        let time = new Date()
        // 设置一分钟对应的数值
        let MS_PER_MINUTE = 60000
        for(let i = 0 ; i < 6 ; i ++){
          // 每20分钟为一个单位
          let newtime = new Date(time - 20 * i * MS_PER_MINUTE)
          this.graph.parkData.xAxis.categories.push(`${newtime.getHours()}:${newtime.getMinutes()}`)
        }

        // 数组反转
        this.graph.parkData.xAxis.categories = this.graph.parkData.xAxis.categories.reverse()

        // 绘制图表
        this.paintStopStatus()
      })
    },
    // 获取STOP小程序端日访问量并绘制
    getVisitNumberAndPaint() {
      this.$api.Statistics.getVisitNumber().then(res => {
        this.graph.visitData.xAxis.data = []
        // pv
        this.graph.visitData.series[0].data = []
        // uv
        this.graph.visitData.series[1].data = []
        res.data.data.visitNumber.forEach(item => {
          this.graph.visitData.xAxis.data.unshift(item.list[0].ref_date)
          this.graph.visitData.series[0].data.unshift(item.list[0].visit_pv)
          this.graph.visitData.series[1].data.unshift(item.list[0].visit_uv)
        })

        // 绘制图表
        this.paintVisitNumber()
      })
    },
    // 绘制福州大学教学楼停车情况
    paintStopStatus() {
      // let domContainer = document.getElementById('park')
      // let chart = this.$echarts.init(domContainer)
      // chart.setOption(this.graph.parkData)
      this.$highcarts.chart('park', this.graph.parkData)

      // 添加重新绘制监听
      window.addEventListener('resize', () => {
        this.$highcarts.chart('park', this.graph.parkData)
      })
    },
    // 绘制图表STOP小程序端日访问量
    paintVisitNumber() {
      let domContainer = document.getElementById('visit')
      let chart = this.$echarts.init(domContainer)
      chart.setOption(this.graph.visitData)

      // 添加重新绘制监听
      window.addEventListener('resize', () => {
        chart.resize()
      })
    },
    // 绘制年龄图表
    paintAges() {
      let domContainer = document.getElementById('ages')
      let chart = this.$echarts.init(domContainer)
      chart.setOption(this.graph.agesData)

      // 添加重新绘制监听
      window.addEventListener('resize', () => {
        chart.resize()
      })
    },
    // 绘制平台图表
    paintPlatforms() {
      let domContainer = document.getElementById('platforms')
      let chart = this.$echarts.init(domContainer)
      chart.setOption(this.graph.platformsData)

      // 添加重新绘制监听
      window.addEventListener('resize', () => {
        chart.resize()
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.base-state-container {
  display: flex;
  flex-direction: column;
  height: 100%;
  width: 100%;
  overflow: hidden;

}

.graph-container, .graph-container-two {
  flex: 0 0 50%;
  padding-top: 20px;
  padding-left: 50px;

  .banner {
    margin-top: 10px;
  }


  .title {
    font-family: "Microsoft YaHei UI Bold", "Microsoft YaHei UI Regular", "Microsoft YaHei UI", sans-serif;
    border-left: 10px solid #813C85;
    font-size: 18px;
    padding-left: 6px;
    margin-bottom: 5px;
  }

}

.graph-container, .graph-container-two {
  display: flex;
  flex-direction: column;

  .graph-container-main {
    display: flex;
    height: 100%;
    width: 100%;
  }
}

.graph-container-two {
  margin-top: -120px;
}


.graph {
  flex: 0 0 50%;
  width: 50%;
  height: 90%;
}


</style>