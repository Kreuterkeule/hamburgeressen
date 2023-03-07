import axios from 'axios';

class BackendService {
  /* eslint-disable-next-line */
  getTags() {
    return axios.get('http://localhost:8084/api/allTags');
  }
  /* eslint-disable-next-line */
  async getLatLonForAddress(address) {
    /* eslint-disable-next-line */
    let latLon = {};
    /* eslint-disable-next-line */
    await axios.get(' http://localhost:8084/api/getLatLon?address=' + address)
      .then((response) => {
        console.log(response.data);
        latLon = response.data;
      });
    console.log(latLon);
    return latLon;
  }
  /* eslint-disable-next-line */
  async deleteRestaurant(id, username, password) {
    /* eslint-disable-next-line */
    let token = '';
    await this.getAuthenticationToken(username, password)
      .then((response) => {
        token = response.data.token;
      });
    console.log(token);
    /* eslint-disable-next-line */
    axios.get('http://localhost:8084/api/deleteRestaurantById?id=' + id, {
      headers: {
        /* eslint-disable-next-line */
        Authorization: 'Bearer ' + token,
      },
    });
  }
  /* eslint-disable-next-line */
  searchForRestaurants(query, tagIds, location, distanceAsString){
    // eslint-disable-next-line
    const distance = parseInt(distanceAsString);
    return axios.post(
      'http://localhost:8084/api/search',
      {
        query: query.toLowerCase(),
        distance,
        location,
        tagIds,
      },
    );
  }
  /* eslint-disable-next-line */
  getAuthenticationToken(username, password) {
    /* eslint-disable-next-line */
    console.log('Basic ' + btoa(username + ':' + password));
    return axios.post('http://localhost:8084/api/auth/token', { someKey: 'someValue' }, {
      headers: {
        /* eslint-disable-next-line */
        'Authorization': 'Basic ' + btoa(username + ':' + password),
      },
    });
  }
  /* eslint-disable-next-line */
  async createTag(tagName, username, password) {
    let token = '';
    await this.getAuthenticationToken(username, password)
      .then((response) => {
        token = response.data.token;
      });
    return axios.post('http://localhost:8084/api/createTag', { tag: tagName }, {
      headers: {
        /* eslint-disable-next-line */
        Authorization: 'Bearer ' + token,
      },
    });
  }
  /* eslint-disable-next-line */
  async createRestaurant(restaurant, username, password) {
    let token = '';
    const location = await this.getLatLonForAddress(
      /* eslint-disable-next-line */
      restaurant.street
      + ' ' + restaurant.houseNumber
      + ' ' + restaurant.postalCode
      + ' ' + restaurant.city
      + ' ' + restaurant.country,
    );
    const toCreateRestaurant = {
      name: restaurant.name,
      description: restaurant.description,
      imageUrl: restaurant.imageUrl,
      city: restaurant.city,
      houseNumber: restaurant.houseNumber,
      street: restaurant.street,
      country: restaurant.country,
      postalCode: restaurant.postalCode,
      phoneNumber: restaurant.phoneNumber,
      tagIds: restaurant.selectedTagIds,
      location,
    };
    await this.getAuthenticationToken(username, password)
      .then((response) => {
        token = response.data.token;
      });
    return axios.post('http://localhost:8084/api/create', toCreateRestaurant, {
      headers: {
        /* eslint-disable-next-line */
        Authorization: 'Bearer ' + token,
      },
    });
  }
  /* eslint-disable-next-line */
  getRestaurantById(id) {
    /* eslint-disable-next-line */
    return axios.get('http://localhost:8084/api/getRestaurantById?id=' + id);
  }
  /* eslint-disable-next-line */
  async deleteTagsById(tagIds, username, password){
    let token = '';
    await this.getAuthenticationToken(username, password)
      .then((response) => {
        token = response.data.token;
      });
    console.log(token);
    return axios.post('http://localhost:8084/api/deleteTagsById', { tagIds }, {
      headers: {
        // eslint-disable-next-line
        Authorization: 'Bearer ' + token,
      },
    });
  }
  // eslint-disable-next-line
  async updateRestaurant(restaurant, username, password) {
    let token = '';
    const location = await this.getLatLonForAddress(
      /* eslint-disable-next-line */
      restaurant.street
      + ' ' + restaurant.houseNumber
      + ' ' + restaurant.postalCode
      + ' ' + restaurant.city
      + ' ' + restaurant.country,
    );
    const toCreateRestaurant = {
      name: restaurant.name,
      description: restaurant.description,
      imageUrl: restaurant.imageUrl,
      city: restaurant.city,
      houseNumber: restaurant.houseNumber,
      street: restaurant.street,
      country: restaurant.country,
      postalCode: restaurant.postalCode,
      phoneNumber: restaurant.phoneNumber,
      tagIds: restaurant.selectedTagIds,
      location,
    };
    await this.getAuthenticationToken(username, password)
      .then((response) => {
        token = response.data.token;
      });
    /* eslint-disable-next-line */
    return axios.post('http://localhost:8084/api/update?id=' + restaurant.id, toCreateRestaurant, {
      headers: {
        /* eslint-disable-next-line */
        Authorization: 'Bearer ' + token,
      },
    });
  }
}

export default new BackendService();
