<template>
  <div class="d-flex row" style="height: 100vh; width: 100vw; margin: 0px;">
    <div class="col-5 login-card">
      <!-- <img src="../../assets/images/Login.jpeg" alt="" class="login-card"> -->
    </div>
    <div class="col-7">
      <center>
      <h1 style="margin-top: 10%; margin-bottom: 5%; font-weight: bold;">회원가입</h1>
      <div class="box d-flex align-items-center row">
        <div class="col-2 icon email">
        </div>
        <div class="v-line col-1">
        </div>
        <input class="col-6" type="text" style="border: 0px; height: 80%;" placeholder="이메일 주소" v-model="state.email" @keyup="clickValidateEmail">
        <span class="col-2 ms-4" style="padding: 0px; font-weight: bold;" type="button" id="email_dup" @click="checkDuplicate($event)">중복확인</span>
      </div>
      <div v-if="state.emailValid===false & state.emailMessage!=''">
        <p style="color: red;">{{ state.emailMessage }}</p>
      </div>
      <div v-else-if="state.emailValie===false & state.emailMessage==''">
        <p style="color: white;">하</p>
      </div>
      <div v-else>
        <p style="color: white;">하</p>
      </div>
      <div class="box mt-4 d-flex align-items-center row">
        <div class="col-2 icon nickname">
        </div>
        <div class="v-line col-1">
        </div>
        <input class="col-6" type="text" style="border: 0px; height: 80%;" placeholder="닉네임" v-model="state.nickname" @keyup="clickValidateNickname">
        <span class="col-2 ms-4" style="padding: 0px; font-weight: bold;" type="button" id="nick_dup" @click="checkDuplicate($event)">중복확인</span>
      </div>
      <div v-if="state.nicknameValid===false & state.nicknameMessage!=''">
        <p style="color: red;">{{ state.nicknameMessage }}</p>
      </div>
      <div v-else-if="state.nicknameValid===false & state.nicknameMessage==''">
        <p style="color: white;">하</p>
      </div>
      <div v-else>
        <p style="color: white;">하</p>
      </div>
      <div class="box mt-4 d-flex align-items-center row">
        <div class="col-2 icon password">
        </div>
        <div class="v-line col-1">
        </div>
        <input class="col-8" type="password" style="border: 0px; height: 80%;" placeholder="비밀번호" v-model="state.password" @keyup="clickValidatePassword">
      </div>
      <div v-if="state.passwordValid===false & state.passwordMessage!=''">
        <p style="color: red;">{{ state.passwordMessage }}</p>
      </div>
      <div v-else-if="state.passwordValid===false & state.passwordMessage==''">
        <p style="color: white;">하</p>
      </div>
      <div v-else>
        <p style="color: white;">하</p>
      </div>
      <div class="box mt-4 d-flex align-items-center row">
        <div class="col-2 icon password">
        </div>
        <div class="v-line col-1">
        </div>
        <input class="col-8" type="password" style="border: 0px; height: 80%;" placeholder="비밀번호 확인" v-model="state.passwordConfirm">
      </div>
      <div v-if="state.password!=state.passwordConfirm">
        <p style="color: red;">비밀번호와 동일하지 않습니다.</p>
      </div>
      <div v-else>
        <p style="color: white;">하</p>
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
import Swal from 'sweetalert2'
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
      nicknameDuplicate: computed(() => store.getters["root/nicknameDuplicate"]),
      emailMessage: "",
      nicknameMessage: "",
      passwordMessage: "",
      emailValid: false,
      nicknameValid: false,
      passwordValid: false,
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

    const clickValidateEmail = function () {
      let emailCheck = state.email
      let lengthEmail = emailCheck.length
      var emailRule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i
      if (emailRule.test(emailCheck)) {
        state.emailMessage = "사용할 수 있는 이메일입니다."
        state.emailValid = true
      } else {
        state.emailMessage = "이메일 양식을 맞춰주세요."
        state.emailValid = false
      }
      if (lengthEmail==0) {
        state.emailMessage = ""
        state.emailValid = false
      }
    }

    const clickValidateNickname = function () {
      let nicknameCheck = state.nickname
      let lengthNickname = nicknameCheck.length
      // eslint-disable-next-line no-useless-escape
      var nicknameRule = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/g
      let specialNickname = nicknameCheck.search(nicknameRule)
      let spaceNickname = nicknameCheck.search(/\s/)
      if (lengthNickname > 15 | lengthNickname <= 1) {
        state.nicknameMessage = "닉네임 길이 1글자 초과 및 15글자 미만"
        state.nicknameValid = false
      } else {
        if (specialNickname != -1 || spaceNickname != -1) {
          state.nicknameMessage =
            "닉네임에 특수문자나 공백을 포함 불가능"
          state.nicknameValid = false
        } else {
          state.nicknameMessage = "사용할 수 있는 닉네임입니다."
          state.nicknameValid = true
        }
      }
      if (lengthNickname == 0) {
        state.nicknameMessage = ""
        state.nicknameValid = false
      }
    }

    const clickValidatePassword = function () {
      let passwordCheck = state.password
      let lengthPassword = passwordCheck.toString().length
      let engPassword = passwordCheck.search(/[a-z]/gi)
      let numPassword = passwordCheck.search(/[0-9]/g)
      let specialPassword = passwordCheck.search(
        // eslint-disable-next-line no-useless-escape
        /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi
      )
      if (lengthPassword < 8 || lengthPassword > 15) {
        state.passwordMessage = "8자 이상 15자 이하로 작성해주세요."
        state.passwordValid = false
      } else {
        if (engPassword == -1 || numPassword == -1 || specialPassword == -1) {
          state.passwordMessage =
            "영문, 숫자, 특수문자를 하나 이상 포함해야 합니다."
          state.passwordValid = false
        } else {
          state.passwordMessage = "사용할 수 있는 비밀번호입니다."
          state.passwordValid = true
        }
      }
      if (lengthPassword == 0) {
        state.passwordMessage = ""
        state.passwordValid = false
      }
    }

    const GetSignUp = async function() {
      if (state.emailDuplicate===1 & state.nicknameDuplicate === 1 & state.password===state.passwordConfirm) {
        if (state.emailValid == true & state.nicknameValid == true & state.passwordValid == true) {
          const payload = {
            email: state.email,
            nickname: state.nickname,
            password: state.password,
          }
          await store.dispatch("root/signup", payload)
          await router.push({
            name: 'SignUpSecond',
          })
        } else if (state.emailValid == false) {
          Swal.fire({
            icon: 'error',
            title: '실패...',
            text: '이메일 양식이 잘못되었습니다.',
          })
        } else if (state.nicknameValid == false) {
          Swal.fire({
            icon: 'error',
            title: '실패...',
            text: '닉네임 양식이 잘못되었습니다.',
          })
        } else {
          Swal.fire({
            icon: 'error',
            title: '실패...',
            text: '비밀번호 양식이 잘못되었습니다.',
          })
        }
      } else {
        if (state.emailDuplicate === 0) {
          Swal.fire({
            icon: 'error',
            title: '실패...',
            text: '이메일 중복 확인을 해주세요.',
          })
        } else if (state.nicknameDuplicate === 0) {
          Swal.fire({
            icon: 'error',
            title: '실패...',
            text: '닉네임 중복 확인을 해주세요.',
          })
        } else {
          Swal.fire({
            icon: 'error',
            title: '실패...',
            text: '비밀번호와 비밀번호 확인이 동일하지 않습니다.',
          })
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

    return { 
      state, onMounted, 
      checkDuplicate, 
      clickValidateEmail,
      clickValidateNickname,
      clickValidatePassword,
      GetSignUp, 
      moveToLogin, moveToMain }
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
