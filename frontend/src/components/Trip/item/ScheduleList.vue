<script setup>
import { ref, watch } from 'vue';
import {usePlanStore} from '@/stores/plan';
import {CaretRightOutlined, PlusOutlined, MinusCircleOutlined} from '@ant-design/icons-vue';

//  private int detailId;

// private Date planDate;
// private String planKey;
// private int sequence;
// private String title;

// private String content;

// private int attractionId;


// 목업 데이터
const DataTest = {
    scheduleInfo : {
        "2023-11-16" : [
            {
                "planDate" : "2023-11-16",
                "day" : 1,
                "planKey" : "dfksjfksjf",
                "title" : "남산 여행",
                "attractionId" : 1
            },
            {
                "planDate" : "2023-11-16",
                "day" : 1,
                "planKey" : "dfksjfksjf",
                "title" : "빠지가기",
                "attractionId" : 1
            },
            {
                "planDate" : "2023-11-16",
                "day" : 1,
                "planKey" : "dfksjfksjf",
                "title" : "집가고 싶음",
                "attractionId" : 1
            }
        ],
        "2023-11-17" : [
            {
                "planDate" : "2023-11-17",
                "day" : 2,
                "planKey" : "dfksjfksjf",
                "title" : "남산 여행",
                "attractionId" : 1
            }
        ],
        "2023-11-18" : {}
    }
}

// pinia에서 여행 계획 관리
const planStore = usePlanStore(); 


 // 현재 선택한 Date에 대해 담아주는 변수
const activeDate = ref(null);
const setActiveDate = (currentDate) => {
    activeDate.value = currentDate; // 현재 선택한 Date 넘기기
    console.log(activeDate.value + " 를 선택했습니다.");

    // pinia의 값에 넣기
    planStore.setClickedActiveDate(currentDate); // 현재 값 pinia 에 세팅한다.
}


const activeKey = ref(['1']);
const text = `A dog is a type of domesticated animal.Known for its loyalty and faithfulness,it can be found as a welcome guest in many households across the world.`;

const handleCollapseChange = (activeKey) => {
    // store.setCurrentCollapsePanel(activeKey)
    // console.log("activeKey 테스트")
    // console.log(activeKey);
}


// 사용자 직접 입력 태그 
const addDetail = () => {

}

const data = {

}

</script>

<template>
    <div class = "schedule-list">
        <div class = "collapse-container">

            <a-collapse
                class = "collapse"
                v-model:activeKey="activeKey"
                :bordered="false"
                @change = "handleCollapseChange"
            >

                <template #expandIcon="{ isActive }">
                    <CaretRightOutlined :rotate="isActive ? 90 : 0" height = "1rem"/>
                </template>


                <!--
                    클릭 시 해당일자에 대해 정보 추가
                -->
                <a-collapse-panel
                    class = "collapse-title" 
                    v-for="(schedule, date, index) in DataTest.scheduleInfo"
                    :class = "{'active-panel' : activeDate === date}"
                    :key="date" 
                    :header="`${date} (Day ${index + 1})`"
                    @click="setActiveDate(date)"
                >
                    <!-- 스케줄에 카드 정보 출력하기  -->
                    <div class = "card-list">
                        <!-- <div class="card"
                            v-for = "(scheduleDetail, date) in schedule"
                            :key="scheduleDetail"
                        >
                            <div class = "card-content">{{ scheduleDetail.title }}</div>
                        </div> -->


                        <div class="card"
                            v-for = "(scheduleDetail, detailIdx) in planStore.tripScheduleInfo.scheduleList[date]"
                            :key="detailIdx"
                        >
                            <div>{{ scheduleDetail.title }}</div>
                            <div><MinusCircleOutlined /></div>
                        </div>

                        <!--폼 추가를 위한 태그-->
                        <a-button type="dashed" style="width: 100%" @click="addDomain">
                                    <PlusOutlined />
                                    Add field
                        </a-button>
                    </div>
                </a-collapse-panel>

            </a-collapse>
        </div>
    </div>
</template>

<style scoped lang = "scss">

.schedule-list {
    display : flex ;
    flex-direction: column;
    width : 100%;
    height : 100%;
    /* 열림 닫힘 리스트를 안고 있는 컨테이너 */
    .collapse-container{
        width : 75%;
        height : 100%;
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
        color : var(--color-gray900)
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
        display : flex;
        align-items: center;

        /*detail Background에 관한 스타일 옵션 */
        background: white ;
        border-radius: 10px;
        border : 0;
        /* box-shadow: 0 1px 0 rgba(black, .2); */

        font-size : 16px;

        /* 카드 내용이 너무 길어졌을 때 생기는 옵션 변경하기 */
        overflow-x : scroll;
        white-space: nowrap; 
        /* 카드에 적는 내용 */
        .card-content {
            min-height : 3rem;
            align-items : center;
            display : flex;
        }
    }

.dynamic-delete-button {
    cursor: pointer;
    position: relative;
    top: 4px;
    font-size: 24px;
    color: #999;
    transition: all 0.3s;
}
.dynamic-delete-button:hover {
    color: #777;
}
.dynamic-delete-button[disabled] {
    cursor: not-allowed;
    opacity: 0.5;
}





/*그림자 지정 클래스 */
/* .shadow {
    -webkit-box-shadow: 0 10px 6px -6px #777;
        -moz-box-shadow: 0 10px 6px -6px #777;
            box-shadow: 0 10px 6px -6px #777;
} */
</style>
