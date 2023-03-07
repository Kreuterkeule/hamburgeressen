<template>
  <div class="about">
    <main>
      <div class="start">
        <h1>About us</h1>
        <h5>Hey! We're Moritz Siefke and Ettan Sargent</h5>
      </div>
      <div class="section1">
        <h2>Who are we?</h2>
        <h5>We're two young men on a journey,
          making a website which is to be loved by a community</h5>
      </div>
      <div class="section2">
        <h2>Why did we create HamburgerEssen?</h2>
        <h5>We had time in our holidays</h5>
      </div>
      <div class="section1">
        <h2>How long did it take to create this very awesome and very cool looking amazing
           tool that is also very sexy and makes everyone
            who sees it jealous?</h2>
        <h5>The main framework only took us about 3 days, but
          creating the databasses takes way longer, for that we need you, and
          your restaurant. Contact us <a href="/register">here</a> </h5>
      </div>
    </main>
  </div>
</template>

<style lang="scss" scoped>

.about {
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
