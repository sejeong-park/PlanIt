import { createRouter, createWebHistory } from 'vue-router';
import MainView from "@/views/MainView.vue";

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
      component : () => import("@/views/PlanView.vue")
    },
    {
      // 데이터까지 연결하면, {plan-key}로 라우터 변경할 예정
      path: '/trip',
      name : 'trip',
      component : () => import("@/views/TripView.vue")
    }

  ]
})

export default router
