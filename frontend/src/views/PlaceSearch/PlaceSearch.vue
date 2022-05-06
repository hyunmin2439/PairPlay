<template>
  <div>
    <!-- 헤더 -->
    <div style="max-width: 1400px; margin: auto">
      <Header></Header>
    </div>
    <!-- <hr style="margin-top: 0px; margin-bottom: 0px; color: #b7b7b7" /> -->
    <place-search-filters @searchFiltersData="getSearchFiltersData"></place-search-filters>
    <div class="container PlaceSearchContentFrame">
      <div class="placeSearchContent container d-flex justify-content-around align-items-start">
        <div class="mt-4 col">
          <div class="placeSearchTitle mb-3 ps-2">부산 금정구 운동시설</div>
          <div class="py-2">
            <div class="placeSearchList">
              <place-search-list v-for="card in cards" :key="card.idx" :card="card" class="placeSearchList me-3 col"> </place-search-list>
            </div>
          </div>
          <!-- <place-search-maps class="placeSearchMaps col-6" style="height: 800px">아아</place-search-maps> -->
        </div>
        <!-- <place-search-maps class="col placeSearchMaps">아아</place-search-maps> -->
      </div>
    </div>
    <nav aria-label="...">
      <ul class="pagination">
        <li class="page-item">
          <a class="page-link" style="font-family: bootstrap-icons"> &#xF284; </a>
        </li>
        <!-- aria-current="page" -->
        <li v-for="idx in totalPages" :key="idx" class="page-item">
          <a class="page-link" href="#">{{ idx }}</a>
        </li>
        <li class="page-item active">
          <a class="page-link" href="#">10</a>
        </li>
        <li class="page-item">
          <a class="page-link" style="font-family: bootstrap-icons" href="#">&#xF285;</a>
        </li>
      </ul>
    </nav>
    <footer>푸터</footer>
  </div>
</template>

<script>
import { onMounted, computed, ref, reactive, watch } from "vue";
import { useStore } from "vuex";
import Header from "../Common/Header.vue";
import PlaceSearchFilters from "./Components/PlaceSearchFilters.vue";
import PlaceSearchList from "./Components/PlaceSearchList.vue";
// import PlaceSearchMaps from "./Components/PlaceSearchMaps.vue";
export default {
  name: "PlaceSearch",
  // PlaceSearchMaps
  components: { Header, PlaceSearchFilters, PlaceSearchList },
  setup() {
    const store = useStore();
    let cards = reactive();
    let totalPages = ref(5);
    let searchFiltersData = ref({
      page: 0,
      size: 6,
      categoryList: "",
      endData: "",
      gugun: "",
      sido: "",
      startData: "",
    });
    const getSearchFiltersData = (res) => {
      console.log(res, "불러와지나요 1");
      searchFiltersData.value = res;
      console.log(searchFiltersData.value, "맞나");
    };
    const getCards = async () => {
      await store
        .dispatch("root/getPlaceSearchInfo", searchFiltersData.value)
        .then((res) => console.log(res, "데이터 나오나 get cards"))
        .catch((err) => console.log(err, "error"));
      if (store.state.placeSearchInfo) {
        cards.push(computed(() => store.state.placeSearchInfo));
      }
    };
    onMounted(() => {
      getCards();
    });
    watch(searchFiltersData.value, async () => {
      await getSearchFiltersData();
      await getCards()
        .then((res) => {
          console.log(res, "뭔데이거된건가");
        })
        .catch((err) => {
          console.log(err);
        });
    });
    // const cards = reactive([]);

    // const store = userStore();
    // const route = useRoute();
    return { cards, store, totalPages, onMounted, getCards, getSearchFiltersData };
  },
};
</script>

<style lang="scss" scoped>
// " {
// }
// .totalFrmae {
//   // height: 100vh;
// }

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
.page-item > .page-link {
  background: white;
  color: black;
  line-height: 2rem;
  font-weight: bold;
  border: #fafafa;
  border-radius: 50%;

  box-shadow: (0 0 8px rgba(24, 24, 24, 0.05));
  margin: 0rem 0.5rem 0rem 0.5rem;
  // border: 1px solid rgba(1, 1, 1, 0.1);
}
.active > .page-link {
  background: black;
  color: white;
  line-height: 2rem;
  font-weight: bold;
  border: #fafafa;
  border-radius: 50%;
  box-shadow: (0 0 8px rgba(24, 24, 24, 0.05));
  margin: 0rem 0.5rem 0rem 0.5rem;
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
