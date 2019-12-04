<template>
  <div id="manage-message">
    <h2>{{ title }}</h2>

    <v-form ref="form" v-model="valid" lazy-validation v-if="!submitted">
      <v-text-field
        v-model.lazy.trim="message.code"
        v-mask="'XXXXXXXXXXXXXXX'"
        label="Code"
        counter
        maxlength="15"
        :rules="[v => !!v || 'Code is required']"
        :readonly="!edit"
        required
      ></v-text-field>

      <v-text-field
        v-model.lazy.trim="message.fallbackName"
        label="Fallback Message"
        counter
        maxlength="80"
        :rules="[v => !!v || 'Fallback name is required']"
        :readonly="!edit"
        required
      ></v-text-field>

      <v-textarea
        counter
        maxlength="3000"
        :clearable="edit"
        clear-icon="mdi-close"
        label="Description"
        v-model.lazy.trim="message.description"
        :readonly="!edit"
      ></v-textarea>

      <v-text-field
        v-model.lazy.trim="message.referenceCode"
        :readonly="!edit"
        v-mask="'XXXXXXXXXXXXXXX'"
        label="Reference Code"
        counter
        maxlength="15"
      ></v-text-field>

      <v-autocomplete
        v-model="message.referenceDomains"
        :items="domains"
        :readonly="!edit"
        chips
        small-chips
        label="Reference Domains"
        multiple
      ></v-autocomplete>

      <div v-bind:key="language.id" v-for="language in languages">
        <p class="display-1">{{ language.label }} Message</p>
        <v-col>
          <v-text-field
            v-model.lazy.trim="language.message"
            label="Language Message"
            outlined
            :clearable="edit"
            :readonly="!edit"
          />
        </v-col>
      </div>

      <hr />
      <v-btn
        class="mr-4"
        color="success"
        v-on:click.prevent="post"
        :disabled="!valid"
        >{{ buttonFace }}</v-btn
      >

      <!--
      <v-btn class="mr-4" color="warning" @click="resetValidation">
        Reset Validation
      </v-btn>
      -->

      <br />
      <br />
    </v-form>
    <div id="Preview" v-show="submitted">
      <span
        ><label>Code: {{ message.code }}</label></span
      >
      <span
        ><label>Fallback Message: {{ message.fallbackName }}</label></span
      >
      <span
        ><label>Description: {{ message.description }}</label></span
      >
      <span
        ><label>Reference Code: {{ message.referenceCode }}</label></span
      >
    </div>
    <div v-if="submitted">
      <v-alert type="success" border="left" color="pink darken-1" dark
        >Thanks for updating the message</v-alert
      >
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      valid: true,
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
      domains: [
        "Order",
        "Forwarding",
        "Warehousing",
        "Transportation",
        "Accounting"
      ],
      submitted: false,
      title: "View Component",
      id: this.$route.params.id,
      buttonFace: "Edit Message",
      message: {},
      edit: false
    };
  },
  created() {
    this.$http
      .get(
        "https://emaily-prod-245213.firebaseio.com/messages/" +
          this.id +
          ".json"
      )
      .then(function(data) {
        return data.json();
      })
      .then(function(data) {
        this.message = data;
        this.languages = this.message.languageName;
        // this.submitted = true;
      });
  },
  methods: {
    post: function() {
      if (!this.edit) {
        this.title = "Edit Component";
        this.buttonFace = "Update Message";
      } else {
        this.message.languageName = this.languages;
        if (this.$refs.form.validate()) {
          this.$http
            .put(
              "https://emaily-prod-245213.firebaseio.com/messages/" +
                this.id +
                ".json",
              this.message
            )
            .then(function(data) {
              return data.json();
            })
            .then(function(data) {
              // alert(data);
              this.message = data;
              this.languages = this.message.languageName;
              this.submitted = true;
            });
          this.title = "View Component";
          this.buttonFace = "Edit Message";
        }
      }
      this.edit = !this.edit;
    }
  }
};
</script>

<style scoped>
#manage-message * {
  box-sizing: border-box;
}
#manage-message {
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
