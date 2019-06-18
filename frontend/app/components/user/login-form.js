import Component from "@ember/component";

export default Component.extend({
  useLinks: true,
  onLogin: null,
  onCreateAccount: null,

  email: null,
  password: null,

  hasError: null,
  errorMessage: null,

  actions: {
    onLogin() {
      const promiseResult = this.get("onLogin")(
        this.get("email"),
        this.get("password")
      );
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

    onCreateAccount() {
      this.get("onCreateAccount")();
    }
  }
});
