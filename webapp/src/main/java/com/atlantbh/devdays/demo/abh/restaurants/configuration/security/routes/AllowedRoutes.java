package com.atlantbh.devdays.demo.abh.restaurants.configuration.security.routes;

import org.springframework.security.web.util.matcher.RequestMatcher;

import static com.atlantbh.devdays.demo.abh.restaurants.configuration.security.RouteUtils.*;

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

  public static final RequestMatcher CITY_API = any("/api/v1/city/**");
  public static final RequestMatcher CUISINE_API = any("/api/v1/cuisine/**");

  public static final RequestMatcher NEAR_BY_RESTAURANTS = get("/api/v1/restaurant/near-by/**");
  public static final RequestMatcher POPULAR_RESTAURANTS = get("/api/v1/restaurant/popular");
  public static final RequestMatcher POPULAR_LOCATIONS_RESTAURANTS = get("/api/v1/restaurant/popular-locations");

  public static final RequestMatcher ROUTES =
      any(LOGIN_ROUTE, LOGOUT_ROUTE, CITY_API, CUISINE_API, NEAR_BY_RESTAURANTS, POPULAR_RESTAURANTS, POPULAR_LOCATIONS_RESTAURANTS);
}
