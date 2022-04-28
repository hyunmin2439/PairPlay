<template>
  <div class="btn-group">
    <button type="button" class="btn btnPlace dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">Action</button>
    <ul class="dropdown-menu ps-5" style="width: 400px; height: auto; font-size: 14px; border-radius: 15px">
      <div class="d-flex">
        <div class="form-check">
          <input class="form-check-input" type="radio" name="Radios" id="Radios1" value="option1" checked />
          <label class="form-check-label" for="Radios1"> 축구 / 풋살</label>
        </div>
        <div class="form-check">
          <input class="form-check-input" type="radio" name="Radios" id="Radios2" value="option2" />
          <label class="form-check-label" for="Radios2"> 농 구 </label>
        </div>
      </div>
      <div class="d-flex">
        <div class="form-check">
          <input class="form-check-input" type="radio" name="Radios" id="Radios3" value="option3" checked />
          <label class="form-check-label" for="Radios3"> 야 구 </label>
        </div>
        <div class="form-check">
          <input class="form-check-input" type="radio" name="Radios" id="Radios4" value="option4" />
          <label class="form-check-label" for="Radios4"> 볼 링 </label>
        </div>
      </div>
      <div class="d-flex">
        <div class="form-check">
          <input class="form-check-input" type="radio" name="Radios" id="Radios5" value="option5" checked />
          <label class="form-check-label" for="Radios5"> 골 프 </label>
        </div>
        <div class="form-check">
          <input class="form-check-input" type="radio" name="Radios" id="Radios6" value="option6" />
          <label class="form-check-label" for="Radios6"> 테니스 </label>
        </div>
      </div>
      <div class="d-flex">
        <div class="form-check">
          <input class="form-check-input" type="radio" name="Radios" id="Radios7" value="option7" checked />
          <label class="form-check-label" for="Radios7"> 배드민턴</label>
        </div>
        <div class="form-check">
          <input class="form-check-input" type="radio" name="Radios" id="Radios8" value="option8" />
          <label class="form-check-label" for="Radios8"> 피트니스 </label>
        </div>
      </div>
      <div class="d-flex">
        <div class="form-check">
          <input class="form-check-input" type="radio" name="Radios" id="Radios9" value="option9" checked />
          <label class="form-check-label" for="Radios9"> 필라테스 </label>
        </div>
        <div class="form-check">
          <input class="form-check-input" type="radio" name="Radios" id="Radios10" value="option10" />
          <label class="form-check-label" for="Radios10"> 격투기 </label>
        </div>
      </div>
      <div class="d-flex">
        <div class="form-check">
          <input class="form-check-input" type="radio" name="Radios" id="Radios11" value="option11" checked />
          <label class="form-check-label" for="Radios11"> 수영 </label>
        </div>
        <div class="form-check">
          <input class="form-check-input" type="radio" name="Radios" id="Radios12" value="option12" />
          <label class="form-check-label" for="Radios12"> 클라이밍 </label>
        </div>
      </div>
      <!-- <div class="d-flex my-4">
        <div class="form-check col">
          <input class="form-check-input" type="radio" value="option1" id="Radios1" />
          <label class="form-check-label" for="Radios1"> 축구 / 풋살</label>
        </div>
        <div class="form-check col">
          <input class="form-check-input" type="radio" value="option2" id="Radios2" />
          <label class="form-check-label" for="Radios2"> 농 구 </label>
        </div>
      </div> -->

      <div class="d-flex">
        <button type="button" class="btn btn-outline-primary mb-4">적용</button>
        <button type="button" class="btn btn-outline-primary mb-4">취소</button>
      </div>
    </ul>
    <!--  -->
    <!-- 부산지역 -->
    <!-- <div class="btn-group">
      <button type="button" class="btn btnPlace dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">부산지역</button>
      <ul class="dropdown-menu" style="width: 250px; height: auto; border-radius: 15px">
        <div class="my-3 fw-bold fs-5 ps-4">부 산</div>
        <div class="d-flex my-4 ps-4">
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" />
            <label class="form-check-label" for="flexCheckChecked"> 중구 </label>
          </div>
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" />
            <label class="form-check-label" for="flexCheckChecked"> 서구 </label>
          </div>
        </div>
        <div class="d-flex my-4 ps-4">
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" />
            <label class="form-check-label" for="flexCheckDefault"> 동구 </label>
          </div>
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" />
            <label class="form-check-label" for="flexCheckChecked"> 영도구 </label>
          </div>
        </div>
        <div class="d-flex my-4 ps-4">
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" />
            <label class="form-check-label" for="flexCheckDefault"> 진구 </label>
          </div>
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" />
            <label class="form-check-label" for="flexCheckChecked"> 동래구 </label>
          </div>
        </div>
        <div class="d-flex my-4 ps-4">
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" />
            <label class="form-check-label" for="flexCheckDefault"> 남구 </label>
          </div>
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" />
            <label class="form-check-label" for="flexCheckChecked"> 북구 </label>
          </div>
        </div>
        <div class="d-flex my-4 ps-4">
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" />
            <label class="form-check-label" for="flexCheckDefault"> 해운대구 </label>
          </div>
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" />
            <label class="form-check-label" for="flexCheckChecked"> 사하구 </label>
          </div>
        </div>
        <div class="d-flex my-4 ps-4">
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" />
            <label class="form-check-label" for="flexCheckDefault"> 금정구 </label>
          </div>
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" />
            <label class="form-check-label" for="flexCheckChecked"> 강서구 </label>
          </div>
        </div>
        <div class="d-flex my-4 ps-4">
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" />
            <label class="form-check-label" for="flexCheckDefault"> 연제구 </label>
          </div>
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" />
            <label class="form-check-label" for="flexCheckChecked"> 수영구 </label>
          </div>
        </div>
        <div class="d-flex my-4 ps-4">
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" />
            <label class="form-check-label" for="flexCheckDefault"> 사상구 </label>
          </div>
          <div class="form-check col">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" />
            <label class="form-check-label" for="flexCheckChecked"> 기장군 </label>
          </div>
        </div>
        <hr />
        <div class="d-flex justify-content-around">
          <div type="button" class="btn btn-dark mb-2">적용</div>
          <div type="button" class="btn btn-outline-secondary mb-2">취소</div>
        </div>
      </ul>
    </div> -->
  </div>
</template>

<script>
export default {
  name: "PlaceSearchFilterEtc",
};
</script>

<style scoped>
.form-check-label {
  font-size: 14px;
}
</style>
