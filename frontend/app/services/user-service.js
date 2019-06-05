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
  }
});
