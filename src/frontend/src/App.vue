<template>
  <div id="app">
    <HeaderComponent></HeaderComponent>
    <router-view></router-view>
    <PreFooterMenuComponent />
    <FooterComponent></FooterComponent>
  </div>
</template>

<script>

import HeaderComponent from './components/HeaderComponent.vue';
import FooterComponent from './components/FooterComponent.vue';
import PreFooterMenuComponent from './components/PreFooterMenuComponent.vue';

export default {
  name: 'AppRoot',
  components: {
    HeaderComponent,
    FooterComponent,
    PreFooterMenuComponent,
  },
};

</script>

<style lang="scss">

* {
  margin: 0;
  padding: 0;
}
</style>
