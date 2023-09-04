import { login } from "@/api/user";
import { setToken, removeToken } from "@/utils/auth";
import { resetRouter } from "@/router";

const getDefaultState = () => {
  return {
    token: "",
    adminInfo: {},
    name: "",
    avatar: "",
    userId: 0,
    sidebarNav: {}
  };
};

const state = getDefaultState();

const mutations = {
  RESET_STATE: state => {
    Object.assign(state, getDefaultState());
  },
  SET_TOKEN: (state, token) => {
    state.token = token;
  },
  SET_NAME: (state, name) => {
    state.name = name;
  },
  SET_ADMIN_INFO: (state, info) => {
    state.adminInfo = info;
  },
  SET_ADMIN_NAV: (state, nav) => {
    state.sidebarNav = nav;
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar;
  },
  SET_USER_ID: (state, id) => {
    state.userId = id;
  }
};

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { username, password } = userInfo;
    return new Promise((resolve, reject) => {
      login({ username: username.trim(), password: password })
        .then(response => {
          console.log(response);

          const { data } = response;
          commit("SET_TOKEN", data.token);
          commit("SET_ADMIN_INFO", data.admin_info);
          commit("SET_ADMIN_NAV", data.nav);
          const { username, avatar, id } = data.admin_info;
          commit("SET_NAME", username);
          commit("SET_AVATAR", avatar);
          commit("SET_USER_ID", id);
          setToken(data.token);
          resolve(response.data);
        })
        .catch(error => {
          reject(error);
        });
    });
  },

  // get user info
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      // getInfo(state.token)
      //   .then(response => {
      //     const { data } = response
      //     if (!data) {
      //       return reject('Verification failed, please Login again.')
      //     }
      //     const { name, avatar } = data
      //     commit('SET_NAME', name)
      //     commit('SET_AVATAR', avatar)
      //     resolve(data)
      //   })
      //   .catch(error => {
      //     reject(error)
      //   })
    });
  },

  // use
  logout({ commit, state }) {
    removeToken(); // must remove  token  first
    resetRouter();
    commit("RESET_STATE");
    localStorage.clear();
    // return new Promise((resolve, reject) => {
    //   logout(state.token)
    //     .then(() => {
    //       removeToken() // must remove  token  first
    //       resetRouter()
    //       commit('RESET_STATE')
    //       resolve()
    //     })
    //     .catch(error => {
    //       reject(error)
    //     })
    // })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      removeToken(); // must remove  token  first
      commit("RESET_STATE");
      resolve();
    });
  }
};

export default {
  namespaced: true,
  state,
  mutations,
  actions
};
