import {ref} from "vue";
import {defineStore} from "pinia";
import { convertDateFormat, calculateDaysBetweenDates, getAllDatesBetween } from "@/util/dateUtil";

// 다음 스토어는 "Schedule & Plan"의 계획을 설정하는 것과 관련된 상태 Store

// getAllDatesBetween
// 계획 짜는 부분에 대한 store
export const usePlanStore = defineStore(
    "plan", () =>{

        const planKey = ref("3f54528e-19a2-4f8e-8ce5-92bbf281be48"); // 임의로 이렇게 설정함.
        // const planKey = ref("87690a6a-32a7-42e7-a175-8fea7e06038a"); // 임의로 이렇게 설정함.

        // plan에서 계획을 설정 (plan에서 계획 설정 시 startDate, endDate 등의 정보를 세부 일정 계획 페이지로 전달한다.)
        const tripScheduleInfo = ref();

        // 일정 옮기기 
        const clickAttractionInfo = ref(null); // 여행지을 선택했음
        const clickScheduleInfo = ref(null); // 일정을 선택했음

        // SchduleList에서 관리하기 편한 형태로 데이터를 호출한다.
        const setTripSchedule = (data, title) => { 
            // data option 만들기
            let scheduleObj = {}; // 객체

            scheduleObj.scheduleArr = data; // 입력 자체 (원형 날짜 데이터 포맷 유지)
            scheduleObj.title = title; // 타이틀
            scheduleObj.period = calculateDaysBetweenDates(data[0], data[1]); // 몇일인지.
            scheduleObj.startDate =  convertDateFormat(data[0], 'YY.MM.DD'); // startDate (FORMAT YY.MM.DD)
            scheduleObj.endDate = convertDateFormat(data[1], 'YY.MM.DD'); // endDate (FORMAT YY.MM.DD)
            scheduleObj.scheduleList = getAllDatesBetween(data[0], data[1]);
            console.log("plan pinia : 내가 전체 등록한 일정셋");
            console.log(scheduleObj);


            tripScheduleInfo.value = scheduleObj; // 객체 넣기
        }

        // Drawer 에서 일정을 등록 할 시 스케줄을 추가한다.
        const addClickedAttractionInfo = (data) => {
            clickAttractionInfo.value = data;
            console.log("plan pinia : 현재 선택한 여행지 : " + data.title);
            console.log("확인해보기" , tripScheduleInfo.value);
            // plan 생성 시 만들어둔 객체에서 조회
            // 다시 테스트
            const currentDate = clickScheduleInfo.value;
            console.log("curretDAte - > List에서 가져옴" , currentDate);
            if (tripScheduleInfo.value.scheduleList.hasOwnProperty(currentDate)) {
                tripScheduleInfo.value.scheduleList[currentDate].push(clickAttractionInfo.value);
                console.log("ok");
            } else {
                // TODO :: error 페이지로 넘기기 -> 선택된 일정이 없다고,
                console.log("날짜를 확인해보시죠.")
                console.log("error");
            }

            console.log("test");
            console.log(tripScheduleInfo.value);
            

        }
        const setClickedActiveDate = (data) => {
            clickScheduleInfo.value = data;
            console.log("plan pinia : 현재 선택한 날짜 : " + data);
        }

        return {
            tripScheduleInfo,
            setTripSchedule,
            addClickedAttractionInfo,
            clickScheduleInfo,
            setClickedActiveDate,
            planKey,
        }
    },{
        persist : true          // stoarge : sessionStroate를 할 수도 있음
    } 
)
