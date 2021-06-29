import Controller from "@ember/controller";
import { computed } from "@ember/object";
import { inject as service } from "@ember/service";
import { htmlSafe } from "@ember/template";
import { timeStringToDate, formatTime, lessThanOneHour } from "../../../utils/datetime";

const { alias } = computed;

export default Controller.extend({
  restaurantService: service("restaurant-service"),

  /**
   * Progress of an image-upload process.
   */
  profileImageUploadProgress: null,
  coverImageUploadProgress: null,

  restaurant: alias("model.restaurant"),

  profileImageStyle: computed("restaurant.profileImagePath", function() {
    return htmlSafe(
      "background-image: url(" + this.get("restaurant.profileImagePath") + ")"
    );
  }),

  coverImageStyle: computed("restaurant.coverImagePath", function() {
    return htmlSafe(
      "background-image: url(" + this.get("restaurant.coverImagePath") + ")"
    );
  }),

  cuisines: computed("model.cuisines", function() {
    return this.get("model.cuisines.content").map(cuisine => cuisine.name);
  }),

  selectedCuisines: computed("model.restaurant.cuisines", function() {
    return (this.get("model.restaurant.cuisines") || []).map(
      cuisine => cuisine.name
    );
  }),

  openTime: computed("model.restaurant.openTime", {
    get() {
      const time = this.get("model.restaurant.openTime");
      if (time) {
        return formatTime(time);
      }
      return "";
    },
    set(property, value) {
      this.set("model.restaurant.openTime", timeStringToDate(value));
      return value;
    }
  }),

  closeTime: computed("model.restaurant.closeTime", {
    get() {
      const time = this.get("model.restaurant.closeTime");
      if (time) {
        return formatTime(time);
      }
      return "";
    },
    set(property, value) {
      this.set("model.restaurant.closeTime", timeStringToDate(value));
      return value;
    }
  }),

  actions: {
    onCitySelected(city) {
      this.set("model.restaurant.cityId", city.id);
    },

    onCoverImageUploaded(path) {
      this.set("model.restaurant.coverImagePath", path);
    },

    onProfileImageUploaded(path) {
      this.set("model.restaurant.profileImagePath", path);
    },

    onPriceRangeChanged(value) {
      this.set("restaurant.priceRange", value);
    },

    onAddTable() {
      let tables = this.get("model.restaurant.tables");
      if (!tables) {
        tables = [];
        this.set("model.restaurant.tables", tables);
      }

      tables.pushObject({
        id: null,
        restaurantId: this.get("model.restaurant.id"),
        numberOfChairs: 0
      });
    },

    onRemoveTable(tableId) {
      this.get("model.restaurant.tables").removeObject(
        this.get("model.restaurant.tables").findBy("id", tableId)
      );
    },

    onAddMenuBreakfast() {
      let menu = this.get("model.restaurant.menu.breakfast");
      if (typeof menu === "undefined") {
        this.set("model.restaurant.menu", {});
        this.set("model.restaurant.menu.breakfast", []);
        this.set("model.restaurant.menu.lunch", []);
        this.set("model.restaurant.menu.dinner", []);
      }

      this.get("model.restaurant.menu.breakfast").pushObject({
        id: null,
        name: "",
        description: "",
        price: 0
      });
    },

    onAddMenuLunch() {
      let menu = this.get("model.restaurant.menu.lunch");
      if (typeof menu === "undefined") {
        this.set("model.restaurant.menu", {});
        this.set("model.restaurant.menu.breakfast", []);
        this.set("model.restaurant.menu.lunch", []);
        this.set("model.restaurant.menu.dinner", []);
      }

      this.get("model.restaurant.menu.lunch").pushObject({
        id: null,
        name: "",
        description: "",
        price: 0
      });
    },

    onAddMenuDinner() {
      let menu = this.get("model.restaurant.menu.dinner");
      if (typeof menu === "undefined") {
        this.set("model.restaurant.menu", {});
        this.set("model.restaurant.menu.breakfast", []);
        this.set("model.restaurant.menu.lunch", []);
        this.set("model.restaurant.menu.dinner", []);
      }

      this.get("model.restaurant.menu.dinner").pushObject({
        id: null,
        name: "",
        description: "",
        price: 0
      });
    },

    onRemoveBreakfastItem(item) {
      this.get("model.restaurant.menu.breakfast").removeObject(item);
    },

    onRemoveLunchItem(item) {
      this.get("model.restaurant.menu.lunch").removeObject(item);
    },

    onRemoveDinnerItem(item) {
      this.get("model.restaurant.menu.dinner").removeObject(item);
    },

    onSubmitRestaurant() {
      if (this.get("marker")) {
        this.set(
          "model.restaurant.latitude",
          32424
          // this.get("marker")
          //   .getPosition()
          //   .lat()
        );
        this.set(
          "model.restaurant.longitude",
          4324324
          // this.get("marker")
          //   .getPosition()
          //   .lng()
        );
      }

      // if (
      //   this.get("marker").getPosition() === this.get("defaultMerkerPosition")
      // ) {
      //   return alert("Please Move the Map Marker to the correct position");
      // }

      // let markerLatLng = new google.maps.LatLng(
      //   this.get("model.restaurant.latitude"),
      //   this.get("model.restaurant.longitude")
      // );
      // if (
      //   !google.maps.geometry.poly.containsLocation(
      //     markerLatLng,
      //     this.get("polygon")
      //   )
      // ) {
      //   return alert(
      //     "Marker out of Bounds. Please position the marker within the selected City."
      //   );
      // }

      // if (
      //   this.get("uploadProgressProfile") !== null ||
      //   this.get("uploadProgressCover") !== null
      // ) {
      //   return alert("Please wait for upload to finish");
      // }

      if (!this.get("model.restaurant.priceRange")) {
        return alert("Please select a correct price range.");
      }

      if (!this.get("model.restaurant.cityId")) {
        return alert("Please select a city.");
      }

      if(!this.get("model.restaurant.coverImagePath")) {
        return alert("Please upload a cover photo.");
      }

      if(!this.get("model.restaurant.profileImagePath")) {
        return alert("Please upload a profile photo.");
      }

      if(!this.get("model.restaurant.openTime")) {
        return alert("Please add an opening time");
      }

      if(!this.get("model.restaurant.closeTime")) {
        return alert("Please add a closing time");
      }

      const openTimeDate = new Date(this.get("model.restaurant.openTime"));
      const closeTimeDate = new Date(this.get("model.restaurant.closeTime"));

      if(lessThanOneHour(openTimeDate, closeTimeDate)) {
        return alert("The restaurant must be open for at least 1 hour");
      }

      this.set(
        "model.restaurant.menu",
        JSON.stringify(this.get("model.restaurant.menu")) || "{}"
      );

      const cuisineCache = this.get("model.cuisines.content").reduce(
        (memo, cuisine) => {
          memo[cuisine.name] = cuisine;
          return memo;
        },
        {}
      );
      let selectedCuisines = this.get("selectedCuisines");
      selectedCuisines = selectedCuisines.map(
        cuisineName => cuisineCache[cuisineName]
      );

      this.set("model.restaurant.cuisines", selectedCuisines);

      const id = this.get("model.restaurant.id");
      let isCreating = typeof id === "undefined";

      if (isCreating) {
        this.get("restaurantService")
          .create(this.get("model.restaurant"))
          .then(() => this.transitionToRoute("admin.restaurants"));
      } else {
        this.get("restaurantService")
          .update(id, this.get("model.restaurant"))
          .then(() => this.transitionToRoute("admin.restaurants"));
      }
    }
  }

  // actions: {

  //   repositionRestaurantMarker(newLat, newLong) {
  //     this.set("model.restaurant.latitude", newLat);
  //     this.set("model.restaurant.longitude", newLong);
  //   },
});
