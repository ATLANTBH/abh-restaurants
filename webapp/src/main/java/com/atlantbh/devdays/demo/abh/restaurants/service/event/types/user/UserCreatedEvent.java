package com.atlantbh.devdays.demo.abh.restaurants.service.event.types.user;

import com.atlantbh.devdays.demo.abh.restaurants.domain.User;

/**
 * User created event is an event that is raised when user is created.
 *
 * @author Kenan Klisura
 */
public class UserCreatedEvent extends BaseUserEvent {
  private Long userId;
  private String userEmail;

  /**
   * Instantiates a new event.
   *
   * @param user User.
   */
  public UserCreatedEvent(User user) {
    super(user);
    this.userId = user.getId();
    this.userEmail = user.getEmail();
  }

  /**
   * Gets user id.
   *
   * @return the user id
   */
  public Long getUserId() {
    return userId;
  }

  /**
   * Gets user email.
   *
   * @return the user email
   */
  public String getUserEmail() {
    return userEmail;
  }
}
