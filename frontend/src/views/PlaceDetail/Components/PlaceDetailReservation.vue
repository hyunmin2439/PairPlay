<template>
  <div>
    <Datepicker v-model="date" :enableTimePicker="false" autoApply class="datePicker" inline></Datepicker>
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
  height: 320px;
  box-shadow: 0 0 8px rgba(24, 24, 24, 0.1);
  margin: 2rem auto 2rem auto;

  // border-radius: 500px;
}
</style>
