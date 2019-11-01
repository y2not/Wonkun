import Vue from "vue";
import VueResource from "vue-resource";
import VueRouter from "vue-router";
import App from "./App.vue";
import Routes from "./routes";
import i18n from './i18n'
import vuetify from './plugins/vuetify';
// import VueI18n from "vue-i18n";

// Use packages
Vue.use(VueResource);
Vue.use(VueRouter);
// Vue.use(VueI18n);

const messages = {};

const dateTimeFormats = {
  "en-US": {
    short: {
      year: "numeric",
      month: "short",
      day: "numeric"
    },
    long: {
      year: "numeric",
      month: "short",
      day: "numeric",
      weekday: "short",
      hour: "numeric",
      minute: "numeric"
    }
  },
  "ja-JP": {
    short: {
      year: "numeric",
      month: "short",
      day: "numeric"
    },
    long: {
      year: "numeric",
      month: "short",
      day: "numeric",
      weekday: "short",
      hour: "numeric",
      minute: "numeric",
      hour12: true
    }
  }
};

const numberFormats = {
  "en-US": {
    currency: {
      style: "currency",
      currency: "USD"
    }
  },
  "ja-JP": {
    currency: {
      style: "currency",
      currency: "JPY",
      currencyDisplay: "symbol"
    }
  }
};

// Register routes
const router = new VueRouter({
  routes: Routes,
  mode: "history"
});

// const i18n = new VueI18n({
//   locale: "en-US",
//   fallbackLocale: "en-US",
//   messages,
//   dateTimeFormats,
//   numberFormats
// });

new Vue({
  el: "#app",

  // i18n,
  // VueI18n,
  render: h => h(App),

  i18n,
  vuetify,
  router: router
});
