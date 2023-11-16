import {ref, computed} from "vue";
import {defineStore} from "pinia";

// 내가 클릭한 호버의 위치가 어디있는 지 조회하는 스토어
export const useTripSearchStore = defineStore(
    "trip", () =>{

        const focusLocation = ref(); //
        const searchLocationList = ref([]); // 검색한 순간 카카오맵에 뿌려줄 전체 위치

        const setFocusLocation = (data) => {
            console.log("focus Location : ", data);
            focusLocation.value = data;
        };

        // kakaomap에 표시할 전체 데이터
        const setTotalSearchLocation = (data) => {
            // console.log("search Data : " , data);
            searchLocationList.value = data; 
        };

        return {
            focusLocation,
            searchLocationList,
            setFocusLocation,
            setTotalSearchLocation
        }
    },{
        persist : true          // stoarge : sessionStroate를 할 수도 있음
    } 
)