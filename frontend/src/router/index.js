import { createRouter, createWebHistory } from "vue-router";

const routes = [
  {
    path: "",
    name: "Main",
    component: () => import("../views/Main/Main.vue"),
  },
  {
    path: "/signup",
    name: "Signup",
    component: () => import("../views/Accounts/Signup.vue"),
  },
  {
    path: "/signup/next",
    name: "SignUpSecond",
    component: () => import("../views/Accounts/SignUpSecond.vue"),
  },
  {
    path: "/login",
    name: "Login",
    component: () => import("../views/Accounts/Login.vue"),
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

  {
    path: "/placedetail/:id",
    name: "PlaceDetail",
    component: () => import("../views/PlaceDetail/PlaceDetail.vue"),
    //props true로 설정하면 데이터를 props로도 받음.
    props: true,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
