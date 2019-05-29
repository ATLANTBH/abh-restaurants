package com.atlantbh.devdays.demo.abh.restaurants.service;

import com.atlantbh.devdays.demo.abh.restaurants.domain.Cuisine;
import com.atlantbh.devdays.demo.abh.restaurants.repository.CuisineRepository;
import com.atlantbh.devdays.demo.abh.restaurants.service.exceptions.EntityNotFoundServiceException;
import com.atlantbh.devdays.demo.abh.restaurants.service.requests.CuisineRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Cuisine service manages all cuisine-related operations.
 *
 * @author Kenan Klisura
 */
@Service
public class CuisineService extends BaseCrudService<Cuisine, Long, CuisineRepository> {
  private static final String DEFAULT_SORT_PROPERTY = "name";

  /**
   * Instantiates a new cuisine service.
   *
   * @param repository Cuisine repository where cuisine is being persisted.
   */
  @Autowired
  public CuisineService(CuisineRepository repository) {
    super(repository);
  }

  /**
   * Returns all models sorted by {@link #DEFAULT_SORT_PROPERTY}.
   *
   * @return All models sorted by {@link #DEFAULT_SORT_PROPERTY}.
   */
  @Override
  public Iterable<Cuisine> findAll() {
    return super.findAll(DEFAULT_SORT_PROPERTY, Sort.Direction.ASC);
  }

  /**
   * Create a new cuisine given a cuisine creation request.
   *
   * @param request Creation request containing all attributes to update.
   * @return Newly created cuisine.
   */
  public Cuisine create(CuisineRequest request) {
    Cuisine cuisine = new Cuisine();

    cuisine.setName(request.getName());

    return repository.save(cuisine);
  }

  /**
   * Update an existing cuisine with the attributes in the cuisine request.
   *
   * @param id Id of the existing cuisine object.
   * @param request Update request containing all properties to update.
   * @return Updated cuisine.
   * @throws EntityNotFoundServiceException If the existing cuisine is not found.
   */
  public Cuisine update(Long id, CuisineRequest request) throws EntityNotFoundServiceException {
    Cuisine cuisine = get(id);

    cuisine.setName(request.getName());

    return repository.save(cuisine);
  }
}
