<template>
  <div>
    <div style="max-width: 1255px; margin: auto;">
      <Header></Header>
    </div>
    <hr style="margin-top: 0px; margin-bottom: 0px; color: #b7b7b7;">
    <div style="max-width: 1255px; margin: auto;">
      <div class="PlaceBackgroundImage">
        <div class="PlaceBackgroundImageCover">
          <div class="PlaceBackground-Content ms-5 ps-5">
            <p class="ps-5 ms-4">
              당신에게 적합한 <br />
              운동메이트를 찾아보세요.
            </p>
          </div>
        </div>
      </div>
    </div>
    <div style="max-width: 1280px; margin: auto; margin-bottom: 5%;">
      <mate-filters></mate-filters>
      <mate-view></mate-view>
      <!-- <nav aria-label="...">
        <ul class="pagination justify-content-center my-5">
          <li class="page-item disabled">
            <a class="page-link">Previous</a>
          </li>
          <li class="page-item"><a class="page-link" href="#">1</a></li>
          <li class="page-item active" aria-current="page">
            <a class="page-link" href="#">2</a>
          </li>
          <li class="page-item"><a class="page-link" href="#">3</a></li>
          <li class="page-item"><a class="page-link" href="#">4</a></li>
          <li class="page-item"><a class="page-link" href="#">5</a></li>
          <li class="page-item">
            <a class="page-link" href="#">Next</a>
          </li>
        </ul>
      </nav> -->
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import Header from "../Common/Header.vue";
import Footer from "../Common/Footer.vue";
import MateFilters from "./Components/MateFilters.vue";
import MateView from "./Components/MateView.vue";

import { onMounted, reactive } from 'vue'
import { useStore } from 'vuex'
export default {
  components: { MateFilters, MateView, Header, Footer },
  name: "Mate",
  setup() {
    const store = useStore()
    const state = reactive({
      // cards: computed(() => store.getters["root/mateArticleList"])
    })
    onMounted(() => {
      store.dispatch("root/mateArticleList", {
        "page": 0,
        "size": 8,
      })
    })
    return { state, onMounted }
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
  height: 330px;
  background: black;
  margin-bottom: 5rem;
  /* background-color: rgba(0, 0, 0, 0.4); */
  background: url("https://images.pexels.com/photos/3601094/pexels-photo-3601094.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260") 50% 40% no-repeat;
  background-size: 100% auto;
}
.PlaceBackgroundImageCover {
  position: absolute;
  width: 1255px;
  height: 330px;
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
    width: 1255px;
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
</style>