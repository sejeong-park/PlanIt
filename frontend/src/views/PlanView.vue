<script setup>
import PlanSchedule from '@/components/Plan/PlanSchedule.vue';
import ContentInfoItem from '@/components/Plan/ContentInfoItem.vue';



</script>
<template>
    <div class = "container">
        
        <div class = "plan-section">
            <!--플랜 짜는거-->
            <PlanSchedule />
        </div>
        <div class = "information-section">
            <div class = "hotplace-section">
                <ContentInfoItem />
            
            </div>
            <div class = "festival-section">
                <ContentInfoItem /> 
            </div>
        </div>
    
    </div>
    


</template>
<style scoped lang = "scss">

.container {    
    /* background-color : blue; */
    width : 70%;
    height : 100%;
    margin : 0 auto; /* 중앙정렬을 위해 자동 마진 */

    .plan-section {
        width : 100%;
        height : 40%;
        /*background-color: pink;*/
        padding : 3% 5% 2% 5%; /* 상우하좌 */
    }
    .information-section { 
        width : 100%;
        height : 60%;
        display : flex;
        /* background-color : skyblue; */
        .hotplace-section {
            width : 50%;
            height : 100%;
            padding: 0 1% 2% 5%; /* 상우 하좌 */
        }

        .festival-section {
            width : 50%;
            height : 100%;
            padding : 0 5% 2% 1%; /* 상우 하좌 */
        }
    }
}


</style>