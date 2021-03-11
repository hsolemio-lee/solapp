import Vue from 'vue'
import Router from 'vue-router'

// pages
import uploadPage from '@/page/UploadPage';

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'UploadPage',
      component: uploadPage
    }
  ]
})
