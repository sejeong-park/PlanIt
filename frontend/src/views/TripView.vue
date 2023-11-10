<script setup>
import KakaoMap from '@/components/Trip/KakaoMap.vue';
import ScheduleList from '@/components/Trip/ScheduleList.vue';

</script>

<template>
    <!--Trip View 페이지-->
    <a-row class="container">
        <!--1. 카카오맵 컴포넌트 확인 가능-->
        <a-col class="map" :span="19">
            <KakaoMap />
            <!--2. Side Bar 클릭하면 나오는 버튼-->
            <a-float-button :style="{
                left: '94px',
            }">
                <template #tooltip>
                    <div>Documents</div>
                </template>
            </a-float-button>
        </a-col>
        <a-col class="left-side" :span="5">
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

.map {
    height: 100%;
}

.left-side {
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
