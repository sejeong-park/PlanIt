import { ref } from "vue";
import { defineStore } from "pinia";

export const usePlanStore = defineStore("plan", () => {
  const planKey = ref("3f54528e-19a2-4f8e-8ce5-92bbf281be48");

  return { planKey };
});
