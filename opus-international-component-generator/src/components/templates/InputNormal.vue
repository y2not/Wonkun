<template>
  <div :id="code" class="field textbox" v-if="inputType === 'textbox'">
    <pre
      id="source"
      class="language-markup"
      style="background-color:transparent;"
    >
      <code>
    &lt;!-- Textbox Label are --&gt;
      &lt;label&gt;
        {{ label }}
          &lt;/label&gt;
            &lt;!-- Tooltip area --&gt;
                &lt;div class=&quot;ui pointing
              below label&quot;&gt;{{ tooltip }}
            &lt;/div&gt;
          &lt;!-- Input area --&gt;
        &lt;input type=&quot;text&quot; name=&quot;gender&quot;
    :placeholder=&quot;{{ placeholder }}&quot; :value=&quot;value&quot;
    :readonly=&quot;isReadonly&quot; :required=&quot;isMandatory&quot; /&gt;
      &lt;!-- Icon area --&gt; &lt;i class=&quot;dropdown icon&quot;&gt;&lt;/i&gt;
        &lt;!-- Hint area --&gt; &lt;div class=&quot;ui error message&quot;&gt;
          &lt;i class=&quot;check circle icon&quot;&gt;
        &lt;/i&gt;
      &lt;span&gt;{{ hint }}
    &lt;/span&gt; &lt;/div&gt;
     </code>
   </pre>
  </div>

  <div :id="id" class="field combobox" v-else-if="inputType === 'combobox'">
    <pre
      id="source"
      class="language-markup"
      style="background-color:transparent;"
    >
      <code>
    &lt;!-- Combobox Label are --&gt;
      &lt;label&gt;
        {{ label }}
          &lt;/label&gt;
            &lt;!-- Tooltip area --&gt;
                &lt;div class=&quot;ui pointing
              below label&quot;&gt;{{ tooltip }}
            &lt;/div&gt;
          &lt;!-- Input area --&gt;
        &lt;input type=&quot;text&quot; name=&quot;gender&quot;
    :placeholder=&quot;{{ placeholder }}&quot; :value=&quot;value&quot;
    :readonly=&quot;isReadonly&quot; :required=&quot;isMandatory&quot; /&gt;
      &lt;!-- Icon area --&gt; &lt;i class=&quot;dropdown icon&quot;&gt;&lt;/i&gt;
        &lt;!-- Hint area --&gt; &lt;div class=&quot;ui error message&quot;&gt;
          &lt;i class=&quot;check circle icon&quot;&gt;
        &lt;/i&gt;
      &lt;span&gt;{{ hint }}
    &lt;/span&gt; &lt;/div&gt;
      </code>
    </pre>
  </div>
</template>

<script>
export default {
  data() {
    return {
      label: "{{$t('" + this.code + ".label')}}",
      tooltip: "{{$t('" + this.code + ".tooltip')}}",
      placeholder: "$t('" + this.code + ".placeholder')",
      hint: "{{$t('" + this.code + ".hint')}}",
      isShow: true
    };
  },
  methods: {},
  props: [
    "code",
    "abbreviation",
    "inputType",
    "maxLength",
    "showTooltip",
    "classes"
  ]
};
</script>

<style>
body {
  margin: 0;
  font-family: "Nunito SemiBold";
}
</style>
