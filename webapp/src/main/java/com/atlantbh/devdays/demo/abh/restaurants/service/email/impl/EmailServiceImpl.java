package com.atlantbh.devdays.demo.abh.restaurants.service.email.impl;

import com.atlantbh.devdays.demo.abh.restaurants.service.email.EmailService;
import com.atlantbh.devdays.demo.abh.restaurants.service.email.impl.config.EmailLinksConfig;
import com.atlantbh.devdays.demo.abh.restaurants.service.email.support.EmailTemplateContext;
import com.atlantbh.devdays.demo.abh.restaurants.service.email.support.EmailTemplateRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Email service implementation. Disabled currently.
 *
 * @author Kenan Klisura
 */
@Service
@Primary
@ConditionalOnBean(MailSender.class)
@Profile("production")
public class EmailServiceImpl implements EmailService {
  private static final String USER_PARAM = "user";
  private static final String LINK_PARAM = "link";

  private JavaMailSender mailSender;
  private EmailTemplateRenderer emailTemplateRenderer;

  private EmailLinksConfig emailLinksConfig;

  /**
   * Instantiates a new email service.
   *
   * @param emailTemplateRenderer Template renderer.
   * @param mailSender Mail sender.
   */
  @Autowired
  public EmailServiceImpl(EmailTemplateRenderer emailTemplateRenderer, JavaMailSender mailSender) {
    this.mailSender = mailSender;
    this.emailTemplateRenderer = emailTemplateRenderer;
  }

  /**
   * Sets the email links configuration.
   *
   * @param emailLinksConfig Configuration.
   */
  @Autowired
  public void setEmailLinksConfig(EmailLinksConfig emailLinksConfig) {
    this.emailLinksConfig = emailLinksConfig;
  }

  @Override
  public void sendRequestResetPasswordEmail(String email, String token) {
    EmailTemplateContext templateContext = new EmailTemplateContext();

    templateContext.put(LINK_PARAM, emailLinksConfig.getResetPasswordLink(token));

    String body =
        emailTemplateRenderer.renderTemplate(
            EmailTemplateRenderer.EmailTemplateType.RESET_PASSWORD_TEMPLATE, templateContext);

    sendEmail(email, "Reset password", body);
  }

  @Override
  public void sendUserPasswordChangedEmail(String email, String fullUserName) {
    EmailTemplateContext templateContext = new EmailTemplateContext();

    templateContext.put(USER_PARAM, fullUserName);

    String body =
        emailTemplateRenderer.renderTemplate(
            EmailTemplateRenderer.EmailTemplateType.USER_PASSWORD_CHANGED_TEMPLATE, templateContext);

    sendEmail(email, "Reset password", body);
  }

  @Override
  public void sendUserActivationEmail(String email, String fullUserName, String token) {
    EmailTemplateContext templateContext = new EmailTemplateContext();

    templateContext.put(USER_PARAM, fullUserName);

    String body =
        emailTemplateRenderer.renderTemplate(
            EmailTemplateRenderer.EmailTemplateType.USER_PASSWORD_CHANGED_TEMPLATE, templateContext);

    sendEmail(email, "Reset password", body);
  }

  private void sendEmail(String email, String subject, String text) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(email);
    message.setSubject(subject);
    message.setText(text);
    mailSender.send(message);
  }
}
