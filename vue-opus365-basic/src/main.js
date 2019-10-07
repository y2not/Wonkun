import Vue from 'vue';
import VueRouter from 'vue-router';
import Routes from './routes';
import App from './App.vue';
import vuetify from './plugins/vuetify';
import VueI18n from 'vue-i18n';
import VueResource from 'vue-resource';
import { store } from './store';

Vue.config.productionTip = false;

Vue.use(VueI18n);
Vue.use(VueRouter);
Vue.use(VueResource);

const messages = {};

const i18n = new VueI18n({
  locale: 'en',
  messages
});

const router = new VueRouter({
  mode: 'history',
  routes: Routes
});

new Vue({
  vuetify,
  i18n,
  VueI18n,
  store,
  // data: {
  //   url: '/term'
  // },
  render: h => h(App),
  router
}).$mount('#app');
