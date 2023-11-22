<script setup>
import { useUserStore } from "@/stores/user";
import { ref, onMounted } from "vue";
import axios from "axios";

const store = useUserStore();

const plans = ref([]);

const createUser = store.userId;

// 각 게시글에 대한 색상을 저장하는 배열
const postColors = ["#daf5f4", "#f1f5da", "#e3f5da"];

onMounted(() => {
  axios.get("http://localhost:/plans/mypage", { params: { createUser } }).then((response) => {
    response.data.forEach((data) => {
      plans.value.push(data);
    });
  });
});
</script>

<template>
  <div v-show="plans.length === 0">
    <h1>작성하신 여행 계획이 없습니다!</h1>
  </div>
  <div v-show="plans.length !== 0" class="full-container">
    <div v-for="(plan, index) in plans" :key="plan.planKey" class="card">
      <div
        class="card-container"
        :style="{ backgroundColor: postColors[index % postColors.length] }"
      >
        <div class="card-p">
          <p id="title">{{ plan.title }}</p>
          <p id="date">{{ plan.startDate }} ~ {{ plan.endDate }}</p>
        </div>
        <div>
          <img src="@\assets\img\Planit.png" alt="logo" class="logo-img" />
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.full-container {
  display: flex;
  flex-wrap: wrap; /* flex 아이템을 여러 줄에 걸쳐 정렬하도록 설정 */
  overflow-y: auto;
  max-height: 45rem;
  padding-right: 10px; /* 스크롤바 너비를 고려한 우측 여백 추가 */
  min-width: 50rem;
}
.card-container {
  background-color: #daf5f4;
  width: 45rem;
  height: 8rem;
  border-radius: 2rem;
  justify-content: space-between; /* 자식 요소를 양쪽 끝으로 분배 */
  cursor: pointer; /* 마우스를 호버했을 때 커서를 pointer로 변경 */
  display: flex;
  transition: transform 0.2s; /* 변화에 대한 애니메이션 효과 추가 */
  margin-left: 2rem;
}

.card-container:hover {
  transform: scale(1.02); /* 호버 시에 크기를 1.05배로 조절 */
}
.logo-img {
  width: 7rem;
  margin-top: 1.55rem;
  align-items: center; /* 로고를 수직 중앙으로 정렬 */
  margin-right: 3rem;
}
.card {
  display: block;
  margin-top: 1rem;

  .card-p {
    margin-left: 2rem;
    padding: 1.7rem;
  }
}
@font-face {
  font-family: "EASTARJET-Medium";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_231029@1.1/EASTARJET-Medium.woff2")
    format("woff2");
  font-weight: 500;
  font-style: normal;
}
#title {
  font-family: "EASTARJET-Medium";
  font-size: 1.5rem;
  font-weight: 700;
}
#date {
  font-family: "EASTARJET-Medium";
  font-size: 1rem;
  font-weight: 500;
}
</style>
