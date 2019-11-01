<template>
  <div :id="id" class="field combobox" v-if="isShow">
    <pre id="source" class="language-markup" style="background-color:transparent;">
      <code>
    &lt;!-- Label are --&gt;
      &lt;label&gt;
        {{text}}
          &lt;/label&gt;
            &lt;!-- Tooltip area --&gt;
                &lt;div class=&quot;ui pointing
              below label&quot;&gt;{{ tooltip }}
            &lt;/div&gt;
          &lt;!-- Input area --&gt;
        &lt;input type=&quot;text&quot; name=&quot;gender&quot;
    :placeholder=&quot;{{placeholder}}&quot; :value=&quot;value&quot;
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
      text: "{{$t('basic.text')}}",
      tooltip: "{{$t('basic.tooltip')}}",
      placeholder: "$t('basic.tooltip')",
      hint: "{{$t('basic.hint')}}"
    };
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
