package com.atlantbh.devdays.demo.abh.restaurants.service.support.users;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Special user with privileges to reset owners password.
 *
 * @author Kenan Klisura
 */
public final class PasswordResetUser implements UserDetails {
  /** The constant INSTANCE. */
  public static final PasswordResetUser INSTANCE = new PasswordResetUser();

  /** Instantiates a new user. */
  private PasswordResetUser() {
    // Empty ctor.
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return null;
  }

  @Override
  public String getUsername() {
    return null;
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }
}
