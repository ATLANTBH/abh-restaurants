package com.atlantbh.devdays.demo.abh.restaurants.web.controller.auth;

import com.atlantbh.devdays.demo.abh.restaurants.service.users.UsersService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Simple user authorization just verifies a user with valid session exists.
 *
 * @author Kenan Klisura
 */
@Component
public class SimpleUserAuthorizationFilter extends OncePerRequestFilter {
  private UsersService usersService;

  private SecurityContextLogoutHandler securityContextLogoutHandler =
      new SecurityContextLogoutHandler();

  @Autowired
  public void setUsersService(UsersService usersService) {
    this.usersService = usersService;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    Authentication existingAuth = SecurityContextHolder.getContext().getAuthentication();
    if (existingAuth != null && existingAuth.isAuthenticated()) {
      UserDetails userDetails = (UserDetails) existingAuth.getPrincipal();

      if (!usersService.verifyUserExists(userDetails.getUsername())) {
        securityContextLogoutHandler.logout(request, response, null);
        response.sendError(
            HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
        return;
      }
    }

    filterChain.doFilter(request, response);
  }
}
