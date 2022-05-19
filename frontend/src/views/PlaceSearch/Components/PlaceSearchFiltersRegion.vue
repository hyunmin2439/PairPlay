<template>
  <div class="btn placeSearchFiltersRegion btnPlace">
    <div class="dropdown">
      <div class="dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">지역</div>
      <ul class="dropdown-menu mt-2" aria-labelledby="dropdownMenuButton">
        <!-- 서울시작 -->
        <li class="sido">
          <a class="dropdown-item d-flex justify-content-between px-4">
            <div>서울</div>
          </a>
          <ul class="dropdown-menu dropdown-submenu px-2 text-align" @click="selectSeoulGugun">
            <li class="d-flex justify-content-around text-start my-2">
              <input type="button" @click="selectSeoulGugun" class="dropdown-item ps-4" value="강남구" />
              <input type="button" class="dropdown-item" value="강동구" />
            </li>
            <li class="d-flex justify-content-around text-start my-2">
              <input type="button" class="dropdown-item ps-4" value="강북구" />
              <input type="button" class="dropdown-item" value="강서구" />
            </li>
            <li class="d-flex justify-content-around text-start my-2">
              <input type="button" class="dropdown-item ps-4" value="관악구" />
              <input type="button" class="dropdown-item" value="광진구" />
            </li>
            <li class="d-flex justify-content-around text-start my-2">
              <input type="button" class="dropdown-item ps-4" value="구로구" />
              <input type="button" class="dropdown-item" value="금천구" />
            </li>
            <li class="d-flex justify-content-around text-start my-2">
              <input type="button" class="dropdown-item ps-4" value="노원구" />
              <input type="button" class="dropdown-item" value="도봉구" />
            </li>
            <li class="d-flex justify-content-around text-start my-2">
              <input type="button" class="dropdown-item ps-4" value="동대문구" />
              <input type="button" class="dropdown-item" value="동작구" />
            </li>
            <li class="d-flex justify-content-around text-start my-2">
              <input type="button" class="dropdown-item ps-4" value="마포구" />
              <input type="button" class="dropdown-item" value="서대문구" />
            </li>
            <li class="d-flex justify-content-around text-start my-2">
              <input type="button" class="dropdown-item ps-4" value="서초구" />
              <input type="button" class="dropdown-item" value="성동구" />
            </li>
            <li class="d-flex justify-content-around text-start my-2">
              <input type="button" class="dropdown-item ps-4" value="성북구" />
              <input type="button" class="dropdown-item" value="송파구" />
            </li>
            <li class="d-flex justify-content-around text-start my-2">
              <input type="button" class="dropdown-item ps-4" value="양천구" />
              <input type="button" class="dropdown-item" value="영등포구" />
            </li>
            <li class="d-flex justify-content-around text-start my-2">
              <input type="button" class="dropdown-item ps-4" value="용산구" />
              <input type="button" class="dropdown-item" value="은평구" />
            </li>
            <li class="d-flex justify-content-around text-start my-2">
              <input type="button" class="dropdown-item ps-4" value="종로구" />
              <input type="button" class="dropdown-item" value="중구" />
            </li>
            <li class="d-flex justify-content-around text-start my-2">
              <input type="button" class="dropdown-item ps-4" value="중랑구" />
            </li>
          </ul>
        </li>
        <!-- 서울끝 -->
        <!-- 부산 처음-->
        <li class="sido">
          <a class="dropdown-item d-flex justify-content-between px-4">
            <div>부산</div>
          </a>
          <ul class="dropdown-menu dropdown-submenu px-2 text-align" @click="selectBusanGugun">
            <li class="d-flex justify-content-around text-start my-2">
              <input type="button" class="dropdown-item ps-4" value="중구" />
              <input type="button" class="dropdown-item" value="서구" />
            </li>
            <li class="d-flex justify-content-around text-start my-2">
              <input type="button" class="dropdown-item ps-4" value="동구" />
              <input type="button" class="dropdown-item" value="영도구" />
            </li>
            <li class="d-flex justify-content-around text-start my-2">
              <input type="button" class="dropdown-item ps-4" value="진구" />
              <input type="button" class="dropdown-item" value="동래구" />
            </li>
            <li class="d-flex justify-content-around text-start my-2">
              <input type="button" class="dropdown-item ps-4" value="남구" />
              <input type="button" class="dropdown-item" value="북구" />
            </li>
            <li class="d-flex justify-content-around text-start my-2">
              <input type="button" class="dropdown-item ps-4" value="사상구" />
              <input type="button" class="dropdown-item" value="사하구" />
            </li>

            <li class="d-flex justify-content-around text-start my-2">
              <input type="button" class="dropdown-item ps-4" value="금정구" />
              <input type="button" class="dropdown-item" value="강서구" />
            </li>
            <li class="d-flex justify-content-around text-start my-2">
              <input type="button" class="dropdown-item ps-4" value="연제구" />
              <input type="button" class="dropdown-item" value="수영구" />
            </li>
            <li class="d-flex justify-content-around text-start my-2">
              <input type="button" class="dropdown-item ps-4" value="해운대구" />
              <input type="button" class="dropdown-item" value="기장군" />
            </li>
          </ul>
        </li>
        <!-- 부산끝 -->
        <!-- <li class="sido">
          <a class="dropdown-item d-flex justify-content-between px-4"><span>서울</span> <span>&gt;</span></a>
          <ul class="dropdown-menu dropdown-submenu">
            <li><a class="dropdown-item">관악구</a></li>
            <li><a class="dropdown-item">서초구</a></li>
            <li>
              <a class="dropdown-item d-flex justify-content-between px-4"><span>부산</span> <span>&gt;</span></a>
              <ul class="dropdown-menu dropdown-submenu">
                <li><a class="dropdown-item">Multi level 1</a></li>
                <li><a class="dropdown-item">Multi level 2</a></li>
              </ul>
            </li>
            <li><a class="dropdown-item">Submenu item 4</a></li>
            <li><a class="dropdown-item">Submenu item 5</a></li>
          </ul>
        </li> -->
      </ul>
    </div>
  </div>
