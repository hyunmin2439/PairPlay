<template>
  <div class="ps-4 pt-4" style="height: 100%;">
    <h2>
      <b>내가 받은 메이트 목록</b>
      <hr>
    </h2>
    <div 
      v-for="(data, idx) in state.profileMateListFrom" :key=idx
      class="container card my-5 d-flex row"
    >
      <div class="col-2 profile-image">
        <img :src="data.profileImage" alt="" style="width: 100%;">
      </div>
      <div class="col-8 mt-4 mb-2">
        <h4 class="mb-3"><b>{{ data.nickname }}의 {{ data.title }} ( {{ data.location }} )</b></h4>
        <p>{{ data.description }}</p>
        <p style="font-size: large;" class="mt-5"><b>종목 : {{ state.category[data.categoryId] }}</b></p>
      </div>
      <div class="col-2 my-4 d-flex justify-content-around" style="flex-direction: column;">
        <button class="btn-mate accept">
          신청
        </button>
        <button class="btn-mate reject">
          거절
        </button>
      </div>
    </div>
    <br><br>
    <h2>
      <b>내가 보낸 메이트 목록</b>
      <hr>
    </h2>
    <div 
      v-for="(data, idx) in state.profileMateListTo" :key=idx
      class="container card my-5 d-flex row"
    >
      <div class="col-2 profile-image">
        <img :src="data.profileImage" alt="" style="width: 100%;">
      </div>
      <div class="col-8 mt-4 mb-2">
        <h4 class="mb-3"><b>{{ data.title }} ( {{ data.location }} )</b></h4>
        <p>신청자 : {{ data.nickname }} <button class="ms-3 profile-btn" @click="moveToProfile(data.memberId)">확인하기</button></p>
        <p style="font-size: large;" class="mt-5"><b>종목 : {{ state.category[data.categoryId] }}</b></p>
      </div>
      <div class="col-2 my-4 d-flex justify-content-around" style="flex-direction: column;">
        <button class="btn-mate accept">
          신청 취소
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive, computed, onMounted } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
export default {
  name: "ProfileMate",
  props: {
    userInfo: Object,
    otherInfo: Object,
  },
  setup(props) {
    const store = useStore()
    const router = useRouter()
    const state = reactive({
      userInfo: props.userInfo,
      otherInfo: props.otherInfo,
      profileMateListFrom: computed(() => store.getters["root/profileMateListFrom"]),
      profileMateListTo: computed(() => store.getters["root/profileMateListTo"]),
      profileMateListFromTotalPage: computed(() => store.getters["root/profileMateListFromTotalPage"]),
      profileMateListToTotalPage: computed(() => store.getters["root/profileMateListToTotalPage"]),
      pageFrom: 1,
      pageTo: 1,
      size: 3,
      category: {
        1: "축구", 2: "풋살", 3: "농구", 4: "야구",
        5: "볼링", 6: "골프", 7: "테니스", 8: "배드민턴",
        9: "헬스", 10:	"필라테스", 11:	"격투기", 12:	"수영"
      },
    })

    onMounted(async () => {
      const bodyFrom = {
        'page': state.pageFrom-1,
        'size': state.size,
      }
      const bodyTo = {
        'page': state.pageTo-1,
        'size': state.size,
      }
      await store.dispatch("root/profileMateListFrom", bodyFrom)
      await store.dispatch("root/profileMateListTo", bodyTo)
    })

    const moveToProfile = function (id) {
      router.push({
        name: "ProfileTemp",
        query: {
          memberId: id,
        }
      })
    }

    return { state, onMounted, moveToProfile }
  }
}
</script>

<style>
.card {
  border-radius: 10pt;
  box-shadow: 2px 2px 1px 1px rgba(0,0,0,0.5);
  flex-direction: row
}

.btn-mate {
  width: 80%;
  border-radius: 5pt;
  border: none;
  padding-top: 7px;
  padding-bottom: 7px;
  font-size: large;
  font-weight: bold;
}

.accept {
  color: white;
  background-color: black;
}

.reject {
  color: black;
  background-color: #B6B6B6;
}

.profile-btn {
  border: none;
  border-radius: 5px;
  padding-top: 5px;
  padding-bottom: 5px;
  font-weight: bold;
}

</style>