import Route from "@ember/routing/route";
import { inject as service } from "@ember/service";
import { hash } from "rsvp";

export default Route.extend({
  restaurantService: service("restaurant-service"),

  queryParams: {
    restaurantName: {
      refreshModel: true
    },
    numberOfPeople: {
      refreshModel: true
    },
    time: {
      refreshModel: true
    },
    page: {
      refreshModel: true
    }
  },

  model(params) {
    return hash({
      restaurants: this.get("restaurantService").findAllRestaurants({
        page: params.page,
        name: params.restaurantName
      })
    });
  }
});
