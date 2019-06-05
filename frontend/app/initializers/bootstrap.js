import request from "ember-ajax/request";

function bootstrap() {
  return request("/api/v1/bootstrap");
}

export function initialize(application) {
  application.deferReadiness();

  function advanceReadiness() {
    application.advanceReadiness();
  }

  bootstrap()
    .then(result => {
      application.register("bootstrap:main", result, { instantiate: false });
      application.register("currentUser:main", result.currentUser, {
        instantiate: false
      });

      application.inject("controller", "bootstrap", "bootstrap:main");
      application.inject("controller", "currentUser", "currentUser:main");

      application.inject("component", "bootstrap", "bootstrap:main");
      application.inject("component", "currentUser", "currentUser:main");

      application.inject("route", "bootstrap", "bootstrap:main");
      application.inject("route", "currentUser", "currentUser:main");

      advanceReadiness();
    })
    .catch(err => {
      console.error(err);
      advanceReadiness();
    });
}

export default {
  initialize
};
