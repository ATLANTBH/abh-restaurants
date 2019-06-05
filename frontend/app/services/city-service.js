import BaseService from "./base-service";

export default BaseService.extend({
  fetchAllCities() {
    return this.get("/api/v1/city");
  }
});
