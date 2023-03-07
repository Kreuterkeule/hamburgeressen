<template>
  <div class="register">
    <main>
      <div class="start">
        <h1>How do I register my restaurant?</h1>
      </div>
      <div class="section1">
        <h5>You own and a restaurant and want people
            to search for it with HamburgerEssen.
            Here are some simple steps to get your restaurant
            onto this amazing tool.</h5>
      </div>
      <div class="section2">
        <h2>Write an E-Mail</h2>
        <h5>Registering your restaurant is really simple!
              Just write an E-Mail to hamburgeressen@proton.me.</h5>
      </div>
      <div class="section1">
        <h2>What information do we need?</h2>
        <h5>We require the following informatrion: <br>
            the name of the restaurant, <br>
            a description of the restaurant, <br>
            a picture without copyright that we're allowed to use,<br>
            what tags you want your restaurant to have, <br>
            a phone number,<br>
            and an address including street, house number,
            postal code, city and country.</h5>
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
@media screen and (max-width: 600px) {
  h1 {
    font-size: 1em;
  }
  .section1 {
    h2{
        font-size: .8em;
    }
    h4{
        font-size: .5em;
    }
  }
  .section2 {
    h2{
        font-size: .8em;
    }
    h4{
        font-size: .5em;
    }
}
}
</style>
