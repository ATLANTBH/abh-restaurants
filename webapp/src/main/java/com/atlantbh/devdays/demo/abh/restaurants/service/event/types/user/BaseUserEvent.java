package com.atlantbh.devdays.demo.abh.restaurants.service.event.types.user;

import com.atlantbh.devdays.demo.abh.restaurants.service.event.types.BaseEvent;
import com.atlantbh.devdays.demo.abh.restaurants.domain.users.User;

/**
 * Base event for user related events.
 *
 * @author Kenan Klisura
 */
public class BaseUserEvent extends BaseEvent {
  private String fullUserName;

  /**
   * Instantiates a new Base user event.
   *
   * @param user User.
   */
  protected BaseUserEvent(User user) {
    this.fullUserName = user.getFirstName() + " " + user.getLastName();
  }

  /**
   * Gets full user name.
   *
   * @return Full user name.
   */
  public String getFullUserName() {
    return fullUserName;
  }
}
