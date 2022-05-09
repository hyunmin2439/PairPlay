<template>
  <h2 class="mt-5">
    <b>예약 기록</b>
    <hr>
  </h2>
</template>

<script>
import { reactive } from 'vue'
export default {
  name: "ProfileReservationView",
  props: {
    userInfo: Object,
    otherInfo: Object,
  },
  setup(props) {
    const state = reactive({
      userInfo: props.userInfo,
      otherInfo: props.otherInfo,
    })

    return { state }
  }
}
</script>

<style>

</style>