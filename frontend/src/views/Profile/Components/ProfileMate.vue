<template>
  <div class="ps-4 pt-4" style="height: 100%;">
    <h2>
      <b>내가 받은 메이트 목록</b>
      <hr>
    </h2>
    <profile-mate-from></profile-mate-from>
    <br><br>
    <h2>
      <b>내가 보낸 메이트 목록</b>
      <hr>
    </h2>
    <profile-mate-to></profile-mate-to>
    <br><br>
  </div>
</template>

<script>
import ProfileMateFrom from './ProfileMateFrom.vue'
import ProfileMateTo from './ProfileMateTo.vue'
import { reactive, onMounted } from 'vue'
import { useStore } from 'vuex'
export default {
  name: "ProfileMate",
  components: {
    ProfileMateFrom,
    ProfileMateTo,
  },
  props: {
    userInfo: Object,
    otherInfo: Object,
  },
  setup(props) {
    const store = useStore()
    const state = reactive({
      userInfo: props.userInfo,
      otherInfo: props.otherInfo,
    })

    onMounted(async () => {
      const bodyFrom = {
        'page': 0,
        'size': 3,
      }
      const bodyTo = {
        'page': 0,
        'size': 3,
      }
      await store.dispatch("root/profileMateListFrom", bodyFrom)
      await store.dispatch("root/profileMateListTo", bodyTo)
    })

    

    return { state, onMounted }
  }
}
</script>

<style scoped style="scss">
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

/* pagination */


.pagination {
  margin: auto;
  justify-content: center;
}

.page-item-left > .page-link {
  background: white;
  color: black;
  width: 45px;
  line-height: 2rem;
  font-weight: bold;
  border: #fafafa;
  border-radius: 50%;
  box-shadow: (0 0 8px rgba(24, 24, 24, 0.05));
  margin: 0rem 0.5rem 0rem 0.5rem;
  cursor: pointer;
}

.page-item-right > .page-link {
  background: white;
  color: black;
  width: 45px;
  line-height: 2rem;
  font-weight: bold;
  border: #fafafa;
  border-radius: 50%;
  box-shadow: (0 0 8px rgba(24, 24, 24, 0.05));
  margin: 0rem 0.5rem 0rem 0.5rem;
  cursor: pointer;
}

.page-item > .page-link {
  background: white;
  color: black;
  width: 45px;
  text-align: center;
  line-height: 2rem;
  font-weight: bold;
  border: #fafafa;
  border-radius: 50%;
  box-shadow: (0 0 8px rgba(24, 24, 24, 0.05));
  margin: 0rem 0.5rem 0rem 0.5rem;
  cursor: pointer;
  /* border: 1px solid rgba(1, 1, 1, 0.1); */
}

.active > .page-link {
  background: black;
  color: white;
  line-height: 2rem;
  font-weight: bold;
  border: #fafafa;
  border-radius: 50%;
  box-shadow: (0 0 8px rgba(24, 24, 24, 0.05));
  margin: 0rem 0.5rem 0rem 0.5rem;
}

</style>