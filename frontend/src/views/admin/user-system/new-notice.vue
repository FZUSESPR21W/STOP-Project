<template>
  <!-- 发布公告容器 -->
  <div class="new-notice-container">
    <!-- 发布新公告表单 -->
    <div class="input-form">
      <!-- 表单标题 -->
      <div class="title">发布新公告</div>
      <!-- 公告标题 -->
      <el-input v-model="newNoticeData.title" placeholder="请输入标题"/>
      <!-- 公告内容 -->
      <el-input v-model="newNoticeData.content" placeholder="请输入公告内容" type="textarea" :rows="10"/>
      <!-- 发布按钮 -->
      <el-button type="primary" class="submit" @click="publishNotice()">发布</el-button>
    </div>
    <!-- 公布列表 -->
    <div class="notice-list">
      <!-- 公告列表标题 -->
      <div class="title">已发布公告</div>
      <!-- 公告 -->
      <div v-for="(item,index) in noticeList" :key="index" class="list-item" @click="getNoticeDetail(item.id)">{{ item.title }}</div>
      <!-- 分页器 -->
      <el-pagination layout="prev, pager, next" :total="(total / 10) * 10" @current-change="changePage($event)"/>
    </div>
    <!-- 更新弹窗开始 -->
    <el-dialog title="修改公告" :visible.sync="updateNoticeData.display" class="update-dialog">
      <!-- 更新表单开始 -->
      <div class="input-form">
        <!-- 公告标题 -->
        <el-input v-model="updateNoticeData.title" placeholder="请输入标题"/>
        <!-- 公告内容 -->
        <el-input v-model="updateNoticeData.content" placeholder="请输入公告内容" type="textarea" :rows="10"/>
        <!-- 修改按钮 -->
        <el-button type="primary" class="submit" @click="updateNotice">修改</el-button>
      </div>
      <!-- 更新表单结束 -->
    </el-dialog>
    <!-- 更新弹窗结束 -->
  </div>
</template>

<script>
import axios from "@/api/axios";

export default {
  name: "new-notice",
  data() {
    return {
      // 发布新公告的数据
      newNoticeData: {
        // 标题
        title: '',
        // 内容
        content: ''
      },
      // 更新公告的数据
      updateNoticeData: {
        // 标题
        title: '',
        // 内容
        content: '',
        // 公告id
        id: 0,
        // 弹窗显示标志
        display: false,
      },
      // 公告列表
      noticeList: [],
      // 第几页
      page: 1,
      // 页数限制
      limit: 10,
      // 总页数
      total: 0
    }
  },
  beforeMount() {
    // 请求获取公告列表
    this.getNoticeList()
    // 更新面包屑路径
    this.$store.commit('setPageLocations', ['用户','发布公告'])
  },
  methods: {
    // 获取公告列表
    getNoticeList() {
      // 请求获取公告列表
      this.$api.Notice.getNoticeList(this.page, this.limit).then(res => {
        this.noticeList = res.data.data.noticeList
        this.total = res.data.data.totalNum
      })
    },
    // 获取详细公告
    getNoticeDetail(id) {
      // 请求获取详细公告
      this.$api.Notice.getNoticeDetail(id).then(res => {
        // 设置updateNoticeData
        this.updateNoticeData.title = res.data.data.notice.title
        this.updateNoticeData.content = res.data.data.notice.content
        this.updateNoticeData.id = id
        // 显示对话框
        this.updateNoticeData.display = true
      })
    },
    // 发布公告
    publishNotice() {
      // 判断标题非空
      if(this.newNoticeData.title == '') {
        return this.$message.error('标题不能为空！')
      }
      // 判断内容非空
      if(this.newNoticeData.content == '') {
        return this.$message.error('内容不能为空！')
      }
      // 判断
      // 请求发布公告
      this.$api.Notice.publishNotice(this.newNoticeData.title, this.newNoticeData.content,
          false, 1).then(res => {
        // 成功提示信息
        this.$message.success('发布成功')
        // 刷新列表
        this.getNoticeList()
        // 清空数据
        this.newNoticeData.title = ''
        this.newNoticeData.content = ''
      }).catch(err => {
        // 失败提示信息
        this.$message.error('发布失败')
      })
    },
    // 更新公告
    updateNotice() {
      // 请求更新公告
      this.$api.Notice.updateNotice(this.updateNoticeData.id, this.updateNoticeData.title,
          this.updateNoticeData.content, false, 1).then(res => {
        // 成功提示信息
        this.$message.success('修改成功')
        // 刷新列表
        this.getNoticeList()
        // 清空数据
        this.updateNoticeData.title = ''
        this.updateNoticeData.content = ''
        // 关闭窗口
        this.updateNoticeData.display = false
      }).catch(err => {
        // 失败提示信息
        this.$message.error('修改失败')
      })
    },
    // 换页
    changePage(page) {
      this.page = page
      this.getNoticeList()
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
      cursor: pointer;
    }
  }

  .input-form, .notice-list {
    padding: 20px;
  }
}

.update-dialog {

  .input-form {
    padding: 0;
  }
}

</style>