<template>
  <h2>
    <b>활동 기록</b>
    <hr>
  </h2>
  <!-- { date: '2021-9-27', count: 6 },
    { date: '2021-9-21', count: 6 } -->
  <calendar-heatmap
    :values="state.calenderInfo"
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
import { reactive, onMounted } from 'vue'
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
      calenderInfo: [
        { date: '2021-09-21', count: 1 },
        { date: '2021-09-28', count: 2 },
        { date: '2021-10-05', count: 3 },
        { date: '2021-10-12', count: 4 },
        { date: '2021-10-19', count: 5 },
        { date: '2021-10-26', count: 6 },
        { date: '2021-11-02', count: 16 },
        { date: '2022-04-05', count: 4},
      ],
      monthInfo: {
        "Jan": 1, "Feb": 2, "Mar": 3,
        "Apr": 4, "May": 5, "Jun": 6,
        "Jul": 7, "Aug": 8, "Sep": 9,
        "Oct": 10, "Nov": 11, "Dec": 12,
      }
    })

    onMounted(() => {
      state.end = state.end.toISOString().substr(0, 10)
    })

    const getDateTodo = async function (event) {
      let dateList = event.target.dataset.tippyContent.split(' ')
      let date = dateList[6] + '-' + String(state.monthInfo[dateList[4]]) + "-" + dateList[5]
      console.log(dateList)

      let payload = {
        'date': date,
        'activity': {
          0: {
            "activity": {
              "title": '메이트2',
              "description": '메이트구해여메이트구해여메이트구해여메이트구해여',
              "category": "축구",
              "location": "부산 강서구"
            },
            "mateList": {
              0: {
                'id': 6,
                'profileImage': "https://lh3.googleusercontent.com/a/AATXAJwq7oX0_n7_NTK89Zgi1_sCNqoQX9YeYLbeezsY=s96-c"
              },
              1: {
                'id': 25,
                'profileImage': "http://k.kakaocdn.net/dn/cjJL37/btrr1I0eYLX/m4k6rwytjL3MCGLA4hrfi0/img_640x640.jpg"
              },
              2: {
                'id': 25,
                'profileImage': "http://k.kakaocdn.net/dn/cjJL37/btrr1I0eYLX/m4k6rwytjL3MCGLA4hrfi0/img_640x640.jpg"
              },
              3: {
                'id': 6,
                'profileImage': "https://lh3.googleusercontent.com/a/AATXAJwq7oX0_n7_NTK89Zgi1_sCNqoQX9YeYLbeezsY=s96-c"
              },
              4: {
                'id': 6,
                'profileImage': "https://lh3.googleusercontent.com/a/AATXAJwq7oX0_n7_NTK89Zgi1_sCNqoQX9YeYLbeezsY=s96-c"
              },
            }
          },
          1: {
            "activity": {
              "title": '메이트2223',
              "description": '메이트구해여123123',
              "category": "배구",
              "location": "부산 금정구"
            },
            "mateList": {
              0: {
                'id': 66,
                'profileImage': "https://lh3.googleusercontent.com/a/AATXAJwq7oX0_n7_NTK89Zgi1_sCNqoQX9YeYLbeezsY=s96-c"
              },
              1: {
                'id': 255,
                'profileImage': "http://k.kakaocdn.net/dn/cjJL37/btrr1I0eYLX/m4k6rwytjL3MCGLA4hrfi0/img_640x640.jpg"
              },
              2: {
                'id': 25,
                'profileImage': "http://k.kakaocdn.net/dn/cjJL37/btrr1I0eYLX/m4k6rwytjL3MCGLA4hrfi0/img_640x640.jpg"
              }
            }
          },
        }
      }
      await store.dispatch("root/getDateTodo", payload)
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