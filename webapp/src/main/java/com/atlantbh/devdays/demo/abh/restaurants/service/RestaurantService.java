package com.atlantbh.devdays.demo.abh.restaurants.service;

import com.atlantbh.devdays.demo.abh.restaurants.domain.Restaurant;
import com.atlantbh.devdays.demo.abh.restaurants.domain.RestaurantReview;
import com.atlantbh.devdays.demo.abh.restaurants.domain.User;
import com.atlantbh.devdays.demo.abh.restaurants.repository.RestaurantRepository;
import com.atlantbh.devdays.demo.abh.restaurants.repository.RestaurantReviewRepository;
import com.atlantbh.devdays.demo.abh.restaurants.repository.specification.RestaurantSpecification;
import com.atlantbh.devdays.demo.abh.restaurants.service.exceptions.EntityNotFoundServiceException;
import com.atlantbh.devdays.demo.abh.restaurants.service.requests.RestaurantFilter;
import com.atlantbh.devdays.demo.abh.restaurants.service.requests.RestaurantImageRequest;
import com.atlantbh.devdays.demo.abh.restaurants.service.requests.RestaurantRequest;
import com.atlantbh.devdays.demo.abh.restaurants.service.requests.ReviewRequest;
import com.atlantbh.devdays.demo.abh.restaurants.service.responses.PopularLocation;
import com.atlantbh.devdays.demo.abh.restaurants.service.responses.RestaurantImageResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

/**
 * Created by Kenan Klisura on 2019-05-23.
 *
 * @author Kenan Klisura
 */
@Service
public class RestaurantService extends BaseCrudService<Restaurant, Long, RestaurantRepository> {
  // TODO(kklisura): This should be moved to configuration.
  private static final String AWS_BASE_PATH = "https://abhrestaurants.s3.amazonaws.com/";

  private CityService cityService;
  private RestaurantReviewRepository restaurantReviewRepository;

  /**
   * Instantiates a new Base crud service.
   *
   * @param repository the repository
   */
  @Autowired
  public RestaurantService(RestaurantRepository repository) {
    super(repository);
  }

  @Autowired
  public void setCityService(CityService cityService) {
    this.cityService = cityService;
  }

  @Autowired
  public void setRestaurantReviewRepository(RestaurantReviewRepository restaurantReviewRepository) {
    this.restaurantReviewRepository = restaurantReviewRepository;
  }

  /**
   * Creates a restaurant.
   *
   * @param request Create request.
   * @return Created restaurant.
   * @throws EntityNotFoundServiceException If dependent entity found.
   */
  public Restaurant create(RestaurantRequest request) throws EntityNotFoundServiceException {
    Restaurant restaurant = new Restaurant();
    updateRestaurant(restaurant, request);
    return repository.save(restaurant);
  }

  /**
   * Updates a restaurant.
   *
   * @param id Id of a restaurant to updated.
   * @param request Update request.
   * @return Updated restaurant.
   * @throws EntityNotFoundServiceException If no restaurant or any other dependent entity found.
   */
  public Restaurant update(Long id, RestaurantRequest request)
      throws EntityNotFoundServiceException {
    Restaurant restaurant = get(id);
    updateRestaurant(restaurant, request);
    return repository.save(restaurant);
  }

  public Page<Restaurant> find(RestaurantFilter filter) {
    final Page<Restaurant> restaurants =
        repository.findAll(
            new RestaurantSpecification(filter), RestaurantSpecification.createPage(filter));
    applySorting(restaurants, filter);
    return restaurants;
  }

  /**
   * Fetches near-by restaurants with center specified by latitude and longitude.
   *
   * @return List of near-by restaurants.
   */
  public List<Restaurant> findNearBy(float latitude, float longitude) {
    return populateRestaurantRatings(repository.findNearBy(latitude, longitude));
  }

  /**
   * Fetches popular restaurants.
   *
   * @return List of popular restaurants.
   */
  public List<Restaurant> findPopular() {
    return populateRestaurantRatings(repository.findPopular());
  }

