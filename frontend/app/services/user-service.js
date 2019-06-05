import BaseService from "./base-service";

export default BaseService.extend({
  login(email, password) {
    return this.post("/api/v1/login", { email, password });
  },

  register(request) {
    return this.post("/api/v1/users", request);
  }
});
