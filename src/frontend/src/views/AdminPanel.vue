<template>
  <div class="adminPanel">
    <label for="username">
      <input type="text" name="username" id="username" v-model="username" placeholder="username">
    </label>
    <label for="password">
      <input type="text" name="password" id="password" v-model="password" placeholder="password">
    </label>
    <h2>Create Tag</h2>
    <form>
      <label for="tagName">
        <input type="text" name="tagName" id="tagName" v-model="tagName" placeholder="tagName">
      </label>
      <button @click.prevent="createTag()">Create</button>
    </form>
    <h2>Delete Tags</h2>
    <form>
      <div class="tags">
        <label v-for="tag of tags" :key="tag.id" :for="tag.id">
          <input
            class="tag"
            type="checkbox"
            :id="tag.id"
            :value="tag.id"
            v-model="tagsToDelete"
            :placeholder="tag.tag"
          >
          {{ tag.tag }}
        </label>
      </div>
      <button @click.prevent="deleteTags()">Delete Tags</button>
    </form>
    <h2>Create Restaurant</h2>
    <form>
      <label for="name">
        <input
          name="name"
          id="name"
          placeholder="name"
          v-model="restaurant.name"
        >
      </label>
      <label for="description">
        <input
          name="description"
          id="description"
          placeholder="description"
          v-model="restaurant.description"
        >
      </label>
      <label for="imageUrl">
        <input
          name="imageUrl"
          id="imageUrl"
          placeholder="imageUrl"
          v-model="restaurant.imageUrl"
        >
      </label>
      <label for="street">
        <input
          name="street"
          id="street"
          placeholder="street"
          v-model="restaurant.street"
        >
      </label>
      <label for="houseNumber">
        <input
          name="houseNumber"
          id="houseNumber"
          placeholder="houseNumber"
          v-model="restaurant.houseNumber"
        >
      </label>
      <label for="city">
        <input
          name="city"
          id="city"
          placeholder="city"
          v-model="restaurant.city"
        >
      </label>
      <label for="country">
        <input
          name="country"
          id="country"
          placeholder="country"
          v-model="restaurant.country"
        >
      </label>
      <label for="postalCode">
        <input
          name="postalCode"
          id="postalCode"
          placeholder="postalCode"
          v-model="restaurant.postalCode"
        >
      </label>
      <label for="phoneNumber">
        <input
          name="phoneNumber"
          id="phoneNumber"
          placeholder="phoneNumber"
          v-model="restaurant.phoneNumber"
        >
      </label>
      <div class="tags">
        <label v-for="tag of tags" :key="tag.id" :for="tag.id">
          <input
            class="tag"
            type="checkbox"
            :id="tag.id"
            :value="tag.id"
            v-model="restaurant.selectedTagIds"
            :placeholder="tag.tag"
          >
          {{ tag.tag }}
        </label>
      </div>
      <button @click.prevent="createRestaurant()">Create</button>
      <br>
      <div class="restaurant">
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
            <router-link to="#">
              Mehr Anzeigen
            </router-link>
          </div>
        </div>
      </div>
      <br>
    </form>
    <h2>Update</h2>
    <form>
      <label for="id">
        <input
        placeholder="id"
        type="number"
        min="0"
        tep="1"
        name="id"
        id="id"
        v-model="updateRestaurant.id">
      </label>
      <label for="name">
        <input
          name="name"
          id="name"
          placeholder="name"
          v-model="updateRestaurant.name"
        >
      </label>
      <label for="description">
        <input
          name="description"
          id="description"
          placeholder="description"
          v-model="updateRestaurant.description"
        >
      </label>
      <label for="imageUrl">
        <input
          name="imageUrl"
          id="imageUrl"
          placeholder="imageUrl"
          v-model="updateRestaurant.imageUrl"
        >
      </label>
      <label for="street">
        <input
          name="street"
          id="street"
          placeholder="street"
          v-model="updateRestaurant.street"
        >
      </label>
      <label for="houseNumber">
        <input
          name="houseNumber"
          id="houseNumber"
          placeholder="houseNumber"
          v-model="updateRestaurant.houseNumber"
        >
      </label>
      <label for="city">
        <input
          name="city"
          id="city"
          placeholder="city"
          v-model="updateRestaurant.city"
        >
      </label>
      <label for="country">
        <input
          name="country"
          id="country"
          placeholder="country"
          v-model="updateRestaurant.country"
        >
      </label>
      <label for="postalCode">
        <input
          name="postalCode"
          id="postalCode"
          placeholder="postalCode"
          v-model="updateRestaurant.postalCode"
        >
      </label>
      <label for="phoneNumber">
        <input
          name="phoneNumber"
          id="phoneNumber"
          placeholder="phoneNumber"
          v-model="updateRestaurant.phoneNumber"
        >
      </label>
      <div class="tags">
        <label v-for="tag of tags" :key="tag.id" :for="'update'+tag.id">
          <input
            class="tag"
            type="checkbox"
            :id="'update'+tag.id"
            :value="tag.id"
            v-model="updateRestaurant.selectedTagIds"
            :placeholder="tag.tag"
          >
          {{ tag.tag }}
        </label>
      </div>
      <button @click.prevent="executeUpdateRestaurant()">Update</button>
      <br>
      {{ updateRestaurant.selectedTagIds }}
      <div class="restaurant">
        <div class="image">
          <img :src="getImageUrl(updateRestaurant.imageUrl)" alt="" class="Thumbnail">
        </div>
        <div class="text">
          <h3>{{ updateRestaurant.name }}</h3>
          <p class="description" v-html="updateRestaurant.description"></p>
          <div class="singleRestaurantTags">
            <p
              class="singleRestaurantTag"
              v-for="tag of updateRestaurant.tags"
              :key="tag.id"
            >{{ tag.tag }}</p>
          </div>
          <div class="link">
            <router-link to="#">
              Mehr Anzeigen
            </router-link>
          </div>
        </div>
      </div>
      <br>
      {{ ResponseRestaurant }}
    </form>
    <h2>Search for restaurants to delete them</h2>
    <form method="none">
      <label for="query">
        <input
        id="query"
        name="query"
        v-model="query"
        type="text"
        placeholder="Type what you are searching for...">
      </label>
    </form>
    <button @click.prevent="searchRestaurants()" class="search">Search &#x1F50D;</button>
    <div class="results">
      <h1>Results</h1>
      <div v-for="restaurant of resultRestaurants" :key="restaurant.id" class="restaurant">
        <div class="image">
          <img :src="getImageUrl(restaurant.imageUrl)" alt="" class="Thumbnail">
        </div>
        <div class="text">
          <h3>{{ restaurant.name }}</h3>
        </div>
        <div>
          <button @click.prevent="deleteRestaurant(restaurant.id)">
            Delete
          </button>
          <button @click.prevent="selectToUpdateRestaurant(restaurant.id)">
            Update
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import backendService from '@/service/backendService';

