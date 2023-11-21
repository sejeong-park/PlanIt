<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";

// const { board } = history.state; // 이렇게 받는다.
const { boardId } = history.state;
// const boardId = ref(0);

const board = ref({});

const planDetails = ref([]);

onMounted(async () => {
  try {
    const boardResponse = await axios.get("http://localhost:/boards/" + boardId);

    // 첫번째 비동기 작업
    board.value = {
      title: boardResponse.data.title,
      createAt: boardResponse.data.createAt,
      hits: boardResponse.data.hits,
      contents: boardResponse.data.contents,
      planKey: boardResponse.data.planKey,
    };
    // console.log("board : ", board.value);
    // 두번째 비동기 작업
    const planResponse = await axios.get("http://localhost:/plans/" + board.value.planKey);
    // planResponse.data.forEach((data) => {
    //   planDetails.value.push({
    //     detail_id: data.detail_id,
    //     attractionId: data.attractionId,
    //     planDate: data.planDate,
    //     sequence: data.sequence,
    //     title: data.title,
    //   });
    // });

    planDetails.value = planResponse.data;

    // planDetails 정렬
    planDetails.value.sort((a, b) => {
      // planDate 기준으로 오름차순 정렬
      if (a.planDate < b.planDate) return -1;
      if (a.planDate > b.planDate) return 1;

      // planDate가 같으면 sequence 기준으로 오름차순 정렬
      if (a.sequence < b.sequence) return -1;
      if (a.sequence > b.sequence) return 1;

      return 0; // planDate와 sequence가 같을 경우
    });
    console.log("planDetails :", planDetails.value);
    // console.log("planRes : ", planResponse);
  } catch (error) {
    console.log("Error : ", error);
  }
});
// console.log(history.state);

const isFileModalOpen = ref(false);

const openFileModal = () => {
  isFileModalOpen.value = true;
  isModalOpen.value = true;
};

const closeFileModal = () => {
  isFileModalOpen.value = false;
  isModalOpen.value = false;
};
</script>

<template>
  <div class="modify-delete-btn" :class="{ fixed: isFixed }">
    <button id="board-modify-btn" @click="openFileModal" style="cursor: pointer">수정</button>
    <button id="board-delete-btn" @click="closeFileModal" style="cursor: pointer">삭제</button>
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
        v-model="title"
      ></textarea>
      <div id="board-editor-container" style="display: block"></div>
    </div>
    <body
      id="tinymce"
      class="mce-content-body content useless_p_margin vsc-initialized"
      data-id="editor-tistory"
      contenteditable="true"
      spellcheck="false"
      style="overflow-y: hidden; padding-left: 10px; padding-right: 10px; padding-bottom: 50px"
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
        @click="uploadPost"
      >
        수정완료
      </button>
    </div>
  </div>
  <!-- modal-end -->

  <div class="detail-scroll" @scroll="handleScroll">
    <div class="container__Container-sc-5ea7eb67-0 sc-4d05c194-0 iLKpSA ixIhnv">
      <div class="container__Container-sc-5ea7eb67-0 sc-beeac02a-1 iLKpSA TXJjD">
        <div size="18" color="gray" class="text__Text-sc-6cffe184-0 gYBzzu">둘째날.</div>
      </div>
      <div class="container__Container-sc-5ea7eb67-0 sc-beeac02a-2 iLKpSA hkqvVg">
        <div class="text__Text-sc-6cffe184-0 sc-beeac02a-0 haSmSi kxcCfC">
          <p>
            <strong>{{ board.title }}</strong
            ><br />
            {{ board.contents }}
            반나절은 다낭 시내에서, 이후에는 오행산과 호이안에서 보낸다. 전통과 빼어난 자연경관
            그리고 은은하게 빛나는 야경까지. 구석구석 자리잡은 다낭의 멋으로 지루할 틈이 없다.
            반나절은 다낭 시내에서, 이후에는 오행산과 호이안에서 보낸다. 전통과 빼어난 자연경관
            그리고 은은하게 빛나는 야경까지. 구석구석 자리잡은 다낭의 멋으로 지루할 틈이 없다.
            반나절은 다낭 시내에서, 이후에는 오행산과 호이안에서 보낸다. 전통과 빼어난 자연경관
            그리고 은은하게 빛나는 야경까지. 구석구석 자리잡은 다낭의 멋으로 지루할 틈이 없다.
            반나절은 다낭 시내에서, 이후에는 오행산과 호이안에서 보낸다. 전통과 빼어난 자연경관
            그리고 은은하게 빛나는 야경까지. 구석구석 자리잡은 다낭의 멋으로 지루할 틈이 없다.
            반나절은 다낭 시내에서, 이후에는 오행산과 호이안에서 보낸다. 전통과 빼어난 자연경관
            그리고 은은하게 빛나는 야경까지. 구석구석 자리잡은 다낭의 멋으로 지루할 틈이 없다.
            <br />
            · 예상 소요시간 : 7시간
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
              class="container__Container-sc-5ea7eb67-0 flex-box__FlexBox-sc-df192771-1 iLKpSA gPRjNB"
              v-for="plan in planDetails"
              :key="plan.planDate"
            >
              <div
                class="container__Container-sc-5ea7eb67-0 flex-box__FlexBox-sc-df192771-1 itinerary__Timeline-sc-681c5310-0 iLKpSA gPRjNB kpPodn"
              >
                <div
                  class="container__Container-sc-5ea7eb67-0 flex-box__FlexBox-sc-df192771-1 iLKpSA hUhUTq"
                >
                  <div
                    class="container__Container-sc-5ea7eb67-0 flex-box__FlexBox-sc-df192771-1 itinerary___StyledFlexBox-sc-681c5310-10 iLKpSA bFFkHa gdAsaT"
                  >
                    <span
                      color="rgba(151, 95, 254, 1)"
                      class="badge__BadgeBase-sc-f2fd9e41-0 badge__CircleBadge-sc-f2fd9e41-2 gJtAJZ fzwqRU"
                      >{{ plan.sequence }}</span
                    >
                  </div>
                  <div
                    class="container__Container-sc-5ea7eb67-0 itinerary__Duration-sc-681c5310-6 iLKpSA hXEfgc"
                  ></div>
                </div>
              </div>
              <a
                class="container__Container-sc-5ea7eb67-0 flex-box__FlexBoxItem-sc-df192771-0 itinerary__CardWrapper-sc-681c5310-3 iLKpSA cIkAYa AiTlk"
                ><div
                  class="segment__CardFrame-sc-e2042e2a-1 iSLaBf itinerary__PoiCard-sc-681c5310-1 itinerary___StyledPoiCard-sc-681c5310-2 jjciAh iDvhtl"
                  radius="6"
                >
                  <div size="16" class="text__Text-sc-6cffe184-0 jdWASn">
                    {{ plan.planDate }}
                  </div>
                  <div size="13" color="gray500" class="text__Text-sc-6cffe184-0 dnGFrr">
                    {{ plan.title }}
                  </div>
                </div></a
              >
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
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
    font-family: Noto Sans DemiLight, AppleSDGothicNeo-Regular, "Malgun Gothic", dotum, sans-serif;
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
  font-family: -apple-system, BlinkMacSystemFont, "Helvetica Neue", "Apple SD Gothic Neo", Arial,
    sans-serif;
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
  background-color: rgba(255, 255, 255, 1); /* Adjust alpha for more transparency */
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
</style>
