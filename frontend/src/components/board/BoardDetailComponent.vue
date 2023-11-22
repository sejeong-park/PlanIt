<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";

import { useRouter } from "vue-router";

const router = useRouter();

// const { board } = history.state; // 이렇게 받는다.
const { boardId } = history.state;
// const boardId = ref(0);

const board = ref({});

const planDetails = ref([]);

const updateTitle = ref("");
const updateContents = ref("");

const dateList = ref([]);
const maxdate = ref("");

const updateContent = (event) => {
  updateContents.value = event.target.innerText;
  console.log(updateContents.value);
  console.log(updateTitle.value);
};

onMounted(async () => {
  try {
    const boardResponse = await axios.get(
      "http://localhost:/boards/" + boardId
    );

    // 첫번째 비동기 작업
    board.value = {
      boardId: boardResponse.data.boardId,
      title: boardResponse.data.title,
      createAt: boardResponse.data.createAt,
      hits: boardResponse.data.hits,
      contents: boardResponse.data.contents,
      planKey: boardResponse.data.planKey,
    };
    // console.log("board : ", board.value);
    // 두번째 비동기 작업
    const planResponse = await axios.get(
      "http://localhost:/plans/list/" + board.value.planKey
    );

    // console.log(planResponse.data);

    // planDetails.value = planResponse.data;

    // planDetails 정렬
    planDetails.value = planResponse.data.sort((a, b) => {
      // planDate 기준으로 오름차순 정렬
      if (a.planDate < b.planDate) return -1;
      if (a.planDate > b.planDate) return 1;

      // planDate가 같으면 sequence 기준으로 오름차순 정렬
      if (a.planDate == b.planDate) if (a.sequence < b.sequence) return -1;
      if (a.sequence > b.sequence) return 1;

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

    console.log("데이트리스트 ", dateList.value);
    // console.log("planDetails :", planDetails.value);
    // console.log("planRes : ", planResponse);
  } catch (error) {
    console.log("Error : ", error);
  }
});
// console.log(history.state);

const isFileModalOpen = ref(false);

const openFileModal = () => {
  isFileModalOpen.value = true;
};

const updatePost = () => {
  // console.log("boardId : ", board);
  const boardUpdateDto = {
    boardId: board.value.boardId,
    title: updateTitle.value,
    contents: updateContents.value,
  };
  axios
    .patch("http://localhost:/boards/" + board.value.boardId, boardUpdateDto)
    .then((response) => {
      // console.log("updatePost", response);
    })
    .catch((error) => {
      console.log("error", error);
    });
  isFileModalOpen.value = false;
  location.reload();
};

const closeFileModal = () => {
  isFileModalOpen.value = false;
};

const deleteBoard = () => {
  // console.log("deleteBoard 호출 성공 : ", deleteBoard);
  axios
    .delete("http://localhost:/boards/" + board.value.boardId)
    .then((response) => {
      // console.log(response);
      router.replace({ name: "board-list" });
    });
};
</script>

<template>
  <div class="modify-delete-btn" :class="{ fixed: isFixed }">
    <button
      id="board-modify-btn"
      @click="openFileModal"
      style="cursor: pointer"
    >
      수정
    </button>
    <button id="board-delete-btn" @click="deleteBoard" style="cursor: pointer">
      삭제
    </button>
  </div>

  <!-- modal -->
  <div v-if="isFileModalOpen" class="modify-modal">
    <div class="modal-title">
      <p>게시글을 수정해보세요!</p>
    </div>
    <div class="post-title">
      <textarea
        id="post-title-inp"
        class="textarea_tit"
        placeholder="제목을 입력하세요"
        style="height: 42px !important"
        v-model="updateTitle"
      ></textarea>
      <div id="board-editor-container" style="display: block"></div>
    </div>
    <body
      id="tinymce"
      class="mce-content-body content useless_p_margin vsc-initialized"
      data-id="editor-tistory"
      contenteditable="true"
      spellcheck="false"
      style="
        overflow-y: hidden;
        padding-left: 10px;
        padding-right: 10px;
        padding-bottom: 50px;
      "
      data-mce-style="overflow-y: hidden; padding-left: 10px; padding-right: 10px; padding-bottom: 50px;"
      role="textbox"
      aria-multiline="true"
      aria-label="글 내용 입력"
      @input="updateContent"
    >
      <p data-ke-size="size16"><br /></p>
    </body>
    <div class="modal-buttons">
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
        취소
      </button>
      <button
        class="complete-button"
        style="
          background-color: gray;
          color: white;
          padding: 10px 20px;
          border: none;
          cursor: pointer;
          border-radius: 4px;
        "
        @click="updatePost"
      >
        수정완료
      </button>
    </div>
  </div>
  <!-- modal-end -->

  <div class="detail-scroll" @scroll="handleScroll">
    <div class="container__Container-sc-5ea7eb67-0 sc-4d05c194-0 iLKpSA ixIhnv">
      <div
        class="container__Container-sc-5ea7eb67-0 sc-beeac02a-1 iLKpSA TXJjD"
      >
        <div size="18" color="gray" class="text__Text-sc-6cffe184-0 gYBzzu">
          {{ board.title }}
        </div>
      </div>
      <div
        class="container__Container-sc-5ea7eb67-0 sc-beeac02a-2 iLKpSA hkqvVg"
      >
        <div class="text__Text-sc-6cffe184-0 sc-beeac02a-0 haSmSi kxcCfC">
          <p>
            <br />
            {{ board.contents }}
          </p>
        </div>
      </div>
      <div
        class="container__Container-sc-5ea7eb67-0 itinerary___StyledContainer2-sc-681c5310-9 iLKpSA gHsyoQ"
      >
        <div
          class="container__Container-sc-5ea7eb67-0 itinerary-map___StyledContainer-sc-bb75f9ca-0 iLKpSA hSALFW chromatic-ignore"
        ></div>
        <div
          class="container__Container-sc-5ea7eb67-0 itinerary___StyledContainer-sc-681c5310-8 iLKpSA geplNv"
        >
          <div
            class="container__Container-sc-5ea7eb67-0 itinerary__Stack-sc-681c5310-4 iLKpSA eAsDxC"
          >
            <div
              class="container__Container-sc-5ea7eb67-0 itinerary__Stack-sc-681c5310-4 iLKpSA eAsDxC"
            >
              <!-- 각 날짜에 대한 반복문 -->
              <div
                v-for="(date, dateIndex) in dateList"
                :key="dateIndex"
                class="day-container"
              >
                <div class="day">
                  <span>{{ date }}</span>
                </div>
                <div class="day-schedule">
                  <!-- 각 날짜 및 시퀀스에 대한 반복문 -->
                  <div
                    v-for="plan in planDetails.filter(
                      (item) => item.planDate === date
                    )"
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
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.day-container {
  width: 47rem;
  height: 100%;
  background-color: white;
  margin-right: 1rem;
  border: 1px solid rgb(187, 183, 183);
  border-radius: 10px;
  margin-top: 2rem;

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
    box-shadow: 0 0 5px rgba(128, 128, 128, 0.5),
      0 5px 10px rgba(128, 128, 128, 0.22);
  }
}
.iLKpSA {
  box-sizing: border-box;
  float: none;
  // display: flex;
}
.post-title {
  width: 860px;
  margin: 35px auto 17px;
  padding-bottom: 26px;
  border-bottom: 1px solid rgba(0, 0, 0, 0.05);

  .textarea_tit {
    display: block;
    width: 100%;
    border: none;
    font-size: 30px;
    color: #202020;
    font-family: Noto Sans DemiLight, AppleSDGothicNeo-Regular, "Malgun Gothic",
      dotum, sans-serif;
    resize: none;
    outline: 0 none;
    line-height: 40px;
    overflow: hidden;
    letter-spacing: -0.4px;

    &::placeholder {
      color: #c3bebe;
    }
  }
}

