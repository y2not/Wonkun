<template>
  <div id="show-messages">
    <h1>All Messages</h1>
    <input type="text" v-model="search" placeholder="search messages" />
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
          <v-icon :color="red" v-show="item.showTooltip"
            >mdi-check-circle-outline</v-icon
          >
        </template>
        <template v-slot:item.edit="{ item }">
          <router-link v-bind:to="'/message/add/' + item.id">
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
        { text: "Reference Code", value: "referenceCode" },
        { text: "Actions", align: "center", sortable: false, value: "edit" }
      ],
      errorMessage: "",
      showError: false,
      loading: true
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
