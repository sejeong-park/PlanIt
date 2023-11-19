<script setup>
import { ref, watch } from "vue";

const title = ref("");
const content = ref("");

const updateContent = (event) => {
  content.value = event.target.innerText;
  console.log("content:", content.value);
};

// watch(
//   () => title.value,
//   (newValue) => {
//     console.log("title : ", newValue);
//   }
// );
// console.log("title : ", title.value);
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
}
body {
  display: block;
  margin: 8px;
}
</style>
