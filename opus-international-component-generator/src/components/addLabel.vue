<template>
  <div id="add-label">
    <h2>Add a New Component</h2>
    <v-form ref="form" v-model="valid" lazy-validation v-if="!submitted">
      <h3>Basic</h3>

      <v-text-field
        v-model.lazy.trim="label.code"
        v-mask="'XXXXXXXXXXXXXXX'"
        label="Code"
        counter
        maxlength="15"
        :rules="rule_code"
      ></v-text-field>

      <v-text-field
        v-model.lazy.trim="label.fallbackName"
        label="Fallback Name"
        counter
        maxlength="80"
        :rules="[v => !!v || 'Fallback name is required']"
      ></v-text-field>

      <v-text-field
        v-model.lazy.trim="label.abbreviation"
        label="Abbreviation"
        counter
        maxlength="40"
      ></v-text-field>

      <v-textarea
        counter
        maxlength="3000"
        clearable
        clear-icon="mdi-close"
        label="Description"
        v-model.lazy.trim="label.description"
      ></v-textarea>

      <v-combobox
        v-model.lazy.trim="label.type"
        :items="input_type"
        label="Input Type"
        :rules="[v => !!v || 'Type is required']"
        required
      ></v-combobox>

      <v-autocomplete
        v-model.lazy.trim="label.size"
        :items="sizes"
        label="Size"
        :rules="[v => !!v || 'Size is required']"
        required
      ></v-autocomplete>

      <v-autocomplete
        v-model.lazy.trim="label.classes"
        :items="classes"
        chips
        small-chips
        label="Class"
        multiple
      ></v-autocomplete>

      <v-autocomplete v-model.lazy.trim="label.mask" :items="masks" label="Masking" clearable></v-autocomplete>

      <v-autocomplete
        v-model="label.referenceDomains"
        :items="domains"
        chips
        small-chips
        label="Reference Domains"
        multiple
      ></v-autocomplete>

      <v-text-field v-model.lazy.trim="label.maxLength" v-mask="'#####'" label="Max Length"></v-text-field>

      <v-checkbox v-model="label.showTooltip" :label="'Show Tooltip?'"></v-checkbox>

      <div v-bind:key="language.id" v-for="language in languages">
        <p class="display-1">{{ language.label }} Label</p>
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

      <hr />

      <v-btn class="mr-4" color="success" v-on:click.prevent="post" :disabled="!valid">Add Label</v-btn>
      <v-btn class="mr-4" color="error" @click="reset">Reset Form</v-btn>
      <!--
      <v-btn class="mr-4" color="warning" @click="resetValidation">
        Reset Validation
      </v-btn>
      -->
      <br />
      <br />
    </v-form>
    <div id="Preview" v-show="submitted">
      <span>
        <label>Code:</label>
        {{ label.code }}
      </span>
      <span>
        <label>Fallback Name:</label>
        {{ label.fallbackName }}
      </span>
      <span>
        <label>Abbreviation:</label>
        {{ label.abbreviation }}
      </span>
      <span>
        <label>Description:</label>
        {{ label.description }}
      </span>
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
        size: "",
        mask: "",
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
      // rule_required: [value => !!value || "Required."],
      // rule_email: [
      //   value => !!value || "Required.",
      //   value => (value || "").length <= 20 || "Max 20 characters",
      //   value => {
      //     const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      //     return pattern.test(value) || "Invalid e-mail.";
      //   }
      // ],
      rule_code: [
        value => !!value || "Code is required.",
        value =>
          this.codeList.findIndex(item => {
            return item === value;
          }) || "Code is duplicated"
      ],
      errorMessage: "",
      showError: false,
      loading: true,
      codeList: [],
      valid: true
    };
  },
  methods: {
    post: function() {
      this.label.languageName = this.languages;
      if (this.$refs.form.validate()) {
        this.$http
          .post(
            "https://emaily-prod-245213.firebaseio.com/labels.json",
            this.label
          )
          .then(function() {
            // alert(data);
            this.submitted = true;
          });
      }
    },
    reset() {
      this.$refs.form.reset();
    }
  },
  filters: {
    capitalize: function(value) {
      if (!value) return "";
      value = value.toString();
      return value.charAt(0).toUpperCase() + value.slice(1);
    },
    toUppercase: function(value) {
      return value.toUpperCase();
    }
  },
  created() {
    this.$http
      .get("https://emaily-prod-245213.firebaseio.com/labels.json")
      .then(function(data) {
        return data.json();
      })
      .then(function(data) {
        for (let key in data) {
          this.codeList.push(data[key].code);
        }
      })
      .catch(err => {
        this.errorMessage = err.response.error;
        this.showError = true;
        this.loading = false;
      });
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
</style>
