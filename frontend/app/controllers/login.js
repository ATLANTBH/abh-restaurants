import Controller from "@ember/controller";
import { inject as service } from "@ember/service";

export default Controller.extend({
  userService: service("user-service"),

  actions: {
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
    }
  }
});
