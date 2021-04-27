import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: () => import('@/views/login/index')
  },
  {
    path: '/admin',
    name: 'Admin',
    component: () => import('@/views/admin/index'),
    children: [
      {
        path: 'baseState',
        name: 'BaseState',
        component: () => import('@/views/admin/basic-info/base-state')
      },
      {
        path: 'carState',
        name: 'CarState',
        component: () => import('@/views/admin/basic-info/car-state')
      },
      {
        path: 'newNotice',
        name: 'NewNotice',
        component: () => import('@/views/admin/user-system/new-notice')
      },
      {
        path: 'userFeedback',
        name: 'UserFeedback',
        component: () => import('@/views/admin/user-system/user-feedback')
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
