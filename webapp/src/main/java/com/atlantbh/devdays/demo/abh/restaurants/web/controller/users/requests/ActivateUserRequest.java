package com.atlantbh.devdays.demo.abh.restaurants.web.controller.users.requests;

import javax.validation.constraints.NotNull;

/**
 * Activate user request.
 *
 * @author Kenan Klisura
 */
public class ActivateUserRequest {
  @NotNull private String token;

  /**
   * Gets token.
   *
   * @return the token
   */
  public String getToken() {
    return token;
  }

  /**
   * Sets token.
   *
   * @param token the token
   */
  public void setToken(String token) {
    this.token = token;
  }
}
