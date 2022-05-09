<template>
  <h2>
    <b>찜한 목록</b>
    <hr>
  </h2>
  
</template>

<script>
import { reactive } from 'vue'
export default {
  name: "ProfileReservationLike",
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