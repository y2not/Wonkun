<template>
  <div id="add-message">
    <h2>Add a New Message</h2>
    <form v-if="!submitted">
      <label>Code</label>
      <input type="text" v-model.lazy.trim="message.code" required />
      <label>Fallback Memssage</label>
      <input type="text" v-model.lazy.trim="message.fallbackName" required />
      <label>Description</label>
      <textarea v-model.lazy.trim="message.description"></textarea>
      <label>Reference Code</label>
      <input type="text" v-model.lazy.trim="message.referenceCode" required />
      <div v-bind:key="language.id" v-for="language in languages">
        <p>{{language.label}} Label</p>
        <label>Language Message</label>
        <input type="text" v-model.lazy.trim="language.message" />
      </div>
      <p>Reference Domains</p>
      <div id="checkboxes" :key="domain.id" v-for="domain in domains">
        <label>{{domain}}</label>
        <input type="checkbox" :value="domain" v-model="message.referenceDomains" />
      </div>
      <hr />
      <button v-on:click.prevent="post">Add Label</button>
    </form>
    <div id="Preview" v-show="submitted">
      <label>Code:</label>
      <span>{{message.code}}</span>
      <label>Fallback Message:</label>
      <span>{{message.fallbackName}}</span>
      <label>Description:</label>
      <span>{{message.description}}</span>
      <label>Reference Code:</label>
      <span>{{message.referenceCode}}</span>
    </div>
    <div v-if="submitted">
      <v-alert type="success" border="left" color="pink darken-1" dark>Thanks for adding new message</v-alert>
    </div>
  </div>
</template>

<script>
// Imports

export default {
  data() {
    return {
      message: {
        code: "",
        fallbackName: "",
        description: "",
        referenceCode: "",
        languageName: [],
        referenceDomains: []
      },
      languages: [
        {
          label: "English",
          code: "en",
          message: ""
        },
        {
          label: "Korean",
          code: "ko",
          message: ""
        },
        {
          label: "Chinese",
          code: "cn",
          message: ""
        },
        {
          label: "Japanese",
          code: "jp",
          message: ""
        }
      ],
      domains: ["Order", "Booking", "Trade", "Partner", "Market"],
      submitted: false
    };
  },
  methods: {
    post: function() {
      this.message.languageName = this.languages;
      this.$http
        .post(
          "https://emaily-prod-245213.firebaseio.com/messages.json",
          this.message
        )
        .then(function(data) {
          alert(data);
          this.submitted = true;
        });
    }
  }
};
</script>

<style scoped>
#add-message * {
  box-sizing: border-box;
}
#add-message {
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
