import Component from "@ember/component";
import { inject as service } from "@ember/service";

export default Component.extend({
  restaurantService: service("restaurant-service"),

  restaurantId: null,
  numberOfPeople: 0,
  time: null,

  hasError: false,
  error: null,

  inquiryResponse: null,

  init(...args) {
    this._super(...args);
    this._reservationInquiry();
  },

  _reservationInquiry() {
    const restaurantId = this.get("restaurantId");
    const numberOfPeople = this.get("numberOfPeople");
    const time = parseInt(this.get("time"), 10);

    const reservationRequest = {
      numberOfPeople,
      date: new Date(time)
    };

    this.get("restaurantService")
      .reservationInquiry(restaurantId, reservationRequest)
      .then(response => this.set("inquiryResponse", response))
      .catch(error => {
        this.set("hasError", true);
        this.set("errorMessage", error.errors[0].title);
      });
  },

  actions: {
    onReserve(selectedTime) {
      const restaurantId = this.get("restaurantId");
      const numberOfPeople = this.get("numberOfPeople");
      const time = this.get("time");

      this.get("onReserve")(restaurantId, numberOfPeople, time, selectedTime);
    }
  }
});
