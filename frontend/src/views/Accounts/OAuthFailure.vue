<template>
<div></div>
</template>

<script>
import { onMounted } from 'vue'
import { useRouter } from 'vue-router'
import Swal from 'sweetalert2'

export default {
  name: 'OAuthFailure',
  setup() {
    const router = useRouter()

    onMounted(async() => {
      Swal.fire({
        icon: 'error',
        title: '실패...',
        text: '소셜 로그인에 실패했습니다!',
      })
      await router.push({ name: "Main"} )
    })

    return { onMounted }
  }
}
</script>
