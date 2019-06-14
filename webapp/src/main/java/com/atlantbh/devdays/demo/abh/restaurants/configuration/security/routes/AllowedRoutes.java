package com.atlantbh.devdays.demo.abh.restaurants.configuration.security.routes;

import static com.atlantbh.devdays.demo.abh.restaurants.configuration.security.RouteUtils.*;

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
  public static final RequestMatcher PUBLIC =
      any(get("/"), get("/assets/**/*"), get("/fonts/**/*"), get("/images/**/*"));

  public static final RequestMatcher BOOTSTRAP = get("/api/v1/bootstrap");

  public static final RequestMatcher LOGIN_ROUTE = post("/api/v1/login");
  public static final RequestMatcher LOGOUT_ROUTE = post("/api/v1/logout");

  public static final RequestMatcher CITY_API_ROUTES = any("/api/v1/city/**");
  public static final RequestMatcher CUISINE_API_ROUTES = any("/api/v1/cuisine/**");

  public static final RequestMatcher ALL_RESTAURANTS_ROUTE = get("/api/v1/restaurant");
  public static final RequestMatcher INDIVIDUAL_RESTAURANT_ROUTES = get("/api/v1/restaurant/*");
  public static final RequestMatcher NEAR_BY_RESTAURANTS_ROUTE =
      get("/api/v1/restaurant/near-by/**");
  public static final RequestMatcher POPULAR_RESTAURANTS_ROUTE = get("/api/v1/restaurant/popular");
  public static final RequestMatcher POPULAR_LOCATIONS_RESTAURANTS_ROUTE =
      get("/api/v1/restaurant/popular-locations");

  public static final RequestMatcher CREATE_USER_ROUTE = post("/api/v1/users");

  public static final RequestMatcher ROUTES =
      any(
          PUBLIC,
          BOOTSTRAP,
          LOGIN_ROUTE,
          LOGOUT_ROUTE,
          CITY_API_ROUTES,
          CUISINE_API_ROUTES,
          ALL_RESTAURANTS_ROUTE,
          INDIVIDUAL_RESTAURANT_ROUTES,
          NEAR_BY_RESTAURANTS_ROUTE,
          POPULAR_RESTAURANTS_ROUTE,
          POPULAR_LOCATIONS_RESTAURANTS_ROUTE,
          CREATE_USER_ROUTE);
}
