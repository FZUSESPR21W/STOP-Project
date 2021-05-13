import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    pageLocations: []
  },
  mutations: {
    setPageLocations(state, localtions) {
      state.pageLocations = localtions
    }
  },
  getters: {
    pageLocations(state) {
      return state.pageLocations
    }
  },
  actions: {

  },
  modules: {

  }
})
