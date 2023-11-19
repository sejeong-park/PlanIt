<script setup>
import { computed } from "vue";

// 현재 페이지 정보와 전체 페이지 정보를 가져온다.
const props = defineProps({ currentPage: Number, totalPage: Number });

// 페이지 변경 이벤트
const emit = defineEmits(["pageChange"]);

// 네비게이션 사이즈
const navigationSize = parseInt(import.meta.env.VITE_ARTICLE_NAVIGATION_SIZE);

const startPage = computed(() => {
  return (
    parseInt((props.currentPage - 1) / navigationSize) * navigationSize + 1
  );
});

const endPage = computed(() => {
  let lastPage =
    parseInt((props.currentPage - 1) / navigationSize) * navigationSize +
    navigationSize;
  return props.totalPage < lastPage ? props.totalPage : lastPage;
});

const endRange = computed(() => {
  return (
    parseInt((props.totalPage - 1) / navigationSize) * navigationSize <
    props.currentPage
  );
});

function range(start, end) {
  const list = [];
  for (let i = start; i <= end; i++) list.push(i);
  return list;
  //   return Array(end - start + 1)
  //     .fill()
  //     .map((val, i) => start + i);
}

function onPageChange(pg) {
  console.log(pg + "로 이동!!!");
  emit("pageChange", pg);
}
</script>

<template>
  <div class="row">
    <ul class="pagination justify-content-center">
      <li class="page-item">
        <a class="page-link" @click="onPageChange(1)">처음</a>
      </li>
      <li class="page-item">
        <a
          class="page-link"
          @click="onPageChange(startPage == 1 ? 1 : startPage - 1)"
          >이전</a
        >
      </li>
      <template v-for="pg in range(startPage, endPage)" :key="pg">
        <li :class="currentPage === pg ? 'page-item active' : 'page-item'">
          <a class="page-link" @click="onPageChange(pg)">{{ pg }}</a>
        </li>
      </template>
      <li class="page-item">
        <a
          class="page-link"
          @click="onPageChange(endRange ? totalPage : endPage + 1)"
          >다음</a
        >
      </li>
      <li class="page-item">
        <a class="page-link" @click="onPageChange(totalPage)">마지막</a>
      </li>
    </ul>
  </div>
</template>

<style scoped>
.pagination-container {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}

.pagination {
  list-style: none;
  display: flex;
  justify-content: center; /* 페이지 네비게이션 버튼을 가로로 중앙 정렬합니다. */

  padding: 0;
}

.page-item {
  margin: 0 5px;
}

.page-link {
  color: gray;
  background-color: #fff;
  padding: 8px 16px;
  border-radius: 4px;
  text-decoration: none;
  transition: background-color 0.3s;
}

.page-link:hover {
  background-color: #e9ecef;
}

.page-item.active .page-link {
  background-color: white;
  color: #a791ff;
  border: 1px solid #a791ff;
}

.page-link,
.page-item.disabled .page-link {
  cursor: pointer;
  pointer-events: auto;
}

a {
  cursor: pointer;
}
</style>
