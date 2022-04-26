<template>
  <!-- 카테고리 카드들 시작 -->
  <div class="catergory-card row row-cols-5">
    <!-- for문돌리면 됨 -->
    <div class="card text-white col">
      <img src="https://images.pexels.com/photos/2294361/pexels-photo-2294361.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940" class="card-img" alt="..." />
      <div class="card-img-overlay">
        <h5 class="card-title">Card title</h5>
      </div>
    </div>
    <div class="card text-white col">
      <img src="https://images.pexels.com/photos/2294361/pexels-photo-2294361.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940" class="card-img" alt="..." />
      <!-- <img src="https://as2.ftcdn.net/v2/jpg/02/88/72/69/1000_F_288726952_DTp7aFoemOB7AhGU66vz3YK7fijkqkMt.jpg" class="card-img" alt="..." /> -->
      <div class="card-img-overlay">
        <h5 class="card-title">Card title</h5>
      </div>
    </div>
    <div class="card text-white col">
      <img src="https://images.pexels.com/photos/2294361/pexels-photo-2294361.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940" class="card-img" alt="..." />
      <div class="card-img-overlay">
        <h5 class="card-title">Card title</h5>
      </div>
    </div>
    <div class="card text-white col">
      <img src="https://images.pexels.com/photos/2294361/pexels-photo-2294361.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940" class="card-img" alt="..." />
      <div class="card-img-overlay">
        <h5 class="card-title">Card title</h5>
      </div>
    </div>
  </div>
  <!-- 카테고리 카드들 끝 -->
</template>

<script>
export default {
  name: "PlaceCategory",
  setup() {
    // const store = userStore();
    // const route = useRoute();
  },
};
</script>

<style lang="scss" scoped>
.catergory-card {
  // gap: 0px;
  margin: auto;
}
.card {
  flex-grow: 1;
  margin: 10px 10px;
  padding: 0px;
  border-radius: 15px;
  // padding: 0px !important;
}
.card-img {
  border-radius: 15px;
}
/* .cards-img {
  margin: auto;
  object-fit: cover;
  width: 250px;
  height: 150px;
  border-radius: 15px;
} */
/* .cards-img {
  margin: auto;
  object-fit: cover;
  width: 250px;
  height: 150px;
  border-radius: 15px;
} */
</style>
