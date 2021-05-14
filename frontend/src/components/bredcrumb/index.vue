<template>
  <!-- 面包屑顶部栏开始 -->
  <div class="bredcrumb-container">
    <!-- 左部容器开始 -->
    <div class="left">
      <i class="el-icon-s-unfold icon" />
      <!-- 面包屑容器开始 -->
      <el-breadcrumb separator="/">
        <!-- 面包屑 -->
        <el-breadcrumb-item v-for="(item, index) in pageLocations" :key="index">{{ item }}</el-breadcrumb-item>
      </el-breadcrumb>
      <!-- 面包屑容器结束 -->
    </div>
    <!-- 左部容器结束 -->
    <!-- 登出按钮 -->
    <el-button type="primary" @click="logout" class="logout-btn" plain>登出</el-button>
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
  },
  methods: {
    // 登出
    logout() {
      // 请求登出
      this.$api.Admin.logout().then(res => {
        this.$message.success('登出成功！')
        this.$store.commit('setLoginStatus', false)
        this.$router.push('/')
      }).catch(err => {
        this.$message.error('登出失败！')
      })
    }
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
  justify-content: space-between;

  .left {
    display: flex;
    align-items: center;
  }

  .icon {
    margin: 10px;
    font-size: 20px;
  }

  .logout-btn {
    margin-right: 20px;
  }

  /deep/ .el-breadcrumb__inner {
    font-size: 16px;
  }

  /deep/ .el-breadcrumb__separator{
    color: #A193A8;
  }
}
</style>