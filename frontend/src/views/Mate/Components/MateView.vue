<template>
  <div class="container">
    <p class="total-cards col-12">총 00 건의 검색결과</p>
    <div class="d-flex row" style="width: 100%;">
      <div
        v-for="(card, i) in state.cards" :key="i"
        class="col-3 my-4"
      >
        <a class="card d-flex justify-content-between" href="#" style="flex-direction: column;">
          <div class="mx-4 mt-4">
            <h3>{{ card.title }}</h3>
            <div class="d-flex justify-content-between align-items-center pt-4">
              <div>
                <div class="small mb-2">{{ card.location }}</div>
                <div class="medium">{{ card.description }}</div>
              </div>
              <div>
                <span style="font-size: medium; font-weight: bold;">{{ state.category[card.categoryId] }}</span>
              </div>
            </div>
          </div>
          <div
            class="d-flex justify-content-between align-items-center"
          >
            <label class="like">
              <input type="checkbox" checked>
              <div class="hearth" />
            </label>
            <div class="me-4" style="color: rgb(174 174 174); font-size: small;">
              {{ card.timeDiff }}일 전 업데이트
            </div>
          </div>
          <!-- <label class="like">
            <input type="checkbox">
            <div class="hearth"/>
          </label> -->
        </a>
      </div>
    </div>

    <!-- 페이지네이션 시작 -->
    <div>
      <ul class="pagination mt-5 pt-5">
        <li class="page-item-left">
          <a class="page-link" style="font-family: bootstrap-icons"> &#xF284; </a>
        </li>
        <!-- aria-current="page" -->
        <li v-for="idx in state.totalPages" :key="idx" class="page-item">
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
  </div>
</template>

<script>
import { reactive, computed, onMounted } from 'vue';
import { useStore } from 'vuex';

export default {
  name: "MateView",
  setup() {
    const store = useStore()
    // let cards = [];
    const state = reactive({
      cards: computed(() => store.getters["root/mateArticleList"]),
      changeList: computed(() => store.getters["root/changeList"]),
      category: {
        1: "축구", 2: "풋살", 3: "농구", 4: "야구",
        5: "볼링", 6: "골프", 7: "테니스", 8: "배드민턴",
        9: "헬스", 10:	"필라테스", 11:	"격투기", 12:	"수영"
      },
      totalPages: computed(() => store.getters["root/mateArticleListTotalPage"]),
      page: 1,
      size: 8,
    })
    onMounted(async () => {
      await store.dispatch("root/mateArticleList", {
        "page": 0,
        "size": 8,
      })
      var activeBtn = document.getElementsByClassName("page-item")[state.page-1]
      activeBtn.classList.add("active")
    })

    const changePage = async function (event) {
      for (var i=0; i < state.totalPages; i++) {
        var Btn = document.getElementsByClassName("page-item")[i]
        Btn.classList.remove("active")
      }
      var activeBtn = document.getElementsByClassName("page-item")[Number(event.target.textContent)-1]
      activeBtn.classList.add("active")
      state.page = Number(event.target.textContent)-1
      await store.dispatch("root/mateArticleList", {
        "page": state.page,
        "size": 8,
      })
    }
    // let expand = false;
    // const refresh = () =>
    // const changedExpand = () => {
    //   expand = !expand;
    //   console.log(expand, "expand");
    //   console.log(value, "value");
    //   return;
    // };

    return { state, onMounted, changePage };
  }
}
</script>

<style lang='scss' scoped>
@import url('https://fonts.googleapis.com/css?family=Nunito:400,700');


* {
  transition: all 0.3s ease-out;
}

html,
body {
  height: 100%;
  font-family: "Nunito", sans-serif;
  font-size: 16px;
}

.container {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  // flex-wrap: wrap;
  // align-items: center;
  // align-items: left;
  // justify-content: center;
  // justify-content: left; 
}

h3 {
  color: #262626;
  font-size: large;
  line-height: 24px;
  font-weight: bold;
  margin-bottom: 4px;
}

span {
  font-size: medium;
  font-weight: 400;
  line-height: 20px;
  color: #666666;
}

.small {
  font-size: small;
  text-decoration: none;
  color: black;
}
.medium {
  font-size: medium;
  text-decoration: none;
  color: black;
}

  // .go-corner {
  //   display: flex;
  //   align-items: center;
  //   justify-content: center;
  //   position: absolute;
  //   width: 32px;
  //   height: 32px;
  //   overflow: hidden;
  //   top: 0;
  //   right: 0;
  //   background-color: #00838d;
  //   border-radius: 0 4px 0 32px;
  // }

  // .go-arrow {
  //   margin-top: -4px;
  //   margin-right: -4px;
  //   color: white;
  //   font-family: courier, sans;
  // }

  .card {
    display: block;
    top: 0px;
    position: relative;
    max-width: 100%;
    height: 110%;
    background-color: #f2f8f9;
    border-radius: 10px;
    // padding: 32px 24px;
    margin: 12px;
    text-decoration: none;
    z-index: 0;
    overflow: hidden;
    border: 1px solid #f2f8f9;

    &:hover {
      transition: all 0.2s ease-out;
      box-shadow: 0px 4px 8px rgba(38, 38, 38, 0.2);
      top: -4px;
      border: 1px solid #cccccc;
      background-color: white;
    }

    // &:before {
    //   content: "";
    //   position: absolute;
    //   z-index: -1;
    //   top: -16px;
    //   right: -16px;
    //   background: #00838d;
    //   height: 32px;
    //   width: 32px;
    //   border-radius: 32px;
    //   transform: scale(2);
    //   transform-origin: 50% 50%;
    //   transition: transform 0.15s ease-out;
    // }

    // &:hover:before {
    //   transform: scale(2.15);
    // }
}

.total-cards {
  text-align: right;
  margin-top: 1rem;
  margin-right: 8rem;
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
  // border: 1px solid rgba(1, 1, 1, 0.1);
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