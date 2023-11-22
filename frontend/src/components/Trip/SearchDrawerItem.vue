<script setup set >
import region from '@/util/region';
import { listTripAttraction } from "@/api/tripAttraction";
import {defineProps, ref, watch} from 'vue'; 
import {useTripSearchStore} from '@/stores/trip';
import {usePlanStore} from '@/stores/plan';
import InfoModal from '@/components/Trip/item/InfoModal.vue';
import {InfoCircleOutlined, PlusCircleOutlined} from '@ant-design/icons-vue';

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

const param = ref({})
const searchList = ref([]);

const getTripAttraction = () => {
    listTripAttraction(
        param.value,
        (response) => {
            searchList.value = response.data
            tripSearchStore.setTotalSearchLocation(searchList.value);
            console.log("조회 리스트 :: ", searchList.value);
        },
        (error) => {
            console.log(error);
        }
    )
}


// 지역 설정
const setSearchRegion = () => {
    // 지역을 설정 시, param 지정
    if (regionResult.value[0]!= '0'){
        param.value.sidoCode = regionResult.value[0];
        // 구군까지 지정되어있으면
        if (regionResult.value[1] != 0){
            param.value.gugun = regionResult.value[1]; 
        }
    }
}

// 검색어를 입력 받는다.
watch(searchKeyword, () => {
    param.value.keyword = searchKeyword;
})

// 검색 버튼을 클릭한다.
const search = () => {
    console.log("Search 버튼 클릭");
    console.log("param :: ", param.value);
    getTripAttraction(); // drawer의 리스트를 불러온다.
    //param.value = null;
}

// searchList에서 조회되는 데이터 묶음
const moveMapLocation = (data, index) => {
    console.log("trip 갈 지역 버튼 클릭 : ", data);
    tripSearchStore.setFocusLocation(data, index); // 객체를 넣는다. 
};


// 일정 추가 Button을 클릭 했을 경우 SchduleList에 등록해주기 위한 데이터
const planDetailStore = usePlanStore();

// 선텍 시, 현재 클릭된 Schdule Date에 추가된다.
const addAttractionToDail = (data) => {
    console.log("Attraction : " + data.title + "선택 -> 일정에 저장");
    planDetailStore.addClickedAttractionInfo(data);
}

const infoOpen = ref(false); // 열리지 않은 상태
const infoData = ref();
// 디테일한 여행지 정보를 보여준다.
const showDetailInfoModal = (data, index) => {
    console.log("선택한 info 버튼의 index : ",  index);
    infoOpen.value = true; // 열린 상태로 변경
    tripSearchStore.setFocusLocation(data, index);
    infoData.value = {
        index : index,
        data : data
    }
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
                            v-model:value.lazy="regionResult" 
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
                    <template #renderItem="{item, index}">
                        <!-- 반복의 아이템 (밑에서부터 왼쪽)-->
                        <a-list-item class = "list-item">
                            <div class = "card-div" @click = "moveMapLocation(item, index)">
                                <div class = "card-section">
                                    <div class = "card-img">
                                        <img v-if="item.firstImage" class = "card-image-src" :src = "item.firstImage" alt="{{ item.title }}의 원본 사진 첨부"/>
                                        <img v-else class = "card-image-src" src="@/assets/img/logo/planit-fullsize-primary.png" alt = "이미지가 존재하지 않습니다."/>
                                    </div>
                                    <div class = "card-content">
                                        <div class = "card-title">
                                            {{ item.title }}
                                        </div>
                                        <div class = "card-description">
                                            {{ item.addr1 }}<br>
                                            {{ item.addr2 }}<br>
                                        </div>
                                        <div class = "card-btn">
                                            <!--상세 정보를 보여주는 모달-->
                                            <a-tooltip title="상세 정보 보기">
                                                <a-button class = "info-btn" type="default" shape="circle" size="20" @click.stop.prevent = "showDetailInfoModal(item, index)">
                                                    <InfoCircleOutlined />
                                                </a-button>
                                            </a-tooltip>
                                            <InfoModal v-if="infoOpen" @click="infoOpen = false" :infoData = infoData ></InfoModal>
                                            <!--상세 일정에 등록하는 버튼-->
                                            <a-tooltip title="일정에 추가하기">
                                                <a-button class = "add-btn" type="primary" shape="circle" size="20" @click = "addAttractionToDail(item)">
                                                    <PlusCircleOutlined />
                                                </a-button>
                                            </a-tooltip>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </a-list-item>
                    </template>
                </a-list>
        </a-drawer>
</template>
<style scoped lang="scss">

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
        padding : 1rem;
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
        height : auto;
        margin-right : 0.5rem;
        display : flex;
        justify-content: center;
        text-align: center;
        align-items: center;
        
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
        padding-left : 0.5rem;
        position : relative;

        .card-title {
            padding : 0.5rem 0;
            /* 글꼴 관련 속성 */
            font-size : 24px;
            font-weight: 900;
            color : var(--planit-dark);
            letter-spacing: 0.01px;
        }
        .card-description {
            height : auto;
            
            /* 글꼴 내역 */
            font-size : 17px;
            font-weight : 600;
            color : var(--color-gray800);
        }
        .card-btn {
            margin-top : auto;
            bottom : 0;
            right : 0;
            align-self : flex-end;

            .add-btn,
            .info-btn {
                font-size: 24px;
                font-weight: bold;
                text-align: center;
                height : 50px;
                width : 50px;
                margin-left : 0.5rem;
                box-shadow: 0 0 12px rgba(128, 128, 128, 0.25), 0 5px 5px rgba(128, 128, 128, 0.22);
            }
        }
    }
}
</style>