<template>
  <!-- 카테고리 카드들 시작 -->
  <div class="category-card row row-cols-5">
    <!-- for문돌리면 됨 -->
    <div class="card text-white col" @click="selectCategory" type="button">
      <img src="https://as1.ftcdn.net/v2/jpg/03/29/57/64/1000_F_329576400_OxTGJEObT6VwF9uVQQZTKAJYKBT4tfe8.jpg" class="card-img" alt="..." />
      <div class="card-img-overlay">
        <div class="card-img-cover d-flex"><p class="card-title fs-3 fw-bold align-self-end pb-3 ps-3">축 구</p></div>
      </div>
    </div>
    <div class="card text-white col" @click="selectCategory" type="button">
      <img src="https://t4.ftcdn.net/jpg/02/17/33/89/240_F_217338920_7ecAmCLjodnCE1y1usoaS2VOdtk0JyOH.jpg" class="card-img" alt="..." />
      <div class="card-img-overlay">
        <div class="card-img-cover d-flex"><p class="card-title fs-3 fw-bold align-self-end pb-3 ps-3">풋 살</p></div>
      </div>
    </div>
    <div class="card text-white col" @click="selectCategory" type="button">
      <img src="https://as2.ftcdn.net/v2/jpg/02/88/72/69/1000_F_288726952_DTp7aFoemOB7AhGU66vz3YK7fijkqkMt.jpg" class="card-img" alt="..." />
      <div class="card-img-overlay">
        <div class="card-img-cover d-flex"><p class="card-title fs-3 fw-bold align-self-end pb-3 ps-3">농 구</p></div>
      </div>
    </div>
    <div class="card text-white col" @click="selectCategory" type="button">
      <img src="https://as2.ftcdn.net/v2/jpg/00/20/11/31/1000_F_20113163_MK1nYy5BQxMJgfcZRUZ7ELYXz8LONesZ.jpg" class="card-img" alt="..." />
      <div class="card-img-overlay">
        <div class="card-img-cover d-flex"><p class="card-title fs-3 fw-bold align-self-end pb-3 ps-3">야 구</p></div>
      </div>
    </div>
    <div class="card text-white col" @click="selectCategory" type="button">
      <img src="https://as1.ftcdn.net/v2/jpg/02/29/59/36/1000_F_229593674_Z30C6EK09hwAfVBQzimiTnnDz9sQIuuh.jpg" class="card-img" alt="..." />
      <div class="card-img-overlay">
        <div class="card-img-cover d-flex"><p class="card-title fs-3 fw-bold align-self-end pb-3 ps-3">볼 링</p></div>
      </div>
    </div>
    <div class="card text-white col" @click="selectCategory" type="button">
      <img src="https://as1.ftcdn.net/v2/jpg/03/57/28/68/1000_F_357286882_Jwnj6ZKr4T6QQaNyZ8iLl1rqpdd80LYA.jpg" class="card-img" alt="..." />
      <div class="card-img-overlay">
        <div class="card-img-cover d-flex"><p class="card-title fs-3 fw-bold align-self-end pb-3 ps-3">골 프</p></div>
      </div>
    </div>
    <div class="card text-white col" @click="selectCategory" type="button">
      <img src="https://as2.ftcdn.net/v2/jpg/01/29/83/71/1000_F_129837102_nLBJWwcRUk0TdeQV1AXVXqI7ysQuwkDZ.jpg" class="card-img" alt="..." />
      <div class="card-img-overlay">
        <div class="card-img-cover d-flex"><p class="card-title fs-3 fw-bold align-self-end pb-3 ps-3">테니스</p></div>
      </div>
    </div>
    <div class="card text-white col" @click="selectCategory" type="button">
      <img src="https://as1.ftcdn.net/v2/jpg/01/92/33/96/1000_F_192339682_X4MeUW7x0tp3J4tpczXrb5PnwjRSQTdo.jpg" class="card-img" alt="..." />
      <div class="card-img-overlay">
        <div class="card-img-cover d-flex"><p class="card-title fs-3 fw-bold align-self-end pb-3 ps-3">배드민턴</p></div>
      </div>
    </div>
    <div class="card text-white col" @click="selectCategory" type="button">
      <img src="https://as1.ftcdn.net/v2/jpg/03/20/45/94/1000_F_320459487_SAvrvfR4NN4CkFQccj49rBfmiLoYebuw.jpg" class="card-img" alt="..." />
      <div class="card-img-overlay">
        <div class="card-img-cover d-flex"><p class="card-title fs-3 fw-bold align-self-end pb-3 ps-3">헬 스</p></div>
      </div>
    </div>
    <div class="card text-white col" @click="selectCategory" type="button">
      <img src="https://t4.ftcdn.net/jpg/01/77/90/87/240_F_177908722_eCReNykIZnmK1WFgQkGnuiplFsfoXsW2.jpg" class="card-img" alt="..." />
      <div class="card-img-overlay">
        <div class="card-img-cover d-flex"><p class="card-title fs-3 fw-bold align-self-end pb-3 ps-3">필라테스</p></div>
      </div>
    </div>
    <div class="card text-white col" @click="selectCategory" type="button">
      <img src="https://t3.ftcdn.net/jpg/02/69/05/88/240_F_269058866_TVWB1nAt44RbmIwrU4jrCLzPvSDhFytY.jpg" class="card-img" alt="..." />
      <div class="card-img-overlay">
        <div class="card-img-cover d-flex"><p class="card-title fs-3 fw-bold align-self-end pb-3 ps-3">격투기</p></div>
      </div>
    </div>
    <div class="card text-white col" @click="selectCategory" type="button">
      <img src="https://t4.ftcdn.net/jpg/01/21/79/67/240_F_121796713_1HXzWNcoAb9gB73FVol689PuO541S7tJ.jpg" class="card-img" alt="..." />
      <div class="card-img-overlay">
        <div class="card-img-cover d-flex"><p class="card-title fs-3 fw-bold align-self-end pb-3 ps-3">수 영</p></div>
      </div>
    </div>
  </div>
  <!-- 카테고리 카드들 끝 -->
