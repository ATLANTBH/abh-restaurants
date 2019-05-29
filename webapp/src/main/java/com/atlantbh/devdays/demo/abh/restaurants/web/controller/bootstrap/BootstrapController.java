package com.atlantbh.devdays.demo.abh.restaurants.web.controller.bootstrap;

import com.atlantbh.devdays.demo.abh.restaurants.configuration.git.config.GitRepositoryInfoConfig;
import com.atlantbh.devdays.demo.abh.restaurants.service.exceptions.EntityNotFoundServiceException;
import com.atlantbh.devdays.demo.abh.restaurants.service.users.UsersService;
import com.atlantbh.devdays.demo.abh.restaurants.web.controller.bootstrap.dto.BootstrapDto;
import com.atlantbh.devdays.demo.abh.restaurants.web.controller.bootstrap.dto.CurrentUserDto;
import com.atlantbh.devdays.demo.abh.restaurants.web.controller.bootstrap.dto.GitRepositoryInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Bootstrap controller.
 *
 * @author Kenan Klisura
 */
@RestController
@RequestMapping("/api/v1/bootstrap")
public class BootstrapController {
  private UsersService usersService;
  private GitRepositoryInfoConfig gitRepositoryConfig;

  /**
   * Sets git repository config.
   *
   * @param gitRepositoryConfig the git repository config
   */
  @Autowired
  public void setGitRepositoryConfig(GitRepositoryInfoConfig gitRepositoryConfig) {
    this.gitRepositoryConfig = gitRepositoryConfig;
  }

  /**
   * Sets users service.
   *
   * @param usersService Users service.
   */
  @Autowired
  public void setUsersService(UsersService usersService) {
    this.usersService = usersService;
  }

  /**
   * Bootstrap API returns all items needed for bootstrapping SPA web application ie. Ember.
   *
   * @param userDetails the user details
   * @return Bootstrap dto.
   * @throws EntityNotFoundServiceException the entity not found service exception
   */
  @RequestMapping(method = RequestMethod.GET)
  public BootstrapDto bootstrap(@AuthenticationPrincipal UserDetails userDetails)
      throws EntityNotFoundServiceException {
    BootstrapDto result = new BootstrapDto();
    result.setBuildInfo(new GitRepositoryInfoDto(gitRepositoryConfig));
    result.setCurrentUser(new CurrentUserDto(usersService.get(userDetails)));
    return result;
  }
}
