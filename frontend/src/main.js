import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'


import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/reset.css';
//import 'ant-design-vue/dist/antd.css'; // 또는 'ant-design-vue/dist/antd.less'


import './assets/styles/custom.css'; //  커스텀 scss


const app = createApp(App)

app.use(createPinia())
app.use(router)


app.use(Antd) // Ant-Design-Vue 적용

app.mount('#app')
