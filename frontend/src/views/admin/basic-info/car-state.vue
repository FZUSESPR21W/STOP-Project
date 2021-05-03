<template>
  <div class="car-state-contianer">
    <div id="car" class="graph"/>
  </div>
</template>

<script>
export default {
  name: "car-state",
  data() {
    return {
      graph: {
        car: {

        }
      }
    }
  },
  beforeMount() {
    // 获取停车情况
    this.getCarState()
  },
  mounted() {
    // 绘制车辆信息图表
    this.paintCarState()
  },
  methods: {
    // 获取停车情况
    getCarState() {},
    // 绘制车辆信息图表
    paintCarState() {}
  }
}
</script>

<style lang="scss" scoped>
.car-state-contianer {
  height: 100%;
  width: 100%;

  .graph {
    width: 100%;
    height: 50%;
  }
}
</style>