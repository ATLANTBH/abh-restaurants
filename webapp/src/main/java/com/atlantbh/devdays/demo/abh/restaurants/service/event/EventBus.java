package com.atlantbh.devdays.demo.abh.restaurants.service.event;

/**
 * Event bus.
 *
 * @author Kenan Klisura
 */
public interface EventBus {
  /**
   * Publishes an event.
   *
   * @param event Event.
   */
  void publishEvent(Object event);
}
