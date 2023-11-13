import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/users",
      name: "users",
      children: [
        {
          path: "regist",
          name: "regist",
          component: () => import("../views/RegistView.vue"),
        },
        {
          path: "login",
          name: "login",
          component: () => import("../views/LoginView.vue"),
        },
      ],
    },
  ],
});

export default router;
