<template>
  <v-container fluid class="pa-0">
    <v-row align="center">
      <v-col cols="12" sm="6">
        <div class="text-center">
          <v-text-field label="로그인 이름" v-model="formData.username"></v-text-field>
          <v-text-field label="패스워드" v-model="formData.password"></v-text-field>
          <div class="my-2">
            <v-btn small color="primary" dark @click="login">login</v-btn>
          </div>
        </div>
        <div class="text-center" v-if="!formData.isLogin">
          로그인 해주세요
        </div>
      </v-col>
    </v-row>
    <br/><br/><br/><br/><br/>
    <v-row align="center">
      <v-col cols="12" sm="6">
        회원가입
        <div class="text-center">
          <v-text-field label="ID" v-model="formData.registerId"></v-text-field>
          <v-text-field label="패스워드" v-model="formData.registerPassword"></v-text-field>
          <div class="my-2">
            <v-btn small color="primary" dark @click="register">Register</v-btn>
          </div>
        </div>
        <div class="text-center">
         {{formData.registerInfo}}
        </div>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import VueCookies from 'vue-cookies'
export default {
  name: 'HelloWorld',
  created () {
    var me = this
    localStorage.clear();
    VueCookies.config('7d');
    if(VueCookies.get("jwtToken") != null)
      this.$router.push({path: '/MyInfo'})
  },
  destroyed() {
    // TODO 소켓 desconnect
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
    login: function () {
      var me = this
      var clientID = 'OPUS365-client'
      var clientSecret = 'OPUS365-secret'
      const clientCredentialBase64 = btoa(clientID + ':' + clientSecret)
      //OAuth 인증을 위한 Body 설정
      const body = `grant_type=password&username=${encodeURIComponent(me.formData.username)}&password=` + encodeURIComponent(me.formData.password)

      me.$http.post(`${window.API_HOST}/oauth/token`, body, {
          headers: {
            'Authorization': `Basic ${clientCredentialBase64}`,
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        }
      ).then(response => {

        if( response && response.data && response.data.access_token){
          me.jwtToken = response.data.access_token
          me.refreshToken = response.data.refresh_token
          VueCookies.set("jwtToken", me.jwtToken)
          VueCookies.set("refreshToken", me.refreshToken)
          VueCookies.set("userId", response.data.user_id)
          me.formData.isLogin = true
          this.$router.push({path: '/MyInfo'})
          //this.$router.push({path: '/MyInfo', param: {param1: 'value1', param2: value2})
        }
      })
    },
    register: function () {
          var me = this
          const body = '{\"user_id\" : \"' + me.formData.registerId
                        + '\" ,\"user_password\" : \"' + me.formData.registerPassword
                        + '\" ,\"active_yn\" : \"Y\"'
                        + ', \"delete_yn\" : \"N\"'
                        + ', \"user_email\" : \"'
                        + me.formData.registerId + '@clt.com\"'
                        + ', \"approval_status_code\" : 1}'

          me.$http.post(`http://localhost:8081/user/api/users`, body, {
              headers: {
                'Authorization': `Bearer ${VueCookies.get("jwtToken")}`,
                'Content-Type': 'application/json'
              }
            }
          ).then(function (e) {
            me.formData.registerInfo = e.data;
          })

    }
  }
}
</script>
