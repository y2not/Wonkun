<template>
  <div id="show-labels">
    <h1>All Labels</h1>
    <input type="text" v-model="search" placeholder="search labels" />
    <v-divider></v-divider>
    <v-container class="lighten-5">
      <v-row no-gutters style="height: 1000px;">
        <template v-for="result in filteredItems">
          <v-col :key="result.id">
            <v-card class="ma-2 pa-2" width="350px" outlined tile>
              <v-card-text>
                <p class="display-1 text--primary">{{ result.fallbackName }}</p>

                <p class="text--primary">Code: {{ result.code }}</p>
                <p class="text--primary">Abbreviation: {{ result.abbreviation }}</p>
                <p class="text--primary">Description: {{ result.description }}</p>
                <ul>
                  <li :key="language.id" v-for="language in result.languageName">{{language.code}}</li>
                </ul>
                <router-link v-bind:to="'/label/add/' + result.id">
                  <v-btn text color="deep-purple accent-4">See More</v-btn>
                </router-link>
              </v-card-text>
            </v-card>
          </v-col>
        </template>
      </v-row>
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
      search: ""
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
