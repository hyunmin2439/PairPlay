<template>
  <div class="d-flex justify-content-start align-items-center">
    <!--  -->
    <!-- Default dropend button -->
    <!-- <div class="input-group serachbar">search</div> -->
    <div class="d-flex align-items-center">
      <place-search-filters-region class="btn placeSearchFiltersRegion btnPlace"></place-search-filters-region>
      <place-search-filters-price></place-search-filters-price>
      <place-search-filters-time></place-search-filters-time>
      <place-search-filter-etc></place-search-filter-etc>
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
    <div>
      <div class="input-group flex-nowrap">
        <!-- <span class="input-group-text" id="addon-wrapping">@</span> -->
        <input type="text" class="form-control serachbar cl" placeholder="&#xf52a; search" aria-label="Username" aria-describedby="addon-wrapping" />
      </div>
    </div>
  </div>
</template>

<script>
import PlaceSearchFilterEtc from "./PlaceSearchFilterEtc.vue";
import PlaceSearchFiltersPrice from "./PlaceSearchFiltersPrice.vue";
import PlaceSearchFiltersRegion from "./PlaceSearchFiltersRegion.vue";
import PlaceSearchFiltersTime from "./PlaceSearchFiltersTime.vue";
// import Slider from "@vueform/slider";

export default {
  name: "PlaceSearchFilters",
  components: { PlaceSearchFiltersRegion, PlaceSearchFiltersPrice, PlaceSearchFiltersTime, PlaceSearchFilterEtc },
  setup() {
    // let value = [20, 40];
    // const format = (value) => {
    //   return `${value}원`;
    // };

    // const refresh = () =>
    return {};
  },
};
</script>
<style src="@vueform/slider/themes/default.css"></style>

<style lang="scss" scoped>
// .dropdown-menu:checked {
//   display: block;
// }
.serachbar {
  margin: 0px 0px 0px 10px;
  max-width: 200px;
  background: #ffff;
  border-radius: 5px;
  box-shadow: 0 1px 10px rgba(24, 24, 24, 0.04);
  // box-shadow: (0 0 8px rgba(24, 24, 24, 0.04));
  border: 1px solid rgba(1, 1, 1, 0.1);
  font-size: 14px;
  font-family: "bootstrap-icons", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
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
