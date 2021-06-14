<template>
  <!-- 摄像头管理容器开始 -->
  <div class="camera-manage-container">
    <!-- 设备列表 -->
    <el-table :data="deviceList" style="width: 100%">
      <!-- 设备昵称 -->
      <el-table-column prop="name" label="设备昵称" />
      <!-- 经度 -->
      <el-table-column prop="longitude" label="经度" />
      <!-- 纬度 -->
      <el-table-column prop="latitude" label="纬度" />
      <!-- 可容纳车辆数 -->
      <el-table-column prop="maxCarsNumber" label="可容纳车辆数" />
      <!-- 唯一设备名 -->
      <el-table-column prop="deviceName" label="唯一设备名" />
      <!-- 在线情况 -->
      <el-table-column fixed="right" label="情况" >
        <template slot-scope="scope">
          <el-tag v-if="scope.row.online" type="success">在线</el-tag>
          <el-tag v-else type="danger">离线</el-tag>
        </template>
      </el-table-column>
      <!-- 操作栏 -->
      <el-table-column fixed="right" label="操作" width="250">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="openStream(scope.row.url, scope.row.name)" :disabled="!scope.row.url">实时监控</el-button>
          <el-button type="text" size="small" @click="openEdit(scope.row)">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 设备列表结束 -->
    <!-- 实时监控框 -->
    <el-dialog :title="`实时监控-${streamName}`" :visible.sync="streamDialogVisible" width="1200px" @close="closeStream()">
        <!-- 视频 -->
        <video ref="video" width="100%" height="700px" controls/>
    </el-dialog>
    <!-- 实时监控框结束 -->
    <!-- 编辑信息框 -->
    <el-dialog title="编辑" :visible.sync="updateDialogVisible" width="700px">
        <!-- 信息框 -->
        <div>
          <!-- 设备昵称 -->
          <p>设备昵称：</p>
          <el-input v-model="updateDeviceDetail.name" />
          <!-- 经度 -->
          <p>经度：</p>
          <el-input v-model="updateDeviceDetail.longitude" />
          <!-- 纬度 -->
          <p>纬度：</p>
          <el-input v-model="updateDeviceDetail.latitude" />
          <!-- 可容纳车辆数 -->
          <p>可容纳车辆数：</p>
          <el-input v-model="updateDeviceDetail.maxCarsNumber" />
          <!-- 唯一设备名 -->
          <p>唯一设备名：</p>
          <el-input v-model="updateDeviceDetail.deviceName" />
          <div class="btn-container">
            <el-button @click="updateDialogVisible = false">取消</el-button>
            <el-button type="primary" @click="updateDevice()">修改</el-button>
          </div>
        </div>
        <!-- 信息框结束 -->
    </el-dialog>
    <!-- 编辑信息框结束 -->

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
      // 编辑信息框标志
      updateDialogVisible: false,
      // 编辑设备信息
      updateDeviceDetail: {
        id: 0,
        name: '',
        longitude: 0,
        latitude: 0,
        maxCarsNumber: 0,
        deviceName: ''
      },
      // 直播地址
      streamUrl: '',
      // 直播名
      streamName: '',
      // Hls对象
      hls: {}
    }
  },
  created() {
    // 初始化设备列表
    this.getDeviceList()
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
    },
    // 打开编辑框
    openEdit(deviceObj) {
      this.updateDeviceDetail.id = deviceObj.id
      this.updateDeviceDetail.name = deviceObj.name
      this.updateDeviceDetail.longitude = deviceObj.longitude
      this.updateDeviceDetail.latitude = deviceObj.latitude
      this.updateDeviceDetail.maxCarsNumber = deviceObj.maxCarsNumber
      this.updateDeviceDetail.deviceName = deviceObj.deviceName
      this.updateDialogVisible = true
    },
    // 更新设备
    updateDevice() {
      this.$api.Device.updateDeviceById(this.updateDeviceDetail).then(res => {
        this.$message.success('更新成功')
        this.updateDialogVisible = false
        this.getDeviceList()
      }).catch(err => {
        this.$message.error('更新失败')
      })
    },
    // 获取设备列表
    getDeviceList() {
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
      }).catch(err => {
        this.$message.error('获取列表失败')
      })
    }
  }
}
</script>

<style scoped>
.camera-manage-container {
  padding: 20px;
}

.btn-container {
  display: flex;
  justify-content: flex-end;
  margin-top: 10px;
}
</style>