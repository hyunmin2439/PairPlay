<template>
  <div>
    <naver-maps :mapOptions="mapOptions" :initLayers="initLayers" @onLoad="onLoadMap($event)"> </naver-maps>
  </div>
</template>

<script>
import { ref } from "vue";
import { NaverMaps } from "vue3-naver-maps";

export default {
  name: "PlaceSearchMaps",
  components: { NaverMaps },
  setup() {
    const map = ref();
    const mapOptions = {
      latitude: 37.56663888630603, // 지도 중앙 위도
      longitude: 126.97838310403904, // 지도 중앙 경도
      zoom: 16,
      zoomControl: false,
      zoomControlOptions: { position: "TOP_RIGHT" },
    };
    const initLayers = ["BACKGROUND", "BACKGROUND_DETAIL", "POI_KOREAN", "TRANSIT", "ENGLISH"];
    // const LatLng = new window.naver.maps.LatLng(37, 127);

    const onLoadMap = (mapObject) => {
      map.value = mapObject; // map에 반환된 객체 저장
      // map.value.setCenter(new window.naver.maps.LatLng(36, 127)); // 지도 중앙 변경
    };
    return {
      mapOptions,
      initLayers,
      onLoadMap,
    };
  },
};
</script>

<style lang="scss" scoped></style>
