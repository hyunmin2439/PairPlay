<template>
  <div style="max-width: 1255px; margin: auto">
    <Header></Header>
  </div>
  <hr style="margin-top: 0px; margin-bottom: 0px; color: #b7b7b7" />
  <div>
    <mate-filter></mate-filter>
    <mate-view></mate-view>
  </div>
</template>

<script>
import Header from "../Common/Header.vue";
import MateFilter from "./Components/MateFilter.vue";
import MateView from "./Components/MateView.vue";
export default {
  name: "Mate",
  components: { Header, MateFilter, MateView },
  setup() {},
};
</script>

<style></style>
