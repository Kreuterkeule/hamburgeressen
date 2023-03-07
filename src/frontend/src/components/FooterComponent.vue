<template>
    <div class="footer">
      <p class="copyright">Copyright Ettan Sargent and Moritz Siefke 2023</p>
    </div>
</template>

<script>

export default {
  name: 'FooterComponent',

  data() {
    return {

    };
  },

  mounted() {

  },

  methods: {

  },
  components: {
  },
};
</script>

<style lang="scss" scoped>

.footer {
  background-color: #111;
  width: 100%;
  min-height: 300px;
  bottom: -100px;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  .copyright {
    color: white;
    letter-spacing: .1em;
    text-align: center;
  }
}

</style>
