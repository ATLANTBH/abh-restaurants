import Component from "@ember/component";
import { computed } from "@ember/object";
import { htmlSafe } from "@ember/template";

export default Component.extend({
  type: null,
  coverImage: null,

  navigationStyle: computed("coverImage", function() {
    return htmlSafe("background-image: url(" + this.get("coverImage") + ")");
  })
});
