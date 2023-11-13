<script setup>
import { onMounted, ref } from "vue";
const kakaoApiKey = "e9f5ba371379c691a4bf3a4ab3827564";

const markerPositions1 = ref([
    [33.452278, 126.567803],
    [33.452671, 126.574792],
    [33.451744, 126.572441],
]);

const markerPositions2 = ref([
    [37.499590490909185, 127.0263723554437],
    [37.499427948430814, 127.02794423197847],
    [37.498553760499505, 127.02882598822454],
    [37.497625593121384, 127.02935713582038],
    [37.49629291770947, 127.02587362608637],
    [37.49754540521486, 127.02546694890695],
    [37.49646391248451, 127.02675574250912],
]);

const markers = ref([]);
const infowindow = ref(null);
let map = null;

const initMap = () => {
    const container = document.getElementById("map");
    const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
    };
    map = new kakao.maps.Map(container, options);
};

onMounted(() => {
    if (window.kakao && window.kakao.maps) {
        initMap();
    } else {
    const script = document.createElement("script");
    script.onload = () => kakao.maps.load(initMap);
    script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=" +
        kakaoApiKey +
        "&libraries=clusterer,drawing,services";
        document.head.appendChild(script);
    }
});
</script>

<template>
    <div id="map"></div>
</template>

<style scoped>
/** 카카오맵 화면 꽉차게 구성 **/
#map {
    width: 100%;
    height: 100%;
    display: flex;
}
</style>
