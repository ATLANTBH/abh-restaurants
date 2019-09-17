package com.atlantbh.devdays.demo.abh.restaurants.web.controller;

import com.atlantbh.devdays.demo.abh.restaurants.domain.Reservation;
import com.atlantbh.devdays.demo.abh.restaurants.domain.Restaurant;
import com.atlantbh.devdays.demo.abh.restaurants.service.ReservationService;
import com.atlantbh.devdays.demo.abh.restaurants.service.RestaurantService;
import com.atlantbh.devdays.demo.abh.restaurants.service.exceptions.EntityNotFoundServiceException;
import com.atlantbh.devdays.demo.abh.restaurants.service.exceptions.NoTablesAvailableServiceException;
import com.atlantbh.devdays.demo.abh.restaurants.service.requests.ReservationRequest;
import com.atlantbh.devdays.demo.abh.restaurants.service.requests.RestaurantFilter;
import com.atlantbh.devdays.demo.abh.restaurants.service.requests.RestaurantRequest;
import com.atlantbh.devdays.demo.abh.restaurants.service.responses.PopularLocation;
import com.atlantbh.devdays.demo.abh.restaurants.service.responses.ReservationInquiryResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Restaurant controller.
 *
 * @author Kenan Klisura
 */
@RestController
@RequestMapping(path = "/api/v1/restaurant")
public class RestaurantController {
  private RestaurantService service;
  private ReservationService reservationService;

  @Autowired
  public RestaurantController(RestaurantService service, ReservationService reservationService) {
    this.service = service;
    this.reservationService = reservationService;
  }

  @Transactional(readOnly = true)
  @GetMapping
  public Page<Restaurant> findAll(
      @RequestParam(name = "page", required = false, defaultValue = "0") int page,
      @RequestParam(name = "size", required = false, defaultValue = "9") int pageSize,
      @RequestParam(name = "name", required = false) String name,
      @RequestParam(name = "price", required = false) Long price,
      @RequestParam(name = "rating", required = false) Long rating,
      @RequestParam(name = "sortBy", required = false) String sortBy,
      @RequestParam(name = "cuisine", required = false) String cuisine,
      @RequestParam(name = "city", required = false) Long cityId) {
    final RestaurantFilter filter = new RestaurantFilter();
    filter.setPage(page);
    filter.setPageSize(pageSize);
    filter.setName(name);
    filter.setPrice(price);
    filter.setRating(rating);
    filter.setCityId(cityId);
    filter.setCuisine(cuisine);
    filter.setSortBy(sortBy);
    return service.find(filter);
  }

  @Transactional(readOnly = true)
  @GetMapping("{id}")
  public Restaurant get(@PathVariable("id") Long id) throws EntityNotFoundServiceException {
    return service.get(id);
  }

  /**
   * Fetches near-by restaurant specified by lat and lng.
   *
   * @param lat Latitude.
   * @param lng Longitude.
   * @return List of near-by restaurants.
   */
  @Transactional(readOnly = true)
  @GetMapping("/near-by/{lat}/{lng}")
  public List<Restaurant> findNearBy(
      @PathVariable("lat") float lat, @PathVariable("lng") float lng) {
    return service.findNearBy(lat, lng);
  }

  /**
   * Fetches a popular restaurants.
   *
   * @return List of restaurants.
   */
  @Transactional(readOnly = true)
  @GetMapping("/popular")
  public List<Restaurant> findPopular() {
    return service.findPopular();
  }

  /**
   * Fetches popular locations for all restaurants.
   *
   * @return List of popular locations.
   * @throws EntityNotFoundServiceException If any entity is not found.
   */
  @Transactional(readOnly = true)
  @GetMapping("/popular-locations")
  public List<PopularLocation> findPopularLocations() throws EntityNotFoundServiceException {
    return service.findPopularLocations();
  }

  @Transactional(readOnly = true)
  @PostMapping("/{id}/reservation-inquiry")
  public ReservationInquiryResponse reservationInquiry(
      @PathVariable("id") Long id, @RequestBody ReservationRequest request) {
    return reservationService.reservationInquiry(id, request);
  }

  @Transactional
  @PostMapping("/{id}/reservation")
  public Reservation reservation(
      @PathVariable("id") Long id,
      @RequestBody ReservationRequest request,
      @AuthenticationPrincipal UserDetails userDetails)
      throws NoTablesAvailableServiceException, EntityNotFoundServiceException {
    return reservationService.create(id, request, userDetails);
  }

  @Transactional
  @PostMapping
  public Restaurant create(@RequestBody RestaurantRequest request)
      throws EntityNotFoundServiceException {
    return service.create(request);
  }

  @Transactional
  @PutMapping("{id}")
  public Restaurant update(@PathVariable("id") Long id, @RequestBody RestaurantRequest request)
      throws EntityNotFoundServiceException {
    return service.update(id, request);
  }
}
