import Controller from "@ember/controller";
import { inject as service } from "@ember/service";

export default Controller.extend({
  email: null,
  password: null,

  hasError: false,
  errorMessage: "",

  userService: service("user-service"),

  actions: {
    onLogin() {
      this.get("userService")
        .login(this.get("email"), this.get("password"))
        .then(user => this.transitionToRoute(user.isAdmin ? "admin" : "index"))
        .catch(error => {
          this.set("hasError", true);
          this.set("errorMessage", error.payload.message);
        });
    }
  }
});
