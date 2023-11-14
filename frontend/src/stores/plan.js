import { ref } from "vue";
import { defineStore } from "pinia";

export const usePlanStore = defineStore("plan", () => {
  // const planKey = ref("3f54528e-19a2-4f8e-8ce5-92bbf281be48"); // 임의로 이렇게 설정함.
  const planKey = ref("87690a6a-32a7-42e7-a175-8fea7e06038a"); // 임의로 이렇게 설정함.
  return { planKey };
});
