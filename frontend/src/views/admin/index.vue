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

import { mapGetters } from 'vuex'

export default {
  name: "index",
  components: {
    navSide,
    bredcrumb
  },
  computed: {
    ...mapGetters([
        'loginStatus'
    ])
  },
  created() {
    // 登陆判断
    if(this.loginStatus == false) {
      this.$message.error('请先登陆')
      this.$router.replace('/')
    }
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