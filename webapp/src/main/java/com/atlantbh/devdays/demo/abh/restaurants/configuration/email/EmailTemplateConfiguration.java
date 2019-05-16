package com.atlantbh.devdays.demo.abh.restaurants.configuration.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 * Email template configuration.
 *
 * @author Kenan Klisura
 */
@Configuration
public class EmailTemplateConfiguration {
  @Bean
  public SpringTemplateEngine emailTemplateEngine() {
    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    templateEngine.addTemplateResolver(emailTemplateResolver());
    return templateEngine;
  }

  private ITemplateResolver emailTemplateResolver() {
    ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
    resolver.setPrefix("email/templates/");
    resolver.setSuffix(".txt");
    resolver.setTemplateMode(TemplateMode.TEXT);
    resolver.setOrder(1);
    resolver.setCacheable(true);
    return resolver;
  }
}
