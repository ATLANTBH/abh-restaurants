import Route from '@ember/routing/route';
import { hash } from "rsvp";

export default Route.extend({
  ajax: Ember.inject.service(),
  model(params) {
    return hash({
      location: this.get('ajax').request('/getCity/' + params.location_id),
    });
  },
});
