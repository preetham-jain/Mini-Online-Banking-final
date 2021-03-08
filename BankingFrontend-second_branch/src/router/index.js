import Vue from 'vue'
import VueRouter from 'vue-router'
import home from '@/views/home.vue'
import register from '@/views/register.vue'
import login from '@/views/login.vue'
import welcome from '@/views/welcome.vue'
import transactiondetails from '@/views/transactiondetails.vue'
import deposit from '@/views/deposit.vue'
import withdraw from '@/views/withdraw.vue'
import accountdetails from '@/views/accountdetails.vue'
import transfer from '@/views/transfer.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: home
  },
  {
    path: '/login',
    name: 'login',
    component: login
  },
  {
    path: '/register',
    name: 'register',
    component: register
  },
  {
    path: '/welcome',
    name: 'welcome',
    component: welcome
  },
  {
    path: '/transactiondetails',
    name: 'transactiondetails',
    component: transactiondetails
  },
  {
    path: '/deposit',
    name: 'deposit',
    component: deposit
  },
  {
    path: '/withdraw',
    name: 'withdraw',
    component: withdraw
  },
  {
    path: '/accountdetails',
    name: 'accountdetails',
    component: accountdetails
  },
  {
    path: '/transfer',
    name: 'transfer',
    component: transfer
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
