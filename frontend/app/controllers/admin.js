import Controller from "@ember/controller";
import { inject as service } from "@ember/service";

export default Controller.extend({
  userService: service("user-service"),

  actions: {
    onLogout() {
      this.get("userService")
        .logout()
        .then(() => window.location.reload());
    }
  }
});
