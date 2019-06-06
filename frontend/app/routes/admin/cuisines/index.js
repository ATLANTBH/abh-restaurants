import Route from '@ember/routing/route';
import { hash } from "rsvp";

export default Route.extend({
  ajax: Ember.inject.service(),
  model() {
    return hash({
      cuisines: this.get('ajax').request('/getAllCuisines'),
    });
  },
});
