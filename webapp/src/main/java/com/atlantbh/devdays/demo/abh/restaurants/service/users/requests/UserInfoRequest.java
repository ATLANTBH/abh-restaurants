package com.atlantbh.devdays.demo.abh.restaurants.service.users.requests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * User info request.
 *
 * @author Kenan Klisura
 */
public class UserInfoRequest {
  @NotNull
  @Size(max = 255)
  private String firstName;

  @NotNull
  @Size(max = 255)
  private String lastName;

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
}
