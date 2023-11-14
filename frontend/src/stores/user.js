import { ref } from "vue";
import { defineStore } from "pinia";

export const useUserStore = defineStore(
  "login",
  () => {
    const loginStatus = ref(false);
    const userId = ref("");

    return { loginStatus, userId };
  },
  { persist: { storage: localStorage } }
);
