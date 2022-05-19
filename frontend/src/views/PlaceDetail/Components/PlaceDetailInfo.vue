<template>
  <div>
    <hr />
  </div>
</template>

<script>
import { ref, reactive, computed } from "vue";
import { useStore } from "vuex";
import { NaverMaps, NaverMarker, NaverCircle } from "vue3-naver-maps";

export default {
  name: "PlaceDetailInfo",
  props: ["latitude", "longitude", "placeInfos"],
  components: { NaverMaps, NaverMarker, NaverCircle },
  setup(props) {
    const store = useStore();

    const markers = reactive({
      latitude: computed(() => store.state.root.placeDetailInfo.latitude),
      longitude: computed(() => store.state.root.placeDetailInfo.longitude),
      // { latitude: 36.5759477, longitude: 128.5056462 },
    });
    // const radius = ref(100);
    const circle = ref();
    const state = reactive({});
    const positionYX = ref({});
    const map = ref();
    const marker = ref();
    const mapOptions = reactive({
      // 요부분이 맨 첫 리스트의경도 위도 일듯
      latitude: computed(() => store.state.root.placeDetailInfo.latitude),
      longitude: computed(() => store.state.root.placeDetailInfo.longitude),
      zoom: 16,
      zoomControl: false,
      zoomControlOptions: { position: "TOP_RIGHT" },
    });
    const initLayers = ["BACKGROUND", "BACKGROUND_DETAIL", "POI_KOREAN", "TRANSIT", "ENGLISH"];
    // const LatLng = new window.naver.maps.LatLng(37, 127);
    const onLoadCircle = (circleObject) => {
      circle.value = circleObject;
    };
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
    const onMarkerClicked = () => {
      // console.log(event, "onMarkerClicked");
    };
    const checkPosition = (event) => {
      // positionYX['latitude':]
      // console.log(event, "checkPosition");
    };
    // console.log(props, "place");
    return {
      map,
      state,
      marker,
      mapOptions,
      initLayers,
      positionYX,
      markers,
      onLoadMap,
      onLoadMarker,
      onMarkerClicked,
      checkPosition,
      onLoadCircle,
    };
  },
};
</script>

<style lang="scss" scoped>
.overlay {
  max-width: 30%;
  // height: 50px;
  background: white;
  border-radius: 10px;
  // opacity: 0.2;
  position: relative;
  left: 30px;
  bottom: 370px;
  // margin: auto;
  z-index: 2;
  box-shadow: 0 0 8px rgba(24, 24, 24, 0.3);
}
// .overlay {
//   max-width: 30%;
//   // height: 50px;
//   background: white;
//   border-radius: 10px;
//   // opacity: 0.2;
//   position: relative;
//   bottom: 70px;
//   margin: auto;
//   z-index: 2;
//   box-shadow: 0 0 8px rgba(24, 24, 24, 0.3);
// }
.placeName {
  font-weight: bold;
  padding: 5px 0 0 0;
  font-size: 16px;
  margin: 0 0 5px 0;
  text-align: center;
}

.placeAddress {
  // font-weight: bold;
  padding: 0 0 5px 0;
  text-align: center;
  font-size: 14px;
}
// .mapCircle {
//   color: black;
// }
// .naverMaps {
// width: 100%;
// height: 500px;
// }
</style>
