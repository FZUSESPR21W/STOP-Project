<template>
  <!-- 地图容器 -->
  <div class="map-container">
    <!-- 高德地图 -->
    <amap :zoom="16" :center="[119.19206586536791, 26.05064261000777]" ref="Amap">
      <amap-marker v-for="item in pointList" :key="item.name" :position="[item.longitude, item.latitude]" :title="item.name"/>
    </amap>
  </div>
  <!-- 地图容器结束 -->
</template>

<script>
export default {
  name: "map-state",
  data() {
    return {
      pointList: []
    }
  },
  created() {
    // 更新面包屑路径
    this.$store.commit('setPageLocations', ['监控', '地图'])
    // 初始化数据
    this.getMapInfo()
  },
  methods: {
    // 获取地图点数据
    getMapInfo() {
      this.$api.Map.getAllDevice().then(res => {
        res.data.data.deviceList.forEach(item => {
          if(item.online) {
            this.pointList.push({
              longitude: item.deviceDO.longitude,
              latitude: item.deviceDO.latitude,
              name: item.deviceDO.name,
            })
          }
        })
      })
    }
  }
}
</script>

<style scoped>
.map-container {
  height: 100%;
}
</style>