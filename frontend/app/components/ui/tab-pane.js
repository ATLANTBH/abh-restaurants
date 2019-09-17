import Component from "@ember/component";

export default Component.extend({
  /**
   * List of items with title and id.
   */
  items: null,

  activeItem: null,

  actions: {
    /**
     * Sets an active item.
     *
     * @param {Object} item Item.
     */
    onSetActiveItem(item) {
      this.set("activeItem", item);
    }
  }
});