</template>

<script>
// console.log();

import { ref } from "vue";
// import {useRoute} from "vue-router"
// import {mapState, useStore} from "vuex"

export default {
  name: "PlaceSearchFiltersRegion",
  emits: ["regionData"],
  setup(props, { emit }) {
    const regionData = ref({ sido: "", gugun: "" });
    // const store = userStore();
    // const route = useRoute();
    const selectSeoulGugun = (res) => {
      // console.log(res, "res!!!!");
      regionData.value.sido = "서울";
      if (res.target.value == null) {
        // pass
      } else {
        regionData.value.gugun = res.target.value;
      }
      // var menus = document.getElementsByClassName("dropdown-menu");
      // for (var i = 0; i < menus.length; i++) {
      // menus[0].classList.remove("show");
      // menus[0].classList.add("show");

      // }
      emit("regionData", regionData.value);
    };
    const selectBusanGugun = (res) => {
      // console.log(res, "res!!!!");
      regionData.value.sido = "부산";
      if (res.target.value == null) {
        // pass
      } else {
        regionData.value.gugun = res.target.value;
      }
      emit("regionData", regionData.value);
    };

    return { regionData, selectSeoulGugun, selectBusanGugun };
  },
};
// let getDropdownItemsData = document.querySelectorAll(".dropdown-item");
// document.addEventListener("click", async function () {
//   await getDropdownItemsData.forEach(function (item) {
//     console.log(item.textContent);
//   });
// });
</script>

<style lang="scss">
.placeSearchFiltersRegion {
  // left: 500px;
}
.btnPlace {
  margin: 10px 5px 10px 5px;
  background: #ffff;
  border-radius: 5px;
  box-shadow: (0 1px 10px rgba(24, 24, 24, 0.04));
  // box-shadow: (0 0 8px rgba(24, 24, 24, 0.04));
  border: 1px solid rgba(1, 1, 1, 0.1);
  font-size: 14px;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}
// .btnPlace {
//   margin: 10px 20px 10px 20px;
//   background: #ffff;
//   border-radius: 30px;
//   /* box-shadow: (0 1px 10px rgba(24, 24, 24, 0.04)); */
//   box-shadow: 0 0 8px rgba(24, 24, 24, 0.04);
//   border: 1px solid rgba(1, 1, 1, 0.1);
//   font-size: 14px;
//   font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
// }
.dropdown-menu li {
  position: relative;
}
.dropdown-menu {
  // min-height: 350px;
  display: none;
  position: relative;
  left: 100%;
  box-shadow: 0 0 8px rgba(24, 24, 24, 0.1);

  border-top-left-radius: 5px;
  border-bottom-left-radius: 5px;
  border-top-right-radius: 0px;
  border-bottom-right-radius: 0px;
}
.dropdown-submenu {
  display: none;
  position: absolute;
  left: 100%;
  top: -9px;
  width: 250px;
  border-top-left-radius: 0px;
  border-bottom-left-radius: 0px;
  border-top-right-radius: 5px;
  border-bottom-right-radius: 5px;
}
.dropdown-menu .dropdown-submenu-left {
  right: 100%;
  left: auto;
  margin: 50px;
}
.dropdown-menu > li:hover {
  display: block;
  border-right: 1px solid rgba(1, 1, 1, 0.1);
}
.dropdown-menu > li:hover > .dropdown-submenu {
  display: block;
  border-left: 1px solid rgba(1, 1, 1, 0.1);
}
.dropdown-menu > li:hover > .dropdown-submenu::after > .dropdown-menu {
  display: none;
  // border-left: 1px solid rgba(1, 1, 1, 0.1);
}

// .dropdown-menu > li:hover > .dropdown-submenu:visited > .dropdown-menu {
//   display: none;
// }
// .dropdown-item {
// &:hover {
// box-shadow: 0 0 8px rgba(24, 24, 24, 0.1);
// }
// }
</style>
