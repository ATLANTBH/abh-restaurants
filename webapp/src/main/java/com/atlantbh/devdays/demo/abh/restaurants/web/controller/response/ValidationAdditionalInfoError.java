package com.atlantbh.devdays.demo.abh.restaurants.web.controller.response;

import org.springframework.validation.FieldError;

/**
 * Validation result additional info.
 *
 * @author Kenan Klisura
 */
public class ValidationAdditionalInfoError extends AdditionalResponseInfo {
  private String fieldName;
  private String code;
  private String message;

  /**
   * Instantiates a new Validation sub error.
   *
   * @param fieldName the field name
   * @param message the message
   * @param code the validation type
   */
  private ValidationAdditionalInfoError(String fieldName, String message, String code) {
    this.fieldName = fieldName;
    this.message = message;
    this.code = code;
  }

  /**
   * Gets field name.
   *
   * @return the field name
   */
  public String getFieldName() {
    return fieldName;
  }

  /**
   * Sets field name.
   *
   * @param fieldName the field name
   */
  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }

  /**
   * Gets message.
   *
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * Sets message.
   *
   * @param message the message
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * Gets validation type.
   *
   * @return the validation type
   */
  public String getCode() {
    return code;
  }

  /**
   * Sets validation type.
   *
   * @param code the validation type
   */
  public void setCode(String code) {
    this.code = code;
  }

  /**
   * Field error to sub error validation sub error.
   *
   * @param fieldError the field error
   * @return the validation sub error
   */
  public static ValidationAdditionalInfoError fieldErrorToSubError(FieldError fieldError) {
    return new ValidationAdditionalInfoError(
        fieldError.getField(), fieldError.getDefaultMessage(), fieldError.getCode());
  }
}
