<script setup>
import LoginView from "./views/LoginView.vue";
import TheHeadingNavBar from "./components/layout/TheHeadingNavBar.vue";
import LoginComponent from "./components/LoginComponent.vue";
</script>

<template>
  <TheHeadingNavBar />
  <router-view></router-view>
</template>
<style scoped></style>
