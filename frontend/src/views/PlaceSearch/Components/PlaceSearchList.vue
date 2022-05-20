<template>
  <div class="cardTotalFrame">
    <div class="card my-4">
      <div class="row card-frame">
        <div class="col-6">
          <img :src="`${card.img[0]}`" @click="moveToPlaceDetail(card.id)" @mouseover="checkthis(card)" class="img-fluid placeSearchListCardImage rounded" alt="..." />
        </div>
        <div class="col d-flex flex-row align-items-start" @mouseover="checkthis(card)">
          <div class="card-body text-start d-flex flex-column justify-content-between text-start">
            <div class="d-flex justify-content-between">
              <p class="card-region me-5" @click="moveToPlaceDetail(card.id)">{{ card.address }}</p>
              <div class="heartBox d-flex justify-content-center align-items-center" v-if="`${card.like}` == `true`" @click="clickLike(card.id)">
                <i class="bi bi-heart-fill" style="color: #e01760"></i>
              </div>
              <div v-else @click="clickLike(card.id)"><i class="bi bi-heart"></i></div>
            </div>
            <div @click="moveToPlaceDetail(card.id)">
              <p class="card-title mb-2 me-4">{{ card.name }}</p>
              <div class="mt-4 mb-5 card-bizhour">
                <p v-for="(bizhour, idx) in card.bizhour" :key="idx">{{ bizhour }}</p>
              </div>
            </div>
            <div class="d-flex justify-content-start" @click="moveToPlaceDetail(card.id)">
              <p class="card-rate fw-bold">{{ card.score }} <i class="bi bi-star-fill me-1" style="color: #fe8a01"></i></p>
              <p class="card-reviews d-flex align-items-center ms-2 pe-3" @click="moveToPlaceDetail(card.id)">({{ card.reviewCnt }} reviews)</p>
            </div>
            <!-- <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p> -->
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import axios from "axios";
const BASE_URL = "https://pairplay.site/api/v1/";

export default {
  name: "PlaceSearchList",
  // emits: ["clickLike"],
  props: ["card"],
  setup() {
    const router = useRouter();
    // console.log(props.card, "props.card");
    // console.log(props.cardId, "props.cardId");
    const store = useStore();
    let temp = reactive({ id: "" });

    // const placeSearchData = ref(props);
    // const like = ref(props.card.like);
    // let like = ref(props.card[props.cardId].like);
    // console.log(card.id);
    // console.log(placeSearchData.value.card, "placeSearchData");
    const clickLike = async (id) => {
      // console.log(id, "id는?");
      await axios({ method: "put", headers: { Authorization: "Bearer " + localStorage.getItem("jwt") }, url: `${BASE_URL}/places/like/${id}` });
      await store.dispatch("root/getPlaceSearchInfo", store.state.root.addPlaceFilters);
    };
    const checkthis = async (card) => {
      if (temp.id != card.id) {
        await store.dispatch("root/showMapMarker", card);
        temp = reactive({ card });
      } else {
        // pass;
      }
    };
    const moveToPlaceDetail = (res) => {
      // console.log(res, "여기디테일어디");
      router
        .push({
          name: "PlaceDetail",
          params: {
            id: res,
          },
        })
        .then(() => window.scrollTo(0, 0));
    };
    // const changeMark = () => {};
    return {
      // placeSearchData,
      temp,
      clickLike,
      moveToPlaceDetail,
      checkthis,
      // getCards,
      // like,
      // changeMark,
    };
  },
};
</script>

<style lang="scss" scoped>
.card {
  border: 0px;
  &:hover {
    box-shadow: 0.8px 0.8px 0.8px 0.8px rgba(0.1, 0.1, 0.1, 0.1);
  }
  cursor: pointer;
}
.cardTotalFrame {
  border-top: 1px solid rgba(1, 1, 1, 0.1);
  border-bottom: 1px solid rgba(1, 1, 1, 0.05);
}
// .card-frame {
//   // border-top: 1px solid rgba(1, 1, 1, 0.1);
//   // border-bottom: 1px solid #ffff;
// }
// .row {
// }
.placeSearchListCardImage {
  height: 100%;
  max-height: 250px;
  width: 100%;
  border-radius: 10px;
  align-self: center;
  object-fit: cover;
  cursor: pointer;
}
.card-body {
  // margin: 0px;
  padding: 5px 12px 0px 10px;
}
.card-region {
  font-weight: bold;
  color: #767676;
  font-size: 14px;
  padding-bottom: 0px;
  margin-bottom: 0px;
  overflow: hidden;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 1;
  cursor: pointer;
}
.card-title {
  padding-top: 0px;
  margin-top: 0px;
  padding-bottom: 0px;
  margin-bottom: 0px;
  font-weight: bold;
  color: black;
  font-size: 22px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  word-break: break-all;
  cursor: pointer;
}
.card-text {
  // margin: 0 0 0px 0;
  font-size: 16px;
  // 두줄 이상이면 말줄임표
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  cursor: pointer;
}
.card-cost {
  font-size: 18px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  cursor: pointer;
}
.card-bizhour {
  font-weight: 500;
  color: #767676;
  font-size: 14px;
  word-break: keep-all;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  cursor: pointer;
}
.card-rate {
  font-size: 18px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  cursor: pointer;
}
.card-reviews {
  font-size: 16px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  cursor: pointer;
}
// .heartBox {
//   // box-shadow: 1px 1px 1px 1px rgba(0.5, 0, 0.5, 0.5);
//   // margin: 0rem auto 0rem auto;
//   width: 2rem;
//   height: 2rem;
//   border-radius: 30%;
//   &:hover {
//     // box-shadow: 4px 4px 6px 0 rgba(255, 255, 255, 0.5), -4px -4px 6px 0 rgba(116, 125, 136, 0.5), inset -4px -4px 6px 0 rgba(255, 255, 255, 0.2), inset 4px 4px 6px 0 rgba(0, 0, 0, 0.4);
//     box-shadow: 0.5px 0.5px 0.5px 0.5px rgba(0.3, 0.3, 0.3, 0.3);
//   }
// }

.bi-heart {
  z-index: 1;
  cursor: pointer;
  // &:hover {
  //   border: 1px solid black;
  //   border-radius: 10px;
  // }
}

.bi-heart-fill {
  cursor: pointer;
  z-index: 1;
}
</style>
