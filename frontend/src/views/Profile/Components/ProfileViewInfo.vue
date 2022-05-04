<template>
  <h2>
    <b>소개</b>
    <hr>
  </h2>
  <div class="row">
    <div class="col-2 pt-4">이메일</div>
    <div class="col-1"></div>
    <div class="col-9 pt-4">
      <div class="d-flex justify-content-between" v-if="state.isEmail==false">
        <span>{{ state.otherInfo.email }}</span>
      </div>
      <hr v-if="state.userInfo.memberId == state.otherInfo.memberId" style="margin-top: 5%;">
      <hr v-else>
    </div>
  </div>
  <div class="row" v-if="state.userInfo.memberId == state.otherInfo.memberId">
    <div class="col-2 pt-4">비밀번호</div>
    <div class="col-1"></div>
    <div class="col-9 pt-4">
      <span><b>비밀번호 재설정이 가능합니다.</b></span><br>
      <span>영문과 숫자, 특수문자를 포함한 8자리 이상 18자리 이하</span><br>
      <button class="mt-3" style="border-radius: 15px;" data-bs-toggle="modal" data-bs-target="#staticBackdrop">비밀번호 재설정</button>
      <!-- Modal -->
      <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content" style="border: none;">
            <div class="modal-header">
              <h5 class="modal-title" id="staticBackdropLabel"><b>비밀번호 재설정</b></h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <div class="mb-5 mt-3">
                <h5>현재 비밀번호</h5>
                <input class="input-modal" type="password" style="width: 100%;" v-model="state.password" @keyup="checkPasswordLength(1)">
                <p style="float: right;">{{ state.passwordLength }} / 20</p>
              </div>
              <div class="my-2">
                <h5>새로운 비밀번호</h5>
                <input class="input-modal" type="password" style="width: 100%;" v-model="state.newPassword" @keyup="checkPasswordLength(2)">
                <p style="float: right;">{{ state.newPasswordLength }} / 20</p>
              </div>
              <div class="my-4">
                <h5>새로운 비밀번호 확인</h5>
                <input class="input-modal" type="password" style="width: 100%;" v-model="state.newPasswordConfirm" @keyup="checkPasswordLength(3)">
                <p style="float: right;">{{ state.newPasswordConfirmLength }} / 20</p>
              </div>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
              <button type="button" class="btn" style="background-color: skyblue;">Understood</button>
            </div>
          </div>
        </div>
      </div>
      <hr style="margin-top: 5%;">
    </div>
  </div>
  <div class="row">
    <div class="col-2 pt-4">닉네임</div>
    <div class="col-1"></div>
    <div class="col-9 pt-4">
      <div class="d-flex justify-content-between" v-if="state.isNickname==false">
        <span>{{ state.otherInfo.nickname }}</span>
        <div v-if="state.userInfo.memberId == state.otherInfo.memberId" class="edit" id="nickname" @click="changeIsInfo($event)"></div>
      </div>
      <div class="d-flex justify-content-between" v-if="state.isNickname==true">
        <input type="text" v-model="state.otherInfo.nickname" style="width: 70%;">
        <button class="btn-edit" id="nick_dup" @click="checkDuplicate">중복 확인</button>
        <div class="edit mt-2" id="nickname" @click="changeNickname"></div>
      </div>
      <hr>
    </div>
  </div>
  <div class="row">
    <div class="col-2 pt-4">이름</div>
    <div class="col-1"></div>
    <div class="col-9 pt-4">
      <div class="d-flex justify-content-between" v-if="state.isName==false">
        <span>{{ state.otherInfo.name }}</span>
        <div v-if="state.userInfo.memberId == state.otherInfo.memberId" class="edit" id="name" @click="changeIsInfo($event)"></div>
      </div>
      <div class="d-flex justify-content-between" v-if="state.isName==true">
        <input type="text" v-model="state.otherInfo.name" style="width: 80%;">
        <div class="edit mt-2" id="name" @click="changeName"></div>
      </div>
      <hr>
    </div>
  </div>
  <div class="row">
    <div class="col-2 pt-4">폰 번호</div>
    <div class="col-1"></div>
    <div class="col-9 pt-4">
      <div class="d-flex justify-content-between" v-if="state.isPhone==false">
        <span>{{ state.otherInfo.phone }}</span>
        <div v-if="state.userInfo.memberId == state.otherInfo.memberId" class="edit" id="phone" @click="changeIsInfo($event)"></div>
      </div>
      <div class="d-flex justify-content-between" v-if="state.isPhone==true">
        <input type="text" v-model="state.otherInfo.phone" style="width: 80%;" @keyup="autoHypen">
        <div class="edit mt-2" id="phone" @click="changePhone"></div>
      </div>
      <hr>
    </div>
  </div>
  <div class="row">
    <div class="col-2 pt-4">생년월일</div>
    <div class="col-1"></div>
    <div class="col-9 pt-4">
      <div class="d-flex justify-content-between" v-if="state.isBirthDt==false">
        <span>{{ state.otherInfo.birthDt[0] }}년 {{ state.otherInfo.birthDt[1] }}월 {{ state.otherInfo.birthDt[2] }}일</span>
        <div v-if="state.userInfo.memberId == state.otherInfo.memberId" class="edit" id="birthDt" @click="changeIsInfo($event)"></div>
      </div>
      <div class="d-flex justify-content-between" v-if="state.isBirthDt==true">
        <!-- <input type="text" v-model="state.otherInfo.birthDt" style="width: 80%;"> -->
        <Datepicker
          format="YYYY-MM-DD"
          style="width: 100%; padding-left: 1%; position: relative; right: 2%;"
          v-model="state.otherInfo.birthDt"
          ref="datepicker"
          placeholder=state.otherInfo.birthDt
        />
        <div class="edit" id="birthDt" @click="changeBirthDt"></div>
      </div>
      <hr>
    </div>
  </div>
  <div class="row">
    <div class="col-2 pt-4">주소</div>
    <div class="col-1"></div>
    <div class="col-9 pt-4">
      <div class="d-flex justify-content-between" v-if="state.isAddress==false">
        <span>{{ state.otherInfo.address }}</span>
        <div v-if="state.userInfo.memberId == state.otherInfo.memberId" class="edit" id="address" @click="changeIsInfo($event)"></div>
      </div>
      <div class="d-flex justify-content-between" v-if="state.isAddress==true">
        <input type="text" id="address" @click="search()" style="width: 80%;" v-model="state.otherInfo.address">
        <div class="edit mt-2" id="address" @click="changeAddress"></div>
      </div>
      <hr>
    </div>
  </div>
  <div class="row">
    <div class="col-2 pt-4">성별</div>
    <div class="col-1"></div>
    <div class="col-9 pt-4">
      <div class="d-flex justify-content-between" v-if="state.isGender==false">
        <span>{{ state.otherInfo.gender }}</span>
        <div v-if="state.userInfo.memberId == state.otherInfo.memberId" class="edit" id="gender" @click="changeIsInfo($event)"></div>
      </div>
      <div class="d-flex justify-content-between" v-if="state.isGender==true">
        <!-- <input type="text" id="address" @click="search()" style="width: 80%;" v-model="state.otherInfo.address"> -->
        <div>
          <input class="mx-3" type="radio" name="gender" value="man" @click="changeGenderStatus($event)">남성
          <input class="ms-5 mx-3" type="radio" name="gender" value="woman" @click="changeGenderStatus($event)">여성
        </div>
        <div class="edit mt-2" id="gender" @click="changeGender"></div>
      </div>
      <hr>
    </div>
  </div>
