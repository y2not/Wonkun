<template>
  <div id="datetime">
    <div class="locale-changer">
      <select v-model="$i18n.locale">
        <option v-for="(lang, i) in langs" :key="`Lang${i}`" :value="lang">{{ lang }}</option>
      </select>
    </div>
    <h1>DateTime</h1>
    <div id="app">
      <p>{{ $d(new Date(), 'short') }}</p>
      <p>{{ $d(new Date(), 'long', 'de') }}</p>
    </div>
    <h1>Number</h1>
    <div id="app">
      <p>{{ $n(100000, 'currency') }}</p>
      <p>{{ $n(100000, 'currency', 'de') }}</p>
    </div>
    <p>{{ $t('welcome', {name: 'Wonkun'}) }}</p>
    <p>{{ $tc('text',2)}}</p>
    <p>{{ $tc('wolves', 1) }}</p>
    <p>{{ $tc('wolves', 5) }}</p>
  </div>
</template>



<script>
export default {
  name: "locale-changer",
  data() {
    return { langs: ["en", "de"] };
  }
};
</script>

