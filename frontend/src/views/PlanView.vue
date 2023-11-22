<script setup>
import PlanSchedule from '@/components/Plan/PlanSchedule.vue';
import ContentInfoItem from '@/components/Plan/ContentInfoItem.vue';
import {ref, onMounted } from "vue";
import { listFestival } from "@/api/tripAttraction";
import { convertDateFormat } from "@/util/dateUtil";
import mockupdata from "@/util/mockup-data";

let today = new Date(); // 오늘 날짜
const startStr = ref('');
const endStr = ref('');
const todayDate = today.toISOString().split('T')[0];
let oneMonthLater = new Date(today.setMonth(today.getMonth() + 1)); // 한달 후
const oneMonthLaterDate = oneMonthLater.toISOString().split('T')[0];

// 페스티벌 정보 가져오기
const param = ref({})

const festivalHeader = ref({});
const festivalList = ref([]);
const getFestivalList = () => {
    param.value.eventStartDate = convertDateFormat(todayDate, 'YYYYMMDD');// 오늘의 YYYYDDMM
    param.value.eventEndDate = convertDateFormat(oneMonthLaterDate, 'YYYYMMDD');// 한달 후의 YYYYDDMM

    console.log(param);
    listFestival(
        param.value, // param 설정
        ({data}) => {
            const responseData = data.response.body.items.item;
            responseData.forEach((item) => {
                const object = {
                    title : item.title,
                    info1 : item.addr1 + " " + item.addr2,
                    info2 : item.eventstartdate + " ~ " + item.eventenddate,
                    image : item.firstimage
                };
                festivalList.value.push(object);
            })
            // festivalList.value = data.response.body.items.item; // result api 결과
            console.log(festivalList.value);
        },
        (error) => {
            console.log(error)
        }
    )
}

const recommendHeader = ref({});
const recommendList = ref(mockupdata);

onMounted(() => {
    getFestivalList(); //  해제 시키기
    // 출력해줄 날짜 나타내기
    startStr.value = convertDateFormat(todayDate, 'YY.MM.DD');
    endStr.value = convertDateFormat(oneMonthLaterDate, 'YY.MM.DD');

    const festivalSubtitle = startStr.value + "부터 " + endStr.value + "까지의 다양한 축제 정보"
    festivalHeader.value = {
        title: "축제의 매력🎉 그리고 여행의 설렘🚘",
        subtitle: festivalSubtitle
    };

    recommendHeader.value = {
        title : "서울에서 떠나는 주말 여행 🏝",
        subtitle : "이번주는 잠시 일상을 떠나보는 건 어떤가요?"
    }

})


</script>
<template>
    <div class = "container">
        
        <div class = "plan-section">
            <!--플랜 짜는거-->
            <PlanSchedule />
        </div>
        <div class = "information-section">
            <div class = "hotplace-section">
                <ContentInfoItem :resultList = "recommendList" :resultHeader = "recommendHeader"/> 
            </div> 
            <div class = "festival-section">
                <ContentInfoItem :resultList = "festivalList" :resultHeader = "festivalHeader"/> 
            </div>
        </div>
    
    </div>

</template>
<style scoped lang = "scss">

.container {    
    /* background-color : blue; */
    width : 60%;
    height : 100%;
    margin : 0 auto; /* 중앙정렬을 위해 자동 마진 */

    .plan-section {
        width : 100%;
        height : 40%;
        /*background-color: pink;*/
        padding : 3% 5% 5% 5%; /* 상우하좌 */
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