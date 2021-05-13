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
          {{ `标记为${ item.status == 2 ? '已处理' : '未处理'}` }}
        </span>
      </div>
      <!-- 发布时间 -->
      <div class="time">{{ item.submitTime }}</div>
      <!-- 查看按钮 -->
      <el-button size="medium" class="check-btn" type="primary" plain @click="checkDetail(item.id)">查看反馈内容</el-button>
    </div>
    <!-- 用户反馈列表结束 -->
    <!-- 分页器 -->
    <el-pagination background layout="prev, pager, next" :total="(total / 5) * 10" @current-change="changePage($event)"/>

    <!-- 详细反馈对话框开始 -->
    <el-dialog title="提示" :visible.sync="showFeedBackDialog" width="30%" >
      <span>{{ feedbackContent }}</span>
      <div class="imageList" v-if="imageUrlList.length !== 0">
        <h5>反馈图片：</h5>
        <a v-for="item in imageUrlList" :href="`https://api.shawnxixi.icu${item}`">{{ item }}</a>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="showFeedBackDialog = false">返回</el-button>
      </span>
    </el-dialog>
    <!-- 详细反馈对话框结束 -->


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
      // 图片路径表
      imageUrlList: [],
      // 反馈列表-当前页
      page: 1,
      // 每页限制
      limit: 5,
      // 总记录
      total: 0,
      // 显示反馈对话框标志
      showFeedBackDialog: false,
      // 反馈内容
      feedbackContent: '',
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
        this.total = res.data.data.total
      }).catch(err => {
        this.$message.error('获取反馈列表失败')
      })
    },
    // 获取反馈内容
    checkDetail(id) {
      // 请求获取反馈内容
      this.$api.Feedback.getDetail(id).then(res => {

        this.imageUrlList = []

        let content = ''
        try {
          // 分拆图片数据
          let temp = res.data.data.feedback.content.split('[imageList]')
          this.feedbackContent = temp[0]
          this.imageUrlList = JSON.parse(temp[1])
        }
        catch(e) {
          // 如果没有图片数据
          this.feedbackContent = res.data.data.feedback.content.split('[imageList]')[0]
        }

        // 显示对话框
        this.showFeedBackDialog = true
      }).catch(err => {
        console.log(err)
        this.$message.error('获取反馈内容失败')
      })
    },
    // 换页
    changePage(page) {
      this.page = page
      this.getFeedbackList()
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

.imageList {
  display: flex;
  flex-direction: column;
}
</style>