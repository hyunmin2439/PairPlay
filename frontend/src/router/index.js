import { createRouter, createWebHistory } from "vue-router";

const routes = [
  {
    path: "/signup",
    name: "Signup",
    component: () => import("../views/Accounts/Signup.vue"),
  },
  {
    path: "/signup/next",
    name: "SignupSecond",
    component: () => import("../views/Accounts/SignupSecond.vue"),
  },
  {
    path: "/place",
    name: "Place",
    component: () => import("../views/Place/Place.vue"),
  },
  {
    path: "/placesearch",
    name: "PlaceSearch",
    component: () => import("../views/PlaceSearch/PlaceSearch.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
