import Controller from "@ember/controller";
import { inject as service } from "@ember/service";

export default Controller.extend({
  restaurantService: service("restaurant-service"),

  queryParams: {
    restaurantName: "name",
    numberOfPeople: "people",
    time: "time",
    page: "page"
  },
  restaurantName: "",
  numberOfPeople: 0,
  page: 0,
  time: null,

  actions: {
    onReserve(restaurantId, numberOfPeople, time) {
      const reservationRequest = {
        numberOfPeople,
        date: new Date(time)
      };

      this.get("restaurantService")
        .createReservation(restaurantId, reservationRequest)
        .then(response =>
          this.transitionToRoute("reservation-details", response.id)
        );
    }
  }
});
