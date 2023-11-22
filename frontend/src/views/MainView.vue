<script setup>
import { ref, computed, onMounted } from "vue";
import { useRouter } from "vue-router";
import Typed from "typed.js";
import { useUserStore } from "@/stores/user";

const store = useUserStore();

const loginStatus = store.loginStatus;

// route 이동
const router = useRouter();
// 버튼 클릭 시 이동
const goLogin = () => {
  router.push({ name: "login" });
};
const goPlanit = () => {
  router.push({ name: "plans" });
};

// 이미지가 있는 디렉토리의 context
const imageContext = import.meta.globEager("/src/assets/img/main/*.jpeg");
const imagePaths = Object.keys(imageContext);
// 모든 이미지 파일 경로를 가져와 이미지 URL 배열로 변환
const images = ref(imagePaths.map((path) => imageContext[path].default));

const currentImageIdx = ref(0);

const currentImageStyle = computed(() => ({
  backgroundImage: `url(${images.value[currentImageIdx.value]})`,
  transition: "background-image 1s ease",
}));

const typedElement = ref(null);

onMounted(() => {
  setInterval(() => {
    currentImageIdx.value = (currentImageIdx.value + 1) % images.value.length;
  }, 10000); // 5초마다 이미지 전환

  if (typedElement.value) {
    new Typed(typedElement.value, {
      strings: [
        "여행을 계획하는 순간부터,<br> 새로운 세계를 발견하는 모든 순간까지",
        "당신의 여행을 위한 우주적 계획.",
        "지금 바로 PLAN IT !",
      ],
      typeSpeed: 200, // 타이핑 속도 조절
      startDelay: 500, // 시작 전 지연시간
      backSpeed: 50, //백스페이스 속도
      backDelay: 2000, // 백스페이스 후 지연시간
      loop: true, // 반복 여부
    });
  }
});
</script>

<template>
  <div class="main-container">
    <div class="background-image" :style="currentImageStyle"></div>
    <div class="logobar">
      <img
        class="planit-logo"
        src="@/assets/img/logo/planit.png"
        alt="로고 이미지"
      />
      <div class="planit-name">Plan <span>I</span>t</div>
    </div>
    <div class="main-content-container">
      <div class="introduce-service" ref="typedElement"></div>
      <div class="next-service">
        <div v-show="!loginStatus">
          <a-button ghost class="login-btn custom-btn" @click="goLogin"
            >Sign in</a-button
          >
        </div>

        <a-button type="primary" class="planit-btn custom-btn" @click="goPlanit"
          >Plan It !</a-button
        >
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
@import url("https://fonts.googleapis.com/css2?family=Gasoek+One&display=swap");

/* Ant-Btn 커스텀 */
.custom-btn {
  width: 200px;
  height: 80px;
  margin-right: 1.5rem;
  border-radius: 20px;
  font-size: 24px;
  font-weight: bold;
}
.login-btn {
  border: 3px solid;
}

/* 이미지 트랜잭션 */
.background-image {
  position: absolute;
  width: 100%;
  height: 100%;
  background-size: cover;
  background-position: center;
}
.background-image::after {
  content: ""; /* 필수 속성 */
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
}

/* 로고 추가 */
.logobar {
  position: relative;
  margin-top: 2rem;
  margin-left: 5rem;
  display: flex;
  .planit-logo {
    width: 100px;
    height: 70px;
  }
  .planit-name {
    width: auto;
    height: 70px;
    display: flex;
    align-items: center;
    justify-content: center;
    margin-left: 1rem;
    padding-top: 0.5rem;
    font-family: "Gasoek One";
    font-size: 40px;
    letter-spacing: 3px;
    color: var(--planit-primary);

    span {
      color: var(--planit-point-2);
      font-weight: 900;
    }
    text-shadow: -1px -1px 0 var(--planit-dark), 1px -1px 0 var(--planit-dark),
      -1px 1px 0 var(--planit-dark), 3px 3px 0 var(--planit-dark);
  }
}

.main-container {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  background-color: white;

  .logobar {
    top: 0;
    left: 0;
    width: 100%;
    height: 10%;
  }
  .main-content-container {
    position: relative;
    justify-content: center;
    width: 100%;
    height: 90%;

    padding: 0 15%;

    .introduce-service {
      width: 100%;
      height: 45%;
      display: flex;
      flex-direction: column;
      justify-content: flex-end;
      padding-bottom: 2rem;
      font-family: "JalnanGothic";
      font-size: 80px;
      letter-spacing: 1px;
      line-height: 1.5; /* 행간 */
      color: white;
    }
    .next-service {
      width: 100%;
      height: 55%;
      padding-top: 1rem;
      display: flex;
    }
  }
}
</style>
