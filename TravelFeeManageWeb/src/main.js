import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import './plugins/element.js'
import ValidCode from './components/ValidCode.vue'

// 导入全局样式表
import './assets/css/global.css'
// 导入阿里图标库样式
import './assets/icon/iconfont.css'

Vue.component('ValidCode', ValidCode)

Vue.config.productionTip = false

Vue.prototype.$http = axios
axios.defaults.baseURL = 'http://localhost:8098/api/'
axios.interceptors.request.use(config => {
  config.headers.Authorization = window.sessionStorage.getItem('token')
  return config
})

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
