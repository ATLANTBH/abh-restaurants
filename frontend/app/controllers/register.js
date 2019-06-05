import Controller from "@ember/controller";
import { inject as service } from "@ember/service";

export default Controller.extend({
  firstName: null,
  lastName: null,
  email: null,
  password: null,
  address: null,
  phone: null,

  hasError: false,
  errorMessage: "",

  userService: service("user-service"),

  actions: {
    onRegister() {
      const userRequest = {
        firstName: this.get("first_name"),
        lastName: this.get("last_name"),
        email: this.get("email"),
        password: this.get("password"),
        address: this.get("address"),
        phone: this.get("phone_number")
      };

      this.get("userService")
        .register(userRequest)
        .then(() => this.transitionToRoute("index"))
        .catch(error => {
          this.set("hasError", true);
          this.set("errorMessage", error.payload.message);
        });
    }
  }
});
