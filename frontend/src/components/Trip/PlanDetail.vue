<!-- RIGTH BAR-->
<script setup>
import PlanInfo from '@/components/Trip/item/PlanInfo.vue';
import ScheduleList from '@/components/Trip/item/ScheduleList.vue';
import {usePlanStore} from '@/stores/plan';
import {onMounted, ref} from "vue";

const scheduleInfo = ref(); // 입력 받은 스케줄 객체 하위 컴포넌트로 넘기기

// Plan 일정세우기
const planStore = usePlanStore(); 
const getScheduleDate = () => {
    scheduleInfo.value = planStore.tripScheduleInfo;
}

onMounted(() => {
    getScheduleDate();
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
                    style="width : 200px; height : 50px">저장하기</a-button>
            </a-col>
        </div>
    </div>
</template>
<style scoped lang = "scss">

.plan-detail-container {
    height : 100vh;
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