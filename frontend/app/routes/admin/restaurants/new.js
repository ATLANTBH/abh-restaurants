import Route from "@ember/routing/route";
import { hash } from "rsvp";
import { inject as service } from "@ember/service";

export default Route.extend({
  cuisineService: service("cuisine-service"),

  model() {
    return hash({
      restaurant: {},
      cuisines: this.get("cuisineService").fetchAllCuisines({
        page: 0,
        size: 100
      })
    });
  }
});
