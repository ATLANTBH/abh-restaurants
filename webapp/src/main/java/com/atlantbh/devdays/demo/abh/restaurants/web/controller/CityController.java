package com.atlantbh.devdays.demo.abh.restaurants.web.controller;

import com.atlantbh.devdays.demo.abh.restaurants.domain.City;
import com.atlantbh.devdays.demo.abh.restaurants.repository.CityRepository;
import com.atlantbh.devdays.demo.abh.restaurants.service.CityService;
import com.atlantbh.devdays.demo.abh.restaurants.service.exceptions.EntityNotFoundServiceException;
import com.atlantbh.devdays.demo.abh.restaurants.service.requests.CityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * City controller.
 *
 * <p>TODO(kklisura): Errors when entity is not found. TODO(kklisura): Protected APIs?
 *
 * @author Kenan Klisura
 */
@RestController
@RequestMapping(path = "/api/v1/city")
public class CityController extends BaseController<CityService, CityRepository, City> {
  @Autowired
  public CityController(CityService service) {
    super(service);
  }

  /**
   * Creates a new city.
   *
   * @param request City request.
   * @return Newly created city.
   */
  @Transactional
  @PostMapping
  public City create(@RequestBody CityRequest request) {
    return service.create(request);
  }

  /**
   * Updates an existing city.
   *
   * @param id Id of a city.
   * @param request City request.
   * @return Updated city.
   * @throws EntityNotFoundServiceException If no city is found.
   */
  @Transactional
  @PutMapping("{id}")
  public City update(@PathVariable("id") Long id, @RequestBody CityRequest request)
      throws EntityNotFoundServiceException {
    return service.update(id, request);
  }
}
