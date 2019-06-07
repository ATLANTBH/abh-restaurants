import Route from "@ember/routing/route";
import { inject as service } from "@ember/service";
import { hash } from "rsvp";

export default Route.extend({
  restaurantService: service("restaurant-service"),

  queryParams: {
    page: {
      refreshModel: true
    }
  },

  model(params) {
    return hash({
      restaurants: this.get("restaurantService").findAllRestaurants({
        page: params.page,
        size: 15
      })
    });
  }
});
