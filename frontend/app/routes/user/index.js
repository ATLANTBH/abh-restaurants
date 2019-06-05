import Route from "@ember/routing/route";
import { inject as service } from "@ember/service";
import { hash } from "rsvp";

export default Route.extend({
  userService: service("user-service"),

  model() {
    return hash({
      reservations: this.get("userService").findMyReservations()
    });
  }
});
