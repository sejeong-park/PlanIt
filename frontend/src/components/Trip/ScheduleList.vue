<script setup>
import TripInfo from "@/components/Trip/TripInfo.vue"
import { ref } from 'vue';

const activeKey = ref(['1']);
const text = `A dog is a type of domesticated animal.Known for its loyalty and faithfulness,it can be found as a welcome guest in many households across the world.`;
const customStyle =
    'width : 100%; background: #f7f7f7;border-radius: 4px;margin-bottom: 24px;border: 0;overflow: hidden';


</script>

<template>
    <!--Plan-->
    <!--세부 일정을 펼치는 리스트-->
    <a-col class="schedule">
        <a-collapse class="trip-list" v-model:activeKey="activeKey" :bordered="false">
            <template #expandIcon="{ isActive }">
                <caret-right-outlined :rotate="isActive ? 90 : 0" />
            </template>
            <a-collapse-panel key="1" header="This is panel header 1" :style="customStyle">
                <p>{{ text }}</p>
            </a-collapse-panel>
            <a-collapse-panel key="2" header="This is panel header 2" :style="customStyle">
                <p>{{ text }}</p>
            </a-collapse-panel>
            <a-collapse-panel key="3" header="This is panel header 3" :style="customStyle">
                <p>{{ text }}</p>
            </a-collapse-panel>
        </a-collapse>
    </a-col>
</template>

<style scoped lang = "scss">
.schedule {
    margin: 2rem, 2rem, 1rem, 2rem
        /*상우하좌 */
}
</style>
