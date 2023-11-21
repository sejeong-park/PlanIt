<script setup>
import {defineProps} from 'vue';

const props = defineProps({
    scheduleInfo : Object,
})
</script>
<template>
    <a-space direction="vertical" class = "trip-info">
        <a-col class = "info-user">{{ props.scheduleInfo?.createUser }}님의 특별한</a-col>
        <a-col class = "info-title">
            {{ props.scheduleInfo?.title || '나에게로 떠나는 여행'}}
            </a-col>
        <a-col class = "info-schedule">
            {{ props.scheduleInfo?.dateShortFormat[0] }} ~ {{ props.scheduleInfo?.dateShortFormat[1] }}
        </a-col>
        <a-col class = "info-period">
            {{ props.scheduleInfo?.period -1 }}박 {{ props.scheduleInfo?.period }}일
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
        color : var(--color-gray800);
        font-weight : 600;
        letter-spacing : 0.5px;
    }
    .info-title {
        width : 100%;
        font-size : 28px;
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