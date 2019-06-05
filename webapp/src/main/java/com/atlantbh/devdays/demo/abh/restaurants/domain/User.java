package com.atlantbh.devdays.demo.abh.restaurants.domain;

import com.atlantbh.devdays.demo.abh.restaurants.utils.persistence.Constraint;
import com.atlantbh.devdays.demo.abh.restaurants.utils.persistence.Constraints;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * User model.
 *
 * @author Kenan Klisura
 */
@Entity
@Table(name = "users")
@Constraints(
  list = {
    @Constraint(name = "users_email_uq", errorMessage = "User with given email already exists."),
  }
)
public class User {

  /** The constant ENTITY_NAME. */
  public static final String ENTITY_NAME = "User";

  @Id @GeneratedValue private Long id;

  @NotNull
  @Size(max = 255)
  @Column(name = "first_name")
  private String firstName;

  @NotNull
  @Size(max = 255)
  @Column(name = "last_name")
  private String lastName;

  @NotNull
  @Size(max = 512)
  @Email
  private String email;

  @JsonIgnore private String password;

  @Column(name = "is_admin")
  private boolean admin = false;

  @Column(name = "created_at", updatable = false, insertable = false)
  private Date createdAt;

  @Column(name = "updated_at", updatable = false, insertable = false)
  private Date updatedAt;

  /**
   * Gets id.
   *
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Gets first name.
   *
   * @return the first name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets first name.
   *
   * @param firstName the first name
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Gets last name.
   *
   * @return the last name
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets last name.
   *
   * @param lastName the last name
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Gets email.
   *
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets email.
   *
   * @param email the email
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Gets password.
   *
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets password.
   *
   * @param password the password
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Gets created at.
   *
   * @return the created at
   */
  public Date getCreatedAt() {
    return createdAt;
  }

  /**
   * Gets updated at.
   *
   * @return the updated at
   */
  public Date getUpdatedAt() {
    return updatedAt;
  }

  public boolean isAdmin() {
    return admin;
  }

  public void setAdmin(boolean admin) {
    this.admin = admin;
  }
}
