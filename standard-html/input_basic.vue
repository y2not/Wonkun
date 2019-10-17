<template>
  <div class="field combobox">
    <!-- Label are -->
    <label>Label</label>
    <!-- Tooltip area -->
    <div class="ui pointing below label">Please enter a value</div>
    <!-- Input area -->
    <input type="text" name="gender" v-bind:placeholder="input-placeholder" />
    <!-- Icon area -->
    <i class="dropdown icon"></i>
    <!-- Hint area -->
    <div class="ui error message">
      <i class="check circle icon"></i>
      <span>You're all signed up for the newsletter.</span>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      inputPlaceholder: ""
    };
  },
  methods: {}
};
</script>

<style>
body {
  margin: 0;
  font-family: "Nunito SemiBold";
}
</style>
