package com.atlantbh.devdays.demo.abh.restaurants.web.controller.users.requests;

import javax.validation.constraints.NotNull;

/**
 * Password reset request.
 *
 * @author Kenan Klisura
 */
public class PasswordResetRequest {
  @NotNull private String token;

  @NotNull private String oldPassword;

  @NotNull private String newPassword;

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

  /**
   * Gets old password.
   *
   * @return the old password
   */
  public String getOldPassword() {
    return oldPassword;
  }

  /**
   * Sets old password.
   *
   * @param oldPassword the old password
   */
  public void setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
  }

  /**
   * Gets new password.
   *
   * @return the new password
   */
  public String getNewPassword() {
    return newPassword;
  }

  /**
   * Sets new password.
   *
   * @param newPassword the new password
   */
  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }
}
