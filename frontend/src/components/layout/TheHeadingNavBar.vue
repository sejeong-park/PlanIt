<script></script>
<template>
  <nav>
    <a-layout>
      <a-layout-header
        class="header"
        style="display: flex; justify-content: flex-end"
      >
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
          <a-menu-item key="1"
            ><router-link to="/users/login">로그인</router-link></a-menu-item
          >
          <a-menu-item key="2"
            ><router-link to="/users/regist">회원가입</router-link></a-menu-item
          >
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
