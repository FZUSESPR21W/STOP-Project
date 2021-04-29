<template>
  <!-- 登录页面容器开始 -->
  <div class="login-container">
    <!-- 登录页面标题 -->
    <span class="login-title">欢迎登录STOP后台管理系统</span>
    <!-- 登录框开始 -->
    <div class="login-box">
      <div class="input-item-title">用户名</div>
      <el-input class="input-item" placeholder="请输入用户名" v-model="username" />
      <div class="input-item-title">密码</div>
      <el-input class="input-item"  placeholder="请输入密码" v-model="password" show-password />
      <el-button class="login-btn" @click="login">登录</el-button>
    </div>
    <!-- 登录框结束 -->
  </div>
  <!-- 登录页面容器结束 -->
</template>

<script>
export default {
  name: "index",
  data() {
    return {
      //用户名
      username: '',
      //密码
      password: ''
    }
  },
  methods: {
    //登录
    login() {
      //路由跳转
      this.$router.push('/admin')
    }
  }
}
</script>

<style lang="scss" scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  height: 100%;
  width: 100%;

  .login-title {
    font-family: '微软雅黑 Bold', '微软雅黑', sans-serif;
    font-weight: 700;
    font-style: normal;
    font-size: 35px;
    color: #BC84A8;
    padding-bottom: 30px;
  }

  .login-box {
    height: 320px;
    width: 500px;
    border: 0.5px solid grey;
    border-radius: 5px;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    align-items: center;
    padding: 0 20px;


    .input-item-title {
      width: 100%;
      font-size: 19px;
    }

    .login-btn {
      width: 135px;
      font-size: 18px;
      background-color: #BC84A8;
      color: white;
      border: 0;
    }

    .login-btn:hover {
      opacity: 0.8;
    }
  }
}
</style>