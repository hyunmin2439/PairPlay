<template>
  <div>
    <div style="max-width: 1255px; margin: auto;">
      <Header></Header>
    </div>
    <hr style="margin-top: 0px; margin-bottom: 0px; color: #b7b7b7;">
    <div style="max-width: 1200px; margin: auto;">
      <div class="PlaceBackgroundImage">
        <div class="PlaceBackgroundImageCover">
          <div class="PlaceBackground-Content ms-5 ps-5">
            <p>
              당신과 함께할 <br />
              메이트 공고를 등록하세요.
            </p>
          </div>
        </div>
      </div>
    </div>
    <div style="max-width: 1200px; margin: auto; margin-bottom: 5%;">
      <div class="row my-5">
        <div class="col-6 d-flex align-items-center">
          <span class="me-5">주소</span>
          <div>
            <div class="btn-group" style="position:relative; right: 0%;">
              <button type="button" class="region-btn me-4" data-bs-toggle="dropdown" aria-expanded="false">{{ state.sidoShow }} ▼</button>
              <ul class="dropdown-menu py-2" style="height: auto; font-size: medium; border-radius: 15px">
                <div v-for="(si, idx) in state.sidoList" :key="idx" class="my-3" style="text-align: center;">
                  <button class="age-btn-detail" :id="idx" @click="checkingSido(idx)">{{ si }}</button>
                </div>
              </ul>
            </div>
            <div class="btn-group" style="position:relative; right: 0%;">
              <button type="button" class="region-btn" data-bs-toggle="dropdown" aria-expanded="false">{{ state.gunguShow }} ▼</button>
              <ul class="dropdown-menu py-2" style="width: 20vw; height: auto; font-size: medium; border-radius: 15px">
                <div v-for="(gugu, idx) in state.gunguSub" :key="idx" class="my-3 d-flex" style="text-align: center;">
                  <div v-for="(gu, index) in gugu" :key="index" class="col-3">
                    <button class="age-btn-detail" @click="checkingGungu($event)">{{ gu }}</button>
                  </div>
                </div>
              </ul>
            </div>
          </div>
        </div>
        <div class="col-6 d-flex align-items-center">
          <span style="width: 10vw;">성별 및 연령</span>
          <div class="d-flex justify-content-between" style="width: 100%;">
            <div class="d-flex">
              <div v-for="(gen, idx) in state.genderList" :key="idx" class="mx-2">
                <button class="gender-btn" :id="idx" @click="chekingGender(idx)">{{ gen }}</button>
              </div>
            </div>
            <div class="btn-group" style="position:relative; right: 0%;">
              <button type="button" class="age-btn" data-bs-toggle="dropdown" aria-expanded="false">{{ state.ageShow }} ▼</button>
              <ul class="dropdown-menu py-2" style="height: auto; font-size: medium; border-radius: 15px">
                <div v-for="(ag, idx) in state.ageList" :key="idx" class="my-3" style="text-align: center;">
                  <button class="age-btn-detail" :id="idx" @click="checkingAge(idx)">{{ ag }}</button>
                </div>
              </ul>
            </div>
          </div>
        </div>
      </div>
      <hr>
      <div class="row my-5 align-items-center">
        <div class="col-1">
          <span>카테고리</span>
        </div>
        <div class="col-11 d-flex">
          <div v-for="(cat, idx) in state.category" :key="idx" class="mx-2">
            <button class="category-btn" :id="idx" @click="chekingCategory(idx)">{{ cat }}</button>
          </div>
        </div>
      </div>
      <hr>
      <div class="row my-5 align-items-center">
        <div class="col-2">
          <span>요일 및 시간</span>
        </div>
        <div class="col-10 d-flex align-items-center">
          <Datepicker
            format="YYYY-MM-DD"
            style="width: 100%; border-radius: 20px; text-align: center; padding-top: 7px; padding-bottom: 7px; border: 1px solid #c8c6c6; font-weight: bold;"
            v-model="state.meetDt"
            ref="datepicker"
            placeholder="운동 날짜 선택"
          />
          <div class="btn-group ms-5">
            <button type="button" class="age-btn" data-bs-toggle="dropdown" aria-expanded="false">{{ state.timeShow }} ▼</button>
            <ul class="dropdown-menu py-2" style="height: auto; font-size: medium; border-radius: 15px">
              <div v-for="(time, idx) in state.timeList" :key="idx" class="my-2" style="text-align: center;">
                <button class="age-btn-detail" :id="idx" @click="checkingTime(idx)">{{ time }}</button>
              </div>
            </ul>
          </div>
        </div>
      </div>
      <hr>
      <div class="row my-5 align-items-center">
        <div class="col-2">
          <span>공고 종료일</span>
        </div>
        <div class="col-10 d-flex">
          <Datepicker
            format="YYYY-MM-DD"
            style="width: 100%; border-radius: 20px; text-align: center; padding-top: 7px; padding-bottom: 7px; border: 1px solid #c8c6c6; font-weight: bold;"
            v-model="state.closeDt"
            ref="datepicker"
            placeholder="종료 날짜 선택"
          />
        </div>
      </div>
      <hr>
      <div class="row my-5 align-items-center">
        <div class="col-1">
          <span>제목</span>
        </div>
        <div class="col-11">
          <div class="title" style="text-align: center;">
            <input type="text" class="title-input" v-model="state.title">
          </div>
        </div>
      </div>
      <hr>
      <div class="row my-5">
        <div class="col-1">
          <span>내용</span>
        </div>
        <div class="col-11">
          <div class="contents" style="text-align: center;">
            <textarea class="contents-input" name="" id="" cols="30" rows="10" v-model="state.description"></textarea>
          </div>
        </div>
      </div>
      <br><br>
      <center><button style="margin: auto;" class="mate-apply-btn" @click="mateCreate">메이트 등록하기</button></center>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import Header from "../../Common/Header.vue";
