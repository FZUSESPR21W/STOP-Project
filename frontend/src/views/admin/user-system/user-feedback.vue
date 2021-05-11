<template>
  <!-- 用户反馈容器开始 -->
  <div class="user-feedback-container">
    <!-- 用户反馈 -->
    <div class="user-feedback" v-for="(item, index) in feedbackList" :key="index">
      <!-- 标题 -->
      <div class="title">{{ item.title }}</div>
      <!-- 发布人信息 -->
      <div class="info">{{ `用户：${item.name} ${item.submitTime}` }}</div>
      <!-- 内容 -->
      <div class="content">{{ item.content }}</div>
    </div>
  </div>
  <!-- 用户反馈容器结束 -->
</template>

<script>
export default {
  name: "user-feedback",
  data() {
    return {
      // 用户反馈列表
      feedbackList: [],
      // 反馈列表-当前页
      page: 1,
      // 每页限制
      limit: 10
    }
  },
  beforeMount() {
    // 获取反馈信息列表
    this.$api.Feedback.getFeedbackList(this.page, this.limit).then(res => {
      this.feedbackList = res.data.data.feedbackList
    })
  }
}
</script>

<style lang="scss" scoped>
.user-feedback-container {
  display: flex;
  flex-direction: column;
  height: 100%;
  width: 100%;
  padding: 50px;
  overflow-y: scroll;

  .user-feedback:nth-child(n+2) {
    margin-top: 20px;
  }

  .user-feedback {

    border-bottom: #9E9E9E 1px solid;

    div {
      margin-bottom: 10px;
    }

    .title {
      font-family: 'Microsoft YaHei UI', sans-serif;
      font-weight: 400;
      font-style: normal;
      font-size: 23px;
      color: #027DB4;
    }

    .info {
      font-family: 'Microsoft YaHei UI', sans-serif;
      font-weight: 400;
      font-style: normal;
      font-size: 18px;
      color: #AAAAAA;
    }

    .content {
      font-family: 'Microsoft YaHei UI', sans-serif;
      font-weight: 400;
      font-style: normal;
      color: #555555;
    }
  }

}
</style>