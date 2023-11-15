<script setup>
import { ref } from "vue";
const open = ref(false);
const showModal = () => {
  const confirmed = window.confirm("게시글로 등록하시겠습니까?");

  if (confirmed) {
    open.value = true;
  } else {
    alert("플랜이 저장되고, 저장된 플랜의 상세페이지로 이동합니다");
  }
};
const handleOk = (e) => {
  console.log(e);
  open.value = false;
};
</script>

<template>
  <div>
    <a-button type="primary" @click="showModal">저장하기</a-button>
    <a-modal v-model:open="open" title="Basic Modal" @ok="handleOk">
      <p>Some contents...</p>
      <p>Some contents...</p>
      <p>Some contents...</p>
    </a-modal>
  </div>
</template>
