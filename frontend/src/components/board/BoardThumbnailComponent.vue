<script setup>
import { ref, onMounted } from "vue";

const boardId = ref(null);

onMounted(() => {
  console.log("RoardId : ", boardId.value);
});

const props = defineProps(["response"]);

watch(
  () => props.response,
  (newValue) => {
    boardId.value = newValue;
  }
);
</script>

<template></template>

<style scoped></style>
