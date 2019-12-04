<template>
  <div id="manage-label">
    <h2>{{ title }}</h2>
    <v-form ref="form" v-model="valid" lazy-validation v-if="!submitted">
      <h3>Basic</h3>

      <v-text-field
        v-model.lazy.trim="label.code"
        v-mask="'XXXXXXXXXXXXXXX'"
        label="Code"
        counter
        maxlength="15"
        :rules="[v => !!v || 'Code is required']"
        :readonly="!edit"
        required
      >{{ label.code | toUppercase }}</v-text-field>

      <v-text-field
        v-model.lazy.trim="label.fallbackName"
        label="Fallback Name"
        counter
        maxlength="80"
        :rules="[v => !!v || 'Fallback name is required']"
        :readonly="!edit"
        required
      ></v-text-field>

      <v-text-field
        v-model.lazy.trim="label.abbreviation"
        label="Abbreviation"
        counter
        maxlength="40"
        :readonly="!edit"
      ></v-text-field>
      <v-textarea
        counter
        maxlength="3000"
        :clearable="edit"
        clear-icon="mdi-close"
        label="Description"
        :readonly="!edit"
        v-model.lazy.trim="label.description"
      ></v-textarea>

      <v-combobox
        v-model.lazy.trim="label.type"
        :items="input_type"
        label="Input Type"
        :rules="[v => !!v || 'Type is required']"
        :readonly="!edit"
        required
      ></v-combobox>
      <v-autocomplete
        v-model.lazy.trim="label.size"
        :items="sizes"
        label="Size"
        :rules="[v => !!v || 'Size is required']"
        :readonly="!edit"
        required
      ></v-autocomplete>
      <v-autocomplete
        v-model.lazy.trim="label.classes"
        :items="classes"
        :readonly="!edit"
        chips
        small-chips
        label="Class"
        multiple
      ></v-autocomplete>

      <v-autocomplete
        v-model.lazy.trim="label.mask"
        :items="masks"
        :readonly="!edit"
        label="Masking"
        :clearable="edit"
      ></v-autocomplete>

      <v-autocomplete
        v-model="label.referenceDomains"
        :items="domains"
        :readonly="!edit"
        chips
        small-chips
        label="Reference Domains"
        multiple
      ></v-autocomplete>

      <v-text-field
        v-model.lazy.trim="label.maxLength"
        :readonly="!edit"
        v-mask="'#####'"
        label="Max Length"
      ></v-text-field>

      <v-checkbox v-model="label.showTooltip" :label="'Show Tooltip?'" :readonly="!edit"></v-checkbox>

      <div v-bind:key="language.id" v-for="language in languages">
        <p class="display-1">{{ language.label }} Label</p>
        <v-col>
          <v-text-field
            v-model.lazy.trim="language.name"
            :readonly="!edit"
            label="Language Name"
            outlined
            dense
            :clearable="edit"
          />

          <v-text-field
            v-model.lazy.trim="language.tooltip"
            :readonly="!edit"
            label="Language Tooltip"
            outlined
            dense
            :clearable="edit"
          />

          <v-text-field
            v-model.lazy.trim="language.hint"
            :readonly="!edit"
            label="Language Hint"
            outlined
            dense
            :clearable="edit"
          />

          <v-text-field
            v-model.lazy.trim="language.placeHolder"
            :readonly="!edit"
            label="Language Placeholder"
            outlined
            dense
            :clearable="edit"
          />
        </v-col>
      </div>

      <hr />

      <v-btn
        class="mr-4"
        color="success"
        v-on:click.prevent="post"
        :disabled="!valid"
      >{{ buttonFace }}</v-btn>
    </v-form>
    <div id="Preview" v-show="submitted">
      <span>
        <label>Code: {{ label.code }}</label>
      </span>
      <span>
        <label>Fallback Name: {{ label.fallbackName }}</label>
      </span>
      <span>
        <label>Abbreviation: {{ label.abbreviation }}</label>
      </span>
      <span>
        <label>Description: {{ label.description }}</label>
      </span>
    </div>
    <div v-if="submitted">
      <v-alert type="success" border="left" color="pink darken-1" dark>Thanks for updating the label</v-alert>
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
      domains: [
        "Order",
        "Forwarding",
        "Warehousing",
        "Transportation",
        "Accounting"
      ],
      classes: ["readonly", "warning", "mandatory", "uppercase"],
      sizes: ["long", "medium", "short"],
      masks: [
        "time",
        "integer",
        "decimal",
        "currency",
        "only-number",
        "alphabet-number",
        "password",
        "upper-case",
        "count"
      ],
      input_type: [
        "textbox",
        "combobox",
        "textarea",
        "checkbox",
        "button",
        "divider"
      ],
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
      buttonFace: "Edit Label",
      valid: true
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
        this.languages = this.label.languageName;
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
        if (this.$refs.form.validate()) {
          this.$http
            .put(
              "https://emaily-prod-245213.firebaseio.com/labels/" +
                this.id +
                ".json",
              this.label
            )
            .then(function(data) {
              return data.json();
            })
            .then(function(data) {
              // alert(data);
              this.label = data;
              this.languages = this.label.languageName;
              this.submitted = true;
            });
          this.title = "View Component";
          this.buttonFace = "Edit Label";
        }
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
