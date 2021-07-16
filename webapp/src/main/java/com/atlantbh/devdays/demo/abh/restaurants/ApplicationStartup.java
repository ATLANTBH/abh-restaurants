package com.atlantbh.devdays.demo.abh.restaurants;

import com.atlantbh.devdays.demo.abh.restaurants.service.users.AdminUserService;
import com.atlantbh.devdays.demo.abh.restaurants.service.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/** Application startup manages application startup. Creates default users if they do not exist. */
@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {
  private AdminUserService adminUserService;
  private UsersService usersService;

  /**
   * Sets admin user service.
   *
   * @param adminUserService the admin user service
   */
  @Autowired
  public void setAdminUserService(AdminUserService adminUserService) {
    this.adminUserService = adminUserService;
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    adminUserService.createDefault();
    usersService.createDefault();
  }
}
