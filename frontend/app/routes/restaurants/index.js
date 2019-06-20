import Route from "@ember/routing/route";
import { inject as service } from "@ember/service";

export default Route.extend({
  restaurantService: service("restaurant-service"),

  queryParams: {
    page: {
      refreshModel: true
    },
    name: {
      refreshModel: true
    },
    price: {
      refreshModel: true
    },
    rating: {
      refreshModel: true
    },
    cuisine: {
      refreshModel: true
    },
    sortBy: {
      refreshModel: true
    },
    city: {
      refreshModel: true
    }
  },

  model(params) {
    const request = {
      page: params.page,
      size: 9,
      name: params.name,
      price: params.price,
      rating: params.rating,
      cuisine: params.cuisine,
      sortBy: params.sortBy,
      city: params.city
    };

    return this.get("restaurantService").findAllRestaurants(request);
  }
});
