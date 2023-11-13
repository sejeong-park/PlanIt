<script setup></script>

<template>
  <div class="background">
    <a-form class="register-form">
      <img src="@\assets\img\PlanIt!.png" alt="logo" />

      <a-label for="userId">아이디 </a-label>
      <a-input id="userId" name="userId" type="text"> </a-input>

      <a-label for="userPassword">패스워드 </a-label>
      <a-input id="userPassword" name="userPassword" type="password"> </a-input>

      <a-label for="nickname">닉네임 </a-label>
      <a-input id="nickname" name="nickname" type="text"> </a-input>

      <a-label for="phone">핸드폰</a-label>
      <a-input id="phone" name="phone" type="text"> </a-input>

      <a-label for="email">이메일</a-label>
      <a-input id="email" name="email" type="email"> </a-input>

      <div class="gender">
        <a-label for="gender">성별 </a-label>
        <a-label for="male">남</a-label>
        <a-input id="male" name="male" type="radio" value="male"></a-input>

        <a-label for="female">여</a-label>
        <a-input id="female" name="female" type="radio" value="female"></a-input>
      </div>

      <a-label for="birth">생일</a-label>
      <a-input id="birth" name="birth" type="date"> </a-input>

      <a-button type="primary" htmlType="submit">회원가입</a-button>
    </a-form>
  </div>
</template>

<style lang="scss" scoped>
.register-form {
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
    color: gray;
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

.gender {
  display: flex;
  align-items: center; /* 세로 중앙 정렬 */
}
</style>
