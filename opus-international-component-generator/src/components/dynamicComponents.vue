<template>
  <div>
    <v-card>
      <v-tabs v-model="tab" background-color="red lighten-2" dark>
        <!-- Render Tabs, supply a unique `key` to each tab -->
        <v-tab v-for="i in tabs" :key="'dyn-tab-' + i">
          Item {{ i }}
          <v-button small color="error" class="mx-2" @click="closeTab(i)">
            Close tab
          </v-button>
        </v-tab>

        <!-- New Tab Button (Using tabs-end slot) -->
        <template v-slot:tabs-end>
          <v-nav-item @click.prevent="newTab" href="#"><b>+</b></v-nav-item>
        </template>

        <!-- Render this if no tabs -->
        <template v-slot:empty>
          <div class="text-center text-muted">
            There are no open tabs<br />
            Open a new tab using the <b>+</b> button above.
          </div>
        </template>
      </v-tabs>
    </v-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tabs: [],
      tabCounter: 0
    };
  },
  methods: {
    closeTab(x) {
      for (let i = 0; i < this.tabs.length; i++) {
        if (this.tabs[i] === x) {
          this.tabs.splice(i, 1);
        }
      }
    },
    newTab() {
      this.tabs.push(this.tabCounter++);
    }
  }
};
//https://bootstrap-vue.js.org/docs/components/tabs/
</script>
