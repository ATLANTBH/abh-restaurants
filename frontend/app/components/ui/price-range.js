import Component from "@ember/component";
import { computed } from "@ember/object";

export default Component.extend({
  isEditable: false,

  /**
   * Action triggered when price is changed.
   */
  onPriceChange: null,

  classNameBindings: ["isEditable:pricernage--editable"],

  trueClass: "full",
  falseClass: "empty",

  isRangeOne: computed("priceRange", function() {
    return this.get("priceRange") >= 1
      ? this.get("trueClass")
      : this.get("falseClass");
  }),

  isRangeTwo: computed("priceRange", function() {
    return this.get("priceRange") >= 2
      ? this.get("trueClass")
      : this.get("falseClass");
  }),

  isRangeThree: computed("priceRange", function() {
    return this.get("priceRange") >= 3
      ? this.get("trueClass")
      : this.get("falseClass");
  }),

  isRangeFour: computed("priceRange", function() {
    return this.get("priceRange") >= 4
      ? this.get("trueClass")
      : this.get("falseClass");
  }),

  actions: {
    onPriceRange(price) {
      if (this.get("isEditable")) {
        this.get("onPriceChange")(price);
      }
    }
  }
});
