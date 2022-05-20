<template>
  <div class="ps-4 py-4" style="height: 100%;">
    <profile-view-info :otherInfo="state.otherInfo" :userInfo="state.userInfo"></profile-view-info>
  </div>
  <br><br>
</template>

<script>
import { reactive } from 'vue'
import ProfileViewInfo from './ProfileViewInfo.vue'
export default {
  name: "ProfileView",
  props: {
    userInfo: Object,
    otherInfo: Object,
  },
  components: {
    ProfileViewInfo,
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