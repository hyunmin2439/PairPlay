<template>
  <div style="height: 100px" class="d-flex justify-content-between align-items-center">
    <div class="logo" @click="moveToHome">PairPlay</div>
    <div class="hyper" v-if="state.loginStatus">
      <span class="mx-4 header-logo" @click="moveToPlace">장소</span>
      <span class="mx-4 header-logo" @click="moveToMate">메이트</span>
      <span class="mx-4 header-logo" @click="moveToProfile">프로필</span>
      <span class="mx-4 header-logo" @click="logout">로그아웃</span>
      <!-- <span class="mx-4 header-logo" @click="moveToPlace">Place</span>
      <span class="mx-4 header-logo" @click="moveToMate">Mate</span>
      <span class="mx-4 header-logo" @click="moveToProfile">Profile</span>
      <span class="mx-4 header-logo" @click="logout">Logout</span> -->
    </div>
    <div class="hyper" v-else>
      <span class="mx-4 header-logo" @click="moveToLogin">로그인</span>
      <span class="mx-4 header-logo" @click="moveToSignUp">회원가입</span>
    </div>
  </div>
</template>
<script>
import { computed, reactive } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
export default {
  name: "Header",
  setup() {
    const store = useStore();
    const router = useRouter();
    const state = reactive({
      loginStatus: computed(() => store.getters["root/loginStatus"]),
      userInfo: computed(() => store.getters["root/userInfo"]),
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
    const moveToMate = function () {
      router.push({
        name: "Mate",
      });
    };
    const moveToProfile = function () {
      router.push({
        name: "ProfileTemp",
        query: {
          memberId: state.userInfo.memberId,
        },
      });
    };
    const logout = async function () {
      localStorage.removeItem("jwt");
      localStorage.removeItem("vuex");
      if (router.currentRoute.value.name == "Main") {
        router.go();
      } else {
        await router.push({
          name: "Main",
        });
        router.go();
      }
    };
    return { state, moveToHome, moveToLogin, moveToSignUp, moveToPlace, moveToMate, moveToProfile, logout };
  },
};
</script>
<style scoped lang="scss">
@import url("https://fonts.googleapis.com/css2?family=DM+Serif+Display&display=swap");

.logo {
  // font-size: 2rem;
  font-family: "DM Serif Display", serif;
  letter-spacing: 0px;
  font-size: 1.8rem;
  font-weight: 500;
  // font-weight: 600;
  color: black;
  // background-image: url("@/assets/images/Main/Logo.png");
  // background-size: 100%;
  // background-repeat: no-repeat;
  // background-position: left;
  width: 10%;
  // height: 100%;
  margin-left: 3%;

  cursor: pointer;
}
.header-logo {
  font-size: 1.2rem;
  font-weight: 600;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}
.hyper {
  font-size: x-large;
  font-weight: bold;
}

span {
  cursor: pointer;
}
</style>
