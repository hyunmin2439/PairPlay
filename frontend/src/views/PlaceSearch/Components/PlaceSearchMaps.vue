<template>
  <div>
    <naver-maps class="naverMaps" :mapOptions="mapOptions" style="width: 100%; height: 70vh" :initLayers="initLayers" @onLoad="onLoadMap($event)">
      <naver-marker :latitude="marker" :longitude="marker" @click="onMarkerClicked" @onLoad="onMarkerLoaded($markerObject)"></naver-marker>
    </naver-maps>
  </div>
</template>

<script>
import { ref } from "vue";
import { NaverMaps, NaverMarker } from "vue3-naver-maps";

export default {
  name: "PlaceSearchMaps",
  components: { NaverMaps, NaverMarker },
  setup() {
    const map = ref();
    const marker = ref();
    const mapOptions = {
      // latitude: 37.56663888630603, // 지도 중앙 위도
      langtitude: 36.5707509,
      longitude: 128.7053159,
      // longitude: 126.97838310403904, // 지도 중앙 경도
      zoom: 16,
      zoomControl: true,
      zoomControlOptions: { position: "TOP_RIGHT" },
    };
    const initLayers = ["BACKGROUND", "BACKGROUND_DETAIL", "POI_KOREAN", "TRANSIT", "ENGLISH"];
    // const LatLng = new window.naver.maps.LatLng(37, 127);

    const onLoadMap = (mapObject) => {
      map.value = mapObject; // map에 반환된 객체 저장
      // map.value.setCenter(new window.naver.maps.LatLng(36, 127)); // 지도 중앙 변경
    };
    const onMarkerLoaded = (markerObject) => {
      // markerObject.marker.setDraggable(true).setCursor("").setClickable(true);
      marker.value = markerObject;
    };
    const onMarkerClicked = (event) => {
      console.log(event);
    };
    return {
      map,
      marker,
      mapOptions,
      initLayers,
      onLoadMap,
      onMarkerLoaded,
      onMarkerClicked,
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
