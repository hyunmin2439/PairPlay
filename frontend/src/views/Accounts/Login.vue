<template>
  <div></div>
</template>

<script>
import { reactive } from "vue";
// import { useStore } from 'vuex'
export default {
  name: "Login",
  setup() {
    // const store = useStore()
    const state = reactive({
      email: "",
    });
    return { state };
  },
};
</script>

<style></style>
