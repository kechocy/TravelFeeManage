import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Nav from '../components/Nav.vue'
import Home from '../components/Home.vue'
import Foot from '../components/Foot.vue'
import Message from '../components/Message.vue'
Vue.use(VueRouter)

const router = new VueRouter({
  routes: [
    { path: '/', redirect: '/login' },
    { path: '/login', component: Login },
    {
      path: '/nav',
      component: Nav,
      redirect: '/home', // "导航"
      children: [
        { path: '/home', component: Home },
        { path: '/foot', component: Foot },
        { path: '/message', component: Message }
      ]
    }
  ]
})

// 挂载路由导航守卫
router.beforeEach((to, from, next) => {
  if (to.path === '/login') return next()
  const tokenStr = window.sessionStorage.getItem('token')
  if (!tokenStr) return next('/login')
  return next()
})

export default router
