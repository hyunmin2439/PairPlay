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
        <span class="col-2 ms-4" style="padding: 0px" type="button">중복확인</span>
      </div>
      <div class="box my-4 d-flex align-items-center row">
        <div class="col-2 icon nickname">
        </div>
        <div class="v-line col-1">
        </div>
        <input class="col-6" type="text" style="border: 0px; height: 80%;" placeholder="닉네임" v-model="state.nickname">
        <span class="col-2 ms-4" style="padding: 0px" type="button">중복확인</span>
      </div>
      <div class="box my-4 d-flex align-items-center row">
        <div class="col-2 icon password">
        </div>
        <div class="v-line col-1">
        </div>
        <input class="col-8" type="text" style="border: 0px; height: 80%;" placeholder="비밀번호" v-model="state.password">
      </div>
      <div class="box my-4 d-flex align-items-center row">
        <div class="col-2 icon password">
        </div>
        <div class="v-line col-1">
        </div>
        <input class="col-8" type="text" style="border: 0px; height: 80%;" placeholder="비밀번호 확인" v-model="state.passwordConfirm">
      </div>
      <button class="my-4 accounts-btn">다음으로</button>
      <div class="d-flex" style="width: 60%;">
        <span class="me-4 hyperlink">로그인</span>
        <span class="hyperlink">뒤로 가기</span>
      </div>
      </center>
    </div>
  </div>
</template>

<script>
import { reactive } from "vue"
import { useStore } from 'vuex'
export default {
  name: "LoginSecond",
  setup(){
    const store = useStore()
    const state = reactive({
      email: "",
      nickname: "",
      password: "",
      passwordConfirm: "",
    })

    const loginGetEmail = async function() {
      await store.dispatch("root/loginGetEmail", {"value": state.email})
    }
    return { state, loginGetEmail }
  }
  
}
</script>

<style scoped lang="scss">
.login-card {
  background-image: url('@/assets/images/Login.png');
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
  background-image: url('@/assets/images/email.png');
}

.nickname {
  background-image: url('@/assets/images/nickname.png');
}

.password {
  background-image: url('@/assets/images/password.png');
}

.v-line{
 border-left: 2px solid #E0E0E0;
 height:80%;
 left: 50%;
 /* position: absolute; */
}

::placeholder {
  color: #E0E0E0;
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