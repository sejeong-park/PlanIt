<script setup set >

import axios from 'axios';
import region from '@/util/region';
import {defineProps, ref, onMounted} from 'vue'; 
const {VITE_OPEN_API_SERVICE_KEY, VITE_TRIP_INFO_KOR_API_URL} = import.meta.env;
// 부모 컴포넌트로부터 버튼의 상태를 전달 받기 위함
const props = defineProps({
    open : Boolean,
    drawerWidth : String
})

// region 지역 나오는 정보
const regionList = ref(region); // region.js에서 export 해온 값
const regionResult = ref([]); // 사용자가 선택한 region의 값


const searchList = ref([
            {
                "addr1": "서울특별시 강남구 테헤란로 109",
                "addr2": "",
                "areacode": "1",
                "booktour": "",
                "cat1": "A05",
                "cat2": "A0502",
                "cat3": "A05020300",
                "contentid": "2685274",
                "contenttypeid": "39",
                "createdtime": "20201124213322",
                "firstimage": "http://tong.visitkorea.or.kr/cms/resource/49/2684649_image2_1.jpg",
                "firstimage2": "http://tong.visitkorea.or.kr/cms/resource/49/2684649_image2_1.jpg",
                "cpyrhtDivCd": "Type3",
                "mapx": "127.0290211120",
                "mapy": "37.4988880116",
                "mlevel": "6",
                "modifiedtime": "20210315185814",
                "sigungucode": "1",
                "tel": "02-2051-1477",
                "title": "갓덴스시 강남"
            },
            {
                "addr1": "서울특별시 강남구 역삼동",
                "addr2": "(역삼동)",
                "areacode": "1",
                "booktour": "0",
                "cat1": "A02",
                "cat2": "A0203",
                "cat3": "A02030400",
                "contentid": "264570",
                "contenttypeid": "12",
                "createdtime": "20050623224701",
                "firstimage": "http://tong.visitkorea.or.kr/cms/resource/08/1984608_image2_1.jpg",
                "firstimage2": "http://tong.visitkorea.or.kr/cms/resource/08/1984608_image3_1.jpg",
                "cpyrhtDivCd": "Type3",
                "mapx": "127.0281573537",
                "mapy": "37.4970465429",
                "mlevel": "6",
                "modifiedtime": "20230822163704",
                "sigungucode": "1",
                "tel": "",
                "title": "강남"
            },
            {
                "addr1": "경기도 광주시 새말길 353",
                "addr2": "",
                "areacode": "31",
                "booktour": "",
                "cat1": "A03",
                "cat2": "A0302",
                "cat3": "A03020700",
                "contentid": "131674",
                "contenttypeid": "28",
                "createdtime": "20030522090000",
                "firstimage": "",
                "firstimage2": "",
                "cpyrhtDivCd": "",
                "mapx": "127.1823575630",
                "mapy": "37.3869398674",
                "mlevel": "6",
                "modifiedtime": "20221227144356",
                "sigungucode": "5",
                "tel": "031-719-0300",
                "title": "강남 300 컨트리클럽"
            },
            {
                "addr1": "서울특별시 강남구 영동대로 513",
                "addr2": "(삼성동)",
                "areacode": "1",
                "booktour": "0",
                "cat1": "A02",
                "cat2": "A0202",
                "cat3": "A02020200",
                "contentid": "2456536",
                "contenttypeid": "12",
                "createdtime": "20161214231140",
                "firstimage": "",
                "firstimage2": "",
                "cpyrhtDivCd": "",
                "mapx": "127.0591318945",
                "mapy": "37.5118092746",
                "mlevel": "6",
                "modifiedtime": "20230503114647",
                "sigungucode": "1",
                "tel": "",
                "title": "강남 마이스 관광특구"
            },
            {
                "addr1": "서울특별시 강남구 압구정로 161",
                "addr2": "",
                "areacode": "1",
                "booktour": "0",
                "cat1": "A02",
                "cat2": "A0203",
                "cat3": "A02030400",
                "contentid": "1949905",
                "contenttypeid": "12",
                "createdtime": "20140923030533",
                "firstimage": "http://tong.visitkorea.or.kr/cms/resource/75/1258175_image2_1.jpg",
                "firstimage2": "http://tong.visitkorea.or.kr/cms/resource/75/1258175_image3_1.jpg",
                "cpyrhtDivCd": "Type3",
                "mapx": "127.0264344408",
                "mapy": "37.5269874797",
                "mlevel": "6",
                "modifiedtime": "20230214144028",
                "sigungucode": "1",
                "tel": "",
                "title": "강남 시티투어 (트롤리버스)"
            },
            {
                "addr1": "서울 강남구 테헤란로111 4층",
                "addr2": "",
                "areacode": "1",
                "booktour": "",
                "cat1": "A04",
                "cat2": "A0401",
                "cat3": "A04011000",
                "contentid": "2917990",
                "contenttypeid": "38",
                "createdtime": "20221030150609",
                "firstimage": "http://tong.visitkorea.or.kr/cms/resource/38/2879838_image2_1.jpg",
                "firstimage2": "http://tong.visitkorea.or.kr/cms/resource/38/2879838_image3_1.jpg",
                "cpyrhtDivCd": "Type3",
                "mapx": "127.0292960850",
                "mapy": "37.4989380108",
                "mlevel": "6",
                "modifiedtime": "20221124144408",
                "sigungucode": "1",
                "tel": "",
                "title": "강남12의원"
            },
            {
                "addr1": "전라남도 목포시 상리로15번길 1-1",
                "addr2": "",
                "areacode": "38",
                "booktour": "",
                "cat1": "A05",
                "cat2": "A0502",
                "cat3": "A05020100",
                "contentid": "2670270",
                "contenttypeid": "39",
                "createdtime": "20200901235614",
                "firstimage": "http://tong.visitkorea.or.kr/cms/resource/62/2666562_image2_1.jpg",
                "firstimage2": "http://tong.visitkorea.or.kr/cms/resource/62/2666562_image2_1.jpg",
                "cpyrhtDivCd": "Type3",
                "mapx": "126.4166406665",
                "mapy": "34.8131236157",
                "mlevel": "6",
                "modifiedtime": "20210310205014",
                "sigungucode": "8",
                "tel": "061-274-8002",
                "title": "강남가든"
            },
            {
                "addr1": "서울특별시 서초구 신반포로 194",
                "addr2": "",
                "areacode": "1",
                "booktour": "",
                "cat1": "A04",
                "cat2": "A0401",
                "cat3": "A04010600",
                "contentid": "132201",
                "contenttypeid": "38",
                "createdtime": "20031117090000",
                "firstimage": "",
                "firstimage2": "",
                "cpyrhtDivCd": "",
                "mapx": "127.0071923683",
                "mapy": "37.5050868999",
                "mlevel": "6",
                "modifiedtime": "20221101100728",
                "sigungucode": "15",
                "tel": "02-533-2489",
                "title": "강남고속터미널 혼수상가"
            },
            {
                "addr1": "경상북도 안동시 중평길 107-7",
                "addr2": "",
                "areacode": "35",
                "booktour": "",
                "cat1": "A05",
                "cat2": "A0502",
                "cat3": "A05020100",
                "contentid": "2679210",
                "contenttypeid": "39",
                "createdtime": "20201028020123",
                "firstimage": "http://tong.visitkorea.or.kr/cms/resource/43/2677143_image2_1.jpg",
                "firstimage2": "http://tong.visitkorea.or.kr/cms/resource/43/2677143_image2_1.jpg",
                "cpyrhtDivCd": "Type3",
                "mapx": "128.7497711895",
                "mapy": "36.5567753646",
                "mlevel": "6",
                "modifiedtime": "20210311223230",
                "sigungucode": "11",
                "tel": "054-858-5665",
                "title": "강남구이나라"
            },
            {
                "addr1": "대전광역시 서구 둔산로123번길 15",
                "addr2": "(둔산동)",
                "areacode": "3",
                "booktour": "",
                "cat1": "A05",
                "cat2": "A0502",
                "cat3": "A05020100",
                "contentid": "2580169",
                "contenttypeid": "39",
                "createdtime": "20181219191700",
                "firstimage": "",
                "firstimage2": "",
                "cpyrhtDivCd": "",
                "mapx": "127.3874122155",
                "mapy": "36.3519877114",
                "mlevel": "6",
                "modifiedtime": "20231109115550",
                "sigungucode": "3",
                "tel": "042-472-9996",
                "title": "강남면옥"
            }
            ]);


// searchList에서 조회되는 데이터 묶음
const moveMapLocation = (data) => {
    // mapx, mapy 데이터 조회
    console.log("mapx : ", data.mapx, "mapy : ", data.mapy);
    emit('clickLocation', data); // 데이터 자체를 부모 컴포넌트에 올려버림
};

const emit = defineEmit(['clickLocation']); // 업데이트

// 값 단순 확인용
const logCheck = (value) => {
    // getSidoCode(); // 전체 시도 가져오기
    console.log(regionResult.value);
}

onMounted(() => {
});

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
        -->
        <a-drawer 
            :open="open"
            @update:open="value => $emit('update:open', value)"
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
                    @change = "logCheck"/>

                <!--검색-->
                <a-input v-model:value="value" size="large" placeholder="Basic usage" />

                <!--서치 검색 버튼-->
                <a-button danger size="large">일단 검색</a-button>
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
                            <a-button type = "primary" @click = "moveMapLocation(item)">버튼</a-button>
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