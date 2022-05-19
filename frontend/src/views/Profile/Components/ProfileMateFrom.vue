<template>
  <div 
    v-for="(data, idx) in state.profileMateListFrom" :key=idx
    class="container card my-5 d-flex row"
  >
    <div class="col-2 profile-image" style="margin: auto;">
      <img :src="data.profileImage" alt="" style="width: 100%;">
    </div>
    <div class="col-7 ms-3 mt-4 mb-2">
      <h4 class="mb-3"><b>{{ data.nickname }}의 {{ data.title }}</b>
        <span style="font-size: large;">( {{ data.location }} )</span>
      </h4> 
      <p>{{ data.description }}</p>
      <p style="font-size: large;" class="mt-5"><b>종목 : {{ state.category[data.categoryId] }}</b></p>
    </div>
    <div v-if="data.accept==0" class="col-2 my-4 d-flex justify-content-around" style="flex-direction: column;">
      <button class="btn-mate accept" @click="mateFromAccept(data.id)">
        수락
      </button>
      <button class="btn-mate reject" @click="mateFromReject(data.id)">
        거절
      </button>
    </div>
    <div v-if="data.accept==1" class="col-2 my-4 d-flex justify-content-around" style="flex-direction: column;">
      <button class="btn-mate accept" style="cursor: auto;">
        수락완료
      </button>
      <button class="btn-mate reject" @click="mateFromReject(data.id)">
        거절
      </button>
    </div>
  </div>
  <!-- 페이지네이션 시작 -->
  <div>
    <ul class="pagination mt-5">
      <li class="page-item-left">
        <a class="page-link" style="font-family: bootstrap-icons"> &#xF284; </a>
      </li>
      <!-- aria-current="page" -->
      <li v-for="idx in state.profileMateListFromTotalPage" :key="idx" class="page-item">
        <a class="page-link" @click="changePage($event)">{{ idx }}</a>
      </li>
      <!-- <li class="page-item active">
        <a class="page-link" href="#">10</a>
      </li> -->
      <li class="page-item-right">
        <a class="page-link" style="font-family: bootstrap-icons">&#xF285;</a>
      </li>
    </ul>
  </div>
</template>

<script>
import { reactive, computed, onMounted } from 'vue'
import { useStore } from 'vuex'
export default {
  name: "ProfileMateFrom",
  setup () {
    const store = useStore()
    const state = reactive({
      profileMateListFrom: computed(() => store.getters["root/profileMateListFrom"]),
      profileMateListFromTotalPage: computed(() => store.getters["root/profileMateListFromTotalPage"]),
      pageFrom: 1,
      size: 3,
      category: {
        1: "축구", 2: "풋살", 3: "농구", 4: "야구",
        5: "볼링", 6: "골프", 7: "테니스", 8: "배드민턴",
        9: "헬스", 10:	"필라테스", 11:	"격투기", 12:	"수영"
      },
    })

    onMounted (async () => {
      var activeBtn = document.getElementsByClassName("page-item")[state.pageFrom-1]
      activeBtn.classList.add("active")
    })

    const changePage = async function (event) {
      for (var i=0; i < state.profileMateListFromTotalPage; i++) {
        var Btn = document.getElementsByClassName("page-item")[i]
        Btn.classList.remove("active")
      }
      var activeBtn = document.getElementsByClassName("page-item")[Number(event.target.textContent)-1]
      activeBtn.classList.add("active")
      state.pageFrom = Number(event.target.textContent)
      await store.dispatch("root/profileMateListFrom", {
        "page": Number(event.target.textContent)-1,
        "size": 3,
      })
    }

    const mateFromAccept = async function (id) {
      await store.dispatch("root/profileMateFromAccept", {
        'id': id,
        'page': state.pageFrom-1,
      })
    }

    const mateFromReject = async function (id) {
      await store.dispatch("root/profileMateFromReject", {
        'id': id,
        'page': state.pageFrom-1,
      })
    }

    return { state, onMounted, changePage, mateFromAccept, mateFromReject }
  }
}
</script>

<style scoped lang="scss">
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