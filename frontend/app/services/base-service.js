import AjaxService from "ember-ajax/services/ajax";

export default AjaxService.extend({
  post(path, data, queryParams) {
    return this.request(path, {
      method: "POST",
      contentType: "application/json",
      data: JSON.stringify(data)
    });
  },

  get(path, queryParams) {
    return this.request(path, {
      method: "GET",
      data: queryParams
    });
  }
});
