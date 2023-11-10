<script>
import { toRaw, ref } from "vue";
const kakaoApiKey = "e9f5ba371379c691a4bf3a4ab3827564"
export default {
    name: "KakaoMap",
    // data()에서 반환된 속성들은 반응적인 상태가 되어, this에 노출
    data() {
        return {
            markerPositions1: [
                [33.452278, 126.567803],
                [33.452671, 126.574792],
                [33.451744, 126.572441],
            ],
            markerPositions2: [
                [37.499590490909185, 127.0263723554437],
                [37.499427948430814, 127.02794423197847],
                [37.498553760499505, 127.02882598822454],
                [37.497625593121384, 127.02935713582038],
                [37.49629291770947, 127.02587362608637],
                [37.49754540521486, 127.02546694890695],
                [37.49646391248451, 127.02675574250912],
            ],
            markers: [],
            infowindow: null,
        };
    },
    mounted() {
        if (window.kakao && window.kakao.maps) {
            // 카카오 객체가 있고, 맵 그릴 준비가 되어 있다면, 맵 실행
            this.initMap();
        } else {
            const script = document.createElement("script");

            script.onload = () => kakao.maps.load(this.initMap);
            // 카카오 api 애플리케이션에서 도메인 주소 자체도 등록해야한다. (default domain) + javascript API Key
            script.src = "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=" + kakaoApiKey
            document.head.appendChild(script);
        }
    },
    // method 내 속성 값을 변경하고, 업데이트 할 수 있는 함수
    methods: {
        initMap() {
            const container = document.getElementById("map");
            const options = {
                center: new kakao.maps.LatLng(33.450701, 126.570667),
                level: 5,
            };

            //지도 객체를 등록합니다.
            //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
            this.map = new kakao.maps.Map(container, options);
        },
        changeSize(size) {
            const container = document.getElementById("map");
            container.style.width = `${size}px`;
            container.style.height = `${size}px`;
            toRaw(this.map).relayout();
        },
        displayMarker(markerPositions) {
            if (this.markers.length > 0) {
                this.markers.forEach((marker) => marker.setMap(null));
            }

            const positions = markerPositions.map(
                (position) => new kakao.maps.LatLng(...position)
            );

            if (positions.length > 0) {
                this.markers = positions.map(
                    (position) =>
                        new kakao.maps.Marker({
                            map: toRaw(this.map),
                            position,
                        })
                );

                const bounds = positions.reduce(
                    (bounds, latlng) => bounds.extend(latlng),
                    new kakao.maps.LatLngBounds()
                );

                toRaw(this.map).setBounds(bounds);
            }
        },
        displayInfoWindow() {
            if (this.infowindow && this.infowindow.getMap()) {
                //이미 생성한 인포윈도우가 있기 때문에 지도 중심좌표를 인포윈도우 좌표로 이동시킨다.
                toRaw(this.map).setCenter(this.infowindow.getPosition());
                return;
            }

            var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
                iwPosition = new kakao.maps.LatLng(33.450701, 126.570667), //인포윈도우 표시 위치입니다
                iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

            this.infowindow = new kakao.maps.InfoWindow({
                map: toRaw(this.map), // 인포윈도우가 표시될 지도
                position: iwPosition,
                content: iwContent,
                removable: iwRemoveable,
            });

            toRaw(this.map).setCenter(iwPosition);
        },
    },
};
</script>

<template>
    <div id="map">
    </div>
</template>

<style scoped>
/** 카카오맵 화면 꽉차게 구성 **/
#map {
    width: 100%;
    height: 100%;
    display: flex;
}
</style>
