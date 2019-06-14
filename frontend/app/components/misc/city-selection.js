import Component from "@ember/component";
import { inject as service } from "@ember/service";

export default Component.extend({
  onChange: null,
  cities: null,

  cityService: service("city-service"),

  init(...args) {
    this._super(...args);

    this.get("cityService")
      .fetchAllCities()
      .then(cities => this.set("cities", cities.content));
  },

  actions: {
    onSelectCity(strId) {
      const id = parseInt(strId);
      const cities = this.get("cities");

      for (let i = 0; i < cities.length; i++) {
        if (cities[i].id === id) {
          this.onChange(cities[i]);
          break;
        }
      }
    }
  }
});
