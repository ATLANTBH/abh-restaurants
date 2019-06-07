import Route from "@ember/routing/route";
import { hash } from "rsvp";
import { inject as service } from "@ember/service";

export default Route.extend({
  locationService: service("location-service"),

  model(params) {
    return hash({
      location: this.get("locationService").getCity(params.id)
    });
  }
});
