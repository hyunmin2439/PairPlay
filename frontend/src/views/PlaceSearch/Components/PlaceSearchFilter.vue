<template>
  <div class="placeSearchFiltersTotalFrame">
    <div class="placeSearchFilters d-flex justify-content-between align-items-center">
      <!--  -->
      <!-- Default dropend button -->
      <!-- <div class="input-group serachbar">search</div> -->
      <div class="d-flex align-items-center col-lg-9 filters">
        <!-- 지역 -->
        <div class="btn placeSearchFiltersRegion btnPlace">
          <div class="dropdown">
            <div class="dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">지역</div>
            <ul class="dropdown-menu mt-2" aria-labelledby="dropdownMenuButton" style="height: 60vh;">
              <li class="sido">
                <a 
                  v-for="(sido, idx) in state.sidoList" :key="idx"
                  class="dropdown-item d-flex justify-content-between px-4"
                  @mouseover="changeSido($event)"
                >
                  <div>{{ sido }}</div>
                </a>

                <ul class="dropdown-menu dropdown-submenu px-2 text-align" @click="selectBusanGugun" style="width: 30vw; height: 60vh;">
                  <li 
                    v-for="(gungu, index) in state.gunguList[searchFiltersData.sido]" :key="index"
                    class="d-flex my-2"
                  >
                    <input 
                      v-for="(gu, ix) in gungu" :key="ix"
                      type="button"
                      class="dropdown-item ps-4" style="width: 25%;"
                      :value="gu"
                      @click="changeGungu($event)"
                    >
                  </li>
                  <div class="d-flex justify-content-center mt-1" style="position: absolute; bottom: 5%; left: 30%;">
                    <button type="button" class="btn btn-dark mb-4" @click="changeFilters">적용</button>
                    <button type="button" class="btn btn-outline-secondary mb-4 ms-5">취소</button>
                  </div>
                </ul>
              </li>
            </ul>
            
          </div>
        </div>
        <!-- 카테고리 -->
        <div class="btn-group">
          <button type="button" class="btn btnPlace dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">운동 종목</button>
          <ul class="dropdown-menu py-2" style="min-width: 18vw; height: auto; font-size: 14px; border-radius: 15px">
            <div v-for="(cate, idx) in state.category" :key="idx" class="d-flex justify-content-start m-3 ms-4 row">
              <div v-for="(ca, index) in cate" :key="index" class="form-check col-6">
                <input class="form-check-input" type="checkbox" name="checkbox" :id="ca" :value="ca" @click="inputCategory($event)" />
                <label class="form-check-label col-6" :for="ca">{{ ca }}</label>
              </div>
            </div>
            <hr style="border: 1px solid #fafafa" />
            <div class="d-flex justify-content-center mt-1">
              <button type="button" class="btn btn-dark mb-4" @click="changeFilters">적용</button>
              <button type="button" class="btn btn-outline-secondary mb-4 ms-5">취소</button>
            </div>
          </ul>
        </div>
        <!-- 날짜 -->
        <div>
          <Datepicker v-model="timeData" :format-locale="ko" format="PP" :enableTimePicker="false" range multiCalendars textInput class="ms-2 datePicker"></Datepicker>
        </div>
        <div class="btn btn-Cancel btn-secondary" type="button" @click="cancelFilters">초기화</div>
        <!-- {{ state.showMapMarker }} -->
        <!-- 지역 -->
      </div>
      <div class="col-lg-3 d-flex">
        <div class="input-group">
          <input
            v-model="searchFiltersData.searchWord"
            type="text"
            class="form-control serachbar"
            placeholder="&#xf52a; 시설명, 해시태그 입력"
            aria-label="Username"
            aria-describedby="addon-wrapping"
          />
        </div>
        <button style="width: 5vw; border: none; border-radius: 5px;" class="ms-4 btn-secondary" @click="changeFilters">검색</button>
      </div>
    </div>
  </div>
</template>

<script>
import Datepicker from "@vuepic/vue-datepicker";
import { ko } from "date-fns/locale";
import "@vuepic/vue-datepicker/dist/main.css";

import { reactive, onMounted, ref, watch, computed } from "vue";
import { useStore } from "vuex";

// import Slider from "@vueform/slider";

