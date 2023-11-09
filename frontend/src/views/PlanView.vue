<script setup>
import { ref } from 'vue';
const setSchedule = ref();
console.log(setSchedule)
</script>
<template>
    <div>계획 짤거임</div>

    <div>
        <a-space direction="vertical" :size="12">
            <a-range-picker v-model:value="setSchedule" />
        </a-space>
    </div>
    <a-button type="primary">계획 세우러 가자</a-button>
</template>
<style scoped></style>