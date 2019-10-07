<template>
  <div class="header">
    <div class="header_inner">
      <div class="header_top">
        <router-link to="/" exact>헤더 내용 삽입</router-link>
      </div>
      <div class="header_bottom">
        <ul class="gnb">
          <li class="hbgnb_1">
            <router-link to="/signin" v-show="signedIn">
              <p class="right_bg_white">SIGN IN</p>
            </router-link>
            <router-link to="/signin" v-show="signOut">
              <p class="right_bg_white">SIGN OUT</p>
            </router-link>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      signedIn: true,
      signOut: false
    };
  },

  created() {
    if (this.$store.state.userToken.user_id == "") {
      this.signedIn = true;
      this.signOut = false;
    } else {
      this.signedIn = false;
      this.signOut = true;
    }
  }
};
</script>