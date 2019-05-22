package com.atlantbh.devdays.demo.abh.restaurants.service.event.types.user;

import com.atlantbh.devdays.demo.abh.restaurants.domain.User;

/**
 * User password changed event is raised when users' password is changed.
 *
 * @author Kenan Klisura
 */
public class UserPasswordChangedEvent extends BaseUserEvent {
  private Long userId;
  private String userEmail;

  /**
   * Instantiates a new event.
   *
   * @param user the user
   */
  public UserPasswordChangedEvent(User user) {
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
