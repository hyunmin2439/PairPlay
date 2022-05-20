<template>
  <!-- <div class="card"> -->
  <div class="card" style="border: 0px">
    <div class="d-flex">
      <!-- {{ review }} -->
      <div class="col d-flex flex-row align-items-start">
        <div class="card-body px-0 me-5 text-start">
          <div class="d-flex align-items-center">
            <div><img :src="`${review.profileImage}`" class="profile-image" alt="" /></div>
            <div class="ms-3 d-flex col justify-content-between">
              <div>
                <p class="mb-0 fw-bold">{{ review.nickname }}</p>
                <p class="review-date mb-0">{{ review.writtenDt[0] }}년 {{ review.writtenDt[1] }}월</p>
              </div>
              <p class="card-rate mb-0 fw-bold d-flex align-items-center">
                <i class="bi bi-star-fill me-1" style="color: #fe8a01"></i>{{ `${((review.cleanness + review.place + review.location + review.price) / 4).toFixed(1)}` }}
              </p>
            </div>
          </div>

          <div>
            <div class="d-flex justify-content-between">
              <p class="review-content me-5">{{ review.description }}</p>
            </div>
          </div>
          <div class="d-flex justify-content-between align-items-center px-3">
            <div class="review-score-button"><strong>시설이</strong> {{ reviewScore.placeScore }}</div>
            <div class="review-score-button"><strong>청결이</strong> {{ reviewScore.cleannessScore }}</div>
            <div class="review-score-button"><strong>위치가</strong> {{ reviewScore.locationScore }}</div>
            <div class="review-score-button"><strong>가격이</strong> {{ reviewScore.priceScore }}</div>
            {{ reviewScore }}
          </div>
        </div>
      </div>
      <div class="review-picture col-4">
        <img v-if="review.reviewImage.length > 1" :src="`${review.reviewImage}`" class="img-fluid review-card-Image rounded" alt="..." />
        <div v-else class="img-fluid review-card-Image rounded" alt="..." />
      </div>
    </div>
  </div>
</template>

<script>
import { reactive, onMounted } from "vue";
export default {
  name: "PlaceDetailReviews",
  props: ["review"],
  setup(props) {
    const reviewData = reactive(props);
    const reviewScore = reactive({});
    const checkScore = () => {
      let score = { cleannessScore: "", placeScore: "", locationScore: "", priceScore: "" };
      if (0 <= reviewData.review.cleanness <= 1) {
        score.cleannessScore = "매우 별로에요";
      } else if (1 < reviewData.review.cleanness <= 2) {
        score.cleannessScore = "별로에요";
      } else if (2 < reviewData.review.cleanness <= 3) {
        score.cleannessScore = "보통이에요";
      } else if (3 < reviewData.review.cleanness <= 4) {
        score.cleannessScore = "좋아요";
      } else {
        score.cleannessScore = "매우 좋아요";
      }
      if (0 <= reviewData.review.place <= 1) {
        score.placeScore = "매우 좋지 않아요";
      } else if (1 < reviewData.review.place <= 2) {
        score.placeScore = "좋지 않아요";
      } else if (2 < reviewData.review.place <= 3) {
        score.placeScore = "보통이에요";
      } else if (3 < reviewData.review.place <= 4) {
        score.placeScore = "좋아요";
      } else {
        score.placeScore = "매우 좋아요";
      }

      if (0 <= reviewData.review.location <= 1) {
        score.locationScore = "매우 좋지 않아요";
      } else if (1 < reviewData.review.location <= 2) {
        score.locationScore = "좋지 않아요";
      } else if (2 < reviewData.review.location <= 3) {
        score.locationScore = "보통이에요";
      } else if (3 < reviewData.review.location <= 4) {
        score.locationScore = "좋아요";
      } else {
        score.locationScore = "매우 좋아요";
      }

      if (0 <= reviewData.review.price <= 1) {
        score.priceScore = "매우 비싸요";
      } else if (1 < reviewData.review.price <= 2) {
        score.priceScore = "비싸요";
      } else if (2 < reviewData.review.price <= 3) {
        score.priceScore = "보통이에요";
      } else if (3 < reviewData.review.price <= 4) {
        score.priceScore = "저렴해요";
      } else {
        score.priceScore = "매우 저렴해요";
      }
      reviewScore.props.memberId = score;
    };
    onMounted(async () => {
      checkScore();
    });
    return { reviewData, reviewScore, checkScore };
  },
};
</script>

<style lang="scss" scoped>
.card {
  // border: 1px solid yellow;
  max-height: 250px;
}
.profile-image {
  width: 40px;
  height: 40px;
  border-radius: 50%;
}
.review-content {
  margin: 1.5rem 1rem 1.5rem 1rem;
  font-size: 1.1rem;
}
.review-score-button {
  padding: 0.25rem 0.5rem 0.25rem 0.5rem;
  margin: 0rem 0.25rem 0rem 0.25rem;
  // box-shadow: 0 0 8px rgba(24, 24, 24, 0.1);
  box-shadow: (0 1px 10px rgba(24, 24, 24, 0.04));
  border: 1px solid rgba(1, 1, 1, 0.1);
  background: #ffff;
  border-radius: 10px;
  font-size: 0.9rem;
}
.review-picture {
  display: flex;
  justify-content: center;
  align-items: center;
  background: #ffff;
  // padding: 0rem 2rem 0rem 0rem;
  margin: 0rem 2rem 0rem 0rem;
}
.review-card-Image {
  object-fit: cover;
  height: 80%;
  min-height: 200px;
  min-width: 380px;
}

.review-date {
  font-size: 0.8rem;
  color: rgb(49, 49, 49);
}
</style>
