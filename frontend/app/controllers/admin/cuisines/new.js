import Controller from "@ember/controller";
import { inject as service } from "@ember/service";

export default Controller.extend({
  cuisineService: service("cuisine-service"),

  actions: {
    onSubmitCuisine() {
      const id = this.get("model.cuisine.id");
      const isCreating = typeof id === "undefined";

      debugger;

      let promise;

      if (isCreating) {
        promise = this.get("cuisineService").create(this.get("model.cuisine"));
      } else {
        promise = this.get("cuisineService").update(
          id,
          this.get("model.cuisine")
        );
      }

      promise.then(() => this.transitionToRoute("admin.cuisines"));
    }
  }
});
