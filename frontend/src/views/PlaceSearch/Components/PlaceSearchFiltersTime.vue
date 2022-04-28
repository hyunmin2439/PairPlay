<template>
  <div>
    <Datepicker v-model="date" :enableTimePicker="false" range multiCalendars style="width: 250px; box-shadow: 0 0 8px rgba(24, 24, 24, 0.1); border-radius: 500px" class="ms-2"></Datepicker>
  </div>
</template>

<script>
import { ref, onMounted } from "vue";
import Datepicker from "@vuepic/vue-datepicker";
import "@vuepic/vue-datepicker/dist/main.css";

export default {
  name: "PlaceSearchFiltersTime",
  components: { Datepicker },

  setup() {
    const date = ref();
    onMounted(() => {
      const startDate = new Date();
      const endDate = new Date(new Date().setDate(startDate.getDate() + 7));
      date.value = [startDate, endDate];
    });
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

<style lang="scss"></style>
