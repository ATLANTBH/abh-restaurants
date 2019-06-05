import Component from "@ember/component";
import { inject as service } from "@ember/service";

export default Component.extend({
  geolocation: navigator.geolocation,
  nearbyRestaurants: null,

  restaurantService: service("restaurant-service"),

  init(...args) {
    this._super(...args);
    this.fetchNearbyRestaurants();
  },

  /**
   * Fetches the near-by restaurants.
   */
  fetchNearbyRestaurants() {
    if (this.get("geolocation")) {
      this.get("restaurantService")
        .fetchNearByRestaurants(43.8563, 18.4131)
        .then(result => {
          this.set("geolocation", true);
          this.set("nearbyRestaurants", result);
        });
    }
  }
});
