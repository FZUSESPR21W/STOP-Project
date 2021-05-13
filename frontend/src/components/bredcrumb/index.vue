<template>
  <!-- 面包屑顶部栏开始 -->
  <div class="bredcrumb-container">
    <i class="el-icon-s-unfold icon" />
    <!-- 面包屑容器开始 -->
    <el-breadcrumb separator="/">
      <!-- 面包屑 -->
      <el-breadcrumb-item v-for="item in pageLocations">{{ item }}</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 面包屑容器结束 -->
  </div>
  <!-- 面包屑顶部栏结束 -->
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: "index",
  computed: {
    ...mapGetters([
        'pageLocations'
    ])
  }
}
</script>

<style lang="scss" scoped>
.bredcrumb-container {
  height: 50px;
  background-color: #D1C2D3;
  overflow: hidden;

  display: flex;
  align-items: center;

  .icon {
    margin: 10px;
    font-size: 20px;
  }

  /deep/ .el-breadcrumb__inner {
    font-size: 16px;
  }

  /deep/ .el-breadcrumb__separator{
    color: #A193A8;
  }
}
</style>