import Route from "@ember/routing/route";
import { hash } from "rsvp";
import { inject as service } from "@ember/service";

export default Route.extend({
  cuisineService: service("cuisine-service"),

  model(params) {
    return hash({
      cuisine: this.get("cuisineService").getCuisine(params.id)
    });
  }
});
