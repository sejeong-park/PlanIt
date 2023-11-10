<script setup>

</script>
<template>
    <!--Plan에 대한 계획을 세우는 컴포넌트
        1. 여행 제목은 무엇인지 : 입력 폼 필요
        2. 언제부터 언제까지 가는지
        3. (추후 옵션) 누구랑 가는지
        4. (추후 옵션) 태그
        -->
    <a-row class="test">
        <a-col class="plan-title">
            여행 타이틀
        </a-col>
        <a-col class="plan-content">
            입력 냥ㅇ랑
        </a-col>
    </a-row>
</template>
<style scoped>
.plan-title {
    width: 100%;
    height: 10%;
    background-color: red;
}

.plan-content {
    width: 100%;
    height: 90%;
    background-color: blue;
}
</style>