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
    path: "/oauth/success",
    name: "OAuthSuccess",
    component: () => import("../views/Accounts/OAuthSuccess.vue"),
  },
  {
    path: "/oauth/failure",
    name: "OAuthFailure",
    component: () => import("../views/Accounts/OAuthFailure.vue"),
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
  {
    path: "/mate",
    name: "Mate",
    component: () => import("../views/Mate/Mate.vue"),
  },
  {
    path: "/profile/:memberId",
    name: "Profile",
    component: () => import("../views/Profile/Profile.vue"),
  },
  {
    path: "/ProfileTemp/temp",
    name: "ProfileTemp",
    component: () => import("../views/Profile/Components/ProfileTemp.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
