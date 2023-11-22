import { tripAttractionAxios, planitAxios } from "@/util/http-commons"; 
const {VITE_OPEN_API_SERVICE_KEY, VITE_MOBILE_OS, VITE_APPLICATION_NAME, VITE_GET_TYPE} = import.meta.env;

const local = planitAxios();
const tripAttraction = tripAttractionAxios();

// 공공 데이터 기본 param
const defaultParam = {
    MobileOS : VITE_MOBILE_OS,
    MobileApp : VITE_APPLICATION_NAME,
    _type : VITE_GET_TYPE,
    serviceKey : VITE_OPEN_API_SERVICE_KEY
}

// [임시] 관광 데이터 
// function listTripAttraction(param, success, fail){
//     const resultParam = { // 인스턴스 생성 부터 입력 받은 데이터
//         ...defaultParam,
//         ...param
//     } 
//     tripAttraction.get("/areaBasedList1", {params : resultParam})
//         .then(success)
//         .then(fail)
// }


// local로부터 search api
function listTripAttraction(param, success, fail) {
    local.get("/attractions/search", {params : param})
        .then(success)
        .then(fail)
}

// 여행 정보 가져오기
function listFestival(param, success, fail){
    const resultParam = {
        ...defaultParam,
        ...param
    }
    tripAttraction.get("/searchFestival1", {params : resultParam})
        .then(success)
        .then(fail)
}

export {
    listTripAttraction,
    listFestival
};