<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";

// const { board } = history.state; // 이렇게 받는다.
const { boardId } = history.state;
// const boardId = ref(0);

const board = ref({});

const planDetails = ref([]);

onMounted(async () => {
  try {
    const boardResponse = await axios.get("http://localhost:/boards/" + boardId);

    // 첫번째 비동기 작업
    board.value = {
      title: boardResponse.data.title,
      createAt: boardResponse.data.createAt,
      hits: boardResponse.data.hits,
      contents: boardResponse.data.contents,
      planKey: boardResponse.data.planKey,
    };
    // console.log("board : ", board.value);
    // 두번째 비동기 작업
    const planResponse = await axios.get("http://localhost:/plans/" + board.value.planKey);
    // planResponse.data.forEach((data) => {
    //   planDetails.value.push({
    //     detail_id: data.detail_id,
    //     attractionId: data.attractionId,
    //     planDate: data.planDate,
    //     sequence: data.sequence,
    //     title: data.title,
    //   });
    // });

    planDetails.value = planResponse.data;

    // planDetails 정렬
    planDetails.value.sort((a, b) => {
      // planDate 기준으로 오름차순 정렬
      if (a.planDate < b.planDate) return -1;
      if (a.planDate > b.planDate) return 1;

      // planDate가 같으면 sequence 기준으로 오름차순 정렬
      if (a.sequence < b.sequence) return -1;
      if (a.sequence > b.sequence) return 1;

      return 0; // planDate와 sequence가 같을 경우
    });
    console.log("planDetails :", planDetails.value);
    // console.log("planRes : ", planResponse);
  } catch (error) {
    console.log("Error : ", error);
  }
});
// console.log(history.state);
</script>

<template>
  <div class="container__Container-sc-5ea7eb67-0 sc-4d05c194-0 iLKpSA ixIhnv">
    <div class="container__Container-sc-5ea7eb67-0 sc-beeac02a-1 iLKpSA TXJjD">
      <div size="18" color="gray" class="text__Text-sc-6cffe184-0 gYBzzu">둘째날.</div>
    </div>
    <div class="container__Container-sc-5ea7eb67-0 sc-beeac02a-2 iLKpSA hkqvVg">
      <div class="text__Text-sc-6cffe184-0 sc-beeac02a-0 haSmSi kxcCfC">
        <p>
          <strong>{{ board.title }}</strong
          ><br />
          {{ board.contents }}
          반나절은 다낭 시내에서, 이후에는 오행산과 호이안에서 보낸다. 전통과 빼어난 자연경관 그리고
          은은하게 빛나는 야경까지. 구석구석 자리잡은 다낭의 멋으로 지루할 틈이 없다. 반나절은 다낭
          시내에서, 이후에는 오행산과 호이안에서 보낸다. 전통과 빼어난 자연경관 그리고 은은하게
          빛나는 야경까지. 구석구석 자리잡은 다낭의 멋으로 지루할 틈이 없다. 반나절은 다낭 시내에서,
          이후에는 오행산과 호이안에서 보낸다. 전통과 빼어난 자연경관 그리고 은은하게 빛나는
          야경까지. 구석구석 자리잡은 다낭의 멋으로 지루할 틈이 없다. 반나절은 다낭 시내에서,
          이후에는 오행산과 호이안에서 보낸다. 전통과 빼어난 자연경관 그리고 은은하게 빛나는
          야경까지. 구석구석 자리잡은 다낭의 멋으로 지루할 틈이 없다. 반나절은 다낭 시내에서,
          이후에는 오행산과 호이안에서 보낸다. 전통과 빼어난 자연경관 그리고 은은하게 빛나는
          야경까지. 구석구석 자리잡은 다낭의 멋으로 지루할 틈이 없다.
          <br />
          · 예상 소요시간 : 7시간
        </p>
      </div>
    </div>
    <div
      class="container__Container-sc-5ea7eb67-0 itinerary___StyledContainer2-sc-681c5310-9 iLKpSA gHsyoQ"
    >
      <div
        class="container__Container-sc-5ea7eb67-0 itinerary-map___StyledContainer-sc-bb75f9ca-0 iLKpSA hSALFW chromatic-ignore"
      ></div>
      <div
        class="container__Container-sc-5ea7eb67-0 itinerary___StyledContainer-sc-681c5310-8 iLKpSA geplNv"
      >
        <div
          class="container__Container-sc-5ea7eb67-0 itinerary__Stack-sc-681c5310-4 iLKpSA eAsDxC"
        >
          <div
            class="container__Container-sc-5ea7eb67-0 flex-box__FlexBox-sc-df192771-1 iLKpSA gPRjNB"
            v-for="plan in planDetails"
            :key="plan.planDate"
          >
            <div
              class="container__Container-sc-5ea7eb67-0 flex-box__FlexBox-sc-df192771-1 itinerary__Timeline-sc-681c5310-0 iLKpSA gPRjNB kpPodn"
            >
              <div
                class="container__Container-sc-5ea7eb67-0 flex-box__FlexBox-sc-df192771-1 iLKpSA hUhUTq"
              >
                <div
                  class="container__Container-sc-5ea7eb67-0 flex-box__FlexBox-sc-df192771-1 itinerary___StyledFlexBox-sc-681c5310-10 iLKpSA bFFkHa gdAsaT"
                >
                  <span
                    color="rgba(151, 95, 254, 1)"
                    class="badge__BadgeBase-sc-f2fd9e41-0 badge__CircleBadge-sc-f2fd9e41-2 gJtAJZ fzwqRU"
                    >{{ plan.sequence }}</span
                  >
                </div>
                <div
                  class="container__Container-sc-5ea7eb67-0 itinerary__Duration-sc-681c5310-6 iLKpSA hXEfgc"
                ></div>
              </div>
            </div>
            <a
              class="container__Container-sc-5ea7eb67-0 flex-box__FlexBoxItem-sc-df192771-0 itinerary__CardWrapper-sc-681c5310-3 iLKpSA cIkAYa AiTlk"
              ><div
                class="segment__CardFrame-sc-e2042e2a-1 iSLaBf itinerary__PoiCard-sc-681c5310-1 itinerary___StyledPoiCard-sc-681c5310-2 jjciAh iDvhtl"
                radius="6"
              >
                <div size="16" class="text__Text-sc-6cffe184-0 jdWASn">
                  {{ plan.planDate }}
                </div>
                <div size="13" color="gray500" class="text__Text-sc-6cffe184-0 dnGFrr">
                  {{ plan.title }}
                </div>
              </div></a
            >
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.ixIhnv {
  position: relative;
  margin: 2rem 37rem;
}

