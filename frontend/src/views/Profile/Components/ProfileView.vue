<template>
  <div class="ps-4 pt-4" style="height: 100%;">
    <profile-view-info :otherInfo="state.otherInfo" :userInfo="state.userInfo"></profile-view-info>
    <profile-view-category :otherInfo="state.otherInfo"></profile-view-category>
  </div>
</template>

<script>
import { reactive } from 'vue'
import ProfileViewInfo from './ProfileViewInfo.vue'
import ProfileViewCategory from './ProfileViewCategory.vue'
export default {
  name: "ProfileView",
  props: {
    userInfo: Object,
    otherInfo: Object,
  },
  components: {
    ProfileViewInfo,
    ProfileViewCategory,
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