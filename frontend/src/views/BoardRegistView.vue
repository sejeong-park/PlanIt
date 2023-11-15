<script setup>
import axios from "axios";
import { usePlanStore } from "@/stores/plan";
import { useUserStore } from "@/stores/user";
import { useRouter } from "vue-router";
import { reactive, ref } from "vue";

const router = useRouter();

/**
 * 유효성 검사.
 */
const formRef = ref();
const formState = reactive({
  title: "",
  content: "",
});
const validateTitle = async (_rule, value) => {
  if (value === "" && value.equals("")) {
    return Promise.reject("Please input the title");
  }
};

const validateContent = async (_rule, value) => {
  if (value === "") {
    return Promise.reject("Please input the content");
  }
};
const rules = {
  title: [
    {
      required: true,
      validator: validateTitle,
      trigger: "change",
    },
  ],
  content: [
    {
      validator: validateContent,
      trigger: "change",
    },
  ],
};
const layout = {
  labelCol: {
    span: 4,
  },
  wrapperCol: {
    span: 14,
  },
};
// const handleFinish = (values) => {
//   console.log(values, formState);
// };
// const handleFinishFailed = (errors) => {
//   console.log(errors);
// };
const resetForm = () => {
  formRef.value.resetFields();
};
// const handleValidate = (...args) => {
//   console.log(args);
// };

const planStore = usePlanStore();
const userStore = useUserStore();

const baseUrl = "http://localhost:/boards";
const planKey = planStore.planKey; // 여기에 실제 동적인 값 대입\

const planRegist = function () {
  if (formState.title.trim() !== "" && formState.content.trim() !== "") {
    axios
      .post(`${baseUrl}/${planKey}`, {
        userId: userStore.userId,
        planKey: planStore.planKey,
        title: formState.title,
        content: formState.content,
      })
      .then((response) => {
        console.log(response.data);
      });

    router.replace({ path: "/boards/thumbnail" });
  }
};

if (formState.title !== "" && formState.content !== "") {
  planRegist();
}
</script>

<template>
  <a-form
    ref="formRef"
    name="custom-validation"
    :model="formState"
    :rules="rules"
    v-bind="layout"
    @finish="handleFinish"
    @validate="handleValidate"
    @finishFailed="handleFinishFailed"
  >
    <a-form-item has-feedback label="title" name="title">
      <a-input v-model:value="formState.title" type="text" autocomplete="off" />
    </a-form-item>
    <a-form-item has-feedback label="content" name="content">
      <a-textarea v-model:value="formState.content" autocomplete="off" />
    </a-form-item>
    <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
      <a-button type="primary" html-type="submit" @click="planRegist()">저장</a-button>
      <a-button style="margin-left: 10px" @click="resetForm">초기화</a-button>
    </a-form-item>
  </a-form>
</template>

<style scoped></style>
