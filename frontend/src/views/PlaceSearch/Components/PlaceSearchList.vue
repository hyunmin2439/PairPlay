<template>
  <div class="cardTotalFrame">
    <div class="card my-4">
      <div class="row card-frame">
        <div class="col-6">
          <img :src="`${card.img[0]}`" class="img-fluid placeSearchListCardImage rounded" alt="..." />
        </div>
        <div class="col d-flex flex-row align-items-start">
          <div class="card-body text-start d-flex flex-column justify-content-between text-start">
            <div class="d-flex justify-content-between">
              <p class="card-region">{{ card.address }}</p>
              <div v-if="`${card.like}` == `true`" @click="clickLike(card.id)"><i class="bi bi-heart-fill pe-3" style="color: #e01760"></i></div>
              <div v-else @click="clickLike(card.id)"><i class="bi bi-heart pe-3"></i></div>
            </div>
            <div>
              <p class="card-title mb-2 me-5">{{ card.name }}</p>
              <div class="mt-4 mb-5 card-bizhour">
                <p v-for="(bizhour, idx) in card.bizhour" :key="idx">{{ bizhour }}</p>
              </div>
            </div>
            <div class="d-flex justify-content-start">
              <p class="card-rate fw-bold">{{ card.score }} <i class="bi bi-star-fill me-1" style="color: #fe8a01"></i></p>
              <p class="card-reviews d-flex align-items-center ms-2 pe-3">({{ card.reviewCnt }} reviews)</p>
            </div>
            <!-- <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p> -->
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import { ref } from "vue";
import { useStore } from "vuex";

const BASE_URL = "https://pairplay.site/api/v1/";
import axios from "axios";
export default {
  name: "PlaceSearchList",
  // emits: ["clickLike"],
  props: ["card", "cardId"],
  setup() {
    // console.log(props.card, "props.card");
    // console.log(props.cardId, "props.cardId");
    const store = useStore();
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
    return {
      // placeSearchData,
      clickLike,
      // getCards,
      // like,
    };
  },
};
</script>

<style lang="scss" scoped>
.card {
  border: 0px;
}
.cardTotalFrame {
  border-top: 1px solid rgba(1, 1, 1, 0.1);
  border-bottom: 1px solid rgba(1, 1, 1, 0.05);
}
.card-frame {
  // border-top: 1px solid rgba(1, 1, 1, 0.1);
  // border-bottom: 1px solid #ffff;
}
.row {
}
.placeSearchListCardImage {
  height: 100%;
  max-height: 250px;
  width: 100%;
  border-radius: 10px;
  align-self: center;
  object-fit: cover;
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
}
.card-cost {
  font-size: 18px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
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
}
.card-rate {
  font-size: 18px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}
.card-reviews {
  font-size: 16px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}
.bi-haert {
  // z-index: 1;
}
</style>
