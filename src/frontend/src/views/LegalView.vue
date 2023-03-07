<template>
  <div class="register">
    <main>
      <div class="start">
        <h1>Legal</h1>
        <h5>We are glad to tell you, that we don't save any usage data.</h5>
      </div>
      <div class="section1">
        <h2>What to do with questions and concerns</h2>
        <h5>If you have questions or concerns please contact us.
            (hamburgeressen@proton.me)
          We want to provide the best usage experience.</h5>
      </div>
    </main>
  </div>
</template>

<script>
export default {
  name: 'VisualStudioLiveShareHowToRegisterView',

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
.register {
  margin-top: 60px;
}

main{
  text-align: center;
  font-size: 2em;
  .start{
    background-color: $prime-accent-color;
    padding: 40px;
    h1 {
      padding: 15px;
    }
  }
  .section1{
    background-color: lightgray;
    color: black;
    padding: 40px;
    h2 {
      padding: 15px;
    }
  }
  .section2{
    background-color: #222;
    color: white;
    padding: 40px;
    h2 {
      padding: 15px;
    }
  }
}
</style>
