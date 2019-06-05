package com.atlantbh.devdays.demo.abh.restaurants.service.users.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * User request.
 *
 * @author Kenan Klisura
 */
public class UserRequest {
  @NotNull
  @Size(max = 255)
  private String firstName;

  @NotNull
  @Size(max = 255)
  private String lastName;

  @Email
  @Size(max = 512)
  private String email;

  @NotNull private String password;

  /**
   * Gets first name.
   *
   * @return the first name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets first name.
   *
   * @param firstName the first name
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Gets last name.
   *
   * @return the last name
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets last name.
   *
   * @param lastName the last name
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

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
