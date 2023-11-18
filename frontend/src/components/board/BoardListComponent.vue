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
  <h1 id="page-title">컨텐츠</h1>
  <a-row class="card-container">
    <div v-for="card in cardList" :key="boardId" class="card">
      <img src="@\assets\img\trip.jpg" alt="Card Image" />
      <h2>
        <strong>{{ card.title }}</strong>
      </h2>
    </div>
  </a-row>
</template>

<style scoped>
.card-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between; /* 카드 사이의 간격을 조절합니다. 필요에 따라 조절하세요. */
  margin: 0 20rem; /*양쪽 여백 만들기*/
  max-width: 1200px; /* 부모 요소 크기를 더 키워서 한 행에 세개의 카드를 나오게 한다. */
  padding-left: 5rem;
}

.card {
  flex: 0 1 calc(33.33% - 1rem); /* 카드의 너비를 조절합니다. 100% / 5 = 20%, 여유 공간을 조절하기 위해 약간 작게 설정했습니다. */
  max-width: calc(33.33% - 1rem);
  margin: 0.5rem; /* 간격을 조절합니다. */
  border: 1px #ddd;
  border-radius: 1rem;
  padding: 1rem;
  text-align: center;
  box-sizing: border-box; /* 패딩 및 테두리를 박스 모델에 포함시킴으로써 겹치지 않도록 한다. */

  h2 {
    margin-top: 2rem;
  }
}

img {
  width: 100%; /* 이미지를 부모 요소에 맞추기 위해 100%로 설정 */
  width: 20rem;
  border-radius: 1rem;
  height: 16rem;
  /* height: auto; */
  max-height: 20rem;
  /* max-width: 13rem; */
  object-fit: fill; /*요소 콘텐츠 박스 크기에 맞춰 대체 콘텐츠의 크기를 조절합니다. 콘텐츠가 콘텐츠 박스를 가득 채웁니다. 서로의 가로세로비가 일치하지 않으면 콘텐츠가 늘어납니다. */
}

#page-title {
  text-align: center;
  margin-top: 1rem;
  padding-left: 2rem;
}

h2 {
  margin-top: 2rem;
}
</style>
