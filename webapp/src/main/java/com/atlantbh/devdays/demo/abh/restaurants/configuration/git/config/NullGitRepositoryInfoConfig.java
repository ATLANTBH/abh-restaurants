package com.atlantbh.devdays.demo.abh.restaurants.configuration.git.config;

/**
 * Null git repo info config.
 *
 * @author Kenan Klisura
 */
public final class NullGitRepositoryInfoConfig implements GitRepositoryInfoConfig {
  public static final GitRepositoryInfoConfig INSTANCE = new NullGitRepositoryInfoConfig();

  private NullGitRepositoryInfoConfig() {
    // Empty ctor.
  }
}
