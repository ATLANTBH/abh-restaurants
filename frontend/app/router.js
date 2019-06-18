import EmberRouter from "@ember/routing/router";
import config from "./config/environment";

const Router = EmberRouter.extend({
  location: config.locationType,
  rootURL: config.rootURL
});

Router.map(function() {
  this.route("login");
  this.route("register");
  this.route("restaurants", function() {});
  this.route("user", function() {});

  this.route("restaurant", { path: "restaurant/:id" });

  this.route("bad-request", { path: "*path" });

  this.route("search-results");

  this.route("admin", function() {
    this.route("restaurants", function() {
      this.route("new");
      this.route("edit", { path: "edit/:id" });
      this.route("delete", { path: "delete/:id" });
      this.route("reservations", { path: "reservations/:id" });
    });

    this.route("locations", function() {
      this.route("new");
      this.route("edit", { path: "edit/:id" });
      this.route("delete", { path: "delete/:id" });
    });

    this.route("users", function() {
      this.route("new");
      this.route("edit", { path: "edit/:id" });
      this.route("delete", { path: "delete/:id" });
    });

    this.route("cuisines", function() {
      this.route("new");
      this.route("edit", { path: "edit/:id" });
      this.route("delete", { path: "delete/:id" });
    });

    this.route("settings");
  });

  this.route("reservation-details", {
    path: "reservation-details/:id"
  });
});

export default Router;
