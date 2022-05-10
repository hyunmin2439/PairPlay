<template>
  <div>
    <!-- 헤더 -->
    <div style="max-width: 1400px; margin: auto">
      <Header></Header>
    </div>
    <!-- <hr style="margin-top: 0px; margin-bottom: 0px; color: #b7b7b7" /> -->
    <place-search-filters></place-search-filters>
    <div class="container PlaceSearchContentFrame">
      <div class="placeSearchContent container d-flex justify-content-around align-items-start">
        <div class="mt-4 col">
          <div class="placeSearchTitle mb-3 ps-2">부산 {{}} 운동시설</div>
          <div class="py-2">
            <div v-if="cards" class="placeSearchList">
              <place-search-list v-for="(card, idx) in cards" :key="idx" :card="card" :cardId="card.id" class="placeSearchList me-3 col"> </place-search-list>
              <!-- 페이지네이션 시작 -->
              <nav aria-label="Search results pages">
                <ul class="pagination">
                  <li class="page-item-left" @click="prevPages">
                    <a class="page-link" style="font-family: bootstrap-icons"> &#xF284; </a>
                  </li>
                  <!-- aria-current="page" -->
                  <li v-for="(page, idx) in totalPages" @click="changePage($event)" :key="idx" class="page-item">
                    <a class="page-link">{{ idx + 1 }}</a>
                  </li>
                  <!-- <li class="page-item"> -->
                  <!-- <a class="page-link" href="#">{{ nowpage }}</a> -->
                  <!-- </li> -->
                  <li class="page-item-right" @click="nextPages">
                    <a class="page-link" style="font-family: bootstrap-icons">&#xF285;</a>
                  </li>
                </ul>
              </nav>
              <!-- 페이지네이션 끝 -->
            </div>
            <div v-else><h1>아아아</h1></div>
          </div>
        </div>
        <place-search-maps class="col placeSearchMaps"></place-search-maps>
      </div>
    </div>
    <footer>푸터</footer>
  </div>
</template>

