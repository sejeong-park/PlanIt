<script setup>
import { ref } from "vue";

const showModal = ref(true); // 초기에 모달을 보이게 함

const closeModal = function () {
  showModal.value = false;
};
</script>

<!-- ModalComponent.vue -->
<template>
  <div v-if="showModal" class="modal">
    <div class="modal-content">
      <!-- 모달 내용 -->

      <p>This is a modal!</p>
      <button @click="closeModal">Close Modal</button>
    </div>
  </div>
</template>

<style>
/* 모달 스타일 */
.modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal-content {
  background: white;
  padding: 20px;
  border-radius: 5px;
}
</style>
