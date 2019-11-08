<template>
  <div id="generate-labels">
    <h1>Select Label To Add</h1>
    <v-toolbar dark color="teal">
      <v-toolbar-title>Fallback Name</v-toolbar-title>
      <v-autocomplete
        v-model="select"
        :loading="loading"
        :items="items"
        :search-input.sync="search"
        class="mx-4"
        flat
        hide-no-data
        hide-details
        label="Input to select one!"
        solo-inverted
      ></v-autocomplete>
      <v-btn icon v-on:click.prevent="pushSelectedItem()">
        <v-icon>mdi-plus</v-icon>
      </v-btn>
    </v-toolbar>
    <v-divider></v-divider>

    <h2>Selected Labels</h2>
    <v-expansion-panels :focusable="focusable">
      <v-expansion-panel
        v-for="(item, i) in selectedItems"
        :key="i"
        class="pa-2"
      >
        <v-expansion-panel-header>{{ item }}</v-expansion-panel-header>
        <v-expansion-panel-content>
          <inputNormal
            :id="basic.comid"
            :value="basic.value"
            :is-readonly="basic.readonly"
            :is-mandatory="basic.mandatory"
            :is-show="basic.show"
          ></inputNormal>
        </v-expansion-panel-content>
      </v-expansion-panel>
      <span>
        <v-btn class="mx-2" fab dark small color="primary">
          <v-icon dark>mdi-minus</v-icon>
        </v-btn>
      </span>
    </v-expansion-panels>
    <v-snackbar v-model="snackbar" :top="top" :color="color">
      {{ text }}
      <v-btn color="black" text @click="snackbar = false">Close</v-btn>
    </v-snackbar>
  </div>
</template>

<script>
// Imports
// import PrismEditor from "vue-prism-editor";
// import "vue-prism-editor/dist/VuePrismEditor.css"; // import the styles
import inputNormal from "./templates/InputNormal.vue";
export default {
  data() {
    return {
      code: null,
      lineNumbers: true,
      focusable: true,
      search: null,
      loading: false,
      select: null,
      items: [],
      selectedItems: [],
      snackbar: false,
      text: "Nothing is selected!",
      top: true,
      color: "error",
      basic: {
        comid: "111",
        value: "ABCDE",
        readonly: false,
        mandatory: true,
        show: true
      }
    };
  },
  methods: {
    post: function() {
      this.label.languageName = this.languages;
      this.$http
        .post(
          "https://emaily-prod-245213.firebaseio.com/labels.json",
          this.label
        )
        .then(function(data) {
          alert(data);
          this.submitted = true;
        });
    },
    pushSelectedItem: function() {
      if (this.select !== null) {
        this.selectedItems.push(this.select);
        this.items.splice(
          this.items.findIndex(v => v.value === this.select),
          1
        );
        this.select = null;
      } else {
        this.snackbar = true;
      }
    }
  },
  components: {
    // PrismEditor
    inputNormal
  },
  created() {
    this.$http
      .get("https://emaily-prod-245213.firebaseio.com/labels.json")
      .then(function(data) {
        return data.json();
      })
      .then(function(data) {
        var resultsArray = [];
        for (let key in data) {
          data[key].id = key;
          resultsArray.push(data[key]);
        }
        this.items = resultsArray.map((currentValue, index, array) => {
          return { text: array[index].fallbackName, value: array[index].id };
        });
        // console.log(this.items);
      });
  }
};
</script>

<style scoped>
.my-editor {
  height: 300px;
  color: indigo;
  line-height: 24px;
  background-color: #f5f5f5;
}
#generate-labels {
  max-width: 800px;
  margin: 0px auto;
}
#generate-labels a {
  color: #444;
  text-decoration: none;
}
.label-input {
  padding: 8px;
  width: 91%;
  box-sizing: border-box;
}
</style>