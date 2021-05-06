<template>
  <!-- 用户反馈容器开始 -->
  <div class="user-feedback-container">
    <!-- 用户反馈 -->
    <div class="user-feedback" v-for="(item, index) in feedbackList" :key="index">
      <!-- 标题 -->
      <div class="title">{{ item.title }}</div>
      <!-- 发布人信息 -->
      <div class="info">{{ `用户：${item.name} ${item.publish_time}` }}</div>
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
      //用户反馈列表
      feedbackList: []
    }
  },
  beforeMount() {
    for(let i = 0 ; i < 10 ; i ++){
      this.feedbackList.push({
        title: '关于xxxxxxxxxxxxxxx的反馈',
        name: 'xxx',
        publish_time: '2020-00-00 00:00',
        content: '正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文'
      })
    }
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