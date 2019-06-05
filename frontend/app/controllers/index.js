import Controller from "@ember/controller";

export default Controller.extend({
  restaurant_name: "",
  number_of_people: 2,

  time: "17:30",
  date: new Date().toISOString().substring(0, 10),

  actions: {
    findTable() {
      let filters = {
        name: this.get("restaurant_name"),
        people: this.get("number_of_people"),
        date: this.get("date"),
        time: this.get("time")
      };
      this.transitionToRoute("search-results", { queryParams: filters });
    },

    setNumberOfPeople() {
      let selectBox = document.getElementById("numberOfPeople");
      this.set(
        "number_of_people",
        selectBox.options[selectBox.selectedIndex].value
      );
    }
  }
});
