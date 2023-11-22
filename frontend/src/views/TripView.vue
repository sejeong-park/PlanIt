<script setup>
import {ref} from "vue";
import {useRouter} from "vue-router";
import {usePlanStore} from "@/stores/plan";
import KakaoMap from '@/components/Trip/KakaoMap.vue';
import SearchDrawerItem from '@/components/Trip/SearchDrawerItem.vue';
import PlanDetail from "@/components/Trip/PlanDetail.vue"

// drawer를 여는 함수
const drawerOpen = ref(true); //drawer가 열리고 닫힘
const drawerWidth = '550px'; // drawer의 크기를 지정하고, 자식 컴포넌트에게 전달한다.

const planStore = usePlanStore(); // pinia에 저장된 planKey 불러온다.

// 컴포넌트에 존재하는 저장버튼을 클릭했을 시 View로 돌아온다.
const router = useRouter();
function onSaved() {
    console.log("planKey!!!", planStore.planKey);
    router.push({
        name : 'result',
        params : {
            planKey : planStore.planKey
        }
    })
    // planStore.planKey = null; // pinia에서 저장하던 key 저장 후 초기화.
    // planStore.tripScheduleInfo = null;  // 사용했던 정보 종료
}

</script>
<template>
    <!--Trip View 페이지-->
    
    <a-row class="container">
        <!--1. 카카오맵 컴포넌트 확인 가능-->
        <a-col class="left-side" :span="19">
            <!--    카카오맵 지도   -->
            <KakaoMap
                :open="drawerOpen"
                :drawer-width="drawerWidth"
            />
            <!--    Drawer 옵션
                @update:open="handleDrawerOpen" -->
            <SearchDrawerItem
                :open="drawerOpen"
                :drawer-width="drawerWidth"
            />
            <!--맵 위에 떠있는 요소들
                drawer가 열리면, css 효과로 버튼 바도 옆으로 밀린다.(transaction 적용)
            -->
            <div class="map-header shadow" :class = "{'is-open' : drawerOpen}">
                <!--Drawer 여는 토글 버튼-->
                <a-button type="primary" danger size="large"
                        @click="drawerOpen = !drawerOpen" 
                        style = "margin-right : 3rem;">
                    {{ drawerOpen ? 'Close' : 'Open' }}
                </a-button>
                <!-- 선택 옵션 -->
                <a-space class="item" size="middle">
                    <a-button type="primary" size="large" shape="circle">A</a-button>
                    <a-button type="primary" size="large" shape="circle">B</a-button>
                    <a-button type="primary" size="large" shape="circle">C</a-button>
                    <!--선택할 연속-->
                    <a-button type="primary" size="large" shape="circle">D</a-button>
                </a-space>
            </div>
        </a-col>
        <a-col class="right-side" :span="5">
            <!--우측 계획-->
            <PlanDetail @save-plan = 'onSaved'/>
        </a-col>
    </a-row>
</template>

<style scoped lang = "scss">

.container {
    height: 100%;
    /*좌우로 한줄 */
    display: flex;
    flex-direction: row;

}

/* 카카오 맵 API 호출하는 부분 */
.left-side {
    height: 100%;
    position: relative;

    .map-header {
        /* background-color: red; */
        position: absolute;
        display: flex;
        top: 0;
        left: 0;
        margin-top: 2.5rem;
        margin-left : 4rem; /* 왼쪽으로부터 마진값이 존재. */
        z-index: 500; /* 입체적으로 위에 노출하는 방법 */
        /* 백그라운드 */
        padding : 0.5rem 1rem;
        background-color : white;
        border-radius: 15px;

        /* 트랜잭션 이동 */
        transition : transform 0.3s ease;
    }
    .map-header.is-open{
    transform: translateX(550px); /**draw 너비만큼 이동/ */
    }
}

/* 옆에 사이드 바 생기는 부분 */
.right-side {
    display: flex;
    flex-direction: column;
    height: 100%; /* 스크롤을 위해 100vh로 고정 */
}

.shadow {
    -webkit-box-shadow: 0 14px 28px rgba(128, 128, 128, 0.25), 0 10px 10px rgba(128, 128, 128, 0.22);
    -moz-box-shadow: 0 14px 28px rgba(128, 128, 128, 0.25), 0 10px 10px rgba(128, 128, 128, 0.22);
    -ms-box-shadow: 0 14px 28px rgba(128, 128, 128, 0.25), 0 10px 10px rgba(128, 128, 128, 0.22);
    -o-box-shadow: 0 14px 28px rgba(128, 128, 128, 0.25), 0 10px 10px rgba(128, 128, 128, 0.22);
    box-shadow: 0 14px 28px rgba(128, 128, 128, 0.25), 0 10px 10px rgba(128, 128, 128, 0.22);
}

</style>
