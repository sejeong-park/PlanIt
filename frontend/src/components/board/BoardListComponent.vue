<script setup>
import { ref } from "vue";
import axios from "axios";

const cardList = ref([]);

axios.get("http://localhost:/boards/").then((response) => {
  //   console.log(response);
  response.data.forEach((card) => {
    cardList.value.push(card);
  });
});
</script>

<template>
  <div class="card-container">
    <div v-for="card in cardList" :key="boardId" class="card">
      <img src="@\assets\img\PlanIt!.png" alt="Card Image" />
      <h2>{{ card.title }}</h2>
    </div>
  </div>
</template>

<style scoped>
.card-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between; /* 카드 사이의 간격을 조절합니다. 필요에 따라 조절하세요. */
}

.card {
  flex: 0 1 18%; /* 카드의 너비를 조절합니다. 100% / 5 = 20%, 여유 공간을 조절하기 위해 약간 작게 설정했습니다. */
  max-width: 18%;
  margin: 0.5rem; /* 간격을 조절합니다. */
  border: 1px solid #ddd;
  border-radius: 1rem;
  padding: 1rem;
  text-align: center;
}

img {
  width: 100%;
  max-height: 10rem;
  object-fit: cover;
}

h2 {
  margin-top: 1rem;
}
</style>
