<script setup>
import ResultByDay from "@/components/Trip/item/ResultByDay.vue";
import { defineProps } from "vue";
import { HomeOutlined } from "@ant-design/icons-vue";
const props = defineProps({
    totalData: Object,
    colorDate: Object,
});
// const data = props.totalData.scheduleList;
// console.log("data : ", data["2023-11-22"][0].planKey);
console.log("totalData : ", props.totalData.planKey);
// console.log("plan result 결과");
// console.log(props.totalData.scheduleList);
</script>
<template>
  <!--
        컴포넌트 : 저장 시 결과 리턴되는 곳
    -->
  <div class="plan-result-container">
    <div class="result-header">
      <div class="result-title">{{ totalData.title }}</div>
      <div class="result-period">
        {{ totalData.startDate }} ~ {{ totalData.endDate }} ({{ totalData.period - 1 }}박
        {{ totalData.period }}일)
      </div>
    </div>
    <div class="result-body">
      <div v-for="(item, date, index) in totalData?.scheduleList" :key="date">
        <ResultByDay :dayData="item" :date="date" :index="index" />
      </div>
    </div>
    <div class="result-footer">
      <a-button style="height: 50px" shape="round" size="large">
        <HomeOutlined />
      </a-button>
      <!-- <a-button type="primary" style = "width : 300px; height : 50px" shape = "round" size = "large">게시판에 내 일정 등록하기</a-button> -->
      <router-link :to="{ name: 'board-regist', query: { planKey: props.totalData.planKey } }"
        >게시글로 저장하기</router-link
      >
    </div>
  </div>
</template>
<style scoped>
.plan-result-container {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;

  .result-header {
    width: 100%;
    height: 10%;
    padding: 2rem 3rem;
  }

  .result-body {
    width: 100%;
    height: 80%;
    /* background-color : pink; */
    padding: 1rem 3rem 2rem 0;
    margin-left: 3rem;
  }

  .result-footer {
    width: 100%;
    height: 10%;
    display: flex;
    padding: 1rem 3rem 2rem 3rem;
    justify-content: end;
    align-items: center;
  }
}

.result-header {
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
  /* align-items: center; */
  justify-content: center;
  .result-title {
    color: var(--planit-primary);
    font-weight: 900;
    font-size: 32px;
    margin-bottom: 1rem;
  }
  .result-period {
    color: var(--color-gray800);
    font-weight: 700;
    font-size: 24px;
  }
}

.result-body {
  display: flex;
  height: 100%;
  overflow-x: scroll;
}
</style>
