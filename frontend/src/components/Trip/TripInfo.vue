<script setup>

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
        <a-col clsss = "trip-perid" style = "width :19rem">
            2023.11.17 ~ 2023.11.24 (Day 6)
        </a-col>
        <a-col class = "trip-title" style = "width : 19rem;">
            <a-input 
                v-model:value="value" 
                size = "large" 
                placeholder="여행 일정 제목을 만들어주세요" 
                style = "height : 3rem;"/>
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
    .trip-period {

    }

}



</style>