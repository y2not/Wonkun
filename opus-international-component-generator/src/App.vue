<template>
  <v-app>
    <v-app-bar color="indigo darken-2" dark prominent app fixed>
      <app-header />
    </v-app-bar>

    <v-content>
      <router-view />
    </v-content>
    <app-bottom />
  </v-app>
</template>

<script>
import header from "./components/header.vue";
import bottom from "./components/bottom.vue";
export default {
  components: {
    "app-header": header,
    "app-bottom": bottom
  },
  data() {
    return {};
  },
  methods: {}
};
</script>

<style>
body {
  margin: 0;
  font-family: "Nunito SemiBold";
}
</style>
