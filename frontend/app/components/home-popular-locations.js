import Component from "@ember/component";
import { inject as service } from "@ember/service";

export default Component.extend({
  locations: null,

  restaurantService: service("restaurant-service"),

  init(...args) {
    this._super(...args);
    this.fetchPopularLocations();
  },

  /**
   * Fetches the near-by restaurants.
   */
  fetchPopularLocations() {
    this.get("restaurantService")
      .fetchPopularLocations()
      .then(result => this.set("locations", result));
  }
});
