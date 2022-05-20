<template>
  <div>
    <naver-maps class="naverMaps" style="width: 100%; height: 100vh; position: sticky" :mapOptions="mapOptions" :initLayers="initLayers" @onLoad="onLoadMap($event)" @click="popUP($event)">
      <naver-marker v-for="card in cards" :key="card.id" :latitude="card.latitude" :longitude="card.longitude" @click="popUP(card.id)">
        <!-- @click="moveToDetail(card.id)" -->
        <div class="" v-if="card">
          <div class="d-flex marker align-items-center">
            <i class="bi bi-geo-alt-fill"></i>
            <p class="my-0">{{ card.name }}</p>
          </div>
        </div>
        <div class="show" :id="card.id">
          <div class="show-box">
            <router-link :to="{ name: 'PlaceDetail', params: { id: card.id } }">
              <img style="border-top-left-radius: 10px; border-top-right-radius: 10px" :src="`${card.img[0]}`" class="card-image" alt="" />
              <div class="d-flex justify-content-around align-items-center">
                <div class="show-box-cardname text-start mt-2" v-if="card.name.length > 8">{{ card.name.slice(0, 8) }}...</div>
                <div class="show-box-cardname text-start mt-3" v-else>{{ card.name }}</div>
                <div class="show-box-cardname text-start ms-4 mt-3"><i class="bi bi-star-fill me-1" style="color: #fe8a01"></i>{{ card.score.toFixed(1) }}</div>
              </div>
              <div class="show-box-cardaddress mt-1 text-start ms-3">{{ card.address }}</div></router-link
            >
          </div>
        </div>
      </naver-marker>
      <!-- <naver-marker v-if="showmarker.name" :latitude="showmarker.latitude" :longitude="showmarker.longitude" class="show-marker d-flex">
        <div>
          <i class="bi bi-geo-alt-fill"></i>
          {{ showmarker.name }}
        </div>
      </naver-marker> -->

      <!-- {{ showmarker.name }} -->
      <!-- {{ this.store.state.root.showMapMarker }} -->
      <!-- {{ card.name }} -->
      <!-- <div class="show"> -->
      <!-- <div class="show-box"> -->

      <!-- <div>{{ card.name }}</div> -->
      <!-- <img :src="`${card.img[0]}`" class="card-image" alt="" /> -->
      <!-- </div> -->
      <!-- </div> -->

      <!-- <naver-marker :latitude="markers[0].latitude" :longitude="markers[0].longitude" /@click="onMarkerClicked" @onLoad="onLoadMarker($markerObject)" -->
      <!-- ><div class="marker"><i class="bi bi-geo-alt-fill"></i> {{ markers[0].placeName }}</div></naver-marker -->
    </naver-maps>
  </div>
</template>

<script>
import { ref, reactive, computed, onMounted, watch } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";

import { NaverMaps, NaverMarker } from "vue3-naver-maps";

