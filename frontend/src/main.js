import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./common/store";
import axios from "./common/axios";
import naver from "vue3-naver-maps";
import Datepicker from "vue3-datepicker";
import VueCalendarHeatmap from 'vue3-calendar-heatmap'

createApp(App).use(axios).use(store).use(router).use(naver, { clientId: "4ne0ifmzh1" }).component("Datepicker", Datepicker).use(VueCalendarHeatmap).mount("#app");
