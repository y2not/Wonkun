<template>
  <div id="home">
    <h1>Home</h1>
    <v-container fluid>
      <v-card class="pa-2" outlined tile>
        <v-subheader dark>
          <v-icon dark right>mdi-checkbox-marked-circle</v-icon>전체 프로젝트
          디렉토리 구성 (외부 사례)
        </v-subheader>
        <pre class="language-markup" style="background-color:transparent;">
            <code>   .
      ├─ README.md
      ├─ index.html
      ├─ webpack.config.js
      ├─ package.json
      ├─ test                 테스트 Unit
      ├─ public               Static Assets
      └─ src
         ├─ main.js
         ├─ App.vue
         ├─ components        컴포넌트
         │  ├─ common
         │  └─ ...
         ├─ routes            라우터
         │  ├─ index.js
         │  └─ routes.js
         ├─ views             라우터 페이지
         │  ├─ MainView.vue
         │  └─ ...
         ├─ store             상태 관리
         │  ├─ auth
         │  ├─ index.js
         │  └─ ...
         ├─ api               api 함수
         │  ├─ index.js
         │  ├─ users.js
         │  └─ ...
         ├─ utils             필터 등의 유틸리티 함수
         │  ├─ filters.js
         │  ├─ bus.js
         │  └─ ...
         ├─ mixins            믹스인
         │  ├─ index.js
         │  └─ ...
         ├─ plugins           플러그인
         │  ├─ ChartPlugin.js
         │  └─ ...
         ├─ locales           다국어
         │  ├─ index.js
         │  ├─ en.json
         │  └─ ...
         ├─ images            이미지
         ├─ fonts             폰트
         └─ assets            기타 자원
       </code>
       </pre>
      </v-card>
      <br />
      <v-card class="pa-2" outlined tile>
        <v-subheader dark>
          <v-icon dark right>mdi-checkbox-marked-circle</v-icon>기본 설명
        </v-subheader>
        <pre class="language-css" style="background-color:transparent;">
            <code>css에 글자가 단축되는 경우를 위해 ellipsis 처리
.txt_line {
     width:70px;
     padding:0 5px;
     overflow:hidden;
     text-overflow:ellipsis;
     white-space:nowrap;
 }

   Block레벨 테그에서만 적용됨.
   overflow:hidden : 넓이가 70px를 넒어서는 내용에 대해서는 보이지 않게 처리함
   text-overflow:ellipsis : 글자가 넓이 70px를 넘을 경우 생략부호를 표시함
   white-space:nowrap : 공백문자가 있는 경우 줄바꿈하지 않고 한줄로 나오게 처리함 (\A로 줄바꿈가능)
            </code>
          </pre>
      </v-card>
      <br />
      <v-card class="pa-2" outlined tile>
        <v-subheader dark>
          <v-icon dark right>mdi-checkbox-marked-circle</v-icon>/main.js (vue를 구성하는 전역 변수 및 라이브러리) 구성
        </v-subheader>
        <pre class="language-js" style="background-color:transparent;">
            <code>
              import Vue from "vue";
              import App from "./App.vue";
              import i18n from "./i18n";

              Vue.config.productionTip = false;

              new Vue({
                i18n,
                render: h => h(App)
              }).$mount("#app");
            </code>
          </pre>
      </v-card>
      <br />
      <v-card class="pa-2" outlined tile>
        <v-subheader dark>
          <v-icon dark right>mdi-checkbox-marked-circle</v-icon>/App.vue (vue 화면을 구성하는 최초 Template) 구성
        </v-subheader>
        <pre class="language-html" style="background-color:transparent;">
            <code>
&lt;template&gt;
  &lt;div id=&quot;app&quot;&gt;
    &lt;header-component&gt;&lt;/header-component&gt;
    &lt;nav-component&gt;&lt;/nav-component&gt;
    &lt;body-component&gt;&lt;/body-component&gt;
    &lt;sidebar-component&gt;&lt;/sidebar-component&gt;
    &lt;footer-component&gt;&lt;/footer-component&gt;
  &lt;/div&gt;
&lt;/template&gt;

&lt;script&gt;
import HeaderComponent from &quot;./views/Header.vue&quot;;
import NavComponent from &quot;./views/Nav.vue&quot;;
import BodyComponent from &quot;./views/Body.vue&quot;;
import SidebarComponent from &quot;./views/SideBar.vue&quot;;
import FooterComponent from &quot;./views/Footer.vue&quot;;

export default {
  name: &quot;app&quot;,
  components: {
    HeaderComponent,
    NavComponent,
    BodyComponent,
    SidebarComponent,
    FooterComponent
  }
};
&lt;/script&gt;

&lt;style&gt;
#app {
  font-family: &quot;Avenir&quot;, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
&lt;/style&gt;

            </code>
          </pre>
      </v-card>
      <br />
      <v-card class="pa-2" outlined tile>
        <v-subheader dark>
          <v-icon dark right>mdi-checkbox-marked-circle</v-icon>/i18n.js (vue-i18n에 대한 설정) 구성
        </v-subheader>
        <pre class="language-js" style="background-color:transparent;">
            <code>
              import Vue from 'vue'
              import VueI18n from 'vue-i18n'

              Vue.use(VueI18n)

              function loadLocaleMessages () {
                const locales = require.context('./locales', true, /[A-Za-z0-9-_,\s]+\.json$/i)
                const messages = {}
                locales.keys().forEach(key => {
                  const matched = key.match(/([A-Za-z0-9-_]+)\./i)
                  if (matched && matched.length > 1) {
                    const locale = matched[1]
                    messages[locale] = locales(key)
                  }
                })
                return messages
              }

              export default new VueI18n({
                locale: process.env.VUE_APP_I18N_LOCALE || 'en',
                fallbackLocale: process.env.VUE_APP_I18N_FALLBACK_LOCALE || 'en',
                messages: loadLocaleMessages()
              })
            </code>
          </pre>
      </v-card>
      <br />
      <v-card class="pa-2" outlined tile>
        <v-subheader dark>
          <v-icon dark right>mdi-checkbox-marked-circle</v-icon>/routes/routes.js (메뉴에 대한 router) 구성
        </v-subheader>
        <pre class="language-js" style="background-color:transparent;">
            <code>
              import Body from "./views/Body.vue";
              import SignIn from "./views/SignIn.vue";

              export default [
                { path: "/", component: Body },
                { path: "/signin", component: SignIn }
              ];
            </code>
          </pre>
      </v-card>
      <br />
      <v-card class="pa-2" outlined tile>
        <v-subheader dark>
          <v-icon dark right>mdi-checkbox-marked-circle</v-icon>/i18n.vue (vue-i18n에 대한 설정) 구성
        </v-subheader>
        <pre class="language-js" style="background-color:transparent;">
            <code>
                {{basicHtml}}
            </code>
          </pre>
      </v-card>
    </v-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      basicHtml:
        "data() {\
        return {\
          basicHtml:\
        }\
      }"
    };
  }
};
</script>
