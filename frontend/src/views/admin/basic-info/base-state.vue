<template>
  <!-- 基本情况页面容器开始 -->
  <div class="base-state-container">
    <!-- 用户登录记录开始 -->
    <div class="user-login-log-container">
      <span class="title">用户登录记录</span>
      <!-- 提示banner -->
      <el-alert title="项目未上线，无法获得数据" type="warning" show-icon class="banner"/>
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
import initParkData from './init-graph-data/park-data'
import initVisitData from './init-graph-data/visit-data'

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
        // 福州大学教学楼停车情况数据 深拷贝
        parkData: JSON.parse(JSON.stringify(initParkData)),
        // STOP小程序端日访问量数据 深拷贝
        visitData: JSON.parse(JSON.stringify(initVisitData))
      }
    }
  },
  beforeMount() {
    // 数据初始化
    this.getUserLoginData()
    this.getStopStatus()
    this.getVisitNumber()
    // 更新面包屑路径
    this.$store.commit('setPageLocations', ['基础','基本情况'])
  },
  mounted() {
    // 绘制图表
    this.paintStopStatus()
    this.paintVisitNumber()
  },
  methods: {
    //获取用户登录数据
    getUserLoginData() {
      // 项目未上线暂时无法取得数据
      // 填充假数据
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

  .banner {
    margin-top: 10px;
  }


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