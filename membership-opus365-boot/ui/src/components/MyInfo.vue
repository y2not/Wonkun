<template>
  <v-container fluid class="pa-0">
    <v-row align="center">
      <v-col cols="12" sm="6">
        <div class="text-center" v-if="formData.isLogin">
          {{formData.username}} 로그인됨
        </div>
        <br/>
        <br/>
        <div class="text-center">
          <div class="my-2">
            <v-btn small color="primary" dark @click="logout">logout</v-btn>
          </div>
        </div>
      </v-col>
    </v-row>
    <v-row align="center">
      <v-col cols="12" sm="6">
        <div class="text-center">
          <div class="my-2">
            <v-btn small color="primary" dark @click="checkUserInfo">Check My Info</v-btn>
          </div>
        </div>
        <div class="text-center">
         {{formData.myInfo}}
        </div>
      </v-col>
    </v-row>
    <br/>
    <br/>
    <br/>

  </v-container>
</template>

<script>
import VueCookies from 'vue-cookies'
export default {
  name: 'HelloWorld1',
  created () {
    var me = this
    VueCookies.config('7d');
    if(VueCookies.get("jwtToken") == null){
      this.$router.push({path: '/Login'})
    }
    else{
      me.formData.isLogin = true
    }
    if(VueCookies.get("userId") != null)
      me.formData.username = VueCookies.get("userId");
  },
  destroyed() {
  },
  data () {
    return {
      evtSource: null,
      msg: 'Welcome to Your Vue.js App',
      stompClient: null,
      jwtToken: null,
      refreshToken: null,
      items: [],
      items2: [],
      formData: {
        username: '',
        password: '',
        isLogin: false,
        myInfo : '',
        registerInfo : ''
      }
    }
  },
  methods: {
    logout: function () {
      this.formData.isLogin = false
      localStorage.clear();
      VueCookies.set("jwtToken", "");
      VueCookies.set("refreshToken", "");
      VueCookies.set("userId", "");
      this.$router.push({path: '/login'})
    },
    checkUserInfo: function () {
      var me = this

      var url = `${window.API_HOST}/user/api/users/${me.formData.username}`
      me.$http.get(url, {
          headers: {
            'Authorization': `Bearer ${VueCookies.get("jwtToken")}`,
            'Content-Type': 'application/json'
          }
        }
      ).then(function (e) {
        me.formData.myInfo = e.data;
      }, function(err){
        const errorcode = err.response.status
        if(errorcode == 500 || errorcode == 404)
          alert("error")
        if(errorcode == 401)
          me.reloginWithRefreshToken()
      })
    },
    reloginWithRefreshToken: function() {
      var me = this
      var clientID = 'OPUS365-client'
      var clientSecret = 'OPUS365-secret'
      const clientCredentialBase64 = btoa(clientID + ':' + clientSecret)
      //OAuth 인증을 위한 Body 설정
      const body = `grant_type=refresh_token&refresh_token=${VueCookies.get("refreshToken")}`

      me.$http.post(`${window.API_HOST}/oauth/token`, body, {
          headers: {
            'Authorization': `Basic ${clientCredentialBase64}`,
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        }
      ).then(function(response){
        if( response && response.data && response.data.access_token){
          me.jwtToken = response.data.access_token
          me.refreshToken = response.data.refresh_token
          VueCookies.set("jwtToken", me.jwtToken)
          VueCookies.set("refreshToken", me.refreshToken)
          VueCookies.set("userId", response.data.user_id)
          me.formData.isLogin = true
          me.checkUserInfo()
        }
      },function(err){
          const errorcode = err.response.status
          if(errorcode == 500 || errorcode == 404)
            alert("error")
          if(errorcode == 401)
            me.$router.push({path: '/Login'})
      })
    }
  }
}
</script>
