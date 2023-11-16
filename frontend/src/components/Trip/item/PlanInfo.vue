<script setup>
import {defineProps} from 'vue';


// PlanView로 넘어오면, /plan/의 결과를 일괄적으로 전송한다.
const props = defineProps({
    scheduleInfo : Object,
})



</script>
<template>
    <!--Plan에 대한 계획을 세우는 컴포넌트
        1. 여행 제목은 무엇인지 : 입력 폼 필요
        2. 언제부터 언제까지 가는지
        3. (추후 옵션) 누구랑 가는지
        4. (추후 옵션) 태그
        -->
    <a-space direction="vertical" class = "trip-info">
        <!--plan 화면에서 넘어온 기간 데이터 불러와야 한다.-->
        <!-- TODO :: userNickname 으로 변경  -->
        <a-col class = "info-user">당신의 특별한</a-col>
        <!-- TODO :: 만약 이름 없으면 이름 랜덤 생성기로 입력해주자 -->
        <a-col class = "info-title">
            {{ props.scheduleInfo?.title || '제목 미정'}}
            </a-col>
        <a-col class = "info-period">
            Day {{ props.scheduleInfo?.period }}
        </a-col>
        <a-col class = "info-schedule">
            {{ props.scheduleInfo?.startDate }} ~ {{ props.scheduleInfo?.endDate }}
        </a-col>
    </a-space>
        
</template>
<style scoped lang = "scss">

.trip-info{
    display : flex;
    /* 글씨가 더 잘보이기 위해 오버레이 */
    background-color : rgba(255,255,255,0.7); /*하얀 색 및 불투명도 */
    width : 100%;
    height : 100%;
    /* 부모 컴포넌트 조건과 맞춰주기 위한 padding */
    padding : 10%;
    justify-content: center;
    align-items: center;
    overflow-x : scroll;

    .info-user {
        font-size : 16px;
        color : var(--color-gray600);
        font-weight : 600;
        letter-spacing : 0.5px;
    }
    .info-title {
        width : 100%;
        font-size : 24px;
        /* 폰트 효과 */
        font-family : 'S-CoreDream-7ExtraBold';
        margin-bottom : 0.5rem;
        color : var(--planit-dark);
        font-weight: 500;
    }
    
    .info-period,
    .info-schedule {
        font-size : large;
        font-weight: 600;
        color : var(--color-gray700);
    }

}



</style>