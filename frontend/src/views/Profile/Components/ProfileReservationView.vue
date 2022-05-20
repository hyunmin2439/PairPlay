<template>
  <h2>
    <div class="d-flex justify-content-between">
      <div><b>예약 기록</b></div>
      <div class="d-flex align-items-end">
        <div class="me-5" style="font-size: medium; cursor: pointer" @click="profileReservationList"><b class="all">모든 장소</b></div>
        <div class="me-5" style="font-size: medium; cursor: pointer" @click="profileReservationDoingList"><b class="doing">예악 중인 장소</b></div>
        <div style="font-size: medium; cursor: pointer" @click="profileReservationDoneList"><b class="done">사용 완료 장소</b></div>
      </div>
    </div>
    <hr />
  </h2>
  <div v-for="(data, idx) in state.profileReservationList" :key="idx">
    <div class="d-flex my-5 row">
      <div class="col-3">
        <img :src="data.myReservation.place[0].img[0]" alt="" class="res-img" />
      </div>
      <div class="col-6 ms-5 d-flex justify-content-center" style="flex-direction: column">
        <p style="font-size: medium; color: rgb(182 182 182)">{{ data.myReservation.place[0].address }}</p>
        <div class="d-flex justify-content-between align-items-center">
          <div>
            <h4>{{ data.myReservation.place[0].name }}</h4>
            <div class="d-flex align-items-center">
              <div class="rating me-2"></div>
              <span class="me-4">{{ data.myReservation.place[0].score }}</span>
              <div class="view me-2"></div>
              <span class="me-4">{{ data.myReservation.place[0].viewCnt }}</span>
              <div class="review me-2"></div>
              <span class="me-4">{{ data.myReservation.place[0].reviewCnt }}</span>
            </div>
          </div>
          <span
            ><b>{{ data.myReservation.place[0].category }}</b></span
          >
        </div>
        <p class="mt-5">
          <span style="font-weight: bold; font-size: small">
            {{ data.myReservation.reserveStartDt[0] }}년 {{ data.myReservation.reserveStartDt[1] }}월 {{ data.myReservation.reserveStartDt[2] }}일
          </span>
          <span v-for="(time, idx) in data.myReservation.time" :key="idx" style="font-weight: bold; font-size: small">
            <span v-if="idx == data.myReservation.time.length - 1">{{ time }}:00 ~ {{ time + 1 }}:00</span>
            <span v-else>{{ time }}:00 ~ {{ time + 1 }}:00, </span>
          </span>
          <span style="font-size: small">에 예약중입니다.</span>
        </p>
        <p v-if="data.used == false" style="font-size: x-small">더 자세한 상품의 이용방법, 환불 등에 대한 문의는 판매자에게 문의해주세요.</p>
        <p v-else style="font-size: x-small">구매확정 이후 상품의 이용방법 및 반품 등에 대한 문의는 판매자에게 문의해주세요.</p>
      </div>
      <div v-if="data.used == false" class="col-2 d-flex justify-content-evenly align-items-center" style="flex-direction: column">
        <button class="reser-btn" @click="moveToPlaceDetail(data.myReservation.placeId)">자세히</button>
        <button class="reser-btn" @click="cancelPlaceReservation(data.myReservation.id)">예약취소</button>
      </div>
      <div v-else-if="(data.used == true) & (data.myReservation.writtenReview == false)" class="col-2 d-flex justify-content-evenly align-items-center" style="flex-direction: column">
        <button class="reser-btn" @click="moveToPlaceDetail(data.myReservation.placeId)">자세히</button>
        <button class="reser-btn" type="button" data-bs-toggle="modal" data-bs-target="#exampleModal1" @click="reviewDetail(data.myReservation.id)">리뷰 작성</button>
      </div>
      <div v-else-if="(data.used == true) & (data.myReservation.writtenReview == true)" class="col-2 d-flex justify-content-evenly align-items-center" style="flex-direction: column">
        <button class="reser-btn" @click="moveToPlaceDetail(data.myReservation.placeId)">자세히</button>
        <button class="reser-btn" type="button" data-bs-toggle="modal" data-bs-target="#exampleModal2" @click="reviewDetailInfo(data.myReservation.id)">내 리뷰</button>
      </div>
    </div>
    <hr />
  </div>
  <div v-if="state.profileReservationList.length == (state.page + 1) * 10" style="margin: auto; text-align: center" class="mt-5">
    <button @click="profileGetMoreList" class="btn-more">더보기</button>
  </div>
  <!-- Modal Create -->
  <div class="modal fade" id="exampleModal1" tabindex="-1" aria-labelledby="exampleModalLabel1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" style="max-width: 750px">
      <div class="modal-content" style="border: none">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel1">리뷰 작성하기</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="d-flex row">
            <div class="col-5" style="height: 300px">
              <div class="d-flex justify-content-around align-items-end">
                <span>청결: </span>
                <div class="stars">
                  <input type="radio" id="clean1" value="1" name="clean" @click="changeClean($event)" />
                  <input type="radio" id="clean2" value="2" name="clean" @click="changeClean($event)" />
                  <input type="radio" id="clean3" value="3" name="clean" @click="changeClean($event)" />
                  <input type="radio" id="clean4" value="4" name="clean" @click="changeClean($event)" />
                  <input type="radio" id="clean5" value="5" name="clean" @click="changeClean($event)" />

                  <label for="clean1" aria-label="Clean">1 star</label>
                  <label for="clean2">2 stars</label>
                  <label for="clean3">3 stars</label>
                  <label for="clean4">4 stars</label>
                  <label for="clean5">5 stars</label>
                </div>
              </div>
              <div class="d-flex justify-content-around align-items-end my-2">
                <span>위치: </span>
                <div class="stars">
                  <input type="radio" id="location1" value="1" name="location" @click="changeLocation($event)" />
                  <input type="radio" id="location2" value="2" name="location" @click="changeLocation($event)" />
                  <input type="radio" id="location3" value="3" name="location" @click="changeLocation($event)" />
                  <input type="radio" id="location4" value="4" name="location" @click="changeLocation($event)" />
                  <input type="radio" id="location5" value="5" name="location" @click="changeLocation($event)" />

                  <label for="location1" aria-label="Location">1 star</label>
                  <label for="location2">2 stars</label>
                  <label for="location3">3 stars</label>
                  <label for="location4">4 stars</label>
                  <label for="location5">5 stars</label>
                </div>
              </div>
              <div class="d-flex justify-content-around align-items-end my-2">
                <span>시설: </span>
                <div class="stars">
                  <input type="radio" id="place1" value="1" name="place" @click="changePlace($event)" />
                  <input type="radio" id="place2" value="2" name="place" @click="changePlace($event)" />
                  <input type="radio" id="place3" value="3" name="place" @click="changePlace($event)" />
                  <input type="radio" id="place4" value="4" name="place" @click="changePlace($event)" />
                  <input type="radio" id="place5" value="5" name="place" @click="changePlace($event)" />

                  <label for="place1" aria-label="Place">1 star</label>
                  <label for="place2">2 stars</label>
                  <label for="place3">3 stars</label>
                  <label for="place4">4 stars</label>
                  <label for="place5">5 stars</label>
                </div>
              </div>
              <div class="d-flex justify-content-around align-items-end mt-2 mb-4">
                <span>가격: </span>
                <div class="stars">
                  <input type="radio" id="price1" value="1" name="price" @click="changePrice($event)" />
                  <input type="radio" id="price2" value="2" name="price" @click="changePrice($event)" />
                  <input type="radio" id="price3" value="3" name="price" @click="changePrice($event)" />
                  <input type="radio" id="price4" value="4" name="price" @click="changePrice($event)" />
                  <input type="radio" id="price5" value="5" name="price" @click="changePrice($event)" />

                  <label for="price1" aria-label="Price">1 star</label>
                  <label for="price2">2 stars</label>
                  <label for="price3">3 stars</label>
                  <label for="price4">4 stars</label>
                  <label for="price5">5 stars</label>
                </div>
              </div>
              <div class="d-flex justify-content-around align-items-start my-2">
                <span>리뷰: </span>
                <textarea name="" id="" style="resize: none" v-model="state.description"></textarea>
              </div>
            </div>
            <div class="col-1"></div>
            <div class="col-6" style="height: 250px">
              <span class="me-3">사진 등록</span>
              <input type="file" @change="changeImgFile" />
              <div v-if="state.reviewImage" class="my-3">
                <img :src="state.reviewImage" alt="" style="width: 270px; height: 180px" />
              </div>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
          <button type="button" class="btn btn-primary" @click="profileCreateReview">리뷰 생성</button>
        </div>
      </div>
    </div>
  </div>
  <!-- Modal View -->
  <div class="modal fade" id="exampleModal2" tabindex="-1" aria-labelledby="exampleModalLabel2" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" style="max-width: 750px">
      <div class="modal-content" style="border: none">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel2">내 리뷰 보기</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="d-flex row">
            <div class="col-5" style="height: 300px">
              <div class="d-flex justify-content-start align-items-end ms-2">
                <span style="font-weight: bold;">작성자: </span>
                <div class="ms-4">{{ state.reviewDetailList.nickname }}</div>
              </div>
              <div class="d-flex justify-content-start align-items-end my-3 ms-2">
                <span style="font-weight: bold;">청결: </span>
                <div class="d-flex ms-5">
                  <div v-for="(star, idx) in state.starList[Number(state.reviewDetailList.cleanness)]" :key="idx" class="view-star-full me-2" style="height: 25px; width: 25px"></div>
                  <div v-for="(star, idx) in state.starList[(5-Number(state.reviewDetailList.cleanness))]" :key="idx" class="view-star-none me-2" style="height: 25px; width: 25px"></div>
                </div>
              </div>
              <div class="d-flex justify-content-start align-items-end my-3 ms-2">
                <span style="font-weight: bold;">위치: </span>
                <div class="d-flex ms-5">
                  <div v-for="(star, idx) in state.starList[Number(state.reviewDetailList.location)]" :key="idx" class="view-star-full me-2" style="height: 25px; width: 25px"></div>
                  <div v-for="(star, idx) in state.starList[(5-Number(state.reviewDetailList.location))]" :key="idx" class="view-star-none me-2" style="height: 25px; width: 25px"></div>
                </div>
              </div>
              <div class="d-flex justify-content-start align-items-end my-3 ms-2">
                <span style="font-weight: bold;">시설: </span>
                <div class="d-flex ms-5">
                  <div v-for="(star, idx) in state.starList[Number(state.reviewDetailList.place)]" :key="idx" class="view-star-full me-2" style="height: 25px; width: 25px;"></div>
                  <div v-for="(star, idx) in state.starList[(5-Number(state.reviewDetailList.place))]" :key="idx" class="view-star-none me-2" style="height: 25px; width: 25px;"></div>
                </div>
              </div>
              <div class="d-flex justify-content-start align-items-end mt-3 mb-4 ms-2">
                <span style="font-weight: bold;">가격: </span>
                <div class="d-flex ms-5">
                  <div v-for="(star, idx) in state.starList[Number(state.reviewDetailList.price)]" :key="idx" class="view-star-full me-2" style="height: 25px; width: 25px"></div>
                  <div v-for="(star, idx) in state.starList[(5-Number(state.reviewDetailList.price))]" :key="idx" class="view-star-none me-2" style="height: 25px; width: 25px"></div>
                </div>
              </div>
              <div class="my-2 ms-2">
                <p style="font-weight: bold;">리뷰:</p>
                <p>{{ state.reviewDetailList.description }}</p>
                <br>
              </div>
            </div>
            <div class="col-1"></div>
            <div class="col-6" style="height: 250px">
              <span class="me-3" style="font-weight: bold;">사진</span>
              <div v-if="state.reviewDetailList.reviewImage" class="my-3">
                <img :src="state.reviewDetailList.reviewImage" alt="" style="width: 270px; height: 180px" />
              </div>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
          <button type="button" class="btn btn-primary">확인</button>
        </div>
      </div>
    </div>
  </div>
  <br /><br />
