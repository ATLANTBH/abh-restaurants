package com.atlantbh.devdays.demo.abh.restaurants.web.controller.auth.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Authentication/login request.
 *
 * @author Kenan Klisura
 */
public class AuthenticationRequest {
  @JsonProperty("email")
  private String email;

  private String password;

  /**
   * Gets username or email.
   *
   * @return the username or email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets username or email.
   *
   * @param email the username or email
   */
  public void setEmail(String email) {
    this.email = email;
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
