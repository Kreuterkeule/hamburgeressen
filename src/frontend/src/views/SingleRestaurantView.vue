<template>
  <div>
    <div class="site-content">
      <div class="header"
      :style="{
        backgroundImage: 'url(' + getImageUrl(restaurant.imageUrl) + ')'
      }">
              <h1>
          {{ restaurant.name }}
        </h1>
      </div>
      <div class="main-content">
        <div class="description" v-html="restaurant.description">
        </div>
        <h3>Contact:</h3>
        <br>
        <div class="phoneNumber">
        <h4>Phone number:</h4>
          {{ restaurant.phoneNumber }}
        </div>
        <div class="address">
          <h4>Address:</h4>
          {{ restaurant.street }} {{ restaurant.houseNumber }}
          <br>
          {{ restaurant.postalCode }} {{ restaurant.city }}
          <br>
          {{ restaurant.country }}
        </div>
        <iframe
          title="map"
          width="100%"
          height="200px"
          frameborder="0"
          scrolling="yes"
          marginheight="0"
          marginwidth="0"
          :src="'https://maps.google.com/maps?q='+restaurant.location.lat+','+restaurant.location.lon+'&hl=en&z=14&amp;output=embed'"
        >
        </iframe>
    </div>
    </div>

  </div>
</template>

<script>
import backendService from '@/service/backendService';

export default {
  name: 'FrontendSingleRestaurnatVue',

  data() {
    return {
      restaurant: {},
    };
  },

  mounted() {
    backendService.getRestaurantById(this.$route.query.restaurantId)
      .then((response) => {
        this.restaurant = response.data;
      });
  },

  methods: {
    getImageUrl(dynamicUrl) {
      try {
        /* eslint-disable-next-line */
        return require('../assets/images/' + dynamicUrl);
      } catch {
        /* eslint-disable-next-line */
        return require('../assets/images/default.png');
      }
    },
  },
};
</script>

<style lang="scss" scoped>

.site-content {
  margin-top: 60px;
  width: 100%;
  min-height: 100vh;
}

.header {
  background-repeat: no-repeat;
  background-size: cover;
  background-color: rgba(0,0,0,.7);
  background-blend-mode: multiply;
  background-position: center;
  width: 100%;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: 4px 4px 4px 4px rgba(0,0,0,.4);
  * {
    color: white;
    letter-spacing: .6em;
    max-width: 70%;
    font-size: 3em;
  }

}

.main-content {
    background-color: white;
    color: #222;
    display: flex;
    flex-direction: column;
    padding: 50px 20vw;
  }
</style>
