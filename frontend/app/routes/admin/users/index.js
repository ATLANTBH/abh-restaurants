import Route from "@ember/routing/route";
import { inject as service } from "@ember/service";

export default Route.extend({
  userService: service("user-service"),

  model(params) {
    return this.get("userService").filter({ page: params.page, size: 15 });
  }
});