export default {
  name: "PlaceSearchMaps",
  components: { NaverMaps, NaverMarker },
  props: ["cards"],
  setup() {
    const store = useStore();
    let gnbSlide = reactive({ ok: "" });
    const router = useRouter();
    const state = reactive({
      showMapMarker: computed(() => store.getters["root/showMapMarker"]),
    });
    const showmarker = reactive({
      longitude: computed(() => store.state.root.showMapMarker.longitude),
      latitude: computed(() => store.state.root.showMapMarker.latitude),
      name: computed(() => store.state.root.showMapMarker.name),
      placeId: computed(() => store.state.root.showMapMarker.placeId),
    });

    const names = computed(() => store.state.root.showMapMarker.name);
    const changemarker = reactive({ name: computed(() => store.state.root.showMapMarker) });
    let markers = reactive(computed(() => store.state.root.placeSearchInfo.placeList));
    // const markers = reactive();
    // watch(markers, () => console.log(markers.value));
    // { latitude: 36, longitude: 127 },
    // { latitude: 36.5759477, longitude: 128.5056462 },
    // ];
    // let cards = reactive(computed(() => store.state.root.placeSearchInfo.placeList));
    const positionYX = ref({});
    const map = ref();
    const marker = ref();
    let tempLongitude;
    let tempLatitude;
    const mapOptions = reactive({
      // 요부분이 맨 첫 리스트의경도 위도 일듯
      longitude: computed(() => {
        return checkLongitude();
      }),
      latitude: computed(() => {
        return checkLatitude();

        // return temp;
      }),

      zoom: 13,
      zoomControl: true,
      zoomControlOptions: { position: "RIGHT_TOP" },
    });
    const initLayers = ["BACKGROUND", "BACKGROUND_DETAIL", "POI_KOREAN", "TRANSIT", "ENGLISH"];
    let checkLatitude = () => {
      if (store.state.root.placeSearchInfo.placeList) {
        tempLatitude = store.state.root.placeSearchInfo.placeList[0].latitude;
      } else {
        tempLatitude = 127;
      }
      return tempLatitude;
    };
    let checkLongitude = () => {
      if (store.state.root.placeSearchInfo.placeList) {
        tempLongitude = store.state.root.placeSearchInfo.placeList[0].longitude;
      } else {
        tempLongitude = 6;
      }
      return tempLongitude;
    };
    const popUP = (id) => {
      var showElements = document.getElementsByClassName("show");
      console.log(showElements, "showElements");
      for (var i in showElements) {
        if (i < showElements.length) {
          // console.log(i);
          var element = document.getElementsByClassName("show")[i];
          if (element.classList.contains("active") & (element.id == id)) {
            element.classList.remove("active");
          } else if (!element.classList.contains("active") & (element.id == id)) {
            element.classList.add("active");
            // element.classList.remove("active");
          } else if (element.classList.contains("active") & (element.id != id)) {
            // console.log(element.classList);
            element.classList.remove("active");
          } else {
            //
          }
        }
      }
    };

    watch(showmarker, () => {
      showmarker.name = names;
    });

    const onLoadMap = (mapObject) => {
      map.value = mapObject; // map에 반환된 객체 저장
      map.value.setCenter(new window.naver.maps.LatLng(37, 127));
    };
    const onLoadMarker = (markerObject) => {
      marker.value = markerObject;
    };
    const onMarkerClicked = () => {
      // console.log(event, "onMarkerClicked");
    };
    const checkPosition = () => {
      // console.log(event, "checkPosition");
    };
    const moveToDetail = (res) => {
      // console.log(res, "여기디테일어디");
      router
        .push({
          name: "PlaceDetail",
          params: {
            id: res,
          },
        })
        .then(() => window.scrollTo(0, 0));
    };
    return {
      // cards,
      map,
      names,
      gnbSlide,
      // name,
      state,
      marker,
      mapOptions,
      initLayers,
      positionYX,
      markers,
      changemarker,
      tempLatitude,
      tempLongitude,
      showmarker,
      onMounted,
      moveToDetail,
      onLoadMap,
      onLoadMarker,
      onMarkerClicked,
      checkPosition,
      checkLatitude,
      checkLongitude,
      popUP,
    };
  },
};
</script>

<style lang="scss" scoped>
// .show-marker {
//   padding: 0.25rem 0.25rem 0.25rem 0.25rem;
//   min-height: 30px;
//   min-width: 10px;
//   max-width: 10rem;
//   border-radius: 0.5rem;
//   font-size: 0.81rem;
//   overflow: hidden;
//   text-overflow: ellipsis;
//   white-space: nowrap;
//   font-weight: bold;
//   box-shadow: (0 0 8px rgba(24, 24, 24, 0.05));
//   z-index: 1;
//   background: black;
//   color: white;
//   position: relative;
//   cursor: pointer;
// }
.marker {
  padding: 0.25rem 0.25rem 0.25rem 0.25rem;
  min-height: 30px;
  min-width: 10px;
  max-width: 10rem;
  border-radius: 0.5rem;
  background: white;
  font-size: 0.81rem;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-weight: bold;
  box-shadow: (0 0 8px rgba(24, 24, 24, 0.05));
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 1;

  &:hover {
    z-index: 3;
    position: relative;
    transform: scale(1.1);
  }
  // display: -webkit-box;
  // -webkit-line-clamp: 1;
  // -webkit-box-orient: vertical;
  cursor: pointer;
}
// .marker:
// .marker:active {
//   z-index: 15;
//   position: relative;
//   transform: scale(1.1);
// }
// .marker:focus-within {
//   z-index: 15;
//   position: relative;
//   transform: scale(1.1);
// }
.show {
  display: none;
  // margin: 0.5rem 0rem 0rem 0rem;
  flex-direction: row;
  align-items: stretch;
  text-align: center;
  // background-color: black;
  height: 230px;
  width: 150px;
  z-index: 5;
}
.show.active {
  display: block;
  box-shadow: (0 0 8px rgba(24, 24, 24, 0.05));
  background-color: white;
  width: 15rem;
  border-radius: 10px;
  left: 8rem;
  top: 2rem;
  z-index: 5;
  position: absolute;
}

.show-box {
  position: relative;
  // width: 200px;
  z-index: 5;
  & > a {
    text-decoration: none;
    color: black;
  }
}
.show-box-cardname {
  font-size: 1rem;
  overflow: hidden;
  font-weight: bold;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
}
.show-box-cardaddress {
  overflow: hidden;
  font-size: 0.7rem;

  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
}
.card-image {
  width: 100%;
  height: 150px;
  object-fit: cover;
  z-index: 5;
  position: relative;
}
.bi-geo-alt-fill {
  font-size: 1rem;
  &:hover {
    z-index: 5;
    // background: black;
    // color: white;
    transform: scale(1.1);
    position: relative;
  }
}
// .marker.show {
//   background-color: black;
//   height: 200px;
//   z-index: 10;
// }
// .active {
//   height: 100px;
//   width: 100px;
//   background-color: black;
// }
</style>
