<template>
  <div class="side-bar-contianer">
    <el-menu :default-active="$route.path" background-color="#813C85" text-color="#ffffff" active-text-color="#ffd04b" router>
      <el-submenu index="/admin/baseState">
        <template slot="title">
          <i class="el-icon-set-up icon" />
          <span>基础信息</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="/admin/baseState">基础情况</el-menu-item>
          <el-menu-item index="/admin/carState">车辆情况</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-submenu index="2">
        <template slot="title">
          <i class="el-icon-tickets icon" />
          <span>用户系统</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="/admin/newNotice">发布公告</el-menu-item>
          <el-menu-item index="/admin/userFeedback">用户反馈</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-submenu index="3">
        <template slot="title">
          <i class="el-icon-video-camera icon" />
          <span>监控系统</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="/admin/cameraManage">摄像头管理</el-menu-item>
          <el-menu-item index="/admin/mapState">地图情况</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "index"
}
</script>

<style lang="scss" scoped>
.side-bar-contianer {
  height: 100%;
  width: 180px;
  background-color: #BC84A8;
  overflow: hidden;

  .icon {
    color: white
  }
}

.side-bar-contianer /deep/ .el-menu {
  border-right: none;
}

.side-bar-contianer /deep/ .el-menu-item-group__title {
  padding: 0;
}

.side-bar-contianer /deep/ .el-menu-item-group li {
  background-color: #806D9E !important;
}
</style>