<template>
  <div style="height: 100%; background-color: black; text-align: center;">
    <div class="logo-footer py-5">
      PairPlay
    </div>
    <div class="pb-5">
      <p>대표 : 조영현</p>
      <div class="pb-3">
        <span>번호 : 010-XXXX-YYYY</span><span class="ms-5">사업자 번호 : 0655215</span>
      </div>
      <p>사업장 : 부산 연제구 해맞이로 유림아시아드 해성이형집</p>
      <p style="font-weight: bold;">Copyright @ SSAFY PairPlay Inc. All Rights Reserved.</p>
    </div>
  </div>
</template>
<script>
import { computed, reactive } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
export default {
  name: "Footer",
  setup() {
    const store = useStore();
    const router = useRouter();
    const state = reactive({
      loginStatus: computed(() => store.getters["root/loginStatus"]),
    });
    const moveToHome = function () {
      router.push({
        name: "Main",
      });
    };
    const moveToLogin = function () {
      router.push({
        name: "Login",
      });
    };
    const moveToSignUp = function () {
      router.push({
        name: "Signup",
      });
    };
    const moveToPlace = function () {
      router.push({
        name: "Place",
      });
    };
    const logout = function () {
      localStorage.removeItem("jwt");
      localStorage.removeItem("vuex");
      router.go();
    };
    return { state, moveToHome, moveToLogin, moveToSignUp, moveToPlace, logout };
  },
};
</script>
<style scoped lang="scss">
.logo-footer {
  font-size: xx-large;
  color: white;
  font-weight: bold;
}

p {
  font-size: large;
  color: white;
}

span {
  font-size: large;
  color: white;
}
</style>
