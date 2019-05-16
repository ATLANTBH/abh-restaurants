package com.atlantbh.devdays.demo.abh.restaurants.service.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Base exception for all other exceptions.
 *
 * @author Kenan Klisura
 */
public abstract class ServiceException extends Exception {
  private HttpStatus status;

  /**
   * Instantiates a new Service exception.
   *
   * @param status Http status.
   */
  protected ServiceException(HttpStatus status) {
    super();
    this.status = status;
  }

  /**
   * Instantiates a new Service exception.
   *
   * @param status Https status.
   * @param message Error message.
   */
  protected ServiceException(HttpStatus status, String message) {
    super(message);
    this.status = status;
  }

  /**
   * Instantiates a new Service exception.
   *
   * @param status Http status.
   * @param message Error message.
   * @param cause Root cause.
   */
  protected ServiceException(HttpStatus status, String message, Throwable cause) {
    super(message, cause);
    this.status = status;
  }

  /**
   * Gets HTTP status.
   *
   * @return Status.
   */
  public HttpStatus getStatus() {
    return status;
  }
}
