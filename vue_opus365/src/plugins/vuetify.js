import Vue from 'vue';
import Vuetify from 'vuetify/lib';
// import VueI18n from 'vue-i18n';

Vue.use(Vuetify);
// Vue.use(VueI18n);

// const dateTimeFormats = {
//   'en-US': {
//     short: {
//       year: 'numeric', month: 'short', day: 'numeric'
//     },
//     long: {
//       year: 'numeric', month: 'short', day: 'numeric',
//       weekday: 'short', hour: 'numeric', minute: 'numeric'
//     }
//   },
//   'ja-JP': {
//     short: {
//       year: 'numeric', month: 'short', day: 'numeric'
//     },
//     long: {
//       year: 'numeric', month: 'short', day: 'numeric',
//       weekday: 'short', hour: 'numeric', minute: 'numeric', hour12: true
//     }
//   }
// }

// // Create VueI18n instance with options
// const i18n = new VueI18n({
//   dateTimeFormats,
// })

export default new Vuetify({
  icons: {
    iconfont: 'mdiSvg', // 'mdi' || 'mdiSvg' || 'md' || 'fa' || 'fa4',
  },
  // lang: {
  //   t: (key, ...params) => i18n.t(key, params),
  // },
});
