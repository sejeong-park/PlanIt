<script setup>
import { useUserStore } from "@/stores/user";
import { ref, onMounted, computed } from "vue";
import axios from "axios";

const store = useUserStore();

const plans = ref([]);

const planDetails = ref([]);

const createUser = store.userId;

// 각 게시글에 대한 색상을 저장하는 배열
const postColors = ["#daf5f4", "#f1f5da", "#e3f5da"];

const dateList = ref([]);
const maxdate = ref("");

onMounted(() => {
  axios.get("http://localhost:/plans/mypage", { params: { createUser } }).then((response) => {
    // console.log(response);
    response.data.forEach((data) => {
      plans.value.push(data);
    });
  });
});

const isFileModalOpen = ref(false);

const openFileModal = async (planKey) => {
  isFileModalOpen.value = true;
  // console.log(planKey);
  // console.log("board : ", board.value);
  // 두번째 비동기 작업
  const planResponse = await axios.get("http://localhost:/plans/list/" + planKey);

  console.log("디테일 : ", planResponse.data);

  planDetails.value = planResponse.data;

  // planDetails 정렬
  planDetails.value.sort((a, b) => {
    // planDate 기준으로 오름차순 정렬
    if (a.planDate < b.planDate) return -1;
    if (a.planDate > b.planDate) return 1;

    // planDate가 같으면 sequence 기준으로 오름차순 정렬
    if (a.planDate === b.planDate) {
      if (a.sequence < b.sequence) return -1;
      if (a.sequence > b.sequence) return 1;
    }

    return 0; // planDate와 sequence가 같을 경우
  });

  maxdate.value = planDetails.value[0].planDate;
  dateList.value.push(maxdate.value);

  planDetails.value.forEach((plan) => {
    if (plan.planDate > maxdate.value) {
      maxdate.value = plan.planDate;
      dateList.value.push(maxdate.value);
    }
  });
};

const closeFileModal = () => {
  isFileModalOpen.value = false;
  // isModalOpen.value = false;
  planDetails.value = [];
  dateList.value = [];
  maxdate.value = 0;
};
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
        @click="openFileModal(plan.planKey)"
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
  <div v-if="isFileModalOpen" class="file-modal">
    <div class="modal-title">
      <h1 id="modal-title-id">해당 여행 계획 상세 일정!</h1>
    </div>
    <div class="container__Container-sc-5ea7eb67-0 itinerary__Stack-sc-681c5310-4 iLKpSA eAsDxC">
      <!-- 각 날짜에 대한 반복문 -->
      <div v-for="(date, dateIndex) in dateList" :key="dateIndex" class="day-container">
        <div class="day">
          <span>{{ date }}</span>
        </div>
        <div class="day-schedule">
          <!-- 각 날짜 및 시퀀스에 대한 반복문 -->
          <div
            v-for="plan in planDetails.filter((item) => item.planDate === date)"
            :key="plan.sequence"
            class="schedule-section"
          >
            <div class="schedule-index">
              <div class="circle-index">{{ plan.sequence }}</div>
              <div class="vertical-line"></div>
            </div>
            <div class="schedule-card">
              {{ plan.title }}
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="modal-btns">
      <button
        @click="closeFileModal"
        style="
          background-color: #d9534f;
          color: white;
          padding: 10px 20px;
          border: none;
          cursor: pointer;
          border-radius: 4px;
        "
        class="cancel-button"
      >
        닫기
      </button>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.jdWASn {
  font-family: "KBO-Dia-Gothic_bold";
  color: gray;
}
.ixIhnv {
  position: relative;
  margin: 2rem 37rem;
}

.iLKpSA {
  box-sizing: border-box;
  float: none;
  display: flex;
}

*,
::before,
::after {
  box-sizing: inherit;
  border-width: 0px;
  border-style: none;
}

body {
  margin: 0px;
  line-height: inherit;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}

.eZRrRb {
  box-sizing: border-box;
  overflow-wrap: break-word;
  color: rgb(54, 143, 255);
  float: none;
  font-weight: bold;
  white-space: pre-line;
  margin: 0px 0px 6px;
  font-size: 14px;
  line-height: 16.8px;
  letter-spacing: 0px;
}

.gYBzzu {
  box-sizing: border-box;
  overflow-wrap: break-word;
  color: rgb(58, 58, 58);
  float: none;
  font-weight: bold;
  white-space: pre-line;
  font-size: 18px;
  line-height: 21.6px;
  letter-spacing: 0px;
}