import Footer from "../../Common/Footer.vue";
import { reactive } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import Swal from 'sweetalert2'

export default {
  name: "MateCreate",
  components: {
    Header, 
    Footer 
  },
  setup () {
    const store = useStore()
    const router = useRouter()
    // const Swal = require('sweetalert2')
    const state = reactive({
      category: [
        "축구", "풋살", "농구", "야구",
        "볼링", "골프", "테니스", "배드민턴",
        "헬스", "필라테스", "격투기", "수영"
      ],
      genderList: ["남성", "여성", "상관없음"],
      ageList: ["연령 무관", "10세 미만", "10대", "20대", "30대", "40대", "50대", "60대 이상"],
      sidoList: ["부산", "서울", "경기", "강원", "경남", "경북", "충남", "충북", "전남", "전북"],
      gunguList: {
        "부산": [["중구", "서구", "동구", "영도구"], ["부산진구", "동래구", "남구", "북구"], ["해운대구", "사하구", "금정구", "강서구"], ["연제구", "수영구", "사상구", "기장군"]],
        "서울": [["강남구", "강동구", "강북구", "강서구"], ["관악구", "광진구", "구로구", "금천구"], ["노원구","도봉구", "동대문구", "동작구"], ["마포구", "서대문구", "서초구", "성동구"], ["성북구", "송파구", "양천구", "영등포구"], ["용산구", "은평구", "종로구", "중구"], ["중랑구"]],
        "경기": [["수원시", "용인시", "성남시", "부천시"], ["화성시", "안산시", "안양시", "평택시"], ["시흥시","김포시", "광주시", "광명시"], ["군포시", "하남시", "오산시", "이천시"], ["안성시", "의왕시", "양평군", "여주시"], ["과천시", "경기북부", "고양시", "남양주시"], ["파주시", "의정부시", "양주시", "구리시"], ["포천시", "동두천시", "가평군", "연천군"]], 
        "강원": [["춘천시", "원주시", "강릉시", "동해시"], ["태백시", "속초시", "삼척시", "홍청군"], ["횡성군","영월군", "평창군", "정선군"], ["철원군", "화천군", "양구군", "인제군"], ["고성군", "양양군"]], 
        "경남": [["창원시", "진주시", "통영시", "사천시"], ["김해시", "밀양시", "거제시", "양산시"], ["의령군","함안군", "창녕군", "고성군"], ["남해군", "하동군", "산청군", "함양군"], ["거창군", "합천군"]], 
        "경북": [["포항시", "경주시", "김천시", "안동시"], ["구미시", "영주시", "영천시", "상주시"], ["문경시","경산시", "군위군", "의성군"], ["청송군", "영양군", "영덕군", "청도군"], ["고령군", "성주군", "칠곡군", "예천군"], ["봉화군", "울진군", "울릉군"]], 
        "충남": [["천안시", "공주시", "보령시", "아산시"], ["서산시", "논산시", "계룡시", "당진시"], ["금산군","부여군", "서천군", "청양군"], ["홍성군", "예산군", "태안군"]], 
        "충북": [["충주시", "제천시", "보은군", "옥천군"], ["영동군", "증평군", "진천군", "괴산군"], ["음성군","단양군"]], 
        "전남": [["목포시", "여수시", "순천시", "나주시"], ["광양시", "담양군", "곡성군", "구례군"], ["고흥군","보성군", "화순군", "장흥군"], ["강진군", "해남군", "영암군", "무안군"], ["함평군", "영광군", "장성군", "완도군"], ["진도군", "신안군"]], 
        "전북": [["전주시", "익산시", "군산시", "정읍시"], ["김제시", "남원시", "완주군", "고창군"], ["부안군","임실군", "순창군", "진안군"], ["무주군", "장수군"]]
      },
      timeList: ["08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00"],
      gunguSub: [],
      sido: "",
      sidoShow: "시도",
      gungu: "",
      gunguShow: "군구",
      categoryId: -1,
      gender: -1,
      ageShow: "연령대",
      age: -1,
      title: "",
      description: "",
      meetDt: "",
      closeDt: "",
      time: "",
      timeShow: "시간",
    })

    const chekingCategory = function (idx) {
      for (var i=0; i < 12; i++) {
        var cateBtn = document.getElementsByClassName("category-btn")[i]
        cateBtn.classList.remove("select")
      }
      var activeBtn = document.getElementsByClassName("category-btn")[idx]
      activeBtn.classList.add("select")
      state.categoryId = idx+1
    }

    const chekingGender = function (idx) {
      for (var i=0; i < 3; i++) {
        var cateBtn = document.getElementsByClassName("gender-btn")[i]
        cateBtn.classList.remove("select")
      }
      var activeBtn = document.getElementsByClassName("gender-btn")[idx]
      activeBtn.classList.add("select")
      state.gender = Number(idx)
    }

    const checkingAge = function (idx) {
      state.ageShow = state.ageList[idx]
      if (idx == 0) {
        state.age = 0
      } else if (idx == 1) {
        state.age = 1
      } else if (idx == 2) {
        state.age = 10
      } else if (idx == 3) {
        state.age = 20
      } else if (idx == 4) {
        state.age = 30
      } else if (idx == 5) {
        state.age = 40
      } else if (idx == 6) {
        state.age = 50
      } else {
        state.age = 60
      }
    }

    const checkingSido = function (idx) {
      state.gunguSub = state.gunguList[state.sidoList[idx]]
      state.sido = state.sidoList[idx]
      state.sidoShow = state.sidoList[idx]
      state.gunguShow = "군구"
    }

    const checkingGungu = function (event) {
      state.gungu = event.target.textContent
      state.gunguShow = event.target.textContent
    }

    const checkingTime = function (idx) {
      state.timeShow = state.timeList[idx]
      state.time = state.timeList[idx]
    }

    const mateCreate = async function () {
      if (state.sido!="" & state.gungu!="" & state.gender!=-1 & state.age!=-1 & state.categoryId!=-1 & state.meetDt!="" & state.closeDt!="" & state.time!="" & state.title!="" & state.description!="") {
        const meetDt = new Date(state.meetDt.getTime() - (state.meetDt.getTimezoneOffset() * 60000)).toISOString().substr(0, 10)
        const closeDt = new Date(state.closeDt.getTime() - (state.closeDt.getTimezoneOffset() * 60000)).toISOString().substr(0, 10)
        let body = {
          'sido': state.sido,
          'gugun': state.gungu,
          'categoryId': state.categoryId,
          'meetDt': meetDt+"T"+state.time+":00.000000",
          'closeDt': closeDt+"T00:00:00.000000",
          'title': state.title,
          'description': state.description,
          'age': state.age,
          'gender': state.gender,
        }
        await store.dispatch("root/mateCreate", body)
        Swal.fire({
          icon: 'success',
          title: '성공!!',
          text: '메이트 등록에 성공했습니다.',
        })
        await router.push({
          name: "Mate"
        })

      } else if (state.sido=="") {
        Swal.fire({
          icon: 'error',
          title: '실패...',
          text: '시도를 선택해주세요.',
        })
      } else if (state.gungu=="") {
        Swal.fire({
          icon: 'error',
          title: '실패...',
          text: '군구를 선택해주세요.',
        })
      } else if (state.gender==-1) {
        Swal.fire({
          icon: 'error',
          title: '실패...',
          text: '성별을 선택해주세요.',
        })
      } else if (state.age==-1) {
        Swal.fire({
          icon: 'error',
          title: '실패...',
          text: '연령대를 선택해주세요.',
        })
      } else if (state.categoryId==-1) {
        Swal.fire({
          icon: 'error',
          title: '실패...',
          text: '운동 종류를 선택해주세요.',
        })
      } else if (state.meetDt=="") {
        Swal.fire({
          icon: 'error',
          title: '실패...',
          text: '운동할 날짜를 선택해주세요.',
        })
      } else if (state.time=="") {
        Swal.fire({
          icon: 'error',
          title: '실패...',
          text: '운동할 시간을 선택해주세요.',
        })
      } else if (state.closeDt=="") {
        Swal.fire({
          icon: 'error',
          title: '실패...',
          text: '공고를 종료할 날짜를 선택해주세요.',
        })
      } else if (state.title=="") {
        Swal.fire({
          icon: 'error',
          title: '실패...',
          text: '공고 제목을 기입해주세요.',
        })
      } else if (state.description=="") {
        Swal.fire({
          icon: 'error',
          title: '실패...',
          text: '공고 내용을 기입해주세요.',
        })
      }
    }

    return { state, chekingCategory, chekingGender, checkingAge, checkingSido, checkingGungu, checkingTime, mateCreate }
  }
}
</script>


