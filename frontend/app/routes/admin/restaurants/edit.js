import Route from '@ember/routing/route';
import { inject as service } from "@ember/service";
import { hash } from "rsvp";

export default Route.extend({
  restaurantService: service("restaurant-service"),

  model(params) {
    return hash({
      restaurant: this.get('ajax').request('/getRestaurant/' + params.restaurant_id),
      cities: this.get('ajax').request('/getAllCities'),
      cuisines: this.get('ajax').request('/getAllCuisines'),
      isEdit: true,
    });
  },

  renderTemplate: function (controller, model) {
    model.restaurant.menu = JSON.parse(model.restaurant.menu);
    this.render('admin.restaurants.new', {
        model: model,
      });
  },
});
