<template>
  <div>
    <div style="max-width: 1400px; margin: auto">
      <Header></Header>
    </div>
    <div class="container">
      <div class="imageBox">
        <img class="imageFrist col-6" v-if="placeInfos.imgUrl[0]" :src="`${placeInfos.imgUrl[0]}`" alt="" />
        <img v-for="(placeInfoImage, idx) in placeInfos.imgUrl.slice(1)" :key="idx" :src="`${placeInfoImage}`" class="imageOthers col-3" alt="" />
        <!-- <img :src="`${placeInfos.imgUrl[0]}`" alt="" /> -->
      </div>
      <div class="importantInfos mt-4">
        <div>
          <h4 style="color: #767676">{{ placeInfos.position }}</h4>
          <h2>{{ placeInfos.title }}</h2>
          <div class="d-flex align-items-end mt-3">
            <h5 class="mx-4 fw-bold">{{ placeInfos.rate }} <i class="bi bi-star-fill mx-1" style="color: #fe8a01"></i></h5>
            <h5 style="color: #767676">
              <u>{{ placeInfos.reviewsCount }}개 리뷰</u>
            </h5>
            <!-- <h4><i class="bi bi-heart-fill me-1" style="color: #ff385c"></i></h4> -->
            <h5><i class="bi bi-heart me-1 ms-4"></i></h5>
            <h5 class="ms-1 fw-bold"><u>저장</u></h5>
          </div>
        </div>
        <div class="placeDetailSportsCategory btnCategory">
          <img src="https://cdn-icons-png.flaticon.com/512/921/921285.png" style="width: 2.5rem" alt="" />
          <h4 class="ms-4 align-self-center">{{ placeInfos.category }}</h4>
        </div>
      </div>
      <hr class="class-1" />
      <!-- 상세정보들 -->
      <div class="detailInfos d-flex justify-content-between">
        <div class="detailInfosLeft col-7">
          <div>
            <h4 class="pb-4">이용시간 및 가격</h4>
            <p v-for="(각시간, idx) in placeInfos.이용시간" :key="idx" class="mx-3">{{ 각시간 }}</p>
            <!-- <p></p> -->
          </div>
          <hr class="class-2" />
          <h4 class="pb-4">시설정보</h4>
          <p v-for="(각시간, idx) in placeInfos.이용시간" :key="idx" class="mx-3">{{ 각시간 }}</p>
        </div>
        <div class="detailInfosRight col-5">
          <h4 class="ps-3 pt-2">예약조회</h4>
          <place-detail-reservation></place-detail-reservation>
          <div class="ReservationRealTime">
            <div style="width: 500px; background: green">아아</div>
            <div style="width: 500px; background: green">아아</div>
            <div style="width: 500px; background: green">아아</div>
          </div>
          <div class="d-flex justify-content-center mt-3">
            <div class="btn btn-primary me-3">예약</div>
            <div class="btn btn-secondary ms-3">취소</div>
          </div>
        </div>
      </div>
      <div>
        <!-- <place-detail-maps></place-detail-maps> -->
      </div>
      <hr class="class-1" style="margin: 2rem 0 2rem 0" />
      <h4 class="pb-4">시설위치</h4>

      <div><PlaceDetailMaps></PlaceDetailMaps></div>
      <hr class="class-1" style="margin: 2rem 0 2rem 0" />
      <h4 class="detailInfosRule">이용규칙</h4>
      <p style="white-space: pre-line; line-height: 2rem">
        {{ rules }}
      </p>
      <hr class="class-1" />
      <div class="reviewTotalFrame">
        <h4 class="mb-4">리 뷰</h4>
        <div class="totalReviews d-flex align-items-center">
          <h4 class="mx-4 fw-bold">{{ placeInfos.rate }} <i class="bi bi-star-fill mx-1" style="color: #fe8a01"></i></h4>
          <h5 class="fw-bold">5개 리뷰</h5>
        </div>
        <div class="writeReview"></div>
        <div class=""></div>
      </div>
      <hr class="class-1" />
      <div class="refundPolicy">환불 및 변경 정책</div>
      <hr class="class-1" />
    </div>
    <Footer></Footer>
  </div>
  <!-- <div class="dropwdonwTotal">
    <div class="dropdown dropdownsmenus">
      <button class="dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">Dropdown button</button>
      <ul class="dropdown-menu mt-2" aria-labelledby="dropdownMenuButton">
        <li>
          <a class="dropdown-item d-flex justify-content-between px-4" href="#"><span>부산</span> <span>></span></a>

          <ul class="dropdown-menu dropdown-submenu px-2 text-align">
            <li class="d-flex justify-content-around text-start">
              <div class="form-check">
                <input class="form-check-input" type="checkbox" value="" id="flexCheckIndeterminate" />
                <label class="form-check-label" for="flexCheckIndeterminate"> 중구 </label>
              </div>
              <div class="form-check">
                <input class="form-check-input" type="checkbox" value="" id="flexCheckIndeterminate" />
                <label class="form-check-label" for="flexCheckIndeterminate"> 서구 </label>
              </div>
            </li>
            <li class="d-flex justify-content-around text-start">
              <a class="dropdown-item" href="#">동구</a>
              <a class="dropdown-item" href="#">영도구</a>
            </li>

            <li class="d-flex justify-content-around text-start">
              <a class="dropdown-item" href="#">진구</a>
              <a class="dropdown-item" href="#">동래구</a>
            </li>
            <li class="d-flex justify-content-around text-start">
              <a class="dropdown-item" href="#">남구</a>
              <a class="dropdown-item" href="#">북구구</a>
            </li>
            <li class="d-flex justify-content-around text-start">
              <a class="dropdown-item" href="#">해운대구</a>
              <a class="dropdown-item" href="#">사하구</a>
            </li>

            <li class="d-flex justify-content-around text-start">
              <a class="dropdown-item" href="#">금정구</a>
              <a class="dropdown-item" href="#">강서구</a>
            </li>
            <li class="d-flex justify-content-around text-start">
              <a class="dropdown-item" href="#">연제구</a>
              <a class="dropdown-item" href="#">연제구</a>
            </li>
            <li class="d-flex justify-content-around text-start">
              <a class="dropdown-item" href="#">수영구</a>
              <a class="dropdown-item" href="#">사상구</a>
            </li>
            <li class="d-flex"><a class="dropdown-item" href="#">기장군</a></li>
            <li><hr class="dropdown-divider" /></li>
            <div>
              <button>아아</button>
              <button>아아</button>
            </div>
          </ul>
        </li>
        <li>
          <a class="dropdown-item d-flex justify-content-between px-4" href="#"><span>부산2</span> <span>&gt;</span></a>
          <ul class="dropdown-menu dropdown-submenu">
            <li>
              <a class="dropdown-item" href="#">Submenu item 1</a>
            </li>
            <li>
              <a class="dropdown-item" href="#">Submenu item 2</a>
            </li>
            <li>
              <a class="dropdown-item d-flex justify-content-between px-4" href="#"><span>부산</span> <span>&gt;</span></a>
              <ul class="dropdown-menu dropdown-submenu">
                <li>
                  <a class="dropdown-item" href="#">Multi level 1</a>
                </li>
                <li>
                  <a class="dropdown-item" href="#">Multi level 2</a>
                </li>
              </ul>
            </li>
            <li>
              <a class="dropdown-item" href="#">Submenu item 4</a>
            </li>
            <li>
              <a class="dropdown-item" href="#">Submenu item 5</a>
            </li>
          </ul>
        </li>
      </ul>
    </div>
  </div> -->
