<template>
  <div id="body">
    <section id="section01">
      Insert Section01
      <h2>Input Heading Title</h2>
      <p>Divide Paragraph Body Content</p>
      <dl>Input term and glossary</dl>
    </section>
    <section id="section02">
      Insert Section02
      <article id="article01">Insert Article01 to divide with section attribute</article>
      <article id="article02">Insert Article02 to divide with section attribute</article>
    </section>
    <input-basic
      :id="basic.comid"
      :value="basic.value"
      :is-readonly="basic.readonly"
      :is-mandatory="basic.mandatory"
      :is-show="basic.show"
    ></input-basic>
    <input-combobox
      :id="combobox.comid"
      :value="combobox.value"
      :is-readonly="combobox.readonly"
      :is-mandatory="combobox.mandatory"
      :is-show="combobox.show"
    ></input-combobox>
    <div id="alert-message" style="color:red">This is alert message</div>
  </div>
</template>

<script>
import inputBasic from "./items/Input_Basic.vue";
import inputCombobox from "./items/Input_Combobox.vue";

export default {
  components: {
    inputBasic,
    inputCombobox
  },
  data() {
    return {
      basic: {
        comid: "111",
        value: "ABCDE",
        readonly: false,
        mandatory: true,
        show: true
      },
      combobox: {
        comid: "222",
        value: "QWERT",
        readonly: true,
        mandatory: false,
        show: true
      }
    };
  }
};
</script>