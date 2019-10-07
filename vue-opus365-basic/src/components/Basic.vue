<template>
  <div>
    <!--    -->
    <app-header></app-header>
    <app-dashboard></app-dashboard>
    <app-footer></app-footer>
  </div>
</template>

<script>
import Dashboard from "./membership/Dashboard.vue";
import Footer from "./membership/Footer.vue";
import Header from "./membership/Header.vue";

export default {
  name: "Basic",
  components: {
    "app-dashboard": Dashboard,
    "app-footer": Footer,
    "app-header": Header
  }
};
</script>