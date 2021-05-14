<template>
  <div id="app">
    <topBar class="top-bar"/>
    <router-view class="router-view"/>
  </div>
</template>

<script>
import topBar from '@/components/top-bar'
export default {
  name: 'app',
  components: {
    topBar
  }
}
</script>

<style lang="scss">

html, body, #app {
  margin: 0;
  height: 100%;
  overflow: hidden;
}

#app {
  display: flex;
  flex-direction: column;
}

.top-bar {
  flex: 0 0 63px;
}

.router-view {
  flex: 1;
}

::-webkit-scrollbar {display:none}

</style>