</template>

<script>
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { ref } from "vue";

export default {
  name: "PlaceCategory",
  setup() {
    const router = useRouter();
    const store = useStore();
    let categoryList = ref({});
    // const store = userStore();
    // const route = useRoute();
    const selectCategory = async (res) => {
      // console.log(res.target.innerText, "텍스트 변화전");
      var text = res.target.innerText;
      text = text.replace(/\s/gi, "");
      categoryList.value = [text];
      // console.log(categoryList, "텍스트 변화후");
      await store.dispatch("root/selectSportsCategory", categoryList.value);
      await router.push({ name: "PlaceSearch" }).then(() => window.scrollTo(0, 0));
    };
    return {
      categoryList,
      selectCategory,
    };
  },
};
</script>

<style lang="scss" scoped>
.category-card {
  // gap: 0px;
  margin: auto;
}
.card {
  flex-grow: 1;
  margin: 10px 10px;
  padding: 0px;
  height: 150px;
  border-radius: 10px;
  transform: scale(1);
  &:hover {
    transform: scale(1.01);
  }
  // padding: 0px !important;
}
.card-img {
  // width: 100%;
  height: 100%;
  border-radius: 10px;
  object-fit: cover;
}

.card-img-cover {
  position: absolute;
  top: 0%;
  left: 0%;
  width: 100%;
  height: 100%;
  border-radius: 10px;

  // background-color: rgba(0, 0, 0, 0.3);
  background: linear-gradient(20deg, rgba(0, 0, 0, 0.6) 0%, rgba(0, 0, 0, 0) 100%);
  &:hover {
    box-shadow: 1px solid black;
    // border: 1px solid black;
    // border-radius: 4px;
    border-radius: 10px;
    background: linear-gradient(20deg, rgba(0, 0, 0, 0.4) 0%, rgba(0, 0, 0, 0) 100%);
  }
  // z-index: 1;
  // color: white;
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
