package com.atlantbh.devdays.demo.abh.restaurants.web.controller;

import com.atlantbh.devdays.demo.abh.restaurants.domain.Cuisine;
import com.atlantbh.devdays.demo.abh.restaurants.repository.CuisineRepository;
import com.atlantbh.devdays.demo.abh.restaurants.service.CuisineService;
import com.atlantbh.devdays.demo.abh.restaurants.service.exceptions.EntityNotFoundServiceException;
import com.atlantbh.devdays.demo.abh.restaurants.service.requests.CuisineRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Cuisine controller.
 *
 * <p>TODO(kklisura): Errors when entity is not found. TODO(kklisura): Protected APIs?
 *
 * @author Kenan Klisura
 */
@RestController
@RequestMapping(path = "/api/v1/cuisine")
public class CuisineController extends BaseController<CuisineService, CuisineRepository, Cuisine> {
  @Autowired
  public CuisineController(CuisineService service) {
    super(service);
  }

  /**
   * Creates a new cuisine.
   *
   * @param request Cuisine request.
   * @return Newly created cuisine.
   */
  @Transactional
  @PostMapping
  public Cuisine create(@RequestBody CuisineRequest request) {
    return service.create(request);
  }

  /**
   * Updates an existing cuisine.
   *
   * @param id Id of a city.
   * @param request Cuisine request.
   * @return Updated cuisine.
   * @throws EntityNotFoundServiceException If no city is found.
   */
  @Transactional
  @PutMapping("{id}")
  public Cuisine update(@PathVariable("id") Long id, @RequestBody CuisineRequest request)
      throws EntityNotFoundServiceException {
    return service.update(id, request);
  }
}
