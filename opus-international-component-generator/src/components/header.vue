<template>
  <div style="min-width: 800px;">
    <h2>OPUS Internationalization Component Generator</h2>
    <v-row justify="center">
      <router-link to="/" exact>
        <v-btn color="secondary" class="ma-2">Home</v-btn>
      </router-link>

      <v-menu offset-y>
        <template v-slot:activator="{ on }">
          <v-btn color="secondary" class="ma-2" v-on="on">Components</v-btn>
        </template>
        <v-list>
          <v-list-item>
            <v-list-item-title>
              <router-link to="/labels">Show</router-link>
            </v-list-item-title>
          </v-list-item>
          <v-list-item>
            <v-list-item-title>
              <router-link to="/label/add">New</router-link>
            </v-list-item-title>
          </v-list-item>
          <v-list-item>
            <v-list-item-title>
              <router-link to="/label/generate">Generate</router-link>
            </v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>

      <v-menu offset-y>
        <template v-slot:activator="{ on }">
          <v-btn color="secondary" class="ma-2" v-on="on">Messages</v-btn>
        </template>
        <v-list>
          <v-list-item>
            <v-list-item-title>
              <router-link to="/messages">Show</router-link>
            </v-list-item-title>
          </v-list-item>
          <v-list-item>
            <v-list-item-title>
              <router-link to="/message/add">New</router-link>
            </v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>

      <router-link to="/showInternalization" exact>
        <v-btn color="secondary" class="ma-2">Show predefine internalization</v-btn>
      </router-link>
    </v-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      list: ["Show", "Add"]
    };
  }
};
</script>

<style>
</style>
