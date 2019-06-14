import Component from "@ember/component";
import { computed } from "@ember/object";

const TRUE_CLASS = "full";
const FALSE_CLASS = "empty";

function getRangeClass(priceRange, threshold) {
  return priceRange >= threshold ? TRUE_CLASS : FALSE_CLASS;
}

export default Component.extend({
  priceRange: null,
  onPriceRangeChanged: null,

  isRangeOne: computed("priceRange", function() {
    return getRangeClass(this.get("priceRange"), 1);
  }),

  isRangeTwo: computed("priceRange", function() {
    return getRangeClass(this.get("priceRange"), 2);
  }),

  isRangeThree: computed("priceRange", function() {
    return getRangeClass(this.get("priceRange"), 3);
  }),

  isRangeFour: computed("priceRange", function() {
    return getRangeClass(this.get("priceRange"), 4);
  }),

  actions: {
    onPriceRangeChanged(value) {
      this.get("onPriceRangeChanged")(value);
    }
  }
});
