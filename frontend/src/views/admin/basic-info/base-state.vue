<template>
  <!-- 基本情况页面容器开始 -->
  <div class="base-state-container">
    <!-- 用户登录记录开始 -->
    <div class="user-login-log-containerr">

    </div>
    <!-- 用户登录记录结束 -->
    <!-- 数据可视化(echarts图表）开始 -->
    <div class="echarts-container">

    </div>
    <!-- 数据可视化(echarts图表）结束 -->
  </div>
  <!-- 基本情况页面容器结束 -->
</template>

<script>
export default {
  name: "base-state"
}
</script>

<style lang="scss" scoped>
.base-state-contianer {
  display: flex;
  flex-direction: column;
}

.user-login-log-contianer {
  flex: 0 0 50%;
}


</style>