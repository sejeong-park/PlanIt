import {planitAxios} from "@/util/http-commons";

const local = planitAxios();

// ( Plan 버튼 실행 시 ) plan-key를 리턴 받는다.
function makePlanKey(requestData, success, fail){
    local.post("/plans/", requestData).then(success).catch(fail);
}

// ( 여행 상세 일정 생성 )
function makeDetailSchedule(planKey, success, fail) {
    local.post("/plans/${planKey}").then(success).catch(fail);
}

export {
    makePlanKey,
    makeDetailSchedule
}