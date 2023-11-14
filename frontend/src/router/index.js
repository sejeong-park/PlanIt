import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: () => import("../views/HomeView.vue"),
    },
    {
      path: "/users",
      name: "users",
      children: [
        {
          path: "regist",
          name: "user-regist",
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
      name: "board",
      children: [
        {
          path: "",
          name: "board-list",
          component: () => import("../views/BoardListView.vue"),
        },
        {
          path: "regist",
          name: "board-regist",
          component: () => import("../views/BoardRegistView.vue"),
        },
        {
          path: "thumbnail",
          name: "thumbnail",
          component: () => import("../views/BoardThumbnailView.vue"),
        },
      ],
    },
  ],
});

export default router;
