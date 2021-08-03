import Component from "@ember/component";
import { inject as service } from "@ember/service";
import { computed } from "@ember/object";

const { alias, not } = computed;

export default Component.extend({
  reservationService: service("reservation-service"),

  actions: {
    onCancelReservation: function(id) {
      this.get("reservationService")
        .cancelReservation(id)
        .then(() => window.location.reload())
        .catch(error => alert(error));
    }
  }
});
