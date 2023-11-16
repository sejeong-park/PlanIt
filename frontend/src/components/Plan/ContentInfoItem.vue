<script setup >
import {defineProps, ref} from 'vue'; 
// import {SmileOutlined} from '@ant-design/icons-vue';
// 부모 컴포넌트 (PlanView)로부터 해당 컴포넌트의 데이터를 입력 받기 위함
const props = defineProps({
    resultHeader : Object,
    resultList : Array
}) // resultList라는 명칭으로 

console.log("컴포넌트입니다.");
// console.log(resultList);


</script>

<template>
    <div class = "item-container">
        <div class = "item-header">
            <div class = "item-title">
                {{ props.resultHeader?.title }}
            </div>
            <div class = "item-subtitle">
                {{ props.resultHeader?.subtitle }}
            </div>
        </div>
        <div class = "item-body">

            <a-list item-layout="horizontal" :data-source="resultList">
                <template #renderItem="{ item, index }">
                    <!--아이템 한 요소 한요소-->
                    <a-list-item class = "item-card">
                        <div class = "card-content"> 
                            <div class = "card-index">
                                {{ index + 1 }}
                            </div>
                            <div class = "card-header">
                                <div class = "card-title">
                                    {{ item.title }}
                                </div>
                                <div class = "card-description">
                                    <!-- TODO :: 위치 아이콘 추가하기-->
                                    <p> {{ item.addr1 }} <br> {{ item.addr2 }}</p>
                                    <!-- TODO :: 시간 아이콘 추가하기 --> 
                                    <p>  {{ item.eventstartdate }} ~ {{ item.eventenddate }}</p>
                                </div>
                            </div>
                            <div class = "card-image">
                                <a-image class = "card-image-source" :src = "item.firstimage" />
                            </div>
                            
                        </div>

                    </a-list-item>
                </template>
            </a-list>
            
        </div>
    </div>

</template>

<style scoped>

/* 전체적인 사이즈 잡기 */
.item-container {

    width : 100%;
    height : 100%;
    /* background-color : skyblue; */
    padding : 0.5rem 1rem;

    /* 스타일 지정 */
    /* font-family:'S-CoreDream-3Light' !important;  */

    .item-header {
        /* height : 20%; */
        /* background-color : palegreen; */

        /* 타이틀 가져오기 */
        .item-title  {
            height : 50%;
            /* width : 100%; */
            margin-bottom : 1rem;

            /* 폰트 스타일 */
            font-size: 30px;
            font-weight: bold;
            letter-spacing : 0.05rem;
        }

        .item-subtitle {
            height : 50%;
            width : 100%;
            padding-bottom : 1rem;
            font-size : 20px;
            font-weight: 500;
            color : var(--color-gray800);
        }
    }


    .item-body { 
        max-height: 50vh;
        height : 80%;
        width: 100%;
        overflow-y : scroll; /* 데이터 많으면 스크롤 */
    }
}

.item-card {
    padding : 1rem 0;
    background-color : white;
    height : 6rem; 
    width : 100%;
    height: 100%;
}

/* 카드 관련 내용 */
.card-content {
        display  : flex;
        width : 100%;
        height : 100%;

        .card-index {
            width : 10%;

            /* 폰트 디테일 */
            font-size : x-large;
            font-weight: bold;
            color : var(--planit-primary)
        }

        .card-header {
            width : auto;
            display : flex;
            flex-direction: column;
            width : 60%;
            height : auto;

            .card-title {
                margin-bottom : 0.5rem;
                /* 폰트 정하기 */
                font-size : x-large;
                font-weight: bold;
            }
            .card-description {
                font-size : 16px;
                color : var(--color-gray700);
                font-weight: 600;
                letter-spacing : 0.05px;
                
            }
        }

        .card-image {
            width : 30%;
            height : 8rem;
            /* overflow : 100%; */
            

            .card-image-source {
                /* width : 100%;
                height : 100%; */
                object-fit : cover;
            }
        }

    }

</style>