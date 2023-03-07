<template>
  <div class="home">
    <h1>Hamburger Essen</h1>
    <br>
    <form method="none">
      <label for="query">
        <input
        id="query"
        name="query"
        v-model="query"
        type="text"
        placeholder="Type what you are searching for...">
      </label>
      <label for="ownaddress">
        <input
          type="text"
          name="ownaddress"
          id="ownaddress"
          v-model="ownaddress"
          placeholder="Your address"
        >
      </label>
      <label for="distance">
        <h1>
          Distance in kilometres <br>
        </h1>
        <input
          type="number"
          name="distance"
          id="distance"
          v-model="distance"
          value="20"
          min="0"
          step="1"
        >
      </label>
      <div class="tags">
        <label v-for="tag of tags" :key="tag.id" :for="tag.id">
          <input
            class="tag"
            type="checkbox"
            :id="tag.id"
            :value="tag.id"
            v-model="selectedTagsIds"
            :placeholder="tag.tag"
          >
          {{ tag.tag }}
        </label>
      </div>
    </form>
    <button @click.prevent="searchRestaurants()" class="search">Search &#x1F50D;</button>
    <div class="results">
      <h1>Results</h1>
      <div v-for="restaurant of restaurants" :key="restaurant.id" class="restaurant">
        <div class="image">
          <img :src="getImageUrl(restaurant.imageUrl)" alt="" class="Thumbnail">
        </div>
        <div class="text">
          <h3>{{ restaurant.name }}</h3>
          <p class="description" v-html="restaurant.description"></p>
          <div class="singleRestaurantTags">
            <p
              class="singleRestaurantTag"
              v-for="tag of restaurant.tags"
              :key="tag.id"
            >{{ tag.tag }}</p>
          </div>
          <div class="link">
            <router-link :to="'/singleRestaurant?restaurantId=' + restaurant.id" >
              Mehr Anzeigen
            </router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import BackendService from '@/service/backendService';

export default {
  name: 'HomeView',
  data() {
    return {
      query: '',
      distance: 0,
      ownaddress: '',
      tags: [
      ],
      selectedTagsIds: [],
      restaurants: [],
      location: {
        lat: 2,
        lon: 2,
      },
    };
  },
  components: {
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
    async searchRestaurants() {
      /* eslint-disable-next-line */
      let location = await BackendService.getLatLonForAddress(this.ownaddress)
      // console.log(await BackendService.getLatLonForAddress(this.ownaddress));
      /* eslint-disable-next-line */
      console.log('location: ' + location);
      console.log(this.ownaddress);
      if (this.ownaddress === '') {
        this.distance = 40000;
        location = {
          lat: 0,
          lon: 0,
        };
      }
      await BackendService.searchForRestaurants(
        this.query,
        this.selectedTagsIds,
        location,
        this.distance,
      )
        .then((response) => {
          this.restaurants = response.data;
        });
    },
  },
  async mounted() {
    await BackendService.getTags()
      .then((response) => {
        console.log(response);
        this.tags = response.data;
      });
  },
};
</script>

<style lang="scss" scoped>

.home {
  margin-top: 60px;
  min-height: 100vh;
  width: calc(100% - 2 * 48px);
  padding: 48px;
}

form {
  width: 100%;
  display: flex;
  flex-direction: column;
  h1 {
    margin-top: 16px;
    padding: 16px;
    font-size: 1em;
  }
  input {
    padding: 16px;
    margin: 10px;
    width: 350px;
    border: 1px solid gray;
    border-radius: 100px;
    font-size: 1em;
    &:focus {
      background-color: lightgray;
    }
  }
  #distance {
    width: 120px;
  }
  .tags label input {
    width: 20px;
    appearance: checkbox;
    accent-color: $prime-accent-color;
    &:checked {
      background-color: $prime-accent-color;
    }
  }
}

.restaurant{
  background-color: white;
  box-shadow: 4px 4px 4px 4px rgba(0,0,0,.4);
  border: 1px solid rgba(0,0,0,.4);
  border-radius: 10px;
  display: flex;
  flex-direction: row;
  padding: 10px;
  margin: 15px;
  img {
    height: 350px;
    width: 350px;
  }
  .singleRestaurantTags{
    display: flex;
    flex-direction: row;
    width: 100%;
    flex-flow: row wrap;
  }
  .singleRestaurantTag{
    border-radius: 10px;
    background-color: $second-accent-color;
    color: #222;
    padding: 8px;
    margin: 10px;
  }
  .text {
    display: flex;
    flex-direction: column;
    margin: 5px;
    padding: 7px;
    .description {
      margin: 16px 0;
      max-height: 250px;
      overflow: hidden;
    }
    .link {
      margin: 3px;
      font-size: 0,7em;
      background-color: #222;
      border-radius: 100px;
      width: fit-content;
      height: 48px;
      transition: .25s;
      display: flex;
      justify-content: center;
      align-items: center;
      &:hover {
        background-color: $prime-accent-color;
        a {
          color: black;
        }
      }
      a {
        text-decoration: none;
        color: white;
        padding: 16px;
        margin-top: 26px;
        font-size: 1em;
        width: 100%;
        height: 100%;
      }
    }
  }
}

.search {
  height: 60px;
  width: fit-content;
  font-size: 1.5em;
  background-color: $prime-accent-color;
  color: black;
  padding: 10px 20px;
  margin: 16px;
  border-radius: 10px;
  border: none;
  transition: .25s;
  &:hover {
    color: white;
    background-color: #222;
  }
}
@media screen and (max-width: 750px) {
  .restaurant {
    flex-direction: column;
  }
}
@media screen and (max-width: 500px) {
#query, #ownaddress {
  width: 250px;
}

.restaurant img {
      width:90%;
      height: auto;
    }
}
</style>
