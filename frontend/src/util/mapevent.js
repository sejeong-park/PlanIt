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
    console.log()
    makePolyLine(polyline); // polyLine 그리기
    // loadResultMarkers(date); // marker 그리기
    Array.prototype.push.apply(totalPosition.value, positions.value); // 전체 포지션에 대한 결과            
})

// 전체 포인트 기준으로 맵 이동 시키기
const bounds = totalPosition.value.reduce(
    (bounds, position) => bounds.extend(position.latlng),
    new kakao.maps.LatLngBounds()
);

map.value.setBounds(bounds);