package com.atlantbh.devdays.demo.abh.restaurants.web.controller.auth.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Authentication/login request.
 *
 * @author Kenan Klisura
 */
public class AuthenticationRequest {
  @JsonProperty("username")
  private String usernameOrEmail;

  private String password;

  /**
   * Gets username or email.
   *
   * @return the username or email
   */
  public String getUsernameOrEmail() {
    return usernameOrEmail;
  }

  /**
   * Sets username or email.
   *
   * @param usernameOrEmail the username or email
   */
  public void setUsernameOrEmail(String usernameOrEmail) {
    this.usernameOrEmail = usernameOrEmail;
  }

  /**
   * Gets password.
   *
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets password.
   *
   * @param password the password
   */
  public void setPassword(String password) {
    this.password = password;
  }
}
