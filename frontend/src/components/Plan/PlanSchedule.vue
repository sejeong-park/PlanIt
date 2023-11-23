<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { makePlanKey } from "@/api/plan";
import { useUserStore } from "@/stores/user";
import { usePlanStore } from "@/stores/plan";
import { message } from 'ant-design-vue';

const router = useRouter();
const userStore = useUserStore();
const planStore = usePlanStore();

const title = ref(""); // title
const setSchedule = ref(null);
const makeMyPlan = () => {
  
  // 전체 일정 등록 Dto
  const planRegistDto = {
    title : title.value,
    startDate : setSchedule.value[0],
    endDate : setSchedule.value[1],
    createUser : userStore.userId
  };

  // POST API 요청
  makePlanKey(
  planRegistDto
  , (response) => {
    planStore.planKey = response.data; // pinia에 저장
    router.push({name : 'planning'}); // 결과 응답 받았을 경우, planning 페이지로 이동
  } , (error) => {
    if (error){
      console.log("에러임")
      console.log(error); 
    }
  });
}

// Plan button 클릭 시 페이지 전환
const makePlanIt = function () {

  if (title.value.trim() === '') {
    message.error('이번 여행을 공유할 제목을 지정해주세요');
    return;
  }
  if (setSchedule.value == null) {
    message.error('여행을 떠날 날짜를 입력해주세요.');
    return;
  }
  makeMyPlan(); // plan 생성
};
</script>
<template>
  <div class="plan-total-container">
    <div class="plan-container">
      <div class="plan header">
        <h1 class="message">완벽한 여행을 위한 우주적 계획</h1>
      </div>
      <div class="plan body">
        <a-input
          class="body-title"
          :bordered="false"
          size="large"
          v-model:value.lazy="title"
          autofocus
          placeholder="당신의 근사한 여행에 이름을 붙여주세요"
        />
      </div>
      <div class="plan bottom">
        <div class="body-input">
          <!-- 계획 입력 버튼 -->
          <div class="date-section">
            <a-range-picker
              class="body-datepicker"
              v-model:value="setSchedule"
              value-format="YYYY-MM-DD"
            />
          </div>
          <!-- 이동 버튼 !! -->
          <div class="move-section">
            <div class="goTripButton" @click="makePlanIt">
              <a-tooltip
                title="PlanIt 으로 일정을 설계하세요 !💫"
                color="#526AF2"
              >
                <template #title> </template>
                <img class="planit-button" src="@/assets/img/logo/planit.svg" alt = "plait 로고 버튼"/>
              </a-tooltip>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped lang="scss">
/* ant-design datepicker 폼 너비 조절 */
/* date-picker 폼 자체를 꽉차게 변경 */
:deep(.ant-picker) {
  width: 100%;
  padding: 0.5rem 2rem; /* 날짜 입력 창 변경 */
  border-radius: 30px;
  font-size: x-large;
  /* 기본 테두리 변경 */
  border: 2px solid var(--planit-primary);
}

:deep(.ant-picker .ant-picker-input > input) {
  padding: 1rem 1rem;
  font-size: large;
  color: var(--planit-primary);
  font-weight: border;
}

:deep(.ant-picker .ant-picker-input > input:focus) {
  padding: 1rem 1rem; /* input 값 변경에 따른 포커싱 이동 */
}

:deep(.ant-picker .ant-picker-input > input::placeholder) {
  padding: 0 0.5rem;
  font-size: large;
  color: var(--color-gray700);
  padding: 0.5px;
}

/* SVG 아이콘의 크기를 조절 */
/* 또는 텍스트라면 폰트 크기를 조절 */
:deep(.ant-picker-separator) {
  width: 100%;
  height: 100%;
  padding: 0;
  font-size: 16px; /* 원하는 크기로 조절 */
}

:deep(.ant-input-borderless) {
  text-align: center;
}

/* Title AntD custom */
.body-title .ant-input {
  font-size: x-large;
}

:deep(.ant-input) {
  padding: 0 1rem;
  font-size: x-large;
  color: #EAEDF6;
}
:deep(.ant-input::placeholder) {
  padding: 0 1rem;
  font-size: x-large;
  color: var(--color-brightGray);
}

:deep(.ant-picker-dropdown) {
  width: auto;
}

:deep(.ant-picker-panel) {
  width: 100%;
}
/*전체 섹션에 대한 정보 */
.plan-total-container {
  width: 100%;
  height: 100%;
  border-radius: 50px;
  box-shadow: var(--shadow-inset);
  display: flex;
  align-items: center;
  justify-content: center;
  /* 배경 이미지 */
  background-image: url('/src/assets/img/etc/space-background-realistic-starry-night-cosmos-shining-stars-milky-way-stardust-color-galaxy.jpeg');
  background-size: cover; /* 이미지 크기를 div에 맞춤 */
}

.plan-container {
  width: 80%;
  height: 90%;
  display: flex;
  align-items: center; /* 세로 중앙 정렬 (필요한 경우) */
  flex-direction: column;
  .header {
    display: flex;
    align-items: center; /* 세로 중앙 정렬 (필요한 경우) */
    flex-direction: column;
    width: 100%;
    height: 40%;
    margin-top: 1rem;
    padding-bottom : 0.5rem;
    /* background-color: red; */
    .message {
      height: 100%;
      margin-bottom: 0;

      /* 폰트 */
      font-family: "JalnanGothic";
      font-size: xx-large;
      color: white;

      /* 글짜를 바닥에 붙이기 */
      display: flex;
      flex-direction: column;
      justify-content: flex-end; /* 컨테이너의 끝으로 아이템들을 정렬 */
    }
  }

  .body {
    display: flex;
    align-items: center;
    width: 100%;
    height: 20%;
    /* background-color: blue; */
    .body-title {
      margin: 0.5rem 0;
    }
  }
  .bottom {
    display: flex;
    width: 100%;
    height: 40%;
  }
}

.body-input {
  width: 100%;
  height: 80%;
  display: flex;
  justify-content: space-between;

  .date-section {
    margin-left: 10%;
    flex-grow: 1;
    display: flex;
    justify-content: center;
    align-items: center;
    padding-right: 7%;

    .body-datepicker {
      margin: 0.5rem 0;
      height: 50%;
    }
  }

  .move-section {
    display: flex;
    width: 10rem;
    justify-content: center;
    align-items: center;
    margin-bottom: 1rem;
  }
}

.planit-button {
  /* 상대적인 이미지 사이즈로 변경 */
  max-width: 100%;
  min-height: 100%;
  width: auto;
  height: auto;

  filter: drop-shadow(0px 14px 15px var(--color-gray900));
  transform: scale(1); /* 이미지 확대 */
  transition: transform 0.5s; /* 시간 설정 */
}
.planit-button:hover {
  filter: drop-shadow(0px 14px 15px var(--color-gray900));
  transform: scale(1.1); /* 이미지 확대 */
  transition: transform 0.3s; /* 시간 설정 */
}
</style>
