package com.atlantbh.devdays.demo.abh.restaurants.configuration.security;

import com.atlantbh.devdays.demo.abh.restaurants.configuration.security.routes.AdminRoutes;
import com.atlantbh.devdays.demo.abh.restaurants.configuration.security.routes.AllowedRoutes;
import com.atlantbh.devdays.demo.abh.restaurants.service.users.UsersService;
import com.atlantbh.devdays.demo.abh.restaurants.web.controller.auth.AuthenticationFilter;
import com.atlantbh.devdays.demo.abh.restaurants.web.controller.auth.SimpleUserAuthorizationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Web security configuration.
 *
 * <p>This configuration makes available all resources under /public/ as 'public' meaning no auth is
 * required for accessing those resources.
 *
 * <p>/api/v1/{login,users,users/request-password-reset,users/password-reset} are public as well. No
 * need for session to access those paths (only POST requests).
 *
 * @author Kenan Klisura
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
  private UsersService usersService;

  private PasswordEncoder passwordEncoder;

  private ObjectMapper objectMapper;

  private SimpleUserAuthorizationFilter authorizationFilter;

  @Autowired
  public void setUsersService(UsersService usersService) {
    this.usersService = usersService;
  }

  @Autowired
  public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Autowired
  public void setObjectMapper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Autowired
  public void setAuthorizationFilter(SimpleUserAuthorizationFilter authorizationFilter) {
    this.authorizationFilter = authorizationFilter;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.requestCache()
        .disable()
        .authorizeRequests()
        .requestMatchers(AllowedRoutes.ROUTES)
        .permitAll()
        .requestMatchers(AdminRoutes.ROUTES)
        .hasRole(Roles.ADMIN.name())
        .anyRequest()
        .authenticated()
        .and()
        .addFilterBefore(
            new AuthenticationFilter(
                usersService, objectMapper, AllowedRoutes.LOGIN_ROUTE, authenticationManager()),
            UsernamePasswordAuthenticationFilter.class)
        .addFilterAfter(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
        .addFilter(logoutFilter())
        .csrf()
        .disable()
        .formLogin()
        .disable()
        .httpBasic()
        .disable();
  }

  @Override
  public void configure(AuthenticationManagerBuilder authBuilder) throws Exception {
    authBuilder.userDetailsService(usersService).passwordEncoder(passwordEncoder);
  }

  private LogoutFilter logoutFilter() {
    LogoutSuccessHandler logoutSuccessHandler = new NullLogoutSuccessHandler();
    LogoutFilter logoutFilter =
        new LogoutFilter(logoutSuccessHandler, new SecurityContextLogoutHandler());
    logoutFilter.setLogoutRequestMatcher(AllowedRoutes.LOGOUT_ROUTE);
    return logoutFilter;
  }

  /** On successful logout - do nothing. */
  private static class NullLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(
        HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
      response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
  }
}
