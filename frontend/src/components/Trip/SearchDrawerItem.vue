<script setup set >
import region from '@/util/region';
import { listTripAttraction } from "@/api/tripAttraction";
import {defineProps, ref} from 'vue'; 
import {useTripSearchStore} from '@/stores/trip';
import {usePlanStore} from '@/stores/plan';

// 부모 컴포넌트로부터 버튼의 상태를 전달 받기 위함
const props = defineProps({
    open : Boolean, // drawer의 열려있음 여부
    drawerWidth : String // drawer의 너비
})

// pinia에 저장한 상태 관리 -> KaKaoMap에 넘겨줄 변수들
const tripSearchStore = useTripSearchStore();

// region 지역 나오는 정보
const regionList = ref(region); // region.js에서 export 해온 값
const regionResult = ref(['0']); // 사용자가 선택한 region의 값 (0은 전국 이라는 이름의 default)
const searchKeyword = ref(''); // 사용자의 검색

// 한국 관광공사 API를 이용해 리스트 불러오기
// (크롤링 및 데이터 인입 시 수정 필요)
const param = ref({})
const searchList = ref([]);
const getTripAttraction = () => {
    console.log(param);
    listTripAttraction(
        param.value, // param 설정
        ({data}) => {
            searchList.value = data.response.body.items.item; // result api 결과
            tripSearchStore.setTotalSearchLocation(searchList.value); // pinia에 setting
        },
        (error) => {
            console.log(error)
        }
    )
}

// 지역 설정
const setSearchRegion = () => {
    // 지역을 설정 시, param 지정
    if (regionResult.value[0]!= '0'){
        param.value.areaCode = regionResult.value[0];
        // 구군까지 지정되어있으면
        if (regionResult.value.length > 1){
            param.value.sigunguCode = regionResult.value[1]; 
        }
    }
}

// 검색 버튼을 클릭한다.
const search = () => {
    console.log("Search 버튼 클릭");
    getTripAttraction(); // drawer의 리스트를 불러온다.
}

// searchList에서 조회되는 데이터 묶음
const moveMapLocation = (data) => {
    console.log("trip 갈 지역 버튼 클릭 : ", data);
    tripSearchStore.setFocusLocation(data); // 객체를 넣는다. 
};




// 일정 추가 Button을 클릭 했을 경우 SchduleList에 등록해주기 위한 데이터
const planDetailStore = usePlanStore();

// 선텍 시, 현재 클릭된 Schdule Date에 추가된다.
const addAttractionToDail = (data) => {
    console.log("Attraction : " + data.title + "선택 -> 일정에 저장");
    planDetailStore.addClickedAttractionInfo(data);
}

</script>

<template>
        <!--좌측 검색 창 열기-->
        <a-drawer 
            :open="open"
            :width="drawerWidth"
            :get-container="false" 
            :mask="false"
            :closable="false"
            :bodyStyle = "{ paddingTop: '0px'}"
            placement="left">
            
            <!-- Header : 지도에 표시할 값 찾기 -->
            <template #title>
                <div class = "search-section">
                    <!-- 시도 구군 -->
                    <!--@change : 값의 변경이 완료 된 후에 바뀜-->
                    <div class ="region-form">
                        <a-cascader size = "large" 
                            v-model:value="regionResult" 
                            :bordered = "true"
                            :options="regionList" 
                            placeholder="지역 선택" 
                            @change = "setSearchRegion"
                        />
                    </div>
                    <!--검색-->
                    <div class = "search-form">
                        <a-input v-model:value="searchKeyword" size="large" placeholder="검색어를 입력하세요" />
                    </div>
                    <div class = "search-btn">
                        <!--서치 검색 버튼-->
                        <a-button
                            type = "primary" 
                            size="large" 
                            @click = "search"
                        >
                            검색
                        </a-button>
                    </div>
                </div>
            </template>

            <!-- 2. 서치한 값 기반 관광지 정보 -->
            
                <!--
                    data-source : 데이터 가져올 객체 변수명
                -->
                <a-list 
                    item-layout="horizontal" 
                    :data-source="searchList"
                >
                    <!---searchList.body.items.item-->
                    <template #renderItem="{item}">
                        <!-- 반복의 아이템 (밑에서부터 왼쪽)-->
                        <a-list-item class = "list-item">
                            <div class = "card-div" @click = "moveMapLocation(item)">
                                <div class = "card-section">
                                    <div class = "card-img">
                                        <img v-if="item.firstimage" class = "card-image-src" :src = "item.firstimage" alt="{{ item.title }}의 원본 사진 첨부"/>
                                        <img v-else class = "card-image-src" src="@/assets/img/logo/planit-fullsize-primary.png" alt = "이미지가 존재하지 않습니다."/>
                                    </div>
                                    <div class = "card-content">
                                        <div class = "card-title">
                                            {{ item.title }}
                                        </div>
                                        <div class = "card-content">
                                            {{ item.addr1 }}
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </a-list-item>
                    </template>
                </a-list>
        </a-drawer>
</template>
<style scoped>
.ant-input { 
    border : 1.5px solid var(--planit-primary);
}
:deep(.ant-select-selector) {
    border : 1.5px solid var(--planit-primary) !important;
}

/* header 안 콘텐츠를 채운다. */
.search-section{
    width : 100%;
    height : auto;
    padding : 1rem 1rem 1rem 1rem;

    display : flex;
    justify-content: center; /* 가운데 정렬 */
    .region-form {
        padding-right : 1rem;
        
    }

    .search-form {
        padding-right : 1rem;
    }

}

.a-list {
    width : 100%;
    box-sizing : border-box;
}
/* item list들의 section*/
.list-item{
    padding : 1rem;
    /* Card Section */
    .card-div {
        width : 100%;
        height : 100%;
        padding : 1rem 0 1rem 1.5rem;
        border-radius: 20px; 
    }
    .card-div:hover {
        background-color: rgba(var(--planit-base), 0.5);
        box-shadow: 0 0 28px rgba(128, 128, 128, 0.25), 0 10px 10px rgba(128, 128, 128, 0.22);
    }
}

.card-section {
    box-sizing: border-box;
    width :100%;
    display : flex;
    .card-img {
        width : 35%;
        margin-right : 0.5rem;
        justify-content: end;
        
        .card-image-src {
            width : 150px;
            height : 150px;
            border-radius: 20px;
            box-shadow: 0 0 28px rgba(128, 128, 128, 0.25), 0 10px 10px rgba(128, 128, 128, 0.22);
        }
    }

    .card-content {
        width : 65%;
        display : flex;
        flex-direction: column;

        .card-title {
            padding : 0.5rem 0;
            /* 글꼴 관련 속성 */
            font-size : x-large;
            font-weight: 900;
            color : var(--planit-dark);
            letter-spacing: 0.01px;
        }
        .card-content {
            height : auto;
            
            /* 글꼴 내역 */
            font-size : 16px;
            font-weight : 500;

        }
    }
}
</style>