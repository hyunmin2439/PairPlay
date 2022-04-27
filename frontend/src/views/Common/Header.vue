<template>
  <div class="header-main is-sticky" :class="isTransparent">
    <div class="container">
      <div class="header-wrap">
        <!-- logo -->
        <div class="header-logo">
          <!-- logo link -->
          <router-link to="/" class="logo-link">
            <p class="logo">Pairplay</p>
          </router-link>
        </div>
        <!-- .header-logo -->
        <!-- mobile action -->
        <!-- <MobileAction></MobileAction> -->
        <!-- heder search -->
        <!-- <HeaderSearch></HeaderSearch> -->
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
