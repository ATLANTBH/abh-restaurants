package com.atlantbh.devdays.demo.abh.restaurants.configuration.security.routes;

import static com.atlantbh.devdays.demo.abh.restaurants.configuration.security.RouteUtils.any;
import static com.atlantbh.devdays.demo.abh.restaurants.configuration.security.RouteUtils.post;

import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * These are allowed routes for the the application. They do not need authenticated user to be
 * present.
 *
 * <p>Even though some of the enums here are not used directly, they are still being used indirectly
 * via {@link AllowedRoutes#ROUTES}, so make sure you don't accidentally remove something.
 *
 * @author Kenan Klisura
 */
public class AllowedRoutes {

  public static final RequestMatcher LOGIN_ROUTE = post("/api/v1/login");
  public static final RequestMatcher LOGOUT_ROUTE = post("/api/v1/logout");

  public static final RequestMatcher ROUTES = any(LOGIN_ROUTE, LOGOUT_ROUTE);
}