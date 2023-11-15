<script setup>
import BoardModal from "@/components/modal/BoardModal.vue";
import { ref } from "vue";

const showModal = ref(false); // 초기에 모달을 보이게 함
</script>

<!-- ParentComponent.vue -->
<template>
  <div>
    <button @click="showModal = true">Open Modal</button>
    <board-modal v-if="showModal" @click="showModal = false"></board-modal>
  </div>
</template>
