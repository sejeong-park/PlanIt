<script setup>
import { useRoute, useRouter } from 'vue-router';

// 플랜으로 이동
// const router = useRouter()
// const goPlan = function () { router.push({ name: 'plans' }) }

// const images = [
//     'image1.jpeg',
//     'image2.jpeg',
//     'image3.jpeg'
// ];

// const getRandomImageUrl = () => {
//     const randomIdx = Math.floor(Math.random() * images.length);
//     return `@/assets/img/main/${images[randomIdx]}`;
// };

// const backgroundImageStyle = ref({});

// onMounted(() =>{
//     backgroundImageStyle.value = {
//     'background-image': `url(${getRandomImageUrl()})`,
//     'background-size': 'cover',
//     'background-position': 'center',
//     'background-repeat': 'no-repeat',
//     'height': '100vh',
//     'width': '100%',
//     };
// });

</script>

<template>
    <!-- <a-button>Log in</a-button>
    <a-button type="primary" @click="goPlan">Plan it</a-button> -->


    <div class='box'>
        <div class='wave -one'></div>
        <div class='wave -two'></div>
        <div class='wave -three'></div>
        <div class='title'>Plan It</div>
    </div>
</template>

<style scoped>

.wave {
    opacity: .4;
    position: absolute;
    left: 70%;
    background: #0af;
    width: 500px;
    height: 500px;
    margin-left: -250px;
    margin-top: 250px;
    transform-origin: 50% 48%;
    border-radius: 43%;
    animation: drift 3000ms infinite linear;
}

.wave.-three {
    animation: drift 5000ms infinite linear;
}

.wave.-two {
    animation: drift 7000ms infinite linear;
    opacity: .1;
    background: yellow;
}

.box:after {
    content: '';
    display: block;
    left: 0;
    width: 100%;
    height: 100%;
    background: linear-gradient(to bottom, rgba(#e8a, 1), rgba(#def, 0) 80%, rgba(white, .5));
    z-index: 11;
    transform: translate3d(0, 0, 0);
}

.title {
    position: absolute;
    left: 0;
    top: 0;
    width: 100%;
    z-index: 1;
    line-height: 300px;
    text-align: center;
    transform: translate3d(0, 0, 0);
    color: white;
    text-transform: uppercase;
    font-family: 'Playfair Display', serif;
    letter-spacing: .4em;
    font-size: 24px;
    text-shadow: 0 1px 0 rgba(black, .1);
    text-indent: .3em;
}
@keyframes drift {
    from { transform: rotate(0deg); }
    from { transform: rotate(360deg); }
}
</style>
