<template>
  <div class="d-flex" style="width: 100%;">
    <div
      v-for="(data, idx) in state.profileLikeMateList"
      :key="idx"
      class="col-4"
    >
      <div class="container my-4 ms-5">
        <img :src="data.categoryImage" alt="" class="like-img">
      </div>
      <div class="container d-flex justify-content-between">
        <div>
          <h5 class="address mt-2">{{ data.location }}</h5>
          <h5 class="name">{{ data.title }}</h5>
          <h5 class="category">종류: {{ state.category[data.categoryId] }}</h5>
        </div>
        <div class="me-3">
          <label class="like">
            <input type="checkbox" checked @click="profileLikeMateCancle(data.id)">
            <div class="hearth" />
          </label>
        </div>
      </div>
      <p class="description">{{ data.description }}</p>
    </div>
  </div>
  <!-- 페이지네이션 시작 -->
  <div>
    <ul class="pagination mt-5">
      <li class="page-mate-left">
        <a class="page-link" style="font-family: bootstrap-icons"> &#xF284; </a>
      </li>
      <!-- aria-current="page" -->
      <li v-for="idx in state.profileLikeMateTotalPages" :key="idx" class="page-mate">
        <a class="page-link" @click="changePage($event)">{{ idx }}</a>
      </li>
      <!-- <li class="page-item active">
        <a class="page-link" href="#">10</a>
      </li> -->
      <li class="page-mate-right">
        <a class="page-link" style="font-family: bootstrap-icons">&#xF285;</a>
      </li>
    </ul>
  </div>
</template>

<script>
import { reactive, computed, onMounted } from 'vue'
import { useStore } from 'vuex'

export default {
  name: "ProfileLikeMate",
  setup () {
    const store = useStore()
    const state = reactive({
      page: 0,
      profileLikeMateTotalPages: computed(() => store.getters["root/profileLikeMateTotalPages"]),
      profileLikeMateList: computed(() => store.getters["root/profileLikeMateList"]),
      category: {
        1: "축구", 2: "풋살", 3: "농구", 4: "야구",
        5: "볼링", 6: "골프", 7: "테니스", 8: "배드민턴",
        9: "헬스", 10:	"필라테스", 11:	"격투기", 12:	"수영"
      },
    })

    onMounted (async () => {
      var activeBtn = document.getElementsByClassName("page-mate")[state.page]
      activeBtn.classList.add("active")
    })

    const changePage = async function () {
      for (var i=0; i < state.profileLikeMateTotalPages; i++) {
        var Btn = document.getElementsByClassName("page-mate")[i]
        Btn.classList.remove("active")
      }
      var activeBtn = document.getElementsByClassName("page-mate")[Number(event.target.textContent)-1]
      activeBtn.classList.add("active")
      console.log(Number(event.target.textContent)-1)
      await store.dispatch("root/profileLikeMate", Number(event.target.textContent)-1)
    }

    const profileLikeMateCancle = async function (id) {
      await store.dispatch("root/profileLikeMateCancle", {
        "id": id,
        "page": state.page
      })
    }

    return { state, onMounted, changePage, profileLikeMateCancle }
  }
}
</script>

<style scoped lang="scss">

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

.description {
  font-size: small;
  margin-left: 1rem;
  margin-top: 1rem;
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

.page-mate-left > .page-link {
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

.page-mate-right > .page-link {
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

.page-mate > .page-link {
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

.like-img {
  position: relative;
  width: 50%;
}

</style>