<style lang="scss" scoped>
/* header { */
/* background-color: wheat; */
/* width: 100%; */
/* } */
.PlaceBackgroundImage {
  width: 100%;
  height: 300px;
  background: black;
  margin-bottom: 5rem;
  /* background-color: rgba(0, 0, 0, 0.4); */
  background: url("@/assets/images/Main/Mate3.jpeg") 50% 40% no-repeat;
  background-size: 100% auto;
}
.PlaceBackgroundImageCover {
  position: absolute;
  width: 1200px;
  height: 300px;
  background: linear-gradient(30deg, rgba(0, 0, 0, 0.7) 0%, rgba(0, 0, 0, 0) 100%);
  /* background-color: rgba(0, 0, 0, 0.3); */
  z-index: 1;
  color: white;
}
.PlaceBackground-Content {
  position: absolute;
  top: 40%;
  /* max-width: 968px; */
  font-size: calc(1em + 1.5vw);
  /* transform: translate(-50%, -50%); */
  z-index: 2;
  text-align: left;
  font-weight: bold;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}
.container {
  width: 1200px;
  height: 100%;
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  // align-items: left;
  justify-content: center;
  // justify-content: left;
  // margin-left: 0;
  // margin-right: 0;
}

span {
  font-size: large;
  font-weight: bold;
}

