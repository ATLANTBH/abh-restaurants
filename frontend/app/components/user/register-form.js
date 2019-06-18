import Component from "@ember/component";

export default Component.extend({
  useLinks: true,

  firstName: null,
  lastName: null,
  email: null,
  password: null,
  address: null,
  phone: null,

  hasError: false,
  errorMessage: "",

  onRegister: null,
  onLogin: null,

  actions: {
    onRegister() {
      const user = {
        firstName: this.get("firstName"),
        lastName: this.get("lastName"),
        email: this.get("email"),
        password: this.get("password"),
        address: this.get("address"),
        phone: this.get("phone")
      };

      const promiseResult = this.get("onRegister")(user);
      if (promiseResult) {
        promiseResult.then(result => {
          if (result && result.hasError) {
            this.set("hasError", result.hasError);
            this.set("errorMessage", result.errorMessage);
          } else {
            this.set("hasError", false);
            this.set("errorMessage", null);
          }
        });
      }
    },

    onLogin() {
      this.get("onLogin")();
    }
  }
});
