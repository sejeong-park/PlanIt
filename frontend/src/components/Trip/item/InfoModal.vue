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
            <img v-if="infoData.firstimage" 
                  class = "modal-image-src" 
                  :src = "infoData.firstimage" 
                  alt="{{ infoData.title }}의 원본 사진 첨부"/>
            <img v-else 
                class = "modal-image-src" 
                src="@/assets/img/logo/planit-fullsize-primary.png" 
                alt = "이미지가 존재하지 않습니다."/>
          </div>
          <div class = "modal-information">
            <div class = "info addr1">{{ infoData.addr1 }}</div>
            <br v-if="infoData.addr1">
            <div class = "info addr2">{{ infoData.addr2 }}</div>
            <br v-if="infoData.addr2">
            <div class = "info tel">{{ infoData.tel }}</div>
            <br>
          </div>
        </div>
        <div class = "modal-description">
          <div class = "data-header">
            {{ infoData.title }}
          </div>
          <div class = "data-description">
            <p>저명산은 보통 도드람산으로 더 알려져 있는데 도드람산은 돗(猪) 울음(鳴)이 세월이 지나면서 변형된 것으로 보이며, 옛날 이 산중에 살던 고승(또는 효자라고도 함)이 약초를 캐는데 절벽 위에 몸을 묶은 밧줄이 모서리와의 마찰로 다 끊어져 목숨이 위태롭게 된 것을 난데없이 산돼지가 울어 고승의 목숨을 건져주었다는 전설을 간직하고 있다. 마장면 동쪽에 위치한 설봉산과 마주하고 있으며 해발 49m로 그리 높지는 않으나 봉우리를 이루는 기암괴석이 절묘한 경관을 이루고 있어 외부로부터 많은 등산객들이 찾아드는 이천의 명산으로 이름 나 있고 특히 산 중턱에 있는 영보사 뒷편 절벽 아래서 샘솟는 차고 시원한 석관수의 맛이 일품이며, 능선을 따라 바위를 오르는 등산객의 아기자기함이 산행의 묘미를 만끽하게 해준다. 옛날 삼각산 신령님이 삼각산을 처음 만들때 마고할미에게 지리산, 도드람봉을 옮겨오도록 명하였는데 마고할미가 도드람봉을 끌고 오던 중 삼각산이 이미 완성되었다는 소식을 듣고 그 자리에 버린 것이 지금의 도드람산이 되었다는 재미있는 전설이 전해내려오기도 한다. 옛 문헌에 저명산에는 정악사라는 절이 있다고 했는데, 지금은 없어지고 산 남쪽기슭에 사지의 흔적이 남아 있으며 이곳을 정악골이라는 명칭으로 부르고 있다.
            </p>
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
  /* background: rgba(0, 0, 0, 0.5); */
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
  height : 50%;
  position : relative;
  background: white;
  /* padding: 1.5rem 3rem; */
  border-radius: 25px;
  box-shadow : 0 0px 10px rgba(128, 128, 128, 0.25), 0 10px 10px rgba(128, 128, 128, 0.22);

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
    padding : 1rem 3rem 3rem 3rem;

    .modal-info {
      width : 50%;
      height : 100%;
    }
    .modal-desription{
      width : 50%;
      height : 100%;
    }
  }
  
}

.modal-info {
  display : flex;
  flex-direction: column;
  margin-right: 2.5rem;
  justify-content: center;
  align-items: center;
  .modal-image-container {

    .modal-image-src {
      width : 300px;
      height : 300px;
      border-radius : 20px;
      box-shadow: 0 0 28px rgba(128, 128, 128, 0.25), 0 10px 10px rgba(128, 128, 128, 0.22);
    }
  }

  .modal-information {
    width : 100%;
  }

}

/* 데이터 찐 정보 */
.modal-description {
  width : 100%;
  height : 100%;
  flex : display;
  flex-direction: column;
  .data-header {
    width : 100%;
    height: 15%;
    font-weight: bold;
    font-size : 32px;
    letter-spacing: 1px;
    color : var(--planit-dark);
  }
  .data-description{
    max-height : 400px;
    padding-right : 1rem;
    overflow-y: scroll;
    font-size : 18px;
    font-weight: 600;
    letter-spacing: 0.05;
    color : var(--color-gray800);
    line-height: 1.8;
    

  }
}



</style>
