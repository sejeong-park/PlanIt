<script setup>
import { useRoute, useRouter } from 'vue-router';

// 플랜으로 이동
const router = useRouter()
const goPlan = function () { router.push({ name: 'plans' }) }

</script>

<template>
    <a-button>Log in</a-button>
    <a-button type="primary" @click="goPlan">Plan it</a-button>
</template>

<style scoped></style>
