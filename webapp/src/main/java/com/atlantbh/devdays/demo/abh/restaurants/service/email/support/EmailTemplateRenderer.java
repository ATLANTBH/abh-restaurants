package com.atlantbh.devdays.demo.abh.restaurants.service.email.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

/**
 * Email template renderer is used to render email templates. This implementation uses thymeleaf for
 * rendering templates. All templates reside in `src/main/java/resources/email/templates` dir.
 *
 * <p>EmailTemplateType bounds template type to the template resource name.
 *
 * @author Kenan Klisura
 */
@Component
public class EmailTemplateRenderer {
  /** Email template type. */
  public enum EmailTemplateType {
    /** Reset password template. */
    RESET_PASSWORD_TEMPLATE("reset-password"),
    USER_PASSWORD_CHANGED_TEMPLATE("user-password-changed"),
    USER_ACTIVATION_EMAIL("user-activation");

    private String resourceName;

    EmailTemplateType(String resourceName) {
      this.resourceName = resourceName;
    }
  }

  private SpringTemplateEngine emailTemplateEngine;

  /**
   * Instantiates a new email template renderer.
   *
   * @param emailTemplateEngine Templating engine.
   */
  @Autowired
  public EmailTemplateRenderer(SpringTemplateEngine emailTemplateEngine) {
    this.emailTemplateEngine = emailTemplateEngine;
  }

  /**
   * Renders template into a string.
   *
   * @param emailTemplateType Type of an email template.
   * @param context Template context.
   * @return Rendered email string.
   */
  public String renderTemplate(EmailTemplateType emailTemplateType, EmailTemplateContext context) {
    Context templateContext = new Context(context.getLocale(), context.getParams());

    return emailTemplateEngine.process(emailTemplateType.resourceName, templateContext);
  }
}
