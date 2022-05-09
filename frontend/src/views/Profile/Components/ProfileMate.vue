<template>
  <div class="ps-4 pt-4" style="height: 100%;">
    <h2>
      <b>메이트 신청 목록</b>
      <hr>
    </h2>
    <div
      class="container card my-5 d-flex row"
    >
      <div class="col-2 category">
      </div>
      <div class="col-8 my-4">
        <h3 class="mb-3"><b>이름</b></h3>
        <p>설명</p>
        <h5>종목 : </h5>
        <h5>지역 : </h5>
      </div>
      <div class="col-2 my-4 d-flex justify-content-around" style="flex-direction: column;">
        <button class="btn-mate">
          신청
        </button>
        <button class="btn-mate">
          신청2
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive } from 'vue'
export default {
  name: "ProfileMate",
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

.btn-mate {
  width: 80%;
  border-radius: 10pt;
  padding-top: 5px;
  padding-bottom: 5px;
  font-size: large;
  font-weight: bold;
}
</style>