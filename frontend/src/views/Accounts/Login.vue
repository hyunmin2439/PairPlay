<template>
  <div class="d-flex row" style="height: 100vh; width: 100vw; margin: 0px;">
    <div class="col-7">
      <center>
      <h1 style="margin-top: 10%; margin-bottom: 10%; font-weight: bold;">로그인</h1>
      <div class="box my-5 d-flex align-items-center row">
        <div class="col-2 icon email">
        </div>
        <div class="v-line col-1">
        </div>
        <input class="col-8" type="text" style="border: 0px; height: 80%;" placeholder="이메일" v-model="state.email">
      </div>
      <div class="box my-5 d-flex align-items-center row">
        <div class="col-2 icon password">
        </div>
        <div class="v-line col-1">
        </div>
        <input class="col-8" type="password" style="border: 0px; height: 80%;" placeholder="비밀번호" v-model="state.password">
      </div>
      <!-- <a class="social_btn" href="http://localhost:8088/oauth2/authorization/google?redirect_uri=http://localhost:8080/oauth/redirect"> -->
      <a class="social_btn" href="https://pairplay.site/oauth2/authorization/google?redirect_uri=https://pairplay.site:443/oauth/redirect">
        <img src="../../assets/images/Accounts/Google.png" alt="" class="social_login social-google">
      </a>
      <!-- <a class="social_btn" href="http://localhost:8088/oauth2/authorization/kakao?redirect_uri=http://localhost:8080/oauth/redirect"> -->
      <a class="social_btn" href="https://pairplay.site/oauth2/authorization/kakao?redirect_uri=https://pairplay.site:443/oauth/redirect">
        <img src="../../assets/images/Accounts/Kakao.png" alt="" class="social_login social-kakao">
      </a>
      <br>
      <button class="my-5 accounts-btn" @click="GetLogin">로그인 완료</button>
      <div class="d-flex" style="width: 60%;">
        <span class="me-4 hyperlink" type="button" @click="moveToSignup">회원가입</span>
        <span class="me-4 hyperlink" type="button" @click="resetPassword">임시 비밀번호 발급</span>
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
import axios from "axios";
import Swal from 'sweetalert2'
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
      const loginStatus = store.getters["root/loginStatus"]
      if (loginStatus == true) {
        Swal.fire({
          icon: 'success',
          title: '성공!',
          text: '로그인에 성공했습니다.',
        })
        await router.push({
        name: "Main"
      })
      } else {
        Swal.fire({
          icon: 'error',
          title: '실패...',
          text: '로그인에 실패했습니다.',
        })
      }
    }

    const moveToSignup = async function () {
      router.push({
        name: "Signup"
      })
    }

    const moveToMain = async function () {
      router.push({
        name: "Main"
      })
    }

    const resetPassword = async function () {
      if (state.email != "") {
        const url = "members/check/email";
        await axios.post(url, state.email)
        .then((res) => {
          if (res.data.code == 200) {
            Swal.fire({
              icon: 'error',
              title: '실패...',
              text: '존재하지 않는 이메일입니다.',
            })
            } else {  
            store.dispatch("root/loginResetPassword", {
              'value': state.email
            })
          }
        })
      } else {
        Swal.fire('자신의 이메일을 기입한 이후 클릭해주세요.')
      }
    }

    return { state, GetLogin, moveToSignup, moveToMain, resetPassword }
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

.social-google {
  width: 18rem;
  margin-right: 2rem;
}

.social-kakao {
  width: 16rem;
  margin-left: 2rem;
}
</style>
