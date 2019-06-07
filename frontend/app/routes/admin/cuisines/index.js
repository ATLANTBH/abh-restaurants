import Route from "@ember/routing/route";
import { hash } from "rsvp";
import { inject as service } from "@ember/service";

export default Route.extend({
  cuisineService: service("cuisine-service"),

  queryParams: {
    page: {
      refreshModel: true
    }
  },

  model(params) {
    return hash({
      cuisines: this.get("cuisineService").fetchAllCuisines({
        page: params.page,
        size: 15
      })
    });
  }
});
