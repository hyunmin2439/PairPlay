<template>
  <div>
    <naver-maps class="naverMaps" style="width: 100%; height: 100vh; position: sticky" :mapOptions="mapOptions" :initLayers="initLayers" @onLoad="onLoadMap($event)">
      <naver-marker v-for="card in cards" :key="card.id" :latitude="card.latitude" :longitude="card.longitude" @click="moveToDetail(card.id)">
        <div class="marker d-flex">
          <i class="bi bi-geo-alt-fill"></i>
          {{ card.name }}
          <!-- <div class="show"> -->
          <!-- <div class="show-box d-flex flex-column">
              <div>{{ card.name }}</div>
              <img :src="`${card.img[0]}`" class="card-image" alt="" />
            </div> -->
          <!-- </div> -->
        </div>
      </naver-marker>
      <naver-marker v-if="changemarker.name" :latitude="showmarker.latitude" :longitude="showmarker.longitude">
        <div class="show-marker d-flex">
          <i class="bi bi-geo-alt-fill"></i>
          {{ changemarker.name }}
        </div>
      </naver-marker>

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
    const router = useRouter();
    const showmarker = reactive({
      longitude: computed(() => store.state.root.showMapMarker.longitude),
      latitude: computed(() => store.state.root.showMapMarker.latitude),
    });
    const changemarker = reactive({ name: computed(() => store.state.root.showMapMarker) });
    let markers = reactive(computed(() => store.state.root.placeSearchInfo.placeList));
    // const markers = reactive();
    // watch(markers, () => console.log(markers.value));
    // { latitude: 36, longitude: 127 },
    // { latitude: 36.5759477, longitude: 128.5056462 },
    // ];
    // let cards = reactive(computed(() => store.state.root.placeSearchInfo.placeList));
    console.log(showmarker);
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
    // console.log(showmarker, "showmarker");
    const initLayers = ["BACKGROUND", "BACKGROUND_DETAIL", "POI_KOREAN", "TRANSIT", "ENGLISH"];
    let checkLatitude = () => {
      if (store.state.root.placeSearchInfo.placeList) {
        tempLatitude = store.state.root.placeSearchInfo.placeList[0].latitude;
        console.log(tempLatitude, "있다 래티튜드");
      } else {
        tempLatitude = 127;
        console.log(tempLatitude, "없다 latitude");
      }
      return tempLatitude;
    };
    let checkLongitude = () => {
      if (store.state.root.placeSearchInfo.placeList) {
        tempLongitude = store.state.root.placeSearchInfo.placeList[0].longitude;
        console.log(tempLongitude, "있다 롱기튜드");
      } else {
        tempLongitude = 6;
        console.log(tempLongitude, "없다 longitude");
      }
      return tempLongitude;
    };
    const popUP = (event) => {
      console.log(event.pointerEvent.target);
      var showElement = event.pointerEvent.target.querySelector(".show");
      console.log(showElement);
      if (showElement.classList.contains("active")) {
        showElement.classList.remove("active");
        console.log(showElement);
      } else {
        showElement.classList.add("active");
        console.log(showElement);
      }
      // console.log(showElement);
      // if ()
      // var target = showElement.style.;
      // if (target === "none") {
      //   target = "block";
      // } else {
      //   target = "none";
      // }
      // showElement.style.display = target;
    };

    watch(showmarker, () => {
      // console.log(showmarker.name, "showmarker");
      //   markers = reactive(computed(() => store.state.root.placeSearchInfo.placeList));
      //   // console.log(timeData, "timeData");
      //   // console.log(searchFiltersData.value, "searchFiltersData");
      // });
      // const LatLng = new window.naver.maps.LatLng(37, 127);
      // onMounted(async () => {
      // changeMarkers();
    });

    const onLoadMap = (mapObject) => {
      map.value = mapObject; // map에 반환된 객체 저장
      map.value.setCenter(new window.naver.maps.LatLng(37, 127));
    };
    const onLoadMarker = (markerObject) => {
      marker.value = markerObject;
    };
    const onMarkerClicked = (event) => {
      console.log(event, "onMarkerClicked");
    };
    const checkPosition = (event) => {
      console.log(event, "checkPosition");
    };
    const moveToDetail = (res) => {
      console.log(res, "여기디테일어디");
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
      // name,
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
.show-marker {
  padding: 0.25rem 0.25rem 0.25rem 0.25rem;
  min-height: 30px;
  min-width: 10px;
  max-width: 10rem;
  border-radius: 0.5rem;
  font-size: 0.81rem;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-weight: bold;
  box-shadow: (0 0 8px rgba(24, 24, 24, 0.05));
  z-index: 10;
  background: black;
  color: white;
  position: relative;
  cursor: pointer;
}
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

  // display: -webkit-box;
  // -webkit-line-clamp: 1;
  // -webkit-box-orient: vertical;
  cursor: pointer;
  &:hover {
    z-index: 1;
    background: black;
    color: white;
    position: relative;
  }
}
.show {
  display: none;
  // margin: 0.5rem 0rem 0rem 0rem;
  flex-direction: row;
  align-items: stretch;
  text-align: center;
  // background-color: black;
  height: 200px;
  width: 100px;
  z-index: 10;
}
// .show.active {
//   display: block;
//   // margin: 0.5rem 0rem 0rem 0rem;
//   flex-direction: row;
//   align-items: stretch;
//   text-align: center;
//   background-color: white;
//   height: 200px;
//   width: 100px;
//   z-index: 11;
//   position: relative;

//   &:hover {
//     z-index: 10;
//     background: black;
//     color: white;
//     position: relative;
//   }
// }
// .show-box.show.active {
//   background: white;
// }
.card-image {
  width: 100%;
  // object-fit: cover;
}
.bi-geo-alt-fill {
  font-size: 1rem;
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
