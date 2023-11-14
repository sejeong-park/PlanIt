<script setup>
import { inject } from "vue";

// inject 함수는 두 개의 매개변수를 받을 수 있습니다. 첫 번째 매개변수는 주입받을 키(key)이며, 두 번째 매개변수는 주입되지 않을 경우 사용될 기본값(default value)입니다.
const boardId = inject("boardId");
console.log("boardId", boardId);
</script>

<template>
  <h1>{{ boardId }}</h1>
</template>

<style scoped></style>
