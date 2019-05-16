package com.atlantbh.devdays.demo.abh.restaurants.configuration.security;

import com.atlantbh.devdays.demo.abh.restaurants.service.users.UsersService;
import com.atlantbh.devdays.demo.abh.restaurants.web.controller.auth.AuthenticationFilter;
import com.atlantbh.devdays.demo.abh.restaurants.web.controller.auth.SimpleUserAuthorizationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

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
  // These requests are publicly accessible and are not required to have session
  private static final RequestMatcher LOGIN_REQUEST_MATCHER = post("/api/v1/login");
  private static final RequestMatcher CREATE_USER_MATCHER = post("/api/v1/users");
  private static final RequestMatcher PASSWORD_RESET_MATCHER = post("/api/v1/users/password-reset");
  private static final RequestMatcher REQUEST_PASSWORD_RESET_MATCHER =
      post("/api/v1/users/request-password-reset");

  private static final String LOGOUT_PATH = "/api/v1/logout";

  /** List of request that do not need auth. */
  private static final RequestMatcher NON_AUTH_URLS_REQUEST_MATCHER =
      new OrRequestMatcher(
          CREATE_USER_MATCHER,
          LOGIN_REQUEST_MATCHER,
          REQUEST_PASSWORD_RESET_MATCHER,
          PASSWORD_RESET_MATCHER);

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
        .requestMatchers(NON_AUTH_URLS_REQUEST_MATCHER)
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .addFilterBefore(
            new AuthenticationFilter(
                usersService, objectMapper, LOGIN_REQUEST_MATCHER, authenticationManager()),
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

  /**
   * Matches a post request on a given path.
   *
   * @param path Path
   * @return POST request matcher.
   */
  private static RequestMatcher post(String path) {
    return new AntPathRequestMatcher(path, HttpMethod.POST.name());
  }

  private LogoutFilter logoutFilter() {
    LogoutSuccessHandler logoutSuccessHandler = new NullLogoutSuccessHandler();
    LogoutFilter logoutFilter =
        new LogoutFilter(logoutSuccessHandler, new SecurityContextLogoutHandler());
    logoutFilter.setLogoutRequestMatcher(post(LOGOUT_PATH));
    return logoutFilter;
  }

  /** On successful logout - do nothing. */
  private static class NullLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(
        HttpServletRequest request, HttpServletResponse response, Authentication authentication) {}
  }
}
