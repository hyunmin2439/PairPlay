import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./common/store";
import axios from "./common/axios";
import naver from "vue3-naver-maps";

import Datepicker from "vue3-datepicker";

createApp(App).use(axios).use(store).use(router).use(naver, { clientId: "your clientId" }).component("Datepicker", Datepicker).mount("#app");
