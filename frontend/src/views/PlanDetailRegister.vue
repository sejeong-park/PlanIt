<script setup>
import { ref } from "vue";
import axios from "axios";
import { usePlanStore } from "@/stores/plan";
import { useUserStore } from "@/stores/user";
import { useRouter } from "vue-router";
import { reactive, createVNode } from "vue";
import { Modal } from "ant-design-vue";
import { ExclamationCircleOutlined } from "@ant-design/icons-vue";

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
const open = ref(false);
const showModal = () => {
  /**
   * 저장하기 버튼을 누르는 순간 plan이 저장된다.
   * confirm : 확인 일 때
   * 저장된 해당 plan 값들을 가져와서 modal 창에 뿌려준다(지도 컴포넌트, 스케줄 컴포넌트, 탭 컴포넌트, title, content입력창은 따로)
   *
   * 취소 일 때 : 어디로 넘어가지?
   *
   */
  Modal.confirm({
    title: "게시글로 저장하시겠습니까?",
    icon: createVNode(ExclamationCircleOutlined),
    content: createVNode(
      "div",
      { style: "color:red;" },
      "확인을 누르면 게시글 작성 화면으로 이동합니다."
    ),
    onOk() {
      open.value = true;
    },
    onCancel() {
      //   alert("플랜이 저장되고, 저장된 플랜의 상세페이지로 이동합니다");
    },
    class: "test",
  });
};
const handleOk = (e) => {
  console.log(e);
  open.value = false;
};
</script>

<template>
  <div>
    <a-button type="primary" @click="showModal">저장하기</a-button>
    <a-modal v-model:open="open" title="Basic Modal" @ok="handleOk">
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
    </a-modal>
  </div>
</template>
