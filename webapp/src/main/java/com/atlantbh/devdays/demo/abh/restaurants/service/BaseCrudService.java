package com.atlantbh.devdays.demo.abh.restaurants.service;

import com.atlantbh.devdays.demo.abh.restaurants.repository.BaseCrudRepository;
import com.atlantbh.devdays.demo.abh.restaurants.service.exceptions.EntityNotFoundServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

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
   * @param pageRequest Page request.
   * @return All models sorted by {@link #DEFAULT_SORT_PROPERTY}.
   */
  public Page<T> findAll(Pageable pageRequest) {
    return findAll(pageRequest, DEFAULT_SORT_PROPERTY, Sort.Direction.DESC);
  }

  /**
   * Returns all models sorted by sortProperty and sortDirection.
   *
   * @param pageRequest Page request.
   * @param sortProperty Sort property.
   * @param sortDirection Sort direction.
   * @return All models sorted.
   */
  public Page<T> findAll(Pageable pageRequest, String sortProperty, Sort.Direction sortDirection) {
    return findAll(pageRequest, Sort.by(sortDirection, sortProperty));
  }

  /**
   * Returns all models sorted by sort.
   *
   * @param sort Sort to sort by. :)
   * @return All models sorted.
   */
  public Page<T> findAll(Pageable request, Sort sort) {
    PageRequest pageRequest = PageRequest.of(request.getPageNumber(), request.getPageSize(), sort);
    return repository.findAll(null, pageRequest);
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
