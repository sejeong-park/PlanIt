<script setup>
import { useUserStore } from "@/stores/user";
import { ref, onMounted } from "vue";
import axios from "axios";

const store = useUserStore();

// 게시글 정보를 담을 배열
const articles = ref([]);

const createUser = store.userId;

console.log(createUser);
onMounted(() => {
  axios
    .get("http://localhost:/boards/mypage", { params: { createUser } })
    .then((response) => {
      response.data.forEach((data) => {
        articles.value.push(data);
      });
      //   console.log(articles.value);
    });
});
</script>

<template>
  <!-- <h1>저의 게시글입니다.</h1> -->
  <a-row class="card-container">
    <div v-for="article in articles" :key="article.boardId" class="card">
      <div v-if="article.base64Image !== null">
        <router-link
          :to="{
            name: 'board-detail',
            params: { boardId: article.boardId },
            state: {
              boardId: article.boardId,
            },
          }"
          ><img
            :src="'data:image/jpeg;base64,' + article.base64Image"
            alt="Card Image"
            class="hover-opacity"
        /></router-link>
      </div>
      <div v-if="article.base64Image === null">
        <router-link
          :to="{
            name: 'board-detail',
            params: { boardId: article.boardId },
            state: {
              boardId: article.boardId,
            },
          }"
          ><img
            src="@\assets\img\trip.jpg"
            alt="Card Image"
            class="hover-opacity"
        /></router-link>
      </div>
      <router-link
        :to="{
          name: 'board-detail',
          params: { boardId: article.boardId },
          state: {
            boardId: article.boardId,
          },
        }"
        ><p>{{ article.title }}</p>
      </router-link>
    </div>
  </a-row>
</template>

<style lang="scss" scoped>
.card-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between; /* 카드 사이의 간격을 조절합니다. 필요에 따라 조절하세요. */
  margin: 0 20rem; /*양쪽 여백 만들기*/
  max-width: 1200px; /* 부모 요소 크기를 더 키워서 한 행에 세개의 카드를 나오게 한다. */
  padding-left: 5rem;

  p {
    font-weight: 600;
    font-size: 1.25rem;
    margin-top: 2rem;
  }
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
  text-align: left; // 제목을 좌측으로 정렬
}

img {
  width: 100%; /* 이미지를 부모 요소에 맞추기 위해 100%로 설정 */
  width: 20rem;
  border-radius: 5px;
  height: 16rem;
  /* height: auto; */
  max-height: 20rem;
  /* max-width: 13rem; */
  object-fit: fill; /*요소 콘텐츠 박스 크기에 맞춰 대체 콘텐츠의 크기를 조절합니다. 콘텐츠가 콘텐츠 박스를 가득 채웁니다. 서로의 가로세로비가 일치하지 않으면 콘텐츠가 늘어납니다. */
  // position: relative; /* 포지션을 상대적으로 지정?*/
  transition: opacity 0.3s ease; /*투명도 전환을 부드럽게 만들기 위한 트랜지션 속성 */
}

img.hover-opacity:hover {
  opacity: 0.6; /*마우스 호버 시 투명도를 0.8로 조정 */
}

#page-top {
  padding-left: 1rem;
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
}

h2 {
  margin-top: 1rem;
}

a {
  color: black;
}
</style>
