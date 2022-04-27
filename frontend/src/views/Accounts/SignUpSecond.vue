<template>
  <div class="d-flex row" style="height: 100vh; width: 100vw; margin: 0px;">
    <div class="col-5 login-card">
      <!-- <img src="../../assets/images/Login.jpeg" alt="" class="login-card"> -->
    </div>
    <div class="col-7">
      <center>
      <h1 style="margin-top: 10%; margin-bottom: 5%; font-weight: bold;">회원가입</h1>
      <div class="box my-4 d-flex align-items-center row">
        <div class="col-2 icon email">
        </div>
        <div class="v-line col-1">
        </div>
        <input class="col-8" type="text" style="border: 0px; height: 80%;" placeholder="이름" v-model="state.email">
      </div>
      <div class="box my-4 d-flex align-items-center row">
        <div class="col-2 icon nickname">
        </div>
        <div class="v-line col-1">
        </div>
        <input class="col-8" type="text" style="border: 0px; height: 80%;" placeholder="휴대 전화" v-model="state.nickname">
      </div>
      <span style="color: rgb(175 173 173); font-size: large; font-weight: 400;">생년월일</span>
      <br>
      <br>
      <div class="box my-4 d-flex align-items-center row">
        <div class="col-2 icon nickname">
        </div>
        <div class="v-line col-1">
        </div>
      </div>
      <!-- <div style="width: 60%;" class="d-flex justify-content-around">
        <span class="birth-drop">
          <select v-model="state.yyyy" class="birth-select">
            <option value="">년</option>
            <option v-for="(item, index) in state.yyyyList" :key="index" :value="item.value">
              {{ item.text }}
            </option>
          </select>
        </span>
        <span class="birth-drop">
          <select v-model="state.mm" class="birth-select">
            <option value="">월</option>
            <option v-for="(item, index) in state.mmlist" :key="index" :value="item.value">
              {{ item.text }}
            </option>
          </select>
        </span>
        <span class="birth-drop">
          <select v-model="state.dd" class="birth-select">
            <option value="">월</option>
            <option v-for="(item, index) in state.ddlist" :key="index" :value="item.value" >
              {{ item.text }}
            </option>
          </select>
        </span>
      </div> -->
      <br>
      <!-- <span class="error_next_box" v-if="checkFlag && (!signup.yyyy || !signup.mm || !signup.dd)" >생년월일을 입력하세요</span> -->
      <div class="box my-4 d-flex align-items-center row">
        <div class="col-2 icon password">
        </div>
        <div class="v-line col-1">
        </div>
        <input class="col-8" type="text" style="border: 0px; height: 80%;" placeholder="주소" v-model="state.password">
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
import { reactive, onMounted } from "vue"
export default {
  name: "SignupSecond",
  setup(){
    const state = reactive({
      name: "",
      phone: "",
      address: "",
      birth: "",
      yyyyList: [],
      mmlist: [],
      ddlist: [],
      yyyy: '',
      mm: '',
      dd: '',
    })

    onMounted(() => {
      const nowYear = new Date().getFullYear()
      for (let i = 0; i < 100; i++){ 
        let date = nowYear - i;
        state.yyyyList.push({ value: date, text: date });
      }
      
      for (let i = 1; i < 13; i++) {
        state.mmlist.push({ value: i, text: i, });
      }

      for ( let i = 1; i < 32; i++) {
        state.ddlist.push({ value: i, text: i, });
      }
    })

    return { state, onMounted }
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

.birth-drop {
  width: 20%;
}

.birth-select {
  width: 100%;
  height: 150%;
}
</style>