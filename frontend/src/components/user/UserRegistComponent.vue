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
    <div class="register-form">
      <div class = "image-section">
        <img src="@\assets\img\login/join-img.svg" alt="logo" />
      </div>
      <div class = "register-form-section">
          <a-label for="userId">아이디 </a-label>
          <a-input
            id="userId"
            name="userId"
            v-model:value="userId"
            type="text"
          >
          </a-input>
          <!-- 에러 메시지 표시 -->
          <div class="valid">
            <p v-if="duplicateCheck">이미 존재하는 계정입니다.</p>
            <p v-if="clickRegist && userId === ''">아이디 입력해주세요</p>
          </div>
          <a-label for="userPassword">패스워드 </a-label>
          <a-input
            id="userPassword"
            name="userPassword"
            v-model:value="userPassword"
            type="password"
          >
          </a-input>
          <!-- 에러 메시지 표시 -->
          <div class="valid">
            <p v-if="clickRegist && userPassword === ''">
              패스워드를 입력해주세요
            </p>
          </div>

          <a-label for="passwordCheck">패스워드 확인</a-label>
          <a-input
            id="passwordCheck"
            name="passwordCheck"
            v-model:value="passwordCheck"
            type="password"
          >
          </a-input>
          <div class="valid">
            <p v-if="passwordCheck !== '' && passwordCheck !== userPassword" style="color: red">비밀번호가 일치하지 않습니다.</p>
          </div>

          <a-label for="userName">이름 </a-label>
          <a-input
            id="userName"
            name="userName"
            v-model:value="userName"
            type="text"
          >
          </a-input>
          <!-- 에러 메시지 표시 -->
          <div class="valid">
            <p v-if="clickRegist && userName === ''">이름을 입력해주세요</p>
          </div>

          <a-label for="email">이메일</a-label>
          <a-input
            id="email"
            name="email"
            v-model:value="email"
            type="email"
          >
          </a-input>
          <!-- 에러 메시지 표시 -->
          <div class="valid" >
            <p v-if="clickRegist && email === ''">
              이메일을 입력해주세요
            </p>
            <p v-if="clickRegist && email !== '' && emailCheck !== ''">
              {{ emailCheck }}
            </p>
          </div>

        <a-button type="primary" @click="regist()">회원가입</a-button>
      </div>
      </div>
      
  </div>
</template>

<style lang="scss" scoped>

.background {
  width : 100%;
  height : 100vh;
  display : flex;
  justify-content: center; 
  align-items: center;
  padding-bottom: 5%;
}

.register-form {
  width: 30rem;
  height: 50rem;

  display: flex;
  flex-direction: column;
  justify-content: center; /* Center vertically */
  align-items: center; /* Center horizontally */

  padding : 2rem;

  margin: 3rem auto; /* Center within the parent container */
  box-shadow : 0 0px 28px rgba(128, 128, 128, 0.25), 0 0px 10px rgba(128, 128, 128, 0.22);
  border-radius: 2rem;

}

.image-section {
  width : 100%;
  height : 25%;
  display : flex;
  justify-content: center;
  align-items: center;

  img {
    width: 100%;
    height: 100%;
    padding : 1rem;
  }
}

.register-form-section {
  width : 100%;
  height : 75%;
  display : flex;
  flex-direction: column;
  justify-content: center;
  padding : 1rem 3rem;

  .form-detail {
    width  :100%;
    text-align: center;
    justify-content: center;

    padding : 0.5rem 1rem;
  }
}

a-label {
    margin : 0.5rem 0.5rem;
    text-align: left;
    color: var(--color-gray600);
    font-size : 16px;
    font-weight: 500;
}

p {
  color: var(--color-gray600);;
}
button {
    margin : 1rem;
    border-radius: 2rem;
}
.valid {
  height : 100%;
  justify-content: start;
  align-items: center;
  p {
    margin : 0;
    padding : 0.3rem;
    position: absolute;
    font-size : 16px;
    color : red;
  }
}
</style>
