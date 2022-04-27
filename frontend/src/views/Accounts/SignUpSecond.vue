<template>
  <div class="d-flex row" style="height: 100vh; width: 100vw; margin: 0px;">
    <div class="col-5 login-card">
      <!-- <img src="../../assets/images/Login.jpeg" alt="" class="login-card"> -->
    </div>
    <div class="col-7">
      <center>
      <h1 style="margin-top: 10%; margin-bottom: 5%; font-weight: bold;">회원가입</h1>
      <div class="box my-4 d-flex align-items-center row">
        <div class="col-2 icon name">
        </div>
        <div class="v-line col-1">
        </div>
        <input class="col-8" type="text" style="border: 0px; height: 80%;" placeholder="이름" v-model="state.name">
      </div>
      <div class="box my-4 d-flex align-items-center row">
        <div class="col-2 icon phone">
        </div>
        <div class="v-line col-1">
        </div>
        <input class="col-8" type="text" style="border: 0px; height: 80%;" placeholder="휴대 전화" v-model="state.phone" @keyup="autoHypen">
      </div>
      <div class="box my-4 d-flex align-items-center row">
        <div class="col-2 icon birthday">
        </div>
        <div class="v-line col-1">
        </div>
        <!-- <input class="col-8" type="text" style="border: 0px; height: 80%;" placeholder="생년월일" v-model="state.birthday"> -->
        <div class="col-8">
          <Datepicker
            format="YYYY-MM-DD"
            style="width: 105%; height: 6vh; border: none; padding-left: 1%; position: relative; right: 2%;"
            v-model="state.birthday"
            ref="datepicker"
            placeholder="생년월일"
          />
        </div>
      </div>
      <div class="box my-4 d-flex align-items-center row">
        <div class="col-2 icon address">
        </div>
        <div class="v-line col-1">
        </div>
        <input class="col-8" type="text" id="address" @click="search()" style="border: 0px; height: 80%;" placeholder="주소" v-model="state.address">
      </div>
      <div style="width: 60%; font-size: large; font-weight: bold;" class="d-flex justify-content-end align-items-center">
        <!-- <span class="col-2" style="text-align: start; padding-left: 4.5%;">성별</span> -->
        <input class="mx-3" type="radio" name="gender" value="man" @click="changeGender($event)">남
        <input class="ms-5 mx-3" type="radio" name="gender" value="woman" @click="changeGender($event)">여
      </div>
      <br>
      <button class="my-4 accounts-btn" @click="UpdateSignUp">회원가입 완료</button>
      </center>
    </div>
  </div>
</template>

<script>
import { reactive } from "vue"
import { useStore } from "vuex"
import { useRouter} from 'vue-router'
export default {
  name: "SignUpSecond",
  setup(){
    const store = useStore()
    const router = useRouter()
    const state = reactive({
      name: "",
      phone: "",
      birthday: "",
      datepicker: null,
      address: "",
      gender_status: 0,
    })

    const yourCustomMethod = function () {
      if (state.datepicker) {
        state.datepicker.value.closeMenu()
      }
    }

    const search = function () {
      new window.daum.Postcode({
        oncomplete: (data) => {
          var roadAddr = data.roadAddress; // 도로명 주소 변수
          var extraRoadAddr = ''; // 참고 항목 변수
          // 법정동명이 있을 경우 추가한다. (법정리는 제외)
          // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
          if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
              extraRoadAddr += data.bname;
          }
          // 건물명이 있고, 공동주택일 경우 추가한다.
          if(data.buildingName !== '' && data.apartment === 'Y'){
              extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
          }
          // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
          if(extraRoadAddr !== ''){
              extraRoadAddr = ' (' + extraRoadAddr + ')';
          }
          // 우편번호와 주소 정보를 해당 필드에 넣는다.
          document.getElementById('address').value = roadAddr;
          state.address = roadAddr
        }
      }).open({
        left: (window.screen.width / 3),
        top: (window.screen.height / 3),
        popupKey: 'popup1',
        autoClose: true,
      });
    }

    const autoHypen = function () {
      state.phone = state.phone
        .replace(/[^0-9]/g, '')
        .replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, "$1-$2-$3").replace(/(-{1,2})$/g, "");
    }

    const changeGender = function(value) {
      if (value.target.value==="man") {
        state.gender_status = 0
      } else {
        state.gender_status = 1
      }
    }

    const UpdateSignUp = async function () {
      const payload = {
        name: state.name,
        gender: state.gender_status,
        birthDt: state.birthday.toISOString().substr(0, 10),
        address: state.address,
        phone: state.phone
      }
      await store.dispatch("root/signupSecond", payload)
      await router.push({
        name: "Main"
      })
    }

    return { state, yourCustomMethod, search, autoHypen, changeGender, UpdateSignUp }
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

.name {
  background-image: url('@/assets/images/Accounts/nickname.png');
}

.phone {
  background-image: url('@/assets/images/Accounts/phone.png');
}

.birthday {
  background-image: url('@/assets/images/Accounts/birthday.png');
}

.address {
  background-image: url('@/assets/images/Accounts/address.png');
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

.birth-drop {
  width: 20%;
}

.birth-select {
  width: 100%;
  height: 150%;
}
</style>