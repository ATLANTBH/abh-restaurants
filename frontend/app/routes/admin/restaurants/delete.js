import Route from "@ember/routing/route";
import { inject as service } from "@ember/service";
import { hash } from "rsvp";

export default Route.extend({
  restaurantService: service("restaurant-service"),

  model(params) {
    return hash({
      restaurant: this.get("restaurantService").getRestaurant(params.id)
    });
  }
});
