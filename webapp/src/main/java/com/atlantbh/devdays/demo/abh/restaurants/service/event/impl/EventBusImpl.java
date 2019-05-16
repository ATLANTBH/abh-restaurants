package com.atlantbh.devdays.demo.abh.restaurants.service.event.impl;

import com.atlantbh.devdays.demo.abh.restaurants.service.event.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * Implementation of event bus that publishes events using spring event publisher.
 *
 * @author Kenan Klisura
 */
@Service
public class EventBusImpl implements EventBus {
  private ApplicationEventPublisher applicationEventPublisher;

  /**
   * Instantiates a new event bus.
   *
   * @param applicationEventPublisher Application event publisher.
   */
  @Autowired
  public EventBusImpl(ApplicationEventPublisher applicationEventPublisher) {
    this.applicationEventPublisher = applicationEventPublisher;
  }

  @Override
  public void publishEvent(Object event) {
    applicationEventPublisher.publishEvent(event);
  }
}
