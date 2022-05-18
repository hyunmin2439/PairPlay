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
              당신에게 적합한 <br />
              운동메이트를 찾아보세요.
            </p>
          </div>
        </div>
      </div>
    </div>
    <div style="max-width: 1200px; margin: auto; margin-bottom: 5%;">
      <mate-filter></mate-filter>
      <mate-view></mate-view>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import Header from "../Common/Header.vue";
import Footer from "../Common/Footer.vue";
import MateFilter from "./Components/MateFilter.vue";
import MateView from "./Components/MateView.vue";

import { onMounted, reactive } from 'vue'
import { useStore } from 'vuex'
export default {
  components: { MateFilter, MateView, Header, Footer },
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
  height: 300px;
  background: black;
  margin-bottom: 5rem;
  /* background-color: rgba(0, 0, 0, 0.4); */
  background: url("https://images.pexels.com/photos/3601094/pexels-photo-3601094.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260") 50% 40% no-repeat;
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
  font-size: calc(1em + 2vw);
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
</style>