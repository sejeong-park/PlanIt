<script setup>
import { ref, defineProps } from "vue";
import {CloseOutlined, InfoCircleOutlined} from '@ant-design/icons-vue';
const props = defineProps({
  infoData : Object,
})

const infoData = ref(props.infoData.data);

const showModal = ref(true); // 초기에 모달을 보이게 함
const closeModal = function () {
  // close를 닫는 시에 모달 종료
  showModal.value = false;
  console.log("모달 닫힐 때 SearchStore에 뭐있는지.", props.infoData);
};
</script>

<!-- ModalComponent.vue -->
<template>
  <div v-if="showModal" class="modal">
    <div class = "modal-background"></div>

    
    <div class="modal-content">
      <!-- 모달 내용 -->
      <div class = "modal-header">
        <div class = "modal-title">
          <InfoCircleOutlined class = "info-icon"/>&nbsp; 
          Information
        </div>
        <div class = "modal-close" @click="closeModal">
          <CloseOutlined />
        </div>
        
      </div>
      <div class = "modal-body">
        <div class = "modal-info">
          <div class = "modal-image-container">
            <img v-if="infoData.firstImage" 
                  class = "modal-image-src" 
                  :src = "infoData.firstImage" 
                  alt="{{ infoData.title }}의 원본 사진 첨부"/>
            <img v-else 
                class = "modal-image-src" 
                src="@/assets/img/logo/planit-fullsize-primary.png" 
                alt = "이미지가 존재하지 않습니다."/>
          </div>
          <div class = "modal-information">
            <div class = "info addr">{{ infoData.addr1 }} &nbsp;{{ infoData.addr2 }}</div>
            <div class = "info tel">{{ infoData.tel }}</div>
            <br>
          </div>
        </div>
        <div class = "modal-description">
          <div class = "data-header">
            {{ infoData.title }}
          </div>
          
            <div class = "data-description">
              <p>{{ infoData.overview }}</p>
            </div>
          
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang = "scss">
/* 모달 스타일 */
.modal {
  position : relative;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  opacity: 1;
  display: flex;
  justify-content: center;
  align-items: center;
}
.modal-background::after {
  top : 0;
  left : 0;
  width : 100%;
  height : 100%;
  position : absolute;
  content : "";
  background: rgba(0, 0, 0, 0.5);
  opacity: 0.1;
}
.modal-content {
  width : 50%;
  height : 60%;
  position : relative;
  background: white;

  border-radius: 25px;
  /* box-shadow : 0 0px 10px rgba(128, 128, 128, 0.25), 0 10px 10px rgba(128, 128, 128, 0.22); */

.modal-header {
  width : 100%;
  height : 4rem;
  display : flex;
  justify-content: space-between;
  font-size : 24px;
  padding : 1rem 2rem 1rem 2rem;
  border-bottom : 1px solid var(--color-gray300);
  font-weight: bold;
  color : var(--color-gray700);

  .info-icon {
    color : var(--planit-primary);
    font-weight: 900;
  }

  .modal-close { 
    padding-left : 3rem;
  }

}
  
.modal-body {
  width : 100%;
  height : 90%;
  display : flex;
  padding : 2rem 3rem 3rem 3rem;
}
  
.modal-info {
    width : 50%;
    height : 100%;
    display : flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

    .modal-image-container {
      width : 100%;
      height : 18rem;
      justify-content: center;
      align-items:center;
        .modal-image-src {
          width : 100%;
          height : 100%;
          object-fit : cover;
          border-radius : 20px;
          box-shadow: 0 0 28px rgba(128, 128, 128, 0.25), 0 10px 10px rgba(128, 128, 128, 0.22);
        }
      }
    .modal-information{
      /* width : 100%; */
      /* height : 40%; */
      font-size : 18px;
      font-weight : bold;
      margin-top : 2rem;
    }
  }
}

/* 데이터 찐 정보 */
.modal-description {
  width : 100%;
  height : 100%;
  flex : display;
  flex-direction: column;
  margin-left : 1.5rem;
  .data-header {
    width : 100%;
    height: 15%;
    font-weight: bold;
    font-size : 32px;
    letter-spacing: 1px;
    margin-bottom : 1rem;
    color : var(--planit-dark);
  }
  .data-description{
    max-height : 350px;
    padding-right : 2rem;
    overflow-y: scroll;
    font-size : 18px;
    font-weight: 600;
    letter-spacing: 0.05;
    color : var(--color-gray800);
    line-height: 1.8;

  }
}



</style>
