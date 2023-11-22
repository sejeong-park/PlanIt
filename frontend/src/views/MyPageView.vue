<script setup>
import { ref } from "vue";
import MyBoardComponent from "@/components/mypage/MyBoardComponent.vue";
import MyPlanComponent from "@/components/mypage/MyPlanComponent.vue";

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
        id="tab"
        @click="changeTab('board')"
        style="margin-left: 10.7rem"
      >
        <span class="tab-text">게시판</span>
      </div>
      <div
        :class="{ 'active-tab': activeTab === 'travel' }"
        id="tab"
        @click="changeTab('travel')"
        style="margin-left: 15rem"
      >
        <span class="tab-text">여행 계획</span>
      </div>
    </div>
    <div class="content" v-if="activeTab === 'board'">
      <MyBoardComponent />
    </div>

    <div class="content" v-if="activeTab === 'travel'">
      <MyPlanComponent />
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
  transition: border-bottom 0.3s; /* 실선의 이동에 대한 애니메이션을 추가 */
}

.active-tab {
  border-bottom: 1px solid blue;
  color: black;
  font-weight: 700;
}

#tab {
  cursor: pointer;
  padding: 10px 20px;
  margin-right: 10px;
  transition: background-color 0.3s;
  font-weight: 400;
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
</style>
