import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/page/home'
import Login from '@/page/login'
import Pileup from '@/page/pileup'
import Bus from '@/assets/bus'

Vue.use(Router);

const router = new Router({
  routes: [
    {
      path: '/',
      component: Login
    },
    {
      path: '/main',
      component: Home
    },
    {
      path: '/pileup',
      component: Pileup
    }
  ]
});

router.beforeEach((to, from, next) => {
  if (Bus.isLogin || (!Bus.isLogin && to.path == '/')) next();
  else next({path: '/'})
});

export default router;
