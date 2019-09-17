package com.atlantbh.devdays.demo.abh.restaurants.service;

import com.atlantbh.devdays.demo.abh.restaurants.repository.BaseCrudRepository;
import com.atlantbh.devdays.demo.abh.restaurants.service.exceptions.EntityNotFoundServiceException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.*;

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
   * Populates items.
   *
   * @param item Item.
   * @return Item.
   */
  protected T populateItem(T item) {
    return item;
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
    final Page<T> result = repository.findAll(null, pageRequest);
    return transformPage(result, request);
  }

  /**
   * Transforms the items in the page.
   *
   * @param page Page.
   * @param pageRequest Page request.
   * @return Transformed page items.
   */
  protected Page<T> transformPage(Page<T> page, Pageable pageRequest) {
    final List<T> items =
        page.getContent().stream().map(this::populateItem).collect(Collectors.toList());
    return new PageImpl<>(items, pageRequest, page.getTotalElements());
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
