<template>
  <div id="add-label">
    <h2>Add a New Component</h2>
    <v-form v-if="!submitted">
      <h3>Basic</h3>

      <v-text-field v-model.lazy.trim="label.code" label="Code" :rules="rule_required" required></v-text-field>

      <v-text-field
        v-model.lazy.trim="label.fallbackName"
        label="Fallback Name"
        :rules="rule_required"
        required
      ></v-text-field>

      <v-text-field v-model.lazy.trim="label.abbreviation" label="Abbreviation"></v-text-field>
      <v-textarea
        counter
        clearable
        clear-icon="mdi-cancel"
        label="Description"
        v-model.lazy.trim="label.description"
      ></v-textarea>

      <v-combobox v-model.lazy.trim="label.type" :items="input_type" label="Input Type"></v-combobox>
      <v-autocomplete
        v-model.lazy.trim="label.class"
        :items="classes"
        outlined
        dense
        chips
        small-chips
        label="Class"
        multiple
      ></v-autocomplete>

      <v-text-field v-model.lazy.trim="label.maxLength" label="Max Length"></v-text-field>
      <div v-bind:key="language.id" v-for="language in languages">
        <p class="display-1">{{language.label}} Label</p>
        <v-col>
          <v-text-field
            v-model.lazy.trim="language.name"
            label="Language Name"
            outlined
            dense
            clearable
          />

          <v-text-field
            v-model.lazy.trim="language.tooltip"
            label="Language Tooltip"
            outlined
            dense
            clearable
          />

          <v-text-field
            v-model.lazy.trim="language.hint"
            label="Language Hint"
            outlined
            dense
            clearable
          />

          <v-text-field
            v-model.lazy.trim="language.placeHolder"
            label="Language Placeholder"
            outlined
            dense
            clearable
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
      ></v-autocomplete>

      <v-checkbox v-model="label.showTooltip" :label="'Show Tooltip?'"></v-checkbox>

      <hr />
      <button v-on:click.prevent="post">Add Message</button>
    </v-form>
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
      <v-alert type="success" border="left" color="pink darken-1" dark>Thanks for adding new label</v-alert>
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
        type: "",
        class: [],
        maxLength: null,
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
      ]
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