</template>

<script>
import Header from "../Common/Header.vue";
import Footer from "../Common/Footer.vue";
import PlaceDetailMaps from "./Components/PlaceDetailMaps.vue";
import PlaceDetailReservation from "./Components/PlaceDetailReservation.vue";

import { ref } from "vue";
// import {useRoute} from "vue-router"
// import {mapState, useStore} from "vuex"

export default {
  name: "PlaceDetail",
  components: { Header, Footer, PlaceDetailReservation, PlaceDetailMaps },

  setup() {
    const placeInfos = ref({
      title: "부산시 사직 실내체육관 농구코트",
      position: "부산 연제구",
      imgUrl: [
        "https://as1.ftcdn.net/v2/jpg/02/17/45/12/1000_F_217451286_ixsvEptyrSYvxBvcyEGWKAVZUxFrayJ9.jpg",
        "https://as2.ftcdn.net/v2/jpg/01/81/20/87/1000_F_181208786_UvD9jXyBbIR4Pb1UIN6LQDk3vOChsmyC.jpg",
        "https://as1.ftcdn.net/v2/jpg/04/95/38/22/1000_F_495382282_buPZ5PEupMPyv1DzNnzKMtXdr4gDBVCC.jpg",
      ],
      facility: "잔디, 바닥, 샤워",
      rate: "3.9",
      reviewsCount: 98,
      category: "농구",
      cost: ["1개월 99,000", "2개월 177,000", "3개월 237,000"],
      이용시간: ["평일 17:30~22:00 OPEN", "평일 18:00~19:00 레슨", "평일 19:00~20:00 레슨", "평일 20:30~21:30 (금요일 20:30~22:00)"],
    });
    const rules =
      "▶️ 체육시설 예약시간 준수 \n ▶️ 체육시설 내 취사, 흡연 및 음주행위, 지나친 소음행위 금지(적발 시 이용불가) \n ▶️ 시설 사용 후 정리정돈(쓰레기 반드시 처리) \n ▶️ 고의 및 과실로 인한 시설물 훼손 및 파손시 사용자가 배상하며 경기중 부상은 본인이 책임집니다. \n ▶️ 시설보호와 부상방지를 위하여 스터드가 있는 축구화는 착용이 제한될 수 있습니다. \n ▶️ 운동시에는 마스크를 꼭 착용해주셔야합니다. 호흡이 어려운 경우 운동템포와 휴식시간을 조정해주세요. \n ▶️ 실내구장의 경우에는 휴식시에도 마스크를 착용해주셔야합니다. \n ▶️ 야외구장의 경우에는 휴식시 2M 이상 거리를 유지해주세요. \n ▶️ 휴식 및 대기는 구장 밖에서 해주셔야 합니다. \n ▶️ 위 내용이 지켜지지 않을 경우 무환불 퇴장조치 될 수 있으니 예약시 꼭 참고부탁드립니다. \n ▶️ 위 내용을 지키지 않아 발생하는 문제는 예약자 본인에게 있습니다.";

    // console.log(placeInfos.value[0]);

    // let { imgUrl, cost } = toRefs(placeInfos);
    return {
      placeInfos,
      rules,
    };
  },
};
</script>

