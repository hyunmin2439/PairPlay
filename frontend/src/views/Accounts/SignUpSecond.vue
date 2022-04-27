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
        <input class="col-8" type="text" style="border: 0px; height: 80%;" placeholder="휴대 전화" v-model="state.phone">
      </div>
      <div class="box my-4 d-flex align-items-center row">
        <div class="col-2 icon birthday">
        </div>
        <div class="v-line col-1">
        </div>
        <input class="col-8" type="text" style="border: 0px; height: 80%;" placeholder="생년월일" v-model="state.birthday">
      </div>
      <div class="box my-4 d-flex align-items-center row">
        <div class="col-2 icon address">
        </div>
        <div class="v-line col-1">
        </div>
        <input class="col-8" type="text" id="address" @click="search()" style="border: 0px; height: 80%;" placeholder="주소" v-model="state.address">
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
      birthday: "",
      address: "",
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
          // document.getElementById('postcode').value = data.zonecode;
          // document.getElementById("roadAddress").value = roadAddr;
          // document.getElementById("jibunAddress").value = data.jibunAddress;
          
          // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
          if(roadAddr !== ''){
              document.getElementById("extraAddress").value = extraRoadAddr;
          } else {
              document.getElementById("extraAddress").value = '';
          }
          var guideTextBox = document.getElementById("guide");
          // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
          if(data.autoRoadAddress) {
              var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
              guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
              guideTextBox.style.display = 'block';
          } else if(data.autoJibunAddress) {
              var expJibunAddr = data.autoJibunAddress;
              guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
              guideTextBox.style.display = 'block';
          } else {
              guideTextBox.innerHTML = '';
              guideTextBox.style.display = 'none';
          }
        }
      }).open();
    }
    return { state, onMounted, search }
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