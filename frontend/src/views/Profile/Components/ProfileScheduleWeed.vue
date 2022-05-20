<template>
  <h2>
    <b>활동 기록</b>
    <hr>
  </h2>
  <!-- { date: '2021-9-27', count: 6 },
    { date: '2021-9-21', count: 6 } -->
  <calendar-heatmap
    :values="state.userSchedule"
    :end-date="state.end"
    :range-color="[
      '#ebedf0',
      '#dae2ef',
      '#c0ddf9',
      '#73b3f3',
      '#3886e1',
      '#17459e',
    ]"
    :max="4"
    tooltip-unit="번의 운동기록"
    style="font-size: x-small; margin-top: 5%;"
    :round="10"
    @click="getDateTodo($event)"
  />
</template>

<script>
import { reactive, computed, onMounted } from 'vue'
import { useStore } from 'vuex'
export default {
  name: "ProfileScheduleWeed",
  props: {
    userInfo: Object,
    otherInfo: Object,
  },
  setup(props) {
    const store = useStore()
    const state = reactive({
      userInfo: props.userInfo,
      otherInfo: props.otherInfo,
      end: new Date,
      userSchedule: computed(() => store.getters["root/profileUserSchedule"]),
      monthInfo: {
        "Jan": "01", "Feb": "02", "Mar": "03",
        "Apr": "04", "May": "05", "Jun": "06",
        "Jul": "07", "Aug": "08", "Sep": "09",
        "Oct": "10", "Nov": "11", "Dec": "12",
      }
    })

    onMounted(() => {
      state.end = state.end.toISOString().substr(0, 10)
    })

    const getDateTodo = async function (event) {
      let dateList = event.target.dataset.tippyContent.split(' ')
      let date = ""
      if (dateList[5].length==2) {
        date = dateList[6] + '-' + state.monthInfo[dateList[4]] + "-0" + String(dateList[5]).substr(0, 1)
      } else {
        date = dateList[6] + '-' + state.monthInfo[dateList[4]] + "-" + String(dateList[5]).substr(0, 2)
      }
      let payload = {
        'date': date,
        'memberId': state.otherInfo.memberId
      }
      await store.dispatch("root/profileDateTodo", payload)
    }

    return { state, onMounted, getDateTodo }
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