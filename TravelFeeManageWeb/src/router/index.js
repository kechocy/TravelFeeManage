import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Nav from '../components/Nav.vue'
import Travel from '../components/Travel.vue'
import Postal from '../components/Postal.vue'
import Train from '../components/Train.vue'
import Phone from '../components/Phone.vue'

Vue.use(VueRouter)

const router = new VueRouter({
  routes: [
    { path: '/', redirect: '/login' },
    { path: '/login', component: Login },
    {
      path: '/nav',
      component: Nav,
      redirect: '/travel', // "导航"
      children: [
        { path: '/travel', component: Travel },
        { path: '/postal', component: Postal },
        { path: '/train', component: Train },
        { path: '/phone', component: Phone }
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
