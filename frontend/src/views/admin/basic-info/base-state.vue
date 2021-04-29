<template>
  <!-- 基本情况页面容器开始 -->
  <div class="base-state-container">
    <!-- 用户登录记录开始 -->
    <div class="user-login-log-container">
      <span class="title">用户登录记录</span>
      <!-- 用户登录记录表格，数据来源：userLoginData -->
      <el-table :data="userLogin.userLoginData" style="width: 100%" stripe>
        <!-- 日期字段 -->
        <el-table-column prop="date" label="日期" width="180" />
        <!-- 姓名字段 -->
        <el-table-column prop="name" label="姓名" width="180" />
        <!-- 登录地字段 -->
        <el-table-column prop="space" label="地址" />
      </el-table>
      <!-- 用户登录记录表格结束 -->
    </div>
    <!-- 用户登录记录结束 -->
    <!-- 数据可视化(echarts图表）开始 -->
    <div class="graph-container">
      <span class="title">数据可视化</span>
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
      //用户登录记录对象
      userLogin: {
        //用户登录数据
        userLoginData: [],
        //页面下标
        page: 0,
        //每页限制条数
        limit: 5
      },
      //数据可视化(echarts图表）对象
      graph: {

      }
    }
  },
  beforeMount() {
    //填充假数据
    for(let i = 0 ; i < 5 ; i ++){
        this.userLogin.userLoginData.push(
            {
              name: '王小虎',
              date: '2016-05-01',
              space: '上海市普陀区金沙江路 1518 弄'
            }
        )
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

.user-login-log-container, .graph-container{
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





</style>