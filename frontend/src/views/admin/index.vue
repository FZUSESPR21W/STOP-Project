<template>
  <div class="admin-page-contianer">
    <nav-side class="nav-side"/>
    <router-view class="main"/>
  </div>
</template>

<script>
import navSide from '@/components/nav-side'

export default {
  name: "index",
  components: {
    navSide
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
  }

}


</style>