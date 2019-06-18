package com.atlantbh.devdays.demo.abh.restaurants.configuration.security.routes;

import org.springframework.security.web.util.matcher.RequestMatcher;

import static com.atlantbh.devdays.demo.abh.restaurants.configuration.security.RouteUtils.*;

/**
 * For these routes user should be logged-in.
 *
 * @author Kenan Klisura
 */
public class UserProtectedRoutes {
    public static final RequestMatcher MY_RESERVATIONS_ROUTE = get("/api/v1/reservation/my");
    public static final RequestMatcher CONFIRM_RESERVATIONS_ROUTE = put("/api/v1/reservation/\\d+/confirm");

    public static final RequestMatcher ROUTES =
            any(MY_RESERVATIONS_ROUTE, CONFIRM_RESERVATIONS_ROUTE);
}
