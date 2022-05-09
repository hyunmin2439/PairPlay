<template>
  <div>
    <div class="ps-4 pt-4" style="height: 100%;">
      <profile-reservation-like :otherInfo="state.otherInfo" :userInfo="state.userInfo"></profile-reservation-like>
      <profile-reservation-view :otherInfo="state.otherInfo" :userInfo="state.userInfo"></profile-reservation-view>
    </div>
  </div>
</template>

<script>
import { reactive } from 'vue'
import ProfileReservationLike from './ProfileReservationLike.vue'
import ProfileReservationView from './ProfileReservationView.vue'
export default {
  name: "ProfileReservation",
  components: {
    ProfileReservationLike,
    ProfileReservationView
  },
  props: {
    userInfo: Object,
    otherInfo: Object,
  },
  setup(props) {
    const state = reactive({
      userInfo: props.userInfo,
      otherInfo: props.otherInfo
    })

    return { state }
  }
}
</script>

<style>

</style>