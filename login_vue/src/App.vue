<template>
  <v-app>
    <v-app-bar app>
      <v-toolbar-title class="headline text-uppercase">
        <span>OPUS</span>
        <span class="font-weight-light">365</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn
        text
        href="https://github.com/vuetifyjs/vuetify/releases/latest"
        target="_blank"
      >
        <span class="mr-2">Latest Release</span>
      </v-btn>
    </v-app-bar>

    <v-content>
      <SignIn/>
    </v-content>
  </v-app>
</template>

<script>
import SignIn from './components/SignIn';

export default {
  name: 'App',
  components: {
    SignIn,
  },
  data: () => ({
    //
  }),
};
</script>
