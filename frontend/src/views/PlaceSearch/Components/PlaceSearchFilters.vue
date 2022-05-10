<template>
  <div class="placeSearchFiltersTotalFrame">
    <div class="placeSearchFilters d-flex justify-content-between align-items-center">
      <!--  -->
      <!-- Default dropend button -->
      <!-- <div class="input-group serachbar">search</div> -->
      <div class="d-flex align-items-center col-lg-9 filters">
        <place-search-filters-region @regionData="selectRegion"></place-search-filters-region>
        <place-search-filters-price></place-search-filters-price>
        <place-search-filters-time @timeData="selectTime"></place-search-filters-time>
        <place-search-filter-sports-category @sportsCategoryData="selectSportsCategory"></place-search-filter-sports-category>
        <div class="btn btn-Cancel btn-secondary" type="button" @click="cancelFilters">초기화</div>
        <!-- <div class="btn-group">
        <button type="button" class="btn btnPlace dropdown-toggle" data-bs-toggle="dropdown" data-bs-auto-close="outside" :aria-expanded="`${expand}`">Price</button>
        <ul class="dropdown-menu ps-5" style="width: 400px; height: 300px; font-size: 14px">
          <Slider v-model="value" type="input" :format="format" />
          <div class="d-flex justify-content-around">
            <button type="button" class="btn btn-dark mb-2" @click="changedExpand">적용</button>
            <button type="button" class="btn btn-outline-secondary mb-2">취소</button>
          </div>
        </ul>
      </div> -->
        <!-- 지역 -->
      </div>
      <div class="col-lg-2">
        <div class="input-group flex-nowrap">
          <!-- <span class="input-group-text" id="addon-wrapping">@</span> -->
          <input type="text" class="form-control serachbar" placeholder="&#xf52a; search" aria-label="Username" aria-describedby="addon-wrapping" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, watch } from "vue";
import PlaceSearchFiltersPrice from "./PlaceSearchFiltersPrice.vue";
import PlaceSearchFiltersRegion from "./PlaceSearchFiltersRegion.vue";
import PlaceSearchFiltersTime from "./PlaceSearchFiltersTime.vue";
import PlaceSearchFilterSportsCategory from "./PlaceSearchFilterSportsCategory.vue";
import { useStore } from "vuex";

// import Slider from "@vueform/slider";

export default {
  name: "PlaceSearchFilters",
  // emits: ["searchFiltersData"],
  components: { PlaceSearchFiltersRegion, PlaceSearchFiltersPrice, PlaceSearchFiltersTime, PlaceSearchFilterSportsCategory },
  setup(/*_, { emit }*/) {
    const store = useStore();
    let searchFiltersData = ref({
      price: "",
      sido: "",
      gugun: "",
      startDate: "",
      endDate: "",
      categoryList: [],
      page: 0,
    });
    // console.log(store.state.root.addPlaceFilters, "store.state.root.addPlaceFilters");
    const selectRegion = (res) => {
      console.log(res, "지역나오나");
      // console.log(res, "나옵니까");
      searchFiltersData.value.sido = res.sido;
      searchFiltersData.value.gugun = res.gugun;
    };
    const selectTime = (res) => {
      let startTime = new Date(+res[0] + 3240 * 10000).toISOString().replace("T", " ").replace(/\..*/, "").substring(0, 10);
      let endTime = new Date(+res[1] + 3240 * 10000).toISOString().replace("T", " ").replace(/\..*/, "").substring(0, 10);
      searchFiltersData.value.startDate = startTime;
      searchFiltersData.value.endDate = endTime;
    };
    const selectSportsCategory = (res) => {
      if (res) {
        //   console.log(res, "고른 스포츠");
        searchFiltersData.value.categoryList = res;
      } else {
        searchFiltersData.value.categoryList = store.state.root.selectSportsCategory;
      }
      // console.log(res);
    };
    // searchFiltersData.value.region.sido = regionData.sido;
    // searchFiltersData.value.region.gugun = regionData.gugun;
    // let value = [20, 40];
    // const format = (value) => {
    //   return `${value}원`;
    // };
    // console.log(searchFiltersData, "아아");
    const cancelFilters = () => {
      searchFiltersData.value.price = "";
      searchFiltersData.value.sido = "";
      searchFiltersData.value.gugun = "";
      searchFiltersData.value.startDate = "";
      searchFiltersData.value.endDate = "";
      searchFiltersData.value.categoryList = [];
      searchFiltersData.value.page = 0;
    };
    watch(searchFiltersData.value, async () => {
      // console.log(res, "맞나이거");
      // console.log(searchFiltersData.value, "searchFiltersData.value");
      // emit("searchFiltersData", searchFiltersData.value);
      await store.dispatch("root/addPlaceFilters", searchFiltersData.value);
    });
    // const refresh = () =>
    return { searchFiltersData, selectRegion, selectTime, selectSportsCategory, cancelFilters };
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
    border: 0.5px solid black;
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
  margin: 10px 5px 10px 5px;
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
