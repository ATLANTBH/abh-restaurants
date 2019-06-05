import BaseService from "./base-service";

export default BaseService.extend({
  fetchNearByRestaurants(lat, lng) {
    return this.get(`/api/v1/restaurant/near-by/${lat}/${lng}`);
  },

  fetchPopularRestaurants() {
    return this.get("/api/v1/restaurant/popular");
  },

  fetchPopularLocations() {
    return this.get("/api/v1/restaurant/popular-locations");
  }
});
