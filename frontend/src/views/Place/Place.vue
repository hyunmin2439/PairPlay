<template>
  <div v-if="checkJwt()">
    <!-- 헤더 -->
    <div style="max-width: 1255px; margin: auto">
      <Header></Header>
    </div>
    <hr style="margin-top: 0px; margin-bottom: 0px; color: #b7b7b7" />
    <div class="PlaceBackgroundImage">
      <div class="PlaceBackgroundImageCover">
        <div class="PlaceBackground-Content ms-5 ps-5">
          <p class="PlaceTitle">
            당신에게 적합한 <br />
            운동장소를 찾아보세요.
          </p>
        </div>
      </div>
    </div>
    <div class="container" style="max-width: 1200px; padding: 0rem 0rem 0rem 0rem">
      <!-- <header></header> -->
      <div class="category container">
        <div class="category-title ps-3 mb-4">
          <div class="fs-3 fw-bold">운동 카테고리</div>
          <div>원하는 운동 카테고리를 선택해보세요.</div>
        </div>
        <place-category></place-category>
      </div>
      <div class="placeRecommend container" v-if="address">
        <div class="category-title ps-3 mt-3 mb-2 d-flex justify-content-between">
          <!-- <div class="fs-3 fw-bold">{{}}</div> -->
          <div class="fs-5 mb-4 d-flex align-items-center">
            <p>
              <strong>{{ address.split(" ")[0] }} {{ address.split(" ")[1] }}</strong
              >에서 <img src="@/assets/images/Place/flame.svg" class="placeFlame" alt="" />
            </p>
            <p class="text-danger"><strong> Hot한</strong></p>
            <p>&nbsp;<strong>시설</strong></p>
          </div>
          <!-- <p class="fw-bold pe-2 pt-3" style="font-size: 16px">전체보기</p> -->
        </div>
        <place-recommend></place-recommend>
      </div>
      <div class="placeRecentView container my-5" v-if="recentCards.length >= 1">
        <div class="category-title ps-3 mt-3 mb-2 d-flex justify-content-between">
          <div class="fs-5 fw-bold mb-4">최근 조회한 시설</div>
          <!-- <p class="fw-bold pe-2 pt-3" style="font-size: 16px">전체보기</p> -->
        </div>
        <place-recent-view></place-recent-view>
      </div>
    </div>
    <Footer class="footer"></Footer>
  </div>
</template>

<script>
import PlaceCategory from "./Components/PlaceCategory.vue";
import PlaceRecentView from "./Components/PlaceRecentView.vue";
import PlaceRecommend from "./Components/PlaceRecommend.vue";
import Header from "../Common/Header.vue";
import Footer from "../Common/Footer.vue";

import { computed, onMounted, reactive } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
export default {
  name: "Place",
  components: { Header, Footer, PlaceCategory, PlaceRecommend, PlaceRecentView },
  setup() {
    const store = useStore();
    const router = useRouter();
    const address = reactive(computed(() => store.state.root.userInfo.address));
    const recentCards = reactive(computed(() => store.state.root.placeRecent));
    // const route = useRoute();
    const checkJwt = () => {
      if (localStorage.getItem("jwt")) {
        // pass
        return true;
      } else {
        router
          .push({
            name: "Login",
          })
          .then(() => window.scrollTo(0, 0));
      }
    };
    onMounted(() => {
      checkJwt();
    });
    return { address, recentCards, onMounted, checkJwt };
  },
};
</script>

<style lang="scss" scoped>
/* header { */
/* background-color: wheat; */
/* width: 100%; */
/* } */
.PlaceBackgroundImage {
  width: 1200px;
  height: 300px;
  margin: auto;
  background: black;
  margin-bottom: 5rem;
  /* background-color: rgba(0, 0, 0, 0.4); */
  background: url("https://images.pexels.com/photos/3601094/pexels-photo-3601094.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260") 50% 40% no-repeat;
  background-size: 100% auto;
}
.PlaceBackgroundImageCover {
  position: absolute;
  margin: auto;
  width: 1200px;
  height: 300px;
  background: linear-gradient(30deg, rgba(0, 0, 0, 0.7) 0%, rgba(0, 0, 0, 0) 100%);
  /* background-color: rgba(0, 0, 0, 0.3); */
  z-index: 1;
  color: white;
}
.PlaceTitle {
  font-size: 2.5rem;
}
.PlaceBackground-Content {
  position: absolute;
  top: 40%;
  /* max-width: 968px; */
  font-size: calc(1em + 2vw);
  /* transform: translate(-50%, -50%); */
  z-index: 2;
  text-align: left;
  font-weight: bold;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}
.container {
  /* max-width: 1280x; */
  margin: auto;
  /* background-color: wheat; */
}
.category {
  /* background-color: wheat; */
  padding: 1rem 0rem;
  // max-width: 1200px;
}
/* #place-category { */
/* width: 100%; */
/* } */
.placeRecommend {
  margin-top: 4rem;
  padding: 1rem 0rem;
  /* background-color: wheat; */
}
.placeRecentView {
  margin-top: 4rem;
  padding: 1rem 0rem;

  /* background-color: wheat; */
}
.placeFlame {
  width: 20px;
  vertical-align: 0;
}
.footer {
  /* background-color: wheat; */
  margin-top: 6rem;
}
</style>
