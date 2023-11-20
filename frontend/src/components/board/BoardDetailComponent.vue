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
    const boardResponse = await axios.get(
      "http://localhost:/boards/" + boardId
    );

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
    const planResponse = await axios.get(
      "http://localhost:/plans/" + board.value.planKey
    );
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
      <div size="18" color="gray" class="text__Text-sc-6cffe184-0 gYBzzu">
        둘째날.
      </div>
    </div>
    <div class="container__Container-sc-5ea7eb67-0 sc-beeac02a-2 iLKpSA hkqvVg">
      <div class="text__Text-sc-6cffe184-0 sc-beeac02a-0 haSmSi kxcCfC">
        <p>
          <strong>{{ board.title }}</strong
          ><br />
          {{ board.contents }}
          반나절은 다낭 시내에서, 이후에는 오행산과 호이안에서 보낸다. 전통과
          빼어난 자연경관 그리고 은은하게 빛나는 야경까지. 구석구석 자리잡은
          다낭의 멋으로 지루할 틈이 없다.<br />
          · 예상 소요시간 : 7시간
        </p>
      </div>
    </div>
    <div
      class="container__Container-sc-5ea7eb67-0 itinerary___StyledContainer2-sc-681c5310-9 iLKpSA gHsyoQ"
    >
      <div
        class="container__Container-sc-5ea7eb67-0 itinerary-map___StyledContainer-sc-bb75f9ca-0 iLKpSA hSALFW chromatic-ignore"
      >
        <div
          style="
            width: 100%;
            height: 100%;
            position: relative;
            overflow: hidden;
          "
        >
          <div
            style="
              height: 100%;
              width: 100%;
              position: absolute;
              top: 0px;
              left: 0px;
              background-color: rgb(229, 227, 223);
            "
          >
            <div
              class="gm-style"
              style="
                position: absolute;
                z-index: 0;
                left: 0px;
                top: 0px;
                height: 100%;
                width: 100%;
                padding: 0px;
                border-width: 0px;
                margin: 0px;
              "
            >
              <div>
                <button
                  draggable="false"
                  aria-label="단축키"
                  title="단축키"
                  type="button"
                  style="
                    background: none transparent;
                    display: block;
                    border: none;
                    margin: 0px;
                    padding: 0px;
                    text-transform: none;
                    appearance: none;
                    position: absolute;
                    cursor: pointer;
                    user-select: none;
                    z-index: 1000002;
                    outline-offset: 3px;
                    right: 0px;
                    bottom: 0px;
                    transform: translateX(100%);
                  "
                ></button>
              </div>
              <div
                tabindex="0"
                aria-label="지도"
                aria-roledescription="지도"
                role="region"
                aria-describedby="3205296A-1F0C-49BB-B7AC-C83665F0F458"
                style="
                  position: absolute;
                  z-index: 0;
                  left: 0px;
                  top: 0px;
                  height: 100%;
                  width: 100%;
                  padding: 0px;
                  border-width: 0px;
                  margin: 0px;
                  cursor: url('https://maps.gstatic.com/mapfiles/openhand_8_8.cur'),
                    default;
                  touch-action: none;
                "
              >
                <div
                  style="
                    z-index: 1;
                    position: absolute;
                    left: 50%;
                    top: 50%;
                    width: 100%;
                    will-change: transform;
                    transform: translate(0px, 0px);
                  "
                >
                  <div
                    style="
                      position: absolute;
                      left: 0px;
                      top: 0px;
                      z-index: 100;
                      width: 100%;
                    "
                  >
                    <div
                      style="
                        position: absolute;
                        left: 0px;
                        top: 0px;
                        z-index: 0;
                      "
                    >
                      <div
                        style="
                          position: absolute;
                          z-index: 990;
                          transform: matrix(1, 0, 0, 1, -203, -164);
                        "
                      >
                        <div
                          style="
                            position: absolute;
                            left: 256px;
                            top: 0px;
                            width: 256px;
                            height: 256px;
                          "
                        >
                          <div style="width: 256px; height: 256px"></div>
                        </div>
                        <div
                          style="
                            position: absolute;
                            left: 512px;
                            top: 0px;
                            width: 256px;
                            height: 256px;
                          "
                        >
                          <div style="width: 256px; height: 256px"></div>
                        </div>
                        <div
                          style="
                            position: absolute;
                            left: 0px;
                            top: 0px;
                            width: 256px;
                            height: 256px;
                          "
                        >
                          <div style="width: 256px; height: 256px"></div>
                        </div>
                        <div
                          style="
                            position: absolute;
                            left: -256px;
                            top: 0px;
                            width: 256px;
                            height: 256px;
                          "
                        >
                          <div style="width: 256px; height: 256px"></div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div
                    style="
                      position: absolute;
                      left: 0px;
                      top: 0px;
                      z-index: 101;
                      width: 100%;
                    "
                  >
                    <div
                      style="
                        position: absolute;
                        left: 0px;
                        top: 0px;
                        z-index: 30;
                      "
                    >
                      <div
                        style="
                          position: absolute;
                          z-index: 990;
                          transform: matrix(1, 0, 0, 1, -203, -164);
                        "
                      >
                        <div
                          style="
                            width: 256px;
                            height: 256px;
                            position: absolute;
                            left: -512px;
                            top: 256px;
                          "
                        ></div>
                        <div
                          style="
                            width: 256px;
                            height: 256px;
                            position: absolute;
                            left: -512px;
                            top: 0px;
                          "
                        ></div>
                        <div
                          style="
                            width: 256px;
                            height: 256px;
                            position: absolute;
                            left: 0px;
                            top: 256px;
                          "
                        >
                          <canvas
                            width="256"
                            height="256"
                            draggable="false"
                            style="
                              width: 256px;
                              height: 256px;
                              user-select: none;
                              position: absolute;
                              left: 0px;
                              top: 0px;
                            "
                          ></canvas>
                        </div>
                        <div
                          style="
                            width: 256px;
                            height: 256px;
                            position: absolute;
                            left: -256px;
                            top: 256px;
                          "
                        ></div>
                        <div
                          style="
                            width: 256px;
                            height: 256px;
                            position: absolute;
                            left: -256px;
                            top: 0px;
                          "
                        ></div>
                        <div
                          style="
                            width: 256px;
                            height: 256px;
                            position: absolute;
                            left: 0px;
                            top: 0px;
                          "
                        >
                          <canvas
                            width="256"
                            height="256"
                            draggable="false"
                            style="
                              width: 256px;
                              height: 256px;
                              user-select: none;
                              position: absolute;
                              left: 0px;
                              top: 0px;
                            "
                          ></canvas>
                        </div>
                        <div
                          style="
                            width: 256px;
                            height: 256px;
                            position: absolute;
                            left: 256px;
                            top: 0px;
                          "
                        >
                          <canvas
                            width="256"
                            height="256"
                            draggable="false"
                            style="
                              width: 256px;
                              height: 256px;
                              user-select: none;
                              position: absolute;
                              left: 0px;
                              top: 0px;
                            "
                          ></canvas>
                        </div>
                        <div
                          style="
                            width: 256px;
                            height: 256px;
                            position: absolute;
                            left: 512px;
                            top: 0px;
                          "
                        ></div>
                        <div
                          style="
                            width: 256px;
                            height: 256px;
                            position: absolute;
                            left: 256px;
                            top: 256px;
                          "
                        >
                          <canvas
                            width="256"
                            height="256"
                            draggable="false"
                            style="
                              width: 256px;
                              height: 256px;
                              user-select: none;
                              position: absolute;
                              left: 0px;
                              top: 0px;
                            "
                          ></canvas>
                        </div>
                        <div
                          style="
                            width: 256px;
                            height: 256px;
                            position: absolute;
                            left: 512px;
                            top: 256px;
                          "
                        ></div>
                      </div>
                    </div>
                  </div>
                  <div
                    style="
                      position: absolute;
                      left: 0px;
                      top: 0px;
                      z-index: 102;
                      width: 100%;
                    "
                  ></div>
                  <div
                    style="
                      position: absolute;
                      left: 0px;
                      top: 0px;
                      z-index: 103;
                      width: 100%;
                    "
                  ></div>
                  <div
                    style="position: absolute; left: 0px; top: 0px; z-index: 0"
                  >
                    <div
                      style="
                        position: absolute;
                        z-index: 990;
                        transform: matrix(1, 0, 0, 1, -203, -164);
                      "
                    >
                      <div
                        style="
                          position: absolute;
                          left: 512px;
                          top: 0px;
                          width: 256px;
                          height: 256px;
                          transition: opacity 200ms linear 0s;
                        "
                      >
                        <img
                          draggable="false"
                          alt=""
                          role="presentation"
                          src="https://maps.googleapis.com/maps/vt?pb=!1m5!1m4!1i10!2i821!3i465!4i256!2m3!1e0!2sm!3i671411975!3m12!2sko-KR!3sKR!5e18!12m4!1e68!2m2!1sset!2sRoadmap!12m3!1e37!2m1!1ssmartmaps!4e0&amp;key=AIzaSyAOOR8HFPf-v4ryk4GVsHDDrdWaAVI_ABg&amp;token=927"
                          style="
                            width: 256px;
                            height: 256px;
                            user-select: none;
                            border: 0px;
                            padding: 0px;
                            margin: 0px;
                            max-width: none;
                          "
                        />
                      </div>
                      <div
                        style="
                          position: absolute;
                          left: 256px;
                          top: 0px;
                          width: 256px;
                          height: 256px;
                          transition: opacity 200ms linear 0s;
                        "
                      >
                        <img
                          draggable="false"
                          alt=""
                          role="presentation"
                          src="https://maps.googleapis.com/maps/vt?pb=!1m5!1m4!1i10!2i820!3i465!4i256!2m3!1e0!2sm!3i671412779!3m12!2sko-KR!3sKR!5e18!12m4!1e68!2m2!1sset!2sRoadmap!12m3!1e37!2m1!1ssmartmaps!4e0&amp;key=AIzaSyAOOR8HFPf-v4ryk4GVsHDDrdWaAVI_ABg&amp;token=6644"
                          style="
                            width: 256px;
                            height: 256px;
                            user-select: none;
                            border: 0px;
                            padding: 0px;
                            margin: 0px;
                            max-width: none;
                          "
                        />
                      </div>
                      <div
                        style="
                          position: absolute;
                          left: 0px;
                          top: 0px;
                          width: 256px;
                          height: 256px;
                          transition: opacity 200ms linear 0s;
                        "
                      >
                        <img
                          draggable="false"
                          alt=""
                          role="presentation"
                          src="https://maps.googleapis.com/maps/vt?pb=!1m5!1m4!1i10!2i819!3i465!4i256!2m3!1e0!2sm!3i671412803!3m12!2sko-KR!3sKR!5e18!12m4!1e68!2m2!1sset!2sRoadmap!12m3!1e37!2m1!1ssmartmaps!4e0&amp;key=AIzaSyAOOR8HFPf-v4ryk4GVsHDDrdWaAVI_ABg&amp;token=36881"
                          style="
                            width: 256px;
                            height: 256px;
                            user-select: none;
                            border: 0px;
                            padding: 0px;
                            margin: 0px;
                            max-width: none;
                          "
                        />
                      </div>
                      <div
                        style="
                          position: absolute;
                          left: -256px;
                          top: 0px;
                          width: 256px;
                          height: 256px;
                          transition: opacity 200ms linear 0s;
                        "
                      >
                        <img
                          draggable="false"
                          alt=""
                          role="presentation"
                          src="https://maps.googleapis.com/maps/vt?pb=!1m5!1m4!1i10!2i818!3i465!4i256!2m3!1e0!2sm!3i671412803!3m12!2sko-KR!3sKR!5e18!12m4!1e68!2m2!1sset!2sRoadmap!12m3!1e37!2m1!1ssmartmaps!4e0&amp;key=AIzaSyAOOR8HFPf-v4ryk4GVsHDDrdWaAVI_ABg&amp;token=47262"
                          style="
                            width: 256px;
                            height: 256px;
                            user-select: none;
                            border: 0px;
                            padding: 0px;
                            margin: 0px;
                            max-width: none;
                          "
                        />
                      </div>
                    </div>
                  </div>
                </div>
                <div
                  style="
                    z-index: 3;
                    position: absolute;
                    height: 100%;
                    width: 100%;
                    padding: 0px;
                    border-width: 0px;
                    margin: 0px;
                    left: 0px;
                    top: 0px;
                    touch-action: pan-x pan-y;
                  "
                >
                  <div
                    style="
                      z-index: 4;
                      position: absolute;
                      left: 50%;
                      top: 50%;
                      width: 100%;
                      will-change: transform;
                      transform: translate(0px, 0px);
                    "
                  >
                    <div
                      style="
                        position: absolute;
                        left: 0px;
                        top: 0px;
                        z-index: 104;
                        width: 100%;
                      "
                    ></div>
                    <div
                      style="
                        position: absolute;
                        left: 0px;
                        top: 0px;
                        z-index: 105;
                        width: 100%;
                      "
                    ></div>
                    <div
                      style="
                        position: absolute;
                        left: 0px;
                        top: 0px;
                        z-index: 106;
                        width: 100%;
                      "
                    >
                      <span
                        id="8B7A7153-DAEB-41C8-9129-31555A5F3F4E"
                        style="display: none"
                        >이동하려면 화살표 키를 누르세요.</span
                      >
                      <div
                        style="
                          top: 10.9928px;
                          left: 11.5077px;
                          width: 0px;
                          height: 0px;
                          position: absolute;
                        "
                      >
                        <div
                          width="22"
                          height="22"
                          color="var(--color-purple)"
                          src="https://assets.triple.guide/images/img_map_attraction_timetable_pick@3x.png"
                          class="circle-marker-base__CirclePin-sc-fb26117c-1 gUwnnB"
                        >
                          <div
                            class="circle-marker-base__Circle-sc-fb26117c-0 bhvryq"
                          >
                            <strong>1</strong>
                          </div>
                        </div>
                      </div>
                      <div
                        style="
                          top: 12.1848px;
                          left: 10.589px;
                          width: 0px;
                          height: 0px;
                          position: absolute;
                        "
                      >
                        <div
                          width="22"
                          height="22"
                          color="var(--color-purple)"
                          src="https://assets.triple.guide/images/img_map_attraction_timetable_pick@3x.png"
                          class="circle-marker-base__CirclePin-sc-fb26117c-1 gdkInQ"
                        >
                          <div
                            class="circle-marker-base__Circle-sc-fb26117c-0 bhvryq"
                          >
                            <strong>2</strong>
                          </div>
                        </div>
                      </div>
                      <div
                        style="
                          top: 16.8923px;
                          left: 10.833px;
                          width: 0px;
                          height: 0px;
                          position: absolute;
                        "
                      >
                        <div
                          width="22"
                          height="22"
                          color="var(--color-purple)"
                          src="https://assets.triple.guide/images/img_map_attraction_timetable_pick@3x.png"
                          class="circle-marker-base__CirclePin-sc-fb26117c-1 ipRrwb"
                        >
                          <div
                            class="circle-marker-base__Circle-sc-fb26117c-0 bhvryq"
                          >
                            <strong>3</strong>
                          </div>
                        </div>
                      </div>
                      <div
                        style="
                          top: 60.4617px;
                          left: 40.5053px;
                          width: 0px;
                          height: 0px;
                          position: absolute;
                        "
                      >
                        <div
                          width="22"
                          height="22"
                          color="var(--color-purple)"
                          src="https://assets.triple.guide/images/img_map_attraction_timetable_pick@3x.png"
                          class="circle-marker-base__CirclePin-sc-fb26117c-1 fEzxFS"
                        >
                          <div
                            class="circle-marker-base__Circle-sc-fb26117c-0 bhvryq"
                          >
                            <strong>4</strong>
                          </div>
                        </div>
                      </div>
                      <div
                        style="
                          top: 156.671px;
                          left: 85.5481px;
                          width: 0px;
                          height: 0px;
                          position: absolute;
                        "
                      >
                        <div
                          width="22"
                          height="22"
                          color="var(--color-purple)"
                          src="https://assets.triple.guide/images/img_map_attraction_timetable_pick@3x.png"
                          class="circle-marker-base__CirclePin-sc-fb26117c-1 bAosPx"
                        >
                          <div
                            class="circle-marker-base__Circle-sc-fb26117c-0 bhvryq"
                          >
                            <strong>5</strong>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div
                      style="
                        position: absolute;
                        left: 0px;
                        top: 0px;
                        z-index: 107;
                        width: 100%;
                      "
                    >
                      <button
                        type="button"
                        title="마지막 수정 실행취소"
                        aria-label="마지막 수정 실행취소"
                        style="
                          background: transparent;
                          border: none;
                          margin: 0px;
                          padding: 0px;
                          z-index: -202;
                          cursor: pointer;
                          display: none;
                          touch-action: none;
                        "
                      >
                        <span style="display: inline-block"
                          ><div
                            style="
                              width: 30px;
                              height: 27px;
                              overflow: hidden;
                              position: relative;
                            "
                          >
                            <img
                              alt=""
                              src="https://maps.gstatic.com/mapfiles/undo_poly.png"
                              draggable="false"
                              style="
                                position: absolute;
                                left: 0px;
                                top: 0px;
                                user-select: none;
                                border: 0px;
                                padding: 0px;
                                margin: 0px;
                                max-width: none;
                                width: 90px;
                                height: 27px;
                              "
                            /></div
                        ></span>
                      </button>
                    </div>
                  </div>
                </div>
                <div
                  class="gm-style-moc"
                  style="
                    z-index: 4;
                    position: absolute;
                    height: 100%;
                    width: 100%;
                    padding: 0px;
                    border-width: 0px;
                    margin: 0px;
                    left: 0px;
                    top: 0px;
                    opacity: 0;
                    transition-duration: 0.2s;
                  "
                >
                  <p class="gm-style-mot"></p>
                </div>
                <div
                  id="3205296A-1F0C-49BB-B7AC-C83665F0F458"
                  style="display: none"
                >
                  <div class="LGLeeN-keyboard-shortcuts-view">
                    <table>
                      <tbody>
                        <tr>
                          <td><kbd aria-label="왼쪽 화살표">←</kbd></td>
                          <td aria-label="왼쪽으로 이동.">왼쪽으로 이동</td>
                        </tr>
                        <tr>
                          <td><kbd aria-label="오른쪽 화살표">→</kbd></td>
                          <td aria-label="오른쪽으로 이동.">오른쪽으로 이동</td>
                        </tr>
                        <tr>
                          <td><kbd aria-label="위쪽 화살표">↑</kbd></td>
                          <td aria-label="위로 이동.">위로 이동</td>
                        </tr>
                        <tr>
                          <td><kbd aria-label="아래쪽 화살표">↓</kbd></td>
                          <td aria-label="아래로 이동.">아래로 이동</td>
                        </tr>
                        <tr>
                          <td><kbd>+</kbd></td>
                          <td aria-label="확대.">확대</td>
                        </tr>
                        <tr>
                          <td><kbd>-</kbd></td>
                          <td aria-label="축소.">축소</td>
                        </tr>
                        <tr>
                          <td><kbd>Home</kbd></td>
                          <td aria-label="왼쪽으로 75% 이동.">
                            왼쪽으로 75% 이동
                          </td>
                        </tr>
                        <tr>
                          <td><kbd>End</kbd></td>
                          <td aria-label="오른쪽으로 75% 이동.">
                            오른쪽으로 75% 이동
                          </td>
                        </tr>
                        <tr>
                          <td><kbd>Page Up</kbd></td>
                          <td aria-label="위로 75% 이동.">위로 75% 이동</td>
                        </tr>
                        <tr>
                          <td><kbd>Page Down</kbd></td>
                          <td aria-label="아래로 75% 이동.">아래로 75% 이동</td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
              <iframe
                aria-hidden="true"
                frameborder="0"
                tabindex="-1"
                style="
                  z-index: -1;
                  position: absolute;
                  width: 100%;
                  height: 100%;
                  top: 0px;
                  left: 0px;
                  border: none;
                "
              ></iframe>
              <div
                style="
                  pointer-events: none;
                  width: 100%;
                  height: 100%;
                  box-sizing: border-box;
                  position: absolute;
                  z-index: 1000002;
                  opacity: 0;
                  border: 2px solid rgb(26, 115, 232);
                "
              ></div>
              <div></div>
              <div></div>
              <div></div>
              <div></div>
              <div>
                <button
                  draggable="false"
                  aria-label="전체 화면보기로 전환"
                  title="전체 화면보기로 전환"
                  type="button"
                  aria-pressed="false"
                  class="gm-control-active gm-fullscreen-control"
                  style="
                    background: none rgb(255, 255, 255);
                    border: 0px;
                    margin: 10px;
                    padding: 0px;
                    text-transform: none;
                    appearance: none;
                    position: absolute;
                    cursor: pointer;
                    user-select: none;
                    border-radius: 2px;
                    height: 40px;
                    width: 40px;
                    box-shadow: rgba(0, 0, 0, 0.3) 0px 1px 4px -1px;
                    overflow: hidden;
                    display: none;
                    top: 0px;
                    right: 0px;
                  "
                >
                  <img
                    src="data:image/svg+xml,%3Csvg%20xmlns%3D%22http%3A//www.w3.org/2000/svg%22%20viewBox%3D%220%200%2018%2018%22%3E%3Cpath%20fill%3D%22%23666%22%20d%3D%22M0%200v6h2V2h4V0H0zm16%200h-4v2h4v4h2V0h-2zm0%2016h-4v2h6v-6h-2v4zM2%2012H0v6h6v-2H2v-4z%22/%3E%3C/svg%3E"
                    alt=""
                    style="height: 18px; width: 18px"
                  /><img
                    src="data:image/svg+xml,%3Csvg%20xmlns%3D%22http%3A//www.w3.org/2000/svg%22%20viewBox%3D%220%200%2018%2018%22%3E%3Cpath%20fill%3D%22%23333%22%20d%3D%22M0%200v6h2V2h4V0H0zm16%200h-4v2h4v4h2V0h-2zm0%2016h-4v2h6v-6h-2v4zM2%2012H0v6h6v-2H2v-4z%22/%3E%3C/svg%3E"
                    alt=""
                    style="height: 18px; width: 18px"
                  /><img
                    src="data:image/svg+xml,%3Csvg%20xmlns%3D%22http%3A//www.w3.org/2000/svg%22%20viewBox%3D%220%200%2018%2018%22%3E%3Cpath%20fill%3D%22%23111%22%20d%3D%22M0%200v6h2V2h4V0H0zm16%200h-4v2h4v4h2V0h-2zm0%2016h-4v2h6v-6h-2v4zM2%2012H0v6h6v-2H2v-4z%22/%3E%3C/svg%3E"
                    alt=""
                    style="height: 18px; width: 18px"
                  />
                </button>
              </div>
              <div></div>
              <div></div>
              <div></div>
              <div></div>
              <div></div>
              <div>
                <div
                  style="
                    margin: 0px 5px;
                    z-index: 1000000;
                    position: absolute;
                    left: 0px;
                    bottom: 0px;
                  "
                >
                  <a
                    target="_blank"
                    rel="noopener"
                    title="Google 지도에서 이 지역 열기(새 창으로 열림)"
                    aria-label="Google 지도에서 이 지역 열기(새 창으로 열림)"
                    href="https://maps.google.com/maps?ll=16.083354,108.207873&amp;z=10&amp;t=m&amp;hl=ko-KR&amp;gl=KR&amp;mapclient=apiv3"
                    style="display: inline"
                    ><div style="width: 66px; height: 26px">
                      <img
                        alt="Google"
                        src="data:image/svg+xml,%3Csvg%20fill%3D%22none%22%20xmlns%3D%22http%3A//www.w3.org/2000/svg%22%20viewBox%3D%220%200%2069%2029%22%3E%3Cg%20opacity%3D%22.6%22%20fill%3D%22%23fff%22%20stroke%3D%22%23fff%22%20stroke-width%3D%221.5%22%3E%3Cpath%20d%3D%22M17.4706%207.33616L18.0118%206.79504%2017.4599%206.26493C16.0963%204.95519%2014.2582%203.94522%2011.7008%203.94522c-4.613699999999999%200-8.50262%203.7551699999999997-8.50262%208.395779999999998C3.19818%2016.9817%207.0871%2020.7368%2011.7008%2020.7368%2014.1712%2020.7368%2016.0773%2019.918%2017.574%2018.3689%2019.1435%2016.796%2019.5956%2014.6326%2019.5956%2012.957%2019.5956%2012.4338%2019.5516%2011.9316%2019.4661%2011.5041L19.3455%2010.9012H10.9508V14.4954H15.7809C15.6085%2015.092%2015.3488%2015.524%2015.0318%2015.8415%2014.403%2016.4629%2013.4495%2017.1509%2011.7008%2017.1509%209.04835%2017.1509%206.96482%2015.0197%206.96482%2012.341%206.96482%209.66239%209.04835%207.53119%2011.7008%207.53119%2013.137%207.53119%2014.176%208.09189%2014.9578%208.82348L15.4876%209.31922%2016.0006%208.80619%2017.4706%207.33616z%22/%3E%3Cpath%20d%3D%22M24.8656%2020.7286C27.9546%2020.7286%2030.4692%2018.3094%2030.4692%2015.0594%2030.4692%2011.7913%2027.953%209.39011%2024.8656%209.39011%2021.7783%209.39011%2019.2621%2011.7913%2019.2621%2015.0594c0%203.25%202.514499999999998%205.6692%205.6035%205.6692zM24.8656%2012.8282C25.8796%2012.8282%2026.8422%2013.6652%2026.8422%2015.0594%2026.8422%2016.4399%2025.8769%2017.2905%2024.8656%2017.2905%2023.8557%2017.2905%2022.8891%2016.4331%2022.8891%2015.0594%2022.8891%2013.672%2023.853%2012.8282%2024.8656%2012.8282z%22/%3E%3Cpath%20d%3D%22M35.7511%2017.2905v0H35.7469C34.737%2017.2905%2033.7703%2016.4331%2033.7703%2015.0594%2033.7703%2013.672%2034.7343%2012.8282%2035.7469%2012.8282%2036.7608%2012.8282%2037.7234%2013.6652%2037.7234%2015.0594%2037.7234%2016.4439%2036.7554%2017.2962%2035.7511%2017.2905zM35.7387%2020.7286C38.8277%2020.7286%2041.3422%2018.3094%2041.3422%2015.0594%2041.3422%2011.7913%2038.826%209.39011%2035.7387%209.39011%2032.6513%209.39011%2030.1351%2011.7913%2030.1351%2015.0594%2030.1351%2018.3102%2032.6587%2020.7286%2035.7387%2020.7286z%22/%3E%3Cpath%20d%3D%22M51.953%2010.4357V9.68573H48.3999V9.80826C47.8499%209.54648%2047.1977%209.38187%2046.4808%209.38187%2043.5971%209.38187%2041.0168%2011.8998%2041.0168%2015.0758%2041.0168%2017.2027%2042.1808%2019.0237%2043.8201%2019.9895L43.7543%2020.0168%2041.8737%2020.797%2041.1808%2021.0844%2041.4684%2021.7772C42.0912%2023.2776%2043.746%2025.1469%2046.5219%2025.1469%2047.9324%2025.1469%2049.3089%2024.7324%2050.3359%2023.7376%2051.3691%2022.7367%2051.953%2021.2411%2051.953%2019.2723v-8.8366zm-7.2194%209.9844L44.7334%2020.4196C45.2886%2020.6201%2045.878%2020.7286%2046.4808%2020.7286%2047.1616%2020.7286%2047.7866%2020.5819%2048.3218%2020.3395%2048.2342%2020.7286%2048.0801%2021.0105%2047.8966%2021.2077%2047.6154%2021.5099%2047.1764%2021.7088%2046.5219%2021.7088%2045.61%2021.7088%2045.0018%2021.0612%2044.7336%2020.4201zM46.6697%2012.8282C47.6419%2012.8282%2048.5477%2013.6765%2048.5477%2015.084%2048.5477%2016.4636%2047.6521%2017.2987%2046.6697%2017.2987%2045.6269%2017.2987%2044.6767%2016.4249%2044.6767%2015.084%2044.6767%2013.7086%2045.6362%2012.8282%2046.6697%2012.8282zM55.7387%205.22083v-.75H52.0788V20.4412H55.7387V5.220829999999999z%22/%3E%3Cpath%20d%3D%22M63.9128%2016.0614L63.2945%2015.6492%2062.8766%2016.2637C62.4204%2016.9346%2061.8664%2017.3069%2061.0741%2017.3069%2060.6435%2017.3069%2060.3146%2017.2088%2060.0544%2017.0447%2059.9844%2017.0006%2059.9161%2016.9496%2059.8498%2016.8911L65.5497%2014.5286%2066.2322%2014.2456%2065.9596%2013.5589%2065.7406%2013.0075C65.2878%2011.8%2063.8507%209.39832%2060.8278%209.39832%2057.8445%209.39832%2055.5034%2011.7619%2055.5034%2015.0676%2055.5034%2018.2151%2057.8256%2020.7369%2061.0659%2020.7369%2063.6702%2020.7369%2065.177%2019.1378%2065.7942%2018.2213L66.2152%2017.5963%2065.5882%2017.1783%2063.9128%2016.0614zM61.3461%2012.8511L59.4108%2013.6526C59.7903%2013.0783%2060.4215%2012.7954%2060.9017%2012.7954%2061.067%2012.7954%2061.2153%2012.8161%2061.3461%2012.8511z%22/%3E%3C/g%3E%3Cpath%20d%3D%22M11.7008%2019.9868C7.48776%2019.9868%203.94818%2016.554%203.94818%2012.341%203.94818%208.12803%207.48776%204.69522%2011.7008%204.69522%2014.0331%204.69522%2015.692%205.60681%2016.9403%206.80583L15.4703%208.27586C14.5751%207.43819%2013.3597%206.78119%2011.7008%206.78119%208.62108%206.78119%206.21482%209.26135%206.21482%2012.341%206.21482%2015.4207%208.62108%2017.9009%2011.7008%2017.9009%2013.6964%2017.9009%2014.8297%2017.0961%2015.5606%2016.3734%2016.1601%2015.7738%2016.5461%2014.9197%2016.6939%2013.7454h-4.9931V11.6512h7.0298C18.8045%2012.0207%2018.8456%2012.4724%2018.8456%2012.957%2018.8456%2014.5255%2018.4186%2016.4637%2017.0389%2017.8434%2015.692%2019.2395%2013.9838%2019.9868%2011.7008%2019.9868z%22%20fill%3D%22%234285F4%22/%3E%3Cpath%20d%3D%22M29.7192%2015.0594C29.7192%2017.8927%2027.5429%2019.9786%2024.8656%2019.9786%2022.1884%2019.9786%2020.0121%2017.8927%2020.0121%2015.0594%2020.0121%2012.2096%2022.1884%2010.1401%2024.8656%2010.1401%2027.5429%2010.1401%2029.7192%2012.2096%2029.7192%2015.0594zM27.5922%2015.0594C27.5922%2013.2855%2026.3274%2012.0782%2024.8656%2012.0782S22.1391%2013.2937%2022.1391%2015.0594C22.1391%2016.8086%2023.4038%2018.0405%2024.8656%2018.0405S27.5922%2016.8168%2027.5922%2015.0594z%22%20fill%3D%22%23E94235%22/%3E%3Cpath%20d%3D%22M40.5922%2015.0594C40.5922%2017.8927%2038.4159%2019.9786%2035.7387%2019.9786%2033.0696%2019.9786%2030.8851%2017.8927%2030.8851%2015.0594%2030.8851%2012.2096%2033.0614%2010.1401%2035.7387%2010.1401%2038.4159%2010.1401%2040.5922%2012.2096%2040.5922%2015.0594zM38.4734%2015.0594C38.4734%2013.2855%2037.2087%2012.0782%2035.7469%2012.0782%2034.2851%2012.0782%2033.0203%2013.2937%2033.0203%2015.0594%2033.0203%2016.8086%2034.2851%2018.0405%2035.7469%2018.0405%2037.2087%2018.0487%2038.4734%2016.8168%2038.4734%2015.0594z%22%20fill%3D%22%23FABB05%22/%3E%3Cpath%20d%3D%22M51.203%2010.4357v8.8366C51.203%2022.9105%2049.0595%2024.3969%2046.5219%2024.3969%2044.132%2024.3969%2042.7031%2022.7955%2042.161%2021.4897L44.0417%2020.7095C44.3784%2021.5143%2045.1997%2022.4588%2046.5219%2022.4588%2048.1479%2022.4588%2049.1499%2021.4487%2049.1499%2019.568V18.8617H49.0759C48.5914%2019.4612%2047.6552%2019.9786%2046.4808%2019.9786%2044.0171%2019.9786%2041.7668%2017.8352%2041.7668%2015.0758%2041.7668%2012.3%2044.0253%2010.1319%2046.4808%2010.1319%2047.6552%2010.1319%2048.5914%2010.6575%2049.0759%2011.2323H49.1499V10.4357H51.203zM49.2977%2015.084C49.2977%2013.3512%2048.1397%2012.0782%2046.6697%2012.0782%2045.175%2012.0782%2043.9267%2013.3429%2043.9267%2015.084%2043.9267%2016.8004%2045.175%2018.0487%2046.6697%2018.0487%2048.1397%2018.0487%2049.2977%2016.8004%2049.2977%2015.084z%22%20fill%3D%22%234285F4%22/%3E%3Cpath%20d%3D%22M54.9887%205.22083V19.6912H52.8288V5.220829999999999H54.9887z%22%20fill%3D%22%2334A853%22/%3E%3Cpath%20d%3D%22M63.4968%2016.6854L65.1722%2017.8023C64.6301%2018.6072%2063.3244%2019.9869%2061.0659%2019.9869%2058.2655%2019.9869%2056.2534%2017.827%2056.2534%2015.0676%2056.2534%2012.1439%2058.2901%2010.1483%2060.8278%2010.1483%2063.3818%2010.1483%2064.6301%2012.1768%2065.0408%2013.2773L65.2625%2013.8357%2058.6843%2016.5623C59.1853%2017.5478%2059.9737%2018.0569%2061.0741%2018.0569%2062.1746%2018.0569%2062.9384%2017.5067%2063.4968%2016.6854zM58.3312%2014.9115L62.7331%2013.0884C62.4867%2012.4724%2061.764%2012.0454%2060.9017%2012.0454%2059.8012%2012.0454%2058.2737%2013.0145%2058.3312%2014.9115z%22%20fill%3D%22%23E94235%22/%3E%3C/svg%3E"
                        draggable="false"
                        style="
                          position: absolute;
                          left: 0px;
                          top: 0px;
                          width: 66px;
                          height: 26px;
                          user-select: none;
                          border: 0px;
                          padding: 0px;
                          margin: 0px;
                        "
                      /></div
                  ></a>
                </div>
              </div>
              <div></div>
              <div>
                <div
                  style="
                    display: inline-flex;
                    position: absolute;
                    right: 0px;
                    bottom: 0px;
                  "
                >
                  <div class="gmnoprint" style="z-index: 1000001">
                    <div
                      draggable="false"
                      class="gm-style-cc"
                      style="
                        user-select: none;
                        position: relative;
                        height: 14px;
                        line-height: 14px;
                      "
                    >
                      <div
                        style="
                          opacity: 0.7;
                          width: 100%;
                          height: 100%;
                          position: absolute;
                        "
                      >
                        <div style="width: 1px"></div>
                        <div
                          style="
                            background-color: rgb(245, 245, 245);
                            width: auto;
                            height: 100%;
                            margin-left: 1px;
                          "
                        ></div>
                      </div>
                      <div
                        style="
                          position: relative;
                          padding-right: 6px;
                          padding-left: 6px;
                          box-sizing: border-box;
                          font-family: Roboto, Arial, sans-serif;
                          font-size: 10px;
                          color: rgb(0, 0, 0);
                          white-space: nowrap;
                          direction: ltr;
                          text-align: right;
                          vertical-align: middle;
                          display: inline-block;
                        "
                      >
                        <button
                          draggable="false"
                          aria-label="단축키"
                          title="단축키"
                          type="button"
                          style="
                            background: none;
                            display: inline-block;
                            border: 0px;
                            margin: 0px;
                            padding: 0px;
                            text-transform: none;
                            appearance: none;
                            position: relative;
                            cursor: pointer;
                            user-select: none;
                            color: rgb(0, 0, 0);
                            font-family: inherit;
                            line-height: inherit;
                          "
                        >
                          단축키
                        </button>
                      </div>
                    </div>
                  </div>
                  <div class="gmnoprint" style="z-index: 1000001">
                    <div
                      draggable="false"
                      class="gm-style-cc"
                      style="
                        user-select: none;
                        position: relative;
                        height: 14px;
                        line-height: 14px;
                      "
                    >
                      <div
                        style="
                          opacity: 0.7;
                          width: 100%;
                          height: 100%;
                          position: absolute;
                        "
                      >
                        <div style="width: 1px"></div>
                        <div
                          style="
                            background-color: rgb(245, 245, 245);
                            width: auto;
                            height: 100%;
                            margin-left: 1px;
                          "
                        ></div>
                      </div>
                      <div
                        style="
                          position: relative;
                          padding-right: 6px;
                          padding-left: 6px;
                          box-sizing: border-box;
                          font-family: Roboto, Arial, sans-serif;
                          font-size: 10px;
                          color: rgb(0, 0, 0);
                          white-space: nowrap;
                          direction: ltr;
                          text-align: right;
                          vertical-align: middle;
                          display: inline-block;
                        "
                      >
                        <button
                          draggable="false"
                          aria-label="지도 데이터"
                          title="지도 데이터"
                          type="button"
                          style="
                            background: none;
                            border: 0px;
                            margin: 0px;
                            padding: 0px;
                            text-transform: none;
                            appearance: none;
                            position: relative;
                            cursor: pointer;
                            user-select: none;
                            color: rgb(0, 0, 0);
                            font-family: inherit;
                            line-height: inherit;
                            display: none;
                          "
                        >
                          지도 데이터</button
                        ><span style="">지도 데이터 ©2023 Google</span>
                      </div>
                    </div>
                  </div>
                  <div class="gmnoscreen">
                    <div
                      style="
                        font-family: Roboto, Arial, sans-serif;
                        font-size: 11px;
                        color: rgb(0, 0, 0);
                        direction: ltr;
                        text-align: right;
                        background-color: rgb(245, 245, 245);
                      "
                    >
                      지도 데이터 ©2023 Google
                    </div>
                  </div>
                  <button
                    draggable="false"
                    aria-label="지도 축척: 68픽셀당 10km"
                    title="지도 축척: 68픽셀당 10km"
                    type="button"
                    class="gm-style-cc"
                    aria-describedby="8B66F63E-B8AA-49EC-9975-2BD23E826F77"
                    style="
                      background: none;
                      display: none;
                      border: 0px;
                      margin: 0px;
                      padding: 0px;
                      text-transform: none;
                      appearance: none;
                      position: relative;
                      cursor: pointer;
                      user-select: none;
                      height: 14px;
                      line-height: 14px;
                    "
                  >
                    <div
                      style="
                        opacity: 0.7;
                        width: 100%;
                        height: 100%;
                        position: absolute;
                      "
                    >
                      <div style="width: 1px"></div>
                      <div
                        style="
                          background-color: rgb(245, 245, 245);
                          width: auto;
                          height: 100%;
                          margin-left: 1px;
                        "
                      ></div>
                    </div>
                    <div
                      style="
                        position: relative;
                        padding-right: 6px;
                        padding-left: 6px;
                        box-sizing: border-box;
                        font-family: Roboto, Arial, sans-serif;
                        font-size: 10px;
                        color: rgb(0, 0, 0);
                        white-space: nowrap;
                        direction: ltr;
                        text-align: right;
                        vertical-align: middle;
                        display: inline-block;
                      "
                    >
                      <span>10 km&nbsp;</span>
                      <div
                        style="
                          position: relative;
                          display: inline-block;
                          height: 8px;
                          bottom: -1px;
                          width: 72px;
                        "
                      >
                        <div
                          style="
                            width: 100%;
                            height: 4px;
                            position: absolute;
                            left: 0px;
                            top: 0px;
                          "
                        ></div>
                        <div
                          style="
                            width: 4px;
                            height: 8px;
                            left: 0px;
                            top: 0px;
                            background-color: rgb(255, 255, 255);
                          "
                        ></div>
                        <div
                          style="
                            width: 4px;
                            height: 8px;
                            position: absolute;
                            background-color: rgb(255, 255, 255);
                            right: 0px;
                            bottom: 0px;
                          "
                        ></div>
                        <div
                          style="
                            position: absolute;
                            background-color: rgb(102, 102, 102);
                            height: 2px;
                            left: 1px;
                            bottom: 1px;
                            right: 1px;
                          "
                        ></div>
                        <div
                          style="
                            position: absolute;
                            width: 2px;
                            height: 6px;
                            left: 1px;
                            top: 1px;
                            background-color: rgb(102, 102, 102);
                          "
                        ></div>
                        <div
                          style="
                            width: 2px;
                            height: 6px;
                            position: absolute;
                            background-color: rgb(102, 102, 102);
                            bottom: 1px;
                            right: 1px;
                          "
                        ></div>
                      </div>
                    </div>
                    <span
                      id="8B66F63E-B8AA-49EC-9975-2BD23E826F77"
                      style="display: none"
                      >클릭하여 미터법과 야드파운드법 간에 전환</span
                    >
                  </button>
                  <div
                    class="gmnoprint gm-style-cc"
                    draggable="false"
                    style="
                      z-index: 1000001;
                      user-select: none;
                      position: relative;
                      height: 14px;
                      line-height: 14px;
                    "
                  >
                    <div
                      style="
                        opacity: 0.7;
                        width: 100%;
                        height: 100%;
                        position: absolute;
                      "
                    >
                      <div style="width: 1px"></div>
                      <div
                        style="
                          background-color: rgb(245, 245, 245);
                          width: auto;
                          height: 100%;
                          margin-left: 1px;
                        "
                      ></div>
                    </div>
                    <div
                      style="
                        position: relative;
                        padding-right: 6px;
                        padding-left: 6px;
                        box-sizing: border-box;
                        font-family: Roboto, Arial, sans-serif;
                        font-size: 10px;
                        color: rgb(0, 0, 0);
                        white-space: nowrap;
                        direction: ltr;
                        text-align: right;
                        vertical-align: middle;
                        display: inline-block;
                      "
                    >
                      <a
                        href="https://www.google.com/intl/ko-KR_KR/help/terms_maps.html"
                        target="_blank"
                        rel="noopener"
                        style="
                          text-decoration: none;
                          cursor: pointer;
                          color: rgb(0, 0, 0);
                        "
                        >약관</a
                      >
                    </div>
                  </div>
                  <div
                    draggable="false"
                    class="gm-style-cc"
                    style="
                      user-select: none;
                      position: relative;
                      height: 14px;
                      line-height: 14px;
                      display: none;
                    "
                  >
                    <div
                      style="
                        opacity: 0.7;
                        width: 100%;
                        height: 100%;
                        position: absolute;
                      "
                    >
                      <div style="width: 1px"></div>
                      <div
                        style="
                          background-color: rgb(245, 245, 245);
                          width: auto;
                          height: 100%;
                          margin-left: 1px;
                        "
                      ></div>
                    </div>
                    <div
                      style="
                        position: relative;
                        padding-right: 6px;
                        padding-left: 6px;
                        box-sizing: border-box;
                        font-family: Roboto, Arial, sans-serif;
                        font-size: 10px;
                        color: rgb(0, 0, 0);
                        white-space: nowrap;
                        direction: ltr;
                        text-align: right;
                        vertical-align: middle;
                        display: inline-block;
                      "
                    >
                      <a
                        target="_blank"
                        rel="noopener"
                        title="Google에 도로 지도 또는 이미지 오류 신고"
                        dir="ltr"
                        href="https://www.google.com/maps/@16.0833543,108.2078731,10z/data=!10m1!1e1!12b1?source=apiv3&amp;rapsrc=apiv3"
                        style="
                          font-family: Roboto, Arial, sans-serif;
                          font-size: 10px;
                          color: rgb(0, 0, 0);
                          text-decoration: none;
                          position: relative;
                        "
                        >지도 오류 신고</a
                      >
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
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
                <div
                  size="13"
                  color="gray500"
                  class="text__Text-sc-6cffe184-0 dnGFrr"
                >
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