.iLKpSA {
  box-sizing: border-box;
  float: none;
}

*,
::before,
::after {
  box-sizing: inherit;
  border-width: 0px;
  border-style: none;
}

div {
  display: block;
}

body {
  margin: 0px;
  line-height: inherit;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}

.eZRrRb {
  box-sizing: border-box;
  overflow-wrap: break-word;
  color: rgb(54, 143, 255);
  float: none;
  font-weight: bold;
  white-space: pre-line;
  margin: 0px 0px 6px;
  font-size: 14px;
  line-height: 16.8px;
  letter-spacing: 0px;
}

.gYBzzu {
  box-sizing: border-box;
  overflow-wrap: break-word;
  color: rgb(58, 58, 58);
  float: none;
  font-weight: bold;
  white-space: pre-line;
  font-size: 18px;
  line-height: 21.6px;
  letter-spacing: 0px;
}

.hkqvVg {
  margin: 0px 20px 24px;
}

.iLKpSA {
  box-sizing: border-box;
  float: none;
}

body {
  margin: 0px;
  line-height: inherit;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}

.gHsyoQ {
  margin: 10px 0px;
}

.iQHLcW {
  position: absolute;
  z-index: 2;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 0%, var(--color-white) 100%);
  width: 100%;
  height: 150px;
  bottom: 49px;
}

.iLKpSA {
  box-sizing: border-box;
  float: none;
}

*,
::before,
::after {
  box-sizing: inherit;
  border-width: 0px;
  border-style: none;
}

.iDvhtl {
  margin-top: 5px;
  margin-bottom: 8px;
}

.jjciAh {
  padding: 16px 15px;
  flex: 1 1 0%;
}

.iSLaBf {
  border-radius: 6px;
  box-shadow: rgba(0, 0, 0, 0.07) 0px 0px 20px 0px;
}

*,
::before,
::after {
  box-sizing: inherit;
  border-width: 0px;
  border-style: none;
}

div {
  display: block;
}

a {
  color: inherit;
  text-decoration: inherit;
}

.fzwqRU {
  font-size: 11px;
  color: white;
  width: 24px;
  height: 24px;
  padding-top: 4px;
  border-radius: 100%;
  background-color: rgb(151, 95, 254);
  border: 2px solid var(--color-white);
}

.gJtAJZ {
  display: inline-block;
  font-weight: bold;
  text-align: center;
}
</style>
