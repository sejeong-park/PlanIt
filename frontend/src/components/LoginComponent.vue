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
  <div class="background">
    <a-form class="login-form">
      <div>
        <img src="@\assets\img\PlanIt!.png" alt="logo" />
      </div>

      <a-label for="id">아이디 </a-label>
      <a-input id="id" name="id" type="text"> </a-input>

      <a-label for="id">패스워드 </a-label>
      <a-input id="id" name="id" type="text"> </a-input>

      <a-button type="primary" htmlType="submit">로그인</a-button>
    </a-form>
  </div>
</template>

<style lang="scss" scoped>
.login-form {
  width: 30rem;
  height: 30rem;
  display: flex;
  flex-direction: column;
  justify-content: center; /* Center vertically */
  align-items: center; /* Center horizontally */
  margin: 10rem auto; /* Center within the parent container */
  border: 1px solid #20b2aa;
  border-radius: 2rem;

  a-label {
    margin-top: 2rem;
    text-align: left;
    color: gray;
  }

  button {
    margin-top: 3rem;
    width: 20rem;
  }

  img {
    width: 12rem;
    height: 12rem;
  }
}
</style>
