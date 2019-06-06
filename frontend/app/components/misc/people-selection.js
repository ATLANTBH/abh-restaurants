import Component from "@ember/component";

export default Component.extend({
  onChange: null,

  tagName: "select",

  change(ev) {
    const numberOfPeople = parseInt(ev.target.value);
    this.onChange(numberOfPeople);
  }
});
