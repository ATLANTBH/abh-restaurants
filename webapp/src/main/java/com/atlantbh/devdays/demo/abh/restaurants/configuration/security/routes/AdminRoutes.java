package com.atlantbh.devdays.demo.abh.restaurants.configuration.security.routes;

import static com.atlantbh.devdays.demo.abh.restaurants.configuration.security.RouteUtils.any;

import com.atlantbh.devdays.demo.abh.restaurants.configuration.security.RouteUtils;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * These are admin routes for the the application. They need authenticated admin user to be present.
 *
 * <p>Even though some of the enums here are not used directly, they are still being used indirectly
 * via {@link AdminRoutes#ROUTES}, so make sure you don't accidentally remove something.
 *
 * @author Kenan Klisura
 */
public final class AdminRoutes {

  public static final RequestMatcher ROUTES = any(RouteUtils.NONE_ROUTE);
}
