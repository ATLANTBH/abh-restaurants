import Route from '@ember/routing/route';
import { hash } from "rsvp";

export default Route.extend({
  ajax: Ember.inject.service(),
  model(params) {
    return hash({
      user: this.get('ajax').request('/admin/getUser/' + params.user_id, {
        xhrFields: {
          withCredentials: true,
        },
      }),
    });
  },
});
