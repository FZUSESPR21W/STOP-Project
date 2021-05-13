<template>
  <!-- 用户反馈容器开始 -->
  <div class="user-feedback-container" v-loading="loading">
    <!-- 用户反馈列表开始 -->
    <div class="user-feedback" v-for="(item, index) in feedbackList" :key="index">
      <!-- 发布人信息 -->

      <div class="username">
        {{ `用户：${item.username}` }}
        <!-- 处理标记 -->
        <el-tag class="tag" :type="item.status == 2 ? 'danger' : 'success'">
          {{ item.status == 2 ? '未处理' : '已处理' }}
        </el-tag>
        <!-- 处理操作按钮 -->
        <span class="update" @click="update(item.id, item.status == 2 ? 3 : 2)">
          <i class="el-icon-s-check"/>
          {{ `标记为${item.status == 2 ? '已处理' : '未处理'}` }}
        </span>
      </div>
      <!-- 发布时间 -->
      <div class="time">{{ item.submitTime }}</div>
      <!-- 查看按钮 -->
      <el-button size="medium" class="check-btn" type="primary" plain @click="checkDetail(item.id)">查看反馈内容</el-button>
    </div>
    <!-- 用户反馈列表结束 -->
    <!-- 分页器 -->
    <el-pagination background layout="prev, pager, next" :total="1000" />
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
      limit: 5,
      //加载动画
      loading: false
    }
  },
  beforeMount() {
    // 更新面包屑路径
    this.$store.commit('setPageLocations', ['用户','用户反馈'])
    // 获取反馈信息列表
    this.getFeedbackList()
  },
  methods: {
    // 更新反馈状态
    update(id, status) {
      // 打开加载动画
      this.loading = true
      this.$api.Feedback.update(id, status).then(res => {
        // 刷新反馈列表
        this.getFeedbackList()
        // 关闭加载动画
        this.loading = false
      }).catch(err => {
        this.$message.error('标记失败')
        // 关闭加载动画
        this.loading = false
      })
    },
    // 获取反馈信息列表
    getFeedbackList() {
      // 请求获取反馈信息列表
      this.$api.Feedback.getFeedbackList(this.page, this.limit).then(res => {
        this.feedbackList = res.data.data.feedbackList
      }).catch(err => {
        this.$message.error('获取反馈列表失败')
      })
    },
    // 获取反馈内容
    checkDetail(id) {
      // 请求获取反馈内容
      this.$api.Feedback.getDetail(id).then(res => {
        // 显示对话框
        this.$alert(res.data.data.feedback.content, '反馈内容', {
          confirmButtonText: '返回',
        })
      }).catch(err => {
        this.$message.error('获取反馈内容失败')
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

  /deep/ .el-pagination {
    margin-top: 10px;
  }

  .user-feedback:nth-child(n+2) {
    margin-top: 20px;
  }

  .user-feedback {

    border-bottom: #9E9E9E 1px solid;

    div {
      margin-bottom: 10px;
    }

    .tag {
      margin-left: 10px;
    }

    .time {
      font-family: 'Microsoft YaHei UI', sans-serif;
      font-weight: 400;
      font-size: 12px;
      color: #969696;
    }

    .username {
      font-family: 'Microsoft YaHei UI', sans-serif;
      font-weight: 400;
      font-style: normal;
      font-size: 15px;
    }

    .content {
      font-family: 'Microsoft YaHei UI', sans-serif;
      font-weight: 400;
      font-style: normal;
      color: #555555;
    }

    .update {
      margin-left: 5px;
      font-size: 14px;
      color: grey;
      cursor: pointer;
      width: 110px;
    }

    .check-btn {
      margin-bottom: 10px;
    }
  }

}
</style>