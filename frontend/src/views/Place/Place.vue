<template>
  <div>
    <!-- 헤더 -->
    <Header></Header>
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
    <div class="container" style="max-width: 1280px">
      <!-- <header></header> -->
      <div class="category container">
        <div class="category-title ps-3 mb-4">
          <div class="fs-3 fw-bold">운동 카테고리</div>
          <div>원하는 운동 카테고리를 선택해보세요.</div>
        </div>
        <place-category></place-category>
      </div>
      <div class="placeRecommend container">
        <div class="category-title ps-3 mt-3 mb-2 d-flex justify-content-between">
          <!-- <div class="fs-3 fw-bold">{{}}</div> -->
          <div class="fs-5 fw-bold">부산 강서구에서 인기있는 운동장소</div>
          <p class="fw-bold pe-2 pt-3" style="font-size: 16px">전체보기</p>
        </div>
        <place-recommend></place-recommend>
      </div>
      <div class="placeRecentView container my-5">
        <div class="category-title ps-3 mt-3 mb-2 d-flex justify-content-between">
          <div class="fs-5 fw-bold">최근 user 님이 조회한 장소</div>
          <p class="fw-bold pe-2 pt-3" style="font-size: 16px">전체보기</p>
        </div>
        <place-recent-view></place-recent-view>
      </div>
    </div>
    <footer>푸터</footer>
  </div>
</template>

<script>
import PlaceCategory from "./Components/PlaceCategory.vue";
import PlaceRecentView from "./Components/PlaceRecentView.vue";
import PlaceRecommend from "./Components/PlaceRecommend.vue";
import Header from "../Common/Header.vue";
export default {
  name: "Place",
  components: { Header, PlaceCategory, PlaceRecommend, PlaceRecentView },
  setup() {
    // const store = userStore();
    // const route = useRoute();
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
  padding: 1rem 2rem;
}
/* #place-category { */
/* width: 100%; */
/* } */
.placeRecommend {
  margin-top: 4rem;
  padding: 1rem 2.5rem;
  /* background-color: wheat; */
}
.placeRecentView {
  margin-top: 4rem;

  padding: 1rem 2.5rem;

  /* background-color: wheat; */
}
footer {
  /* background-color: wheat; */
}
</style>
