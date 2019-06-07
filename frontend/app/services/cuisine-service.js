import BaseService from "./base-service";

export default BaseService.extend({
  fetchAllCuisines(filter) {
    return this.get("/api/v1/cuisine", filter);
  },

  getCuisine(id) {
    return this.get(`/api/v1/cuisine/${id}`);
  },

  create(request) {
    return this.post("/api/v1/cuisine", request);
  },

  update(id, request) {
    return this.put(`/api/v1/cuisine/${id}`, request);
  },

  delete(id) {
    return this.delete(`/api/v1/cuisine/${id}`);
  }
});
