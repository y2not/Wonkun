<template>
  <div id="datetime">
    <div class="locale-changer">
      <select v-model="$i18n.locale">
        <option v-for="(lang, i) in langs" :key="`Lang${i}`" :value="lang">{{ lang }}</option>
      </select>
    </div>
    <h1>DateTime</h1>
    <div id="app">
      <p>{{ $d(new Date(), 'short', 'en') }}</p>
      <p>{{ $d(new Date(), 'long', 'de') }}</p>
    </div>
    <h1>Number</h1>
    <div id="app">
      <p>{{ $n(100000, 'currency', 'en') }}</p>
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
    return { langs: ["en", "de"], resultsArray: [], items: [], enjson: [] };
  },
  created() {
    this.$http
      .get("https://emaily-prod-245213.firebaseio.com/labels.json")
      .then(function(data) {
        return data.json();
      })
      .then(function(data) {
        var resultsArray = [];
        for (let key in data) {
          data[key].id = key;
          resultsArray.push(data[key]);
        }
        this.items = resultsArray.map((currentValue, index, array) => {
          return {
            code: array[index].code,
            languageName: array[index].languageName
          };
        });

        console.log(this.items);
      });
  }
};
</script>

