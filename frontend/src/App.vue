<script setup>
import { RouterView } from "vue-router";
import TheHeadingNavBar from "@/components/layout/TheHeadingNavBar.vue";
</script>

<template>
  <div class="container">
    <!-- 기본 테마 설정 -->
    <a-config-provider
      :theme="{
        token: {
          colorPrimary: '#526AF2',
          colorTextBase: '#2E3440',
        },
      }"
    />

    <TheHeadingNavBar />

    <RouterView />

  </div>
</template>
<style scoped>
.container {
  /*전체 화면은 고정되어있다.*/
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100vh;
  overflow: hidden;
  display: flex;
  flex-direction: column;
}

.content {
  /*헤더를 제외한 나머지 배열 100% */
  flex: 1;
}
</style>
<style scoped></style>
