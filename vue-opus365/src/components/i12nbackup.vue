<template>

    


   <v-card
    class="pa-md-4 mx-lg-auto"
    max-width="344"
  >
    <v-card-text class="font-weight-black">
      <!-- basic -->
      <p>{{ $t('key1') }}</p>
      <!-- nested -->
      <p>{{ $t('nested.message1') }}</p>
      <!-- array -->
      <p>{{ $t('errors[0]') }}</p>
      <!-- object in array -->
      <p>{{ $t('errors[1].internal1') }}</p>
      <!-- array in array -->
      <p>{{ $t('errors[2][0]') }}</p>
      <h1>Formatting</h1>
      <p>{{ $t('hello', { msg:'1st Wonkun'}) }}</p>
      <p>{{ $t('message.hello', {'0': '2nd Wonkun'}) }}</p>
      <p v-html="$t('message2.hello', {'0':'3rd Wonkun'})"></p>
      <p>{{ $t('message3.hello', { msg: 'Oh ho hello' }) }}</p>
      <v-divider></v-divider>
      <h1> Pluralization </h1>
      <p>{{ $tc('car', 1) }}</p>
      <p>{{ $tc('car', 2) }}</p>
      <p>{{ $tc('apple', 0) }}</p>
      <p>{{ $tc('apple', 1) }}</p>
      <p>{{ $tc('apple', 10, { count: 10 }) }}</p>
      <v-divider></v-divider>
      <p>{{ $d(new Date(), 'short') }}</p>
      <p>{{ $d(new Date(), 'long') }}</p>
      <v-divider></v-divider>
      <p>{{ $n(100, 'currency') }}</p>
      <p>{{ $n(100, 'currency', 'ja-JP') }}</p>
      <label for="locale">locale</label>
      <select v-model="locale">
        <option>en</option>
        <option>ja</option>
      </select>
      <p>message: {{ $t('hello') }}</p>
    </v-card-text>
  </v-card>
</template>

<script>

export default {
  name: 'HelloI18n'
}
</script>

<i18n>
{
  
}
</i18n>

<script>
export default {
  name: 'app',
  data () {
    this.$i18n.locale = 'en';
    return { locale: 'en' } 
  },
  watch: {
    locale (val) {
      this.$i18n.locale = val
    }
  }
}
</script>
