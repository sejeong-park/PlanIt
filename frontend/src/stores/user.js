import { ref } from "vue";
import { defineStore } from "pinia";

export const useUserStore = defineStore(
  "login",
  () => {
    const loginStatus = ref(false);
    const userId = ref("");
    const userName = ref("");

    return { loginStatus, userId, userName };
  },
  // { persist: { storage: localStorage } }
  { persist: { storage: sessionStorage } }
);
