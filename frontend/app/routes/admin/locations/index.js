import Route from "@ember/routing/route";
import { inject as service } from "@ember/service";

export default Route.extend({
  locationService: service("location-service"),

  queryParams: {
    page: {
      refreshModel: true
    }
  },

  model(params) {
    return this.get("locationService").getAllCities({
      page: params.page,
      size: 15
    });
  }
});
