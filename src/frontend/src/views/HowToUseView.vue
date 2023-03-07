<template>
  <div class="documentation">
    <main>
      <div class="start">
        <h1>Documentation</h1>
      </div>
      <div class="section1">
        <div class="table-of-contents">
      <ul>
        <div class="section1">
          <li><h2>Disclaimers</h2></li>
          <ul>
            <li>Please keep in Mind, that this is a Hobby project,
              and that we are limited to 5000 request per day.
            </li>
            <li>
              This will change, if we see lots of love by the community
            </li>
            <li>
              follow the Project on <a href="https://github.com/Kreuterkeule/hamburgeressen">GitHub</a>
            </li>
          </ul>
        </div>
        <li><h2>How to search</h2></li>
        <ul>
          <li>Type your search.</li>
          <li>Type your address, so that we can find Restaurants in your place.</li>
          <li>Type you wished Distance.</li>
          <li>Click the Tags, that correspond to you search.</li>
          <li>Click Search and there you go!</li>
        </ul>
        <li>
          <h2>Features</h2>
        </li>
        <ul>
          <li>
            If the distance is set to 0, there is no distance set,
            so you will get Restaurants from the whole world.
          </li>
          <li>
            If no address is set, you will get the same as if no distance is set.
          </li>
        </ul>
      </ul>
    </div>
      </div>
    </main>
  </div>
</template>

<script>
export default {
  name: 'FrontendHowToUseView',

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

.documentation {
  margin-top: 60px;
  min-height: 100vh;
  width: 100%;
  .header {
  }
  .table-of-contents {
    ul {
      list-style-type: disc;
      ul {
        list-style-type: circle !important;
        margin-left: 30px;
      }
    }
  }
}

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
    text-align: left;
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
  .section1 li {
    font-size: .5em;
  }
}
</style>
