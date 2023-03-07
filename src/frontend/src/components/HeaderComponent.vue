<template>
  <div>
    <nav>
      <router-link to="/">Home</router-link>
      <router-link to="/about">About</router-link>
      <router-link to="/documention">How to use?</router-link>
    </nav>
  </div>
</template>

<script>
export default {
  name: 'HeaderComponent',

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

nav {
  position: absolute;
  top: 0;
  width: 100%;
  display: flex;
  align-content: center;
  justify-content: center;
  height: 60px;
  background-color: #222;
  box-shadow: 4px 4px 4px 4px rgba(0,0,0,.4);
  position: fixed;
  a {
    color: white;
    text-decoration: none;
    padding: 16px;
    transition: .25s;
    display: flex;
    justify-content: center;
    align-items: center;
    &:hover {
      color: $prime-accent-color;
      text-decoration: underline;
    }
  }
}

</style>
