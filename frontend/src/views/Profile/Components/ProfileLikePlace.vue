<template>
  <div class="d-flex" style="width: 100%;">
    <div
      v-for="(data, idx) in state.profileLikePlaceList"
      :key="idx"
      class="col-4"
    >
      <div class="container">
        <img :src="data.img[0]" alt="" class="like-img" style="cursor: pointer;" @click="moveToPlace(data.placeId)">
      </div>
      <div class="container d-flex justify-content-between">
        <div>
          <h5 class="address mt-2">{{ data.address.split(" ")[0] }} {{ data.address.split(" ")[1] }} {{ data.address.split(" ")[2] }}</h5>
          <h5 class="name">{{ data.name }}</h5>
          <h5 class="category">종류: {{ data.category }}</h5>
        </div>
        <div class="me-3">
          <label class="like">
            <input type="checkbox" checked @click="profileLikePlaceCancle(data.placeId)">
            <div class="hearth" />
          </label>
        </div>
      </div>
    </div>
  </div>
  <!-- 페이지네이션 시작 -->
  <div>
    <ul class="pagination mt-5">
      <li class="page-item-left">
        <a class="page-link" style="font-family: bootstrap-icons"> &#xF284; </a>
      </li>
      <!-- aria-current="page" -->
      <li v-for="idx in state.profileLikePlaceTotalPages+1" :key="idx" class="page-item">
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
import { useRouter } from 'vue-router'

export default {
  name: "ProfileLikePlace",
  setup () {
    const store = useStore()
    const router = useRouter()
    const state = reactive({
      page: 0,
      profileLikePlaceTotalPages: computed(() => store.getters["root/profileLikePlaceTotalPages"]),
      profileLikePlaceList: computed(() => store.getters["root/profileLikePlaceList"]),
    })

    onMounted (async () => {
      var activeBtn = document.getElementsByClassName("page-item")[state.page]
      activeBtn.classList.add("active")
    })

    const changePage = async function (event) {
      for (var i=0; i <= state.profileLikePlaceTotalPages; i++) {
        var Btn = document.getElementsByClassName("page-item")[i]
        Btn.classList.remove("active")
      }
      var activeBtn = document.getElementsByClassName("page-item")[Number(event.target.textContent)-1]
      activeBtn.classList.add("active")
      console.log(Number(event.target.textContent)-1)
      await store.dispatch("root/profileLikePlace", Number(event.target.textContent)-1)
    }

    const profileLikePlaceCancle = async function (id) {
      await store.dispatch("root/profileLikePlaceCancle", {
        "id": id,
        "page": state.page
      })
    }

    const moveToPlace = function(placeId) {
      router.push({
        name: "PlaceDetail",
        params: {
          id: placeId
        }
      })
    }

    return { state, onMounted, changePage, profileLikePlaceCancle, moveToPlace }
  }
}
</script>

<style scoped lang="scss">

.like-img {
  width: 90%;
  height: 13rem;
  border-radius: 10px;
}

.address {
  font-size: small;
  color: #adadad;
}

.name {
  font-size: large;
  font-weight: bold;
}

.category {
  font-size: medium;
  font-weight: bold;
}


input {
  display: none;
}

.like {
  display: inline;
  width: var(--size);
  height: var(--size);
  cursor: pointer;
  border-radius: 999px;
  overflow: visible;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
  -webkit-tap-highlight-color: transparent;
    --size: 80px;
  --frames: 62;
}

.hearth {
  background-image: url('https://assets.codepen.io/23500/Hashflag-AppleEvent.svg');
  background-size: calc(var(--size) * var(--frames)) var(--size);
  background-repeat: no-repeat;
  background-position-x: calc(var(--size) * (var(--frames) * -1 + 2));
  background-position-y: calc(var(--size) * 0.02);
  width: var(--size);
  height: var(--size);
  --size: 80px;
  --frames: 62;
  animation: like 1s steps(calc(var(--frames) - 3));  
  animation-fill-mode: forwards;
}

input:checked + .hearth {
  animation: like 1s steps(calc(var(--frames) - 3));  
  animation-fill-mode: forwards;
}

@keyframes like {
  0% {
    background-position-x: 0;
  }
  100% {
    background-position-x: calc(var(--size) * (var(--frames) * -1 + 3));
  }
}

@media (hover: hover) {
  .like:hover {
    background-color: #E1255E15;
    .hearth {
      background-position-x: calc(var(--size) * (var(--frames) * -1 + 1));
    }
  }
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