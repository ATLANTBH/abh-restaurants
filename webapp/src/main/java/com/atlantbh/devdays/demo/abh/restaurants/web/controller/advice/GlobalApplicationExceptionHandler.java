package com.atlantbh.devdays.demo.abh.restaurants.web.controller.advice;

import com.atlantbh.devdays.demo.abh.restaurants.service.exceptions.ServiceException;
import com.atlantbh.devdays.demo.abh.restaurants.utils.persistence.PersistenceUtils;
import com.atlantbh.devdays.demo.abh.restaurants.web.controller.utils.ResponseUtils;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Global application exception handler.
 *
 * @author Kenan Klisura
 */
@ControllerAdvice
public class GlobalApplicationExceptionHandler extends ResponseEntityExceptionHandler {
  private static final String MISSING_REQUEST_BODY = "Missing request body";

  private static final Logger LOGGER =
      LoggerFactory.getLogger(GlobalApplicationExceptionHandler.class);

  /**
   * Handles service exception.
   *
   * @param ex Exception.
   * @param request Request.
   * @return Response entity.
   */
  @ExceptionHandler(ServiceException.class)
  public ResponseEntity<Object> handleServiceException(
      ServiceException ex, HttpServletRequest request) {
    LOGGER.error(
        "Error while executing {} {}: {}",
        request.getMethod(),
        request.getRequestURI(),
        ex.getMessage(),
        ex);

    return ResponseUtils.buildErrorResponseEntity(
        ResponseUtils.buildError(ex.getStatus(), ex.getMessage(), request));
  }

  /**
   * Handles data integrity violation exception.
   *
   * @param ex Exception.
   * @param request Request.
   * @return Response entity.
   */
  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<Object> handleDataIntegrityViolationException(
      DataIntegrityViolationException ex, HttpServletRequest request) {

    if (PersistenceUtils.isConstraintViolation(ex)) {
      String errorMessage = PersistenceUtils.getConstraintViolationErrorMessage(ex);
      if (errorMessage != null) {
        return ResponseUtils.buildBadRequestResponseEntity(errorMessage, request);
      }
    }

    return ResponseUtils.buildInternalServerErrorResponseEntity(request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    return ResponseUtils.buildBadRequestResponseEntity(ex.getBindingResult(), request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      HttpMessageNotReadableException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    return ResponseUtils.buildBadRequestResponseEntity(MISSING_REQUEST_BODY, request);
  }
}
