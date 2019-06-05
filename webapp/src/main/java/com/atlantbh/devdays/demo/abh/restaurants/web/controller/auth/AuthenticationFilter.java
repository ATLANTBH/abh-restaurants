package com.atlantbh.devdays.demo.abh.restaurants.web.controller.auth;

import com.atlantbh.devdays.demo.abh.restaurants.service.exceptions.EntityNotFoundServiceException;
import com.atlantbh.devdays.demo.abh.restaurants.service.users.UsersService;
import com.atlantbh.devdays.demo.abh.restaurants.web.controller.auth.requests.AuthenticationRequest;
import com.atlantbh.devdays.demo.abh.restaurants.web.controller.bootstrap.dto.CurrentUserDto;
import com.atlantbh.devdays.demo.abh.restaurants.web.controller.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Authentication filter parses incoming login request, as {@link AuthenticationRequest} and
 * delegates authentication to {@link AuthenticationManager}.
 *
 * @author Kenan Klisura
 */
public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter {
  private ObjectMapper objectMapper;
  private UsersService usersService;

  public AuthenticationFilter(
      UsersService usersService,
      ObjectMapper objectMapper,
      RequestMatcher loginRequestMatcher,
      AuthenticationManager authenticationManager) {
    super(loginRequestMatcher);
    this.usersService = usersService;
    this.objectMapper = objectMapper;
    setAuthenticationManager(authenticationManager);
    setAuthenticationSuccessHandler(new RespondingAuthenticationSuccessHandler());
    setAuthenticationFailureHandler(new NotAuthorizedAuthenticationFailureHandler());
  }

  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException, IOException {
    AuthenticationRequest authenticationRequest = getAuthenticationRequest(request);

    UsernamePasswordAuthenticationToken authRequest =
        new UsernamePasswordAuthenticationToken(
            authenticationRequest.getEmail(),
            authenticationRequest.getPassword(),
            new ArrayList<>());

    authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));

    return getAuthenticationManager().authenticate(authRequest);
  }

  private AuthenticationRequest getAuthenticationRequest(HttpServletRequest request)
      throws IOException {
    return objectMapper.readValue(request.getInputStream(), AuthenticationRequest.class);
  }

  /** On failure - send response error. */
  private class NotAuthorizedAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(
        HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
        throws IOException {
      final Response error =
          new Response(
              HttpStatus.UNAUTHORIZED, "Bad username and/or password.", request.getServletPath());
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      sendResponse(response, error);
    }
  }

  /** On success - return logged in user info */
  public class RespondingAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws IOException {
      final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      try {
        final CurrentUserDto currentUserDto = new CurrentUserDto(usersService.get(userDetails));
        sendResponse(response, currentUserDto);
      } catch (EntityNotFoundServiceException e) {
        throw new RuntimeException(
            "Unknown user " + userDetails.getUsername() + " (" + userDetails.getPassword() + ")");
      }
    }
  }

  private <T> void sendResponse(HttpServletResponse response, T value) throws IOException {
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    objectMapper.writeValue(response.getOutputStream(), value);
  }
}
