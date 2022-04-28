<template>
  <div>
    <mate-filter></mate-filter>
    <mate-view></mate-view>
  </div>
</template>

<script>
import MateFilter from './Components/MateFilter.vue'
import MateView from './Components/MateView.vue'
export default {
  name: "Mate",
  components: { MateFilter, MateView },
  setup() {

  }
}
</script>

<style>

</style>