import Route from "@ember/routing/route";
import { hash } from "rsvp";
import { inject as service } from "@ember/service";

export default Route.extend({
  userService: service("user-service"),

  model(params) {
    return hash({
      user: this.get("userService").getUser(params.id)
    });
  }
});
