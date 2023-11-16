import {localAxios} from "@/util/http-commons";

const local = localAxios();

// ( Plan 버튼 실행 시 ) plan-key를 리턴 받는다.
function makePlanKey(success, fail){
    local.post("/plans").then(success).catch(fail);
}

// ( 여행 상세 일정 생성 )
function makeDetailSchedule(planKey, success, fail) {
    local.post("/plans/${planKey}").then(success).catch(fail);
}

export {
    makePlanKey,
    makeDetailSchedule
}