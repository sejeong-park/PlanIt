<script setup>
import { ref } from "vue";
import axios from "axios";

const userId = ref("");
const userPassword = ref("");
const userName = ref("");

const regist = function () {
  // console.log(userId.value, password.value, nickname.value);
  // 변수명이 서버측 변수 명과 동일해야한다. 그렇지 않으면 데이터가 DB에 저장되지 않는다.
  axios.post("http://localhost:/users/regist", {
    userId: userId.value,
    userPassword: userPassword.value,
    userName: userName.value,
  });

  userId.value = "";
  userPassword.value = "";
  userName.value = "";
};
</script>

<template>
  <div class="background">
    <a-form class="register-form">
      <img src="@\assets\img\PlanIt!.png" alt="logo" />

      <!-- v-model:value = v-model -->
      <a-label for="userId">아이디 </a-label>
      <a-input id="userId" name="userId" v-model:value="userId" type="text" style="width: 20rem">
      </a-input>

      <a-label for="userPassword">패스워드 </a-label>
      <a-input
        id="userPassword"
        name="userPassword"
        v-model:value="userPassword"
        type="password"
        style="width: 20rem"
      >
      </a-input>

      <a-label for="userName">닉네임 </a-label>
      <a-input
        id="userName"
        name="userName"
        v-model:value="userName"
        type="text"
        style="width: 20rem"
      >
      </a-input>

      <!-- <a-label for="phone">핸드폰</a-label>
      <a-input id="phone" name="phone" type="text"> </a-input> -->

      <a-label for="email">이메일</a-label>
      <a-input id="email" name="email" type="email" style="width: 20rem"> </a-input>
      <!-- 
      <a-label for="gender">성별 </a-label>
      <div class="gender">
        <a-label for="male">남</a-label>
        <a-input id="male" name="gender" type="radio" value="male"></a-input>

        <a-label for="female">여</a-label>
        <a-input id="female" name="gender" type="radio" value="female"></a-input>
      </div> -->
      <!-- 
      <a-label for="birth">생일</a-label>
      <a-input id="birth" name="birth" type="date"> </a-input> -->

      <a-button type="primary" @click="regist()">회원가입</a-button>
    </a-form>
  </div>
</template>

<style lang="scss" scoped>
.register-form {
  width: 27rem;
  height: 35rem;
  display: flex;
  flex-direction: column;
  justify-content: center; /* Center vertically */
  align-items: center; /* Center horizontally */
  margin: 3rem auto; /* Center within the parent container */
  border: 1px solid #20b2aa;
  border-radius: 2rem;

  a-label {
    color: gray;
    margin-top: 1rem;
    text-align: left;
  }

  button {
    margin-top: 1rem;
    border-radius: 2rem;
    width: 20rem;
    height: 2rem;
  }

  img {
    width: 12rem;
    height: 9rem;
  }

  p {
    color: gray;
  }
}

.gender {
  display: flex;
  align-items: center; /* 세로 중앙 정렬 */
}
</style>
