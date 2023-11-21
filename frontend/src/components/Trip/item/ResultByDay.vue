<script setup>
import {ref, defineProps, computed} from "vue";

const props = defineProps({
    dayData : Array,
    date : String, 
    index : Number
})

const formattedDate = computed(() => {
    const dateObj = new Date(props.date);
    const year = String(dateObj.getFullYear()).slice(2);
    const month = String(dateObj.getMonth() + 1).padStart(2, '0');
    const day = String(dateObj.getDate()).padStart(2, '0');
    const weekdays = ["일", "월", "화", "수", "목", "금", "토"];
    const weekday = weekdays[dateObj.getDay()]; // 요일을 얻습니다.
    
    return `${year}.${month}.${day} ${weekday}`;
})

</script>
<template>
    <div class = "day-container">
        <div class = "day">
            <span>{{index + 1}} 일차</span>&nbsp;
            ( {{ formattedDate }} )
        </div>
        <div v-for = "(item, index) in dayData" :key= "index" class = "day-schedule">
            <div class = "schedule-section">
                <div class = "schedule-index">
                    <div class = "vertical-line"></div>
                    <div class = "circle-index">{{ index + 1}}</div>
                </div>
                <div class = "schedule-card">
                    {{ item.title }}
                </div>
            </div>
        </div>

    </div>
</template>
<style scoped lang = "scss">
.day-container {
    width : 19rem;
    height : 100%;
    background-color: white;
    margin-right : 1rem;

    .day {
        width : 100%;
        height : 7%;
        justify-content: center;
        padding : 1rem;

        font-size : 18px;
        font-weight: bold;
        color : var(--color-gray800);

        span {
            font-size : 23px;
            color : var(--planit-dark);
        }

    }

    .day-schedule {
        width : 100%;
        height : auto;
        /* background-color: yellow; */
        padding : 0 0.5rem;
    }
}

.schedule-section {

    display : flex;
    flex-direction: row;
    
    .schedule-index {
        margin : 0 1rem;
        position: relative;

        .vertical-line {
            width : 2px;
            height : 100%;
            background-color : var(--color-gray300);
            position: absolute;
            left : 50%;
            transform: translateX(-50%); 
        }
        .circle-index {
            width :30px;
            height : 30px;
            line-height: 30px;
            border-radius : 50%;
            background-color: var(--planit-primary);
            color : white;
            text-align: center;
            font-weight: bold;
            position: absolute;
            top : 50%;
            left :50%;
            transform: translate(-50%, -50%);
            z-index : 1;
        }
    }

    .schedule-card {
        flex-grow : 1;
        min-height: 3em; /* 현재 폰트의 크기 배수 */
        background-color : #F2F4F6;
        border-radius: 10px;
        padding  : 1rem 1rem;
        margin : 1rem;
        line-height: 1.3;
        

        font-size : 16px;
        font-weight : 500;
        letter-spacing: 0.5px;
        box-shadow: 0 0 5px rgba(128, 128, 128, 0.5), 0 5px 10px rgba(128, 128, 128, 0.22);
        
    }
}
</style>