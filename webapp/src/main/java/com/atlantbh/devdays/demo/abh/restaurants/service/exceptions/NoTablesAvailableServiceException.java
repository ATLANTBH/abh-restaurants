package com.atlantbh.devdays.demo.abh.restaurants.service.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Created by Kenan Klisura on 2019-05-23.
 *
 * @author Kenan Klisura
 */
public class NoTablesAvailableServiceException extends ServiceException {
  /** Instantiates a new Service exception. */
  public NoTablesAvailableServiceException() {
    super(HttpStatus.BAD_REQUEST);
  }
}
