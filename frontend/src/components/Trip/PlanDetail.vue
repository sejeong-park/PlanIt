<!-- RIGTH BAR-->
<script setup>
import PlanInfo from '@/components/Trip/item/PlanInfo.vue';
import ScheduleList from '@/components/Trip/item/ScheduleList.vue';
import {usePlanStore} from '@/stores/plan';
import {onMounted, ref, defineEmits} from "vue";
import {CheckCircleOutlined} from '@ant-design/icons-vue';
import { getPlanInfo, makeTripSchedule } from "@/api/plan";

const planKey = ref();
const scheduleInfo = ref(); // 입력 받은 스케줄 객체 하위 컴포넌트로 넘기기

const planStore = usePlanStore();  // Store에서 관리

// 스케줄에 이용할 값 세팅
const setDataInfo = () => {
    getPlanInfo(
    planKey.value
    ,(response) => {
        planStore.setTripSchedule(response.data); // planStore에 데이터 저장
        scheduleInfo.value = planStore.tripScheduleInfo;
    }, (error) => {
        console.log("해당하는 Plan에 대한 정보가 없습니다.")/
        console.log(error);
    })
}

// 전체 Detail의 결과를 저장한다.
const emit = defineEmits(['save-plan'])
const savePlanTotal = () => {
    
    emit('save-plan');

    console.log("현재 저장 결과 :: ", planStore.tripScheduleInfo.scheduleList);

    const resultData = planStore.tripScheduleInfo.scheduleList;
    const scheduleDetailList = []
    Object.keys(resultData).forEach(date => {
        resultData[date].forEach((item, index) => {
            const detailResult = {
                "planDate" : date,
                "sequence" : index + 1,
                "title" : item.title
            }
            if ("contentid" in item) {
                //TODO :: attraction API 연결 후 detailResult.overview 추가 필요
                detailResult.attractionId = item.contentid; // 만약 Attraction 추가 정보라면, 넣기
                // detailResult.overview = item.descript~~
            }
            scheduleDetailList.push(detailResult);
        }) 
    })
    
    makeTripSchedule(planKey.value,
        scheduleDetailList,
        (response) => {
            console.log("저장 성공!!");
            console.log(response);
        },
        (error) => {
            console.log(error);
        })
}

onMounted(() => {
    planKey.value = planStore.planKey;
    setDataInfo(); // data전체 Info 가져오기
});

</script>
<template>
    <div class = "plan-detail-container">
        <!--여행 일정 정하기-->
        <a-col class="upper">
            <PlanInfo :scheduleInfo = "scheduleInfo"/>
        </a-col>
        <!--전체 스케줄 정하기-->
        <a-col class="main">
            <ScheduleList :scheduleInfo = "scheduleInfo" />
        </a-col>
        <!--하단의 저장 버튼 ( 별도로 분리 )-->
        <div class = "bottom">
            <a-col>
                <template #icon>
                    <CheckCircleOutlined />
                </template>
                <a-button class="save-btn" type="primary" shape="round" size="large"
                    style="width : 200px; height : 50px" @click = "savePlanTotal">
                    저장하기
                </a-button>
            </a-col>
        </div>
    </div>
</template>
<style scoped lang = "scss">

.plan-detail-container {
    height : 100%;
    width : 100%;
    display  :flex;
    flex-direction: column;

    .upper {
        /* 위치 정렬 */
        width: 100%;
        height: 20%;
        /* 백그라운드 이미지 */
        background-image : url('@/assets/img/info/daniel-cartin-LgHNehAIcOs-unsplash.jpeg');
        background-size : cover;
        background-position: center center;
        background-color : rgba(0,0,0,0.5);
        /* background-color: aquamarine; */
        display: flex;
        /* 텍스트 중앙 정렬 */
        justify-content: center;
        align-items: center;
        text-align: center;
        /*텍스트를 위한 여백 설정 */
        /* padding: 10%; */
    }

    .main {
        /*위치 정렬*/
        width: 100%;
        height: 70%;
        /* 중앙 정렬 */
        display: flex;
    }

    .bottom {
        /*위치 정렬 */
        width: 100%;
        height: 10%;
        padding: 1rem 1.5rem 2rem 1.5rem;
        /*상 하 (좌우) */
        /*중앙 정렬 */
        display: flex;
        justify-content: center;
        align-items: center;
    }


}


</style>