<script setup>
import { ref, watch } from "vue";
import {useRouter } from "vue-router";
import { usePlanStore } from "@/stores/plan";

const title = ref(""); // title
const titleError = ref(false);
const setSchedule = ref();
const planStore = usePlanStore();

watch(title, () => {
  // 확인 용
  console.log(title.value);
});

// 플랜으로 이동
const router = useRouter();
// 버튼을 눌렀을 때 pinia에 데이터를 세팅 해준다.
const goTrip = function () {
  // if (title.value == '' || !setSchedule.value) {
  //     titleError.value = true;
  // }else {
  planStore.setTripSchedule(setSchedule.value, title.value); // 스케줄 저장
  // TODO :: local에서 axios 요청으로 planKey 반환 받고, router가 trip이 아닌 {planKey} 로 이어져야 한다.
  router.push({ name: "trip" }); // trip으로 넘기기
};
</script>
<template>
  <div class="plan-total-container">
    <div class="plan-container">
      <div class="plan header">
        <h1 class="message">대충 당장 떠나자는 적당한 멘트</h1>
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
            <div class="goTripButton" @click="goTrip">
              <a-tooltip
                title="PlanIt 으로 일정을 설계하세요 !💫"
                color="#526AF2"
              >
                <template #title> </template>
                <img class="planit-button" src="@/assets/img/logo/planit.svg" />
              </a-tooltip>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<style lang="scss" scoped>
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
  color: var(--color-gray900);
}
:deep(.ant-input::placeholder) {
  padding: 0 1rem;
  font-size: x-large;
  color: var(--color-gray700);
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
  background-color: pink;
  border-radius: 50px;
  box-shadow: var(--shadow-inset);
  display: flex;
  align-items: center;
  justify-content: center;
  /* 배경 이미지 */
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
    /* background-color: red; */
    .message {
      height: 100%;
      margin-bottom: 0;

      /* 폰트 */
      font-family: "JalnanGothic";
      font-size: xx-large;
      color: var(--planit-dark);

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
