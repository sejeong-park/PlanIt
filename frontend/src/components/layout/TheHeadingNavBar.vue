<script setup>
import { useUserStore } from "@/stores/user";
import axios from "axios";
import { useRouter } from "vue-router";
import { UserOutlined } from "@ant-design/icons-vue";

const router = useRouter();

const store = useUserStore();
// console.log("loginStatus : ", store.loginStatus);

const logout = function () {
  // console.log("storeuserName before", store.userName);

  // "login" 키에 해당하는 sessionStorage 값 제거
  sessionStorage.removeItem("login");
  // console.log(axios.defaults.headers.common["Authorization"]);
  axios.defaults.headers.common["Authorization"] = "";
  // console.log(axios.defaults.headers.common["Authorization"]);
  store.loginStatus = false;
  store.userName = "";
  // console.log("storeuserName after", store.userName);
  // console.log(store.loginStatus);
  router.replace({ name: "main" });
};
</script>

<template>
  <nav class="navbar">
    <a-layout>
      <a-layout-header class="header">
        <a href="/" class="nav-logo">
          <img class="planit-logo" src="@\assets\img\PlanIt.png" alt="logo" />
          <div class="planit-name">Plan <span>I</span>t</div>
        </a>
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
            <a-menu-item key="1">
              <router-link to="/users/login">로그인</router-link>
            </a-menu-item>
            <a-menu-item key="2">
              <router-link :to="{ name: 'user-regist' }"
                >회원가입</router-link
              ></a-menu-item
            >
            <a-menu-item key="3"
              ><router-link :to="{ name: 'board-list' }"
                >공유게시판</router-link
              ></a-menu-item
            >
          </div>
          <div v-if="store.loginStatus">
            <!-- <div>
              <a-avatar :size="8">
                <template #icon><UserOutlined /></template>
              </a-avatar>
            </div> -->
            <span
              ><strong>{{ store.userName }}</strong
              >님</span
            >
            <a-menu-item key="1"
              ><router-link :to="{ name: 'plans' }"
                >여행 계획 짜기</router-link
              ></a-menu-item
            >
            <a-menu-item key="2"
              ><router-link to="/boards">공유게시판</router-link></a-menu-item
            >
            <a-menu-item key="3"
              ><router-link :to="{ name: 'user-mypage' }"
                >나의 계획</router-link
              ></a-menu-item
            >
            <a-menu-item key="4" @click="logout()">로그아웃</a-menu-item>
          </div>
        </a-menu>
      </a-layout-header>
    </a-layout>
  </nav>
</template>
<style lang="scss" scoped>
/* 로고 부분 */

.nav-logo {
  display: flex;
  padding: 0.7rem;
  align-items: center;

  .planit-logo {
    width: 100%;
    height: 100%;
  }

  .planit-name {
    padding-top: 5px;
    display: flex;
    align-items: center;
    justify-content: center;
    margin-left: 0.3rem;
    font-family: "TheJamsil6ExtraBold";
    font-size: 32px;
    color: var(--planit-primary);
    -webkit-text-stroke: 2px var(--planit-primary);
    // text-shadow: -1px 0 var(--planit-primary), 0 1px var(--planit-primary), 1px 0 var(--planit-primary), 0 -1px var(--planit-primary);

    span {
      color: var(--planit-point-2);
      font-weight: 900;
      -webkit-text-stroke: 3px var(--planit-point-2);
    }
  }
}

/* Antd custom */
:deep(.ant-menu) {
  font-size: 18px;
  border-bottom: none !important;
}
:deep(.ant-menu-item) {
  // padding : 0 2rem;
  margin-left: 0.3rem;
}

.header {
  display: flex;
  justify-content: flex-end;
  background-color: white;
  padding: 0 5rem;
}
</style>
