<template>
  <div class="placeSearchFilters">
    <!--  -->
    <!-- Default dropend button -->

    <!--  -->
    <!--  -->
    <place-search-filters-region class="btn btnPlace"></place-search-filters-region>
    <div class="btn-group">
      <button type="button" class="btn btnPlace dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">Price</button>
      <ul class="dropdown-menu ps-5" style="width: 400px; height: 300px; font-size: 14px">
        <Slider v-model="value" :format="format" />
      </ul>
    </div>
    <!-- 지역 -->
    <div class="btn-group">
      <button type="button" class="btn btnPlace dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">Action</button>
      <ul class="dropdown-menu ps-5" style="width: 400px; height: auto; font-size: 14px; border-radius: 15px">
        <div class="d-flex my-4">
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" />
            <label class="form-check-label" for="flexCheckChecked"> 서울특별시</label>
          </div>
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" />
            <label class="form-check-label" for="flexCheckChecked"> 경기 </label>
          </div>
        </div>
        <div class="d-flex my-4">
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" />
            <label class="form-check-label" for="flexCheckDefault"> 인천 </label>
          </div>
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" />
            <label class="form-check-label" for="flexCheckChecked"> 강원 </label>
          </div>
        </div>
        <div class="d-flex my-4">
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" />
            <label class="form-check-label" for="flexCheckDefault"> 제주 </label>
          </div>
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" />
            <label class="form-check-label" for="flexCheckChecked"> 부산 </label>
          </div>
        </div>
        <div class="d-flex my-4">
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" />
            <label class="form-check-label" for="flexCheckDefault"> 경남 </label>
          </div>
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" />
            <label class="form-check-label" for="flexCheckChecked"> 대구 </label>
          </div>
        </div>
        <div class="d-flex my-4">
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" />
            <label class="form-check-label" for="flexCheckDefault"> 경북 </label>
          </div>
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" />
            <label class="form-check-label" for="flexCheckChecked"> 울산 </label>
          </div>
        </div>
        <div class="d-flex my-4">
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" />
            <label class="form-check-label" for="flexCheckDefault"> 대전 </label>
          </div>
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" />
            <label class="form-check-label" for="flexCheckChecked"> 충남 </label>
          </div>
        </div>
        <div class="d-flex my-4">
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" />
            <label class="form-check-label" for="flexCheckDefault"> 충북 </label>
          </div>
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" />
            <label class="form-check-label" for="flexCheckChecked"> 광주 </label>
          </div>
        </div>
        <div class="d-flex my-4">
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" />
            <label class="form-check-label" for="flexCheckDefault"> 전남 </label>
          </div>
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" />
            <label class="form-check-label" for="flexCheckChecked"> 전북 </label>
          </div>
        </div>
        <div class="d-flex">
          <button type="button" class="btn btn-outline-primary mb-4">적용</button>
          <button type="button" class="btn btn-outline-primary mb-4">취소</button>
        </div>
      </ul>
    </div>
    <!-- 부산지역 -->
    <div class="btn-group">
      <button type="button" class="btn btnPlace dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">부산지역</button>
      <ul class="dropdown-menu" style="width: 250px; height: auto; border-radius: 15px">
        <div class="my-3 fw-bold fs-5 ps-4">부 산</div>
        <div class="d-flex my-4 ps-4">
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" />
            <label class="form-check-label" for="flexCheckChecked"> 중구 </label>
          </div>
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" />
            <label class="form-check-label" for="flexCheckChecked"> 서구 </label>
          </div>
        </div>
        <div class="d-flex my-4 ps-4">
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" />
            <label class="form-check-label" for="flexCheckDefault"> 동구 </label>
          </div>
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" />
            <label class="form-check-label" for="flexCheckChecked"> 영도구 </label>
          </div>
        </div>
        <div class="d-flex my-4 ps-4">
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" />
            <label class="form-check-label" for="flexCheckDefault"> 진구 </label>
          </div>
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" />
            <label class="form-check-label" for="flexCheckChecked"> 동래구 </label>
          </div>
        </div>
        <div class="d-flex my-4 ps-4">
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" />
            <label class="form-check-label" for="flexCheckDefault"> 남구 </label>
          </div>
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" />
            <label class="form-check-label" for="flexCheckChecked"> 북구 </label>
          </div>
        </div>
        <div class="d-flex my-4 ps-4">
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" />
            <label class="form-check-label" for="flexCheckDefault"> 해운대구 </label>
          </div>
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" />
            <label class="form-check-label" for="flexCheckChecked"> 사하구 </label>
          </div>
        </div>
        <div class="d-flex my-4 ps-4">
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" />
            <label class="form-check-label" for="flexCheckDefault"> 금정구 </label>
          </div>
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" />
            <label class="form-check-label" for="flexCheckChecked"> 강서구 </label>
          </div>
        </div>
        <div class="d-flex my-4 ps-4">
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" />
            <label class="form-check-label" for="flexCheckDefault"> 연제구 </label>
          </div>
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" />
            <label class="form-check-label" for="flexCheckChecked"> 수영구 </label>
          </div>
        </div>
        <div class="d-flex my-4 ps-4">
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" />
            <label class="form-check-label" for="flexCheckDefault"> 사상구 </label>
          </div>
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" />
            <label class="form-check-label" for="flexCheckChecked"> 기장군 </label>
          </div>
        </div>
        <hr />
        <div class="d-flex justify-content-around">
          <div type="button" class="btn btn-dark mb-2">적용</div>
          <div type="button" class="btn btn-outline-secondary mb-2">취소</div>
        </div>
      </ul>
    </div>
  </div>
</template>

<script>
import Slider from "@vueform/slider";
import PlaceSearchFiltersRegion from "./PlaceSearchFiltersRegion.vue";

export default {
  name: "PlaceSearchFilters",
  components: { Slider, PlaceSearchFiltersRegion },
  data: () => ({
    value: [20, 40],
    format: function (value) {
      return `$${value}`;
    },
  }),
  // setup() {
  //   const value = [20];
  //   return { value };
  // },
};
</script>
<style src="@vueform/slider/themes/default.css"></style>
<style lang="scss" scoped>
.placeSearchFilters {
  width: 100%;
  // background: green;
}
.btnPlace {
  margin: 10px 20px 10px 20px;
  background: #ffff;
  border-radius: 30px;
  box-shadow: (0 1px 10px rgba(24, 24, 24, 0.04));
  // box-shadow: (0 0 8px rgba(24, 24, 24, 0.04));
  border: 1px solid rgba(1, 1, 1, 0.1);
  font-size: 14px;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}
.form-check-label {
  font-size: 14px;
}
</style>
