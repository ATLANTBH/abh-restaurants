package com.atlantbh.devdays.demo.abh.restaurants.service.email.impl.config;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMockRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Email links config test.
 *
 * @author Kenan Klisura
 */
@RunWith(EasyMockRunner.class)
public class EmailLinksConfigTest {
  @Test
  public void getResetPasswordLink() {
    EmailLinksConfig config = new EmailLinksConfig();
    config.setHost("http://example.com");
    config.setResetPasswordLink("${host}/user/passwords/reset?token=${token}");

    assertEquals("${host}/user/passwords/reset?token=${token}", config.getResetPasswordLink());
    assertEquals(
        "http://example.com/user/passwords/reset?token=123", config.getResetPasswordLink("123"));
  }

  @Test
  public void getUserConfirmationLink() {
    EmailLinksConfig config = new EmailLinksConfig();
    config.setHost("http://example.com");
    config.setUserActivationLink("${host}/user/confirmation?token=${token}&user=${user}");

    assertEquals(
        "${host}/user/confirmation?token=${token}&user=${user}", config.getUserActivationLink());
    assertEquals(
        "http://example.com/user/confirmation?token=123&user=456",
        config.getUserConfirmationLink(456L, "123"));
  }
}
