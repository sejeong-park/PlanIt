<script setup>
import { onMounted, ref, watch, defineProps } from "vue";
import {useTripSearchStore} from '@/stores/trip';


const props = defineProps({
    open : Boolean, // drawer의 열려있음 여부
    drawerWidth : String // drawer의 너비
})

const tripSearchStore = useTripSearchStore();   // SearchDrawerItem에서 불러오는 데이터

// 카카오맵 자체
const map = ref(null);  // kakao map import 
const container = ref(null);
// 지도 표시 할 것들
const positions = ref([]);
const markers = ref([]); // marker를 로드하는 함수
const focusMarkerIdx = ref(); // markers에서 변경해줄 markerIdx (인덱스에서 접근한다.)

// 마커 이미지 만들기 
const imgSrc = "src/assets/img/map/map-marker-primary.png";
const clickSrc = "src/assets/img/map/map-marker-point-1.png";
const originMarkerImg = ref();
const clickMarkerImg = ref();

function createMarkerImage(markerSize, imageOrigin) {
    const markerImage = new kakao.maps.MarkerImage(
        imageOrigin,
        markerSize
    )
    return markerImage;
}

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
    [() => tripSearchStore.focusLocation, () => props.open],
    ([focusLocation, isOpen]) => {
        // 입력받은 focus 확인
        if (focusLocation) {
            const newCenter = new kakao.maps.LatLng(focusLocation.data.mapy, focusLocation.data.mapx);
            changeMarkerImg(focusLocation.index);
            adjustMapCenter(newCenter, isOpen);
        }
    },
    {deep : true}
);


// 특정 marker의 index를 선택해서, 변경하는 마커
const changeMarkerImg = (focusIndex) => {
    // 이전에 포커싱된 것이 있었다면 해제해주고 이미지 이동
    if (focusMarkerIdx.value !== null && focusMarkerIdx.value !== undefined && markers.value[focusMarkerIdx.value]){
        markers.value[focusMarkerIdx.value].setImage(originMarkerImg.value);
    }
    markers.value[focusIndex].setImage(clickMarkerImg.value);
    focusMarkerIdx.value = focusIndex;
};


// marker 화면 표시
const loadMarkers = () => {
    deleteMarkers();
    
    // 마커 값 표시
    markers.value = [];
    positions.value.forEach((pos, index) => {
        // 각 포지션 for문 돌리기
        console.log(pos);
        const marker = new kakao.maps.Marker({
            map : map.value,
            position : pos.latlng,
            title : pos.title,
            clickable : true,
            image : originMarkerImg.value
        });

        markers.value.push(marker); // 전역에 넣기


        kakao.maps.event.addListener(marker, 'click', function() { 
            console.log("클릭")
            changeMarkerImg(index); // 색상 변경
            adjustMapCenter(pos.latlng); // 센터 이동
        })

    });

    // 지도를 이동시키기
    const bounds = positions.value.reduce(
        (bounds, position) => bounds.extend(position.latlng),
        new kakao.maps.LatLngBounds()
    );

    map.value.setBounds(bounds);
}

// marker 초기화
const deleteMarkers = () => {
    if (markers.value.length > 0){
        markers.value.forEach((marker) => marker.setMap(null));
    }
};

// 지도의 중심을 조정하는 함수
const adjustMapCenter = (center, isOpen) => {
    if (!map.value) return;

    // drawerWidth 정수 추출
    const drawerWidth = parseInt(props.drawerWidth, 10); // 10진수 변환
    const offsetX = props.open ? drawerWidth / 2: 0;
    
    const mapWidth = container.value.offsetWidth; // map의 너비
    const bounds  = map.value.getBounds();
    const lngSpan = bounds.getNorthEast().getLng() - bounds.getSouthWest().getLng();
    const lngPerPixel = lngSpan / mapWidth;
    const lngOffset = lngPerPixel * offsetX;
    const newCenter = new kakao.maps.LatLng(
        center.getLat(),
        center.getLng() - lngOffset
    );
    map.value.panTo(newCenter);
};


// kakao map 객체가 생성되기 전에 객체를 부르는 경우 때문에 init 뒤에 초기화를 묶어주었다.
const initKakaoObj = () => {
    originMarkerImg.value = createMarkerImage(new kakao.maps.Size(64, 64), imgSrc);
    clickMarkerImg.value = createMarkerImage(new kakao.maps.Size(72, 72), clickSrc);
}


// Kakao Map 초기화
const initMap = () => {
    const initContainer = document.getElementById("map");
    container.value = initContainer;
    const options = {
        center : new kakao.maps.LatLng(33.450701, 126.570667),
        level : 3
    };
    
    map.value = new kakao.maps.Map(container.value, options);
    initKakaoObj();
};


// 화면이 켜지자 마자.
onMounted(() => {
    if (window?.kakao && window?.kakao?.maps) {
        initMap();
    } else{
        const script = document.createElement("script");
        script.onload = () => kakao.maps.load(() => initMap());
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
