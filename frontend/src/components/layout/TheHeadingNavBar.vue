<script setup>
import { useUserStore } from "@/stores/user";
import axios from "axios";

const store = useUserStore();
// console.log("loginStatus : ", store.loginStatus);

const logout = function () {
  // "login" 키에 해당하는 sessionStorage 값 제거
  sessionStorage.removeItem("login");
  // console.log(axios.defaults.headers.common["Authorization"]);
  axios.defaults.headers.common["Authorization"] = "";
  // console.log(axios.defaults.headers.common["Authorization"]);
  store.loginStatus = false;
  // console.log(store.loginStatus);
};
</script>
<template>
  <nav>
    <a-layout>
      <a-layout-header class="header" style="display: flex; justify-content: flex-end">
        <a href="/"><img src="@\assets\img\PlanIt!.png" alt="logo" /></a>
        <a-menu
          v-model:selectedKeys="selectedKeys1"
          mode="horizontal"
          :style="{
            lineHeight: '64px',
            display: 'flex',
            alignItems: 'center',
            marginLeft: 'auto',
          }"
        >
          <div v-if="!store.loginStatus">
            <a-menu-item key="1"><router-link to="/users/login">로그인</router-link></a-menu-item>
            <a-menu-item key="2"
              ><router-link :to="{ name: 'user-regist' }">회원가입</router-link></a-menu-item
            >
            <a-menu-item key="3"><router-link to="/boards">게시판</router-link></a-menu-item>
            <a-menu-item key="4"><router-link to="/">계획 만들기</router-link></a-menu-item>
          </div>
          <div v-if="store.loginStatus">
            <a-menu-item key="1" @click="logout()">로그아웃</a-menu-item>
            <a-menu-item key="2"
              ><router-link to="/users/myPage">나의 계획</router-link></a-menu-item
            >
            <a-menu-item key="3"><router-link to="/boards">게시판</router-link></a-menu-item>
            <a-menu-item key="4"
              ><router-link :to="{ name: 'plan-detail-regist' }"
                >계획 상세 만들기</router-link
              ></a-menu-item
            >
            <!-- <a-menu-item key="5"
              ><router-link to="/boards/regist">게시글 만들기</router-link></a-menu-item
            > -->
          </div>
        </a-menu>
      </a-layout-header>
      <!-- <a-layout-footer style="text-align: center">
      Ant Design ©2018 Created by Ant UED
    </a-layout-footer> -->
    </a-layout>
  </nav>
</template>
<style lang="scss" scoped>
.header {
  background-color: white;
  img {
    width: 10rem;
    height: 4rem;
  }
}
</style>
