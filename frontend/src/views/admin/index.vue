<template>
  <div class="admin-page-contianer">
    <nav-side class="nav-side"/>
    <div class="main">
      <bredcrumb />
      <router-view />
    </div>
  </div>
</template>

<script>
import navSide from '@/components/nav-side'
import bredcrumb from '@/components/bredcrumb'

export default {
  name: "index",
  components: {
    navSide,
    bredcrumb
  }
}
</script>

<style lang="scss" scoped>
.admin-page-contianer {
  display: flex;
  height: 100%;

  .nav-side {
    flex: 0 0 180px;
  }

  .main {
    flex: 1;
    overflow-y: scroll;
    overflow-x: hidden;
  }

}


</style>