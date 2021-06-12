<template>
  <div>
    <el-table :data="deviceList" style="width: 100%">
      <el-table-column prop="name" label="设备昵称" />
      <el-table-column prop="longitude" label="经度" />
      <el-table-column prop="latitude" label="纬度" />
      <el-table-column prop="maxCarsNumber" label="可容纳车辆数" />
      <el-table-column prop="deviceName" label="唯一设备名" />

    </el-table>
  </div>
</template>

<script>
export default {
  name: "camera-manage",
  data() {
    return {
      deviceList: []
    }
  },
  created() {
    this.$api.Device.getAllDevice().then(res => {
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
        if(Object.prototype.toString.call(item.deviceDTO) != '[object Null]') {
          obj.url = item.deviceDTO.url
        }
        return obj
      })
    })
  }
}
</script>

<style scoped>

</style>