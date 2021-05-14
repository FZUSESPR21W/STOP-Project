import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    // 面包屑路径
    pageLocations: [],
    // 登陆状态
    loginStatus: false
  },
  mutations: {
    setPageLocations(state, localtions) {
      state.pageLocations = localtions
    },
    setLoginStatus(state, flag) {
      state.loginStatus = flag
    }
  },
  getters: {
    pageLocations(state) {
      return state.pageLocations
    },
    loginStatus(state) {
      return state.loginStatus
    }
  },
  actions: {

  },
  modules: {

  }
})
