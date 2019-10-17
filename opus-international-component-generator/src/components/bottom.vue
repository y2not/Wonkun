<template>
  <v-footer dark padless fixed>
    <v-col class="text-center" cols="12">
      <div>
        &copy; {{ new Date().getFullYear() }}
        &nbsp; Made by StormBoy
      </div>
    </v-col>
  </v-footer>
</template>

<script>
export default {
  data() {
    return {
      activeBtn: 1
    };
  }
};
</script>