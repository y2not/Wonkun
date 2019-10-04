<template>


      <div class="left_bg_wrap">
        <div class="l_bg_header">
          <img src="../assets/logo.png" style="margin: 54px 0px 0px 63px; float: left;"/>
          <div class="login_tit" style="float: right; margin: 54px 63px 0px 0px;">
            {{ $t('newhere') }} <a href="#">{{ $t('signup') }}</a>
          </div>
          <a @click="setLangEn"><img src="../assets/us.png" ></a><a @click="setLangJp"><img src="../assets/jp.png" ></a>
        </div>
        <div class="left_bg">
          <div class="left_bg_box1">
            
              <p class="left_bg_maintit_white">{{ $t("signin") }}</p>
          </div>

          <div class="left_bg_box2">
            <p class="left_bg_maintit_black">{{ $t("welcome") }} <br>OPUS</p>
          
            <div class="input_wrap">
              <p class="input_tit" v-show="useridTitle">{{ $t("email") }} </p>
              <input type="text" v-bind:placeholder="$t('email')" value="" class="input_email" name="input_email" v-on:onclick="useridTitle=!useridTitle">
            </div>

            <div class="input_wrap" style="margin:30px 0px 0px 0px;">
              <p class="input_tit" v-show="passwordTitle">{{ $t("password") }} <span> 6-digit numbers</span></p>
              <input type="password" v-bind:placeholder="$t('password')" value="" class="input_psw" name="input_psw" v-on:onclick="passwordTitle=!passwordTitle">
            </div>

            <div class="error_tit">
              <p class="etit">Sorry, we couldn’t find an account</p>
              <p class="etit2" onclick="location.href='#'">having a trouble to sign in ?</p>
            </div>

            <button class="lbnext_btn">{{ $t('signin') }}</button>
            <p class="sns_tit">{{ $t('instantaccess') }}</p>
            <div class="icon_box">
              <ul>
                <li><a href="./welcome_back.html"><img src="../assets/ic-logo-wechat-normal.png"></a></li>
                <li><a href="./welcome_back.html"><img src="../assets/ic-logo-facebook-normal.png"></a></li>
                <li><a href="./welcome_back.html"><img src="../assets/ic-logo-qq-normal.png"></a></li>
                <li><a href="./welcome_back.html"><img src="../assets/ic-logo-linkedin-normal.png"></a></li>
                <li><a href="./welcome_back.html"><img src="../assets/ic-logo-weibo-normal.png"></a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>


</template>

<script>

export default {
  data () {
    this.$i18n.locale = "en";
    return {
      url: "/term",
      name: "HelloI18n",
      useridTitle: true,
      passwordTitle: true,
      locale: "en",
      fallbackLocale: "en",
    }
  },
  methods : {
    setLangJp : function() {
      this.$i18n.locale = "ja";
    },
    setLangEn : function() {
      this.$i18n.locale = "en";
    }
  },

}

</script>

<style>

</style>

<i18n>
{
  "en": {
    "tos": "Term of Service",
    "term": "I accept xxx {0}.",
    "signin": "sign in",
    "welcome": "Welcome to",
    "email": "e-mail",
    "password": "password",
    "newhere": "Are you new here? ",
    "signup": "Sign up",
    "instantaccess": "Wanna try? Instant access by"
  },
  "ja": {
    "tos": "利用規約",
    "term": "私は xxx の{0}に同意します。",
    "signin": "サインイン",
    "welcome": "へようこそ",
    "email": "Eメール",
    "password": "パスワード",
    "newhere": "ここは初めてですか？ ",
    "signup": "サインアップ",
    "instantaccess": "やってみたい？ による即時アクセス"
  }
}
</i18n>