<style>
h1 {
  font-weight: bold;
  font-size: 3rem;
}
h2 {
  font-weight: bold;
  font-size: 2.5rem;
}
h3 {
  font-weight: bold;
  font-size: 2rem;
}
h4 {
  font-weight: bold;
  font-size: 1.5rem;
}
h5 {
  font-size: 1rem;
}
.container {
  max-width: 80%;
  /* border: 1px solid black; */
}
.imageBox {
  max-width: 100%;
  height: 60vh;
  /* margin: auto; */
  display: flex;
  /* height: 100%; */
  justify-content: center;
  align-items: center;
}
.imageFrist {
  object-fit: cover;
  border-radius: 5px;
  margin: 1rem 0.5rem 1rem 0.5rem;
  align-self: stretch;
}
.imageOthers {
  object-fit: cover;
  border-radius: 5px;
  align-self: stretch;
  margin: 1rem 0.5rem 1rem 0;
}
.placeDetailImage {
  /* margin: 1rem 0.1vw 1rem 0.1vw; */
  border-radius: 5px;
  object-fit: cover;
  align-self: stretch;
}
.importantInfos {
  display: flex;
  justify-content: space-between;
}

.detailInfos {
  width: 100%;
}
.placeDetailSportsCategory {
  display: flex;
  padding: 1.5rem 2rem 1.5rem 2rem;
  justify-items: center;
  align-self: center;
  align-items: center;
  height: auto;
  box-shadow: 0 0 8px rgba(24, 24, 24, 0.1);
  border-radius: 5px;
  border: 1px solid rgb(0, 0, 0, 0.1);
}
.detailInfosLeft {
  /* border: 1px solid black; */
}
.detailInfosRight {
  /* width: 600px;
  height: 300px; */
  /* justify-items: center; */
  /* align-self: center; */
  /* align-items: center; */
  padding: 1rem 0rem 1rem 1rem;
  height: auto;
  box-shadow: 0 0 8px rgba(24, 24, 24, 0.1);
  border-radius: 10px;
  border: 1px solid rgb(0, 0, 0, 0.1);
  /* border: 1px solid black; */
}
.ReservationRealTime {
  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
  height: 80px;
  width: 300px;
  margin: auto;
  overflow: scroll;
  color: #112031;
  border: 1px solid #000;
  overflow: auto;
  white-space: nowrap;
}
.detailInfosRule {
  margin-bottom: 2rem;
  /* width: 600px;
  height: 300px; */
  /* border: 1px solid black; */
}
.reviews {
  /* width: 600px;
  height: 300px; */
  border: 1px solid black;
}
.refundPolicy {
  /* width: 600px;
  height: 300px; */
  border: 1px solid black;
}

hr.class-1 {
  width: 100%;
  margin: 3rem 0 3rem 0;
  color: rgb(0, 0, 0, 0.4);
  /* border-top: 1px solid black; */
}
hr.class-2 {
  margin: 2rem 0 2rem 0;
  height: 0.5px;
  width: 80%;
  color: rgb(0, 0, 0, 0.4);

  /* border-top: 1px solid black; */
  /* border: 0px; */
}
/* .dropwdonwTotal {
  width: 450px;
  height: 550px;
}

.dropdown-menu li {
  position: relative;
}
.dropdown-menu {
  height: 400px;
  display: none;
  position: absolute;
  left: 100%;

  border-top-left-radius: 10px;
  border-bottom-left-radius: 10px;
}
.dropdown-submenu {
  display: none;
  position: absolute;
  left: 100%;
  top: -30%;
  border-top-left-radius: 0px;
  border-bottom-left-radius: 0px;
  border-top-right-radius: 10px;
  border-bottom-right-radius: 10px;
}
.dropdown-menu .dropdown-submenu-left {
  right: 100%;
  left: auto;
  margin: 50px;
}
.dropdown-menu > li:hover > .dropdown-submenu {
  display: block;
} */
</style>
