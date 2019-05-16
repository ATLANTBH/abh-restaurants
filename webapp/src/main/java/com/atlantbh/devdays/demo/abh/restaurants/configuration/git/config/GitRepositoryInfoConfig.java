package com.atlantbh.devdays.demo.abh.restaurants.configuration.git.config;

/**
 * Git repository config.
 *
 * @author Kenan Klisura
 */
public interface GitRepositoryInfoConfig {
  /**
   * Gets commit id.
   *
   * @return the commit id
   */
  default String getCommitId() {
    return null;
  }

  /**
   * Gets commit id abbrev.
   *
   * @return the commit id abbrev
   */
  default String getCommitIdAbbrev() {
    return null;
  }

  /**
   * Gets build version.
   *
   * @return the build version
   */
  default String getBuildVersion() {
    return null;
  }
}