export default {
  name: 'FrontendAdminPanel',

  data() {
    return {
      query: '',
      tagName: '',
      username: '',
      password: '',
      tags: [],
      tag: {
      },
      restaurant: {
        name: '',
        description: '',
        imageUrl: '',
        city: '',
        houseNumber: '',
        street: '',
        country: '',
        postalCode: '',
        phoneNumber: '',
        selectedTagIds: [],
      },
      updateRestaurant: {
        id: null,
        name: '',
        description: '',
        imageUrl: '',
        city: '',
        houseNumber: '',
        street: '',
        country: '',
        postalCode: '',
        phoneNumber: '',
        selectedTagIds: [],
        tags: [],
      },
      ResponseRestaurant: {

      },
      resultRestaurants: [

      ],
      tagsToDelete: [],
    };
  },

  async mounted() {
    await backendService.getTags()
      .then((response) => {
        console.log(response);
        this.tags = response.data;
      });
  },

  methods: {
    deleteTags() {
      backendService.deleteTagsById(this.tagsToDelete, this.username, this.password);
    },
    getImageUrl(dynamicUrl) {
      try {
        /* eslint-disable-next-line */
        return require('../assets/images/' + dynamicUrl);
      } catch {
        /* eslint-disable-next-line */
        return require('../assets/images/default.png');
      }
    },
    createTag() {
      backendService.createTag(this.tagName, this.username, this.password)
        .then((response) => {
          console.log(response.data);
          this.tag = response.data;
        });
    },
    createRestaurant() {
      backendService.createRestaurant(this.restaurant, this.username, this.password)
        .then((response) => {
          this.ResponseRestaurant = response.data;
        });
    },
    searchRestaurants() {
      backendService.searchForRestaurants(
        this.query,
        [],
        {
          lat: 0,
          lon: 0,
        },
        '40000',
      )
        .then((response) => {
          this.resultRestaurants = response.data;
        });
    },
    deleteRestaurant(id) {
      backendService.deleteRestaurant(
        id,
        this.username,
        this.password,
      );
    },
    selectToUpdateRestaurant(id) {
      backendService.getRestaurantById(id)
        .then((response) => {
          this.updateRestaurant = response.data;
          this.updateRestaurant.selectedTagIds = [];
          /* eslint-disable-next-line */
          for (const tag of response.data.tags) {
            console.log(tag);
            this.updateRestaurant.selectedTagIds.push(tag.id);
          }
        });
    },
    executeUpdateRestaurant() {
      backendService.updateRestaurant(this.updateRestaurant, this.username, this.password)
        .then((response) => {
          this.updateRestaurant = response.data;
          this.updateRestaurant.selectedTagIds = [];
          /* eslint-disable-next-line */
          for (const tag of response.data.tags) {
            console.log(tag);
            this.updateRestaurant.selectedTagIds.push(tag.id);
          }
        });
    },
  },
  components: {
  },
};
</script>

<style lang="scss" scoped>
.adminPanel {
  margin: 100px;
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
@media screen and (max-width: 750px) {
  .restaurant {
    flex-direction: column;
  }
}
@media screen and (max-width: 500px) {
  .restaurant img {
        width:90%;
        height: auto;
      }
  }
</style>
