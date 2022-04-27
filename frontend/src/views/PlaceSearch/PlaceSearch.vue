<template>
  <div>
    <!-- 헤더 -->
    <Header></Header>
    <place-search-filters></place-search-filters>
    <div class="d-flex justify-content-around align-items-center">
      <place-search-list class="placeSearchList"></place-search-list>
      <place-search-maps class="placeSearchMaps"></place-search-maps>
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
    // const store = userStore();
    // const route = useRoute();
  },
};
</script>

<style lang="scss" scoped>
.placeSearchList {
  width: 45%;
  background-color: wheat;
}
.placeSearchMaps {
  width: 55%;
  background: black;
}
</style>
