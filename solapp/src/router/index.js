import Vue from 'vue'
import Router from 'vue-router'

// pages
import uploadPage from '@/page/UploadPage';
import mainPage from '@/page/mainPage';
import joinPage from '@/page/joinPage';
import loginPage from '@/page/loginPage';

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'mainPage',
      component: mainPage
    },
    {
      path: '/loginForm',
      name: 'loginPage',
      component: loginPage
    },
    {
      path: '/joinForm',
      name: 'joinPage',
      component: joinPage
    }
  ]
})
