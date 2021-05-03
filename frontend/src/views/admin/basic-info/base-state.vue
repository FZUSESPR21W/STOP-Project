<template>
  <!-- 基本情况页面容器开始 -->
  <div class="base-state-container">
    <!-- 用户登录记录开始 -->
    <div class="user-login-log-container">
      <span class="title">用户登录记录</span>
      <!-- 用户登录记录表格，数据来源：userLoginData -->
      <el-table :data="userLogin.userLoginData" style="width: 100%" stripe>
        <!-- 日期字段 -->
        <el-table-column prop="date" label="日期" width="180"/>
        <!-- 姓名字段 -->
        <el-table-column prop="name" label="姓名" width="180"/>
        <!-- 登录地字段 -->
        <el-table-column prop="space" label="地址"/>
      </el-table>
      <!-- 用户登录记录表格结束 -->
    </div>
    <!-- 用户登录记录结束 -->
    <!-- 数据可视化(echarts图表）开始 -->
    <div class="graph-container">
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
export default {
  name: "base-state",
  data() {
    return {
      // 用户登录记录对象
      userLogin: {
        //用户登录数据
        userLoginData: [],
        //页面下标
        page: 0,
        //每页限制条数
        limit: 5
      },
      // 数据可视化(echarts图表）对象
      graph: {
        // 福州大学教学楼停车情况数据
        parkData: {
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
              padding: [0 , 0 , 30 , 0]
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
        },
        // STOP小程序端日访问量数据
        visitData: {
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
      }
    }
  },
  beforeMount() {
    // 数据初始化
    this.getUserLoginData()
    this.getStopStatus()
    this.getVisitNumber()
  },
  mounted() {
    // 绘制图表
    this.paintStopStatus()
    this.paintVisitNumber()
  },
  methods: {
    //获取用户登录数据
    getUserLoginData() {
      //填充假数据
      for (let i = 0; i < 5; i++) {
        this.userLogin.userLoginData.push(
            {
              name: '王小虎',
              date: '2016-05-01',
              space: '上海市普陀区金沙江路 1518 弄'
            }
        )
      }
    },
    // 获取福州大学教学楼停车情况
    getStopStatus() {
    },
    // 获取STOP小程序端日访问量
    getVisitNumber() {
    },
    // 绘制福州大学教学楼停车情况
    paintStopStatus() {
      let domContainer = document.getElementById('park')
      let chart = this.$echarts.init(domContainer)
      chart.setOption(this.graph.parkData)
    },
    // 绘制图表STOP小程序端日访问量
    paintVisitNumber() {
      let domContainer = document.getElementById('visit')
      let chart = this.$echarts.init(domContainer)
      chart.setOption(this.graph.visitData)
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

.user-login-log-container, .graph-container {
  flex: 0 0 50%;
  padding-top: 20px;
  padding-left: 50px;


  .title {
    font-family: "Microsoft YaHei UI Bold", "Microsoft YaHei UI Regular", "Microsoft YaHei UI", sans-serif;
    font-weight: 700;
    font-size: 20px;
    text-decoration: underline;
  }

}

.graph-container {
  display: flex;
  flex-direction: column;

  .graph-container-main {
    display: flex;
    height: 100%;
  }
}

.graph {
  flex: 0 0 50%;
  width: 50%;
  height: 80%;
}


</style>