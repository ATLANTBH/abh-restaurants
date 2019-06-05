import Component from "@ember/component";
import { computed } from "@ember/object";
import { inject as service } from "@ember/service";

const { notEmpty } = computed;

export default Component.extend({
  popularRestaurants: null,
  hasPopularRestaurants: notEmpty("popularRestaurants"),

  restaurantService: service("restaurant-service"),

  init(...args) {
    this._super(...args);
    this.fetchPopularRestaurants();
  },

  /**
   * Fetches the near-by restaurants.
   */
  fetchPopularRestaurants() {
    this.get("restaurantService")
      .fetchPopularRestaurants()
      .then(result => this.set("popularRestaurants", result));
  }
});
