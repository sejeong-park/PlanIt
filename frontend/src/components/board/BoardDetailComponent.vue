<script setup>
import { ref, onMounted } from "vue";

const { board } = history.state; // 이렇게 받는다.
const boardId = ref(0);
const planKey = ref("");
const title = ref("");
const createAt = ref("");
const hits = ref("");
const contents = ref("");

onMounted(() => {
  // console.log(board);
  (boardId.value = board.boardId),
    (planKey.value = board.planKey),
    (title.value = board.title),
    (createAt.value = board.createAt),
    (hits.value = board.hits),
    (contents.value = board.contents);
});
// console.log(history.state);
</script>

<template>
  {{ boardId }}
  {{ title }}
  {{ contents }}
  {{ hits }}
  {{ createAt }}
  {{ planKey }}
  <!-- {{ board.boardId }}
  {{ board.title }} -->
</template>

<style scoped></style>
