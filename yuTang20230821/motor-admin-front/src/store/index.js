import Vue from "vue";
import Vuex from "vuex";
import getters from "./getters";

import SecureLS from "secure-ls";

var ls = new SecureLS({
  encodingType: "aes",
  isCompression: false,
  encryptionSecret: "vuex"
});
import createPersistedState from "vuex-persistedstate";
import createMutationsSharer from "vuex-shared-mutations";

Vue.use(Vuex);

// https://webpack.js.org/guides/dependency-management/#requirecontext
const modulesFiles = require.context("./modules", true, /\.js$/);

// you do not need `import app from './modules/app'`
// it will auto require all vuex module from modules file
const modules = modulesFiles.keys().reduce((modules, modulePath) => {
  // set './app.js' => 'app'
  const moduleName = modulePath.replace(/^\.\/(.*)\.\w+$/, "$1");
  const value = modulesFiles(modulePath);
  modules[moduleName] = value.default;
  return modules;
}, {});

const store = new Vuex.Store({
  modules,
  getters,
  plugins: [
    createMutationsSharer({
      predicate: ["user/SET_TOKEN", "user/SET_ADMIN_INFO", "user/SET_ADMIN_NAV"]
    }),
    createPersistedState({
      key: "vuex",
      storage: {
        getItem: key => ls.get(key),
        setItem: (key, value) => ls.set(key, value),
        removeItem: key => ls.remove(key)
      }
    })
  ]
});

export default store;
