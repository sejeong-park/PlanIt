<script setup>
import { RouterView } from 'vue-router';
import HeaderComponent from '@/components/layout/HeaderComponent.vue';
</script>

<template>
    <div class="container">
        <HeaderComponent />
        <a-row class="content">
            <RouterView />
        </a-row>
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
