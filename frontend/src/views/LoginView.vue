<script setup>
import axios from "axios";
import { ref } from "vue";
import { useRouter, useRoute } from "vue-router";
import { useUserStore } from "@/stores/user";

const store = useUserStore();

const userId = ref("");
const userPassword = ref("");

const router = useRouter();
const route = useRoute();

const login = function () {
  axios
    .post("http://localhost:/users/login", {
      userId: userId.value,
      userPassword: userPassword.value,
    })
    .then((response) => {
      // 로그인에 성공해서 200 status code를 응답 받았을 때
      if (response.status === 200) {
        alert("로그인 성공!!");
        // console.log(store.loginStatus);
        store.loginStatus = true;
        store.userId = userId.value;
        router.replace({ path: "/" });
        // console.log(store.loginStatus);
        // console.log(store.userId);
      } else {
        alert("로그인 실패!!");
      }
    })
    .catch((error) => {
      if (error.response.status === 404) {
        alert("계정을 찾을 수 없습니다!!");
      }
    });
};
</script>

<template>
  <div class="background">
    <a-form class="login-form">
      <div>
        <img src="@\assets\img\PlanIt!.png" alt="logo" />
      </div>

      <a-label for="id">아이디 </a-label>
      <a-input id="id" name="id" v-model:value="userId" type="text" style="width: 20rem"> </a-input>

      <a-label for="id">패스워드 </a-label>
      <a-input id="id" name="id" v-model:value="userPassword" type="text" style="width: 20rem">
      </a-input>

      <a-button type="primary" @click="login()" htmlType="submit">로그인</a-button>
      <p>아직 회원이 아니세요?<router-link to="/users/regist">회원가입</router-link></p>
    </a-form>
  </div>
</template>

<style lang="scss" scoped>
.login-form {
  width: 27rem;
  height: 25rem;
  display: flex;
  flex-direction: column;
  justify-content: center; /* Center vertically */
  align-items: center; /* Center horizontally */
  margin: 5rem auto; /* Center within the parent container */
  border: 1px solid #20b2aa;
  border-radius: 2rem;

  a-label {
    margin-top: 1rem;
    text-align: left;
    color: gray;
  }

  button {
    margin-top: 1rem;

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
</style>