  /**
   * Fetches the popular locations for all restaurants.
   *
   * @return List of popular locations.
   * @throws EntityNotFoundServiceException If any entity is not found.
   */
  public List<PopularLocation> findPopularLocations() throws EntityNotFoundServiceException {
    final List<PopularLocation> popularLocations = repository.findPopularLocations();

    for (PopularLocation popularLocation : popularLocations) {
      popularLocation.setCity(cityService.get(popularLocation.getCityId()));
    }

    return popularLocations;
  }

  public void createReview(Long restaurantId, ReviewRequest request, User user)
      throws EntityNotFoundServiceException {
    final Restaurant restaurant = get(restaurantId);
    final Optional<RestaurantReview> existingReview =
        restaurantReviewRepository.findByRestaurantAndUser(restaurant, user);

    final RestaurantReview review =
        existingReview.orElseGet(
            () -> {
              RestaurantReview result = new RestaurantReview();
              result.setUser(user);
              result.setRestaurant(restaurant);
              return result;
            });

    review.setRating(request.getReviewScore());
    review.setReview(request.getReviewText());

    restaurantReviewRepository.save(review);
  }

  public long getNumberOfRestaurants() {
    return repository.count();
  }

  public RestaurantImageResponse updateRestaurantImage(
      Long restaurantId, RestaurantImageRequest request) throws EntityNotFoundServiceException {
    final Restaurant restaurant = get(restaurantId);

    String newImagePath =
        AWS_BASE_PATH + restaurantId + "-" + request.getImageType() + "." + request.getExtension();

    // TODO(kklisura): Handle default here.
    switch (request.getImageType()) {
      case COVER:
        restaurant.setCoverImagePath(newImagePath);
        break;
      case PROFILE:
        restaurant.setProfileImagePath(newImagePath);
        break;
    }

    repository.save(restaurant);

    return new RestaurantImageResponse(request.getImageType(), newImagePath);
  }

  /**
   * This is used to apply custom sorting. This only supports {@link RestaurantFilter.Sort#RATING}.
   * This should sort restaurants in place.
   *
   * @param restaurants Restaurants result.
   * @param filter Filter.
   */
  private void applySorting(Page<Restaurant> restaurants, RestaurantFilter filter) {
    if (!filter.getSortBy().hasPropertyName()) {
      // TODO(kklisura): Add support for RATING sort.
    }
  }

  private void updateRestaurant(Restaurant restaurant, RestaurantRequest request)
      throws EntityNotFoundServiceException {
    restaurant.setName(request.getName());
    restaurant.setDescription(request.getDescription());
    restaurant.setAddress(request.getAddress());
    restaurant.setCity(cityService.get(request.getCityId()));
    restaurant.setCoverImagePath(request.getCoverImagePath());
    restaurant.setLatitude(request.getLatitude());
    restaurant.setLongitude(request.getLongitude());
    restaurant.setMenu(request.getMenu());
    restaurant.setPhone(request.getPhone());
    restaurant.setPriceRange(request.getPriceRange());
    restaurant.setProfileImagePath(request.getProfileImagePath());
    restaurant.setCloseTime(request.getCloseTime());
    restaurant.setOpenTime(request.getOpenTime());
  }

  /**
   * Populates a ratings on a list of restaurants.
   *
   * @param restaurants List of restaurants.
   * @return List of restaurants with its ratings populated.
   */
  private List<Restaurant> populateRestaurantRatings(Collection<Restaurant> restaurants) {
    return restaurants.stream().map(this::populateRestaurantRatings).collect(Collectors.toList());
  }

  /**
   * Populates a ratings on a restaurant.
   *
   * @param restaurant Restaurant to populate its rating.
   * @return A restaurant.
   */
  private Restaurant populateRestaurantRatings(Restaurant restaurant) {
    final List<RestaurantReview> reviews = restaurantReviewRepository.findByRestaurant(restaurant);
    if (CollectionUtils.isNotEmpty(reviews)) {
      restaurant.setNumberOfRatings(reviews.size());
      final OptionalDouble averageRating = reviews.stream().mapToInt(RestaurantReview::getRating).average();
      restaurant.setAverageRating((float) averageRating.orElse(0d));
    }

    return restaurant;
  }
}
