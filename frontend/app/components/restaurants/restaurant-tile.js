import Component from "@ember/component";
import { computed } from "@ember/object";
import { htmlSafe } from "@ember/template";

export default Component.extend({
  tileStyle: computed("data.profileImagePath", function() {
    return htmlSafe(
      "background-image: url(" +
        this.get("data.profileImagePath") +
        "), url('/images/restaurants/thumbnails/placeholder.png')"
    );
  })
});
