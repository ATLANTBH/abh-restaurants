import Route from '@ember/routing/route';

export default Route.extend({
  ajax: Ember.inject.service(),
  model() {
    return Ember.RSVP.hash({
      locations: this.get('ajax').request('/getAllCities'),
    });
  },
});
