<script setup>
import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const setSchedule = ref();
console.log(setSchedule)

// 플랜으로 이동
const router = useRouter()
const goTrip = function () { router.push({ name: 'trip' }) }


</script>
<template>
    <div>계획 짤거임</div>

    <div>
        <a-space direction="vertical" :size="12">
            <a-range-picker v-model:value="setSchedule" />
        </a-space>
    </div>
    <a-button type="primary" @click="goTrip">계획 세우러 가자</a-button>
</template>
<style scoped></style>