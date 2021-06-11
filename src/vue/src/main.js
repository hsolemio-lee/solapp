import Vue from "vue";
import router from "./router";
import store from "./store";
import App from "./App.vue";
import vuetify from "./plugins/vuetify";
import {solAxios, smartThingsAxios} from "./config/solAxios";

Vue.config.productionTip = false;
Vue.prototype.$http = solAxios;
Vue.prototype.$smart = smartThingsAxios;

new Vue({
  router,
  store,
  beforeCreate() {
    this.$store.dispatch('loginCheck');
  },
  vuetify,
  render: (h) => h(App),
}).$mount("#app");
