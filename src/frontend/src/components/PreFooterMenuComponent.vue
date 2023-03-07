<template>
  <div class="footer-menu">
    <router-link to="/about">About</router-link>
    <router-link to="/legal">Legal</router-link>
    <router-link to="/register">how can I register my restaurant</router-link>
  </div>
</template>

<script>
export default {
  name: 'FrontendPreFooterMenuComponent',

  data() {
    return {

    };
  },

  mounted() {

  },

  methods: {

  },
};
</script>

<style lang="scss" scoped>
.footer-menu {
  width: 100%;
  height: 200px;
  background-color: #222;
  display: flex;
  align-items: center;
  justify-content: center;
  a {
    transition: .25s;
    color: white;
    text-decoration: none;
    margin: 16px;
    &:hover {
      color: #ED9737;
      text-decoration: underline;
    }
  }
}
</style>
