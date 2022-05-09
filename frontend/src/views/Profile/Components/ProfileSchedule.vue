<template>
  <div class="ps-4 pt-4" style="height: 100%;">
    <profile-schedule-weed :otherInfo="state.otherInfo" :userInfo="state.userInfo"></profile-schedule-weed>
    <profile-schedule-view :otherInfo="state.otherInfo" :userInfo="state.userInfo"></profile-schedule-view>
  </div>
</template>

<script>
import { reactive, onMounted } from 'vue'
import { useStore } from 'vuex'
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
    const store = useStore()
    const state = reactive({
      userInfo: props.userInfo,
      otherInfo: props.otherInfo
    })

    onMounted (() => {
      store.dispatch("root/getUserSchedule")
    })

    return { state, onMounted }
  }
}
</script>

<style>

</style>