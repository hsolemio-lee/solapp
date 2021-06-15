import Vue from "vue";
import Vuex from "vuex";
import router from "../router";
import {solAxios, smartThingsAxios} from "../config/solAxios";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    userInfo: null,
    allUsers: [
      {id: 1, username: 'sol', password: "1234"}
    ],
    isLogin: false,
    isLoginError: false,

    progressBar: false,

    snackbar: false,
    snackbarText: "",
  },
  mutations: {
    // 로그인 성공
    loginSuccess(state, userInfo) {
      state.isLogin = true;
      state.isLoginError = false;
      state.userInfo = userInfo;
    },
    // 로그인 실패
    loginError(state) {
      state.isLogin = false;
      state.isLoginError = true;
    },
    logout(state) {
      state.isLogin = false;
      state.userInfo = null;
    },
    showProgressBar(state, arg) {
      state.progressBar = arg;
    },
    showSnackbar(state, arg) {
        state.snackbar = false;
        state.snackbar = true;
        state.snackbarText = arg;
    }
  },
  actions: {
    // 로그인 시도
     
    login({ dispatch, commit }, signObj) {
      solAxios.post("/login", signObj)
      .then(res => {
        const loginInfo = res.data;
        localStorage.setItem("authToken", loginInfo.authToken);
        dispatch("loginCheck");
      })
      .catch(error => {
        console.log(error);
        commit('loginError');
      });
    },
    loginCheck({commit}) {
      let authToken = localStorage.getItem("authToken");
      solAxios.defaults.headers.common['Authorization'] = authToken;
      solAxios.get("/rest/v1/login/check").
      then(res => {
        const loginInfo = res.data;
        let userInfo = {
          username: loginInfo.username,
          firstName: loginInfo.firstName,
          lastName: loginInfo.lastName,
          email: loginInfo.email,
        };
        commit('loginSuccess', userInfo);
        smartThingsAxios.defaults.headers.common['Authorization'] = "Bearer "+loginInfo.smartThingsToken;
        router.push({name: 'home'});
      })
      .catch(error => {
        console.log(error);
        commit('logout');
        router.push({name: 'login'});
      })
    },
    logout({commit}) {
        commit('logout');
        router.push({name: 'login'});
    },
    showProgressBar({commit}, arg) {
        commit('showProgressBar', arg);
    },
    showSnackbar({commit}, arg) {
        commit('showSnackbar', arg);
    }
  },
  modules: {},
});
