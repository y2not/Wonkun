import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/Login'
import MyInfo from '../components/MyInfo'

Vue.use(Router)

export default new Router({
  mode : "history",
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/myInfo',
      name: 'MyInfo',
      component: MyInfo
    }
  ]
})


