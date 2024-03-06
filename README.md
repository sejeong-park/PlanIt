# PlanIt

> 삼성 청년 소프트웨어 아카데미(SSAFY) 10기 1학기 관통 프로젝트

![image](https://github.com/sejeong-park/PlanIt/assets/90013342/6dcb6159-bad0-49fe-9aab-26b53faad958)


## ✨ 프로젝트 개요

🏷 **프로젝트 이름: PlanIt**

🗓️ **프로젝트 기간: 2023.11.03 ~ 2023.11.22 (3주)**

👥 **구성원: 👑박세정, 김성수**

---

## 🌃 기획 의도

> '국내 여행 계획을 여럿이서 쉽고 편하게 짤 수 없을까?'

시간 내서 가는 여행인 만큼 인기 있고 검증된 명소로 여행을 가고 싶은 마음은 모두가 같겠죠?

또, 여행 계획을 짜는데 시간이 너무 오래 걸려도 지쳐버리기 일 쑤입니다.

그래서, 저희는 이러한 불편함을 개선하고자 PlanIt을 만들었습니다.

모두 **PlanIt**에 놀러와서 쉽고 편하게 여행 계획을 세워보세요!

---

## 💎 서비스 소개

- 시와 도를 입력하면 카카오 지도로 **해당 지역의 여행 명소 상세 정보를 제공**합니다.
- **사용자는 여러 명소를 선택하여 여행 동선을 계획**할 수 있습니다.
- **선택한 명소별로 상세 계획을 메모**할 수 있습니다.
- **나의 여행 계획을 전체 사용자와 공유**할 수 있습니다.
---

## 🎯 서비스 대상

- 쉽고 편하게 여행 계획을 세우고 공유하고 싶은 사람 누구나

---

## 👨‍👩‍👧‍👦 구성원

| [![정필모](https://github.com/itsmo1031.png)](https://github.com/itsmo1031) | [![김병현](https://github.com/byunghyunkim0.png)](https://github.com/itsmo1031) | [![김성수](https://github.com/ksungsu.png)](https://github.com/ksungsu) | [![김현지](https://github.com/hjjj99.png)](https://github.com/hjjj99) | [![박세정](https://github.com/sejeong-park.png)](https://github.com/sejeong-park) | [![전은평](https://github.com/eunpyeong114.png)](https://github.com/eunpyeong114) |
| :-------------------------------------------------------------------------: | :-----------------------------------------------------------------------------: | :---------------------------------------------------------------------: | :-------------------------------------------------------------------: | :-------------------------------------------------------------------------------: | :-------------------------------------------------------------------------------: |
|                   [👑정필모](https://github.com/itsmo1031)                   |                   [김병현](https://github.com/byunghyunkim0)                    |                  [김성수](https://github.com/ksungsu)                   |                  [김현지](https://github.com/hjjj99)                  |                     [박세정](https://github.com/sejeong-park)                     |                     [전은평](https://github.com/eunpyeong114)                     |

---

## 👩‍💻 역할

### 김성수

- **FullStack**
  - Swagger 셋업
  - Spring Security 적용
    - OAuth2.0 모듈 적용
  - 카카오 소셜 로그인 적용
    - JWT 적용
  - User API 작성
    - 회원 가입/로그인/로그아웃/수정
  - Feed API 작성
  - File Upload API 작성
- **ETC**
  - UCC 제작
  - 최종 발표 프레젠테이션 제작

### 박세정

- **FullStack**
  - Redis 운영 총괄
  - Waitroom(대기방) API 작성
  - Player API 작성
  - Gameroom(문답방) API 작성
    - 마니또-마니띠 매칭 로직 작성
  - Question API 작성
    - 데일리 질문 생성 로직 작성
  - User API 작성
    - 회원별 방 목록 조회
- **ETC**
  - Notion 문서 작성 총괄
  - 최종 발표

---

## 🌈 서비스 화면 소개

| ![Main Page](./assets/screen/main.gif) | ![Wait Room](./assets/screen/waitroom.gif) | ![Game Start](./assets/screen/game_start.gif) |
| :------------------------------------: | :----------------------------------------: | :-------------------------------------------: |
|                홈 화면                 |                대기방 생성                 |                   게임 시작                   |

| ![Enter Waitroom](./assets/screen/waitroom_enter.gif) | ![Enter Room](./assets/screen/room.gif) | ![Write Feed](./assets/screen/write_feed.gif) |
| :---------------------------------------------------: | :-------------------------------------: | :-------------------------------------------: |
|                      대기방 입장                      |                 방 입장                 |                   피드 작성                   |

| ![Predict Success](./assets/screen/predict_success.gif) | ![Enter Room](./assets/screen/predict_failed.gif) | ![Write Feed](./assets/screen/result.gif) |
| :-----------------------------------------------------: | :-----------------------------------------------: | :---------------------------------------: |
|                        예측 성공                        |                     예측 실패                     |         결과 확인 및 이미지 저장          |

---

## 🛠 기술 스택

### Frontend

<p>
	<img src="https://img.shields.io/badge/Nuxt.js-00DC82?style=flat-square&logo=Nuxt.js&logoColor=white">
  <img src="https://img.shields.io/badge/Typescript-3178C6?style=flat-square&logo=typescript&logoColor=white">
  <img src="https://img.shields.io/badge/TailwindCSS-06B6D4?style=flat-square&logo=TailwindCSS&logoColor=white"/>
  <img src="https://img.shields.io/badge/shadcn%2Fui-000000?style=flat-square&logo=shadcnui&logoColor=white
  "/>
  <img src="https://img.shields.io/badge/node.js-339933?style=flat-square&logo=nodedotjs&logoColor=white">
  <img src="https://img.shields.io/badge/PWA-5A0FC8?style=flat-square&logo=pwa&logoColor=white">
  <img src="https://img.shields.io/badge/yarn-2C8EBB?style=flat-square&logo=yarn&logoColor=white">
  <img src="https://img.shields.io/badge/dotenv-ECD53F?style=flat-square&logo=dotenv&logoColor=white">
  <img src="https://img.shields.io/badge/Firebase-FFCA28?style=flat-square&logo=firebase&logoColor=white">
  <img src="https://img.shields.io/badge/editorconfig-FEFEFE?style=flat-square&logo=editorconfig&logoColor=black">
</p>

### Backend

<p>
	<img src="https://img.shields.io/badge/Java-007396?style=flat-square&logo=OpenJDK&logoColor=white"/>
  <img src="https://img.shields.io/badge/Gradle-02303A?style=flat-square&logo=gradle&logoColor=white">
	<img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat-square&logo=springboot&logoColor=white"/>
	<img src="https://img.shields.io/badge/Spring_Security-6DB33F?style=flat-square&logo=springsecurity&logoColor=white"/>
  <img src="https://img.shields.io/badge/Firebase-FFCA28?style=flat-square&logo=firebase&logoColor=white">
  <img src="https://img.shields.io/badge/editorconfig-FEFEFE?style=flat-square&logo=editorconfig&logoColor=black">
</p>

### Database

<p>
  <img src="https://img.shields.io/badge/MariaDB-003545?style=flat-square&logo=mariadb&logoColor=white"/>
  <img src="https://img.shields.io/badge/Redis-DC382D?style=flat-square&logo=redis&logoColor=white"/>
</p>

### DevOps

<p>
  <img src="https://img.shields.io/badge/Ubuntu-E95420?style=flat-square&logo=ubuntu&logoColor=white">
  <img src="https://img.shields.io/badge/GitLab-FC6D26?style=flat-square&logo=gitlab&logoColor=white">
	<img src="https://img.shields.io/badge/AWS EC2-FF9900?style=flat-square&logo=amazonec2&logoColor=white"/>
	<img src="https://img.shields.io/badge/Jenkins-D24939?style=flat-square&logo=jenkins&logoColor=white"/>
	<img src="https://img.shields.io/badge/Docker-2496ED?style=flat-square&logo=docker&logoColor=white"/>
  <img src="https://img.shields.io/badge/Portainer-13BEF9?style=flat-square&logo=portainer&logoColor=white">
	<img src="https://img.shields.io/badge/NGINX-009639?style=flat-square&logo=nginx&logoColor=white"/>
  <img src="https://img.shields.io/badge/NGINX_Proxy_Manager-F15833?style=flat-square&logo=nginxproxymanager&logoColor=white">
</p>

### Communication

<p>
	<img src="https://img.shields.io/badge/figma-F24E1E?style=flat-square&logo=figma&logoColor=white">
	<img src="https://img.shields.io/badge/jira-0052CC?style=flat-square&logo=jira&logoColor=white">
	<img src="https://img.shields.io/badge/notion-000000?style=flat-square&logo=notion&logoColor=white">
	<img src="https://img.shields.io/badge/Mattermost-0058CC?style=flat-square&logo=mattermost&logoColor=white">
</p>

---

## 📝 프로젝트 산출물

- [Notion](https://ssafy-s10-a809.notion.site/Oomool-WIKI-e152fb257b3e45a1bbdb6fc3c569bbdd?pvs=4)

### 시스템 아키텍처

![System Architecture](./assets/system_architecture.png)

### E-R Diagram

![ERD](./assets/erd.png)
