import Controller from "@ember/controller";
import { inject as service } from "@ember/service";

export default Controller.extend({
  restaurantService: service("restaurant-service"),

  actions: {
    onCitySelected(city) {
      debugger;
    },

    onUploadedImage() {
      debugger;
    }
  }
});
