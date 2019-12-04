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
      <v-expansion-panel v-for="(item, i) in selectedItems" :key="i" class="pa-2">
        <v-expansion-panel-header>
          {{ item.fallbackName }}
          <template v-slot:actions>
            <v-icon color="primary">$expand</v-icon>
          </template>
        </v-expansion-panel-header>
        <v-expansion-panel-content>
          <v-card-actions>
            <v-spacer></v-spacer>

            <v-btn text color="primary" v-on:click.prevent="sliceSelectedItem(i)">Delete</v-btn>
          </v-card-actions>
          <inputNormal
            :code="item.code"
            :abbreviation="item.abbreviation"
            :input-type="item.type"
            :max-length="item.maxLength"
            :show-tooltip="item.showTooltip"
            :classes="listup(item.classes)"
          ></inputNormal>
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
    <v-snackbar v-model="snackbar" :top="top" :color="color">
      {{ text }}
      <v-btn color="black" text @click="snackbar = false">Close</v-btn>
    </v-snackbar>
    <br />
    <br />
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
        let seletedId = this.select;
        this.$http
          .get(
            "https://emaily-prod-245213.firebaseio.com/labels/" +
              this.select +
              ".json"
          )
          .then(function(data) {
            return data.json();
          })
          .then(function(data) {
            data.id = seletedId;
            this.selectedItems.push(data);
          });

        this.items.splice(
          this.items.findIndex(v => v.value === this.select),
          1
        );
        this.select = null;
      } else {
        this.snackbar = true;
      }
    },
    listup: function(data) {
      let listupdata = "";
      for (let key in data) {
        listupdata += data[key].value + " ";
      }
      return listupdata;
    },
    sliceSelectedItem: function(i) {
      this.items.push({
        text: this.selectedItems[i].fallbackName,
        value: this.selectedItems[i].id
      });
      this.selectedItems.splice(i, 1);
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
