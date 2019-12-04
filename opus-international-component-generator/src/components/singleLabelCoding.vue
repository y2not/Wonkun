<template>
  <div>
    <b>Label</b>
    <input-normal
      :id="basic.comid"
      :value="basic.value"
      :is-readonly="basic.readonly"
      :is-mandatory="basic.mandatory"
      :is-show="basic.show"
    ></input-normal>
    <button
      type="button"
      v-clipboard:copy="basic"
      v-clipboard:success="onCopy"
      v-clipboard:error="onError"
    >Copy!</button>
  </div>
</template>

<script>
import inputNormal from "./templates/InputNormal.vue";
export default {
  components: {
    inputNormal
  },
  data() {
    return {
      basic: {
        comid: "111",
        value: "ABCDE",
        readonly: false,
        mandatory: true,
        show: true
      }
    };
  },
  methods: {
    onCopy: function() {
      this.$copyText(this.$el.children[1].children[2].textContent).then(
        function(e) {
          alert("Copied");
          console.log(this.$el);
        },
        function(e) {
          alert("Can not copy");
          console.log(e);
        }
      );
    },
    onError: function() {
      alert("Failed to copy texts");
    }
  }
};
</script>