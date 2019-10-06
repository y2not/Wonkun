import addLabel from "./components/addLabel.vue";
import showLabels from "./components/showLabels.vue";
import singleLabel from "./components/singleLabel.vue";
import addMessage from "./components/addMessage.vue";
import showMessages from "./components/showMessages.vue";
import singleMessage from "./components/singleMessage.vue";
import showInternalization from "./components/showInternalization.vue";
import home from "./components/home.vue";

export default [
  { path: "/", component: home },
  { path: "/label/add", component: addLabel },
  { path: "/lable/add/:id", component: singleLabel },
  { path: "/labels", component: showLabels },
  { path: "/message/add", component: addMessage },
  { path: "/message/add/:id", component: singleMessage },
  { path: "/messages", component: showMessages },
  { path: "/showInternalization", component: showInternalization }
];
