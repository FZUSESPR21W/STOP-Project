import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

//引入echarts
import * as echarts from 'echarts'
//原型链挂载echarts
Vue.prototype.$echarts = echarts

//引入highcharts
import Highcharts from 'highcharts/highstock'
//原型链挂载highcharts
Vue.prototype.$highcarts = Highcharts

//引入element-ui
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

//Vue插件使用element-ui
Vue.use(ElementUI)

//引入分离的api接口
import api from './api'
Vue.use(api)

//引入高德地图
import AmapVue from '@amap/amap-vue'
AmapVue.config.key = 'c39dd5801e3e92132bea271c0d7abdc0'
Vue.use(AmapVue)

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
