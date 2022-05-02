<template>
  <div>
    {{ state.userInfo }}
  </div>
</template>

<script>
import { reactive, computed } from "vue"
import { useStore } from 'vuex'
export default {
  name: 'Profile',
  setup() {
    const store = useStore()
    const state = reactive({
      userInfo: computed(() => store.getters["root/userInfo"])
    })

    return { state }
  }
}
</script>

<style>

</style>