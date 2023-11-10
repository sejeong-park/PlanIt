
import './assets/reset.css'

import { createApp } from 'vue'
// import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'


import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/reset.css';


// vuesax
// import Vuesax from 'vuesax'
// import 'vuesax/dist/vuesax.css' //Vuesax styles

const app = createApp(App)

// app.use(createPinia())
app.use(router)

app.use(Antd) // Ant-Design-Vue 적용
// app.use(Vuesax)

app.mount('#app')
