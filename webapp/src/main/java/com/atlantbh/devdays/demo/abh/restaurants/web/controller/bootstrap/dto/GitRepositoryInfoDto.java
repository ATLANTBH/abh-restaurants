package com.atlantbh.devdays.demo.abh.restaurants.web.controller.bootstrap.dto;

import com.atlantbh.devdays.demo.abh.restaurants.configuration.git.config.GitRepositoryInfoConfig;

/**
 * Git repository info dto.
 *
 * @author Kenan Klisura
 */
public class GitRepositoryInfoDto {
  private String commitId;
  private String commitIdAbbrev;
  private String buildVersion;

  /**
   * Instantiates a new Git repository info dto.
   *
   * @param gitRepositoryConfig the git repository config
   */
  public GitRepositoryInfoDto(GitRepositoryInfoConfig gitRepositoryConfig) {
    this.commitId = gitRepositoryConfig.getCommitId();
    this.commitIdAbbrev = gitRepositoryConfig.getCommitIdAbbrev();
    this.buildVersion = gitRepositoryConfig.getBuildVersion();
  }

  /**
   * Gets commit id.
   *
   * @return the commit id
   */
  public String getCommitId() {
    return commitId;
  }

  /**
   * Sets commit id.
   *
   * @param commitId the commit id
   */
  public void setCommitId(String commitId) {
    this.commitId = commitId;
  }

  /**
   * Gets commit id abbrev.
   *
   * @return the commit id abbrev
   */
  public String getCommitIdAbbrev() {
    return commitIdAbbrev;
  }

  /**
   * Sets commit id abbrev.
   *
   * @param commitIdAbbrev the commit id abbrev
   */
  public void setCommitIdAbbrev(String commitIdAbbrev) {
    this.commitIdAbbrev = commitIdAbbrev;
  }

  /**
   * Gets build version.
   *
   * @return the build version
   */
  public String getBuildVersion() {
    return buildVersion;
  }

  /**
   * Sets build version.
   *
   * @param buildVersion the build version
   */
  public void setBuildVersion(String buildVersion) {
    this.buildVersion = buildVersion;
  }
}
