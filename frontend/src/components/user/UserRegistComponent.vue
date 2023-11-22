<script setup>
import { ref, watch } from "vue";
import axios from "axios";
import { useRouter } from "vue-router";

const router = useRouter();

const userId = ref("");
const userPassword = ref("");
const userName = ref("");
const email = ref("");
const clickRegist = ref(false);
const passwordCheck = ref("");
const duplicateCheck = ref(false);
const emailCheck = ref("");
const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; // 간단한 이메일 정규표현식

const regist = function () {
  // console.log(userId.value, password.value, nickname.value);
  // 변수명이 서버측 변수 명과 동일해야한다. 그렇지 않으면 데이터가 DB에 저장되지 않는다.
  clickRegist.value = true;
  // console.log(userId.value, userPassword.value, userName.value, email.value);
  emailCheck.value = "";

  // 이메일 유효성 검사
  if (!emailRegex.test(email.value)) {
    emailCheck.value = "유효한 이메일 주소를 입력해주세요.";
    return; // 이메일이 유효하지 않으면 더 이상 진행하지 않음
  }

  // 빈 값이 없을 때만 회원가입 가능
  if (
    userId.value !== "" &&
    userPassword.value !== "" &&
    passwordCheck.value !== "" &&
    userName.value !== "" &&
    email.value !== ""
  ) {
    axios
      .post("http://localhost:/users/regist", {
        userId: userId.value,
        userPassword: userPassword.value,
        userName: userName.value,
        email: email.value,
      })
      .then((response) => {
        // coduplicateCheck("회원가입 성공 여부 ", response);
        alert("회원가입에 성공하셨습니다!");
        userPassword.value = "";
        passwordCheck.value = "";
        router.replace({ name: "login" });
      })
      .catch((error) => {
        if (error.response && error.response.status === 406) {
          // 이미 존재하는 계정 오류 처리
          duplicateCheck.value = true;
        } else {
          console.log(error);
        }
      });
  }
};

watch(userId, () => {
  duplicateCheck.value = false;
});

watch(email, () => {
  emailCheck.value = "";
});
</script>

<template>
  <div class="background">
    <a-form class="register-form">
      <img src="@\assets\img\PlanIt!.png" alt="logo" />

      <a-label for="userId">아이디 </a-label>
      <a-input
        id="userId"
        name="userId"
        v-model:value="userId"
        type="text"
        style="width: 20rem"
      >
      </a-input>

      <div v-if="duplicateCheck" class="valid">
        <p style="color: red">이미 존재하는 계정입니다.</p>
      </div>
      <!-- 에러 메시지 표시 -->
      <div v-if="clickRegist" class="valid">
        <p v-if="userId === ''" style="color: red">아이디 입력해주세요</p>
      </div>

      <a-label for="userPassword">패스워드 </a-label>
      <a-input
        id="userPassword"
        name="userPassword"
        v-model:value="userPassword"
        type="password"
        style="width: 20rem"
      >
      </a-input>
      <!-- 에러 메시지 표시 -->
      <div v-if="clickRegist" class="valid">
        <p v-if="userPassword === ''" style="color: red">
          패스워드를 입력해주세요
        </p>
      </div>

      <a-label for="passwordCheck">패스워드 확인</a-label>
      <a-input
        id="passwordCheck"
        name="passwordCheck"
        v-model:value="passwordCheck"
        type="password"
        style="width: 20rem"
      >
      </a-input>
      <div
        v-if="passwordCheck !== '' && passwordCheck !== userPassword"
        class="valid"
      >
        <p style="color: red">비밀번호가 일치하지 않습니다.</p>
      </div>

      <a-label for="userName">이름 </a-label>
      <a-input
        id="userName"
        name="userName"
        v-model:value="userName"
        type="text"
        style="width: 20rem"
      >
      </a-input>
      <!-- 에러 메시지 표시 -->
      <div v-if="clickRegist" class="valid">
        <p v-if="userName === ''" style="color: red">이름을 입력해주세요</p>
      </div>

      <a-label for="email">이메일</a-label>
      <a-input
        id="email"
        name="email"
        v-model:value="email"
        type="email"
        style="width: 20rem"
      >
      </a-input>
      <!-- 에러 메시지 표시 -->
      <div v-if="clickRegist" class="valid" style="margin-right: 14rem">
        <p v-if="email === ''" style="color: red">이메일을 입력해주세요</p>
        <p v-if="email !== '' && emailCheck !== ''" style="color: red">
          {{ emailCheck }}
        </p>
      </div>

      <a-button type="primary" @click="regist()">회원가입</a-button>
    </a-form>
  </div>
</template>

<style lang="scss" scoped>
.register-form {
  width: 27rem;
  height: 45rem;
  display: flex;
  flex-direction: column;
  justify-content: center; /* Center vertically */
  align-items: center; /* Center horizontally */
  margin: 3rem auto; /* Center within the parent container */
  border: 1px solid #20b2aa;
  border-radius: 2rem;

  a-label {
    color: gray;
    margin-top: 2rem;
    text-align: left;
  }

  button {
    margin-top: 3rem;
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
    margin-top: -0.1rem;
  }
}

.gender {
  display: flex;
  align-items: center; /* 세로 중앙 정렬 */
}

.valid {
  margin-right: 8rem;
  p {
    position: absolute;
  }
}
</style>
