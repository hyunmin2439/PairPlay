<template>
  <div class="ps-4 pt-4" style="height: 100%;">
    <h2>
      <b>내가 찜한 장소</b>
      <hr>
    </h2>
    <profile-like-place></profile-like-place>
    <br><br>
    <h2>
      <b>내가 찜한 메이트</b>
      <hr>
    </h2>
    <profile-like-mate></profile-like-mate>
    <br><br>
  </div>
</template>

<script>
import ProfileLikePlace from './ProfileLikePlace.vue'
import ProfileLikeMate from './ProfileLikeMate.vue'

import { reactive, onMounted } from 'vue'
import { useStore } from 'vuex'
export default {
  name: "ProfileLike",
  components: {
    ProfileLikePlace,
    ProfileLikeMate
  },
  props: {
    userInfo: Object,
    otherInfo: Object,
  },
  setup(props) {
    const store = useStore()
    const state = reactive({
      userInfo: props.userInfo,
      otherInfo: props.otherInfo,
      page: 0,
    })

    onMounted(async () => {
      await store.dispatch("root/profileLikePlace", state.page)
      await store.dispatch("root/profileLikeMate", state.page)
    })

    return { state, onMounted }
  }
}
</script>

<style>

</style>