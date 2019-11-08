<template>
  <div id="manage-label">
    <h2>{{ title }}</h2>
    <v-form v-if="!submitted">
      <h3>Basic</h3>

      <v-text-field
        v-model.lazy.trim="label.code"
        label="Code"
        :rules="rule_required"
        required
        :readonly="!edit"
      ></v-text-field>

      <v-text-field
        v-model.lazy.trim="label.fallbackName"
        label="Fallback Name"
        :rules="rule_required"
        required
        :readonly="!edit"
      ></v-text-field>

      <v-text-field v-model.lazy.trim="label.abbreviation" label="Abbreviation" :readonly="!edit"></v-text-field>
      <v-textarea
        counter
        clearable
        clear-icon="mdi-close"
        label="Description"
        v-model.lazy.trim="label.description"
        :readonly="!edit"
      ></v-textarea>

      <v-combobox
        v-model.lazy.trim="label.type"
        :items="input_type"
        label="Input Type"
        :readonly="!edit"
      ></v-combobox>
      <v-autocomplete
        v-model.lazy.trim="label.class"
        :items="classes"
        outlined
        dense
        chips
        small-chips
        label="Class"
        multiple
        :readonly="!edit"
      ></v-autocomplete>

      <v-text-field v-model.lazy.trim="label.maxLength" label="Max Length" :readonly="!edit"></v-text-field>
      <div v-bind:key="language.id" v-for="language in label.languageName">
        <p class="display-1">{{ language.label }} Label</p>
        <v-col>
          <v-text-field
            v-model.lazy.trim="language.name"
            label="Language Name"
            outlined
            dense
            clearable
            :readonly="!edit"
          />

          <v-text-field
            v-model.lazy.trim="language.tooltip"
            label="Language Tooltip"
            outlined
            dense
            clearable
            :readonly="!edit"
          />

          <v-text-field
            v-model.lazy.trim="language.hint"
            label="Language Hint"
            outlined
            dense
            clearable
            :readonly="!edit"
          />

          <v-text-field
            v-model.lazy.trim="language.placeHolder"
            label="Language Placeholder"
            outlined
            dense
            clearable
            :readonly="!edit"
          />
        </v-col>
      </div>

      <v-autocomplete
        v-model="label.referenceDomains"
        :items="domains"
        outlined
        dense
        chips
        small-chips
        label="Reference Domains"
        multiple
        :readonly="!edit"
      ></v-autocomplete>

      <v-checkbox v-model="label.showTooltip" :label="'Show Tooltip?'" :readonly="!edit"></v-checkbox>

      <hr />
      <button v-on:click.prevent="post">{{ buttonFace }}</button>
    </v-form>
    <div id="Preview" v-show="submitted">
      <label>Code:</label>
      <span>{{ label.code }}</span>
      <label>Fallback Name:</label>
      <span>{{ label.fallbackName }}</span>
      <label>Abbreviation:</label>
      <span>{{ label.abbreviation }}</span>
      <label>Description:</label>
      <span>{{ label.description }}</span>
    </div>
    <div v-if="submitted">
      <v-alert type="success" border="left" color="pink darken-1" dark>Thanks for adding new label</v-alert>
    </div>
  </div>
</template>

<script>
// Imports
export default {
  data() {
    return {
      label: {},
      edit: false,
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
      classes: [
        "number",
        "email",
        "zipcode",
        "phone",
        "date",
        "uppercase",
        "currency",
        "readonly"
      ],
      input_type: ["textbox", "combobox", "textarea", "checkbox"],
      submitted: false,
      rule_required: [value => !!value || "Required."],
      rule_email: [
        value => !!value || "Required.",
        value => (value || "").length <= 20 || "Max 20 characters",
        value => {
          const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
          return pattern.test(value) || "Invalid e-mail.";
        }
      ],
      title: "View Component",
      id: this.$route.params.id,
      buttonFace: "Edit Label"
    };
  },
  created() {
    this.$http
      .get(
        "https://emaily-prod-245213.firebaseio.com/labels/" + this.id + ".json"
      )
      .then(function(data) {
        return data.json();
      })
      .then(function(data) {
        this.label = data;
        // this.submitted = true;
      });
  },
  methods: {
    post: function() {
      if (!this.edit) {
        this.title = "Edit Component";
        this.buttonFace = "Update Label";
      } else {
        this.label.languageName = this.languages;
        this.$http
          .put(
            "https://emaily-prod-245213.firebaseio.com/labels/" +
              this.id +
              ".json",
            this.label
          )
          .then(function(data) {
            // alert(data);
            this.label = data;
            this.submitted = true;
          });
        this.title = "View Component";
        this.buttonFace = "Edit Label";
      }
      this.edit = !this.edit;
    }
  }
};
</script>

<style scoped>
#manage-label * {
  box-sizing: border-box;
}
#manage-label {
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