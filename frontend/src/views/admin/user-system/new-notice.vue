<template>
  <!-- 发布公告容器 -->
  <div class="new-notice-container">
    <!-- 发布新公告表单 -->
    <div class="input-form">
      <!-- 表单标题 -->
      <div class="title">发布新公告</div>
      <!-- 公告标题 -->
      <el-input v-model="newNotice.title" placeholder="请输入标题"/>
      <!-- 公告内容 -->
      <el-input v-model="newNotice.content" placeholder="请输入公告内容" type="textarea" :rows="25"/>
      <!-- 发布按钮 -->
      <el-button type="primary" class="submit">发布</el-button>
    </div>
    <!-- 公布列表 -->
    <div class="notice-list">
      <!-- 公告列表标题 -->
      <div class="title">已发布公告</div>
      <!-- 公告 -->
      <div v-for="(item,index) in noticeList" :key="index" class="list-item">{{ item.title }}</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "new-notice",
  data() {
    return {
      // 发布新公告的数据
      newNotice: {
        // 标题
        title: '',
        // 内容
        content: ''
      },
      // 公告列表
      noticeList: []
    }
  },
  beforeMount() {
    for(let i = 0 ; i < 10 ; i ++){
      this.noticeList.push({
        title: '东三路口现正发生交通意外，请绕行'
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.new-notice-container {
  display: flex;

  .title {
    font-family: "Microsoft YaHei UI Bold", "Microsoft YaHei UI Regular", "Microsoft YaHei UI", sans-serif;
    font-weight: 700;
    font-style: normal;
    font-size: 20px;
    text-decoration: underline;
    color: rgb(48, 49, 51);
  }

  .input-form {
    flex: 1;

    .submit {
      width: 120px;
      margin-left: calc(100% - 120px);
    }

    *:nth-child(n+2) {
      margin-top: 20px;
    }

  }

  .notice-list {
    flex: 0 0 400px;

    .list-item {
      font-family: "Microsoft YaHei UI", sans-serif;
      font-weight: 400;
      font-style: normal;
      font-size: 15px;
      color: rgb(127, 127, 127);
      margin: 10px;
      text-decoration: underline;
    }
  }

  .input-form, .notice-list {
    padding: 20px;
  }
}

</style>