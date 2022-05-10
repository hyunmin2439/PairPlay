<template>
  <div class="placeSearchFiltersTotalFrame">
    <div class="placeSearchFilters d-flex justify-content-between align-items-center">
      <div class="d-flex align-items-center col-lg-8 filters">
        <mate-filters-region @regionData="selectRegion"></mate-filters-region>
        <mate-filters-time @timeData="selectTime"></mate-filters-time>
        <mate-filters-sports-category @sportsCategoryData="selectSportsCategory"></mate-filters-sports-category>
        <div class="btn btn-Cancel btn-secondary" type="button" @click="cancelFilters">초기화</div>
        <!-- 지역 -->
      </div>
      <div class="col-lg-4">
        <div class="input-group flex-nowrap">
          <input type="text" class="form-control serachbar" placeholder="&#xf52a; search" aria-label="Username" aria-describedby="addon-wrapping" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, watch } from "vue";
import MateFiltersRegion from "./MateFiltersRegion.vue";
import MateFiltersTime from "./MateFiltersTime.vue";
import MateFiltersSportsCategory from "./MateFiltersSportsCategory.vue";

// import Slider from "@vueform/slider";
import { useStore } from 'vuex'
export default {
  name: "MateFilters",
  emits: ["searchFiltersData"],
  components: { 
    MateFiltersRegion, 
    MateFiltersTime, 
    MateFiltersSportsCategory
  },
  setup(_, { emit }) {
    const store = useStore()
    let searchFiltersData = ref({
      price: "",
      region: { sido: "", gugun: "" },
      startDate: "",
      endDate: "",
      categoryList: [],
    });
    const selectRegion = (res) => {
      // console.log(res, "나옵니까");
      searchFiltersData.value.region = res;
    };
    const selectTime = (res) => {
      let startTime = new Date(+res[0] + 3240 * 10000).toISOString().replace("T", " ").replace(/\..*/, "").substring(0, 10);
      let endTime = new Date(+res[1] + 3240 * 10000).toISOString().replace("T", " ").replace(/\..*/, "").substring(0, 10);
      searchFiltersData.value.startDate = startTime;
      searchFiltersData.value.endDate = endTime;
    };
    const selectSportsCategory = (res) => {
      // console.log(res);
      searchFiltersData.value.categoryList = res;
    };
    // searchFiltersData.value.region.sido = regionData.sido;
    // searchFiltersData.value.region.gugun = regionData.gugun;
    // let value = [20, 40];
    // const format = (value) => {
    //   return `${value}원`;
    // };
    // console.log(searchFiltersData, "아아");
    const cancelFilters = async function () {
      await store.dispatch("root/mateArticleList", {
        "page": 0,
        "size": 8,
      })
    };
    watch(searchFiltersData.value, () => {
      emit("searchFiltersData", searchFiltersData.value);
      // console.log(res);
      // console.log(searchFiltersData.value, "searchFiltersData");
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
