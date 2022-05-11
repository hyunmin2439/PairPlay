<template>
  <div>
    <naver-maps class="naverMaps" :mapOptions="mapOptions" style="width: 100%; height: 70vh" :initLayers="initLayers" @onLoad="onLoadMap($event)" @click="checkPosition">
      <naver-marker v-for="mark in markers" :key="mark.id" :latitude="mark.latitude" :longitude="mark.longitude" @click="onMarkerClicked" @onLoad="onLoadMarker($markerObject)"></naver-marker>
      <!-- <naver-marker :latitude="markers.latitude" :longitude="markers.longitude" @click="onMarkerClicked" @onLoad="onLoadMarker($markerObject)"></naver-marker> -->
    </naver-maps>
  </div>
</template>

<script>
import { ref, reactive, computed, onMounted } from "vue";
import { useStore } from "vuex";

import { NaverMaps, NaverMarker } from "vue3-naver-maps";

export default {
  name: "PlaceSearchMaps",
  components: { NaverMaps, NaverMarker },
  setup() {
    const store = useStore();
    const markers = reactive(computed(() => store.state.root.mapPosition));

    // const markers = reactive();
    // watch(markers, () => console.log(markers.value));
    // { latitude: 36, longitude: 127 },
    // { latitude: 36.5759477, longitude: 128.5056462 },
    // ];
    const positionYX = ref({});
    const map = ref();
    const marker = ref();
    const mapOptions = reactive({
      // 요부분이 맨 첫 리스트의경도 위도 일듯
      longitude: computed(() => {
        if (store.state.root.placeSearchInfo.placeList) {
          return store.state.root.placeSearchInfo.placeList[0].longitude;
        } else {
          return 6;
        }
      }),
      latitude: computed(() => {
        if (store.state.root.placeSearchInfo.placeList) {
          return store.state.root.placeSearchInfo.placeList[0].latitude;
        } else {
          return 127;
        }
      }),
      zoom: 13,
      zoomControl: true,
      zoomControlOptions: { position: "TOP_RIGHT" },
    });
    const initLayers = ["BACKGROUND", "BACKGROUND_DETAIL", "POI_KOREAN", "TRANSIT", "ENGLISH"];
    // const LatLng = new window.naver.maps.LatLng(37, 127);
    const changeMarkers = async () => {
      var tempLong;
      var tempLat;
      var tempList = { tempLong, tempLat };
      for (let i = 0; i < store.state.root.placeSearchInfo.placeList.length; i++) {
        tempLong = store.state.root.placeSearchInfo.placeList[i].longitude;
        tempLat = store.state.root.placeSearchInfo.placeList[i].latitude;
        // console.log(tempLat);
        markers.pos = tempList;
      }
      return tempList;
      // await store.dispatch("root/changePosition", markers.value);
      // console.log(markers.value);
    };
    // watch(markers.pos, async () => {
    // changeMarkers();
    // });
    // onMounted(async () => {
    // changeMarkers();
    // });

    const onLoadMap = (mapObject) => {
      map.value = mapObject; // map에 반환된 객체 저장
      map.value.setCenter(new window.naver.maps.LatLng(37, 127));
      // console.log(map.value, "map");
      // map.value.setCenter(new window.naver.maps.LatLng(36, 127)); // 지도 중앙 변경
    };
    const onLoadMarker = (markerObject) => {
      // markerObject.marker.setDraggable(true).setCursor("").setClickable(true);
      marker.value = markerObject;
      // console.log(marker.value, "marker");
    };
    const onMarkerClicked = (event) => {
      console.log(event, "onMarkerClicked");
    };
    const checkPosition = (event) => {
      // positionYX['latitude':]
      console.log(event, "checkPosition");
    };
    return {
      map,
      marker,
      mapOptions,
      initLayers,
      positionYX,
      markers,
      onMounted,
      onLoadMap,
      onLoadMarker,
      onMarkerClicked,
      checkPosition,
      changeMarkers,
    };
  },
};
</script>

<style lang="scss" scoped>
// .naverMaps {
// width: 100%;
// height: 500px;
// }
</style>
