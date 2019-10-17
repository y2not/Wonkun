<template>
  <div id="app">
    <header-component></header-component>
    <nav-component></nav-component>
    <body-component></body-component>
    <sidebar-component></sidebar-component>
    <footer-component></footer-component>
  </div>
</template>

<script>
import HeaderComponent from "./components/Header.vue";
import NavComponent from "./components/Nav.vue";
import BodyComponent from "./components/Body.vue";
import SidebarComponent from "./components/SideBar.vue";
import FooterComponent from "./components/Footer.vue";

export default {
  name: "app",
  components: {
    HeaderComponent,
    NavComponent,
    BodyComponent,
    SidebarComponent,
    FooterComponent
  }
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
