<script setup>
import KakaoMap from '@/components/Trip/KakaoMap.vue';
import ScheduleList from '@/components/Trip/ScheduleList.vue';
import SearchDrawerItem from '../components/Trip/item/SearchDrawerItem.vue';

import { ref } from 'vue';

const drawerOpen = ref(true); //drawer가 열리고 닫힘
const drawerWidth = '600px'; // drawer의 크기를 지정하고, 자식 컴포넌트에게 전달한다.

const handleDrawerOpen = (open) =>{
    drawerOpen.value = open; // drawer Open 값 넣어준다.
}

</script>
<template>
    <!--Trip View 페이지-->
    <a-row class="container">
        <!--1. 카카오맵 컴포넌트 확인 가능-->
        <a-col class="left-side" :span="19">
            <KakaoMap />
            <!--Drawer 옵션-->
            <SearchDrawerItem
                :open="drawerOpen"
                :drawer-width="drawerWidth"
                @update:open="handleDrawerOpen"
            />
            <!--맵 위에 떠있는 요소들
                drawer가 열리면, css 효과로 버튼 바도 옆으로 밀린다.(transaction 적용)
            -->
            <div class="map-header" :class = "{'is-open' : drawerOpen}">
                <!--Drawer 여는 토글 버튼-->
                <a-button type="primary" danger size="large"
                        @click="drawerOpen = !drawerOpen" 
                        style = "margin-right : 1rem;">
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
            <!--여행 일정 정하기-->
            <a-col class="upper">
                <TripInfo />
            </a-col>
            <!--전체 스케줄 정하기-->
            <a-col class="main">
                <ScheduleList />
            </a-col>
            <!--하단의 저장 버튼-->
            <a-col class="bottom">
                <template #icon>
                    <CheckCircleOutlined />
                </template>
                <a-button class="save-btn" type="primary" shape="round" size="large"
                    style="width : 200px; height : 50px">저장하기</a-button>
            </a-col>
        </a-col>
    </a-row>
</template>

<style scoped lnag = "sass">
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
        /* 상우하좌*/
        padding: 2rem, 0; /* 패딩은 상하만 준다.*/
        margin-top: 2.5rem;
        margin-left : 4rem; /* 왼쪽으로부터 마진값이 존재. */
        z-index: 1000; /* 입체적으로 위에 노출하는 방법 */
        /* 트랜잭션 이동 */
        transition : transform 0.3s ease;
    }
    .map-header.is-open{
    transform: translateX(38rem); /**draw 너비만큼 이동/ */
    }
}

/* 옆에 사이드 바 생기는 부분 */
.right-side {
    display: flex;
    flex-direction: column;
    height: 100%;
    /*부모높이 */

    .upper {
        /* 위치 정렬 */
        width: 100%;
        height: 30%;
        background-color: aquamarine;
        display: flex;
        /* 텍스트 중앙 정렬 */
        justify-content: center;
        align-items: center;
        text-align: center;
        /*텍스트를 위한 여백 설정 */
        padding: 10%;
    }

    .main {
        /*위치 정렬*/
        width: 100%;
        height: 60%;
        background-color: blueviolet;
        /* 중앙 정렬 */
        display: flex;
        /* justify-content: center; */
        /*중앙 정렬을 위한 여백과 설정 */
        padding: 2rem 3rem 1rem 3rem;
        /*상우 하좌 */
    }

    .bottom {
        /*위치 정렬 */
        width: 100%;
        height: 10%;
        padding: 1rem 1.5rem 2rem;
        /*상 하 (좌우) */
        /*중앙 정렬 */
        display: flex;
        justify-content: center;
        align-items: center;
    }
}

/* 저장 버튼 커스텀하기 */
.save-btn {
    /* padding: 10px 20px; */
    /* 원하는 패딩으로 조절 */
    font-size: 16px;
    /* 원하는 폰트 크기로 조절 */
    border-radius: 5px;
    /* 원하는 테두리 반경으로 조절 */
    /* 기타 원하는 CSS 속성 추가 */
}

.icons-list :deep(.anticon) {
    margin-right: 6px;
    font-size: 24px;
}
</style>
