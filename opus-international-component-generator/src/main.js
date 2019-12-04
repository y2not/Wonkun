import Vue from "vue";
import App from "./App.vue";
import vuetify from "./plugins/vuetify";
import i18n from "./i18n";
import VueResource from "vue-resource";
import VueRouter from "vue-router";
import Routes from "./routes/routes";
// import 'prismjs';
import "./plugins/prism";
import VuePrism from "vue-prism";
import VueTheMask from "vue-the-mask";
// import VueClipboard from 'vue-clipboard2';
// import VuePrismEditor from "vue-prism-editor";
// import "vue-prism-editor/dist/VuePrismEditor.css"; // import the styles

Vue.config.productionTip = false;
// VueClipboard.config.authSetContainer = true;

Vue.use(VueResource);
Vue.use(VueRouter);
Vue.use(VuePrism);
Vue.use(VueTheMask);
// Vue.use(VueClipboard);

// import 'prismjs/themes/prism-coy.css';
import "./plugins/prism.css";

// Vue.component("prism-editor", VuePrismEditor);

// Register routes
const router = new VueRouter({
  routes: Routes,
  mode: "history"
});

new Vue({
  vuetify,
  i18n,
  router,
  render: h => h(App)
}).$mount("#app");
