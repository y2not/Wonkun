<template>
  <div id="show-labels">
    <h1>All Labels</h1>
    <input type="text" v-model="search" placeholder="search labels" />
    <div :key="result.id" v-for="result in filteredItems" class="single-label">
      <router-link v-bind:to="'/label/add/' + result.id">
        <h2>{{ result.fallbackName }}</h2>
      </router-link>
      <article>Code: {{ result.code }}</article>
      <article>Abbreviation: {{ result.abbreviation }}</article>
      <article>Description: {{ result.description }}</article>
      <ul>
        <li :key="language.id" v-for="language in result.languageName">{{language.code}}</li>
      </ul>
    </div>
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
        console.log(this.results);
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
.single-label {
  padding: 20px;
  margin: 20px 0;
  box-sizing: border-box;
  background: #eee;
  border: 1px dotted #aaa;
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
