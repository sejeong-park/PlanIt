<script setup>
import { ref } from "vue";

const activeTab = ref("board");

const changeTab = (tabName) => {
  activeTab.value = tabName;
};
</script>

<template>
  <div class="container">
    <div class="tab-container">
      <div
        :class="{ 'active-tab': activeTab === 'board' }"
        id="board-tab"
        @click="changeTab('board')"
        style="margin-left: 10rem"
      >
        <span class="tab-text">게시판</span>
      </div>
      <div
        :class="{ 'active-tab': activeTab === 'travel' }"
        id="travel-tab"
        @click="changeTab('travel')"
        style="margin-left: 15rem"
      >
        <span class="tab-text">여행 계획</span>
      </div>
    </div>
    <div class="content" v-if="activeTab === 'board'">
      <!-- 게시판 컨텐츠 내용 -->
      <p>게시판 내용이 여기에 들어갑니다.</p>
    </div>

    <div class="content" v-if="activeTab === 'travel'">
      <!-- 여행 계획 컨텐츠 내용 -->
      <p>여행 계획 내용이 여기에 들어갑니다.</p>
    </div>
  </div>
</template>

<style lang="scss" scoped>
body {
  font-family: Arial, sans-serif;
  margin: 0;
}
.container {
  margin: 2rem 35rem;
  min-width: 50rem;
}
.tab-container {
  position: relative;
  display: flex;
  border-bottom: 1px solid var(--color-gray50);
  max-width: 50rem;
}

.active-tab {
  //   background-color: #3498db; /* 파란색으로 변경 */
  border-bottom: 1px solid blue;
  color: black;
  font-weight: 700;
}

.tab {
  cursor: pointer;
  padding: 10px 20px;
  margin-right: 10px;
  transition: background-color 0.3s;
}

.tab:hover {
  background-color: #ddd;
}

.tab-text {
  font-weight: bold;
  display: flex;
  flex: 1 1 0%;
  padding: 1.5rem;
  cursor: pointer;
}

.content {
  padding: 20px;
  border-style: none;
}

@media (max-width: 600px) {
  .tab-container {
    flex-direction: column;
  }
}
</style>