export default {
  name: "PlaceSearchFilter",
  // emits: ["searchFiltersData"],
  components: { Datepicker },
  setup() {
    const store = useStore();
    const state = reactive({
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
      category: [
        ['축구', '풋살'],
        ['농구', '야구'],
        ['볼링', '골프'], 
        ['테니스', '배드민턴'], 
        ['헬스', '필라테스'], 
        ['격투기', '수영']
      ],
      showMapMarker: computed(() => store.getters["root/showMapMarker"])
    })
    const timeData = ref({});

    let searchFiltersData = reactive({
      price: "",
      sido: "",
      gugun: "",
      startDate: "",
      endDate: "",
      categoryList: [],
      page: 0,
      searchWord: "",
    });

    onMounted (() => {
      const startDate = new Date();
      const endDate = new Date(new Date().setDate(startDate.getDate() + 7));
      timeData.value = [startDate, endDate]
      console.log(timeData)
    })

    watch(timeData, () => {
      let startTime = new Date(+timeData.value[0] + 3240 * 10000).toISOString().replace("T", " ").replace(/\..*/, "").substring(0, 10);
      let endTime = new Date(+timeData.value[0] + 3240 * 10000).toISOString().replace("T", " ").replace(/\..*/, "").substring(0, 10);
      searchFiltersData.startDate = startTime;
      searchFiltersData.endDate = endTime;
      changeFilters()
    });

    const changeSido = async function (event) {
      searchFiltersData.sido = event.target.textContent
    }

    const changeGungu = function (event) {
      searchFiltersData.gugun = event.target.value
    }

    const inputCategory = async function (event) {
      for (var i of document.getElementsByClassName("form-check-input")) {
        if (i.value == event.target.value) {
          continue
        } else {
          var tag = i
          tag.checked = false
        }
      }
      searchFiltersData.categoryList = [event.target.value]
    }

    const changeFilters = async function () {
      console.log(searchFiltersData);
      await store.dispatch("root/addPlaceFilters", searchFiltersData);
    };

    const cancelFilters = async function () {
      searchFiltersData.price = "";
      searchFiltersData.sido = "";
      searchFiltersData.gugun = "";
      searchFiltersData.startDate = "";
      searchFiltersData.endDate = "";
      searchFiltersData.categoryList = [];
      searchFiltersData.page = 0;
      searchFiltersData.page = 0;
      searchFiltersData.searchWord = "";
      await changeFilters()
    };

    return { ko, onMounted, state, timeData, searchFiltersData, changeSido, changeGungu, inputCategory, cancelFilters, changeFilters };
  },
};
</script>

<style src="@vueform/slider/themes/default.css"></style>

<style lang="scss" scoped>
// .dropdown-menu:checked {
//   display: block;
// }
.btn {
  &:hover {
    border: 0.02px solid rgba(1, 1, 1, 0.3);
  }
}
.placeSearchFiltersTotalFrame {
  border-bottom-width: 100vw;
  border-bottom: 1px solid rgba(1, 1, 1, 0.1);
}
.placeSearchFilters {
  // width: 100vw;
  margin: auto;
  padding: 0rem 0.5rem 1rem 0.5rem;
  max-width: 1400px;
}
.serachbar {
  margin: 0px 0px 0px 10px;
  // min-width: 200px;
  // max-width: 300px;
  background: #ffff;
  border-radius: 5px;
  box-shadow: 0 1px 10px rgba(24, 24, 24, 0.04);
  // box-shadow: (0 0 8px rgba(24, 24, 24, 0.04));
  border: 1px solid rgba(1, 1, 1, 0.1);
  font-size: 14px;
  font-family: "bootstrap-icons", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}
.btn-Cancel {
  margin: auto 0.5rem auto 0.8rem;
  // background: red;
  color: white;
  border-radius: 5px;
  // box-shadow: (0 1px 10px rgba(24, 24, 24, 0.04));
  box-shadow: (0 0 8px rgba(24, 24, 24, 0.04));
  border: 1px solid rgba(1, 1, 1, 0.1);
  font-size: 14px;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}
// .btnPlace {
//   margin: 10px 5px 10px 5px;
//   background: #ffff;
//   border-radius: 30px;
//   box-shadow: (0 1px 10px rgba(24, 24, 24, 0.04));
//   // box-shadow: (0 0 8px rgba(24, 24, 24, 0.04));
//   border: 1px solid rgba(1, 1, 1, 0.1);
//   font-size: 14px;
//   font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
// }
</style>
