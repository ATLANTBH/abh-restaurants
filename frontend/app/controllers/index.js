import Controller from "@ember/controller";
import { formatTime, formatDate, toDate } from "../utils/datetime";

const TODAY = new Date();

export default Controller.extend({
  restaurant_name: "",
  number_of_people: 2,

  time: formatTime(TODAY),
  date: formatDate(TODAY),

  actions: {
    onFindTable() {
      const filters = {
        page: 0,
        name: this.get("restaurant_name"),
        people: this.get("number_of_people"),
        time: toDate(this.get("date"), this.get("time")).getTime()
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
