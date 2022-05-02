<template>
<div></div>
</template>

<script>
import { onMounted } from 'vue'
import { useStore } from 'vuex'
import { useRouter, useRoute } from 'vue-router'

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
      alert(route)
      if (localStorage.getItem("jwt")) {
        if (isLogin) {
          store.commit("root/LOGIN_STATUS", true)
          await store.dispatch("root/getUserInfo", { 'jwt': token, 'memberId': memberId })
          await router.push({
            name: "Main",
          })
        } else {
          await router.push({
            name: "SignUpSecond"
          })
        }
      } else {
        alert('로그인 실패')
      }
    })

    return { onMounted }
  }
}
</script>
