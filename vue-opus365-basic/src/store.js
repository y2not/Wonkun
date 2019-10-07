// store.js
import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export const store = new Vuex.Store({
  state: {
    userToken: {
      user_id: '',
      user_name: ''
    }
  }
});
