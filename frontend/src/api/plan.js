import {planitAxios} from "@/util/http-commons";

const local = planitAxios();

// plan-key를 리턴 받는다.
function makePlanKey(requestData, success, fail){
    local.post("/plans/", requestData).then(success).catch(fail);
}

// 생성한 plan 에 대해 받는다.
function getPlanInfo(planKey, success, fail) {
    local.get(`/plans/${planKey}`).then(success).catch(fail);
}

// 전체 일정에 대한 디테일 정보
function getPlanDetailList(planKey, success, fail) {
    local.get(`/plans/list/${planKey}`).then(success).catch(fail);
}

// TODO :: API 생성이 안된다.
function makeTripSchedule(planKey, success, fail) {
    local.post(`/plans/${planKey}`).then(success).catch(fail);
}



export {
    makePlanKey,
    getPlanInfo,
    getPlanDetailList,
    makeTripSchedule
}