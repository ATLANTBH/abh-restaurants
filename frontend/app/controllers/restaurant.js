import Controller from "@ember/controller";
import { inject as service } from "@ember/service";
import { computed } from "@ember/object";
import { formatTime, toDate } from "../utils/datetime";

const { alias } = computed;

const TAB_PANE_ITEMS = [{
  id: 'BREAKFAST',
  title: 'Breakfast'
}, {
  id: 'LUNCH',
  title: 'Lunch'
}, {
  id: 'DINNER',
  title: 'Dinner'
}];

export default Controller.extend({
  restaurantService: service("restaurant-service"),

  date: null,
  time: null,

  restaurantId: alias("model.restaurant.id"),
  numberOfPeople: 1,

  inquiryResponse: null,

  tabPaneItems: TAB_PANE_ITEMS,

  menu: computed("model.restaurant.menu", function() {
    return JSON.parse(this.get("model.restaurant.menu"));
  }),

  breakfastMenu: alias("menu.breakfast"),
  lunchMenu: alias("menu.lunch"),
  dinnerMenu: alias("menu.dinner"),

  openTime: computed("model.restaurant.openTime", function() {
    return formatTime(this.get("model.restaurant.openTime"));
  }),

  closeTime: computed("model.restaurant.closeTime", function() {
    return formatTime(this.get("model.restaurant.closeTime"));
  }),

  actions: {
    onPeopleSelection(numberOfPeople) {
      this.set("numberOfPeople", numberOfPeople);
    },

    onFindTable() {
      const reservationRequest = {
        numberOfPeople: this.get("numberOfPeople"),
        date: toDate(this.get("date"), this.get("time"))
      };

      this.get("restaurantService")
        .reservationInquiry(this.get("restaurantId"), reservationRequest)
        .then(response => this.set("inquiryResponse", response));
    },

    onReserve(time) {
      const reservationRequest = {
        numberOfPeople: this.get("numberOfPeople"),
        date: new Date(time)
      };

      this.get("restaurantService")
        .createReservation(this.get("restaurantId"), reservationRequest)
        .then(response =>
          this.transitionToRoute("reservation-details", response.id)
        );
    }
  }
});
