<template>
  <h4 class="mt-5">
    <b>{{ state.datePerDay.substr(0,4) }}년 {{ state.datePerDay.substr(5, 2) }}월 {{ state.datePerDay.substr(8, 10)}}일 활동 기록</b>
    <hr>
  </h4>
  <div v-if="state.activityPerDay.length >= 1">
    <div 
      v-for="(data, idx) in state.activityPerDay" :key=idx
      class="container card my-5 d-flex row"
    >
      <div class="col-2 category">
      </div>
      <div class="col-6 my-3">
        <h4 class="my-3"><b>{{ data.title }}</b></h4>
        <p>{{ data.description }}</p>
        <p style="font-size: large;">종목 : <b>{{ state.category[data.categoryId] }}</b></p>
        <p style="font-size: large;">지역 : <b>{{ data.location }}</b></p>
      </div>
      <div class="col-4 mt-4">
        <div class="my-3">
          <h4><b>함께한 메이트</b></h4>
        </div>
        <div class="d-flex mt-5">
          <!-- {{ Object.keys(data.mateList).length }} -->
          <div v-if="Object.keys(data.mateResList).length >= 1">
            <img :src="data.mateResList[0].profileImage" alt="" class="mate-image">
          </div>
          <div v-if="Object.keys(data.mateResList).length >= 2" style="position: relative; right: 5%;">
            <img :src="data.mateResList[1].profileImage" alt="" class="mate-image">
          </div>
          <div v-if="Object.keys(data.mateResList).length >= 3" style="position: relative; right: 10%;">
            <img :src="data.mateResList[2].profileImage" alt="" class="mate-image">
          </div>
          <div v-if="Object.keys(data.mateResList).length >= 4" style="position: relative; right: 15%;">
            <img :src="data.mateResList[3].profileImage" alt="" class="mate-image">
          </div>
          <div v-if="Object.keys(data.mateResList).length >= 5" style="position: relative; right: 20%;">
            <img :src="data.mateResList[4].profileImage" alt="" class="mate-image">
          </div>
          <div v-if="Object.keys(data.mateResList).length >= 6" style="position: relative; right: 20%;"
            class="mate-last d-flex justify-content-center align-items-center"
          >
            <h5><b>+{{ Object.keys(data.mateResList).length - 4 }}</b></h5>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div v-else>
    <br><br>
    <h3><b>해당 활동 내역이 없습니다ㅠㅠ</b></h3>
    <br><br>
  </div>
  <br><br>
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
      activityPerDay: computed(() => store.getters["root/profileActivityPerDay"]),
      datePerDay: computed(() => store.getters["root/profileDatePerDay"]),
      category: {
        1: "축구", 2: "풋살", 3: "농구", 4: "야구",
        5: "볼링", 6: "골프", 7: "테니스", 8: "배드민턴",
        9: "헬스", 10:	"필라테스", 11:	"격투기", 12:	"수영"
      },
    })

    return { state }
  }
}
</script>

<style scoped lang="scss">
.card {
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