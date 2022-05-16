<template>
  <div>
    <!-- <div>
      <Header></Header>
    </div> -->
    <div style="max-width: 1255px; margin: auto;">
      <div class="PlaceBackgroundImage">
        <div class="PlaceBackgroundImageCover">
          <div class="PlaceBackground-Content ms-5 ps-5">
            <p class="ps-5 ms-4">
              메이트 등록
            </p>
          </div>
        </div>
      </div>
    </div>
    <div class="container">
      <div class="d-flex row">
        <!-- <div class="row"> -->
        <label class="col-2">주소</label>
        <p class="col-1">옵션1</p>
        <p class="col-1">옵션2</p>
        <label class="offset-2 col-2">성별 및 연령</label>
        <p class="col-1">남여</p>
        <p class="col-1">나이</p>
        <!-- </div> -->
        <hr/>
        <label class="col-2">운동 종류</label>
        <label class="col-10"></label>
        <button class="btn col-1">축구</button>
        <button class="btn col-1">풋살</button>
        <button class="btn col-1">농구</button>
        <button class="btn col-1">야구</button>
        <button class="btn col-1">볼링</button>
        <button class="btn col-1">골프</button>
        <button class="btn col-1">테니스</button>
        <button class="btn col-1">배드민턴</button>
        <button class="btn col-1">피트니스</button>
        <button class="btn col-1">필라테스</button>
        <button class="btn col-1">격투기</button>
        <button class="btn col-1">수영</button>
        <p class="col-12"></p>
        <hr>
        <label class="col-2">요일 및 시간</label>
        <hr>
        <label for="">구직서 제목</label>
        <input type="text">
      </div>
    </div>
  </div>
</template>

<script>
// import Header from "../Common/Header.vue";


export default {
  name: 'MateCreate',
  setup() {
    
  }
}
</script>

<style>
  .PlaceBackgroundImage {
    width: 100%;
    height: 330px;
    background: black;
    margin-bottom: 5rem;
    /* background-color: rgba(0, 0, 0, 0.4); */
    background: url("https://images.pexels.com/photos/3601094/pexels-photo-3601094.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260") 50% 40% no-repeat;
    background-size: 100% auto;
  }
  .PlaceBackgroundImageCover {
    position: absolute;
    width: 1255px;
    height: 330px;
    background: linear-gradient(30deg, rgba(0, 0, 0, 0.7) 0%, rgba(0, 0, 0, 0) 100%);
    /* background-color: rgba(0, 0, 0, 0.3); */
    z-index: 1;
    color: white;
  }
  .PlaceBackground-Content {
    position: absolute;
    top: 40%;
    /* max-width: 968px; */
    font-size: calc(1em + 2vw);
    /* transform: translate(-50%, -50%); */
    z-index: 2;
    text-align: left;
    font-weight: bold;
    font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
  }
  .container {
    /* width: 100%;
    height: 100%;
    display: flex; */
    flex-wrap: wrap;
    align-items: center;
    justify-content: center;
    text-align: center;
    /* align-items: left; */
    /* justify-content: left; */
    /* margin-left: 0; */
    /* margin-right: 0; */
  }

  .btn {
    margin-top: 1rem;
    border: 1px solid rgb(186, 186, 186);
    background-color: rgb(209, 255, 255);
    /* color:aqua */
    /* width: 5.5vw */
  }
</style>