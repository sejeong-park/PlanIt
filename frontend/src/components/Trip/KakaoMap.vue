<script setup>
import { onMounted, ref, watch, defineProps } from "vue";
import { useTripSearchStore } from "@/stores/trip";

const props = defineProps({
  open: Boolean, // drawer의 열려있음 여부
  drawerWidth: String, // drawer의 너비
  planResultData: Object, // 전체 일정까지 완료되었을 때 넘어오는 결과 데이터
  colorDate: Object, // RGB 코드
});

// 카카오맵 자체
const map = ref(null); // kakao map import
const container = ref(null);
const kakaoMapStatus = ref(false); // 카카오맵 로드가 느릴 경우,,

// SearchDrawerItem에서 불러오는 데이터s
const tripSearchStore = useTripSearchStore();

// 지도 표시 할 것들
const positions = ref([]);
const markers = ref([]); // marker를 로드하는 함수
const focusMarkerIdx = ref(); // markers에서 변경해줄 markerIdx (인덱스에서 접근한다.)


// 마커 이미지 만들기 
const imgSrc = "/src/assets/img/map/map-marker-primary.png";
const clickSrc = "/src/assets/img/map/map-marker-point-1.png";
const resultSrc = "/src/assets/img/map/map-pin.png";

const originMarkerImg = ref();
const clickMarkerImg = ref();
const resultMarkerImg = ref();

function createMarkerImage(markerSize, imageOrigin) {

    const markerImage = new kakao.maps.MarkerImage(
        imageOrigin,
        markerSize
    )
    return markerImage;

}

onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    if (typeof kakao !== "undefined" && kakao.maps) {
      initMap();
    } else {
      setTimeout(initMap, 1000);
    }
    console.log("kakao", kakao);
  } else {
    const script = document.createElement("script");
    script.onload = () => kakao.maps.load(initMap);
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${
      import.meta.env.VITE_KAKAO_MAP_SERVICE_KEY
    }&libraries=services,clusterer`;
    /* global kakao */
    script.onload = () => kakao.maps.load(() => initMap());
    document.head.appendChild(script);
  }
});

// 조회 리스트 뽑아오기
watch(

    () => tripSearchStore.searchLocationList,
    () => {
        positions.value = []; // 초기화
        tripSearchStore.searchLocationList.forEach((tripInfo) =>{
            let obj = {};
            obj.latlng = new kakao.maps.LatLng(tripInfo.latitude, tripInfo.longitude); // (mapy, mapx) 좌표 이다. -> 이거 찾는데 하루 날림 ㅋ,
            obj.title = tripInfo.title;
            positions.value.push(obj);
        });
        loadSearchMarkers(); // 마커 로딩하기
    },
    {deep : true}
);

// 포커싱할 것 찾아오기
watch(

    [() => tripSearchStore.focusLocation, () => props.open],
    ([focusLocation, isOpen]) => {
        // 입력받은 focus 확인
        if (focusLocation) {
            const newCenter = new kakao.maps.LatLng(focusLocation.data.latitude, focusLocation.data.longitude);
            changeMarkerImg(focusLocation.index);
            adjustMapCenter(newCenter, isOpen);
        }
    },
    {deep : true}

);

// 결과 창으로 넘어갔을 때, 카카오맵에 내가 선택한 일정의 정보를 노출시킨다.
const resultPosition = ref({}); // date key 기준으로 묶음이 달라져야해서, object 형식으로 넣을 poistion

watch( [() => props.planResultData, kakaoMapStatus], 
    ([data, kakaoMapStatus]) => {
        // date를 기준으로 key-value
        const targetItem = data.scheduleList; // 조회 정보만
        const totalPosition = ref([]); // 전체 position에 대한 정보

        Object.keys(targetItem).forEach(date => {

            // 날짜별 일정 결과 만들기
            const polyline = []; // 폴리라인 만들기
            positions.value = []; // 일자별 일정 정보 초기화
            
            targetItem[date].forEach((tripInfo, index, array) => {

                // 만약 유저가 개인적으로 생성한 메모라면, 마커 생성하지 않는다.
                if (!tripInfo.latitude || !tripInfo.longitude){
                    console.log(`사용자가 등록한 일정 : ${tripInfo.title}`);
                }else {

                    const currentLatLng = new kakao.maps.LatLng(tripInfo.latitude, tripInfo.longitude); // (mapy, mapx) 좌표 이다. -> 이거 찾는데 하루 날림 ㅋ,

                    // 좌표들의 위치 구하기
                    positions.value.push({
                        latlng : currentLatLng,
                        title : tripInfo.title
                    }); 

                    if (index < array.length - 1){
                        const nextTripInfo = array[index + 1];
                        if (nextTripInfo.latitude && nextTripInfo.longitude){
                            const nextLatLng = new kakao.maps.LatLng(nextTripInfo.latitude, nextTripInfo.longitude);
                            const obj = {
                                path : [currentLatLng, nextLatLng],
                                color : props?.colorDate[date]
                            }
                            polyline.push(obj);
                        }
                    } // 탐색 종료
                }
            })
            makePolyLine(polyline); // polyLine 그리기
            loadResultMarkers(date); // marker 그리기
            Array.prototype.push.apply(totalPosition.value, positions.value); // 전체 포지션에 대한 결과            
        })

        // 전체 포인트 기준으로 맵 이동 시키기
        const bounds = totalPosition.value.reduce(
            (bounds, position) => bounds.extend(position.latlng),
            new kakao.maps.LatLngBounds()
        );

        map.value.setBounds(bounds);
        
    }
)

function makePolyLine(polyLineList){


        if (index < array.length - 1) {
          const nextTripInfo = array[index + 1];
          if (nextTripInfo.mapx && nextTripInfo.mapy) {
            const nextLatLng = new kakao.maps.LatLng(nextTripInfo.mapy, nextTripInfo.mapx);
            const obj = {
              path: [currentLatLng, nextLatLng],
              color: props?.colorDate[date],
            };
            polyline.push(obj);
          }
        } // 탐색 종료
      }
    });
    makePolyLine(polyline); // polyLine 그리기
    loadResultMarkers(date); // marker 그리기
    Array.prototype.push.apply(totalPosition.value, positions.value); // 전체 포지션에 대한 결과
  });

  // 전체 포인트 기준으로 맵 이동 시키기
  const bounds = totalPosition.value.reduce(
    (bounds, position) => bounds.extend(position.latlng),
    new kakao.maps.LatLngBounds()
  );

  map.value.setBounds(bounds);
});

function makePolyLine(polyLineList) {
  for (var index = 0; index < polyLineList.length; index++) {
    const item = polyLineList[index];

    var drawPolyline = new kakao.maps.Polyline({
      map: map.value,
      path: item.path,
      strokeWeight: 7,
      strokeColor: item.color,
      strokeOpacity: 0.9,
      strokeStyle: "solid",
    });
  }
}

// 특정 marker의 index를 선택해서, 변경하는 마커
const changeMarkerImg = (focusIndex) => {
  // 이전에 포커싱된 것이 있었다면 해제해주고 이미지 이동
  if (
    focusMarkerIdx.value !== null &&
    focusMarkerIdx.value !== undefined &&
    markers.value[focusMarkerIdx.value]
  ) {
    markers.value[focusMarkerIdx.value].setImage(originMarkerImg.value);
  }
  markers.value[focusIndex].setImage(clickMarkerImg.value);
  focusMarkerIdx.value = focusIndex;
};

const loadResultMarkers = (date) => {
  resultMarkerImg.value = createMarkerImage(new kakao.maps.Size(56, 56), resultSrc);
  // deleteMarkers();
  markers.value = []; // 마커 값 표시
  positions.value.forEach((pos, index) => {
    const marker = new kakao.maps.Marker({
      map: map.value,
      position: pos.latlng,
      title: pos.title,
      yAnchor: 1,
      xAnchor: 0.5,
      clickable: true,
      image: resultMarkerImg.value,
    });
    const content = `<div class="customoverlay"> 
                            <a href="https://map.kakao.com/link/map/11394059" target="_blank"> 
                                <span class="title">${pos.title}</span> 
                            </a> 
                        </div>`;

    const customOverlay = new kakao.maps.CustomOverlay({
      map: map.value,
      position: pos.latlng,
      content: content,
      yAnchor: 1,
      xAnchor: 0.5,
    });

    markers.value.push(marker);
  });
};

// marker 화면 표시
const loadSearchMarkers = () => {
  deleteMarkers();

  // 마커 값 표시
  markers.value = [];
  positions.value.forEach((pos, index) => {
    // 각 포지션 for문 돌리기
    console.log(pos);
    const marker = new kakao.maps.Marker({
      map: map.value,
      position: pos.latlng,
      title: pos.title,
      clickable: true,
      image: originMarkerImg.value,
    });

    markers.value.push(marker); // 전역에 넣기

    kakao.maps.event.addListener(marker, "click", function () {
      console.log("클릭");
      changeMarkerImg(index); // 색상 변경
      adjustMapCenter(pos.latlng); // 센터 이동
    });
  });

  // 지도를 이동시키기
  const bounds = positions.value.reduce(
    (bounds, position) => bounds.extend(position.latlng),
    new kakao.maps.LatLngBounds()
  );

  map.value.setBounds(bounds);
};

// marker 초기화
const deleteMarkers = () => {
  if (markers.value.length > 0) {
    markers.value.forEach((marker) => marker.setMap(null));
  }
};

// 지도의 중심을 조정하는 함수
const adjustMapCenter = (center, isOpen) => {
  if (!map.value) return;

  // drawerWidth 정수 추출
  const drawerWidth = parseInt(props.drawerWidth, 10); // 10진수 변환
  const offsetX = props.open ? drawerWidth / 2 : 0;

  const mapWidth = container.value.offsetWidth; // map의 너비
  const bounds = map.value.getBounds();
  const lngSpan = bounds.getNorthEast().getLng() - bounds.getSouthWest().getLng();
  const lngPerPixel = lngSpan / mapWidth;
  const lngOffset = lngPerPixel * offsetX;
  const newCenter = new kakao.maps.LatLng(center.getLat(), center.getLng() - lngOffset);
  map.value.panTo(newCenter);
};

// kakao map 객체가 생성되기 전에 객체를 부르는 경우 때문에 init 뒤에 초기화를 묶어주었다.
const initKakaoObj = () => {
  originMarkerImg.value = createMarkerImage(new kakao.maps.Size(64, 64), imgSrc);
  clickMarkerImg.value = createMarkerImage(new kakao.maps.Size(72, 72), clickSrc);
};

// Kakao Map 초기화
const initMap = () => {
  const initContainer = document.getElementById("map");
  container.value = initContainer;
  const options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 3,
  };

  map.value = new kakao.maps.Map(container.value, options);
  kakaoMapStatus.value = true;
  initKakaoObj();
};


// 화면이 켜지자 마자.
onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    const script = document.createElement("script");
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${
      import.meta.env.VITE_KAKAO_MAP_SERVICE_KEY
    }&libraries=services,clusterer`;
    /* global kakao */
    script.onload = () => kakao.maps.load(() => initMap());
    document.head.appendChild(script);
  }

});
</script>
<template>
  <div id="map"></div>
</template>
<style>
/** 카카오맵 화면 꽉차게 구성 **/
#map {
  width: 100%;
  height: 100%;
  display: flex;
}

.customoverlay {
  position: relative;
  bottom: 85px;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  float: left;
}
.customoverlay:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.customoverlay a {
  display: block;
  text-decoration: none;
  color: #000;
  text-align: center;
  border-radius: 6px;
  font-size: 14px;
  font-weight: bold;
  overflow: hidden;
  background: #d95050;
  background: #d95050 url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 14px center;
}
.customoverlay .title {
  display: block;
  text-align: center;
  background: #fff;
  margin-right: 35px;
  padding: 10px 15px;
  font-size: 14px;
  font-weight: bold;
}
.customoverlay:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: -12px;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
</style>
