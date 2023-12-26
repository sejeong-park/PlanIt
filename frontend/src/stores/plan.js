import {ref} from "vue";
import {defineStore} from "pinia";
import { convertDateFormat, calculateDaysBetweenDates, getAllDatesBetween } from "@/util/dateUtil";

// 다음 스토어는 "Schedule & Plan"의 계획을 설정하는 것과 관련된 상태 Store

// getAllDatesBetween
// 계획 짜는 부분에 대한 store
export const usePlanStore = defineStore(
    "plan", () =>{

        const planKey = ref(); // 임의로 이렇게 설정함.

        // plan에서 계획을 설정 (plan에서 계획 설정 시 startDate, endDate 등의 정보를 세부 일정 계획 페이지로 전달한다.)
        const tripScheduleInfo = ref();

        // 일정 옮기기 
        const clickAttractionInfo = ref(null); // 여행지을 선택했음
        const clickScheduleInfo = ref(null); // 일정을 선택했음

        // SchduleList에서 관리하기 편한 형태로 데이터를 호출한다.
        const setTripSchedule = (data) => { 
            data.scheduleList = getAllDatesBetween(data.startDate, data.endDate);
            data.scheduleArr = [data.startDate, data.endDate]; 
            data.period = calculateDaysBetweenDates(data.startDate, data.endDate); 
            data.dateShortFormat = [convertDateFormat(data.startDate, 'YY.MM.DD'), convertDateFormat(data.endDate, 'YY.MM.DD')]; // startDate (FORMAT YY.MM.DD)
            tripScheduleInfo.value = data; // api에서 받아온 데이터
        }

        // Drawer 에서 일정을 등록 할 시 스케줄을 추가한다.
        const addClickedAttractionInfo = (data) => {
            clickAttractionInfo.value = data;
            const currentDate = clickScheduleInfo.value; // 여행지 선택과 일정 선택 교차.

            tripScheduleInfo.value.scheduleList[currentDate].push(clickAttractionInfo.value);
    
            console.log("test");
            console.log(tripScheduleInfo.value);
            

        }
        const setClickedActiveDate = (data) => {
            clickScheduleInfo.value = data; // 클릭 이벤트 시, 현재 클릭된 일정을 관리한다.
        }

        return {
            planKey,
            tripScheduleInfo,
            clickScheduleInfo,
            setTripSchedule,
            addClickedAttractionInfo,
            setClickedActiveDate,
        }
    },{
        persist : true          // stoarge : sessionStroate를 할 수도 있음
    } 
)
