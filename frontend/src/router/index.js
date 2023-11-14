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
          component: () => import("../views/UserRegistView.vue"),
        },
        {
          path: "login",
          name: "login",
          component: () => import("../views/LoginView.vue"),
        },
      ],
    },
    {
      path: "/boards",
      name: "board-list",
      children: [
        {
          path: "",
          name: "board-list",
          component: () => import("../views/BoardListView.vue"),
        },
        {
          path: "regist",
          name: "regist",
          component: () => import("../views/BoardRegistView.vue"),
        },
      ],
    },
    // {
    //   path: "/",
    //   name: "main-page",
    //   component: () => import("@/App.vue"),
    // },
  ],
});

export default router;
