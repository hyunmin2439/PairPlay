<template>
  <div class="dropwdonwTotal">
    <div class="dropdown dropdownsmenus">
      <button class="dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">Dropdown button</button>
      <ul class="dropdown-menu mt-2" aria-labelledby="dropdownMenuButton">
        <li>
          <a class="dropdown-item d-flex justify-content-between px-4" href="#"><span>부산</span> <span>></span></a>

          <ul class="dropdown-menu dropdown-submenu px-2 text-align">
            <li class="d-flex justify-content-around text-start">
              <div class="form-check">
                <input class="form-check-input" type="checkbox" value="" id="flexCheckIndeterminate" />
                <label class="form-check-label" for="flexCheckIndeterminate"> 중구 </label>
              </div>
              <div class="form-check">
                <input class="form-check-input" type="checkbox" value="" id="flexCheckIndeterminate" />
                <label class="form-check-label" for="flexCheckIndeterminate"> 서구 </label>
              </div>
              <!-- <a class="dropdown-item" href="#">중구</a>
              <a class="dropdown-item" href="#">서구</a> -->
            </li>
            <li class="d-flex justify-content-around text-start">
              <a class="dropdown-item" href="#">동구</a>
              <a class="dropdown-item" href="#">영도구</a>
            </li>

            <li class="d-flex justify-content-around text-start">
              <a class="dropdown-item" href="#">진구</a>
              <a class="dropdown-item" href="#">동래구</a>
            </li>
            <li class="d-flex justify-content-around text-start">
              <a class="dropdown-item" href="#">남구</a>
              <a class="dropdown-item" href="#">북구구</a>
            </li>
            <li class="d-flex justify-content-around text-start">
              <a class="dropdown-item" href="#">해운대구</a>
              <a class="dropdown-item" href="#">사하구</a>
            </li>

            <li class="d-flex justify-content-around text-start">
              <a class="dropdown-item" href="#">금정구</a>
              <a class="dropdown-item" href="#">강서구</a>
            </li>
            <li class="d-flex justify-content-around text-start">
              <a class="dropdown-item" href="#">연제구</a>
              <a class="dropdown-item" href="#">연제구</a>
            </li>
            <li class="d-flex justify-content-around text-start">
              <a class="dropdown-item" href="#">수영구</a>
              <a class="dropdown-item" href="#">사상구</a>
            </li>
            <li class="d-flex"><a class="dropdown-item" href="#">기장군</a></li>
            <li><hr class="dropdown-divider" /></li>
            <div>
              <button>아아</button>
              <button>아아</button>
            </div>
          </ul>
        </li>
        <li>
          <a class="dropdown-item d-flex justify-content-between px-4" href="#"><span>부산2</span> <span>&gt;</span></a>
          <ul class="dropdown-menu dropdown-submenu">
            <li>
              <a class="dropdown-item" href="#">Submenu item 1</a>
            </li>
            <li>
              <a class="dropdown-item" href="#">Submenu item 2</a>
            </li>
            <li>
              <a class="dropdown-item d-flex justify-content-between px-4" href="#"><span>부산</span> <span>&gt;</span></a>
              <ul class="dropdown-menu dropdown-submenu">
                <li>
                  <a class="dropdown-item" href="#">Multi level 1</a>
                </li>
                <li>
                  <a class="dropdown-item" href="#">Multi level 2</a>
                </li>
              </ul>
            </li>
            <li>
              <a class="dropdown-item" href="#">Submenu item 4</a>
            </li>
            <li>
              <a class="dropdown-item" href="#">Submenu item 5</a>
            </li>
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
  name: "PlaceDetail",
  // setup() {
  // const store = userStore();
  // const route = useRoute();
  // },
};
</script>

<style>
.dropwdonwTotal {
  width: 450px;
  height: 550px;
}

.dropdown-menu li {
  position: relative;
}
.dropdown-menu {
  height: 400px;
  display: none;
  position: absolute;
  left: 100%;

  border-top-left-radius: 10px;
  border-bottom-left-radius: 10px;
}
.dropdown-submenu {
  display: none;
  position: absolute;
  left: 100%;
  top: -30%;
  border-top-left-radius: 0px;
  border-bottom-left-radius: 0px;
  border-top-right-radius: 10px;
  border-bottom-right-radius: 10px;
}
.dropdown-menu .dropdown-submenu-left {
  right: 100%;
  left: auto;
  margin: 50px;
}
.dropdown-menu > li:hover > .dropdown-submenu {
  display: block;
}
</style>
