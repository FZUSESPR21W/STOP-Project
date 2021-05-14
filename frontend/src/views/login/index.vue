<template>
  <!-- 登录页面容器开始 -->
  <div class="login-container">
    <!-- 登录页面标题 -->
    <span class="login-title">欢迎登录STOP后台管理系统</span>
    <!-- 登录框开始 -->
    <div class="login-box">
      <!-- 用户名标题 -->
      <div class="input-item-title">用户名</div>
      <!-- 用户名输入框 -->
      <el-input class="input-item" placeholder="请输入用户名" v-model="username" />
      <!-- 密码标题 -->
      <div class="input-item-title">密码</div>
      <!-- 密码输入框 -->
      <el-input class="input-item"  placeholder="请输入密码" v-model="password" show-password />
      <!-- 登陆按钮 -->
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
      // 用户名
      username: '',
      // 密码
      password: ''
    }
  },
  methods: {
    // 登录
    login() {
      // 判断账号非空
      if(this.username == '') {
        return this.$message.error('账号不能为空！')
      }
      // 判断密码非空
      if(this.password == '') {
        return this.$message.error('密码不能为空！')
      }
      // 登陆请求
      this.$api.Admin.login(this.username, this.password).then(res => {
        if(res.data.message === '登陆成功') {
          // 登陆成功提示信息
          this.$message.success(res.data.message)
          // 设置登陆状态
          this.$store.commit('setLoginStatus', true)
          // 路由跳转
          this.$router.push('/admin')
        }
        else {
          // 密码错误抛出异常
          throw(res.data.message)
        }
      }).catch(err => {
        // 如果是除了200状态码意外的状态码
        if(err.response instanceof Object) {
          // 错误信息提示
          this.$message.error(`登陆失败, code: ${err.response.status}`)
        }
        // 密码错误抛出异常
        else {
          // 错误信息提示
          this.$message.error(err)
        }
      })

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