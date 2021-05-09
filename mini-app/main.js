import Vue from 'vue'
import App from './App'

Vue.config.productionTip = false

//引入uView
import uView from 'uview-ui'
Vue.use(uView)

// //引入分离的api接口
import * as api from './api/api.js'
Vue.prototype.$api = api

App.mpType = 'app'

const app = new Vue({
    ...App
})
app.$mount()
