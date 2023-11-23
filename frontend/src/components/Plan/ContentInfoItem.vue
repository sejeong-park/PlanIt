<script setup>
import { defineProps, ref } from "vue";
// import {SmileOutlined} from '@ant-design/icons-vue';
// 부모 컴포넌트 (PlanView)로부터 해당 컴포넌트의 데이터를 입력 받기 위함
const props = defineProps({
  resultHeader: Object,
  resultList: Array,
}); // resultList라는 명칭으로

console.log("컴포넌트입니다.");
// console.log(resultList);
</script>

<template>
    <div class="item-container">
        <div class="item-header">
          <div class="item-title">
            {{ props.resultHeader?.title }}
          </div>
          <div class="item-subtitle">
            {{ props.resultHeader?.subtitle }}
          </div>
      </div>
    <div class="item-body">
      <a-list item-layout="horizontal" :data-source="resultList">
        <template #renderItem="{ item, index }">
          <!--아이템 한 요소 한요소-->
          <a-list-item class="item-card">
            <div class="card-content">
              <div class="card-index">
                {{ index + 1 }}
              </div>
              <div class="card-header">
                <div class="card-title">
                  {{ item.title }}
                </div>
                <div class="card-description">
                  <p>
                    {{ item.info1 }}
                  </p>
                  <p> {{ item.info2 }}</p>
                </div>
              </div>
              <div class="card-image">
                <img v-if="item.image" class = "card-image-src" :src = "item.image" alt="{{ item.title }}의 원본 사진 첨부"/>
                <img v-else class = "card-image-src" src="@/assets/img/logo/planit-fullsize.png" alt = "이미지가 존재하지 않습니다."/>
              </div>
            </div>
          </a-list-item>
        </template>
      </a-list>
    </div>
  </div>
</template>

<style lang="scss" scoped>
/* 전체적인 사이즈 잡기 */
.item-container {
  width: 100%;
  height: 100%;
  /* background-color : skyblue; */
  padding: 0.5rem 1rem;

  /* 스타일 지정 */
  /* font-family:'S-CoreDream-3Light' !important;  */

  .item-header {
    /* height : 20%; */
    /* background-color : palegreen; */

    /* 타이틀 가져오기 */
    .item-title {
      height: 50%;
      /* width : 100%; */
      margin-bottom: 1rem;

      /* 폰트 스타일 */
      font-size: 25px;
      font-weight: bold;
      letter-spacing: 0.05rem;
    }

    .item-subtitle {
      height: 50%;
      width: 100%;
      padding-bottom: 1rem;
      font-size: 20px;
      font-weight: 500;
      color: var(--color-gray800);
    }
  }

  .item-body {
    max-height: 50vh;
    height: 80%;
    width: 100%;
    overflow-y: scroll; /* 데이터 많으면 스크롤 */
  }
}

.item-card {
  padding: 1rem 0;
  background-color: white;
  height: 6rem;
  width: 100%;
  height: 100%;
}

/* 카드 관련 내용 */
.card-content {
  display: flex;
  width: 100%;
  height: 100%;

  .card-index {
    width: 10%;

    /* 폰트 디테일 */
    font-size: x-large;
    font-weight: bold;
    color: var(--planit-primary);
  }

  .card-header {
    width: auto;
    display: flex;
    flex-direction: column;
    width: 60%;
    height: auto;

    .card-title {
      margin-bottom: 1rem;
      /* 폰트 정하기 */
      font-size: 22px;
      font-weight: bold;
    }
    .card-description {
      font-size: 16px;
      color: var(--color-gray700);
      font-weight: 600;
      letter-spacing: 0.05px;

      p {
        margin-bottom: 0.5rem;
      }
      padding-right : 0.5rem;
    }
  }

  .card-image {
    width: 30%;
    height: 8rem;
    margin-right : 1rem;
    display : flex;
    justify-content: center;
    text-align: center;
    align-items: center;

    .card-image-src {
      width : 100%;
      height : 100%;
      object-fit : cover; /* div 채우면서 비율 유지 */
      border-radius: 20px;
      box-shadow: 0 0 28px rgba(128, 128, 128, 0.25), 0 10px 10px rgba(128, 128, 128, 0.22);
    }
  }
}
</style>
