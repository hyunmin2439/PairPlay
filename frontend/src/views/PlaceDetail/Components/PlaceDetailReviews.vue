<template>
  <!-- <div class="card"> -->
  <div class="card" style="border: 0px">
    <div class="d-flex">
      <!-- {{ review }} -->
      <div class="col d-flex flex-row align-items-start">
        <div class="card-body px-0 me-5 text-start">
          <div class="d-flex align-items-center">
            <div><img src="https://images.pexels.com/photos/2294361/pexels-photo-2294361.jpeg?auto=compress&cs=tinysrgb&h=650&w=940" class="profile-image" alt="" /></div>
            <div class="ms-3 d-flex col justify-content-between">
              <div>
                <p class="mb-0 fw-bold">글쓴이</p>
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
            <div class="review-score-button"><strong>청결이</strong> {{ reviewScore.cleannessScore }}</div>
            <div class="review-score-button"><strong>시설이</strong> {{ reviewScore.placeScore }}</div>
            <div class="review-score-button"><strong>위치가</strong> {{ reviewScore.locationScore }}</div>
            <div class="review-score-button"><strong>가격이</strong> {{ reviewScore.priceScore }}</div>
          </div>
        </div>
      </div>
      <div class="review-picture col-4">
        <img src="https://as1.ftcdn.net/v2/jpg/01/62/91/42/1000_F_162914286_O4BwMXsYDASlf49pe69tbUwgT9O7LqNa.jpg" class="img-fluid review-card-Image rounded" alt="..." />
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
    const reviewScore = reactive({
      cleannessScore: "",
      placeScore: "",
      locationScore: "",
      priceScore: "",
    });
    onMounted(async () => {
      if (reviewData.cleanness <= 1) {
        reviewScore.cleannessScore = "매우 별로에요";
      } else if (reviewData.cleanness <= 2) {
        reviewScore.cleannessScore = "별로에요";
      } else if (reviewData.cleanness <= 3) {
        reviewScore.cleannessScore = "보통이에요";
      } else if (reviewData.cleanness <= 4) {
        reviewScore.cleannessScore = "좋아요";
      } else {
        reviewScore.cleannessScore = "매우 좋아요";
      }

      if (reviewData.place <= 1) {
        reviewScore.placeScore = "매우 좋지 않아요";
      } else if (reviewData.place <= 2) {
        reviewData.placeScore = "좋지 않아요";
      } else if (reviewData.place <= 3) {
        reviewScore.placeScore = "보통이에요";
      } else if (reviewData.place <= 4) {
        reviewScore.placeScore = "좋아요";
      } else {
        reviewScore.placeScore = "매우 좋아요";
      }

      if (reviewData.location <= 1) {
        reviewScore.locationScore = "매우 좋지 않아요";
      } else if (reviewData.location <= 2) {
        reviewScore.locationScore = "좋지 않아요";
      } else if (reviewData.location <= 3) {
        reviewScore.locationScore = "보통이에요";
      } else if (reviewData.location <= 4) {
        reviewScore.locationScore = "좋아요";
      } else {
        reviewScore.locationScore = "매우 좋아요";
      }

      if (reviewData.price <= 1) {
        reviewScore.priceScore = "매우 비싸요";
      } else if (reviewData.price <= 2) {
        reviewScore.priceScore = "비싸요";
      } else if (reviewData.price <= 3) {
        reviewScore.priceScore = "보통이에요";
      } else if (reviewData.price <= 4) {
        reviewScore.priceScore = "저렴해요";
      } else {
        reviewScore.priceScore = "매우 저렴해요";
      }
    });

    return { reviewData, reviewScore };
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
  // padding: 0rem 2rem 0rem 0rem;
  margin: 0rem 2rem 0rem 0rem;
}
.review-card-Image {
  object-fit: cover;
  height: 80%;
}

.review-date {
  font-size: 0.8rem;
  color: rgb(49, 49, 49);
}
</style>
