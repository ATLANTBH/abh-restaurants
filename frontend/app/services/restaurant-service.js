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
  },

  findAllRestaurants(request) {
    return this.get("/api/v1/restaurant", request);
  },

  getRestaurant(id) {
    return this.get(`/api/v1/restaurant/${id}`);
  },

  reservationInquiry(id, request) {
    return this.post(`/api/v1/restaurant/${id}/reservation-inquiry`, request);
  },

  createReservation(id, request) {
    return this.post(`/api/v1/restaurant/${id}/reservation`, request);
  },

  deleteRestaurant(id) {
    return this.delete(`/api/v1/restaurant/${id}`);
  },

  create(request) {
    return this.post(`/api/v1/restaurant`, request);
  },

  update(id, restaurant) {
    return this.put(`/api/v1/restaurant/${id}`, restaurant);
  }
});
