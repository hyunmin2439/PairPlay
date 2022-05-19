<template>
  <div>

  </div>
</template>

<script>
import { onMounted } from 'vue'
import { useRouter } from 'vue-router'
export default {
  name: "ProfileTemp",
  setup() {
    const router = useRouter()
    onMounted(() => {
      router.push({
        name: "Profile",
        params: {
          memberId: router.currentRoute.value.query["memberId"]
        }
      })
    })
    return { onMounted }
  }
}
</script>

<style>

</style>