<script setup>
import { ref, reactive } from 'vue';
// import {usePlanStore} from '@/stores/plan';

const activeKey = ref(['1']);
const text = `A dog is a type of domesticated animal.Known for its loyalty and faithfulness,it can be found as a welcome guest in many households across the world.`;


// pinia 객체
// const store = usePlanStore();

const handleCollapseChange = (activeKey) => {
    // store.setCurrentCollapsePanel(activeKey)
}


// 사용자 직접 입력 태그 
const addDetail = () => {

}



</script>

<template>
    <!--Plan-->
    <!--세부 일정을 펼치는 리스트-->
    <!--
        handleCollapseChange : pinia에 현재 선택한 값 넣어주기

    -->
    <div class = "collapse-container">
        <a-collapse
            class = "collapse"
            v-model:activeKey="activeKey"
            :bordered="false"
            @change = "handleCollapseChange"
        >
            <template #expandIcon="{ isActive }">
                <caret-right-outlined :rotate="isActive ? 90 : 0" />
            </template>
            <a-collapse-panel class = "collapse-title shadow" key="1" header="This is panel header 1">
                <div class = "card-list">
                    <div class="card">
                        <div class = "card-content">card 1</div>
                    </div>
                    <div class="card">
                        <div class="card-content">겁나 길게 쓰는 테스트지로알아멀아ㅣㅓ리날,ㅓㅣ어라어라ㅓ아ㅓㄹㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ</div>
                    </div>
                    <div class="card">
                        <div class = "card-content">card 1</div>
                    </div>
                    <!--폼 추가를 위한 태그-->
                    <a-button type="dashed" style="width: 100%" @click="addDomain">
                                <PlusOutlined />
                                Add field
                    </a-button>
                </div>
            </a-collapse-panel>
            <a-collapse-panel class = "collapse-title" key="2" header="This is panel header 2">
                <p>
                    <!--증가 감소 폼-->
                    <a-form
                        ref="formRef"
                        name="dynamic_form_item"
                        :model="dynamicValidateForm"
                        v-bind="formItemLayoutWithOutLabel"
                    >
                        <a-form-item
                            v-for="(domain, index) in dynamicValidateForm.domains"
                            :key="domain.key"
                            v-bind="index === 0 ? formItemLayout : {}"
                            :label="index === 0 ? 'Domains' : ''"
                            :name="['domains', index, 'value']"
                            :rules="{
                                required: true,
                                message: 'domain can not be null',
                                trigger: 'change',
                        }"
                        >
                        <a-input
                            v-model:value="domain.value"
                            placeholder="please input domain"
                            style="width: 60%; margin-right: 8px"
                        />
                        <MinusCircleOutlined
                            v-if="dynamicValidateForm.domains.length > 1"
                            class="dynamic-delete-button"
                            @click="removeDomain(domain)"
                        />
                        </a-form-item>
                        <!--폼 추가를 위한 태그-->
                        <a-form-item v-bind="formItemLayoutWithOutLabel">
                            <a-button type="dashed" style="width: 60%" @click="addDomain">
                                <PlusOutlined />
                                Add field
                            </a-button>
                        </a-form-item>


                        <a-form-item v-bind="formItemLayoutWithOutLabel">
                        </a-form-item>
                    </a-form>
                
                </p>
            </a-collapse-panel>
            <a-collapse-panel class = "collapse-title" key="3" header="This is panel header 3">
                <p>{{ text }}</p>
            </a-collapse-panel>
            <a-collapse-panel class = "collapse-title" key="3" header="This is panel header 3">
                <p>{{ text }}</p>
            </a-collapse-panel>

        </a-collapse>
    </div>
</template>

<style scoped lang = "scss">
.collapse-container{
    width : 100%;
    margin: 3rem 3rem 1rem 3rem;
    overflow-y : scroll; /*만약 날짜가 너무 길어질 경우 스크롤로 대체한다. */
}
/*스크롤 숨기기 */
.collapse-container::-webkit-scrollbar {
    display : none;
}
.collapse {
    width  : 100%;
    background: rgb(255, 255, 255);
    border-radius: 10px;
    /* 기간 내용 */
    .collapse-title{
        background-color: rgba(180, 198, 208, 1);;
        border-radius: 10px;
        margin-bottom : 24px;
        border : 0;
        overflow : hidden;
    }

    .card {
        padding: 0.5rem 1rem;
        margin-bottom : 1rem;
        height : 3rem;
        display : flex;
        align-items: center;

        /*detail Background에 관한 스타일 옵션 */
        background: white ;
        border-radius: 10px;
        border : 0;
        box-shadow: 0 1px 0 rgba(black, .2);

        /* 카드 내용이 너무 길어졌을 때 생기는 옵션 변경하기 */
        overflow-x : scroll;
        white-space: nowrap; 
        /* 카드에 적는 내용 */
        .card-content {
            min-height : 3rem;
            align-items : center;
            display : flex;
        }
    }
}


.dynamic-delete-button {
    cursor: pointer;
    position: relative;
    top: 4px;
    font-size: 24px;
    color: #999;
    transition: all 0.3s;
}
.dynamic-delete-button:hover {
    color: #777;
}
.dynamic-delete-button[disabled] {
    cursor: not-allowed;
    opacity: 0.5;
}

/*그림자 지정 클래스 */
/* .shadow {
    -webkit-box-shadow: 0 10px 6px -6px #777;
        -moz-box-shadow: 0 10px 6px -6px #777;
            box-shadow: 0 10px 6px -6px #777;
} */
</style>
