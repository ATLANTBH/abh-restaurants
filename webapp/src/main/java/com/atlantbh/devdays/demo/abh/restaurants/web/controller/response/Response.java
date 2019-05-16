package com.atlantbh.devdays.demo.abh.restaurants.web.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Date;
import java.util.List;
import org.springframework.http.HttpStatus;

/**
 * Response returned by controllers.
 *
 * @author Kenan Klisura
 */
public class Response {
  private long timestamp;

  private int status;
  private String reason;

  private String message;

  private String path;

  @JsonInclude(Include.NON_NULL)
  private List<AdditionalResponseInfo> additional;

  private Response(HttpStatus status) {
    this.status = status.value();
    this.reason = status.getReasonPhrase();
    this.timestamp = new Date().getTime();
  }

  public Response(HttpStatus status, String path) {
    this(status);
    this.path = path;
    this.message = "Unexpected reason";
  }

  public Response(HttpStatus status, String message, String path) {
    this(status);
    this.reason = status.getReasonPhrase();
    this.message = message;
    this.path = path;
  }

  public int getStatus() {
    return status;
  }

  public String getReason() {
    return reason;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public String getMessage() {
    return message;
  }

  public List<AdditionalResponseInfo> getAdditional() {
    return additional;
  }

  public void setAdditional(List<AdditionalResponseInfo> additional) {
    this.additional = additional;
  }

  public String getPath() {
    return path;
  }
}
