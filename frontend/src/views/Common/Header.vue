<template>
  <div class="header-main is-sticky" :class="isTransparent">
    <div class="container">
      <div class="header-wrap">
        <!-- logo -->
        <div class="header-logo">
          <!-- logo link -->
          <router-link to="/" class="logo-link">
            <p class="logo">Pairplay</p>
            <!-- <img class="logo-dark logo-img" :src="require('@/images/logo-black.png')" alt="logo" /> -->
            <!-- <img class="logo-light logo-img" :src="require('@/images/logo-white.png')" alt="logo" /> -->
          </router-link>
        </div>
        <!-- .header-logo -->
        <!-- mobile action -->
        <MobileAction></MobileAction>
        <!-- heder search -->
        <HeaderSearch></HeaderSearch>
        <!-- Menu -->
        <Menu classname="btn-dark"></Menu>
        <div class="header-overlay"></div>
      </div>
      <!-- .header-warp-->
    </div>
    <!-- .container-->
  </div>
  <!-- .header-main-->
</template>
<script>
export default {
  name: "Header",
  props: [],
  components: {},
};
</script>
