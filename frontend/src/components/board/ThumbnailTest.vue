<script setup>
import { ref, onMounted } from "vue";

import axios from "axios";

const filename = "bd507a4b-d480-4ebd-b9cb-e0b6d0faa14a.jpg";
const imgUrl = ref("");
onMounted(() => {
  axios
    .get("http://localhost:/boards/display", {
      params: { filename },
      responseType: "arraybuffer", // Axios를 사용하여 서버에서 이미지를 가져옵니다. responseType: "arraybuffer"를 사용하여 이미지 데이터를 이진 데이터로 받습니다
    })
    .then((response) => {
      //받아온 이진 데이터를 base64로 인코딩하여 이미지 데이터를 Data URL로 변환합니다.
      const base64 = btoa(
        new Uint8Array(response.data).reduce(
          (data, byte) => data + String.fromCharCode(byte),
          ""
        )
      );
      imgUrl.value = "data:image/jpeg;base64," + base64;
      console.log(imgUrl.value);
    });
});
</script>

<template>
  <h1>썸네일 테스트</h1>
  <img :src="imgUrl" alt="" />
</template>

<style scoped></style>
