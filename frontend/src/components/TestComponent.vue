<script setup>
import { ref } from 'vue'
import axios from 'axios'

/**
 * createUser만 프론트에서 넘겨주면 됨.
 */
const boardRegistDto = ref({})

const title = ref('')
const contents = ref('')
const selectedFile = ref(null)

const baseUrl = 'http://localhost:/boards'
const planKey = '303e14f1-cc7b-49b5-9da4-fe392fdd2af9'

const handleFileChange = (event) => {
  selectedFile.value = event.target.files[0]
  //   console.log(selectedFile.value)
}

const uploadPost = async () => {
  try {
    const formData = new FormData()
    boardRegistDto.value = {
      title: title.value,
      contents: contents.value
    }
    // console.log(boardRegistDto.value)
    formData.append(
      'boardRegistDto',
      new Blob([JSON.stringify(boardRegistDto.value)], { type: 'application/json' })
    )

    formData.append('file', selectedFile.value)

    console.log(formData)

    const response = await axios.post(`${baseUrl}/${planKey}`, formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })

    console.log('Post uploaded sucecess ', response.data)
  } catch (error) {
    console.error('Error uploading post :', error)
  }
}
</script>

<template>
  <div>
    <input type="text" v-model="title" placeholder="Title" />
    <textarea v-model="contents" placeholder="Content"></textarea>
    <input type="file" @change="handleFileChange" />
    <button @click="uploadPost">Upload Post</button>
  </div>
</template>

<style scoped></style>
