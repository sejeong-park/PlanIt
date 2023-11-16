<script setup>
import { ref, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router'
import {usePlanStore} from '@/stores/plan';

// PlanView로 넘어오면, /plan/의 결과를 일괄적으로 전송한다.
const props = defineProps({
    scheduleInfo : Object
})


const title = ref(''); // title
const setSchedule = ref();
const planStore = usePlanStore(); 

watch(title, () => {
    // 확인 용
    console.log(title.value);
});

// 플랜으로 이동
const router = useRouter()
// 버튼을 눌렀을 때 pinia에 데이터를 세팅 해준다.
const goTrip = function () { 
    
    planStore.setTripSchedule(setSchedule.value, title.value); // 스케줄 저장
    // TODO :: local에서 axios 요청으로 planKey 반환 받고, router가 trip이 아닌 {planKey} 로 이어져야 한다.
    router.push({ name: 'trip' }); // trip으로 넘기기
}
</script>
<template>


    <div class = "plan-container">
        <div class = "plan header">
            <h1 class = "message">대충 당장 떠나자는 적당한 멘트</h1>
        </div>
        <div class = "plan body">
            <a-input class = "body-title" :bordered="false" size="large"  v-model:value.lazy="title" autofocus placeholder="당신의 근사한 여행에 이름을 붙여주세요" />
            <a-range-picker class = "body-datepicker" v-model:value="setSchedule" value-format="YYYY-MM-DD"/>
        </div>
        <div class = "plan footer" style = "justify-content: flex-end;">
            <a-button class = "goTripButton" type="primary" @click="goTrip">Plan It</a-button>
        </div>
    </div>
    
</template>
<style scoped>


/* ant-design datepicker 폼 너비 조절 */

/* date-picker 폼 자체를 꽉차게 변경 */
::v-deep .ant-picker{
    width : 100%;
    padding : 1rem 2rem; /* 날짜 입력 창 변경 */
    border-radius: 50px;
    font-size : x-large;
    
}

::v-deep .ant-picker .ant-picker-input >input {
    padding : 0 0.5rem;
    font-size : large;
    color : var(--color-gray900);
    padding : 0.5px;
}
::v-deep .ant-picker .ant-picker-input >input::placeholder {
    padding : 0 0.5rem;
    font-size : large;
    color : var(--color-gray700);
    padding : 0.5px;
}



/* input 크기 */
.body-title .ant-input { 
    font-size : x-large;
}

::v-deep .ant-input {
    padding : 0 1rem;
    font-size : x-large;
    color : var(--color-gray900);
}
::v-deep .ant-input::placeholder{
    padding : 0 1rem;
    font-size : x-large;
    color : var(--color-gray700);
}


/* .adropdownnt-picker- {
    width : 400px, !important;
} */

.plan-container {
    width :100%;
    height :100%;
    padding : 3rem 5rem 2rem 5rem; /* 패딩 상 우 하좌 */
    background-color: pink; /*var(--planit-base);*/
    border-radius: 50px;
    box-shadow : var(--shadow-inset);
    /* 세 요소에 전체 적용 */
    .plan {
        display : flex;
        align-items: center; /* 세로 중앙 정렬 (필요한 경우) */
        flex-direction: column;
        margin : 0 auto;

    }
    .header {
        width : 80%;
        height : 30%;
        margin-bottom: 1rem;
        .message {
            height : 100%;
            margin-bottom : 0;

            /* 폰트 */
            font-family: 'JalnanGothic';
            font-size : 32px;
            color : var(--planit-dark); 

            /* 글짜를 바닥에 붙이기 */
            display: flex;
            flex-direction: column;
            justify-content: flex-end; /* 컨테이너의 끝으로 아이템들을 정렬 */
        }
    }

    .body {
        display : flex;
        width : 60%; /* 여행 title & datepicker 길이 => 가로 길이*/
        height : 50%;
        display: flex;
        align-items : center;
        padding-bottom : 1rem;

        .body-title { 
            margin : 0.5rem 0;
            height: 50%;
        }
        .body-datepicker {
            margin : 0.5rem 0;
            height : 50%;
        }
    }

    .bottom {
        width : 5rem;
    }

}

</style>