import { createRouter, createWebHistory } from "vue-router";
import Login from "../views/Accounts/Login.vue";
import Place from "../views/Place/Place.vue"

const routes = [
  {
    path: "/login",
    name: "Login",
    component: Login,
  },
  {
    path: "/place",
    name: "Place",
    component: Place,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
