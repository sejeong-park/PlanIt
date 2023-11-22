<script setup>
import KakaoMap from '@/components/Trip/KakaoMap.vue';
import PlanResult from "@/components/Trip/PlanResult.vue";
import {ref, onMounted} from 'vue';
import {usePlanStore} from "@/stores/plan";
import { useRoute } from 'vue-router';
import {getPlanDetailList, getPlanInfo} from "@/api/plan"

// API를 로컬에서 get 받아와야 한다.!!! // 임시로 전 페이지에서 저장한 내역으로 테스트 한다.

const resultPlanInfo = ref(null);

const planStore = usePlanStore();
const route = useRoute();
const colorByDate = ref({});

function generateRandomDarkColor() {
    const r = Math.floor(Math.random() * 256);
    const g = Math.floor(Math.random() * 256);
    const b = Math.floor(Math.random() * 256);

    return `rgb(${r}, ${g}, ${b})`;
}

const generateColorByDate = () => {
    Object.keys(resultPlanInfo.value.scheduleList).forEach(date => {
        colorByDate.value[date] = generateRandomDarkColor();
    })
}


// 전체 데이터 결과 가져오기
const getResultSchedule = () => {
    
    const planKey = route.params.planKey;
    getPlanInfo(planKey,
        (response) => {
            planStore.setTripSchedule(response.data);
            resultPlanInfo.value = planStore.tripScheduleInfo; // 기본 PlanInfo 저장
        }
    )
    //TODO :: 이거 위에 resultPlanInfo 여기 있는 scheduleList 키에 넣어주기
    getPlanDetailList(planKey, 
        (response) => {
            // Date 별 그룹 화
            const groupByDate = response.data.reduce(
                (acc, item) => {
                    const date = item.planDate;
                    if (!acc[date]) acc[date] = []; // 날짜 없으면 생성
                    acc[date].push(item);
                    return acc;
                }, {});
            for (const date in groupByDate) {
                groupByDate[date].sort((a, b) => a.sequence - b.sequence);
            }
            console.log("결과", groupByDate);
        },(error) => {
            console.log(error);
        }
    )
}



onMounted(() => {
    // resultPlanInfo.value = planStore.tripScheduleInfo;
    // generateColorByDate();
    getResultSchedule()
});


</script>
<template>
    <!-- Trip View 페이지 -->
    <a-row class = "container">
        <a-col class = "right-side" :span="10">
            <PlanResult v-if= "resultPlanInfo" :totalData="resultPlanInfo" :colorDate = "colorByDate"/>
        </a-col>
        <a-col class = "left-side" :span="14">
            <KakaoMap :planResultData = "resultPlanInfo" :colorDate = "colorByDate"/>
        </a-col>
    </a-row>
</template>

<style scoped lang="scss">
.container {
    width : 100%;
    height : 100%;
    display : flex;
    flex-direction: row;
    
    .left-side {
        order : 1;
        height :100%;
    }
    .right-side {
        order : 2;
        right : 0;
        top : 0;
        height : 100%;
    }
}
</style>