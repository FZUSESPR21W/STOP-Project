<template>
  <!-- 摄像头管理容器开始 -->
  <div class="camera-manage-container">
    <!-- 设备列表 -->
    <el-table :data="deviceList" style="width: 100%">
      <el-table-column prop="name" label="设备昵称" />
      <el-table-column prop="longitude" label="经度" />
      <el-table-column prop="latitude" label="纬度" />
      <el-table-column prop="maxCarsNumber" label="可容纳车辆数" />
      <el-table-column prop="deviceName" label="唯一设备名" />
      <!-- 操作栏 -->
      <el-table-column fixed="right" label="操作" width="250">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="openStream(scope.row.url, scope.row.name)">实时监控</el-button>
          <el-button type="text" size="small">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 设备列表结束 -->
    <!-- 实时监控框 -->
    <el-dialog :title="`实时监控-${streamName}`" :visible.sync="streamDialogVisible" width="1200px" @close="closeStream()">
        <video ref="video" width="100%" height="700px" controls/>
    </el-dialog>
  </div>
  <!-- 摄像头管理容器结束 -->
</template>

<script>
import Hls from 'hls.js'

export default {
  name: "camera-manage",
  data() {
    return {
      // 设备列表
      deviceList: [],
      // 实时监控框标志
      streamDialogVisible: false,
      // 直播地址
      streamUrl: '',
      // 直播名
      streamName: '',
      // Hls对象
      hls: {}
    }
  },
  created() {
    // 调用获取设备列表
    this.$api.Device.getAllDevice().then(res => {
      // 数据扁平化
      this.deviceList = res.data.data.deviceList.map((item) => {
        let obj = {
          id: item.deviceDO.id,
          name: item.deviceDO.name || '未知',
          longitude: item.deviceDO.longitude || '未知',
          latitude: item.deviceDO.latitude || '未知',
          maxCarsNumber: item.deviceDO.maxCarsNumber || '未知',
          deviceName: item.deviceDO.deviceName || '未知',
          online: item.online
        }
        // 判断DTO是否存在，如存在就把url属性加入
        if(Object.prototype.toString.call(item.deviceDTO) != '[object Null]') {
          obj.url = item.deviceDTO.url
        }
        return obj
      })
    })
  },
  methods: {
    // 打开监控
    openStream(url, name) {
      this.streamDialogVisible = true
      this.streamUrl = url
      this.streamName = name
      if(Hls.isSupported()) {
        this.hls = new Hls()
        this.hls.loadSource(this.streamUrl);
        this.hls.attachMedia(this.$refs.video);
        this.hls.on(Hls.Events.MANIFEST_PARSED, () => {
          console.log('加载成功')
          this.$refs.video.play()
        });
        this.hls.on(Hls.Events.ERROR, (event, data) => {
          // console.log(event, data);
          // 监听出错事件
          console.log('加载失败')
        });
      }
    },
    // 关闭监控
    closeStream() {
      this.$refs.video.pause()
      this.hls.destroy()
      this.hls = null
    }
  }
}
</script>

<style scoped>
.camera-manage-container {
  padding: 20px;
}
</style>