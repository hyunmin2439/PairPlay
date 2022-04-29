<template>
  <div>
    <!-- 헤더 -->
    <div style="max-width: 1400px; margin: auto">
      <Header></Header>
    </div>
    <hr style="margin-top: 0px; margin-bottom: 0px; color: #b7b7b7" />
    <place-search-filters class="placeSearchFilters"></place-search-filters>
    <div class="container PlaceSearchContentFrame">
      <div class="placeSearchContent container d-flex justify-content-around align-items-start">
        <div class="mt-4 col">
          <div class="placeSearchTitle mb-3 ps-3">부산 금정구 운동시설</div>
          <div class="py-2">
            <div class="placeSearchList">
              <place-search-list v-for="card in cards" :key="card.idx" :card="card" class="placeSearchList col"> </place-search-list>
            </div>
          </div>
          <!-- <place-search-maps class="placeSearchMaps col-6" style="height: 800px">아아</place-search-maps> -->
        </div>
        <place-search-maps class="col placeSearchMaps">아아</place-search-maps>
      </div>
    </div>
    <footer>푸터</footer>
  </div>
</template>

<script>
import Header from "../Common/Header.vue";
import PlaceSearchFilters from "./Components/PlaceSearchFilters.vue";
import PlaceSearchList from "./Components/PlaceSearchList.vue";
import PlaceSearchMaps from "./Components/PlaceSearchMaps.vue";
export default {
  name: "PlaceSearch",

  components: { Header, PlaceSearchFilters, PlaceSearchList, PlaceSearchMaps },
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
        title: "위닝 헬스 클럽",
        position: "부산 금정구",
        imgUrl: "https://t4.ftcdn.net/jpg/02/67/46/51/240_F_267465121_hbjzEm2kJc9yowdM1TqCwdgNYZA76fse.jpg",
        facility: "스미스 머신, 치닝디핑 바, 락커룸, 샤워",
        rate: "3.9",
        cost: "시간당 10,000원 ",
      },
      {
        title: "피플 농구 gym",
        position: "부산 해운대구",
        imgUrl: "https://t3.ftcdn.net/jpg/04/95/38/22/240_F_495382282_buPZ5PEupMPyv1DzNnzKMtXdr4gDBVCC.jpg",
        facility: "바닥, 농구 공 대여 가능, 장비 구매 가능, 샤워장",
        rate: "4.5",
        cost: "시간당 10,000원 ",
      },
      {
        title: "해운대 실내 풋살장",
        position: "부산 해운대구",
        imgUrl: "https://t3.ftcdn.net/jpg/01/26/12/78/240_F_126127819_122Ec37M5l4ptz1AGZ4BsIp396yKm7se.jpg",
        facility: "인조잔디, 공 대여, 신발대여가능 샤워",
        rate: "4.0",
        cost: "시간당 10,000원 ",
      },
      {
        title: "부산 사하구 테니스실내코트장",
        position: "부산 사하구",
        imgUrl: "https://t3.ftcdn.net/jpg/01/66/25/70/240_F_166257070_l1cxVvzdBhzdtNDXro6Mibif2x80ovoP.jpg",
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
// .totalFrmae {
//   // height: 100vh;
// }
.placeSearchFilters {
  // width: 100vw;
  padding: 0rem 0rem 1rem 5%;
  border-bottom: 1px solid rgba(1, 1, 1, 0.1);
}
.PlaceSearchContentFrame {
  width: 100%;

  // width: 50%;
  // background-color: wheat;
  max-height: 70vh;
  overflow-y: overlay;
  // overflow-x: overlay;
  // scrollbar-width: 0px;
  max-width: 100vw;
}
.PlaceSearchContentFrame {
}
// ::-webkit-scrollbar {
//   // display: none;
//   // width: 0px;
// }
.placeSearchContent {
  max-width: 1400px;
}
.placeSearchTitle {
  font-size: 16px;
}

// .placeSearchListCards {
// padding-left: 5px;
// padding-right: 5px;
// }

.placeSearchMaps {
  display: block;
  height: unset;
  position: sticky;
  top: 0px;
  visibility: unset;
  width: unset;
  //   width: 55%;
  // background: black;
  // height: 600px;
}
</style>
