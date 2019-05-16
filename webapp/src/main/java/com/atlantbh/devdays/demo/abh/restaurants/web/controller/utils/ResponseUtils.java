package com.atlantbh.devdays.demo.abh.restaurants.web.controller.utils;

import com.atlantbh.devdays.demo.abh.restaurants.web.controller.response.AdditionalResponseInfo;
import com.atlantbh.devdays.demo.abh.restaurants.web.controller.response.Response;
import com.atlantbh.devdays.demo.abh.restaurants.web.controller.response.ValidationAdditionalInfoError;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

/**
 * Response utils.
 *
 * @author Kenan Klisura
 */
public final class ResponseUtils {
  private static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal server error.";
  private static final String VALIDATION_FAILURE_ERROR_MESSAGE = "Failed validating request.";

  /**
   * Builds an error.
   *
   * @param status Http status.
   * @param message Error message.
   * @param request Request.
   * @return Error object.
   */
  public static Response buildError(HttpStatus status, String message, HttpServletRequest request) {
    return new Response(status, message, request.getServletPath());
  }

  /**
   * Builds a response entity.
   *
   * @param message Message.
   * @param request Request.
   * @return Response entity with error.
   */
  public static ResponseEntity<Object> buildBadRequestResponseEntity(
      String message, HttpServletRequest request) {
    Response error = buildError(HttpStatus.BAD_REQUEST, message, request);
    return buildErrorResponseEntity(error);
  }

  /**
   * Builds a response entity.
   *
   * @param message Message.
   * @param request Request.
   * @return Response entity with error.
   */
  public static ResponseEntity<Object> buildBadRequestResponseEntity(
      String message, WebRequest request) {
    return buildBadRequestResponseEntity(message, ((ServletWebRequest) request).getRequest());
  }

  /**
   * Builds a response entity.
   *
   * @param bindingResult Binding result.
   * @param request Request.
   * @return Response entity with error.
   */
  public static ResponseEntity<Object> buildBadRequestResponseEntity(
      BindingResult bindingResult, HttpServletRequest request) {
    Response error = buildError(HttpStatus.BAD_REQUEST, VALIDATION_FAILURE_ERROR_MESSAGE, request);

    List<AdditionalResponseInfo> validationSubErrors =
        bindingResult
            .getFieldErrors()
            .stream()
            .map(ValidationAdditionalInfoError::fieldErrorToSubError)
            .collect(Collectors.toList());

    error.setAdditional(validationSubErrors);

    return buildErrorResponseEntity(error);
  }

  /**
   * Builds a response entity.
   *
   * @param bindingResult Binding result.
   * @param request Web request.
   * @return Response entity with error.
   */
  public static ResponseEntity<Object> buildBadRequestResponseEntity(
      BindingResult bindingResult, WebRequest request) {
    return buildBadRequestResponseEntity(bindingResult, ((ServletWebRequest) request).getRequest());
  }

  /**
   * Builds a response entity.
   *
   * @param request Request.
   * @return Response entity with error.
   */
  public static ResponseEntity<Object> buildInternalServerErrorResponseEntity(
      HttpServletRequest request) {
    Response error = buildError(HttpStatus.BAD_REQUEST, INTERNAL_SERVER_ERROR_MESSAGE, request);
    return buildErrorResponseEntity(error);
  }

  /**
   * Builds a response entity with error from error.
   *
   * @param error Error.
   * @return Response entity with error.
   */
  public static ResponseEntity<Object> buildErrorResponseEntity(Response error) {
    return ResponseEntity.status(error.getStatus()).body(error);
  }
}
