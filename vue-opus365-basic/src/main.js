import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify';
import VueI18n from 'vue-i18n'

Vue.config.productionTip = false

Vue.use(VueI18n)

const messages = {}

 const i18n = new VueI18n({
   locale: 'en',
   messages, 
 })

new Vue({
  vuetify,
  i18n,
  VueI18n,
  // data: {
  //   url: '/term'
  // },
  render: h => h(App)
}).$mount('#app')
