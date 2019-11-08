<template>
  <div id="show-labels">
    <h1>All Labels</h1>
    <input type="text" v-model="search" placeholder="search labels" />
    <v-divider></v-divider>
    <v-container class="lighten-5">
      <v-data-table
        :headers="headers"
        :items="results"
        :search="search"
        multi-sort
        class="elevation-1"
      >
        <template v-slot:item.showTooltip="{ item }">
          <v-icon :color="red" v-show="item.showTooltip">mdi-check-circle-outline</v-icon>
        </template>
        <template v-slot:item.edit="{ item }">
          <router-link v-bind:to="'/label/add/' + item.id">
            <v-icon clas="mr-2">mdi-playlist-edit</v-icon>
          </router-link>
        </template>
      </v-data-table>
    </v-container>
  </div>
</template>

<script>
// Imports
import searchMixin from "../mixins/searchMixin";
export default {
  data() {
    return {
      results: [],
      search: "",
      headers: [
        {
          text: "Code",
          align: "left",
          sortable: true,
          value: "code"
        },
        { text: "Fallback Name", value: "fallbackName" },
        { text: "Abbreviation", value: "abbreviation" },
        { text: "Tooltip", value: "showTooltip" },
        { text: "Actions", align: "center", sortable: false, value: "edit" }
      ],
      errorMessage: "",
      showError: false,
      loading: true
    };
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
        this.results = resultsArray;
        // console.log(this.results);
      })
      .catch(err => {
        this.errorMessage = err.response.error;
        this.showError = true;
        this.loading = false;
      });
  },
  mixins: [searchMixin]
};
</script>

<style scoped>
#show-labels {
  max-width: 800px;
  margin: 0px auto;
}
#show-labels a {
  color: #444;
  text-decoration: none;
}
input[type="text"] {
  padding: 8px;
  width: 100%;
  box-sizing: border-box;
}
</style>