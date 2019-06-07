import BaseService from "./base-service";

export default BaseService.extend({
  login(email, password) {
    return this.post("/api/v1/login", { email, password });
  },

  logout() {
    return this.post("/api/v1/logout");
  },

  register(request) {
    return this.post("/api/v1/users", request);
  },

  findMyReservations() {
    return this.get("/api/v1/reservation/my");
  },

  filter(params) {
    return this.get(`/api/v1/users`, params);
  },

  getUser(id) {
    return this.get(`/api/v1/users/${id}`);
  },

  editUser(id, request) {
    return this.put(`/api/v1/users/${id}`, request);
  },

  delete(id) {
    return this.delete(`/api/v1/users/${id}`);
  }
});