.category-btn {
  font-size: medium;
  font-weight: bold;
  background-color: white;
  border: 1px solid #c8c6c6;
  padding-left: 16px;
  padding-right: 16px;
  padding-top: 7px;
  padding-bottom: 7px;
  border-radius: 20px;
}

.select {
  background-color: grey !important;
  color: white;
}

.gender-btn, .age-btn, .region-btn {
  font-size: medium;
  font-weight: bold;
  background-color: white;
  border: 1px solid #c8c6c6;
  padding-left: 16px;
  padding-right: 16px;
  padding-top: 7px;
  padding-bottom: 7px;
  border-radius: 20px;
}

.age-btn-detail {
  border: none;
  background-color: white;
}

.title {
  width: 100%;
  border: 1px solid #c8c6c6;
  border-radius: 20px;
  padding-top: 7px;
  padding-bottom: 7px;
}

.title-input {
  width: 98%;
  border: none;
}

.title-input:focus {
  outline: none;
}

.contents {
  width: 100%;
  border: 1px solid #c8c6c6;
  border-radius: 20px;
}

.contents-input {
  width: 95%;
  border: none;
  resize: none;
}

.contents-input:focus {
  outline: none;
}

.mate-apply-btn {
  font-size: large;
  font-weight: bold;
  background-color: black;
  color: white;
  border: 1px solid #c8c6c6;
  padding-left: 16px;
  padding-right: 16px;
  padding-top: 7px;
  padding-bottom: 7px;
  border-radius: 20px;
}

</style>