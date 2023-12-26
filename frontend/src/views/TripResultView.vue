<script setup>
import KakaoMap from "@/components/Trip/KakaoMap.vue";
import PlanResult from "@/components/Trip/PlanResult.vue";
import { ref , onMounted} from "vue";
import { usePlanStore } from "@/stores/plan";
import { useRoute } from "vue-router";
import {planitAxios} from "@/util/http-commons";

// API를 로컬에서 get 받아와야 한다.!!! // 임시로 전 페이지에서 저장한 내역으로 테스트 한다.
const resultPlanInfo = ref();
const local = planitAxios();
const planStore = usePlanStore();
const route = useRoute();
const colorByDate = ref({});
const planKey = route.params.planKey;

// 하나의 반응형 객체로 리턴
async function getTotalData(){
  try {
    const planInfoResponse = await local.get(`/plans/${planKey}`);
    const planDetailResponse = await local.get(`/plans/list/${planKey}`);

    planStore.setTripSchedule(planInfoResponse.data);
    const planInfo = planStore.tripScheduleInfo;

    const planDetails = planDetailResponse.data;
    console.log("planDetailDAta!!! :: ", planDetails);
    // planDetails를 날짜별로 그룹화합니다.
    const scheduleInfo = planDetails.reduce((acc, item) => {
      const date = item.planDate;
      if (!acc[date]) acc[date] = [];
      acc[date].push(item);
      return acc;
    }, {});

    for (const date in scheduleInfo) {
      scheduleInfo[date].sort((a, b) => a.sequence - b.sequence);
    }

    planInfo.scheduleList = scheduleInfo;
    return planInfo;
  } catch(error ){
    console.log(error);
  }

}

onMounted(async() => {
  resultPlanInfo.value = await getTotalData();
});

</script>
<template>
  <!-- Trip View 페이지 -->
  <a-row class="container">
    <a-col class="right-side" :span="10">
      <PlanResult v-if="resultPlanInfo" :totalData="resultPlanInfo" :colorDate="colorByDate" />
    </a-col>
    <a-col class="left-side" :span="14">
      <KakaoMap :planResultData="resultPlanInfo" :colorDate="colorByDate" />
    </a-col>
  </a-row>
</template>

<style scoped lang="scss">
.container {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: row;

  .left-side {
    order: 1;
    height: 100%;
  }
  .right-side {
    order: 2;
    right: 0;
    top: 0;
    height: 100%;
  }
}
</style>
