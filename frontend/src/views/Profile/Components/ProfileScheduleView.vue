<template>
  <h4 class="mt-5">
    <b>{{ state.datePerDay }} 활동 기록</b>
    <hr>
  </h4>
  <div 
    v-for="(data, idx) in state.activityPerDay" :key=idx
    class="container card my-5 d-flex row"
  >
    <div class="col-2 category">
    </div>
    <div class="col-6 my-4">
      <h3 class="mb-3"><b>{{ data.activity.title }}</b></h3>
      <p>{{ data.activity.description }}</p>
      <h5>종목 : <b>{{ data.activity.category }}</b></h5>
      <h5>지역 : <b>{{ data.activity.location }}</b></h5>
    </div>
    <div class="col-4 my-4">
      <div>
        <h3><b>함께한 메이트</b></h3>
      </div>
      <div class="d-flex mt-5">
        <!-- {{ Object.keys(data.mateList).length }} -->
        <div v-if="Object.keys(data.mateList).length >= 1">
          <img :src="data.mateList[0].profileImage" alt="" class="mate-image">
        </div>
        <div v-if="Object.keys(data.mateList).length >= 2" style="position: relative; right: 5%;">
          <img :src="data.mateList[1].profileImage" alt="" class="mate-image">
        </div>
        <div v-if="Object.keys(data.mateList).length >= 3" style="position: relative; right: 10%;">
          <img :src="data.mateList[2].profileImage" alt="" class="mate-image">
        </div>
        <div v-if="Object.keys(data.mateList).length >= 4" style="position: relative; right: 15%;">
          <img :src="data.mateList[3].profileImage" alt="" class="mate-image">
        </div>
        <div v-if="Object.keys(data.mateList).length >= 5" style="position: relative; right: 20%;">
          <img :src="data.mateList[4].profileImage" alt="" class="mate-image">
        </div>
        <div v-if="Object.keys(data.mateList).length >= 6" style="position: relative; right: 20%;"
          class="mate-last d-flex justify-content-center align-items-center"
        >
          <h5><b>+{{ Object.keys(data.mateList).length - 4 }}</b></h5>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'
export default {
  name: "ProfileScheduleView",
  props: {
    userInfo: Object,
    otherInfo: Object,
  },
  setup(props) {
    const store = useStore()
    const state = reactive({
      userInfo: props.userInfo,
      otherInfo: props.otherInfo,
      activityPerDay: computed(() => store.getters["root/activityPerDay"]),
      datePerDay: computed(() => store.getters["root/datePerDay"])
    })

    return { state }
  }
}
</script>

<style scoped lang="scss">
.card {
  // border: 1px solid black;
  border-radius: 10pt;
  box-shadow: 2px 2px 1px 1px rgba(0,0,0,0.5);
  flex-direction: row
}

.category {
  background-image: url("@/assets/images/Profile/Category/축구.png");
  background-size: 70%;
  background-repeat: no-repeat;
  background-position-y: center;
}

.mate-image {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  border: 3px solid white;
  cursor: pointer;
  &:hover{
    width: 62px;
    height: 62px;
  }  
}

.mate-last {
  background-color: white;
  width: 60px;
  height: 60px;
  border-radius: 50%;
  border: 3px solid #E5E5E5;
  cursor: pointer;
}
</style>