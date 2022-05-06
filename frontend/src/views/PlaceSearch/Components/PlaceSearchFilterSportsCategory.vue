<template>
  <div class="btn-group">
    <button type="button" class="btn btnPlace dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">운동 종목</button>
    <ul class="dropdown-menu py-2" style="min-width: 15vw; height: auto; font-size: 14px; border-radius: 15px">
      <div class="d-flex justify-content-start m-3 ms-4">
        <div class="form-check">
          <input class="form-check-input" type="radio" name="Radios" id="Radios1" value="축구" @click="inputCategory" />
          <label class="form-check-label" for="Radios1"> 축 구 </label>
        </div>
        <div class="form-check ms-5">
          <input class="form-check-input" type="radio" name="Radios2" id="Radios2" value="풋살" @click="inputCategory" />
          <label class="form-check-label" for="Radios2"> 풋 살 </label>
        </div>
      </div>
      <div class="d-flex justify-content-start m-3 ms-4">
        <div class="form-check">
          <input class="form-check-input" type="radio" name="Radios3" id="Radios3" value="농구" @click="inputCategory" />
          <label class="form-check-label" for="Radios3"> 농 구 </label>
        </div>
        <div class="form-check ms-5">
          <input class="form-check-input" type="radio" name="Radios4" id="Radios4" value="야구" @click="inputCategory" />
          <label class="form-check-label" for="Radios4"> 야 구 </label>
        </div>
      </div>

      <div class="d-flex justify-content-start m-3 ms-4">
        <div class="form-check">
          <input class="form-check-input" type="radio" name="Radios5" id="Radios5" value="골프" @click="inputCategory" />
          <label class="form-check-label" for="Radios5"> 골 프 </label>
        </div>
        <div class="form-check ms-5">
          <input class="form-check-input" type="radio" name="Radios6" id="Radios6" value="볼링" @click="inputCategory" />
          <label class="form-check-label" for="Radios6"> 볼 링 </label>
        </div>
      </div>
      <div class="d-flex justify-content-start m-3 ms-4">
        <div class="form-check">
          <input class="form-check-input" type="radio" name="Radios7" id="Radios7" value="수영" @click="inputCategory" />
          <label class="form-check-label" for="Radios7"> 수 영 </label>
        </div>
        <div class="form-check ms-5">
          <input class="form-check-input" type="radio" name="Radios8" id="Radios8" value="격투기" @click="inputCategory" />
          <label class="form-check-label" for="Radios8"> 격투기 </label>
        </div>
      </div>
      <div class="d-flex justify-content-start m-3 ms-4">
        <div class="form-check">
          <input class="form-check-input" type="radio" name="Radios9" id="Radios9" value="배드민턴" @click="inputCategory" />
          <label class="form-check-label" for="Radios9"> 배드민턴</label>
        </div>
        <div class="form-check ms-4">
          <input class="form-check-input" type="radio" name="Radios10" id="Radios10" value="테니스" @click="inputCategory" />
          <label class="form-check-label" for="Radios10"> 테니스 </label>
        </div>
      </div>
      <div class="d-flex justify-content-start m-3 ms-4">
        <div class="form-check">
          <input class="form-check-input" type="radio" name="Radios11" id="Radios11" value="피트니스" @click="inputCategory" />
          <label class="form-check-label" for="Radios11"> 피트니스 </label>
        </div>
        <div class="form-check ms-4">
          <input class="form-check-input" type="radio" name="Radios12" id="Radios12" value="필라테스" @click="inputCategory" />
          <label class="form-check-label" for="Radios12"> 필라테스 </label>
        </div>
      </div>
      <hr style="border: 1px solid #fafafa" />
      <div class="d-flex justify-content-center mt-1">
        <button type="button" class="btn btn-dark mb-4" @click="submitSportsCategory">적용</button>
        <button type="button" class="btn btn-outline-secondary mb-4 ms-5">취소</button>
      </div>
    </ul>
  </div>
</template>

<script>
import { ref } from "vue";

export default {
  name: "PlaceSearchFilterSportsCategory",
  emits: ["sportsCategoryData"],
  setup(_, { emit }) {
    const sportsCategoryData = ref({});
    const inputCategory = (res) => {
      // console.log(res.target.value, "뭔데");
      sportsCategoryData.value = res.target.value;
      // res.
    };
    const submitSportsCategory = () => {
      // sportsCategoryData;
      // console.log(res);
      // let sportsCategoryData = sportsCategoryData;
      // return sportsCategoryData.value;
      // sportsCategoryData.toString().charAt();
      emit("sportsCategoryData", sportsCategoryData.value);
    };
    return { sportsCategoryData, inputCategory, submitSportsCategory };
  },
};
</script>

<style lang="scss" scoped>
.btn {
  &:hover {
    border: 0.5px solid black;
  }
}
.form-check-label {
  font-size: 14px;
}
.btnPlace {
  margin: 10px 5px 10px 10px;
  background: #ffff;
  border-radius: 5px;
  /* box-shadow: 0 1px 10px rgba(24, 24, 24, 0.04); */
  box-shadow: 0 0 8px rgba(24, 24, 24, 0.04);
  border: 1px solid rgba(1, 1, 1, 0.1);
  font-size: 14px;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}
</style>
