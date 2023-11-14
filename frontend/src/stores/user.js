import { ref, computed } from "vue";
import { defineStore } from "pinia";

export const useLoginStore = defineStore("login", () => {
  const loginStatus = ref(false);

  return { loginStatus };
});
