import Vue from 'vue';
import App from './App.vue';
import vuetify from './plugins/vuetify';
import i18n from './i18n';
import VueResource from 'vue-resource';
import VueRouter from 'vue-router';
import Routes from './routes/routes';

Vue.config.productionTip = false;

Vue.use(VueResource);
Vue.use(VueRouter);

// Register international date format
// const dateTimeFormats = {
//   'en-US': {
//     short: {
//       year: 'numeric',
//       month: 'short',
//       day: 'numeric'
//     },
//     long: {
//       year: 'numeric',
//       month: 'short',
//       day: 'numeric',
//       weekday: 'short',
//       hour: 'numeric',
//       minute: 'numeric'
//     }
//   },
//   'ja-JP': {
//     short: {
//       year: 'numeric',
//       month: 'short',
//       day: 'numeric'
//     },
//     long: {
//       year: 'numeric',
//       month: 'short',
//       day: 'numeric',
//       weekday: 'short',
//       hour: 'numeric',
//       minute: 'numeric',
//       hour12: true
//     }
//   }
// };

// Register international number format
// const numberFormats = {
//   'en-US': {
//     currency: {
//       style: 'currency',
//       currency: 'USD'
//     }
//   },
//   'ja-JP': {
//     currency: {
//       style: 'currency',
//       currency: 'JPY',
//       currencyDisplay: 'symbol'
//     }
//   }
// };

// Register routes
const router = new VueRouter({
  routes: Routes,
  mode: 'history'
});

new Vue({
  vuetify,
  i18n,
  router,
  render: h => h(App)
}).$mount('#app');
