package com.atlantbh.devdays.demo.abh.restaurants.service;

import com.atlantbh.devdays.demo.abh.restaurants.repository.BaseCrudRepository;
import com.atlantbh.devdays.demo.abh.restaurants.service.exceptions.EntityNotFoundServiceException;
import java.util.Optional;
import org.springframework.data.domain.Sort;

/**
 * Base crud service.
 *
 * @param <T> Type of model.
 * @param <ID> Type of model id.
 * @param <R> Type of model repository.
 * @author Kenan Klisura
 */
public abstract class BaseCrudService<T, ID, R extends BaseCrudRepository<T, ID>> {
  private static final String DEFAULT_SORT_PROPERTY = "id";

  /** The Repository. */
  protected R repository;

  /**
   * Instantiates a new Base crud service.
   *
   * @param repository the repository
   */
  public BaseCrudService(R repository) {
    this.repository = repository;
  }

  /**
   * Returns all models sorted by {@link #DEFAULT_SORT_PROPERTY}.
   *
   * @return All models sorted by {@link #DEFAULT_SORT_PROPERTY}.
   */
  public Iterable<T> findAll() {
    return findAll(DEFAULT_SORT_PROPERTY, Sort.Direction.DESC);
  }

  /**
   * Returns all models sorted by sortProperty and sortDirection.
   *
   * @param sortProperty Sort property.
   * @param sortDirection Sort direction.
   * @return All models sorted.
   */
  public Iterable<T> findAll(String sortProperty, Sort.Direction sortDirection) {
    return findAll(Sort.by(sortDirection, sortProperty));
  }

  /**
   * Returns all models sorted by sort.
   *
   * @param sort Sort to sort by. :)
   * @return All models sorted.
   */
  public Iterable<T> findAll(Sort sort) {
    return repository.findAll(null, sort);
  }

  /**
   * Finds a model given its id.
   *
   * @param id Model id.
   * @return Optional model.
   */
  public Optional<T> findById(ID id) {
    return repository.findById(id);
  }

  /**
   * Returns the model given its id. It is strongly advised that you override this method and throw
   * appropriate EntityNotFoundServiceException exception, together with its entity name and id.
   *
   * @param id Model id.
   * @return Model. t
   * @throws EntityNotFoundServiceException the entity not found service exception
   */
  public T get(ID id) throws EntityNotFoundServiceException {
    return findById(id).orElseThrow(EntityNotFoundServiceException::new);
  }

  /**
   * Deletes a model given its id.
   *
   * @param id Model id.
   */
  public void delete(ID id) {
    repository.deleteById(id);
  }
}
