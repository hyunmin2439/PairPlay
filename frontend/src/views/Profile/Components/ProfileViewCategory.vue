<template>
  <div class="py-5 my-5">
    <h2>
      <b>구력</b>
      <hr>
    </h2>
  </div>
</template>

<script>
import { reactive } from 'vue'
export default {
  name: "ProfileViewCategory",
  props: {
    otherInfo: Object,
  },
  setup (props) {
    const state = reactive({
      otherInfo: props.otherInfo
    })

    return { state }
  }
}
</script>

<style>

</style>