import Component from "@ember/component";

export default Component.extend({
  useLinks: true,

  firstName: null,
  lastName: null,
  email: null,
  password: null,
  address: null,
  phone: null,

  confirmPassword: null,

  hasError: false,
  errorMessage: "",

  onRegister: null,
  onLogin: null,

  /**
   * Validates a user request.
   */
  validateRequest(user) {
    const userProperties = [
      { propertyName: "firstName", friendlyName: "First name" },
      { propertyName: "lastName", friendlyName: "Last name" },
      { propertyName: "email", friendlyName: "Email" },
      { propertyName: "phone", friendlyName: "Phone" },
      { propertyName: "address", friendlyName: "Address" },
      { propertyName: "password", friendlyName: "Password" },
    ];

    for (let i = 0; i < userProperties.length; i++) {
      const { propertyName, friendlyName } = userProperties[i];

      const userPropertyValue = user[propertyName];
      if (!userPropertyValue || !userPropertyValue.trim()) {
        return {
          message: `${friendlyName} is required`
        };
      }
    }
  },

  actions: {
    onRegister() {
      const { password, confirmPassword } = this.getProperties(
        "password",
        "confirmPassword"
      );

      if (password !== confirmPassword) {
        this.setProperties({
          hasError: true,
          errorMessage: "Password fields do not match"
        });
        return;
      }

      const user = {
        firstName: this.get("firstName"),
        lastName: this.get("lastName"),
        email: this.get("email"),
        address: this.get("address"),
        phone: this.get("phone"),
        password
      };

      const validationResult = this.validateRequest(user);
      if (validationResult) {
        this.setProperties({
          hasError: true,
          errorMessage: validationResult.message
        });
        return;
      }

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
