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
  methods: {}
};
</script>
