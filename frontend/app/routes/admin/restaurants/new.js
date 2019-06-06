import Route from '@ember/routing/route';
import { inject as service } from "@ember/service";
import { hash } from "rsvp";

export default Route.extend({
  restaurantService: service("restaurant-service"),

  model() {
    return hash({
      restaurant: {},
      cities: this.get('ajax').request('/getAllCities'),
      cuisines: this.get('ajax').request('/getAllCuisines'),
    });
  },

  afterModel(model) {
    model.restaurant.city = model.cities[0];
    model.restaurant.priceRange = 1;
    model.restaurant.cuisines = [];
    model.restaurant.tables = [];
  },
});
