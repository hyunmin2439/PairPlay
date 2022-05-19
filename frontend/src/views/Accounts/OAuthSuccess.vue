<template>
<div></div>
</template>

<script>
import { onMounted } from 'vue'
import { useStore } from 'vuex'
import { useRouter, useRoute } from 'vue-router'
import Swal from 'sweetalert2'

export default {
  name: 'OAuthSuccess',
  setup() {
    const store = useStore()
    const route = useRoute()
    const router = useRouter()

    onMounted(async() => {
      const token = route.query.accessToken;
      const memberId = route.query.memberId
      // isLogin : true => 메인페이지 / false => 2차 로그인
      const isLogin = route.query.isLogin;
      localStorage.setItem("jwt", token)
      if (localStorage.getItem("jwt")) {
        if (isLogin==1) {
          Swal.fire({
            icon: 'success',
            title: '성공!',
            text: '로그인에 성공했습니다.',
          })
          store.commit("root/LOGIN_STATUS", true)
          await store.dispatch("root/getUserInfo", { 'jwt': token, 'memberId': memberId })
          await router.push({
            name: "Main",
          })
        } else if (isLogin==0) {
          await router.push({
            name: "SignUpSecond"
          })
        }
      } else {
        Swal.fire({
          icon: 'error',
          title: '실패...',
          text: '로그인에 실패했습니다.',
        })
      }
    })

    return { onMounted }
  }
}
</script>
