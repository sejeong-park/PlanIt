<script setup>
import axios from "axios";
import { ref, watch } from "vue";
import { useRouter } from "vue-router";
import { useUserStore } from "@/stores/user";
import { notification } from 'ant-design-vue';

const store = useUserStore();

const userId = ref("");
const userPassword = ref("");

const router = useRouter();

const notFoundUser = ref("");

const loginCheck = ref(false); // 로그인 버튼을 눌렀다면 true, 누르지 않았다면 false

const openNotificationWithIcon = (type, message , description) => {
  notification[type]({
    message: message,
    description: description,
    placement : 'top',
    });
}

const login = function () {
  loginCheck.value = true;
  axios
    .post("http://localhost:/users/login", {
      userId: userId.value,
      userPassword: userPassword.value,
    })
    .then((response) => {
      // 로그인에 성공해서 200 status code를 응답 받았을 때
      if (response.status === 201) {
        openNotificationWithIcon('success', '로그인에 성공하였습니다', store.userId + "님 환영합니다!");
        
        store.loginStatus = true;
        store.userId = userId.value;
        const { accessToken } = response.data;

        axios.defaults.headers.common["Authorization"] = accessToken;

        router.replace({ name: "plans" });
      } else {
        return;
      }
    })
    .catch((error) => {
      // alert("계정을 찾을 수 없습니다!!");
      openNotificationWithIcon('error', '계정을 찾을 수 없습니다.');
      notFoundUser.value = "가입되지 않은 계정입니다.!";
      // userPassword.value = "";
    });
};

watch(userPassword, () => {
  notFoundUser.value = "";
});
</script>

<template>
  <div class="background">
    <div class="login-form">
      <div class = "image-section">
        <div class = "text-overlay">Login</div>
        <img src="@\assets\img\login/login-img.svg" alt="logo" />
      </div>
      <div class = "login-form-section">
        <!--아이디 부분-->
        <a-label  for="id" >아이디 </a-label>
        <a-input
          
          id="id"
          name="id"
          v-model:value="userId"
          type="text"
        >
        </a-input>
        <div class="valid">
          <p v-if="loginCheck && userId === ''">아이디를 입력해주세요</p>
        </div>
        <!--패스워드 부분-->
        <a-label  for="id">패스워드 </a-label>
        <a-input
          
          id="id"
          name="id"
          v-model:value="userPassword"
          type="password"
        >
        </a-input>
        <div class="valid">
          <p v-if="loginCheck && userPassword === ''">패스워드를 입력해주세요</p>
          <p v-if="userPassword !== '' && notFoundUser" >
            {{ notFoundUser }}
          </p>
        </div>
        <a-button id = "login-btn"  type="primary" @click="login()" htmlType="submit"
        >로그인</a-button
        >
        <p class = "form-detail">
          아직 회원이 아니세요? &nbsp;&nbsp;
          <router-link to="/users/regist">회원가입</router-link>
        </p>
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
  padding-bottom: 10%;
}

.login-form {
  width: 30rem;
  height: 40rem;

  display: flex;
  flex-direction: column;
  justify-content: center; /* Center vertically */
  align-items: center; /* Center horizontally */

  padding : 2rem;

  margin: 5rem auto; /* Center within the parent container */
  box-shadow : 0 0px 28px rgba(128, 128, 128, 0.25), 0 0px 10px rgba(128, 128, 128, 0.22);
  border-radius: 2rem;
}


.image-section {
  width : 100%;
  height : 40%;
  display : flex;
  justify-content: center;
  align-items: center;
  position : relative;
  img {
    width: 12rem;
    height: 10rem;
  }
  .text-overlay {
    position : absolute;
    top : 50%;
    left : 50%;
    transform: translate(-50%, -50%); /* 중앙 정렬 */
    color : var(--planit-dark);
    font-size : 32px;
    font-weight: bold;
  }
}

.login-form-section {
  width : 100%;
  height : 60%;
  display : flex;
  flex-direction: column;
  justify-content: center;
  padding : 0 3rem;

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
    height: 2rem;
}

.valid {
  height : 2rem;
  justify-content: start;
  align-items: center;
  p {
    margin : 0;
    padding-top : 0.5rem;
    position: absolute;
    font-size : 16px;
    color : red;
  }
}
</style>
