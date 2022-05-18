<template>
  <div v-if="checkJwt() && placeInfos">
    <div style="max-width: 1400px; margin: auto">
      <Header></Header>
    </div>
    <div class="container">
      <div class="imageBox" v-if="placeInfos.img">
        <img class="imageFrist col-6" :src="`${placeInfos.img[0]}`" alt="" />
        <img v-for="(placeInfoImage, idx) in placeInfos.img.slice(1)" :key="idx" :src="`${placeInfoImage}`" class="imageOthers col-3" alt="" />
        <!-- <img :src="`${placeInfos.img[0]}`" alt="" /> -->
      </div>
      <div class="importantInfos mt-4">
        <div>
          <h4 style="color: #605f5f; font-weight: 500">{{ placeInfos.address }}</h4>
          <h2>{{ placeInfos.name }}</h2>
          <div class="d-flex align-items-end">
            <h5 class="me-2 mt-2 fw-bold p-2">{{ placeInfos.score.toFixed(1) }} <i class="bi bi-star-fill mx-1" style="color: #fe8a01"></i></h5>
            <h5 style="color: #605f5f" class="p-2">
              <u>{{ placeInfos.reviewCnt }}개 리뷰</u>
            </h5>
            <!-- <h4><i class="bi bi-heart-fill me-1" style="color: #ff385c"></i></h4> -->
            <div class="saveBox ms-2 p-2">
              <div v-if="`${placeInfos.like}` == `true`" @click="clickLike" class="d-flex">
                <h5><i class="bi bi-heart-fill me-1" style="color: #e01760"></i></h5>
                <h5 class="ms-1 fw-bold"><u>저장</u></h5>
              </div>
              <div v-else @click="clickLike" class="d-flex">
                <h5><i class="bi bi-heart me-1"></i></h5>
                <h5 class="ms-1 fw-bold"><u>저장</u></h5>
              </div>
            </div>
          </div>
        </div>
        <div class="placeDetailSportsCategory btnCategory">
          <img src="https://cdn-icons-png.flaticon.com/512/921/921285.png" style="width: 2.5rem" alt="" />
          <h4 class="ms-4 me-2 align-self-center">{{ placeInfos.category }}</h4>
        </div>
      </div>
      <hr class="class-1" style="margin: 2.5rem auto 3rem auto" />
      <!-- 상세정보들 -->
      <div class="detailInfos d-flex justify-content-between">
        <div class="detailInfosLeft col-7">
          <div style="min-height: 150px">
            <h4 class="pb-4">이용시간 및 가격</h4>
            <p v-for="(각시간, idx) in placeInfos.bizhour" :key="idx" class="">{{ 각시간 }}</p>
            <p v-for="(각시간, idx) in placeInfos.menu" :key="idx" class="">{{ 각시간 }}</p>
            <!-- <p></p> -->
          </div>
          <hr class="class-2" />
          <h4 class="pb-4 pt-3">시설정보</h4>
          <div class="col-12">
            <div class="d-flex facility-info-image-totalFrame col-12">
              <div class="d-flex justify-content-start align-items-center facility-info-image-frame col-6">
                <p class="col-4 my-0">주차가능</p>
                <img class="facility-info-image" style="width: 3.2rem" src="@/assets/images/Place/parked-car.png" alt="" />
              </div>
              <div class="d-flex justify-content-start align-items-center facility-info-image-frame col-6">
                <p class="col-4 my-0">샤워실</p>
                <img class="facility-info-image" src="@/assets/images/Place/shower.png" alt="" />
              </div>
            </div>
            <div class="d-flex facility-info-image-totalFrame">
              <div class="d-flex justify-content-start align-items-center facility-info-image-frame col-6">
                <p class="col-4 my-0">냉난방</p>
                <img class="facility-info-image" style="width: 2.7rem" src="@/assets/images/Place/aircon.png" alt="" />
              </div>
              <div class="d-flex justify-content-start align-items-center facility-info-image-frame col-6">
                <p class="col-4 my-0">장비대여</p>
                <img class="facility-info-image" src="@/assets/images/Place/rental.png" alt="" />
              </div>
            </div>
            <div class="d-flex facility-info-image-totalFrame">
              <div class="d-flex justify-content-start align-items-center facility-info-image-frame col-6">
                <p class="col-4 my-0">잔 디</p>
                <img class="facility-info-image" style="width: 3rem" src="@/assets/images/Place/grass.png" alt="" />
              </div>
              <div class="d-flex justify-content-start align-items-center facility-info-image-frame col-6">
                <p class="col-4 my-0">최대20명</p>
                <img class="facility-info-image" src="@/assets/images/Place/admittedperson.png" alt="" />
              </div>
            </div>
            <div class="d-flex facility-info-image-totalFrame">
              <div class="d-flex justify-content-start align-items-center facility-info-image-frame col-6">
                <p class="col-4 my-0">조 명</p>
                <img class="facility-info-image" src="@/assets/images/Place/light.png" alt="" />
              </div>
              <div class="d-flex justify-content-start align-items-center facility-info-image-frame col-6">
                <p class="col-4 my-0" style="line-break: auto">
                  150m <br />
                  x 150m
                </p>
                <img class="facility-info-image" src="@/assets/images/Place/placesize.png" alt="" />
              </div>
            </div>
          </div>
        </div>
        <div class="detailInfosRight col-5">
          <h4 class="ps-3 pt-2">예약조회</h4>
          <place-detail-reservation></place-detail-reservation>
        </div>
      </div>
      <div></div>
      <hr class="class-1" style="margin: 2.5rem 0 2.5rem 0" />
      <h4 class="pb-4">시설위치</h4>

      <div><PlaceDetailMaps :placeInfos="placeInfos" :longitude="placeInfos.longitude" :latitude="placeInfos.latitude"></PlaceDetailMaps></div>
      <hr class="class-1" style="margin: 2rem 0 2rem 0" />
      <h4 class="detailInfosRule">이용규칙</h4>
      <p style="white-space: pre-line; line-height: 2rem">
        {{ rules }}
      </p>
      <hr class="class-1" />
      <div class="reviewTotalFrame pe-5">
        <div class="review-score-frame d-flex flex-row align-items-center justify-content-between mb-2">
          <!-- <h4 class="me-4">리 뷰</h4> -->
          <div class="d-flex align-items-center">
            <!-- <div class="d-flex align-items-center"> -->
            <h4 class="fw-bold">{{ placeInfos.score.toFixed(1) }} <i class="bi bi-star-fill mx-1" style="color: #fe8a01"></i></h4>
            <h4 class="fw-bold ms-2">({{ placeInfos.reviewCnt }}개 리뷰)</h4>
          </div>
          <!-- <PlaceDetailReviewCreate class="PlaceDetailReviewCreate"></PlaceDetailReviewCreate> -->
        </div>
        <div>
          <div class="d-flex justify-content-between align-items-center mt-4 mb-3">
            <div class="col">
              <div class="d-flex align-items-end justify-content-start">
                <p class="my-0 col-4"><strong>시 설</strong></p>
                <div class="d-flex flex-column col-4 pb-1">
                  <div class="progress col d-flex" style="background-color: white">
                    <div
                      class="progress-bar d-flex"
                      role="progressbar"
                      :style="`width:${(placeInfos.place / 5) * 100}%; background-color:white;`"
                      :aria-valuenow="`${placeInfos.place}`"
                      aria-valuemin="0"
                      aria-valuemax="5"
                    ></div>
                    <img class="progress-bar-image" style="right: 100" src="@/assets/images/Place/run.svg" alt="" />
                  </div>
                  <div class="progress col d-flex">
                    <div
                      class="progress-bar bg-dark d-flex"
                      role="progressbar"
                      :style="`width:${(placeInfos.place / 5) * 100}%; height:6px;`"
                      :aria-valuenow="`${placeInfos.place}`"
                      aria-valuemin="0"
                      aria-valuemax="5"
                    ></div>
                  </div>
                </div>
                <p class="ms-4 my-0 col-2">{{ placeInfos.place.toFixed(1) }}</p>
              </div>
            </div>
            <div class="col">
              <div class="d-flex align-items-end justify-content-start">
                <p class="my-0 col-4"><strong>청 결</strong></p>
                <div class="d-flex flex-column col-4 pb-1">
                  <div class="progress col d-flex" style="background-color: white">
                    <div
                      class="progress-bar d-flex"
                      role="progressbar"
                      :style="`width:${placeInfos.cleanness * 100}%; background-color:white;`"
                      :aria-valuenow="`${placeInfos.cleanness}`"
                      aria-valuemin="0"
                      aria-valuemax="5"
                    ></div>
                    <img class="progress-bar-image" style="right: 100" src="@/assets/images/Place/run.svg" alt="" />
                  </div>
                  <div class="progress col d-flex">
                    <div
                      class="progress-bar bg-dark d-flex"
                      role="progressbar"
                      :style="`width:${placeInfos.cleanness * 100}%; height:6px;`"
                      :aria-valuenow="`${placeInfos.cleanness}`"
                      aria-valuemin="0"
                      aria-valuemax="5"
                    ></div>
                  </div>
                </div>
                <p class="ms-4 my-0 col-2">{{ placeInfos.cleanness.toFixed(1) }}</p>
              </div>
            </div>
          </div>
          <div class="d-flex justify-content-between align-items-center mb-5">
            <div class="col">
              <div class="d-flex align-items-end justify-content-start">
                <p class="my-0 col-4"><strong>위 치</strong></p>
                <div class="d-flex flex-column col-4 pb-1">
                  <div class="progress col d-flex" style="background-color: white">
                    <div
                      class="progress-bar d-flex"
                      role="progressbar"
                      :style="`width:${placeInfos.location * 100}%; background-color:white;`"
                      :aria-valuenow="`${placeInfos.location}`"
                      aria-valuemin="0"
                      aria-valuemax="5"
                    ></div>
                    <img class="progress-bar-image" style="right: 100" src="@/assets/images/Place/run.svg" alt="" />
                  </div>
                  <div class="progress col d-flex">
                    <div
                      class="progress-bar bg-dark d-flex"
                      role="progressbar"
                      :style="`width:${placeInfos.location * 100}%; height:6px; `"
                      :aria-valuenow="`${placeInfos.location}`"
                      aria-valuemin="0"
                      aria-valuemax="5"
                    ></div>
                  </div>
                </div>
                <p class="ms-4 my-0 col-2">{{ placeInfos.location.toFixed(1) }}</p>
              </div>
            </div>
            <div class="col">
              <div class="d-flex align-items-end justify-content-start">
                <p class="my-0 col-4"><strong>가 격</strong></p>
                <div class="d-flex flex-column col-4 pb-1">
                  <div class="progress col d-flex" style="background-color: white">
                    <div
                      class="progress-bar d-flex"
                      role="progressbar"
                      :style="`width:${(placeInfos.price / 5) * 100}%; background-color:white;`"
                      :aria-valuenow="`${placeInfos.price}`"
                      aria-valuemin="0"
                      aria-valuemax="5"
                    ></div>
                    <img class="progress-bar-image" style="right: 100" src="@/assets/images/Place/run.svg" alt="" />
                  </div>
                  <div class="progress col d-flex">
                    <div
                      class="progress-bar bg-dark d-flex"
                      role="progressbar"
                      :style="`width:${(placeInfos.price / 5) * 100}%; height:6px;`"
                      :aria-valuenow="`${placeInfos.price}`"
                      aria-valuemin="0"
                      aria-valuemax="5"
                    ></div>
                  </div>
                </div>
                <p class="ms-4 my-0 col-2">{{ placeInfos.price.toFixed(1) }}</p>
                <!-- <p class="ms-4 my-0 col-2">{{ 5 }}</p> -->
              </div>
            </div>
          </div>
          <!-- 총평점 끝 -->
          <!-- 리뷰시작 -->
          <!-- <hr class="class-1" /> -->
          <div class="place-detail-reviews-frame d-flex flex-row align-items-center my-2">
            <place-detail-reviews v-for="(review, idx) in placeInfos.reviewList" :key="idx" :review="review"></place-detail-reviews>
            <!-- {{ placeInfos.reviewList }} -->
          </div>
        </div>
        <div class="writeReview">
          <!-- <Slider v-model="value" type="input" :format="format" /> -->
        </div>
        <div class=""></div>
      </div>
      <hr class="class-1" />
      <div class="refund-policy">
        <h4 class="refundPolicy">환불 및 변경 정책</h4>
        <p style="white-space: pre-line; line-height: 2rem">
          {{ refundPolicy }}
        </p>
      </div>
      <hr class="class-1" />
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import Header from "../Common/Header.vue";
import Footer from "../Common/Footer.vue";
import PlaceDetailMaps from "./Components/PlaceDetailMaps.vue";
import PlaceDetailReservation from "./Components/PlaceDetailReservation.vue";
// import PlaceDetailReviewCreate from "./Components/PlaceDetailReviewCreate.vue";
import { ref, reactive, computed, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useStore } from "vuex";
import axios from "axios";
import PlaceDetailReviews from "./Components/PlaceDetailReviews.vue";
const BASE_URL = "https://pairplay.site/api/v1";

