<template>
  <!-- 카테고리 카드들 시작 -->
  <div class="row row-cols-1 row-cols-md-5 g-4">
    <div class="col">
      <div class="card">
        <img src="https://as2.ftcdn.net/v2/jpg/02/97/52/81/1000_F_297528198_uu6DjM2ZqSp5y0nXMeo2WsUFOhlyeHeO.jpg" class="card-img" alt="" />
        <div class="card-body">
          <p class="card-placeRegion">부산 해운대구</p>
          <p class="card-title fw-bold">팀터틀랫 해운대장산점</p>
          <p class="card-text">부산 해운대구 card with supporting text .</p>
        </div>
      </div>
    </div>
    <div class="col">
      <div class="card">
        <img src="https://t4.ftcdn.net/jpg/00/00/53/99/240_F_539921_WqMApqrN2uQabcHwxTd9fYCM1XhiEt.jpg" class="card-img" alt="" />
        <div class="card-body">
          <p class="card-placeRegion">부산 연제구</p>
          <p class="card-title fw-bold">제주유나이티드클럽하우스축구장</p>
          <p class="card-text">부산 연제구 card with supporting text .</p>
        </div>
      </div>
    </div>
    <div class="col">
      <div class="card">
        <img src="https://t4.ftcdn.net/jpg/03/08/54/91/240_F_308549122_oW7HcGySCq73g4yXKG1QGVAEGkgHJcAx.jpg" class="card-img" alt="" />
        <div class="card-body">
          <p class="card-placeRegion">부산 금정구</p>
          <p class="card-title fw-bold">클린샷 농구교실</p>
          <p class="card-text">부산 연제구 card with supporting text .</p>
        </div>
      </div>
    </div>
    <div class="col">
      <div class="card">
        <img src="https://t4.ftcdn.net/jpg/01/31/59/21/240_F_131592129_eTgY2jmvtWt1uXBVV3o6Khvg1ZJERltv.jpg" class="card-img" alt="" />
        <div class="card-body">
          <p class="card-placeRegion">부산 진구</p>
          <p class="card-title fw-bold">부산대학교 부산캠퍼스 농구장</p>
          <p class="card-text">부산 연제구 card with supporting text .</p>
        </div>
      </div>
    </div>
    <div class="col">
      <div class="card">
        <img src="https://t4.ftcdn.net/jpg/04/97/42/67/240_F_497426715_JxTIazdz2t2aXeTi1fRFaJIlzTIOatbv.jpg" class="card-img" alt="" />
        <div class="card-body">
          <p class="card-placeRegion">부산 수영구</p>
          <p class="card-title fw-bold">저스트 플레이짐</p>
          <p class="card-text">부산 연제구 card with supporting text .</p>
        </div>
      </div>
    </div>
  </div>
  <!-- 카테고리 카드들 끝 -->
</template>

<script>
export default {
  name: "PlaceRecommend",

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
  // padding: 0px !important;
  border: 0px;
  height: 400px;
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

  background-color: rgba(0, 0, 0, 0.2);
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
  // line-height: 2rem;
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
