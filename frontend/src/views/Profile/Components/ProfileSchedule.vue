<template>
  <div class="ps-4 pt-4" style="height: 100%;">
    <profile-schedule-weed :otherInfo="state.otherInfo" :userInfo="state.userInfo"></profile-schedule-weed>
    <profile-schedule-view :otherInfo="state.otherInfo" :userInfo="state.userInfo"></profile-schedule-view>
  </div>
</template>

<script>
import { reactive } from 'vue'
import ProfileScheduleWeed from './ProfileScheduleWeed.vue'
import ProfileScheduleView from './ProfileScheduleView.vue'
export default {
  name: "ProfileSchedule",
  components: {
    ProfileScheduleWeed,
    ProfileScheduleView
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