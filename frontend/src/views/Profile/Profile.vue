<template>
  <div>
    <div style="max-width: 70%; margin: auto;">
      <Header></Header>
    </div>
    <hr style="margin-top: 0px; margin-bottom: 0px; color: #b7b7b7;">
    <div class="mt-5 d-flex justify-content-between" style="max-width: 70%; margin: auto;">
      <div class="ms-4 profile-image-card">
      </div>
      <div class="me-4 mt-4" style="width: 60%; height: 80%; font-size: x-large;">
        <div class="d-flex justify-content-between" style="width: 100%; height: 100%;">
          <h2>Profile</h2>
          <div class="edit mt-2" @click="changeDescription"></div>
        </div>
        <hr>
        <span>
          {{ state.userInfo.description }}
        </span>
      </div>
    </div>
    <div style="max-width: 70%; margin: auto;" class="mt-5">
      <hr style="margin-bottom: 0px;">
      <div class="d-flex">
        <div class="container pt-5" style="position: sticky; top: 30px; max-width: 30%; height: 100%;">
          <span>Profile</span><br>
          <span>Reservation</span><br>
          <span>Schedule</span><br>
          <span>Mate List</span>
        </div>
        <div class="container pt-5" style="max-width: 70%; height: 100vh; border-left: 1px solid #c8c9ca;">
          <span>hello</span>
        </div>
      </div>
    </div>
  </div>
  <Footer></Footer>
</template>

<script>
import { reactive, computed } from "vue"
import { useStore } from 'vuex'
import Header from "../Common/Header.vue";
import Footer from "../Common/Footer.vue";
export default {
  name: 'Profile',
  components: {
    Header,
    Footer,
  },
  setup() {
    const store = useStore()
    const state = reactive({
      userInfo: computed(() => store.getters["root/userInfo"])
    })

    return { state }
  }
}
</script>

<style scoped lang="scss">
.profile-image-card {
  width: 250px;
  height: 250px;
  border-radius: 50%;
  background-image: url("https://search.pstatic.net/common/?src=http%3A%2F%2Fimgnews.naver.net%2Fimage%2F144%2F2022%2F04%2F29%2F0000809588_003_20220429133001525.jpg&type=sc960_832");
  background-repeat: no-repeat;
  background-size: 100%;
}

.edit {
  width: 2vw;
  height: 4vh;
  background-image: url("@/assets/images/Profile/Edit.png");
  background-size: 80%;
  background-repeat: no-repeat;
  cursor: pointer;
}

</style>