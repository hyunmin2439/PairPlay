<template>
  <div class="btn-group">
    <button type="button" class="btn btnPlace dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">운동 종목</button>
    <ul class="dropdown-menu py-2" style="min-width: 15vw; height: auto; font-size: 14px; border-radius: 15px">
      <div class="d-flex justify-content-start m-3 ms-4">
        <div class="form-check">
          <input class="form-check-input" type="radio" name="Radios" id="Radios1" value="option1" checked />
          <label class="form-check-label" for="Radios1"> 축 구 </label>
        </div>
        <div class="form-check ms-5">
          <input class="form-check-input" type="radio" name="Radios" id="Radios12" value="option12" />
          <label class="form-check-label" for="Radios12"> 풋 살 </label>
        </div>
      </div>
      <div class="d-flex justify-content-start m-3 ms-4">
        <div class="form-check">
          <input class="form-check-input" type="radio" name="Radios" id="Radios2" value="option2" />
          <label class="form-check-label" for="Radios2"> 농 구 </label>
        </div>
        <div class="form-check ms-5">
          <input class="form-check-input" type="radio" name="Radios" id="Radios1" value="option1" checked />
          <label class="form-check-label" for="Radios1"> 야 구 </label>
        </div>
      </div>

      <div class="d-flex justify-content-start m-3 ms-4">
        <div class="form-check">
          <input class="form-check-input" type="radio" name="Radios" id="Radios5" value="option5" checked />
          <label class="form-check-label" for="Radios5"> 골 프 </label>
        </div>
        <div class="form-check ms-5">
          <input class="form-check-input" type="radio" name="Radios" id="Radios2" value="option2" />
          <label class="form-check-label" for="Radios2"> 볼 링 </label>
        </div>
      </div>
      <div class="d-flex justify-content-start m-3 ms-4">
        <div class="form-check">
          <input class="form-check-input" type="radio" name="Radios" id="Radios11" value="option11" checked />
          <label class="form-check-label" for="Radios11"> 수 영 </label>
        </div>
        <div class="form-check ms-5">
          <input class="form-check-input" type="radio" name="Radios" id="Radios10" value="option10" />
          <label class="form-check-label" for="Radios10"> 격투기 </label>
        </div>
      </div>
      <div class="d-flex justify-content-start m-3 ms-4">
        <div class="form-check">
          <input class="form-check-input" type="radio" name="Radios" id="Radios7" value="option7" checked />
          <label class="form-check-label" for="Radios7"> 배드민턴</label>
        </div>
        <div class="form-check ms-4">
          <input class="form-check-input" type="radio" name="Radios" id="Radios6" value="option6" />
          <label class="form-check-label" for="Radios6"> 테니스 </label>
        </div>
      </div>
      <div class="d-flex justify-content-start m-3 ms-4">
        <div class="form-check">
          <input class="form-check-input" type="radio" name="Radios" id="Radios8" value="option8" />
          <label class="form-check-label" for="Radios8"> 피트니스 </label>
        </div>
        <div class="form-check ms-4">
          <input class="form-check-input" type="radio" name="Radios" id="Radios9" value="option9" checked />
          <label class="form-check-label" for="Radios9"> 필라테스 </label>
        </div>
      </div>
      <hr style="border: 1px solid #fafafa" />
      <div class="d-flex justify-content-center mt-1">
        <button type="button" class="btn btn-dark mb-4">적용</button>
        <button type="button" class="btn btn-outline-secondary mb-4 ms-5">취소</button>
      </div>
    </ul>
  </div>
</template>

<script>
export default {
  name: "PlaceSearchFilterSportsCategory",
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
