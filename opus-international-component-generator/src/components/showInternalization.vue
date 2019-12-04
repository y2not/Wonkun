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
    <ul id="example-1">
      <li v-bind:key="index" v-for="(lang, index) in langs">{{ lang.langList }}</li>
    </ul>
    <br />
    <br />
  </div>
</template>



<script>
export default {
  name: "locale-changer",
  data() {
    return { langs: [], items: [] };
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
          resultsArray.push(data[key]);
        }
        this.items = resultsArray.map(currentValue => {
          for (let idx in currentValue.languageName) {
            let findLang = this.langs.findIndex(
              val => val.lang === currentValue.languageName[idx].code
            );
            // let fullHint = {};
            // fullHint[currentValue.code + ".hint"] =
            //   currentValue.languageName[idx].hint;
            // let fullName = {};
            // fullName[currentValue.code + ".name"] =
            //   currentValue.languageName[idx].name;
            // let fullPlaceholder = {};
            // fullPlaceholder[currentValue.code + ".placeHolder"] =
            //   currentValue.languageName[idx].placeHolder;
            // let fullTooltip = {};
            // fullTooltip[currentValue.code + ".tooltip"] =
            //   currentValue.languageName[idx].tooltip;
            let fullHint =
              '"' +
              currentValue.code +
              '.hint": "' +
              currentValue.languageName[idx].hint +
              '"';
            let fullName =
              '"' +
              currentValue.code +
              '.name": "' +
              currentValue.languageName[idx].name +
              '"';
            let fullPlaceholder =
              '"' +
              currentValue.code +
              '.placeHolder": "' +
              currentValue.languageName[idx].placeHolder +
              '"';
            let fullTooltip =
              '"' +
              currentValue.code +
              '.tooltip": "' +
              currentValue.languageName[idx].tooltip +
              '"';
            let fullList = [];
            if (currentValue.languageName[idx].hint) fullList.push(fullHint);
            if (currentValue.languageName[idx].name) fullList.push(fullName);
            if (currentValue.languageName[idx].placeHolder)
              fullList.push(fullPlaceholder);
            if (currentValue.languageName[idx].tooltip)
              fullList.push(fullTooltip);
            fullList = fullList.join(",");
            if (findLang === -1) {
              this.langs.push({
                lang: currentValue.languageName[idx].code,
                langList: fullList
              });
            } else {
              this.langs[findLang].langList =
                this.langs[findLang].langList +
                "," +
                // String.fromCharCode(13) +
                // String.fromCharCode(10) +
                fullList;
            }
          }
          return {
            text: currentValue.fallbackName,
            value: currentValue.languageName
          };
        });
      });
  }
};
</script>