<script>
import { onMounted, ref, reactive, computed } from "vue";
import { useStore } from "vuex";
// import { useRoute } from "vue-router";
import Header from "../Common/Header.vue";
import PlaceSearchFilters from "./Components/PlaceSearchFilters.vue";
import PlaceSearchList from "./Components/PlaceSearchList.vue";
import PlaceSearchMaps from "./Components/PlaceSearchMaps.vue";
export default {
  name: "PlaceSearch",
  components: { Header, PlaceSearchFilters, PlaceSearchList, PlaceSearchMaps },
  setup() {
    const store = useStore();
    // console.log(store.state.root.addPlaceFilters);
    // const route = useRoute();
    let cards = reactive(computed(() => store.state.root.placeSearchInfo.placeList));
    // let cards = reactive(computed(() => store.getters["root/getPlaceInfo"]));
    let totalPages = ref(computed(() => store.state.root.placeSearchInfo.totalPages));
    let nowPage = ref(computed(() => searchFiltersData.value["page"]));
    let searchFiltersData = reactive(
      computed(() => store.state.root.addPlaceFilters)
      // categoryList: [route.params.categoryList],
      // categoryList: ref(Object.values(store.state.root.selectSportsCategory)).value,
      // endDate: "",
      // gugun: "",
      // page: 0,
      // sido: "",
      // searchWord: "",
      // startDate: "",
    );
    const getCards = async () => {
      await store.dispatch("root/getPlaceSearchInfo", searchFiltersData.value);
      if (store.state.root.placeSearchInfo) {
        totalPages = store.state.root.placeSearchInfo.totalPages;
      }
    };
    const prevPages = async () => {
      for (var i = 0; i < totalPages.value; i++) {
        var Btn = document.getElementsByClassName("page-item")[searchFiltersData.value["page"]];
        Btn.classList.remove("active");
      }
      if (searchFiltersData.value["page"] == 0) {
        searchFiltersData.value["page"] = 0;
        var activeBtn = document.getElementsByClassName("page-item")[searchFiltersData.value["page"]];
      } else {
        searchFiltersData.value["page"] -= 1;
        activeBtn = document.getElementsByClassName("page-item")[searchFiltersData.value["page"]];
      }

      activeBtn.classList.add("active");
      searchFiltersData.value["page"];
      await store.dispatch("root/getPlaceSearchInfo", searchFiltersData.value);
      console.log(nowPage.value);
    };
    const nextPages = async () => {
      for (var i = 0; i < totalPages.value; i++) {
        var Btn = document.getElementsByClassName("page-item")[i];
        Btn.classList.remove("active");
        activeBtn = document.getElementsByClassName("page-item")[searchFiltersData.value["page"]];
      }
      if (searchFiltersData.value["page"] == totalPages.value - 1) {
        searchFiltersData.value["page"] == totalPages.value - 1;
        var activeBtn = document.getElementsByClassName("page-item")[searchFiltersData.value["page"]];
      } else {
        searchFiltersData.value["page"] += 1;
        activeBtn = document.getElementsByClassName("page-item")[searchFiltersData.value["page"]];
      }

      activeBtn.classList.add("active");
      searchFiltersData.value["page"];
      await store.dispatch("root/getPlaceSearchInfo", searchFiltersData.value);
      console.log(nowPage.value);
    };
    const changePage = async (event) => {
      console.log(event.target.textContent);
      if (Number(event.target.textContent) <= 1) {
        var activeBtn = document.getElementsByClassName("page-item")[0];
      } else if (Number(event.target.textContent) >= totalPages.value) {
        activeBtn = document.getElementsByClassName("page-item")[Number(event.target.textContent) - 1];
      } else {
        activeBtn = document.getElementsByClassName("page-item")[Number(event.target.textContent) - 1];
      }
      for (var i = 0; i < totalPages.value; i++) {
        var Btn = document.getElementsByClassName("page-item")[i];
        Btn.classList.remove("active");
      }
      // console.log(Number(event.target.textContent));
      activeBtn.classList.add("active");
      searchFiltersData.value["page"] = Number(event.target.textContent) - 1;
      await store.dispatch("root/getPlaceSearchInfo", searchFiltersData.value);
    };
    onMounted(async () => {
      nowPage.value = computed(() => store.state.root.placeSearchInfo.page);

      // nowPage = computed(() => store.state.root.placeSearchInfo.page);
      // console.log(store.state.root.placeSearchInfo.page, "지금페이지");

      // if (nowPage.value == undefined) {
      //   nowPage.value = 0;
      // }
      var activeBtn = document.getElementsByClassName("page-item")[nowPage.value];
      activeBtn.classList.add("active");
      // if (store.state.root.searchFiltersData.categoryList == []) {
      //   store.state.root.placeSearchInfo.categoryList = store.state.root.selectSportsCategory;
      // }
      // await getCards();
    });

    return { cards, store, totalPages, nowPage, onMounted, changePage, /*getSearchFiltersData*/ getCards, prevPages, nextPages };
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
.pagination {
  margin: auto;
  justify-content: center;
}
.page-item > .page-link {
  background: white;
  color: black;
  line-height: 2rem;
  font-weight: bold;
  border: #fafafa;
  border-radius: 50%;
  cursor: pointer;
  box-shadow: (0 0 8px rgba(24, 24, 24, 0.05));
  margin: 0rem 0.5rem 0rem 0.5rem;
  // border: 1px solid rgba(1, 1, 1, 0.1);
}
.page-item-left > .page-link {
  background: white;
  color: black;
  line-height: 2rem;
  font-weight: bold;
  border: #fafafa;
  border-radius: 50%;
  cursor: pointer;
  box-shadow: (0 0 8px rgba(24, 24, 24, 0.05));
  margin: 0rem 0.5rem 0rem 0.5rem;
}
.page-item-right > .page-link {
  background: white;
  color: black;
  line-height: 2rem;
  font-weight: bold;
  border: #fafafa;
  border-radius: 50%;
  cursor: pointer;
  box-shadow: (0 0 8px rgba(24, 24, 24, 0.05));
  margin: 0rem 0.5rem 0rem 0.5rem;
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
