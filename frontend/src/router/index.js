import { createRouter, createWebHistory } from "vue-router";
import MainView from "@/views/MainView.vue";
import PlanView from "@/views/PlanView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "main",
      component: MainView,
    },
    {
      path: "/plans",
      name: "plans",
      component: PlanView,
    },
    {
      path: "/plans/planning",
      name: "planning",
      component: () => import("@/views/TripView.vue"),
    },
    {
      // 데이터 결과 View 만들기 위해 라우터 먼저 테스트
      path: "/plans/:planKey",
      name: "result",
      component: () => import("@/views/TripResultView.vue"),
    },
    {
      path: "/modal",
      name: "modal-test",
      component: () => import("@/components/modal/ParentComponent.vue"),
    },
    {
      path: "/users",
      name: "users",
      component: () => import("@/views/TheUserView.vue"),
      children: [
        {
          path: "regist",
          name: "user-regist",
          component: () => import("@/components/user/UserRegistComponent.vue"),
        },
        {
          path: "login",
          name: "login",
          component: () => import("@/components/user/UserLoginComponent.vue"),
        },
        {
          path: "mypage",
          name: "user-mypage",
          component: () => import("@/views/MyPageView.vue"),
        },
      ],
    },
    {
      path: "/boards",
      name: "board",
      component: () => import("@/views/TheBoardView.vue"),
      children: [
        {
          path: "",
          name: "board-list",
          component: () => import("@/components/board/BoardListComponent.vue"),
        },
        {
          path: "regist",
          name: "board-regist",
          component: () => import("@/components/board/BoardRegistComponent.vue"),
        },
        {
          path: "detail/:boardId",
          name: "board-detail",
          component: () => import("@/components/board/BoardDetailComponent.vue"),
        },
        {
          path: "thumbnailtest",
          name: "board-thumbnail-test",
          component: () => import("@/components/board/ThumbnailTest.vue"),
        },
      ],
    },
  ],
});

export default router;
