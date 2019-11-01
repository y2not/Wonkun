export default {
  computed: {
    filteredItems: function() {
      return this.results.filter(result => {
        return result.fallbackName
          .toUpperCase()
          .match(this.search.toUpperCase());
      });
    }
  }
};