</template>

<script>
import { reactive, computed, onMounted } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
export default {
  name: "ProfileReservationView",
  props: {
    userInfo: Object,
    otherInfo: Object,
  },
  setup(props) {
    const store = useStore();
    const router = useRouter();
    const state = reactive({
      userInfo: props.userInfo,
      otherInfo: props.otherInfo,
      status: "All",
      profileReservationList: computed(() => store.getters["root/profileReservationList"]),
      reviewCreateId: 0,
      starList: [[], [1], [1,2], [1,2,3], [1,2,3,4], [1,2,3,4,5]],
      page: 0,
      clean: 0,
      location: 0,
      price: 0,
      place: 0,
      reviewImage: "",
      description: "",
      reviewImg: "",
      reviewDetailList: computed(() => store.getters["root/reviewDetailList"]),
    });

    onMounted(async () => {
      await store.dispatch("root/profileReservationList", 0);
      var all = document.getElementsByClassName("all")[0];
      all.classList.add("select");
    });

    const profileReservationList = async function () {
      state.status = "All";
      state.page = 0;
      var all = document.getElementsByClassName("all")[0];
      var doing = document.getElementsByClassName("doing")[0];
      var done = document.getElementsByClassName("done")[0];
      all.classList.add("select");
      doing.classList.remove("select");
      done.classList.remove("select");
      await store.dispatch("root/profileReservationList", state.page);
    };

    const profileReservationDoingList = async function () {
      state.status = "Doing";
      state.page = 0;
      var all = document.getElementsByClassName("all")[0];
      var doing = document.getElementsByClassName("doing")[0];
      var done = document.getElementsByClassName("done")[0];
      all.classList.remove("select");
      doing.classList.add("select");
      done.classList.remove("select");
      await store.dispatch("root/profileReservationDoingList", state.page);
    };

    const profileReservationDoneList = async function () {
      state.status = "Done";
      state.page = 0;
      var all = document.getElementsByClassName("all")[0];
      var doing = document.getElementsByClassName("doing")[0];
      var done = document.getElementsByClassName("done")[0];
      all.classList.remove("select");
      doing.classList.remove("select");
      done.classList.add("select");
      await store.dispatch("root/profileReservationDoneList", state.page);
    };

    const profileGetMoreList = async function () {
      if (state.status == "All") {
        state.page += 1;
        store.dispatch("root/profileReservationListMore", state.page);
      } else if (state.status == "Doing") {
        state.page += 1;
        store.dispatch("root/profileReservationDoingListMore", state.page);
      } else if (state.status == "Done") {
        state.page += 1;
        store.dispatch("root/profileReservationDoneListMore", state.page);
      }
    };

    const reviewDetail = async function (id) {
      state.reviewCreateId = id;
    };

    const reviewDetailInfo = async function (id) {
      console.log(id);
      store.dispatch("root/reviewDetailInfo", {
        reservationId: id,
      });
    };

    const moveToPlaceDetail = function (id) {
      router.push({
        name: "PlaceDetail",
        params: {
          id: id,
        },
      });
    };

    const cancelPlaceReservation = function (id) {
      store.dispatch("root/profileCancelPlaceReservation", {
        reservationId: id,
      });
    };

    const changeClean = async function (event) {
      state.clean = event.target.value;
    };
    const changeLocation = async function (event) {
      state.location = event.target.value;
    };
    const changePlace = async function (event) {
      state.place = event.target.value;
    };
    const changePrice = async function (event) {
      state.price = event.target.value;
    };

    const changeImgFile = async function (event) {
      if (event.target.files && event.target.files.length > 0) {
        const file = event.target.files[0];
        state.reviewImage = URL.createObjectURL(file);
        state.reviewImg = file;
        // let data = new FormData()
        // data.append("profileImage", file)
        // await store.dispatch('root/profileChangeImage', { 'file': file })
      }
    };

    const profileCreateReview = async function () {
      const reviewInfo = {
        reservationId: state.reviewCreateId,
        description: state.description,
        cleanness: state.clean,
        place: state.place,
        location: state.location,
        price: state.price,
      };
      const formData = new FormData();
      formData.append("reviewInfo", new Blob([JSON.stringify(reviewInfo)], { type: "application/json" }));
      formData.append("reviewImage", state.reviewImg);
      await store.dispatch("root/profileCreateReview", formData);
    };

    return {
      state,
      onMounted,
      profileReservationList,
      profileReservationDoingList,
      profileReservationDoneList,
      profileGetMoreList,
      reviewDetail,
      reviewDetailInfo,
      moveToPlaceDetail,
      cancelPlaceReservation,
      changeImgFile,
      changeClean,
      changeLocation,
      changePlace,
      changePrice,
      profileCreateReview,
    };
  },
};
</script>

