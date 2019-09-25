<template>
  <p class="display-1 font-weight-bold mb-2">{{ $t('hello') }}</p>
</template>

<script>
export default {
  name: 'HelloI18n'
}
</script>

<i18n>
{
  "en": {
    "hello": "Hello i18n in SFC!"
  }
}
</i18n>
