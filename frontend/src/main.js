import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./common/lib/store";
import VueAxios from "./common/lib/axios";
import axios from "./common/lib/axios";

import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.js";

createApp(App).use(VueAxios, axios).use(store).use(router).mount("#app");