.hkqvVg {
  margin: 0px 20px 24px;
}

body {
  margin: 0px;
  line-height: inherit;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}

.gHsyoQ {
  margin: 10px 0px;
}

.iQHLcW {
  position: absolute;
  z-index: 2;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 0%, var(--color-white) 100%);
  width: 100%;
  height: 150px;
  bottom: 49px;
}

*,
::before,
::after {
  box-sizing: inherit;
  border-width: 0px;
  border-style: none;
}

.iDvhtl {
  margin-top: 5px;
  margin-bottom: 8px;
}

.jjciAh {
  padding: 16px 15px;
  flex: 1 1 0%;
}

.iSLaBf {
  border-radius: 6px;
  box-shadow: rgba(0, 0, 0, 0.07) 0px 0px 20px 0px;
}

*,
::before,
::after {
  box-sizing: inherit;
  border-width: 0px;
  border-style: none;
}

div {
  display: block;
}

a {
  color: inherit;
  text-decoration: inherit;
}

.fzwqRU {
  font-size: 11px;
  color: white;
  width: 24px;
  height: 24px;
  padding-top: 4px;
  border-radius: 100%;
  background-color: rgb(151, 95, 254);
  border: 2px solid var(--color-white);
}

.gJtAJZ {
  display: inline-block;
  font-weight: bold;
  text-align: center;
}

.detail-scroll {
  overflow-y: scroll;
  max-height: 100vh; /* 화면 높이에 맞게 조절 */
}
.day-container {
  width: 19rem;
  height: 100%;
  background-color: white;
  margin-right: 1rem;

  .day {
    width: 100%;
    height: 7%;
    justify-content: center;
    padding: 1rem;

    font-size: 18px;
    font-weight: bold;
    color: var(--color-gray800);

    span {
      font-size: 23px;
      color: var(--planit-dark);
    }
  }

  .day-schedule {
    width: 100%;
    height: auto;
    /* background-color: yellow; */
    padding: 0 0.5rem;
  }
}

.schedule-section {
  display: flex;
  flex-direction: row;

  .schedule-index {
    margin: 0 1rem;
    position: relative;

    .vertical-line {
      width: 2px;
      height: 100%;
      background-color: var(--color-gray300);
      position: absolute;
      left: 50%;
      transform: translateX(-50%);
    }
    .circle-index {
      width: 30px;
      height: 30px;
      line-height: 30px;
      border-radius: 50%;
      background-color: var(--planit-primary);
      color: white;
      text-align: center;
      font-weight: bold;
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      z-index: 1;
    }
  }

  .schedule-card {
    flex-grow: 1;
    min-height: 3em; /* 현재 폰트의 크기 배수 */
    background-color: #f2f4f6;
    border-radius: 10px;
    padding: 1rem 1rem;
    margin: 1rem;
    line-height: 1.3;

    font-size: 16px;
    font-weight: 500;
    letter-spacing: 0.5px;
    box-shadow: 0 0 5px rgba(128, 128, 128, 0.5), 0 5px 10px rgba(128, 128, 128, 0.22);
  }
}
.modal-btns {
  display: flex;
  justify-content: center;
}
.file-modal {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: white;
  padding: 20px;
  border: 1px solid #ccc;
  z-index: 2;
  border-radius: 1rem;
  min-width: 60rem;
  min-height: 40rem;
  max-height: 40rem;
  overflow-y: auto;

  .modal-title {
    text-align: center;
  }

  #modal-title-id {
    font-family: "KBO-Dia-Gothic_bold";
    font-weight: 600;
  }
}

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
  font-family: "KBO-Dia-Gothic_bold";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2304-2@1.0/KBO-Dia-Gothic_bold.woff")
    format("woff");
  font-weight: 700;
  font-style: normal;
}
@font-face {
  font-family: "EASTARJET-Medium";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_231029@1.1/EASTARJET-Medium.woff2")
    format("woff2");
  font-weight: 500;
  font-style: normal;
}
#title {
  font-family: "KBO-Dia-Gothic_bold";
  font-size: 1.5rem;
  font-weight: 700;
}
#date {
  font-family: "KBO-Dia-Gothic_bold";
  font-size: 1rem;
  font-weight: 500;
  color: gray;
}
</style>
