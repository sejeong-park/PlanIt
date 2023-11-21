<script setup>
import { ref, reactive } from 'vue';
import {usePlanStore} from '@/stores/plan';
import {CaretRightOutlined, PlusOutlined, MinusCircleOutlined} from '@ant-design/icons-vue';

const planStore = usePlanStore(); // pinia에서 여행 계획 관리

const activeDate = ref(null);  // 현재 선택한 Date에 대해 담아주는 변수
const setActiveDate = (currentDate) => {
    activeDate.value = currentDate; // 현재 선택한 Date 넘기기
    // pinia의 값에 넣기
    planStore.setClickedActiveDate(currentDate); // 현재 값 pinia 에 세팅한다.
}

// 사용자 직접 입력 태그 
const userAddCards = reactive({});

// 메서드에서 스케줄 세부 정보 업데이트 
function updateScheduleDetail(uniqueKey, newTitle) {
    const [date, detailIdx] = uniqueKey.split(',');
    // PiniaStore에 새 제목을 업데이트 한다.
    if (planStore.tripScheduleInfo.scheduleList[date]) {
        planStore.tripScheduleInfo.scheduleList[date][detailIdx].title = newTitle;
        planStore.tripScheduleInfo.scheduleList[date][detailIdx].isNew = false; // 비 활성화 하기
    }
}

//  Add Schdule 추가했다.
const addUserSchedule = (date) => {
    // Add Schedule에서 추가한 카드 
    const newScheduleDetail = {
        title : '', // 기본 제목이 없는 새 일정
        isNew : true // 새 항목임을 나타나는 플래그
    }
    planStore.tripScheduleInfo.scheduleList[date].push(newScheduleDetail); 
    // 가장 최근 추가된 인덱스 관리
    const detailIdx = planStore.tripScheduleInfo.scheduleList[date].length - 1; // 배열 인덱스
    const uniqueKey = `${date} ${detailIdx}`;
    userAddCards[uniqueKey] = ''; //  카드 index 값 초기화
}

const removeSchedule = (date, detailIdx) => {
    console.log("사용자가 삭제 버튼을 눌렀습니다. + ", date , "idx : ", detailIdx)
    if (detailIdx !== -1) {
        // 배열의 index 값 삭제한다.
        planStore.tripScheduleInfo.scheduleList[date].splice(detailIdx, 1); // index 한개 삭제
    }

}

</script>

<template>
    <div class = "schedule-list">
        <div class = "collapse-container">

            <a-collapse
                class = "collapse"
                :bordered="false">

                <template #expandIcon="{ isActive }">
                    <CaretRightOutlined :rotate="isActive ? 90 : 0" height = "1rem"/>
                </template>


                <!--클릭 시 해당일자에 대해 정보 추가-->
                <a-collapse-panel
                    class = "collapse-title" 
                    v-for="(schedule, date, index) in planStore.tripScheduleInfo.scheduleList"
                    :class = "{'active-panel' : activeDate === date}"
                    :key="date" 
                    :header="`${date} (${index + 1}일차)`"
                    @click="setActiveDate(date)"
                >
                    <!-- 스케줄에 카드 정보 출력하기  -->
                    <div class = "card-list">
                        <div class="card"
                            v-for = "(scheduleDetail, detailIdx) in schedule"
                            :key="detailIdx"
                        >   
                            <div class = "card-imoji">
                                😋
                            </div>
                            <div class = "card-content" v-if = "scheduleDetail.isNew">
                                <div></div>
                                <a-input 
                                    class = "card-form"
                                    v-model:value.lazy="userAddCards[`${date},${detailIdx}`]"
                                    @input.lazy="updateScheduleDetail(`${date},${detailIdx}`, userAddCards[`${date},${detailIdx}`])"
                                    placeholder = "일정을 등록해주세요" 
                                    :bordered = "false">
                                </a-input>    
                            </div>
                            <div v-else class = "card-content">
                                <div>
                                    {{ scheduleDetail.title }}
                                </div>
                            </div>
                            <div class = "card-remove-btn">
                                <MinusCircleOutlined class = "dynamic-delete-button" @click = "removeSchedule(date, detailIdx)"/>
                            </div>
                        </div>

                        <!--폼 추가를 위한 태그-->
                        <a-button class = "add-btn" style = "width : 100%;" type="dashed" @click="addUserSchedule(date)">
                            <PlusOutlined />Add Schedule
                        </a-button>
                    </div>
                </a-collapse-panel>
            </a-collapse>
        </div>
    </div>
</template>

<style scoped lang = "scss">

:deep(.ant-input) {
    padding : 0;
    height : 100%;
    font-size : 17px;
    color : var(--planit-primary);
}

.schedule-list {
    display : flex ;
    flex-direction: column;
    width : 100%;
    /* height : 100%; */
    /* 열림 닫힘 리스트를 안고 있는 컨테이너 */
    .collapse-container{
        width : 75%;
        margin : 3rem auto 1rem auto;
        /* margin: 3rem 3rem 1rem 3rem; */
        overflow-y : scroll; /*만약 날짜가 너무 길어질 경우 스크롤로 대체한다. */
    }
    .collapse-container::-webkit-scrollbar {
        display : none; 
    }
}

/* 토글 버튼에 해당 하는 스타일 */
.collapse {
    width  : 100%;
    background: rgb(255, 255, 255);
    border-radius: 10px;
    /* 기간 내용 */
    .collapse-title{
        background-color: var(--planit-base);
        border-radius: 5px;
        margin-bottom : 24px;
        border : 0;
        overflow : hidden;
        /* font 내용 */
        font-size : 18px;
        font-weight: 500;
        color : var(--color-gray900);
        box-shadow: 0 0 5px rgba(128, 128, 128, 0.22), 0 3px 8px rgba(128, 128, 128, 0.18);
    }
    /* activeDate 기준으로 panel이 열린다. */
    .active-panel {
        background-color: var(--planit-pink);
    }
}

.card {
        padding: 0.5rem 1rem;
        margin-bottom : 1rem;
        height : 3rem;
        width : 100%;
        display : flex;
        align-items: center;

        /*detail Background에 관한 스타일 옵션 */
        background: white ;
        border-radius: 10px;
        border : 0;
        width : 100%;

        font-size : 17px;
        letter-spacing: 0.5px;

        white-space: nowrap; 
        box-shadow: 0 0 5px rgba(128, 128, 128, 0.25), 0 5px 10px rgba(128, 128, 128, 0.18);
        /* 카드에 적는 내용 */
        .card-imoji  {
            width : 10%;
            /* min-height : 3rem; */
            align-items : center;
        }
        .card-content {
            width : 80%;
            min-height : 3rem;
            margin-right: 6px;
            align-items : center;
            display : flex;
            overflow-x : scroll; /* 카드 내용이 너무 길어졌을 때 생기는 옵션 변경하기 */
        }
        .card-remove-btn{
            width : 10%;
            align-items: center;
        }

        .add-btn {
            box-shadow: 0 0 5px rgba(128, 128, 128, 0.20), 0 5px 10px rgba(128, 128, 128, 0.18);
        }
    }


/* 삭제 버튼 */
.dynamic-delete-button {
    cursor: pointer;
    position: relative;
    /* top: 4px; */
    font-size: 24px;
    color: #999;
    transition: all 0.3s;
    top : 2px;
    text-align: center;
}
.dynamic-delete-button:hover {
    color: red;
}
.dynamic-delete-button[disabled] {
    cursor: not-allowed;
    opacity: 0.5;
}

</style>
