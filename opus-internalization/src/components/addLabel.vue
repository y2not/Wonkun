<template>
  <div id="add-label">
    <h2>Add a New Label</h2>
    <form v-if="!submitted">
      <label>Code</label>
      <input type="text" v-model.lazy.trim="label.code" required />
      <label>Fallback Name</label>
      <input type="text" v-model.lazy.trim="label.fallbackName" required />
      <label>Abbreviation</label>
      <input type="text" v-model.lazy.trim="label.abbreviation" />
      <label>Description</label>
      <textarea v-model.lazy.trim="label.description"></textarea>
      <div v-bind:key="language.id" v-for="language in languages">
        <h3>{{language.label}} Label</h3>
        <label>Language Name</label>
        <input type="text" v-model.lazy.trim="language.name" />
        <p>Language Tooltip</p>
        <input type="text" v-model.lazy.trim="language.tooltip" />
        <p>Language Hint</p>
        <input type="text" v-model.lazy.trim="language.hint" />
        <p>Language Placeholder</p>
        <input type="text" v-model.lazy.trim="language.placeHolder" />
      </div>
      <p>Reference Domains</p>
      <div id="checkboxes" :key="domain.id" v-for="domain in domains">
        <label>{{domain}}</label>
        <input type="checkbox" :value="domain" v-model="label.referenceDomains" />
      </div>
      <label>Show Tooltip?:</label>
      <select v-model="label.showTooltip">
        <option value="true">Yes</option>
        <option value="false">No</option>
      </select>
      <hr />
      <button v-on:click.prevent="post">Add Message</button>
    </form>
    <div id="Preview" v-show="submitted">
      <label>Code:</label>
      <span>{{label.code}}</span>
      <label>Fallback Name:</label>
      <span>{{label.fallbackName}}</span>
      <label>Abbreviation:</label>
      <span>{{label.abbreviation}}</span>
      <label>Description:</label>
      <span>{{label.description}}</span>
    </div>
    <div v-if="submitted">
      <h3>Thanks for adding new label</h3>
    </div>
  </div>
</template>

<script>
// Imports

export default {
  data() {
    return {
      label: {
        code: "",
        fallbackName: "",
        abbreviation: "",
        description: "",
        languageName: [],
        referenceDomains: [],
        showTooltip: true
      },
      languages: [
        {
          label: "English",
          code: "en",
          name: "",
          tooltip: "",
          hint: "",
          placeHolder: ""
        },
        {
          label: "Korean",
          code: "ko",
          name: "",
          tooltip: "",
          hint: "",
          placeHolder: ""
        },
        {
          label: "Chinese",
          code: "cn",
          name: "",
          tooltip: "",
          hint: "",
          placeHolder: ""
        },
        {
          label: "Japanese",
          code: "jp",
          name: "",
          tooltip: "",
          hint: "",
          placeHolder: ""
        }
      ],
      domains: ["Order", "Booking", "Trade", "Partner", "Market"],
      submitted: false
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
          console.log(data);
          this.submitted = true;
        });
    }
  }
};
</script>

<style scoped>
#add-label * {
  box-sizing: border-box;
}
#add-label {
  margin: 20px auto;
  max-width: 600px;
  padding: 20px;
}
label {
  display: block;
  margin: 20px 0 10px;
}
input[type="text"],
textarea,
select {
  display: block;
  width: 100%;
  padding: 8px;
}
textarea {
  height: 200px;
}
#preview {
  padding: 10px 20px;
  border: 1px dotted #ccc;
  margin: 30px 0;
}
h3 {
  margin-top: 10px;
}
#checkboxes input {
  display: inline-block;
  margin-right: 10px;
}
#checkboxes label {
  display: inline-block;
  margin-top: 0;
}
hr {
  display: none;
}
button {
  display: block;
  margin: 20px 0;
  background: crimson;
  color: #fff;
  border: 0;
  padding: 14px;
  border-radius: 4px;
  font-size: 18px;
  cursor: pointer;
}
</style>
