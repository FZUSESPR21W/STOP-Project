<template>
  <!-- 地图容器 -->
  <div class="map-container">
    <!-- 高德地图 -->
    <amap :zoom="18" :center="[119.19206586536791, 26.05064261000777]" ref="Amap">
      <amap-marker v-for="item in pointList" :key="item.name" :position="[item.longitude, item.latitude]"
                   :title="item.name"/>
      <amap-polygon v-for="(item, index) in polygons" :key="index"
                    :path="polygons[index]" :fillColor="polygons[index].fillColor"
                    :stroke="polygons[index].strokeColor"/>
    </amap>
    <!-- 地点列表 -->
    <div class="place-list">
      <div class="place-list-title">
        <span>地点列表</span>
        <el-input class="search-input" v-model="searchInput" placeholder="请输入要搜索的地点名" size="mini" />
      </div>
      <div v-for="item in pointList.filter((item) => {return item.name.includes(searchInput)})" class="place-list-item" @click="changeCenter(item.longitude, item.latitude)">
        <span>{{ item.name }}</span>
        <span v-if="item.capacity <= 0.5" class="parking-remain">车位空闲</span>
        <span v-if="item.capacity > 0.5 && item.capacity <= 0.7" class="parking-warning">车位紧张</span>
        <span v-if="item.capacity > 0.7" class="parking-alert">车位不足</span>
      </div>
    </div>
  </div>
  <!-- 地图容器结束 -->
</template>

<script>
export default {
  name: "map-state",
  data() {
    return {
      pointList: [],
      polygons: [],
      searchInput: ''
    }
  },
  created() {
    // 更新面包屑路径
    this.$store.commit('setPageLocations', ['监控', '地图'])
    // 初始化数据
    this.getAllDevice()
    this.getPoints()
  },
  methods: {
    // 改变地图中心点
    changeCenter(longitude, latitude) {
      this.$refs.Amap.$map.setCenter([longitude, latitude])
    },
    // 获取地图点数据
    getAllDevice() {
      this.$api.Map.getAllDevice().then(deviceRes => {
        this.$api.Map.getParkingValue().then(valueRes => {
          let parkValue = []
          valueRes.data.data.stopStatusList.forEach(item => {
            parkValue[item.id] = item.value / item.volume
          })
          deviceRes.data.data.deviceList.forEach(item => {
            if (item.online) {
              this.pointList.push({
                id: item.deviceDO.id,
                longitude: item.deviceDO.longitude,
                latitude: item.deviceDO.latitude,
                name: item.deviceDO.name,
                capacity: parkValue[item.deviceDO.id]
              })
            }
          })
        })
      }).catch(err => {
        this.$message.error('数据初始化失败')
      })
    },
    // 获取区域点集合
    getPoints() {
      this.$api.Map.getPoints().then(res => {
        res.data.data.DeviceAndPointsInfo.forEach(item => {
          if (item.online) {
            let path = []
            item.points.forEach(point => {
              path.push([parseFloat(point.longitude), parseFloat(point.latitude)])
            })
            let index = parseInt(item.value) / parseInt(item.maxValue)
            if (index <= 0.5 && index >= 0) {
              path.strokeColor = '#266339'
              path.fillColor = '#7FFFAA'
            } else if (index > 0.5 && index < 0.7) {
              path.strokeColor = '#194063'
              path.fillColor = '#70aeff'
            } else if (index < 0) {
              path.strokeColor = '#adadad'
              path.fillColor = '#d1d1d1'
            } else {
              path.strokeColor = '#732907'
              path.fillColor = '#ff5f54'
            }
            this.polygons.push(path)
          }
        })
      })
    }
  }
}
</script>

<style scoped>
  .map-container {
    position: absolute;
    width: 100%;
    height: 100%;
  }

  .place-list {
    position: relative;
    top: -96%;
    left: 30px;
    background-color: white;
    height: 80%;
    width: 300px;
    border-radius: 5px;
    box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
    padding: 5px;
  }

  .place-list-title {
    font-size: 18px;
    width: 100%;
    padding-top: 5px;
    padding-left: 5px;
    padding-bottom: 5px;
    border-bottom: 3px solid #BB83A7;
    display: flex;
    justify-content: space-around;
  }

  .place-list-item {
    padding: 5px;
    margin: 10px;
    border-bottom: 1px solid #8c8c8c;
    cursor: pointer;
    font-size: 17px;
    letter-spacing: 1px;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .search-input {
    width: 200px;
  }

  .parking-remain, .parking-alert , .parking-warning {
    font-size: 12px;
  }

  .parking-remain {
    color: green;
  }

  .parking-alert {
    color: red;
  }

  .parking-warning {
    color: yellow;
  }
</style>