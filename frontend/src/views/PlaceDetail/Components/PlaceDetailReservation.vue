<template>
  <div>
    <Datepicker v-model="date" :enableTimePicker="false" autoApply class="datePicker" inline></Datepicker>
    <div class="ReservationRealTime" id="style-1">
      <div class="wrapper d-flex flex-row">
        <div class="d-flex row">
          <div class="d-flex">
            <div class="col menu justify-content-center">10,000원</div>
            <div class="col menu justify-content-center">10,000원</div>
            <div class="col menu justify-content-center">10,000원</div>
            <div class="col menu justify-content-center">10,000원</div>
            <div class="col menu justify-content-center">10,000원</div>
            <div class="col menu justify-content-center">10,000원</div>
            <div class="col menu justify-content-center">10,000원</div>
          </div>
          <div class="d-flex justify-content-between">
            <p class="time-mark">09:00</p>
            <p class="time-mark">10:00</p>
            <p class="time-mark">11:00</p>
            <p class="time-mark">12:00</p>
            <p class="time-mark">13:00</p>
            <p class="time-mark">14:00</p>
            <p class="time-mark">15:00</p>
            <p class="time-mark">16:00</p>
            <p class="time-mark">17:00</p>
            <p class="time-mark">18:00</p>
            <p class="time-mark">19:00</p>
            <p class="time-mark">20:00</p>
            <p class="time-mark">21:00</p>
            <p class="time-mark">22:00</p>
            <p class="time-mark">23:00</p>
          </div>
        </div>
        <!-- <div class="col">
          <div class="menu d-flex justify-content-center">10,000원</div>
          <div class="d-flex justify-content-around align-items-center">
            <p class="time-mark text-white">10:00</p>
            <p class="time-mark ps-5">12:00</p>
          </div>
        </div>
        <div class="col">
          <div class="menu d-flex justify-content-center">10,000원</div>
          <div class="d-flex justify-content-around align-items-center">
            <p class="time-mark text-white">10:00</p>
            <p class="time-mark ps-5">13:00</p>
          </div>
        </div>
        <div class="col">
          <div class="menu d-flex justify-content-center">10,000원</div>
          <div class="d-flex justify-content-around align-items-center">
            <p class="time-mark text-white">10:00</p>
            <p class="time-mark ps-5">14:00</p>
          </div>
        </div>
        <div class="col">
          <div class="menu d-flex justify-content-center">10,000원</div>
          <div class="d-flex justify-content-around align-items-center">
            <p class="time-mark text-white">10:00</p>
            <p class="time-mark ps-5">15:00</p>
          </div>
        </div>
        <div class="col">
          <div class="menu d-flex justify-content-center">10,000원</div>
          <div class="d-flex justify-content-around align-items-center">
            <p class="time-mark text-white">10:00</p>
            <p class="time-mark ps-5">16:00</p>
          </div>
        </div>
        <div class="col">
          <div class="menu d-flex justify-content-center">10,000원</div>
          <div class="d-flex justify-content-around align-items-center">
            <p class="time-mark text-white">10:00</p>
            <p class="time-mark ps-5">17:00</p>
          </div>
        </div>
        <div class="col">
          <div class="menu d-flex justify-content-center">10,000원</div>
          <div class="d-flex justify-content-around align-items-center">
            <p class="time-mark text-white">10:00</p>
            <p class="time-mark ps-5">18:00</p>
          </div>
        </div> -->
      </div>
    </div>
    <div class="d-flex justify-content-center mt-3">
      <div class="btn reservation me-3">예약</div>
      <div class="btn btn-secondary ms-3">취소</div>
    </div>
  </div>
</template>

<script>
import { ref, onMounted } from "vue";
import Datepicker from "@vuepic/vue-datepicker";
import "@vuepic/vue-datepicker/dist/main.css";

export default {
  name: "PlaceDetailReservation",
  components: { Datepicker },

  setup() {
    const date = ref(new Date());
    onMounted(() => {});
    // const picked = ref(new Date());
    return {
      date,
      // picked,
    };
    // const store = userStore();
    // const route = useRoute();
  },
};
</script>

<style lang="scss">
.datePicker {
  width: 250px;
  // height: 320px;
  box-shadow: 0 0 8px rgba(24, 24, 24, 0.1);
  margin: 2rem auto 2rem auto;

  // border-radius: 500px;
}
.ReservationRealTime {
  // display: flex;
  flex-wrap: nowrap;
  min-height: 80px;
  width: 100%;
  // margin: 0rem 1rem 0rem 0rem;
  // padding: 0rem 0rem 0rem 0rem;
  overflow: auto;
  margin: 0rem auto 0rem auto;
  color: #112031;
  // border: 1px solid #000;
  // overflow: auto;
  // white-space: nowrap;
  // overflow-x: overlay;
  // overflow-y: overlay;
  ::-webkit-scrollbar {
    display: none;
    width: 0px;
    height: 0px;
  }
}
#style-1::-webkit-scrollbar-track {
  -webkit-box-shadow: inset 0 0 3px rgba(0, 0, 0, 0.1);
  border-radius: 5px;
  background-color: #f5f5f5;
}
#style-1::-webkit-scrollbar {
  // width: 5px;
  height: 0.7rem;
  background-color: #f5f5f5;
}
#style-1::-webkit-scrollbar-thumb {
  border-radius: 5px;
  -webkit-box-shadow: inset 0 0 3px rgba(0, 0, 0, 0.3);
  background-color: #555;
}
.wrapper {
  overflow-x: auto;
  display: flex;
  flex-direction: row;
  width: 120%;
}
.menu {
  // display: inline-block;
  text-align: center;
  background-color: #1976d2;
  padding: 0.8rem 0.2rem 0.8rem 0.2rem;
  margin: 0rem 0rem 0rem 0.2rem;
  box-shadow: 0 0 8px rgba(24, 24, 24, 0.1);
  color: white;
  border-radius: 5px;
  // width: 130px;
}
.menu.disabled {
  // display: inline-block;
  text-align: center;
  background-color: rgb(145, 145, 145);
  padding: 0.8rem 0rem 0.8rem 0rem;
  margin: 0rem 0rem 0rem 0.2rem;
  box-shadow: 0 0 8px rgba(24, 24, 24, 0.1);
  color: white;
  border-radius: 5px;
}
.btn.reservation {
  background-color: #1976d2;
  color: white;
  &:hover {
  }
}
.time-mark {
  font-size: 0.8rem;
}
</style>
