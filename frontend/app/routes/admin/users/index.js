import Route from '@ember/routing/route';
import { hash } from "rsvp";

export default Route.extend({
  ajax: Ember.inject.service(),
  model() {
    return hash({
      users: this.get('ajax').request('/admin/getAllUsers', {
        xhrFields: {
          withCredentials: true,
        },
      }),
    });
  },
});
