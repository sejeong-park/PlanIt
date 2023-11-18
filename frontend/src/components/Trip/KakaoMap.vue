<script setup>
import { onMounted, ref, watch, defineProps } from "vue";
import {useTripSearchStore} from '@/stores/trip';


const props = defineProps({
    open : Boolean, // drawer의 열려있음 여부
    drawerWidth : String // drawer의 너비
})

const map = ref(null);  // kakao map import 
const positions = ref([]);
const markers = ref([]);

const tripSearchStore = useTripSearchStore();   // SearchDrawerItem에서 불러오는 데이터
const focusLocation = ref(); // 버튼을 누른 값
const searchLocationList = ref([]);

// 조회 리스트 뽑아오기
watch(
    () => tripSearchStore.searchLocationList,
    () => {
        positions.value = []; // 초기화
        tripSearchStore.searchLocationList.forEach((tripInfo) =>{
            let obj = {};
            obj.latlng = new kakao.maps.LatLng(tripInfo.mapy, tripInfo.mapx); // (mapy, mapx) 좌표 이다. -> 이거 찾는데 하루 날림 ㅋ,
            obj.title = tripInfo.title;
            positions.value.push(obj);
        });
        loadMarkers(); // 마커 로딩하기
    },
    {deep : true}
);

// 포커싱할 것 찾아오기
watch(
    () => tripSearchStore.focusLocation,
    () => {
        let moveLatLon = new kakao.maps.LatLng(tripSearchStore.focusLocation.mapy, tripSearchStore.focusLocation.mapx);
        map.value.panTo(moveLatLon);
    },
    {deep : true}
);

// drawer의 상태가 변경될 때마다 지도의 중심을 재조정
watch(() => props.drawerOpen, (isOpen) => {
    console.log("drawer 상태변경 :: ", isoOpen);
    if (isOpen) {
        // drawer가 열렸을 때 새로운 중심 좌표 계산
        const currentCenter = map.value.getCenter;
        panToCenterWithDrawerOffset(currentCenter);
    }
})

// marker 화면 표시
const loadMarkers = () => {
    deleteMarkers();

    // 마커 이미지 생성
    // var imageSrc = `assets/img/marker_${ positions[i].contenttypeid }_color_round.png`; (색상 지정할 것)
    const imgSrc = "src/assets/img/map/map-marker-primary.png";
    const imgSize = new kakao.maps.Size(52, 52);
    const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);

    const content = '<div class ="label"><span class="left"></span><span class="center">카카오!</span><span class="right"></span></div>;'
    
    // 마커 값 표시
    markers.value = [];
    positions.value.forEach((pos) => {
        // 각 포지션 for문 돌리기

        const marker = new kakao.maps.Marker({
            map : map.value,
            position : pos.latlng,
            title : pos.title,
            clickable : true,
            image : markerImage
        });


        markers.value.push(marker); // 전역에 넣기
        // 이벤트 리스너 등록하기
        const infowindow = new kakao.maps.InfoWindow({
            content : pos.title
        })

        kakao.maps.event.addListener(marker, "mouseover", makeOverListener(map, marker, infowindow));
        // kakao.maps.event.addListener(marker, "mouseout"), makeOutListener(infowindow);

        // kakao.maps.event.addListener(marker, "mousemover", )
        kakao.maps.event.addListener(marker, 'click', function() { 
            console.log("클릭")
            const overlay = new kakao.maps.CustomOverlay({
                map : map.value,
                position : marker.getPosition(),
                content : content
            });
        })

    });

    // 지도를 이동시키기
    const bounds = positions.value.reduce(
        (bounds, position) => bounds.extend(position.latlng),
        new kakao.maps.LatLngBounds()
    );

    map.value.setBounds(bounds);
}


// 이벤트 함수
function makeOverListener(map, marker, infowindow) {
    return function(){
        infowindow.open(map, marker);
    };
}
function makeOutListener(infowindow) {
    return function() {
        infowindow.close();
    }
}

// marker 초기화
const deleteMarkers = () => {
    if (markers.value.length > 0){
        markers.value.forEach((marker) => marker.setMap(null));
    }
};

// 지도의 중심을 조정하는 함수
const panToCenterWithDrawerOffset = (center) => {
    if (props.open && map.value) {
        const offsetX = props.drawerWidth / 2; // 중심점을 옮길 x offset 값;
        const mapWidth = mapContainer.value.offsetWidth;
        const bounds = map.value.getBounds();
        // 중점 가져오기
        const lngSpan = bounds.getNorthEast().getLng() - bounds.getSouthWest().getLng();
        const lngPerPixel = lngSpan / mapWidth;
        const lngOffset = lngPerPixel * offsetX;
        
        // 새로운 중심 좌표
        const newCenter = new kakao.maps.LatLng(
            center.getLat(),
            center.getLng() - lngOffset
        );
        map.value.panTo(newCenter);
    }else {
        map.value && map.value.panTo(center);
    }
};


// Kakao Map 초기화
const initMap = () => {
    const container = document.getElementById("map");
    const options = {
        center : new kakao.maps.LatLng(33.450701, 126.570667),
        level : 3
    };
    map.value = new kakao.maps.Map(container, options);
};


// 화면이 켜지자 마자.
onMounted(() => {
    if (window.kakao && window.kakao.maps) {
        initMap();
    } else{
        const script = document.createElement("script");
        script.onload = () => kakao.maps.load(initMap);
        script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${
            import.meta.env.VITE_KAKAO_MAP_SERVICE_KEY
            }&libraries=services,clusterer`;
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
