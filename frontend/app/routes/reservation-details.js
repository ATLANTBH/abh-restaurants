import Route from "@ember/routing/route";
import { inject as service } from "@ember/service";
import { hash } from "rsvp";

export default Route.extend({
  reservationService: service("reservation-service"),

  model(params) {
    return hash({
      reservation: this.get("reservationService").getReservation(params.id)
    });
  }
});
