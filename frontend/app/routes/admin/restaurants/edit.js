import Route from "@ember/routing/route";
import { inject as service } from "@ember/service";
import { hash } from "rsvp";

export default Route.extend({
  restaurantService: service("restaurant-service"),
  cuisineService: service("cuisine-service"),

  model(params) {
    return hash({
      restaurant: this.get("restaurantService").getRestaurant(params.id),
      cuisines: this.get("cuisineService").fetchAllCuisines({
        page: 0,
        size: 100
      }),
      isEdit: true
    });
  },

  renderTemplate: function(controller, model) {
    model.restaurant.menu = JSON.parse(model.restaurant.menu);
    this.render("admin.restaurants.new", {
      model: model
    });
  }
});