<style scoped lang="scss">
.res-img {
  width: 220px;
  height: 220px;
  border-radius: 10px;
  object-fit: cover;
}

.rating {
  background-image: url("@/assets/images/Profile/Rating.png");
  width: 20px;
  height: 20px;
  background-size: 100%;
}

.view {
  background-image: url("@/assets/images/Profile/View.png");
  width: 20px;
  height: 20px;
  background-size: 100%;
}

.review {
  background-image: url("@/assets/images/Profile/Review.png");
  width: 20px;
  height: 20px;
  background-size: 100%;
}

.reser-btn {
  width: 75%;
  padding-top: 10px;
  padding-bottom: 10px;
  border-radius: 25px;
  font-weight: bold;
  font-size: 0.9rem;
  background-color: white;
  border: 0.02px solid rgba(1, 1, 1, 0.3);

  cursor: pointer;
  &:hover {
    box-shadow: (0 1px 5px rgba(24, 24, 24, 0.2));
    border: 0.02px solid rgba(1, 1, 1, 0.5);

    background-color: #fafafa;
  }
}

$color: orange;

@mixin set-star($border: $color, $fill: transparent) {
  background: url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 512 512'%3E%3Cpath fill='#{$fill}' stroke='#{$border}' stroke-width='38' d='M259.216 29.942L330.27 173.92l158.89 23.087L374.185 309.08l27.145 158.23-142.114-74.698-142.112 74.698 27.146-158.23L29.274 197.007l158.89-23.088z' stroke-linecap='round' stroke-linejoin='round'/%3E%3C/svg%3E");
}

.stars input {
  position: absolute;
  clip: rect(0, 0, 0, 0);
}

.stars label {
  box-sizing: border-box;
  display: inline-block;
  margin-left: 6px;
  height: 25px;
  width: 25px;
  @include set-star;
  font-size: 0;
  cursor: pointer;
}

.stars input:nth-child(1):checked ~ label:nth-of-type(-n + 1),
.stars input:nth-child(2):checked ~ label:nth-of-type(-n + 2),
.stars input:nth-child(3):checked ~ label:nth-of-type(-n + 3),
.stars input:nth-child(4):checked ~ label:nth-of-type(-n + 4),
.stars input:nth-child(5):checked ~ label:nth-of-type(-n + 5) {
  @include set-star($color, $color);
}

.view-star-full {
  @include set-star($color, $color);
}

.view-star-none {
  @include set-star;
}

.btn-more {
  font-size: large;
  color: white;
  font-weight: bold;
  background-color: black;
  border-radius: 30px;
  width: 7rem;
  height: 3rem;
}

.select {
  font-size: large;
}
</style>
