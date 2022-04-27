<template>
  <div class="card-frame">
    <div class="card my-4">
      <div class="row">
        <div class="col-6">
          <img :src="`${card.imgUrl}`" class="img-fluid placeSearchListCardImage rounded-start" alt="..." />
        </div>
        <div class="col-6">
          <div class="card-body text-start">
            <p class="card-region">{{ card.position }}</p>
            <p class="card-title">{{ card.title }}</p>
            <p class="card-facility mt-3">{{ card.facility }}</p>
            <div class="d-flex justify-content-between">
              <p class="card-text">{{ card.rate }}</p>
              <p class="card-cost pe-3">{{ card.cost }}</p>
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
.card-frame {
  border-top: 1px solid rgba(1, 1, 1, 0.1);
  border-bottom: 1px solid rgba(1, 1, 1, 0.05);
}
.card-region {
  font-weight: bold;
  color: #767676;
  font-size: 14px;
}
.card-title {
  font-weight: bold;
  color: black;
  font-size: 20px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}
.card-text {
  // margin: 0 0 0px 0;
  font-size: 14px;
  // 두줄 이상이면 말줄임표
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}
.card-cost {
  font-size: 13px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}
.card-facility {
  font-weight: bold;
  color: #767676;
  font-size: 12px;
}
.placeSearchListCardImage {
  height: 100%;
  border-radius: 10px;
  align-self: center;
  object-fit: cover;
}
</style>
