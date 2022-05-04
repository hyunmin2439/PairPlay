<template>
  <h4 class="mt-5">
    <b>소개</b>
    <hr>
  </h4>
</template>

<script>
import { reactive } from 'vue'
export default {
  name: "ProfileScheduleView",
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