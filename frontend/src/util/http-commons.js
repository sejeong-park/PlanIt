import axios from "axios";

const {VITE_SERVER_URL, VITE_TRIP_INFO_KOR_API_URL} = import.meta.env;

// planit server
function planitAxios(){
    const instance = axios.create({
        baseURL : VITE_SERVER_URL,
        header : {
            "Content-Type" : "application/json;charset=utf-8",
        },
    });
    return instance;
}


// 한국 관광 교통 공사 API (추후 데이터 인입 하고, planit 서버에서 실행 할 예정)
function tripAttractionAxios(){
    const instance = axios.create({
        baseURL : VITE_TRIP_INFO_KOR_API_URL,
        header : {
            "Content-Type" : "application/json;charset=utf-8",
        },
    });
    return instance;
}

// axios 인스턴스 export
export {planitAxios, tripAttractionAxios};