<template>
  <div>
    <Datepicker v-model="date.selectedDate" :format-locale="ko" :enableTimePicker="false" autoApply class="datePicker" inline></Datepicker>
    <div class="ReservationRealTime" id="style-1">
      <div class="wrapper d-flex flex-row justify-content-center align-items-center">
        <div class="d-flex">
          <div class="col" v-for="(reservation, time) in reservationCheck" :key="reservation.id">
            <!--  -->
            <div class="btn-group" role="group" aria-label="Basic checkbox toggle button group">
              <!--  -->
              <div v-if="reservation < 20">
                <input type="checkbox" :id="`btn${time}`" class="btn-check btn menu" />
                <label class="btn btn-primary" :for="`btn${time}`" @click="timeSelect(time)">{{ reservation }} / 20</label>
              </div>
              <div v-if="reservation == 20">
                <div type="checkbox" :id="`btn${time}`" class="btn-check btn menu" disabled />
                <label class="btn btn-disabled" disabled :for="`btn${time}`">예약불가</label>
              </div>
            </div>
            <p class="time-mark">{{ time }}:00 ~</p>
          </div>
        </div>
      </div>
    </div>
    <div class="d-flex justify-content-center mt-3">
      <div class="btn reservation me-3" @click="requestPay">예약</div>
      <div class="btn btn-secondary ms-3">취소</div>
    </div>
  </div>
</template>

<script>
import { reactive, computed, onMounted, watch } from "vue";
import Datepicker from "@vuepic/vue-datepicker";
import { ko } from "date-fns/locale";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import axios from "axios";
import "@vuepic/vue-datepicker/dist/main.css";
import Swal from "sweetalert2";
const BASE_URL = "https://pairplay.site/api/v1";

export default {
  name: "PlaceDetailReservation",
  components: { Datepicker },

  setup() {
    const store = useStore();
    const router = useRouter();
    const date = reactive({ selectedDate: "" });
    // console.log(date, "하");
    const reservationCheck = reactive(computed(() => store.state.root.reservationCheck));
    const selectedTime = [];
    const placeInfos = reactive(computed(() => store.state.root.placeDetailInfo));
    const timeSelect = (time) => {
      if (clickedNumber[time] == true) {
        clickedNumber[time] = false;
      } else {
        clickedNumber[time] = true;
      }
      // console.log(clickedNumber);
      selectedTime.push(time);
    };
    let clickedNumber = { 8: false, 9: false, 10: false, 11: false, 12: false, 13: false, 14: false, 15: false, 16: false, 17: false, 18: false, 19: false, 20: false, 21: false };
    const requestPay = () => {
      let selectedDate = new Date(date.selectedDate + 3240 * 10000).toISOString().replace("T", " ").replace(/\..*/, "").substring(0, 10);
      let temp = [];
      for (let i = 8; i <= 21; i++) {
        if (clickedNumber[i] == true) {
          temp.push(i);
        } else {
          //
        }
      }
      // console.log(temp, "temp");
      if (temp.length == 0) {
        Swal.fire({
          icon: "error",
          title: "실패...",
          text: "예약하려는 시간을 선택해주세요",
        });
      } else {
        // 임시
        // let tempbody = { placeId: placeInfos.value.placeId, reservationDt: selectedDate, price: 1, time: temp };
        // // console.log(body, "제대로?");
        // axios({
        //   method: "post",
        //   data: tempbody,
        //   headers: { Authorization: "Bearer " + localStorage.getItem("jwt") },
        //   url: `${BASE_URL}/places/reservation`,
        // }).then(() => {
        //   // console.log(res);
        //   Swal.fire({
        //     icon: "success",
        //     title: "성공!",
        //     text: "예약이 완료되었습니다.",
        //   });
        //   router.go(0);
        // });

        // 아래가 정답
        const { IMP } = window;
        IMP.init("imp57638465"); //iamport 대신 자신의 "가맹점 식별코드"를 사용
        IMP.request_pay(
          {
            pg: "inicis",
            pay_method: "card",
            merchantuid: "merchant" + new Date().getTime(),
            name: placeInfos.value.name + " 예약",
            amount: 100, // 결제금액,
            buyer_email: store.state.root.userInfo.email,
            buyer_name: store.state.root.userInfo.name,
            buyer_tel: store.state.root.userInfo.phone,
            buyer_addr: store.state.root.userInfo.address,
            buyer_postcode: "123-456",
          },
          function (rsp) {
            // callback
            if (rsp.success) {
              console.log("결제성공");

              let tempbody = { placeId: placeInfos.value.placeId, reservationDt: selectedDate, price: 1, time: temp };
              // console.log(body, "제대로?");
              axios({
                method: "post",
                data: tempbody,
                headers: { Authorization: "Bearer " + localStorage.getItem("jwt") },
                url: `${BASE_URL}/places/reservation`,
              }).then(() => {
                // console.log(res);
                Swal.fire({
                  icon: "success",
                  title: "성공!",
                  text: "예약이 완료되었습니다.",
                });
                router.go(0);
              });
            } else {
              console.log("결제실패");
            }
          }
        );
      }
    };
    const changeData = async () => {
      let selectedDate = new Date(date.selectedDate + 3240 * 10000).toISOString().replace("T", " ").replace(/\..*/, "").substring(0, 10);
      let temp = { placeId: placeInfos.value.placeId, reservationDt: selectedDate };
      // console.log(temp);
      await store.dispatch("root/checkReservation", temp);
      // console.log(reservationCheck.value, "언제고");
      clickedNumber = { 8: false, 9: false, 10: false, 11: false, 12: false, 13: false, 14: false, 15: false, 16: false, 17: false, 18: false, 19: false, 20: false, 21: false };
    };
    onMounted(() => {
      date.selectedDate = new Date();
      changeData();
    });
    watch(date, async () => {
      // console.log(date.value, "몇일인데");
      await changeData();
    });
    // const picked = ref(new Date());
    return {
      placeInfos,
      date,
      reservationCheck,
      ko,
      selectedTime,
      timeSelect,
      requestPay,
      changeData,
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
  // overflow: auto;
  margin: 4rem auto 0rem auto;
  color: #112031;
  // border: 1px solid #000;
  // overflow: auto;
  // white-space: nowrap;
  overflow-x: overlay;
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
  // overflow-x: auto;
  display: flex;
  // justify-items: stretch;
  // flex-direction: column;
  // width: 100%;
  // width: 100%;
  width: 70rem;
}
.btn-primary {
  background-color: #3f8fdf;
  color: white;
  padding: 0.8rem 0.2rem 0.8rem 0.2rem;
  margin: 0rem 0rem 0rem 0.8rem;
  font-size: 0.9rem;
  min-width: 4rem;
}
.btn-disabled {
  background-color: rgb(145, 145, 145);
  color: white;
  padding: 0.8rem 0.2rem 0.8rem 0.2rem;
  margin: 0rem 0rem 0rem 0.8rem;
  font-size: 0.85rem;
  min-width: 4rem;
}

.btn.reservation {
  background-color: #1976d2;
  color: white;
  &:hover {
    background-color: #1976d2;
    color: white;
    box-shadow: 0 0 8px rgba(24, 24, 24, 0.1);
    border: 1px solid rgba(1, 1, 1, 0.3);
    border-radius: 5px;
  }
}

.time-mark {
  font-size: 0.8rem;
  padding: 0rem 0rem 0rem 0.5rem;
}
</style>
