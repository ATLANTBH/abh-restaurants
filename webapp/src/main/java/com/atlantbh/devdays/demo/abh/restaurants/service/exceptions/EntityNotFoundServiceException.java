package com.atlantbh.devdays.demo.abh.restaurants.service.exceptions;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

/**
 * Entity not found service exception.
 *
 * @author Kenan Klisura
 */
public class EntityNotFoundServiceException extends ServiceException {
  /** Instantiates a new Entity not found service exception. */
  public EntityNotFoundServiceException() {
    this("Entity");
  }

  /**
   * Instantiates a new Entity not found service exception.
   *
   * @param entityName Name of the entity.
   */
  public EntityNotFoundServiceException(String entityName) {
    super(HttpStatus.NOT_FOUND, String.format("%s not found.", entityName));
  }

  /**
   * Instantiates a new Entity not found service exception.
   *
   * @param entityName Name of the entity.
   * @param id Id of an entity.
   */
  public EntityNotFoundServiceException(String entityName, Long id) {
    super(
        HttpStatus.NOT_FOUND,
        String.format("%s with id %d not found.", StringUtils.capitalize(entityName), id));
  }
}
