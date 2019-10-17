<template>
  <div :id="id" class="field combobox" v-if="isShow">
    <!-- Label are -->
    <label>{{ $t('combobox.text')}}</label>
    <!-- Tooltip area -->
    <div class="ui pointing below label">{{$t('combobox.tooltip')}}</div>
    <!-- Input area -->
    <input
      type="text"
      name="gender"
      :placeholder="$t('combobox.placeholder')"
      :value="value"
      :readonly="isReadonly"
      :required="isMandatory"
    />
    <!-- Icon area -->
    <i class="dropdown icon"></i>
    <!-- Drop down list items -->
    <div class="menu" tabindex="-1">
      <div class="item" data-value="male">Male</div>
      <div class="item" data-value="female">Female</div>
    </div>
    <!-- Hint area -->
    <div class="ui error message">
      <i class="check circle icon"></i>
      <span>{{$t('combobox.hint')}}</span>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
  methods: {},
  props: ["id", "value", "isReadonly", "isMandatory", "isShow"]
};
</script>


<style>
body {
  margin: 0;
  font-family: "Nunito SemiBold";
}
</style>
