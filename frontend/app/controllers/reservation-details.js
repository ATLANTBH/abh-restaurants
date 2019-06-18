import Controller from "@ember/controller";
import { inject as service } from "@ember/service";
import { computed } from "@ember/object";

const { alias, not } = computed;

export default Controller.extend({
  reservationService: service("reservation-service"),
  userService: service("user-service"),

  restaurant: alias("model.reservation.restaurant"),
  reservedOn: alias("model.reservation.reservedOn"),
  reservationConfirmed: alias("model.reservation.confirmed"),

  countdownStart: computed(function() {
    return this.get("reservedOn") + 300000;
  }),
  countdownEnd: computed(function() {
    return this.get("reservedOn");
  }),

  expirationCounter: computed("countdownStart", "countdownEnd", function() {
    return new Date(
      new Date(this.get("countdownStart")).getTime() -
        new Date(this.get("countdownEnd")).getTime()
    ).getTime();
  }),

  hasExpired: computed("expirationCounter", "reservationConfirmed", function() {
    return this.get("reservationConfirmed")
      ? false
      : this.get("expirationCounter") <= 0;
  }),

  isNotLoggedIn: not("currentUser"),
  showRegistrationForm: true,

  actions: {
    onRegister({ firstName, lastName, email, password, address, phone }) {
      const userRequest = {
        firstName,
        lastName,
        email,
        password,
        address,
        phone
      };

      return this.get("userService")
        .register(userRequest)
        .then(() => window.location.reload())
        .catch(error => {
          return {
            hasError: true,
            errorMessage: error.payload.message
          };
        });
    },

    onLogin(email, password) {
      return this.get("userService")
        .login(email, password)
        .then(() => window.location.reload())
        .catch(error => {
          return {
            hasError: true,
            errorMessage: error.payload.message
          };
        });
    },

    onSwitchToLoginView() {
      this.set("showRegistrationForm", false);
    },

    onSwitchToRegisterView() {
      this.set("showRegistrationForm", true);
    },

    onConfirmReservation() {
      const id = this.get("model.reservation.id");

      this.get("reservationService")
        .confirmReservation(id)
        .then(() => this.transitionToRoute("user"))
        .catch(error => alert(error));
    }
  }
});
