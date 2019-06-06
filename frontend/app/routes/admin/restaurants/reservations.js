import Route from '@ember/routing/route';
import { inject as service } from "@ember/service";
import { hash } from "rsvp";

export default Route.extend({
  restaurantService: service("restaurant-service"),

  model(params) {
    return hash({
      restaurant: this.get('ajax').request('/getRestaurant/' + params.restaurant_id),
    });
  },

  afterModel(model, transition) {
    transition.then(function () {
      this.controller.send('dateChanged');
    }.bind(this));
  },
});