:root {
  --color-gray: rgba(58, 58, 58, 1);
  --color-gray20: rgba(58, 58, 58, 0.02);
  --color-gray50: rgba(58, 58, 58, 0.05);
  --color-gray100: rgba(58, 58, 58, 0.1);
  --color-gray200: rgba(58, 58, 58, 0.2);
  --color-gray300: rgba(58, 58, 58, 0.3);
  --color-gray400: rgba(58, 58, 58, 0.4);
  --color-gray500: rgba(58, 58, 58, 0.5);
  --color-gray600: rgba(58, 58, 58, 0.6);
  --color-gray700: rgba(58, 58, 58, 0.7);
  --color-gray800: rgba(58, 58, 58, 0.8);
  --color-gray900: rgba(58, 58, 58, 0.9);
  --color-brightGray: rgba(239, 239, 239, 1);
  --color-blue: rgba(54, 143, 255, 1);
  --color-blue60: rgba(54, 143, 255, 0.06);
  --color-blue100: rgba(54, 143, 255, 0.1);
  --color-blue500: rgba(54, 143, 255, 0.5);
  --color-blue980: rgba(54, 143, 255, 0.98);
  --color-mint: rgba(38, 206, 194, 1);
  --color-mint100: rgba(38, 206, 194, 0.1);
  --color-orange: rgba(255, 150, 35, 1);
  --color-red: rgba(253, 46, 105, 1);
  --color-red100: rgba(253, 46, 105, 0.1);
  --color-red50: rgba(253, 46, 105, 0.05);
  --color-deepOrange: rgba(255, 91, 46, 1);
  --color-mediumRed: rgba(255, 33, 60, 1);
  --color-deepRed: rgba(190, 0, 23, 1);
  --color-purple: rgba(151, 95, 254, 1);
  --color-purple100: rgba(151, 95, 254, 0.1);
  --color-emerald: rgba(13, 208, 175, 1);
  --color-white: rgba(255, 255, 255, 1);
  --color-white600: rgba(255, 255, 255, 0.6);
  --color-white900: rgba(255, 255, 255, 0.9);
  --color-skyblue: rgba(55, 168, 255, 1);
  --color-lightpurple: rgba(151, 95, 254, 1);
  --color-black: rgba(34, 34, 34, 1);
  --color-azul: rgba(31, 87, 250, 1);
  --color-azul500: rgba(31, 87, 250, 0.5);
  --color-teal: rgba(10, 219, 143, 1);
  --color-teal100: rgba(10, 219, 143, 0.1);
  --color-teal900: rgba(10, 219, 143, 0.9);
  --color-vermilion: rgb(255, 97, 105, 1);
}

