import BaseService from "./base-service";

export default BaseService.extend({
  getReservation(id) {
    return this.get(`/api/v1/reservation/${id}`);
  },

  confirmReservation(id) {
    return this.put(`/api/v1/reservation/${id}/confirm`);
  }
});
