<script setup>
import { ref, watch } from "vue";
import axios from "axios";
import { useRouter } from "vue-router";

const router = useRouter();

const boardRegistDto = ref({});

const title = ref("");
const contents = ref("");
const selectedFile = ref(null);

const file = ref(null);
const isModalOpen = ref(false);

const baseUrl = "http://localhost:/boards";
const planKey = "277d11f6-e153-41fd-acc8-d0391da4caab";
const board = ref({});

const handleFileChange = (event) => {
  selectedFile.value = event.target.files[0];
  file.value = selectedFile.value;
  // console.log(event.target.files[0]);

  // 이미지 파일 미리보기 생성
  if (selectedFile.value.type.startsWith("image/")) {
    const reader = new FileReader();
    reader.onload = () => {
      file.value = reader.result;
    };
    reader.readAsDataURL(selectedFile.value);
  } else {
    file.value = null; // 이미지가 아닌 경우 처리
  }
};

const uploadPost = async () => {
  try {
    const formData = new FormData();
    boardRegistDto.value = {
      title: title.value,
      contents: contents.value,
    };
    // console.log(boardRegistDto.value
    // 제목과 컨텐츠는 json 형태로
    formData.append(
      "boardRegistDto",
      new Blob([JSON.stringify(boardRegistDto.value)], {
        type: "application/json",
      })
    );

    formData.append("file", selectedFile.value);

    // console.log(formData)

    // 파일은 multipart 타입으로
    const responsePost = await axios
      .post(`${baseUrl}/${planKey}`, formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      })
      .then((response) => {
        // console.log(response);
        // 업로드 성공 후 변수 초기화
        title.value = "";
        contents.value = "";
        selectedFile.value = null;
        const boardId = response.data.boardId;
        router.replace({
          name: "board-detail",
          params: { boardId: response.data.boardId },
          state: {
            boardId: boardId,
          },
        });
      });

    // console.log(
    //   "타이틀, 컨텐츠, 파일 등록 초기화 체크 : ",
    //   title.value,
    //   contents.value,
    //   selectedFile.value
    // );

    // router.push({name:''})

    // console.log("Post uploaded sucecess ", response.data);
  } catch (error) {
    console.error("Error uploading post :", error);
  }
};

const updateContent = (event) => {
  contents.value = event.target.innerText;
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
  isModalOpen.value = true;
};

const closeFileModal = () => {
  isFileModalOpen.value = false;
  isModalOpen.value = false;
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
      style="overflow-y: hidden; padding-left: 10px; padding-right: 10px; padding-bottom: 50px"
      data-mce-style="overflow-y: hidden; padding-left: 10px; padding-right: 10px; padding-bottom: 50px;"
      role="textbox"
      aria-multiline="true"
      aria-label="글 내용 입력"
      @input="updateContent"
    >
      <p data-ke-size="size16"><br /></p>
    </body>

    <div class="content-aside">
      <div class="wrap_btn" v-if="isModalOpen === false">
        <button id="publish-layer-btn" class="btn btn-default" @click="openFileModal">완료</button>
      </div>
    </div>

    <div v-if="isFileModalOpen" class="file-modal">
      <div class="modal-title">
        <p>{{ title }}</p>
      </div>
      <div id="thumbnail" v-if="file === null" class="no-files-container">
        선택된 썸네일이 없습니다.
      </div>
      <div v-if="file !== null" class="yes-files-container">
        <img :src="file" alt="Selected File" class="preview-image" />
      </div>
      <div class="sc-jHkVzv hrosqC">
        <label
          for="thumbnail-upload"
          class="sc-jWUzzU hhwHgR"
          style="
            background-color: #4caf50;
            color: white;
            padding: 10px 20px;
            border: none;
            cursor: pointer;
            border-radius: 4px;
          "
          >썸네일 업로드</label
        >
        <input
          type="file"
          accept="image/*"
          id="thumbnail-upload"
          style="display: none"
          @change="handleFileChange"
        />
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
          출간하기
        </button>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.modal-title > p {
  font-weight: 900;
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
  margin: 0 32rem;
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
  border-radius: 1rem;
  min-width: 40rem;
  min-height: 25rem;

  .modal-title {
    text-align: center;
  }

  .no-files-container {
    text-align: center;
  }

  .modal-buttons {
    position: absolute;
    bottom: 20px;
    left: 50%;
    transform: translateX(-50%);
    display: flex;
    justify-content: space-between;
    width: 30%; /* 버튼 간 간격을 조절하기 위해 너비를 조정 */

    > button {
      min-width: 10rem;
      min-height: 3rem;
      margin: 0 1rem; /* 각 버튼 간의 마진 조절 */
      border-radius: 4px;
    }
  }
}

.no-files-container {
  margin-bottom: 5rem; /* Adjust the margin as needed to create spacing */
}

.yes-files-container {
  margin-bottom: 5rem; /* Adjust the margin as needed to create spacing */

  .preview-image {
    max-width: 15rem; /* 이미지의 최대 너비를 부모 요소에 맞게 설정 */
    max-height: 20rem; /* 이미지의 최대 높이를 부모 요소에 맞게 설정 */
    border-radius: 10px;
  }
}
</style>