:root {
  --toastify-color-light: #fff;
  --toastify-color-dark: #121212;
  --toastify-color-info: #3498db;
  --toastify-color-success: #07bc0c;
  --toastify-color-warning: #f1c40f;
  --toastify-color-error: #e74c3c;
  --toastify-color-transparent: hsla(0, 0%, 100%, 0.7);
  --toastify-icon-color-info: var(--toastify-color-info);
  --toastify-icon-color-success: var(--toastify-color-success);
  --toastify-icon-color-warning: var(--toastify-color-warning);
  --toastify-icon-color-error: var(--toastify-color-error);
  --toastify-toast-width: 320px;
  --toastify-toast-background: #fff;
  --toastify-toast-min-height: 64px;
  --toastify-toast-max-height: 800px;
  --toastify-font-family: sans-serif;
  --toastify-z-index: 9999;
  --toastify-text-color-light: #757575;
  --toastify-text-color-dark: #fff;
  --toastify-text-color-info: #fff;
  --toastify-text-color-success: #fff;
  --toastify-text-color-warning: #fff;
  --toastify-text-color-error: #fff;
  --toastify-spinner-color: #616161;
  --toastify-spinner-color-empty-area: #e0e0e0;
  --toastify-color-progress-light: linear-gradient(
    90deg,
    #4cd964,
    #5ac8fa,
    #007aff,
    #34aadc,
    #5856d6,
    #ff2d55
  );
  --toastify-color-progress-dark: #bb86fc;
  --toastify-color-progress-info: var(--toastify-color-info);
  --toastify-color-progress-success: var(--toastify-color-success);
  --toastify-color-progress-warning: var(--toastify-color-warning);
  --toastify-color-progress-error: var(--toastify-color-error);
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
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 0%,
    var(--color-white) 100%
  );
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
