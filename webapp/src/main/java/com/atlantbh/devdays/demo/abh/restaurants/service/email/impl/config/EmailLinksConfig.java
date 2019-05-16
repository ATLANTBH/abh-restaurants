package com.atlantbh.devdays.demo.abh.restaurants.service.email.impl.config;

import java.util.HashMap;

import com.atlantbh.devdays.demo.abh.restaurants.utils.data.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Email links configuration.
 *
 * @author Kenan Klisura
 */
@Component
@ConfigurationProperties(prefix = "app.mail.links", ignoreInvalidFields = true)
public class EmailLinksConfig {
  private static final String USER_PLACEHOLDER = "user";
  private static final String TOKEN_PLACEHOLDER = "token";
  private static final String HOST_PLACEHOLDER = "host";

  private String host;
  private String userActivationLink;
  private String resetPasswordLink;

  /**
   * Gets host.
   *
   * @return the host
   */
  public String getHost() {
    return host;
  }

  /**
   * Sets host.
   *
   * @param host the host
   */
  public void setHost(String host) {
    this.host = host;
  }

  /**
   * Gets user confirmation link.
   *
   * @return the user confirmation link
   */
  public String getUserActivationLink() {
    return userActivationLink;
  }

  /**
   * Sets user confirmation link.
   *
   * @param userActivationLink the user confirmation link
   */
  public void setUserActivationLink(String userActivationLink) {
    this.userActivationLink = userActivationLink;
  }

  /**
   * Gets reset password link.
   *
   * @return the reset password link
   */
  public String getResetPasswordLink() {
    return resetPasswordLink;
  }

  /**
   * Sets reset password link.
   *
   * @param resetPasswordLink the reset password link
   */
  public void setResetPasswordLink(String resetPasswordLink) {
    this.resetPasswordLink = resetPasswordLink;
  }

  /**
   * Returns a reset password link with resolved placeholders.
   *
   * @param token Token.
   * @return Resolved link.
   */
  public String getResetPasswordLink(String token) {
    return StringUtils.resolvePlaceholders(
        resetPasswordLink,
        new HashMap<String, String>() {
          {
            put(TOKEN_PLACEHOLDER, token);
            put(HOST_PLACEHOLDER, host);
          }
        });
  }

  /**
   * Returns a user confirmation link with resolved placeholders.
   *
   * @param userId User id.
   * @param token Token.
   * @return Resolved link.
   */
  public String getUserConfirmationLink(Long userId, String token) {
    return StringUtils.resolvePlaceholders(
        userActivationLink,
        new HashMap<String, String>() {
          {
            put(TOKEN_PLACEHOLDER, token);
            put(USER_PLACEHOLDER, userId.toString());
            put(HOST_PLACEHOLDER, host);
          }
        });
  }
}
