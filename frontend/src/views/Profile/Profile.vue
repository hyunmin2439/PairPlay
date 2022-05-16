<template>
  <div>
    <div style="max-width: 70%; margin: auto;">
      <Header></Header>
    </div>
    <hr style="margin-top: 0px; margin-bottom: 0px; color: #b7b7b7;">
    <div class="mt-5 d-flex justify-content-between" style="max-width: 70%; margin: auto;">
      <div class="ms-4 profile-image-card">
        <img :src="state.otherInfo.profileImage" alt="" style="width: 100%; height: 100%; border-radius: 50%; border: 1px solid grey;">      
      </div>
      <!-- <div v-if="state.userInfo.memberId == state.otherInfo.memberId" class="edit mt-2" style="margin-right: 15%;" for="input-file"></div> -->
      <label for="input-file" style="cursor: pointer; width: 10%; margin-right: 50px;">
        <img src="@/assets/images/Profile/Edit.png" alt="" style="width: 25%;">
      </label>
      <input @change="changeImgFile" type="file" id="input-file" style="display: none;">
      <div class="me-4 mt-4" style="width: 60%; height: 80%; font-size: x-large;">
        <div class="d-flex justify-content-between" style="width: 100%; height: 100%;">
          <h2><b>Profile</b></h2>
          <div v-if="state.userInfo.memberId == state.otherInfo.memberId" class="edit mt-2" @click="changeDescriptionStatus"></div>
        </div>
        <hr>
        <div v-if="state.isDescript==false">
          {{ state.otherInfo.description }}
        </div>
        <div v-else>
          <input type="text" v-model="state.otherInfo.description" style="width: 100%; height: 10vh;">
          <button class="mt-3 btn-edit" @click="changeDescription">수정하기</button>
        </div>
      </div>
    </div>
    <div style="max-width: 70%; margin: auto;" class="mt-5">
      <hr style="margin-bottom: 0px;">
      <div class="d-flex">
        <div class="container pt-4" style="position: sticky; top: 30px; max-width: 20%; height: 100%;">
          <div class="py-4">
            <span class="side" :class="{ 'is-active': state.isActivePro }" @click="changeSideComponents($event)">Profile</span>
          </div>
          <div class="py-4">
            <span class="side" :class="{ 'is-active': state.isActiveRes }" @click="changeSideComponents($event)">Reservation</span>
          </div>
          <div class="py-4">
            <span class="side" :class="{ 'is-active': state.isActiveSch }" @click="changeSideComponents($event)">Schedule</span>
          </div>
          <div class="py-4">
            <span class="side" :class="{ 'is-active': state.isActiveMat }" @click="changeSideComponents($event)">Mate List</span>
          </div>
        </div>
        <div class="container pt-4" style="max-width: 80%; height: 100%; border-left: 1px solid #c8c9ca;">
          <profile-view v-if="state.sideComponents=='Profile'" :otherInfo="state.otherInfo" :userInfo="state.userInfo"></profile-view>
          <profile-reservation v-else-if="state.sideComponents=='Reservation'" :otherInfo="state.otherInfo" :userInfo="state.userInfo"></profile-reservation>
          <profile-schedule v-else-if="state.sideComponents=='Schedule'" :otherInfo="state.otherInfo" :userInfo="state.userInfo"></profile-schedule>
          <profile-mate v-else-if="state.sideComponents=='Mate List'" :otherInfo="state.otherInfo" :userInfo="state.userInfo"></profile-mate>
        </div>
      </div>
    </div>
  </div>
  <Footer></Footer>
</template>

<script>
import { reactive, computed, onMounted } from "vue"
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import Header from "../Common/Header.vue";
import Footer from "../Common/Footer.vue";
import ProfileView from './Components/ProfileView.vue';
import ProfileReservation from './Components/ProfileReservation.vue';
import ProfileSchedule from './Components/ProfileSchedule.vue';
import ProfileMate from './Components/ProfileMate.vue';

export default {
  name: 'Profile',
  components: {
    Header,
    Footer,
    ProfileView,
    ProfileReservation,
    ProfileSchedule,
    ProfileMate
  },
  setup() {
    const store = useStore()
    const router = useRouter()
    const state = reactive({
      userInfo: computed(() => store.getters["root/userInfo"]),
      otherInfo: computed(() => store.getters["root/otherInfo"]),
      sideComponents: "Mate List",
      isDescript: false,
      isActivePro: true,
      isActiveRes: false,
      isActiveSch: false,
      isActiveMat: false,
    })

    onMounted(async () => {
      const proMemberId = router.currentRoute.value.params['memberId']
      const jwt = localStorage.getItem('jwt')
      await store.dispatch("root/profileOtherInfo", {'memberId': proMemberId, 'jwt': jwt})
    })

    const changeDescriptionStatus = function () {
      if (state.isDescript == false) {
        state.isDescript = true
      } else {
        state.isDescript = false
      }
    }

    const changeImgFile = async function (event) {
      if( event.target.files && event.target.files.length > 0 ) {
        console.log(event.target.files, '파일')
        const file = event.target.files[0];
        state.otherInfo.profileImage = file;
        console.log(state.otherInfo)
        // let data = new FormData()
        // data.append("profileImage", file)
        await store.dispatch('root/profileChangeImage', file)
      }
    }

    const changeDescription = async function () {
      await store.dispatch("root/profileChangeInfo",{
        'description': state.otherInfo.description
      })
      state.isDescript = false
    }

    const changeSideComponents = function (event) {
      state.sideComponents = event.target.textContent
      if (state.sideComponents == "Profile") {
        state.isActivePro = true
        state.isActiveRes = false
        state.isActiveSch = false
        state.isActiveMat = false
      } else if (state.sideComponents == "Reservation") {
        state.isActivePro = false
        state.isActiveRes = true
        state.isActiveSch = false
        state.isActiveMat = false
      } else if (state.sideComponents == "Schedule") {
        state.isActivePro = false
        state.isActiveRes = false
        state.isActiveSch = true
        state.isActiveMat = false
      } else {
        state.isActivePro = false
        state.isActiveRes = false
        state.isActiveSch = false
        state.isActiveMat = true
      }
    }
    return { state, onMounted, changeDescriptionStatus, changeImgFile, changeDescription, changeSideComponents }
  }
}
</script>

<style scoped lang="scss">
.profile-image-card {
  width: 250px;
  height: 250px;
}

.edit {
  width: 2vw;
  height: 4vh;
  background-image: url("@/assets/images/Profile/Edit.png");
  background-size: 80%;
  background-repeat: no-repeat;
  cursor: pointer;
}

.side {
  font-size: x-large;
  color: #b6b4b4;
  cursor: pointer;
}

.is-active {
  font-size: x-large;
  color: black;
  font-weight: bold;
}

.btn-edit {
  font-size: large;
  float: right;
  border-radius: 15px;
  width: 15%;
}

</style>