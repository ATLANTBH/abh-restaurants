package com.atlantbh.devdays.demo.abh.restaurants.configuration.git;

import com.atlantbh.devdays.demo.abh.restaurants.configuration.git.config.GitRepositoryInfoConfig;
import com.atlantbh.devdays.demo.abh.restaurants.configuration.git.config.NullGitRepositoryInfoConfig;
import com.atlantbh.devdays.demo.abh.restaurants.configuration.git.config.PropertySourceGitRepositoryInfoConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Git repository info configuration. This is used to source git info (commit, branch) to be exposed
 * via API.
 *
 * @author Kenan Klisura
 */
@Configuration
@PropertySource(value = "classpath:git.properties", ignoreResourceNotFound = true)
public class GitRepositoryInfoConfiguration {
  @Bean
  @ConditionalOnProperty("git.commit.id")
  public GitRepositoryInfoConfig gitRepositoryInfoConfig() {
    return new PropertySourceGitRepositoryInfoConfig();
  }

  @Bean
  @ConditionalOnMissingBean
  public GitRepositoryInfoConfig nullGitRepositoryInfoConfig() {
    return NullGitRepositoryInfoConfig.INSTANCE;
  }
}
