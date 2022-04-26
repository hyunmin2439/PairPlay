<template>
  <div>
    <!-- 헤더 -->
    <header>헤더</header>
    <div class="PlaceBackgroundImage">
      <div class="PlaceBackgroundImageCover">
        <div class="PlaceBackground-Content ms-5 ps-5">
          당신에게 적합한 <br />
          운동장소를 찾아보세요.
        </div>
      </div>
    </div>
    <div class="container">
      <!-- <header></header> -->
      <div class="category container">
        <div class="category-title ps-3 mt-5 mb-4">
          <div class="fs-3 fw-bold">운동 카테고리</div>
          <div>원하는 운동 카테고리를 선택해보세요.</div>
        </div>
        <place-category></place-category>
      </div>
      <div class="recommendPlaceForUser">유저를 위한 추천</div>
      <div class="nowdaysPlace">최근본 장소</div>
    </div>
    <footer>푸터</footer>
  </div>
</template>

<script>
import PlaceCategory from "./Components/PlaceCategory.vue";
export default {
  components: { PlaceCategory },
  name: "Place",

  setup() {
    // const store = userStore();
    // const route = useRoute();
  },
};
</script>

<style>
header {
  background-color: wheat;
  /* width: 100%; */
}
.PlaceBackgroundImage {
  width: 100%;
  height: 330px;
  background: black;
  margin-bottom: 5rem;
  background-color: rgba(0, 0, 0, 0.4);
  background: url("https://images.pexels.com/photos/3601094/pexels-photo-3601094.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260") 50% 40% no-repeat;
  background-size: 100% auto;
}
.PlaceBackgroundImageCover {
  position: absolute;
  width: 100%;
  height: 330px;
  background-color: rgba(0, 0, 0, 0.3);
  z-index: 1;
  color: white;
}
.PlaceBackground-Content {
  position: absolute;
  top: 40%;
  /* max-width: */
  font-size: calc(1em + 2vw);
  /* transform: translate(-50%, -50%); */
  z-index: 2;
  text-align: left;
  font-weight: bold;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}
.container {
  /* max-width: 1280px; */
  margin: auto;
  background-color: wheat;
}
.category {
  background-color: wheat;
  padding: 2rem;
}
.recommendPlaceForUser {
  background-color: wheat;
}
.nowdaysPlace {
  background-color: wheat;
}
footer {
  background-color: wheat;
}
</style>
