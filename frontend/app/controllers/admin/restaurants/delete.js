import Controller from "@ember/controller";
import { inject as service } from "@ember/service";

export default Controller.extend({
  restaurantService: service("restaurant-service"),

  actions: {
    onDeleteRestaurant() {
      this.get("restaurantService")
        .deleteRestaurant(this.get("model.restaurant.id"))
        .then(() => this.transitionToRoute("admin.restaurants"));
    }
  }
});
