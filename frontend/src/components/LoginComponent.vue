<script setup>
import { reactive } from "vue";
const formState = reactive({
  username: "",
  password: "",
  //   remember: true, // true일 떄 쿠키 등록해주는 방식 쓰면 아이디 저장될 듯?
});
const onFinish = (values) => {
  console.log("Success:", values);
};
const onFinishFailed = (errorInfo) => {
  console.log("Failed:", errorInfo);
};
</script>

<template>
  <a-form
    :model="formState"
    name="basic"
    :label-col="{ span: 8 }"
    :wrapper-col="{ span: 16 }"
    autocomplete="off"
    @finish="onFinish"
    @finishFailed="onFinishFailed"
  >
    <a-form-item
      label="아이디"
      name="id"
      :rules="[{ required: true, message: '아이디를 입력하세요' }]"
    >
      <a-input v-model:value="formState.username" />
    </a-form-item>

    <a-form-item
      label="패스워드"
      name="password"
      :rules="[{ required: true, message: '비밀번호를 입력하세요!' }]"
    >
      <a-input-password v-model:value="formState.password" />
    </a-form-item>

    <!-- 
        아이디 저장
        <a-form-item name="remember" :wrapper-col="{ offset: 8, span: 16 }">
      <a-checkbox v-model:checked="formState.remember">아이디 저장</a-checkbox>
    </a-form-item> -->

    <a-form-item :wrapper-col="{ offset: 8, span: 16 }">
      <a-button type="primary" html-type="submit">로그인</a-button>
    </a-form-item>
  </a-form>
</template>

<style lang="scss" scoped></style>
