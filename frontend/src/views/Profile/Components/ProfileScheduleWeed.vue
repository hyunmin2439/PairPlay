<template>
  <h2>
    <b>활동 기록</b>
    <hr>
  </h2>
  <!-- { date: '2021-9-27', count: 6 },
    { date: '2021-9-21', count: 6 } -->
  <calendar-heatmap
    :values="state.calenderInfo"
    :end-date=state.end
    :range-color="[
      '#ebedf0',
      '#dae2ef',
      '#c0ddf9',
      '#73b3f3',
      '#3886e1',
      '#17459e',
    ]"
    :max="4"
    style="font-size: x-small;"
  />
</template>

<script>
import { reactive, onMounted } from 'vue'
export default {
  name: "ProfileScheduleWeed",
  props: {
    userInfo: Object,
    otherInfo: Object,
  },
  setup(props) {
    const state = reactive({
      userInfo: props.userInfo,
      otherInfo: props.otherInfo,
      end: new Date,
      calenderInfo: [
        { date: '2021-9-21', count: 1 },
        { date: '2021-9-28', count: 2 },
        { date: '2021-10-5', count: 3 },
        { date: '2021-10-12', count: 4 },
        { date: '2021-10-19', count: 5 },
        { date: '2021-10-26', count: 6 },
        { date: '2021-11-2', count: 16 },
      ]
    })

    onMounted(() => {
      state.end = state.end.toISOString().substr(0, 10)
    })

    return { state }
  }
}
</script>

<style>
.vch__legend {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  margin-top: 1%;
}

.vch__external-legend-wrapper {
  margin-right: 4%;
  margin-left: 4%;
}
</style>