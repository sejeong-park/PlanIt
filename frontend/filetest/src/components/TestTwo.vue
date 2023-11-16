<script setup>
import { ref } from 'vue'
import axios from 'axios'

const selectedFile = ref(null)

const baseUrl = 'http://localhost:/boards'
const boardId = '1'

const handleFileChange = (event) => {
  selectedFile.value = event.target.files[0]
}

const uploadPost = async () => {
  try {
    const formData = new FormData()
    formData.append('file', selectedFile.value)

    const response = await axios.post(`${baseUrl}/${boardId}`, formData, {
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
    <input type="file" @change="handleFileChange" />
    <button @click="uploadPost">Upload Post</button>
  </div>
</template>

<style scoped></style>
