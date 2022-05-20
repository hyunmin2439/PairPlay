<template>
  <div class="placeSearchFiltersTotalFrame">
    <div class="placeSearchFilters d-flex justify-content-between align-items-center">
      <!--  -->
      <!-- Default dropend button -->
      <!-- <div class="input-group serachbar">search</div> -->
      <div class="d-flex align-items-center col-lg-9 filters">
        <place-search-filters-region @regionData="selectRegion"></place-search-filters-region>
        <!-- <place-search-filters-price></place-search-filters-price> -->
        <place-search-filter-sports-category @sportsCategoryData="selectSportsCategory"></place-search-filter-sports-category>
        <place-search-filters-time @timeData="selectTime"></place-search-filters-time>
        <div class="btn btn-Cancel btn-secondary" type="button" @click="cancelFilters">초기화</div>
        <!-- 지역 -->
      </div>
      <div class="col-lg-2">
        <div class="input-group flex-nowrap">
          <!-- <input @keyup.enter="inputWord($event)" type="text" class="form-control serachbar" placeholder="&#xf52a;  시설명, 해시태그 입력" aria-label="Username" aria-describedby="addon-wrapping" />           -->
          <input
            v-model="searchFiltersData.searchWord"
            type="text"
            class="form-control serachbar"
            placeholder="&#xf52a; 시설명, 해시태그 입력"
            aria-label="Username"
            aria-describedby="addon-wrapping"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive, ref, watch } from "vue";
// import PlaceSearchFiltersPrice from "./PlaceSearchFiltersPrice.vue";
import PlaceSearchFiltersRegion from "./PlaceSearchFiltersRegion.vue";
import PlaceSearchFiltersTime from "./PlaceSearchFiltersTime.vue";
import PlaceSearchFilterSportsCategory from "./PlaceSearchFilterSportsCategory.vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";

// import Slider from "@vueform/slider";

export default {
  name: "PlaceSearchFilters",
  // emits: ["searchFiltersData"],
  components: { PlaceSearchFiltersRegion, /*PlaceSearchFiltersPrice*/ PlaceSearchFiltersTime, PlaceSearchFilterSportsCategory },
  setup(/*_, { emit }*/) {
    const store = useStore();
    const router = useRouter();
    let searchFiltersData = reactive({
      price: "",
      sido: "",
      gugun: "",
      startDate: "",
      endDate: "",
      categoryList: [],
      page: 0,
      searchWord: "",
    });
    const word = ref("");
    const selectRegion = (res) => {
      searchFiltersData.sido = res.sido;
      searchFiltersData.gugun = res.gugun;
    };
    const selectTime = (res) => {
      let startTime = new Date(+res[0] + 3240 * 10000).toISOString().replace("T", " ").replace(/\..*/, "").substring(0, 10);
      let endTime = new Date(+res[1] + 3240 * 10000).toISOString().replace("T", " ").replace(/\..*/, "").substring(0, 10);
      searchFiltersData.startDate = startTime;
      searchFiltersData.endDate = endTime;
    };
    const selectSportsCategory = (res) => {
      if (res) {
        //   console.log(res, "고른 스포츠");
        searchFiltersData.categoryList = res;
      } else {
        searchFiltersData.categoryList = store.state.root.selectSportsCategory;
      }
      // console.log(res);
    };
    // const  async (e) => {
    //   word.value = e.target.value;
    //   console.log(word.value, "검색어추가함");
    //   await store.dispatch("root/addSearchWord", [searchFiltersData, word.value]);
    //   // searchFiltersData.searchWord = e.target.value;
    // };

    // const = () => {
    //   console.log(word.value, "검색어임");
    //   searchFiltersData.searchWord = word.value;
    //   console.log(searchFiltersData.value.searchWord, "추가됬음?");
    // };
    // const handleSearchInput = (e) => {
    //   word.value = e.target.value;
    //   if (word.value.length !== 0) {
    //     clearTimeout(debounce);
    //     const debounce = setTimeout(() => {
    //       const filteredList = this.stageInfoList.filter((item) => item.title.includes(word.value));
    //       searchFiltersData.searchWord = filteredList;
    //     }, 300);
    //   } else {
    //     clearTimeout(debounce);
    //     debounce = setTimeout(() => {
    //       searchFiltersData.searchWord = "";
    //     }, 500);
    //   }
    // };

    const changeFilters = async () => {
      console.log(searchFiltersData);
      await store.dispatch("root/addPlaceFilters", searchFiltersData);
    };
    const cancelFilters = () => {
      searchFiltersData.price = "";
      searchFiltersData.sido = "";
      searchFiltersData.gugun = "";
      searchFiltersData.startDate = "";
      searchFiltersData.endDate = "";
      searchFiltersData.categoryList = [];
      searchFiltersData.page = 0;
      searchFiltersData.page = 0;
      searchFiltersData.searchWord = "";
      router.go(0);
    };
    watch(searchFiltersData, async () => {
      // word.value = "";
      await changeFilters();
    });
    // watch(() => {
    //   if (word.value.length == 0) {
    //     async () => {
    //       // await changeFilters();
    //     };
    //   }
    // });
    // const refresh = () =>
    return { searchFiltersData, word, selectRegion, selectTime, selectSportsCategory, cancelFilters, changeFilters };
  },
};
</script>

<style src="@vueform/slider/themes/default.css"></style>

<style lang="scss" scoped>
// .dropdown-menu:checked {
//   display: block;
// }
.btn {
  &:hover {
    border: 0.02px solid rgba(1, 1, 1, 0.3);
  }
}
.placeSearchFiltersTotalFrame {
  border-bottom-width: 100vw;
  border-bottom: 1px solid rgba(1, 1, 1, 0.1);
}
.placeSearchFilters {
  // width: 100vw;
  margin: auto;
  padding: 0rem 0.5rem 1rem 0.5rem;
  max-width: 1400px;
}
.serachbar {
  margin: 0px 0px 0px 10px;
  // min-width: 200px;
  // max-width: 300px;
  background: #ffff;
  border-radius: 5px;
  box-shadow: 0 1px 10px rgba(24, 24, 24, 0.04);
  // box-shadow: (0 0 8px rgba(24, 24, 24, 0.04));
  border: 1px solid rgba(1, 1, 1, 0.1);
  font-size: 14px;
  font-family: "bootstrap-icons", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}
.btn-Cancel {
  margin: auto 0.5rem auto 0.8rem;
  // background: red;
  color: white;
  border-radius: 5px;
  // box-shadow: (0 1px 10px rgba(24, 24, 24, 0.04));
  box-shadow: (0 0 8px rgba(24, 24, 24, 0.04));
  border: 1px solid rgba(1, 1, 1, 0.1);
  font-size: 14px;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}
// .btnPlace {
//   margin: 10px 5px 10px 5px;
//   background: #ffff;
//   border-radius: 30px;
//   box-shadow: (0 1px 10px rgba(24, 24, 24, 0.04));
//   // box-shadow: (0 0 8px rgba(24, 24, 24, 0.04));
//   border: 1px solid rgba(1, 1, 1, 0.1);
//   font-size: 14px;
//   font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
// }
</style>
