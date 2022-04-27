<template>
  <div class="d-flex row" style="height: 100vh; width: 100vw; margin: 0px;">
    <div class="col-7">
      <center>
      <h1 style="margin-top: 10%; margin-bottom: 10%; font-weight: bold;">로그인</h1>
      <div class="box my-4 d-flex align-items-center row">
        <div class="col-2 icon email">
        </div>
        <div class="v-line col-1">
        </div>
        <input class="col-8" type="text" style="border: 0px; height: 80%;" placeholder="이메일" v-model="state.email">
      </div>
      <div class="box my-4 d-flex align-items-center row">
        <div class="col-2 icon password">
        </div>
        <div class="v-line col-1">
        </div>
        <input class="col-8" type="password" style="border: 0px; height: 80%;" placeholder="비밀번호" v-model="state.password">
      </div>
      <button class="my-4 accounts-btn" @click="GetLogin">다음으로</button>
      <div class="d-flex" style="width: 60%;">
        <span class="me-4 hyperlink" type="button" @click="moveToLogin">회원가입</span>
        <span class="me-4 hyperlink" type="button" @click="moveToLogin">비밀번호 찾기</span>
        <span class="hyperlink" type="button" @click="moveToMain">뒤로 가기</span>
      </div>
      </center>
    </div>
    <div class="col-5 login-card">
    </div>
  </div>
</template>

<script>
import { reactive } from "vue"
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
export default {
  name: "Login",
  setup() {
    const store = useStore()
    const router = useRouter()
    const state = reactive({
      email: "",
      password: "",
    })

    const GetLogin = async function () {
      const payload = {
        email: state.email,
        password: state.password
      }
      await store.dispatch("root/login", payload)
      await router.push({
        name: "Main"
      })
    }
    return { state, GetLogin }
  }
};
</script>

<style scoped lang="scss">
.login-card {
  background-image: url('@/assets/images/Accounts/Login.png');
  background-size: cover;
  background-repeat: no-repeat;
}

.box {
  width: 60%;
  height: 8vh;
  border: 1px solid #E0E0E0;
}

.icon {
  background-size: 40%;
  background-repeat: no-repeat;
  background-position: center;
  height: 100%;
  filter: opacity(0.5);
}

.email {
  background-image: url('@/assets/images/Accounts/email.png');
}

.password {
  background-image: url('@/assets/images/Accounts/password.png');
}

.v-line{
 border-left: 2px solid #E0E0E0;
 height:80%;
 left: 50%;
 /* position: absolute; */
}

::placeholder {
  color: rgb(180 180 180);
  font-size: large;
  font-weight: 400;
  opacity: 1; /* Firefox */
}

.accounts-btn {
  position: relative;
  font-size: 25px;
  padding-top: 5px;
  padding-bottom: 5px;
  backface-visibility: hidden;
  width: 60%;
  font-weight: 700;
  letter-spacing: 5px;
  border: 2px solid #4D4D4D;
  border-radius: 10px;
  background: #4D4D4D;
  color: white !important;
}

.hyperlink {
  text-decoration: underline;
  color: blue;
  font-weight: 700;
}
</style>
