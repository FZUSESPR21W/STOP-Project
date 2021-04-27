<template>
  <div class="top-bar-contianer">
    <span>后台管理系统</span>
    <span>©CopyRight 2021 发际线和我作队</span>
  </div>
</template>

<script>
export default {
  name: "index"
}
</script>

<style lang="scss" scoped>
.top-bar-contianer {
  height: 63px;
  width: 100%;
  background-color: #BB83A7;
  display: flex;
  justify-content: space-between;
  align-items: center;

  span {
    font-size: 19px;
    color: white;
    font-weight: 700;
    margin: 10px;
  }
}
</style>