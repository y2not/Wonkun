<template>
  <div id="show-messages">
    <h1>All Messages</h1>
    <input type="text" v-model="search" placeholder="search messages" />
    <div :key="result.id" v-for="result in filteredItems" class="single-message">
      <router-link v-bind:to="'/message/add/' + result.id">
        <h2>{{ result.fallbackName }}</h2>
      </router-link>
      <article>Code: {{ result.code }}</article>
      <article>Description: {{ result.description }}</article>
      <article>Reference Code: {{result.referenceCode}}</article>
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
      .get("https://emaily-prod-245213.firebaseio.com/messages.json")
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
        //console.log(this.blogs);
      });
  },
  mixins: [searchMixin]
};
</script>

<style scoped>
#show-messages {
  max-width: 800px;
  margin: 0px auto;
}
.single-message {
  padding: 20px;
  margin: 20px 0;
  box-sizing: border-box;
  background: #eee;
  border: 1px dotted #aaa;
}

#show-messages a {
  color: #444;
  text-decoration: none;
}
input[type="text"] {
  padding: 8px;
  width: 100%;
  box-sizing: border-box;
}
</style>
