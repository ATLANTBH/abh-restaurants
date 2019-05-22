package com.atlantbh.devdays.demo.abh.restaurants.web.controller.bootstrap.dto;

import com.atlantbh.devdays.demo.abh.restaurants.domain.User;

/**
 * Current user DTO.
 *
 * @author Kenan Klisura
 */
public class CurrentUserDto {
  private Long id;

  private String firstName;

  private String lastName;

  private String email;

  private String username;

  /**
   * Instantiates a new CurrentUserDto given a user.
   *
   * @param user the user
   */
  public CurrentUserDto(User user) {
    this.id = user.getId();
    this.email = user.getEmail();
    this.username = user.getUsername();
    this.firstName = user.getFirstName();
    this.lastName = user.getLastName();
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * Gets first name.
   *
   * @return the first name
   */
  public String getFirstName() {
    return firstName;
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
   * Gets email.
   *
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Gets username.
   *
   * @return the username
   */
  public String getUsername() {
    return username;
  }
}