export default {
  name: "PlaceDetail",
  components: { Header, Footer, PlaceDetailReservation, PlaceDetailMaps, PlaceDetailReviews },

  setup() {
    const route = useRoute();
    const router = useRouter();
    const store = useStore();
    const pageId = ref();
    const placeInfos = reactive(computed(() => store.state.root.placeDetailInfo));
    const checkJwt = () => {
      if (localStorage.getItem("jwt")) {
        // pass
        return true;
      } else {
        router.push({
          name: "Login",
        });
      }
    };
    const rules =
      "▶️ 체육시설 예약시간 준수 \n ▶️ 체육시설 내 취사, 흡연 및 음주행위, 지나친 소음행위 금지(적발 시 이용불가) \n ▶️ 시설 사용 후 정리정돈(쓰레기 반드시 처리) \n ▶️ 고의 및 과실로 인한 시설물 훼손 및 파손시 사용자가 배상하며 경기중 부상은 본인이 책임집니다. \n ▶️ 시설보호와 부상방지를 위하여 스터드가 있는 축구화는 착용이 제한될 수 있습니다. \n ▶️ 운동시에는 마스크를 꼭 착용해주셔야합니다. 호흡이 어려운 경우 운동템포와 휴식시간을 조정해주세요. \n ▶️ 실내구장의 경우에는 휴식시에도 마스크를 착용해주셔야합니다. \n ▶️ 야외구장의 경우에는 휴식시 2M 이상 거리를 유지해주세요. \n ▶️ 휴식 및 대기는 구장 밖에서 해주셔야 합니다. \n ▶️ 위 내용이 지켜지지 않을 경우 무환불 퇴장조치 될 수 있으니 예약시 꼭 참고부탁드립니다. \n ▶️ 위 내용을 지키지 않아 발생하는 문제는 예약자 본인에게 있습니다.";

    const refundPolicy =
      " - 사용 10일 전 까지 : 100% 환불 \n - 9일 전 ~ 7일 전 : 90% 환불 \n - 6일 전 ~ 5일 전 : 70% 환불 \n - 4일 전 ~ 2일 전 : 50% 환불 \n - 1일 전 : 20% 환불 \n - 대관 당일 : 환불 불가";

    onMounted(() => {
      checkJwt();
      // await getCards();
      pageId.value = route.params.id;
      getPlaceDetailInfo();
      console.log(placeInfos);
    });
    const getPlaceDetailInfo = async () => await store.dispatch("root/getPlaceDetailInfo", pageId.value);
    const clickLike = async (id) => {
      console.log(id, "id는?");
      await axios({ method: "put", headers: { Authorization: "Bearer " + localStorage.getItem("jwt") }, url: `${BASE_URL}/places/like/${pageId.value}` });
      await store.dispatch("root/getPlaceDetailInfo", pageId.value);
    };
    console.log(placeInfos.value, "placeInfos");
    // let { imgUrl, cost } = toRefs(placeInfos);
    return {
      placeInfos,
      rules,
      pageId,
      refundPolicy,
      onMounted,
      checkJwt,
      getPlaceDetailInfo,
      clickLike,
    };
  },
};
</script>

