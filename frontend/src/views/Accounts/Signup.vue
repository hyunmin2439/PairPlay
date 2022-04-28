<template>
  <div class="d-flex row" style="height: 100vh; width: 100vw; margin: 0px;">
    <div class="col-5 login-card">
      <!-- <img src="../../assets/images/Login.jpeg" alt="" class="login-card"> -->
    </div>
    <div class="col-7">
      <center>
      <h1 style="margin-top: 10%; margin-bottom: 10%; font-weight: bold;">회원가입</h1>
      <div class="box my-4 d-flex align-items-center row">
        <div class="col-2 icon email">
        </div>
        <div class="v-line col-1">
        </div>
        <input class="col-6" type="text" style="border: 0px; height: 80%;" placeholder="이메일 주소" v-model="state.email">
        <span class="col-2 ms-4" style="padding: 0px" type="button" id="email_dup" @click="checkDuplicate($event)">중복확인</span>
      </div>
      <div class="box my-4 d-flex align-items-center row">
        <div class="col-2 icon nickname">
        </div>
        <div class="v-line col-1">
        </div>
        <input class="col-6" type="text" style="border: 0px; height: 80%;" placeholder="닉네임" v-model="state.nickname">
        <span class="col-2 ms-4" style="padding: 0px" type="button" id="nick_dup" @click="checkDuplicate($event)">중복확인</span>
      </div>
      <div class="box my-4 d-flex align-items-center row">
        <div class="col-2 icon password">
        </div>
        <div class="v-line col-1">
        </div>
        <input class="col-8" type="password" style="border: 0px; height: 80%;" placeholder="비밀번호" v-model="state.password">
      </div>
      <div class="box my-4 d-flex align-items-center row">
        <div class="col-2 icon password">
        </div>
        <div class="v-line col-1">
        </div>
        <input class="col-8" type="password" style="border: 0px; height: 80%;" placeholder="비밀번호 확인" v-model="state.passwordConfirm">
      </div>
      <button class="my-4 accounts-btn" @click="GetSignUp">다음으로</button>
      <div class="d-flex" style="width: 60%;">
        <span class="me-4 hyperlink" type="button" @click="moveToLogin">로그인</span>
        <span class="hyperlink" type="button" @click="moveToMain">뒤로 가기</span>
      </div>
      </center>
    </div>
  </div>
</template>

<script>
import { reactive, computed, onMounted } from "vue"
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
export default {
  name: "Signup",
  setup(){
    const store = useStore()
    const router = useRouter()
    const state = reactive({
      email: "",
      nickname: "",
      password: "",
      passwordConfirm: "",
      emailDuplicate: computed(() => store.getters["root/emailDuplicate"]),
      nicknameDuplicate: computed(() => store.getters["root/nicknameDuplicate"])
    })

    onMounted(() => {
      store.commit("root/SIGNUP_DUPLICATE_EMAIL", 0)
      store.commit("root/SIGNUP_DUPLICATE_NICKNAME", 0)
    })

    const checkDuplicate = async function(val) {
      if (val.target.id === 'email_dup') {
        await store.dispatch("root/signupDuplicateEmail", {"value": state.email})
      } else {
        await store.dispatch("root/signupDuplicateNickname", {"value": state.nickname})
      }
    }

    const GetSignUp = async function() {
      if (state.emailDuplicate===1 & state.nicknameDuplicate === 1 & state.password===state.passwordConfirm) {
        const payload = {
          email: state.email,
          nickname: state.nickname,
          password: state.password,
        }
        await store.dispatch("root/signup", payload)
        await router.push({
          name: 'SignUpSecond',
        })
      } else {
        if (state.emailDuplicate === 0) {
          alert('이메일 중복 확인을 해주세요.')
        } else if (state.nicknameDuplicate === 0) {
          alert('닉네임 중복 확인을 해주세요.')
        } else {
          alert('비밀번호와 비밀번호 확인이 동일하지 않습니다.')
        }
      }
      
    }

    const moveToLogin = async function () {
      await router.push({
        name: "Login"
      })
    }

    const moveToMain = async function () {
      await router.push({
        name: "Main"
      })
    }

    return { state, onMounted, checkDuplicate, GetSignUp, moveToLogin, moveToMain }
  }
  
}
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

.nickname {
  background-image: url('@/assets/images/Accounts/nickname.png');
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
