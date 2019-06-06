import Route from '@ember/routing/route';
import { hash } from "rsvp";

export default Route.extend({
  ajax: Ember.inject.service(),
  model(params) {
    return hash({
      cuisine: this.get('ajax').request('/getCuisine/' + params.cuisine_id),
      isEdit: true,
    });
  },

  renderTemplate: function (controller, model) {
    this.render('admin.cuisines.new', {
      model: model,
    });
  },
});
