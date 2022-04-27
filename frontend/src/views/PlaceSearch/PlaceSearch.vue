<template>
  <div>
    <!-- 헤더 -->
    <Header></Header>
    <div class="container">
      <place-search-filters class="mx-2 pb-2 mb-3"></place-search-filters>
      <div class="placeSearchContent container d-flex justify-content-around align-items-start">
        <div class="mt-4">
          <div class="placeSearchTitle fw-bold mb-2 ps-3">2000건 운동장소</div>
          <div class="py-2">
            <div class="placeSearchList">
              <place-search-list v-for="card in cards" :key="card.idx" :card="card" class="placeSearchList col"> </place-search-list>
            </div>
          </div>
          <!-- <place-search-maps class="placeSearchMaps col-6" style="height: 800px">아아</place-search-maps> -->
        </div>
        <div class="placeSearchMaps">카드맵</div>
      </div>
    </div>
    <footer>푸터</footer>
  </div>
</template>

<script>
import Header from "../Common/Header.vue";
import PlaceSearchFilters from "./Components/PlaceSearchFilters.vue";
import PlaceSearchList from "./Components/PlaceSearchList.vue";
// import PlaceSearchMaps from "./Components/PlaceSearchMaps.vue";
export default {
  name: "PlaceSearch",
  // PlaceSearchMaps
  components: { Header, PlaceSearchFilters, PlaceSearchList },
  setup() {
    const cards = [
      {
        title: "장정구 복싱 체육관",
        position: "부산 연제구",
        imgUrl: "https://t4.ftcdn.net/jpg/00/83/15/95/240_F_83159570_JOI1NtD7MzEOqzTYIz3eTOhEm20HgGzp.jpg",
        facility: "잔디, 바닥, 샤워",
        rate: "3.9",
        cost: "10,000원",
      },
      {
        title: "위닝 축구 클럽",
        position: "부산 금정구",
        imgUrl: "https://t4.ftcdn.net/jpg/02/67/46/51/240_F_267465121_hbjzEm2kJc9yowdM1TqCwdgNYZA76fse.jpg",
        facility: "잔디, 바닥, 샤워",
        rate: "3.9",
        cost: "시간당 10,000원 ",
      },
      {
        title: "피플 농구 gym",
        position: "부산 해운대구",
        imgUrl: "https://t3.ftcdn.net/jpg/02/10/17/94/240_F_210179424_mTLrEUOv1bbiYHW7kGjq2xRmr73rfcGI.jpg",
        facility: "바닥, 농구 공 대여 가능, 장비 구매 가능, 샤워장",
        rate: "4.5",
        cost: "시간당 10,000원 ",
      },
      {
        title: "해운대 실내 풋살장",
        position: "부산 해운대구",
        imgUrl: "https://t3.ftcdn.net/jpg/02/10/17/94/240_F_210179424_mTLrEUOv1bbiYHW7kGjq2xRmr73rfcGI.jpg",
        facility: "인조잔디, 공 대여, 신발대여가능 샤워",
        rate: "4.0",
        cost: "시간당 10,000원 ",
      },
      {
        title: "사하구 테니스실내코트장",
        position: "부산 사하구",
        imgUrl: "https://t3.ftcdn.net/jpg/02/10/17/94/240_F_210179424_mTLrEUOv1bbiYHW7kGjq2xRmr73rfcGI.jpg",
        facility: "잔디, 테니스공 및 라켓 대여가능, 조명, 샤워시설",
        rate: "4.1",
        cost: "시간당 10,000원 ",
      },
    ];
    // const store = userStore();
    // const route = useRoute();
    return { cards };
  },
};
</script>

<style lang="scss" scoped>
// " {
// }
.totalFrmae {
  // height: 100vh;
}
.container {
  // padding: 0px 0px 0px 0px;
  // margin: 0px 0px 0px 0px;
  // width: 100vw;
}
.placeSearchContent {
  border-top: 1px solid rgba(1, 1, 1, 0.1);
}
.placeSearchTitle {
  font-size: 16px;
}
// .placeSearchListCards {
// padding-left: 5px;
// padding-right: 5px;
// }
.placeSearchList {
  width: 100%;

  // width: 50%;
  // background-color: wheat;
  max-height: 500px;
  overflow-y: overlay;
  overflow-x: overlay;
  scrollbar-width: 0px;
}
::-webkit-scrollbar {
  // display: none;
  width: 0px;
}

.placeSearchMaps {
  width: 60%;
  background: black;
  // height: 600px;
}
</style>
