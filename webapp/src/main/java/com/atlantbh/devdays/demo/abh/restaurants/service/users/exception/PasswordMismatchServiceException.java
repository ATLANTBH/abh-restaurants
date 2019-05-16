package com.atlantbh.devdays.demo.abh.restaurants.service.users.exception;

import com.atlantbh.devdays.demo.abh.restaurants.service.exceptions.ServiceException;
import org.springframework.http.HttpStatus;

/**
 * Password mismatch service exception.
 *
 * @author Kenan Klisura
 */
public class PasswordMismatchServiceException extends ServiceException {
  public PasswordMismatchServiceException() {
    super(HttpStatus.UNAUTHORIZED, "Bad password.");
  }
}
