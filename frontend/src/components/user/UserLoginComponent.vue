<script setup>
import axios from "axios";
import { ref, watch } from "vue";
import { useRouter } from "vue-router";
import { useUserStore } from "@/stores/user";

const store = useUserStore();

const userId = ref("");
const userPassword = ref("");

const router = useRouter();

const notFoundUser = ref("");

const loginCheck = ref(false); // 로그인 버튼을 눌렀다면 true, 누르지 않았다면 false

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
        alert("로그인 성공!!");
        store.loginStatus = true;
        store.userId = userId.value;
        const { accessToken } = response.data;

        axios.defaults.headers.common["Authorization"] = accessToken;

        router.replace({ path: "/" });
      } else {
        alert("로그인 실패!!");
      }
    })
    .catch((error) => {
      // alert("계정을 찾을 수 없습니다!!");
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
      <div>
        <img src="@\assets\img\Planit.png" alt="logo" />
      </div>

      <a-label for="id">아이디 </a-label>
      <a-input
        id="id"
        name="id"
        v-model:value="userId"
        type="text"
        style="width: 20rem"
      >
      </a-input>
      <div v-if="loginCheck && userId === ''" class="valid">
        <p style="color: red">아이디를 입력해주세요</p>
      </div>

      <a-label for="id">패스워드 </a-label>
      <a-input
        id="id"
        name="id"
        v-model:value="userPassword"
        type="password"
        style="width: 20rem"
      >
      </a-input>
      <div v-if="loginCheck && userPassword === ''" class="valid">
        <p style="color: red">패스워드를 입력해주세요</p>
      </div>
      <div v-if="userPassword !== '' && notFoundUser" class="valid">
        <p style="color: red">
          {{ notFoundUser }}
        </p>
      </div>

      <a-button type="primary" @click="login()" htmlType="submit"
        >로그인</a-button
      >
      <p>
        아직 회원이 아니세요?<router-link to="/users/regist"
          >회원가입</router-link
        >
      </p>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.login-form {
  width: 27rem;
  height: 30rem;
  display: flex;
  flex-direction: column;
  justify-content: center; /* Center vertically */
  align-items: center; /* Center horizontally */
  margin: 5rem auto; /* Center within the parent container */
  border: 1px solid #20b2aa;
  border-radius: 2rem;

  a-label {
    margin-top: 2rem;
    text-align: left;
    color: gray;
  }

  button {
    margin-top: 2rem;
    margin-bottom: 1rem;
    border-radius: 2rem;
    width: 20rem;
    height: 2rem;
  }

  img {
    width: 12rem;
    height: 10rem;
  }

  p {
    color: gray;
  }
}
.valid {
  margin-right: 10rem;

  p {
    position: absolute;
  }
}
</style>
