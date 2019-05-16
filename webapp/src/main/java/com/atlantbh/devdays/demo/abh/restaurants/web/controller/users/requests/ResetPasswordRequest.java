package com.atlantbh.devdays.demo.abh.restaurants.web.controller.users.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * Reset password request.
 *
 * @author Kenan Klisura
 */
public class ResetPasswordRequest {
  @Email @NotNull private String email;

  /**
   * Gets email.
   *
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets email.
   *
   * @param email the email
   */
  public void setEmail(String email) {
    this.email = email;
  }
}
