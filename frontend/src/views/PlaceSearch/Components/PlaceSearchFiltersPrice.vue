<template>
  <div class="btn-group">
    <button type="button" class="btn btnPlace dropdown-toggle" data-bs-toggle="dropdown" data-bs-auto-close="outside" :aria-expanded="`${expand}`">가격</button>
    <ul class="dropdown-menu ps-5" style="width: 400px; height: 300px; font-size: 14px">
      <Slider v-model="value" type="input" :format="format" />
      <div class="d-flex justify-content-around">
        <button type="button" class="btn btn-dark mb-2" @click="changedExpand">적용</button>
        <button type="button" class="btn btn-outline-secondary mb-2">취소</button>
      </div>
    </ul>
  </div>
</template>

<script>
import Slider from "@vueform/slider";

export default {
  name: "PlaceSearchFiltersPrice",
  components: { Slider },
  setup() {
    let value = [20, 40];
    const format = (value) => {
      return `${value}원`;
    };
    let expand = false;
    // const refresh = () =>
    const changedExpand = () => {
      expand = !expand;
      console.log(expand, "expand");
      console.log(value, "value");
      return;
    };

    return { value, format, changedExpand };
  },
};
</script>

<style src="@vueform/slider/themes/default.css"></style>

<style lang="scss" scoped>
.btn {
  &:hover {
    border: 1px solid black;
  }
}
.dropdown-menu:checked {
  display: block;
}
.btnPlace {
  margin: 10px 5px 10px 5px;
  background: #ffff;
  border-radius: 5px;
  box-shadow: (0 1px 10px rgba(24, 24, 24, 0.04));
  box-shadow: (0 0 8px rgba(24, 24, 24, 0.04));
  border: 1px solid rgba(1, 1, 1, 0.1);
  font-size: 14px;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}
</style>
