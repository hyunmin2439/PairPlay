<template>
  <div class="btn placeSearchFiltersRegion btnPlace">
    <div class="dropdown">
      <div class="dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">지역</div>
      <ul class="dropdown-menu mt-2" aria-labelledby="dropdownMenuButton">
        <li>
          <a class="dropdown-item d-flex justify-content-between px-4" href="#"><span>부산</span><span>></span></a>
          <ul class="dropdown-menu dropdown-submenu px-2 text-align">
            <li class="d-flex justify-content-around text-start my-2">
              <a class="dropdown-item ps-4" href="#">중구</a>
              <a class="dropdown-item" href="#">서구</a>
            </li>
            <!-- <a class="dropdown-item" href="#">중구</a>
              <a class="dropdown-item" href="#">서구</a> -->

            <li class="d-flex justify-content-around text-start my-2">
              <a class="dropdown-item ps-4" href="#">동구</a>
              <a class="dropdown-item" href="#">영도구</a>
            </li>
            <li class="d-flex justify-content-around text-start my-2">
              <a class="dropdown-item ps-4" href="#">진구</a>
              <a class="dropdown-item" href="#">동래구</a>
            </li>
            <li class="d-flex justify-content-around text-start my-2">
              <a class="dropdown-item ps-4" href="#">남구</a>
              <a class="dropdown-item" href="#">북구</a>
            </li>
            <li class="d-flex justify-content-around text-start my-2">
              <a class="dropdown-item ps-4" href="#">사상구</a>
              <a class="dropdown-item" href="#">사하구</a>
            </li>

            <li class="d-flex justify-content-around text-start my-2">
              <a class="dropdown-item ps-4" href="#">금정구</a>
              <a class="dropdown-item" href="#">강서구</a>
            </li>
            <li class="d-flex justify-content-around text-start my-2">
              <a class="dropdown-item ps-4" href="#">연제구</a>
              <a class="dropdown-item" href="#">수영구</a>
            </li>
            <li class="d-flex justify-content-around text-start my-2">
              <a class="dropdown-item ps-4" href="#">기장군</a>
              <a class="dropdown-item" href="#">해운대구</a>
            </li>
            <li class="d-flex"></li>
          </ul>
        </li>
        <li>
          <a class="dropdown-item d-flex justify-content-between px-4" href="#"><span>부산2</span> <span>&gt;</span></a>
          <ul class="dropdown-menu dropdown-submenu">
            <li><a class="dropdown-item" href="#">Submenu item 1</a></li>
            <li><a class="dropdown-item" href="#">Submenu item 2</a></li>
            <li>
              <a class="dropdown-item d-flex justify-content-between px-4" href="#"><span>부산</span> <span>&gt;</span></a>
              <ul class="dropdown-menu dropdown-submenu">
                <li><a class="dropdown-item" href="#">Multi level 1</a></li>
                <li><a class="dropdown-item" href="#">Multi level 2</a></li>
              </ul>
            </li>
            <li><a class="dropdown-item" href="#">Submenu item 4</a></li>
            <li><a class="dropdown-item" href="#">Submenu item 5</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
// import {ref} from vue
// import {useRoute} from "vue-router"
// import {mapState, useStore} from "vuex"

export default {
  name: "PlaceSearchFiltersRegion",
  // setup() {
  // const store = userStore();
  // const route = useRoute();
  // },
};
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
  height: 350px;
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
// .dropdown-item {
// &:hover {
// box-shadow: 0 0 8px rgba(24, 24, 24, 0.1);
// }
// }
</style>