</template>

<script>
import { reactive } from 'vue'
import { useStore } from 'vuex'
export default {
  name: "ProfileViewInfo",
  props: {
    userInfo: Object,
    otherInfo: Object,
  },
  setup (props) {
    const store = useStore()
    const state = reactive({
      userInfo: props.userInfo,
      otherInfo: props.otherInfo,
      password: '',
      newPassword: '',
      newPasswordConfirm: '',
      passwordLength: 0,
      newPasswordLength: 0,
      newPasswordConfirmLength: 0,
      isNickname: false,
      isName: false,
      isEmail: false,
      isPhone: false,
      isBirthDt: false,
      isAddress: false,
      isGender: false,
    })

    const changeIsInfo = function (event) {
      state.isNickname = false
      state.isName = false
      state.isEmail = false
      state.isPhone = false
      state.isBirthDt = false
      state.isAddress = false
      state.isGender = false
      if (event.target.id == 'nickname') {
        state.isNickname = true
      } else if (event.target.id == 'name') {
        state.isName = true
      } else if (event.target.id == 'email') {
        state.isEmail = true
      } else if (event.target.id == 'phone') {
        state.isPhone = true
      } else if (event.target.id == 'birthDt') {
        state.isBirthDt = true
      } else if (event.target.id == 'address') {
        state.isAddress = true
      } else {
        state.isGender = true
      }
      console.log(state)
    }

    const checkPasswordLength = function (num) {
      if (num==1) {
        state.passwordLength = state.password.length
      } else if (num==2) {
        state.newPasswordLength = state.newPassword.length
      } else {
        state.newPasswordConfirmLength = state.newPasswordConfirm.length
      }
    }

    const autoHypen = function () {
      state.otherInfo.phone = state.otherInfo.phone
        .replace(/[^0-9]/g, '')
        .replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, "$1-$2-$3").replace(/(-{1,2})$/g, "");
    }

    const checkDuplicate = async function() {
      await store.dispatch("root/signupDuplicateNickname", {"value": state.otherInfo.nickname})
    }

    const changeNickname = async function () {
      await store.dispatch("root/profileChangeInfo",{
        'nickname': state.otherInfo.nickname
      })
      state.isNickname = false
    }

    const changeName = async function () {
      await store.dispatch("root/profileChangeInfo",{
        'name': state.otherInfo.name
      })
      state.isName = false
    }

    const changePhone = async function () {
      await store.dispatch("root/profileChangeInfo",{
        'phone': state.otherInfo.phone
      })
      state.isPhone = false
    }

    const changeBirthDt = async function () {
      const birthDt= new Date(state.otherInfo.birthDt.getTime() - (state.otherInfo.birthDt.getTimezoneOffset() * 60000)).toISOString().substr(0, 10)
      await store.dispatch("root/profileChangeInfo",{
        'birthDt': birthDt
      })
      state.isBirthDt = false
    }

    const changeAddress = async function () {
      await store.dispatch("root/profileChangeInfo",{
        'address': state.otherInfo.address
      })
      state.isAddress = false
    }

    const changeGenderStatus = function(value) {
      if (value.target.value==="man") {
        state.otherInfo.gender = 0
      } else {
        state.otherInfo.gender = 1
      }
    }

    const changeGender = async function () {
      await store.dispatch("root/profileChangeInfo",{
        'gender': state.otherInfo.gender
      })
      state.isGender = false
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
          state.otherInfo.address = roadAddr
        }
      }).open({
        left: window.screenLeft/2,
        right: window.screenTop/2,
        popupKey: 'popup1',
        autoClose: true,
      });
    }

    return { state, changeIsInfo, checkPasswordLength, autoHypen, checkDuplicate, changeNickname, changeName, changePhone, changeBirthDt, changeAddress, changeGenderStatus, changeGender, search }
  }
}
</script>

<style scoped lang="scss">
.edit {
  width: 2vw;
  height: 4vh;
  background-image: url("@/assets/images/Profile/Edit.png");
  background-size: 80%;
  background-repeat: no-repeat;
  cursor: pointer;
}

.btn-edit {
  font-size: large;
  float: right;
  border-radius: 15px;
}

.input-modal {
  border: none;
  border-bottom: 1px solid black;
}

.input-modal:focus {
  outline: none;
}
</style>