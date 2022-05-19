<template>
  <div>
    <p class="total-cards col-12">총 {{ state.totalElements }} 건의 검색결과</p>
    <div class="d-flex row" style="width: 100%;">
      <div
        v-for="(card, i) in state.cards" :key="i"
        class="col-3 mt-4 mb-5"
      >
        <a class="card d-flex justify-content-between" style="flex-direction: column;">
        <!-- Modal -->
          <div class="mx-4 mt-4">
            <div class="d-flex justify-content-between">
              <span class="title">{{ card.title }}</span>
              <button class="detail" type="button" data-bs-toggle="modal" data-bs-target="#staticBackdrop" @click="changeMateDetail(card.activityId)">자세히</button>
            </div>
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
            <label v-if="card.like==true" class="like">
              <input type="checkbox" checked @click="mateLikeChange(card.activityId)">
              <div class="hearth" />
            </label>
            <label v-else class="like">
              <input type="checkbox" @click="mateLikeChange(card.activityId)">
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
        <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
          <div class="modal-dialog modal-dialog-centered" style="max-width: 50vw;">
            <div class="modal-content" style="border: none;">
              <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel"><b>자세한 정보</b></h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                <br>
                <div class="row">
                  <div class="col-5">
                    {{ mateDetail }}
                    <div class="row my-3">
                      <div class="col-1"></div>
                      <div class="col-3 modal-text" style="padding-bottom: 4%; border-bottom: double; border-color: rgb(226 226 226);">공고 제목</div>
                      <div class="col-7" style="border-bottom: double; border-color: rgb(226 226 226);">{{ mateDetail.title }}</div>
                    </div>
                    <div class="row my-3">
                      <div class="col-1"></div>
                      <div class="col-3 modal-text" style="padding-bottom: 4%; border-bottom: 1px solid rgb(226 226 226);">운동 종류</div>
                      <div class="col-7" style="border-bottom: 1px solid rgb(226 226 226);">{{ state.category[mateDetail.categoryId] }}</div>
                    </div>
                    <div class="row my-3">
                      <div class="col-1"></div>
                      <div class="col-3 modal-text" style="padding-bottom: 4%; border-bottom: 1px solid rgb(226 226 226);">장소</div>
                      <div class="col-7" style="border-bottom: 1px solid rgb(226 226 226);">{{ mateDetail.location }}</div>
                    </div>
                    <div class="row my-3">
                      <div class="col-1"></div>
                      <div class="col-3 modal-text" style="padding-bottom: 4%; border-bottom: 1px solid rgb(226 226 226);">성별</div>
                      <div v-if="mateDetail.gender==0" class="col-7" style="border-bottom: 1px solid rgb(226 226 226);">남성</div>
                      <div v-else-if="mateDetail.gender==1" class="col-7" style="border-bottom: 1px solid rgb(226 226 226);">여성</div>
                      <div v-else class="col-7" style="border-bottom: 1px solid rgb(226 226 226);">성별무관</div>
                    </div>
                    <div class="row my-3">
                      <div class="col-1"></div>
                      <div class="col-3 modal-text" style="padding-bottom: 4%; border-bottom: 1px solid rgb(226 226 226);">연령대</div>
                      <div class="col-7" style="border-bottom: 1px solid rgb(226 226 226);">{{ mateDetail.age }}대 모집</div>
                    </div>
                    <div class="row my-3">
                      <div class="col-1"></div>
                      <div class="col-3 modal-text" style="padding-bottom: 4%; border-bottom: 1px solid rgb(226 226 226);">설명</div>
                      <div class="col-7" style="border-bottom: 1px solid rgb(226 226 226);">{{ mateDetail.description }}</div>
                    </div>
                    <div class="row mt-5">
                      <div class="col-1"></div>
                      <button class="col-3 mate-apply" @click="mateApplyFor(mateDetail.activityId)">신청하기</button>
                    </div>
                  </div>
                  <div class="col-7" style="text-align: center;">
                    <br>
                    <img :src="mateDetail.profileImage" alt="" style="width: 70%;">
                  </div>
                </div>               
                <br>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" id="modal-check" data-bs-dismiss="modal">확인</button>
              </div>
            </div>
          </div>
        </div>
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
import { reactive, computed, onMounted, ref } from 'vue';
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
      totalElements: computed(() => store.getters["root/mateArticleListTotalElements"]),
      size: 8,
      page: 0,
      mateArticleFilter: computed(() => store.getters["root/mateArticleFilter"]),
    })

    const mateDetail = ref({});

    onMounted(async () => {
      await store.dispatch("root/mateArticleList", {
        "page": 0,
        "size": 8,
      })
      var activeBtn = document.getElementsByClassName("page-item")[state.page]
      activeBtn.classList.add("active")
    })

    const changeMateDetail = async function (id) {
      await store.dispatch("root/mateDetailInfo", {
        activityId: id
      })
      mateDetail.value = store.getters["root/mateDetailList"]
    }

    const changePage = async function (event) {
      for (var i=0; i < state.totalPages; i++) {
        var Btn = document.getElementsByClassName("page-item")[i]
        Btn.classList.remove("active")
      }
      var activeBtn = document.getElementsByClassName("page-item")[Number(event.target.textContent)-1]
      activeBtn.classList.add("active")
      if (state.mateArticleFilter.categoryId==0 & state.mateArticleFilter.sido=="" & state.mateArticleFilter.search=="") {
        state.page = Number(event.target.textContent)-1
        await store.dispatch("root/mateArticleList", {
          "page": Number(event.target.textContent)-1,
        })
      } else {
        const body = {
          "categoryId": state.mateArticleFilter.categoryId,
          "gungu": state.mateArticleFilter.gungu,
          "search": state.mateArticleFilter.search,
          "sido": state.mateArticleFilter.sido,
          "page": Number(event.target.textContent)-1,
        }
        state.page = Number(event.target.textContent)-1
        await store.dispatch("root/mateFilterChange", body)
      }
    }

    const mateApplyFor = async function (activityId) {
      await store.dispatch("root/mateApplyFor", {
        'activityId': activityId
      })
      var button = document.getElementById("modal-check")
      button.click()
    }

    const mateLikeChange = async function (activityId) {
      await store.dispatch("root/mateLikeChange", {
        'activityId': activityId,
        // "categoryId": state.mateArticleFilter.categoryId,
        // "gungu": state.mateArticleFilter.gungu,
        // "search": state.mateArticleFilter.search,
        // "sido": state.mateArticleFilter.sido,
        // "page": state.page,
      })
    }

    return { state, mateDetail, onMounted, changeMateDetail, changePage, mateApplyFor, mateLikeChange };
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

.title {
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

    .detail {
      background-color: #626161;
      color: white;
      font-size: small;
      border-radius: 5px;
      border: none;
    }

    &:hover {
      transition: all 0.2s ease-out;
      box-shadow: 0px 4px 8px rgba(38, 38, 38, 0.2);
      top: -4px;
      border: 1px solid #cccccc;
      background-color: white;

      .detail {
        font-size: medium;
        font-weight: bold;
        padding-top: 1%;
        padding-bottom: 1%;
      }
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

.modal-text {
  font-weight: bold;
}

.mate-apply {
  background: #626161;
  border: none;
  border-radius: 5px;
  font-size: large;
  font-weight: bold;
  color: white;
  padding-top: 2%;
  padding-bottom: 2%;
}
</style>