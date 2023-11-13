<template>
  <a-layout>
    <a-layout-header class="header" style="display: flex; justify-content: flex-end">
      <div class="logo">
        <!-- <img src="@\assets\img\PlanIt!.png" alt="logo" /> -->
      </div>
      <a-menu
        v-model:selectedKeys="selectedKeys1"
        theme="dark"
        mode="horizontal"
        :style="{ lineHeight: '64px', display: 'flex', alignItems: 'center', marginLeft: 'auto' }"
      >
        <a-menu-item key="1"><router-link to="/users/login">로그인</router-link></a-menu-item>
        <a-menu-item key="2"><router-link to="/users/regist">회원가입</router-link></a-menu-item>
      </a-menu>
    </a-layout-header>
    <!-- <a-layout-footer style="text-align: center">
      Ant Design ©2018 Created by Ant UED
    </a-layout-footer> -->
  </a-layout>
</template>
<script></script>
<style scoped>
/* #components-layout-demo-top-side .logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
}

.ant-row-rtl #components-layout-demo-top-side .logo {
  float: right;
  margin: 16px 0 16px 24px;
}

.site-layout-background {
  background: #fff;
} */
</style>
