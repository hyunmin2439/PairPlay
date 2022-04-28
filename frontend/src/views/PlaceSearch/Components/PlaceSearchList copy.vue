<template>
  <div class="cardTotalFrame">
    <div class="card my-4">
      <div class="row card-frame">
        <div class="col-6">
          <img :src="`${card.imgUrl}`" class="img-fluid placeSearchListCardImage rounded" alt="..." />
        </div>
        <div class="col d-flex flex-row align-items-start">
          <div class="card-body text-start d-flex flex-column justify-content-between text-start">
            <div class="d-flex justify-content-between">
              <p class="card-region">{{ card.position }}</p>
              <i class="bi bi-heart"></i>
            </div>
            <div>
              <p class="card-title mb-2 me-4">{{ card.title }}</p>
              <p class="card-facility mt-4 mb-5">{{ card.facility }}</p>
            </div>
            <div class="d-flex justify-content-between">
              <p class="card-rate fw-bold"><i class="bi bi-star-fill me-1" style="color: #fe8a01"></i> {{ card.rate }}</p>
              <p class="card-cost pe-3 fw-bold">{{ card.cost }}</p>
            </div>
            <!-- <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p> -->
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from "vue";

export default {
  name: "PlaceSearchList",
  props: ["card"],
  setup(props) {
    const placeSearchData = ref(props.detailData);
    return {
      placeSearchData,
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
.card-rate {
  font-size: 18px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}
.card-facility {
  font-weight: 500;
  color: #767676;
  font-size: 14px;
}
</style>