.content {
  padding: 0 0 20px;
  font-size: 18px;

  padding: 20px 20px 50px;
  word-wrap: break-word;
  color: #333;
  font-size: 14px;
  min-height: 370px;
  max-width: 860px;
  font-family: -apple-system, BlinkMacSystemFont, "Helvetica Neue",
    "Apple SD Gothic Neo", Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  letter-spacing: 0;
  // position: relative;
}
body {
  display: block;
  margin: 8px;
}

.modify-modal {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: rgba(
    255,
    255,
    255,
    1
  ); /* Adjust alpha for more transparency */
  padding: 20px;
  border: 1px solid #ccc;
  z-index: 2;
  border-radius: 1rem;
  min-width: 40rem;
  min-height: 25rem;

  .modal-title {
    text-align: center; /* 가로로 중앙에 텍스트 정렬 */
  }

  .modal-buttons {
    position: absolute;
    bottom: 20px;
    left: 50%;
    transform: translateX(-50%);
    display: flex;
    justify-content: space-between;
    width: 30%; /* 버튼 간 간격을 조절하기 위해 너비를 조정 */
  }

  .cancel-button,
  .complete-button {
    background-color: #d9534f;
    color: white;
    border: none;
    cursor: pointer;
    border-radius: 4px;
  }

  .complete-button {
    background-color: gray;
  }
}
.modify-delete-btn {
  margin-left: 85rem;
  margin-top: 2rem;
  position: absolute;
  transition: margin-top 0.3s ease; /* 애니메이션 추가 */

  > button {
    margin-left: 1rem;
    min-width: 5rem;
    min-height: 3rem;
    border-radius: 1rem;
  }

  #board-modify-btn {
    color: white;
    background-color: rgb(151, 95, 254);
  }
}

.ixIhnv {
  position: relative;
  margin: 2rem 37rem;
}

.iLKpSA {
  box-sizing: border-box;
  float: none;
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
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 0%,
    var(--color-white) 100%
  );
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
</style>
