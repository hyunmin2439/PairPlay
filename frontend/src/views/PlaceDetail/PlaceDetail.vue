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
          <h4 class="fw-bold" style="color: #767676">{{ placeInfos.position }}</h4>
          <h2 class="fw-bold">{{ placeInfos.title }}</h2>
          <div class="d-flex align-items-end my-3">
            <h5 class="fw-bold mx-4">{{ placeInfos.rate }} <i class="bi bi-star-fill mx-1" style="color: #fe8a01"></i></h5>
            <h5 style="color: #767676">
              <u>{{ placeInfos.reviewsCount }}개 리뷰</u>
            </h5>
            <!-- <h4><i class="bi bi-heart-fill me-1" style="color: #ff385c"></i></h4> -->
            <h5><i class="bi bi-heart me-1 ms-4"></i></h5>
            <h5 class="ms-1"><u>저장</u></h5>
          </div>
        </div>
        <div class="placeDetailSportsCategory btnCategory">
          <img src="https://cdn-icons-png.flaticon.com/512/921/921285.png" style="width: 2.5rem" alt="" />
          <h4 class="ms-4 align-self-center">{{ placeInfos.category }}</h4>
        </div>
      </div>
      <hr style="margin-top: 1px; margin-bottom: 1px; color: #b7b7b7" />
      <!-- 상세정보들 -->
      <div class="detailInfos d-flex justify-content-between mt-4">
        <div class="detailInfosLeft col-7">
          <div>
            <p v-for="(각시간, idx) in placeInfos.이용시간" :key="idx" class="mx-3">{{ 각시간 }}</p>
            <!-- <p></p> -->
          </div>
          <div>시설 정보</div>
        </div>
        <div class="detailInfosRight col-5">
          <h4 class="fw-bold mb-4">예약조회</h4>
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
      <div class="detailInfosRule">이용규칙</div>
      <div class="reviews">리뷰들</div>
      <div class="refundPolicy">환불 및 변경 정책</div>
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
// import PlaceDetailMaps from "./Components/PlaceDetailMaps.vue";
import PlaceDetailReservation from "./Components/PlaceDetailReservation.vue";

import { ref } from "vue";
// import {useRoute} from "vue-router"
// import {mapState, useStore} from "vuex"

export default {
  name: "PlaceDetail",
  components: { Header, Footer, PlaceDetailReservation },

  setup() {
    const placeInfos = ref({
      title: "장정구 복싱 체육관",
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
    console.log(placeInfos.value[0]);

    // let { imgUrl, cost } = toRefs(placeInfos);
    return {
      placeInfos,
    };
  },
};
</script>

<style>
h1 {
  font-size: 3rem;
}
h2 {
  font-size: 2.5rem;
}
h3 {
  font-size: 2rem;
}
h4 {
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

  /* width: 600px;
  height: 300px; */
  /* border: 1px solid grey; */
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
  /* width: 600px; */
  /* height: 300px; */
  /* border: 1px solid black; */
}

.detailInfos {
  /* border: 1px solid black; */
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
  border: 1px solid black;
}
.detailInfosRight {
  /* width: 600px;
  height: 300px; */
  /* justify-items: center; */
  /* align-self: center; */
  /* align-items: center; */
  padding: 1rem 1rem 1rem 1rem;
  height: auto;
  box-shadow: 0 0 8px rgba(24, 24, 24, 0.1);
  border-radius: 10px;
  border: 1px solid rgb(0, 0, 0, 0.1);
  /* border: 1px solid black; */
}
.ReservationRealTime {
  display: flex;
  height: 80px;
  /* width: 400px; */
  margin: auto;
  overflow: scroll;
  color: #112031;
  border: 1px solid #000;
  overflow: auto;
  white-space: nowrap;
}
.detailInfosRule {
  /* width: 600px;
  height: 300px; */
  border: 1px solid black;
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
