<template>
  <div style="height: 100px" class="d-flex justify-content-between align-items-center">
    <div class="logo" @click="moveToHome"></div>
    <div class="hyper" v-if="state.loginStatus">
      <span class="mx-4" @click="moveToPlace">Place</span>
      <span class="mx-4" @click="moveToMate">Mate</span>
      <span class="mx-4" @click="moveToProfile">Profile</span>
      <span class="mx-4" @click="logout">Logout</span>
    </div>
    <div class="hyper" v-else>
      <span class="mx-4" @click="moveToLogin">Login</span>
      <span class="mx-4" @click="moveToSignUp">SignUp</span>
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
        name: "Mate"
      })
    }
    const moveToProfile = function () {
      router.push({
        name: "Profile",
        params: {
          memberId: state.userInfo.memberId,
        }
      })
    }
    const logout = async function () {
      localStorage.removeItem("jwt");
      localStorage.removeItem("vuex");
      if (router.currentRoute.value.name == "Main") {
        router.go()
      } else {
        await router.push({
          name: "Main"
        })
        router.go()
      }
    };
    return { state, moveToHome, moveToLogin, moveToSignUp, moveToPlace, moveToMate, moveToProfile, logout };
  },
};
</script>
<style scoped lang="scss">
.logo {
  background-image: url("@/assets/images/Main/Logo.png");
  background-size: 100%;
  background-repeat: no-repeat;
  background-position: left;
  width: 10%;
  height: 100%;
  margin-left: 2%;

  cursor: pointer;
}

.hyper {
  font-size: x-large;
  font-weight: bold;
}

span {
  cursor: pointer;
}
</style>
