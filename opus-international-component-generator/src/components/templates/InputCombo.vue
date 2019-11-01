<template>
  <pre>
  <template>
    <div v-bind:id="id" class="field combobox" v-if="isShow">
      <!-- Label are -->
      <label>{{ $t('basic.text') }}</label>
      <!-- Tooltip area -->
      <div class="ui pointing below label">{{ $t('basic.tooltip')}}</div>
      <!-- Input area -->
      <input
        type="text"
        name="gender"
        :placeholder="$t('basic.placeholder')"
        :value="value"
        :readonly="isReadonly"
        :required="isMandatory"
      />
      <!-- Icon area -->
      <i class="dropdown icon"></i>
      <!-- Hint area -->
      <div class="ui error message">
        <i class="check circle icon"></i>
        <span>{{ $t('basic.hint') }}</span>
      </div>
    </div>
  </template>
</pre>
</template>

<script>
export default {
  data() {
    return {};
  },
  methods: {}
};
</script>

<style scoped>
body {
  margin: 0;
  font-family: "Nunito SemiBold";
}
</style>
