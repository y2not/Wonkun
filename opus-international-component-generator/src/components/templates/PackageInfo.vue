<template>
  <div class="Package">
    <h1 class="Package__title">{{ packageName }}</h1>
    <div class="Package__info">
      <div class="Package__info-item">
        <span>{{ totalDownloads.toLocaleString() }}</span>
        <span class="info-label">Total Downloads</span>
      </div>
      <div class="Package__info-item">
        <span>{{ period }}</span>
        <span class="info-label">Period</span>
      </div>
      <div class="Package__info-item">
        <span>
          <a :href="packageLink">{{ packageName }} on npm</a>
        </span>
        <span class="info-label">Link</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    packageName: {
      type: String
    },
    totalDownloads: {
      type: Number
    },
    period: {
      type: String
    }
  },
  computed: {
    packageLink() {
      return `https://www.npmjs.com/package/vue.js`;
    }
  }
};
</script>

<style lang="scss">
.Package {
  padding: rem(20);
  background-color: color(fjord);
  box-shadow: 0 2px 16px 0 rgba(0, 0, 0, 0.3);
  color: #fff;
  text-align: center;
  max-width: rem(400);
}
</style>