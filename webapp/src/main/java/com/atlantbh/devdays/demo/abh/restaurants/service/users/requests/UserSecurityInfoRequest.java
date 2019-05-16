package com.atlantbh.devdays.demo.abh.restaurants.service.users.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * User security info.
 *
 * @author Kenan Klisura
 */
public class UserSecurityInfoRequest {
  @NotNull private String oldPassword;

  @Email
  @NotNull
  @Size(max = 512)
  private String email;

  @NotNull private String password;

  /**
   * Gets old email.
   *
   * @return the old email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets old email.
   *
   * @param email the old email
   */
  public void setEmail(String email) {
    this.email = email;
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
  public String getPassword() {
    return password;
  }

  /**
   * Sets new password.
   *
   * @param password the new password
   */
  public void setPassword(String password) {
    this.password = password;
  }
}