<style lang="scss" scoped>
h1 {
  font-weight: bold;
  font-size: 2.5rem;
}
h2 {
  font-weight: bold;
  font-size: 2.2rem;
}
h3 {
  font-weight: bold;
  font-size: 1.6rem;
}
h4 {
  font-weight: bold;
  font-size: 1.3rem;
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
.saveBox {
  display: flex;
  justify-content: center;
  align-content: center;
  margin: 0 0 0 2rem;
}
.saveBox:hover {
  box-shadow: 0 0 8px rgba(24, 24, 24, 0.1);
  border-radius: 5px;
  cursor: pointer;
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
  padding: 1rem 1rem 1rem 1rem;
  height: auto;
  box-shadow: 0 0 8px rgba(24, 24, 24, 0.1);
  border-radius: 10px;
  border: 1px solid rgb(0, 0, 0, 0.1);
  /* border: 1px solid black; */
}

.detailInfosRule {
  margin-bottom: 2rem;
  /* width: 600px;
  height: 300px; */
  /* border: 1px solid black; */
}
.facility-info-image-totalFrame {
  margin: 1rem 0rem 0rem 0rem;
}
.facility-info-image-frame {
  // margin: 0.5rem 1rem 0.5rem 1rem;
}
.facility-info-image {
  width: 3.5rem;
  // margin-left: 2rem;
}
.place-detail-reviews-frame {
  // margin: 3rem 0rem 0rem 0rem;
  max-height: 600px;
  max-width: 100%;
  // min-height: 300px;
  // background-color: black;
  // border: 1px solid black;
}
.refundPolicy {
  /* width: 600px;
  height: 300px; */
  /* border: 1px solid black; */
}
/* .PlaceDetailReviewCreate {
  margin: 0 0 0 0;
} */
.review-score-frame {
  /* padding-right: calc(12vw); */
}
.progress-bar-image {
  width: 13px;
  position: relative;
  z-index: 4;
  /* height: 1px; */
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
