import { createRouter, createWebHistory } from 'vue-router';
import MainView from "@/views/MainView.vue";
import PlanView from "@/views/PlanView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'main',
      component: MainView
    },
    {
      path: '/plans',
      name: 'plans',
      component : PlanView
    },{
      // 데이터 결과 View 만들기 위해 라우터 먼저 테스트
      path : '/result',
      name : 'result',
      component : () => import("@/views/TripResultView.vue")
    },
    {
      // 데이터까지 연결하면, {plan-key}로 라우터 변경할 예정
      path: '/trip',
      name : 'trip',
      component : () => import("@/views/TripView.vue")
    },
    {
      path: "/modal",
      name: "modal-test",
      component: () => import("@/components/modal/ParentComponent.vue"),
    },
    {
      path: "/plans",
      children: [
        {
          path: "regist", // 일단 동적 라우팅 (/: planKey는 사용 하지 않겠음, )
          name: "plan-detail-regist",
          component: () => import("@/views/PlanDetailRegister.vue"),
        },
      ],
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
          component: () =>
            import("@/components/board/BoardRegistComponent.vue"),
        },
        {
          path: "thumbnail",
          name: "thumbnail",
          component: () =>
            import("@/components/board/BoardThumbnailComponent.vue"),
        },
      ],
    },
  ]
})

export default router
