import {ref, computed} from "vue";
import {defineStore} from "pinia";
import { convertDateFormat, calculateDaysBetweenDates, getAllDatesBetween } from "@/util/dateUtil";
// getAllDatesBetween
// 계획 짜는 부분에 대한 store
export const usePlanStore = defineStore(
    "plan", () =>{

        // plan에서 계획을 설정
        const tripScheduleInfo = ref();

        const setTripSchedule = (data, title) => { 
            console.log("tconsole 실행")
            // data option 만들기
            let scheduleObj = {}; // 객체

            scheduleObj.scheduleArr = data; // 입력 자체 (원형 날짜 데이터 포맷 유지)
            scheduleObj.title = title; // 타이틀
            scheduleObj.period = calculateDaysBetweenDates(data[0], data[1]); // 몇일인지.
            scheduleObj.startDate =  convertDateFormat(data[0], 'YY.MM.DD'); // startDate (FORMAT YY.MM.DD)
            scheduleObj.endDate = convertDateFormat(data[1], 'YY.MM.DD'); // endDate (FORMAT YY.MM.DD)
            scheduleObj.scheduleList = getAllDatesBetween(data[0], data[1]);
            console.log("totalSchedule");
            console.log(scheduleObj);


            tripScheduleInfo.value = scheduleObj; // 객체 넣기
        }

        return {
            tripScheduleInfo,
            setTripSchedule,
        }
    },{
        persist : true          // stoarge : sessionStroate를 할 수도 있음
    } 
)