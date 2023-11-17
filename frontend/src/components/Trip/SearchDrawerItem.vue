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
        <!--Drawer 밀기
            1. open 변수 -> 부모 컴포넌트로부터 열리고 닫힘의 T/F
            2. width -> 부모 컴포넌트로 전달 받은 drawer의 넓이
            3. get-container -> Header를 감싸느냐, 아니냐 (True 지정 시, 전체 height 100vh)
            4. style : (:style="{ position: 'absolute', height : '100%' }" ) 다음 태그가 있었으나, 문제가 없어 생략함 (임시)
            5. mask : drawer가 열리고도 나머지 배경이 mask가 씌워지느냐 안씌워지느냐
            6. title -> 컴포넌트 제목
            7. placement -> 어느쪽으로 열리느냐

            <지운 옵션들>
            title="여행지 찾기" 
            :headerStyle="{padding : '5px'}"
            :closable="true"
            @update:open="value => $emit('update:open', value)"
        -->
        <a-drawer 
            :open="open"
            :width="drawerWidth"
            :get-container="false" 
            :mask="false"
            :closable="false"
            :bodyStyle = "{ paddingTop: '0px', marginTop : '2.5rem'}"
            placement="left">
            

            <!-- 1. 지도에 위치값 표시할 값들 찾기 -->
            <a-space class = "search-form">


                <!-- 시도 구군 -->
                <!--@change : 값의 변경이 완료 된 후에 바뀜-->
                <a-cascader size = "large" 
                    v-model:value="regionResult" 
                    bordered = "true"
                    :options="regionList" 
                    placeholder="Please select" 
                    @change = "setSearchRegion"
                />

                <!--검색-->
                <a-input v-model:value="value" size="large" placeholder="Basic usage" />

                <!--서치 검색 버튼-->
                <a-button
                    danger 
                    size="large" 
                    @click = "search"
                >
                    일단 검색
                </a-button>


            </a-space>

            <!-- 2. 서치한 값 기반 관광지 정보 -->
            <div class = "attraction-information-list">
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
                            <!-- 맨 하단 디스크립션-->
                            <a-list-item-meta class = "item-info"
                                description="Ant Design, a design language for background applications, is refined by Ant UED Team"
                                style = "width : 80px;"
                            >
                                <!-- 제목-->
                                <template #title>
                                    <a class = "item-title" href="https://www.antdv.com/">
                                        {{ item.title }}
                                    </a>
                                </template> 
                                <!--미리보기-->
                                <template #avatar>
                                    <!--미리보기 -> 이미지 싱크 넣기-->
                                    <a-image
                                        :width="200"
                                        :height="150"
                                        :preview = "false"
                                        :src="item.firstimage"
                                    />
                                </template>
                            </a-list-item-meta>

                            <!--버튼을 클릭하면 카카오맵의 위치가 변경된다.-->
                            <!-- TODO :: UI 작업 안했기 때문에 그냥 쓴다 대신, 나중에 바꿔줘야돼-->
                            <div style="display : flex; flex-direction: column; width : 80px;">
                                <a-button type = "primary" @click = "moveMapLocation(item)">버튼</a-button>
                                <a-button type = "primary" @click = "addAttractionToDail(item)" >일정 추가</a-button>
                            </div>
                        </a-list-item>
                    </template>
                </a-list>
            </div>
        </a-drawer>
</template>
<style scoped>
.search-form{
    display : flex;
    width : 100%;
    justify-content: center; /* 가운데 정렬 */
    margin: 1rem 0 2rem 0;
}

.list-item{
    margin : 0.5rem;
    

    .item-title {
        height : 3rem;
    }
    .item-info{
        width  : 200px;
    }

    
    /* 아이템 안에 타이틀 */

}
</style>