<template>
  <div class="left_bg_wrap">
    <div class="l_bg_header">
      <img src="../assets/logo.png" style="margin: 54px 0px 0px 63px; float: left;" />
      <div class="login_tit" style="float: right; margin: 54px 63px 0px 0px;">
        {{ $t('newhere') }}
        <a @click="register">{{ $t('signup') }}</a>
      </div>
      <a @click="setLangEn">
        <img src="../assets/us.png" />
      </a>
      <a @click="setLangJp">
        <img src="../assets/jp.png" />
      </a>
    </div>
    <div class="left_bg">
      <div class="left_bg_box1">
        <p class="left_bg_maintit_white">{{ $t("signin") }}</p>
      </div>

      <div class="left_bg_box2">
        <p class="left_bg_maintit_black">
          {{ $t("welcome") }}
          <br />OPUS
        </p>
        <form>
          <div class="input_wrap">
            <p class="input_tit" v-show="useridTitle">{{ $t("email.title") }}</p>
            <input
              type="text"
              v-bind:placeholder="$t('email.title')"
              value
              class="input_email"
              name="input_email"
              @focus="useridTitle=true"
              @blur="checkUseridTitle"
              v-model="formData.username"
            />
          </div>

          <div class="input_wrap" style="margin:30px 0px 0px 0px;">
            <p class="input_tit" v-show="passwordTitle">
              {{ $t("password") }}
              <span>{{$t("6-digit-number")}}</span>
            </p>
            <input
              type="password"
              v-bind:placeholder="$t('password')"
              value
              class="input_psw"
              name="input_psw"
              autocomplete="on"
              @focus="passwordTitle=true"
              @blur="checkPasswordTitle"
              v-model="formData.password"
            />
          </div>

          <div class="error_tit" v-show="failMessage">
            <p class="etit">Sorry, we couldn’t find an account</p>
            <p class="etit2" onclick="location.href='#'">having a trouble to sign in ?</p>
          </div>

          <button class="lbnext_btn" @click.prevent="login">{{ $t('signin') }}</button>
          <p class="sns_tit">{{ $t('instantaccess') }}</p>
        </form>
        <div class="icon_box">
          <ul>
            <li>
              <a href="./welcome_back.html">
                <img src="../assets/ic-logo-wechat-normal.png" />
              </a>
            </li>
            <li>
              <a href="./welcome_back.html">
                <img src="../assets/ic-logo-facebook-normal.png" />
              </a>
            </li>
            <li>
              <a href="./welcome_back.html">
                <img src="../assets/ic-logo-qq-normal.png" />
              </a>
            </li>
            <li>
              <a href="./welcome_back.html">
                <img src="../assets/ic-logo-linkedin-normal.png" />
              </a>
            </li>
            <li>
              <a href="./welcome_back.html">
                <img src="../assets/ic-logo-weibo-normal.png" />
              </a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import VueCookies from "vue-cookies";
export default {
  name: "SignIn",
  data() {
    this.$i18n.locale = "en";
    return {
      useridTitle: false,
      passwordTitle: false,
      failMessage: false,
      locale: "en",
      fallbackLocale: "en",
      jwtToken: null,
      refreshToken: null,
      formData: {
        username: "",
        password: "",
        isLogin: false,
        myInfo: "",
        registerInfo: ""
      }
    };
  },
  created() {
    localStorage.clear();
    VueCookies.config("7d");
    if (VueCookies.get("jwtToken") != null)
      this.$router.push({ path: "/MyInfo" });
  },
  destroyed() {
    // TODO 소켓 desconnect
  },
  methods: {
    setLangJp: function() {
      this.$i18n.locale = "ja";
    },
    setLangEn: function() {
      this.$i18n.locale = "en";
    },
    checkUseridTitle: function() {
      if (this.formData.username == "") {
        this.useridTitle = false;
      } else {
        this.useridTitle = true;
      }
      this.failMessage = false;
    },
    checkPasswordTitle: function() {
      if (this.formData.password == "") {
        this.passwordTitle = false;
      } else {
        this.passwordTitle = true;
      }
      this.failMessage = false;
    },
    login: function() {
      var clientID = "OPUS365-client";
      var clientSecret = "OPUS365-secret";
      const clientCredentialBase64 = btoa(clientID + ":" + clientSecret);
      //OAuth 인증을 위한 Body 설정
      const body =
        `grant_type=password&username=${encodeURIComponent(
          this.formData.username
        )}&password=` + encodeURIComponent(this.formData.password);

      this.$http
        .post(`${window.API_HOST}/oauth/token`, body, {
          headers: {
            Authorization: `Basic ${clientCredentialBase64}`,
            "Content-Type": "application/x-www-form-urlencoded"
          }
        })
        .then(
          response => {
            if (response && response.data && response.data.access_token) {
              this.jwtToken = response.data.access_token;
              this.refreshToken = response.data.refresh_token;
              VueCookies.set("jwtToken", this.jwtToken);
              VueCookies.set("refreshToken", this.refreshToken);
              VueCookies.set("userId", response.data.user_id);
              this.formData.isLogin = true;

              //vuex value 정의
              this.$store.state.userToken.user_id = response.data.user_id;
              this.$store.state.userToken.user_name = "define_user_name_here";
              this.$router.replace(this.$route.query.redirect || "/");

              //this.$router.push({path: '/MyInfo', param: {param1: 'value1', param2: value2})
            }
          },
          response => {
            alert(response.data);
            this.failMessage = true;
          }
        );
    },
    register: function() {
      const body =
        '{"user_id" : "' +
        this.formData.registerId +
        '" ,"user_password" : "' +
        this.formData.registerPassword +
        '" ,"active_yn" : "Y"' +
        ', "delete_yn" : "N"' +
        ', "user_email" : "' +
        this.formData.registerId +
        '@clt.com"' +
        ', "approval_status_code" : 1}';

      this.$http
        .post(`http://localhost:8081/user/api/users`, body, {
          headers: {
            Authorization: `Bearer ${VueCookies.get("jwtToken")}`,
            "Content-Type": "application/json"
          }
        })
        .then(function(e) {
          this.formData.registerInfo = e.data;
        });
    }
  }
};
</script>

<style>
</style>
