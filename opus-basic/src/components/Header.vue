<template>
  <header>
    Insert Header Here
    <select v-model="$i18n.locale">
      <option v-for="(lang, i) in langs" :key="`Lang${i}`" :value="lang">{{ lang }}</option>
    </select>
  </header>
</template>

<script>
export default {
  name: "locale-changer",
  data() {
    return { langs: ["ja", "en", "ko"] };
  }
};
</script>