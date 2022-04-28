<template>
  <!-- 카테고리 카드들 시작 -->
  <div class="row row-cols-1 row-cols-md-5 g-4">
    <div class="col">
      <div class="card">
        <img src="https://t3.ftcdn.net/jpg/03/29/60/84/240_F_329608479_vP9nFK795X8lWmoTa8DPhMgoewQ7U1lG.jpg" class="card-img" alt="" />
        <div class="card-body">
          <p class="card-placeRegion">This is a longer</p>
          <p class="card-title fw-bold">Card title</p>
          <p class="card-text">This is a longer card with supporting text .</p>
        </div>
      </div>
    </div>
    <div class="col">
      <div class="card">
        <img src="https://t3.ftcdn.net/jpg/01/08/99/20/240_F_108992094_SZ5WSERYBUzfI2uookwvFQdFc6DSt9IB.jpg" class="card-img" alt="" />
        <div class="card-body">
          <p class="card-placeRegion">This is a longer</p>
          <p class="card-title fw-bold">Card title</p>
          <p class="card-text">This is a longer card with supporting text .</p>
        </div>
      </div>
    </div>
    <div class="col">
      <div class="card">
        <img src="https://t4.ftcdn.net/jpg/02/67/46/51/240_F_267465121_hbjzEm2kJc9yowdM1TqCwdgNYZA76fse.jpg" class="card-img" alt="" />
        <div class="card-body">
          <p class="card-placeRegion">This is a longer</p>
          <p class="card-title fw-bold">Card title</p>
          <p class="card-text">This is a longer card with supporting text .</p>
        </div>
      </div>
    </div>
    <div class="col">
      <div class="card">
        <img src="https://t4.ftcdn.net/jpg/01/71/23/49/240_F_171234990_cSNErNz2LkXTP7YxMNzRY3jDhz0laMa2.jpg" class="card-img" alt="" />
        <div class="card-body">
          <p class="card-placeRegion">This is a longer</p>
          <p class="card-title fw-bold">Card title</p>
          <p class="card-text">
            This is a longer card with supporting text .This is a longer card with supporting text .This is a longer card with supporting text .This is a longer card with supporting text .This is a
            longer card with supporting text . This is a longer card with supporting text .This is a longer card with supporting text .This is a longer card with supporting text .This is a longer card
            with supporting text . This is a longer card with supporting text .This is a longer card with supporting text .This is a longer card with supporting text . This is a longer card with
            supporting text .This is a longer card with supporting text .
          </p>
        </div>
      </div>
    </div>
    <div class="col">
      <div class="card">
        <img src="https://as1.ftcdn.net/v2/jpg/03/03/44/82/1000_F_303448274_0N7iYpZ7B8Er3z7iDRa70zhxihGfjpNZ.jpg" class="card-img" alt="" />
        <div class="card-body">
          <p class="card-placeRegion">This is a longer</p>
          <p class="card-title fw-bold">Card title</p>
          <p class="card-text">This is a longer card with supporting text .</p>
        </div>
      </div>
    </div>
  </div>
  <!-- 카테고리 카드들 끝 -->
</template>

<script>
export default {
  name: "PlaceRecentView",

  setup() {
    // const store = userStore();
    // const route = useRoute();
  },
};
</script>

<style lang="scss" scoped>
.card {
  // box-shadow: (0 1px 10px rgba(24, 24, 24, 0.07));
  // box-shadow: (0 0 8px rgba(24, 24, 24, 0.04));
  flex-grow: 1;
  // margin: 10px 10px;
  padding: 0px;
  // height: 350px;
  border-radius: 10px;
  height: 400px;
  // padding: 0px !important;
  border: 0px;

  // white-space: nowrap;
}
.card-img {
  // width: 100%;
  // height: 100%;
  height: 250px;
  // max-height: 350px;
  border-radius: 10px;
  object-fit: cover;
}
.card-img-cover {
  position: absolute;
  top: 0%;
  left: 0%;
  // width: 100%;
  height: 100%;
  border-radius: 10px;
  // border:0px;
  background-color: rgba(0, 0, 0, 0.3);
  // z-index: 1;
  // color: white;
}
.card-body {
  padding: 10px;
  max-height: 150px;
}
.card-placeRegion {
  margin: 0 0 0px 0;
  font-weight: bold;
  color: #767676;
  font-size: 11px;
}
.card-title {
  font-weight: bold;
  font-size: 20px;
  // 두줄 이상이면 말줄임표
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}
.card-text {
  font-size: 14px;
  // 두줄 이상이면 말줄임표
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  // font-weight: 500;
}
/* .cards-img {
  margin: auto;
  object-fit: cover;
  width: 250px;
  height: 150px;
  border-radius: 15px;
} */
</style>
