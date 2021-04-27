<template>
  <div class="login-contianer">
    <div class="login-box">
      <div class="input-item-title">用户名</div>
      <el-input class="input-item" placeholder="请输入用户名" v-model="username" />
      <div class="input-item-title">密码</div>
      <el-input class="input-item"  placeholder="请输入密码" v-model="password" show-password />
      <el-button class="login-btn">登录</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "index",
  data() {
    return {
      username: '',
      password: ''
    }
  }
}
</script>

<style lang="scss" scoped>
.login-contianer {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  width: 100%;

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