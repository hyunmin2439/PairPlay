<template>
  <div>
    <Datepicker v-model="timeData" :format-locale="ko" format="PP" :enableTimePicker="false" range multiCalendars textInput class="ms-2 datePicker"></Datepicker>
  </div>
</template>

<script>
import { ref, onMounted, watch } from "vue";
import Datepicker from "@vuepic/vue-datepicker";
import { ko } from "date-fns/locale";
import "@vuepic/vue-datepicker/dist/main.css";

export default {
  name: "PlaceSearchFiltersTime",
  components: { Datepicker },
  emits: ["timeData"],
  setup(_, { emit }) {
    const timeData = ref({});
    const selectTimeData = () => {
      const startDate = new Date();
      const endDate = new Date(new Date().setDate(startDate.getDate() + 7));
      timeData.value = [startDate, endDate];
    };
    onMounted(() => {
      selectTimeData();
      // console.log(timeData.value, "온마운티드");
    });
    watch(timeData, () => {
      // console.log(timeData, "timeData");
      emit("timeData", timeData.value);

      // console.log(searchFiltersData.value, "searchFiltersData");
    });

    // const picked = ref(new Date());
    return {
      selectTimeData,
      timeData,
      ko,
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
  box-shadow: 0 0 8px rgba(24, 24, 24, 0.1);
  // border-radius: 500px;
  &:hover {
    // border: 0.2px solid black;
    border-radius: 4px;
  }
}
</style>
