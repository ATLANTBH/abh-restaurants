import Component from "@ember/component";
import { computed } from "@ember/object";
import { htmlSafe } from "@ember/template";

export default Component.extend({
  type: null,
  coverImage: null,

  navigationStyle: computed("coverImage", function() {
    const url = this.get("coverImage") || "/assets/images/home-header.jpg";
    return htmlSafe(`background-image: url(${url})`);
  })
});
