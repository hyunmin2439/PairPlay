<template>
  <div class="btn-group">
    <button type="button" class="btn btnPlace dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">운동 종목</button>
    <ul class="dropdown-menu py-2" style="min-width: 15vw; height: auto; font-size: 14px; border-radius: 15px">
      <div class="d-flex justify-content-start m-3 ms-4">
        <div class="form-check">
          <input class="form-check-input" type="checkbox" name="checkbox" id="checkbox1" value="축구" @click="inputCategory" />
          <label class="form-check-label" for="checkbox1"> 축 구 </label>
        </div>
        <div class="form-check ms-5">
          <input class="form-check-input" type="checkbox" name="checkbox2" id="checkbox2" value="풋살" @click="inputCategory" />
          <label class="form-check-label" for="Radios2"> 풋 살 </label>
        </div>
      </div>
      <div class="d-flex justify-content-start m-3 ms-4">
        <div class="form-check">
          <input class="form-check-input" type="checkbox" name="checkbox3" id="checkbox3" value="농구" @click="inputCategory" />
          <label class="form-check-label" for="checkbox3"> 농 구 </label>
        </div>
        <div class="form-check ms-5">
          <input class="form-check-input" type="checkbox" name="checkbox4" id="checkbox4" value="야구" @click="inputCategory" />
          <label class="form-check-label" for="checkbox4"> 야 구 </label>
        </div>
      </div>

      <div class="d-flex justify-content-start m-3 ms-4">
        <div class="form-check">
          <input class="form-check-input" type="checkbox" name="checkbox5" id="checkbox5" value="골프" @click="inputCategory" />
          <label class="form-check-label" for="checkbox5"> 골 프 </label>
        </div>
        <div class="form-check ms-5">
          <input class="form-check-input" type="checkbox" name="checkbox6" id="checkbox6" value="볼링" @click="inputCategory" />
          <label class="form-check-label" for="checkbox6"> 볼 링 </label>
        </div>
      </div>
      <div class="d-flex justify-content-start m-3 ms-4">
        <div class="form-check">
          <input class="form-check-input" type="checkbox" name="checkbox7" id="checkbox7" value="수영" @click="inputCategory" />
          <label class="form-check-label" for="checkbox7"> 수 영 </label>
        </div>
        <div class="form-check ms-5">
          <input class="form-check-input" type="checkbox" name="checkbox8" id="checkbox8" value="격투기" @click="inputCategory" />
          <label class="form-check-label" for="checkbox8"> 격투기 </label>
        </div>
      </div>
      <div class="d-flex justify-content-start m-3 ms-4">
        <div class="form-check">
          <input class="form-check-input" type="checkbox" name="checkbox9" id="checkbox9" value="배드민턴" @click="inputCategory" />
          <label class="form-check-label" for="checkbox9"> 배드민턴</label>
        </div>
        <div class="form-check ms-4">
          <input class="form-check-input" type="checkbox" name="checkbox10" id="checkbox10" value="테니스" @click="inputCategory" />
          <label class="form-check-label" for="checkbox10"> 테니스 </label>
        </div>
      </div>
      <div class="d-flex justify-content-start m-3 ms-4">
        <div class="form-check">
          <input class="form-check-input" type="checkbox" name="checkbox11" id="checkbox11" value="헬스" @click="inputCategory" />
          <label class="form-check-label" for="checkbox11"> 헬 스 </label>
        </div>
        <div class="form-check ms-4">
          <input class="form-check-input" type="checkbox" name="checkbox12" id="checkbox12" value="필라테스" @click="inputCategory" />
          <label class="form-check-label" for="checkbox12"> 필라테스 </label>
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
    let sportsCategoryData = ref([]);
    const checkingClicked = {
      축구: 0,
      풋살: 0,
      농구: 0,
      야구: 0,
      골프: 0,
      볼링: 0,
      수영: 0,
      격투기: 0,
      배드민턴: 0,
      테니스: 0,
      피트니스: 0,
      필라테스: 0,
    };
    const inputCategory = (res) => {
      if (checkingClicked[res.target.value]) {
        checkingClicked[res.target.value] = 0;
      } else {
        checkingClicked[res.target.value] = 1;
      }
      // console.log(checkingClicked, "확인용");
    };
    const submitSportsCategory = () => {
      let temp = ref([]);
      for (let i = 0; i < Object.keys(checkingClicked).length; i++) {
        if (checkingClicked[Object.keys(checkingClicked)[i]]) {
          temp.value.push(Object.keys(checkingClicked)[i]);
        }
      }
      sportsCategoryData = temp;
      emit("sportsCategoryData", sportsCategoryData.value);
    };

    return { sportsCategoryData, checkingClicked, inputCategory, submitSportsCategory };
  },
};
</script>

<style lang="scss" scoped>
.btn {
  &:hover {
    border: 0.02px solid rgba(1, 1, 1, 0.3);
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
