package com.atlantbh.devdays.demo.abh.restaurants.configuration.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

/**
 * Web mvc configuration.
 *
 * @author Kenan Klisura
 */
@Configuration
@EnableWebMvc
public class WebMvcConfiguration implements WebMvcConfigurer {
  private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {"classpath:/public/"};

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    if (!registry.hasMappingForPattern("/**")) {
      registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }
  }

  @Override
  public void configureViewResolvers(ViewResolverRegistry registry) {
    registry.viewResolver(new ThymeleafViewResolver());
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/")
            .setViewName("forward:/index.html");

    // Map "/word", "/word/word", and "/word/word/word" - except for anything starting with "/api/..." or ending with
    // a file extension like ".js" - to index.html. By doing this, the client receives and routes the url. It also
    // allows client-side URLs to be bookmarked.

    // Single directory level - no need to exclude "api"
    registry.addViewController("/{x:[\\w\\-]+}")
            .setViewName("forward:/index.html");
    // Multi-level directory path, need to exclude "api" on the first part of the path
    registry.addViewController("/{x:^(?!api$).*$}/**/{y:[\\w\\-]+}")
            .setViewName("forward:/index.html");
  }

  //  @Bean
  //  public HandlerMapping defaultServletHandlerMapping() {
  //    return new NullHandlerMapping();
  //  }
  //
  //  public static class NullHandlerMapping extends AbstractHandlerMapping {
  //    @Override
  //    protected Object getHandlerInternal(HttpServletRequest request) {
  //      return null;
  //    }
  //  }
}
