<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";

import VPageNavigation from "@/components/common/VPageNavigation.vue";

// 게시글 정보를 담을 배열
const articles = ref([]);

// 현재 페이지 정보(1부터 시작)
const currentPage = ref(1);

// 전체 페이지
const totalPage = ref(0);

// .env에서 설정한 게시글 리스트 사이즈
const { VITE_ARTICLE_LIST_SIZE } = import.meta.env;
// console.log("VITE :" ,VITE_ARTICLE_LIST_SIZE)
// 페이징 정보를 넘겨줄 param
const param = ref({
  pgno: currentPage.value.toString(),
  spp: VITE_ARTICLE_LIST_SIZE.toString(),
});

onMounted(() => {
  getArticleList();
});
// 화면이 띄어지자마자 바로 게시글들을 보여주기 위해
// onMounted(() => {
//   getArticleList();
// });

// 서버에서 아티클 정보를 가져온다.
// const getArticleList = () => {
//   console.log("서버에서 글목록 얻어오자!!", param.value);
//   listArticles(
//     param.value,
//     ({ data }) => {
//       articles.value = data.articles;
//     },
//     (error) => {
//       console.log(error);
//     }
//   );
// };

// const getArticleList = () => {
//   // console.log("getArticleList 호출!", param.value);
//   axios.get("http://localhost:/boards/", param.value).then((response) => {
//     console.log(response.data);
//     response.data.articles.forEach((data) => {
//       // console.log("data:", data);
//       articles.value.push(data);
//     });
//     currentPage.value = response.data.currentPage;
//     totalPage.value = response.data.totalPage;
//   });
// };

const getArticleList = () => {
  axios
    .get("http://localhost:/boards/", {
      params: param.value,
    })
    .then((response) => {
      console.log(response.data);
      // 새로운 데이터를 받아올 때마다 articles 배열을 초기화
      articles.value = response.data.articles;
      currentPage.value = response.data.currentPage;
      totalPage.value = response.data.totalPageCount;
    });
};
// 페이지 이동할 때 사용되는 메서드
const onPageChange = (val) => {
  console.log(val + "번 페이지로 이동 준비 끝!!!");
  // 클릭한 페이지 정보를 다시 받고
  currentPage.value = val;
  param.value.pgno = val;
  //여기서 getArticle을 다시 호출하는구나?
  getArticleList();
};
</script>

<template>
  <h1 id="page-title">컨텐츠</h1>
  <a-row class="card-container">
    <div v-for="article in articles" :key="article.boardId" class="card">
      <img src="@\assets\img\trip.jpg" alt="Card Image" />
      <h2>
        <strong>{{ article.title }}</strong>
      </h2>
    </div>
  </a-row>
  <VPageNavigation
    :current-page="currentPage"
    :total-page="totalPage"
    @pageChange="onPageChange"
  ></VPageNavigation>
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
