<script setup>
import { ref, watch } from "vue";

const title = ref("");
const content = ref("");

const updateContent = (event) => {
  content.value = event.target.innerText;
  // console.log("content:", content.value);
};

// watch(
//   () => title.value,
//   (newValue) => {
//     console.log("title : ", newValue);
//   }
// );
// console.log("title : ", title.value);

const isFileModalOpen = ref(false);

const openFileModal = () => {
  isFileModalOpen.value = true;
};

const closeFileModal = () => {
  isFileModalOpen.value = false;
};
</script>

<template>
  <div class="board-editor">
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
    <div class="content-aside">
      <div class="wrap_btn">
        <button
          id="publish-layer-btn"
          class="btn btn-default"
          @click="openFileModal"
        >
          완료
        </button>
      </div>
    </div>
    <div v-if="isFileModalOpen" class="file-modal">
      <div class="sc-jHkVzv hrosqC">
        <div class="sc-bQtKYq fZgivC">
          <div class="sc-fXEqDS eUikOV">
            <button
              class="sc-jWUzzU hhwHgR"
              style="
                background-color: #4caf50;
                color: white;
                padding: 10px 20px;
                border: none;
                cursor: pointer;
                border-radius: 4px;
              "
            >
              썸네일 업로드
            </button>
          </div>
        </div>
      </div>
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
      >
        취소
      </button>
      <button
        class="sc-jWUzzU hhwHgR"
        style="
          background-color: gray;
          color: white;
          padding: 10px 20px;
          border: none;
          cursor: pointer;
          border-radius: 4px;
        "
      >
        출간하기
      </button>
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
  margin: 0 32rem;
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

.content-aside {
  position: absolute;
  bottom: 0;
  width: 100%;
  height: 66px;
  background-color: #f5f5f5;
  z-index: 1;
  overflow: hidden;
  min-width: 944px;
  text-align: right;
  position: fixed; // 완료 버튼이 아래 고정
  bottom: 0;
  left: 0;
  text-align: center;

  .wrap_btn {
    margin: 10px;
  }

  .btn {
    --tw-bg-opacity: 1;
    /* 배경색 설정 */
    background-color: rgb(80 212 229 / var(--tw-bg-opacity));
    color: white; /* 글자색 설정 */
    padding: 12px 24px; /* 안쪽 여백 설정 */
    font-size: 16px; /* 글자 크기 설정 */
    border: none; /* 테두리 없애기 */
    border-radius: 4px; /* 테두리 모서리 둥글게 만들기 */
    cursor: pointer; /* 커서 모양 변경 */
    transition: opacity 0.3s ease; /*투명도 전환을 부드럽게 만들기 위한 트랜지션 속성 */
    font-weight: 700;
    width: 10rem;
  }

  .btn:hover {
    opacity: 0.6; /*마우스 호버 시 투명도를 0.8로 조정 */
  }
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
}
</style>
