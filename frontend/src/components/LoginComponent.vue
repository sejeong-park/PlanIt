<script setup></script>

<template>
  <div class="background">
    <a-form class="login-form">
      <div><img src="@\assets\img\PlanIt!.png" alt="logo" /></div>

      <a-label for="id">아이디 </a-label>
      <a-input id="id" name="id" type="text"> </a-input>

      <a-label for="id">패스워드 </a-label>
      <a-input id="id" name="id" type="text"> </a-input>

      <a-button type="primary" htmlType="submit">로그인</a-button>
      <!-- <p>아직 회원이 아니세요?<a href="/users/regist">회원가입</a></p> -->

      <p>
        아직 회원이 아니세요?<router-link to="/users/regist"
          >회원가입</router-link
        >
      </p>
    </a-form>
  </div>
</template>

<style lang="scss" scoped>
.login-form {
  width: 27rem;
  height: 30rem;
  display: flex;
  flex-direction: column;
  justify-content: center; /* Center vertically */
  align-items: center; /* Center horizontally */
  margin: 10rem auto; /* Center within the parent container */
  border: 1px solid #20b2aa;
  border-radius: 2rem;

  a-label {
    margin-top: 1rem;
    text-align: left;
    color: gray;
  }

  button {
    margin-top: 1rem;

    border-radius: 2rem;
    width: 20rem;
    height: 2rem;
  }

  img {
    width: 12rem;
    height: 12rem;
  }

  p {
    color: gray;
  }
}
</style>
