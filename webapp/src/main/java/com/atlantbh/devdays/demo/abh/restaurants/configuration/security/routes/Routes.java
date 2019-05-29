package com.atlantbh.devdays.demo.abh.restaurants.configuration.security.routes;

import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * Route contract.
 *
 * @author Kenan Klisura
 */
public interface Routes<T extends Routes> {
  /**
   * Array of routes.
   *
   * @return Array of routes.
   */
  T[] routes();

  /**
   * Route request matcher.
   *
   * @return Route request matcher.
   */
  RequestMatcher requestMatcher();
}
