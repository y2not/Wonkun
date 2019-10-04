<template>
  <v-app>
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

<style>

</style>
