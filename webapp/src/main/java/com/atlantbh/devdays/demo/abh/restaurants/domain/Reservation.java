package com.atlantbh.devdays.demo.abh.restaurants.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.*;

/** Reservation domain model. */
@Entity
@Table(name = "reservation")
public class Reservation {
  @Id
  @GeneratedValue
  @Column(name = "id")
  private Long id;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "table_id")
  private RestaurantTable table;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "start_time")
  private Date startTime;

  @Column(name = "reserved_on")
  private Date reservedOn;

  @Column(name = "is_confirmed")
  private Boolean confirmed = false;

  @Column(name = "canceled")
  private boolean canceled;

  @Column(name = "canceled_at")
  private Date canceledAt;

  @Column(name = "created_at", updatable = false, insertable = false)
  private Date createdAt;

  @Column(name = "updated_at", updatable = false, insertable = false)
  private Date updatedAt;

  /** Instantiates a new Reservation. */
  public Reservation() {}

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
   * Gets table.
   *
   * @return the table
   */
  public RestaurantTable getTable() {
    return table;
  }

  /**
   * Sets table.
   *
   * @param table the table
   */
  public void setTable(RestaurantTable table) {
    this.table = table;
  }

  /**
   * Gets user.
   *
   * @return the user
   */
  public User getUser() {
    return user;
  }

  /**
   * Sets user.
   *
   * @param user the user
   */
  public void setUser(User user) {
    this.user = user;
  }

  /**
   * Gets start time.
   *
   * @return the start time
   */
  public Date getStartTime() {
    return startTime;
  }

  /**
   * Sets start time.
   *
   * @param startTime the start time
   */
  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  /**
   * Gets reserved on.
   *
   * @return the reserved on
   */
  public Date getReservedOn() {
    return reservedOn;
  }

  /**
   * Sets reserved on.
   *
   * @param reservedOn the reserved on
   */
  public void setReservedOn(Date reservedOn) {
    this.reservedOn = reservedOn;
  }

  /**
   * Gets confirmed.
   *
   * @return the confirmed
   */
  public Boolean getConfirmed() {
    return confirmed;
  }

  /**
   * Sets confirmed.
   *
   * @param confirmed the confirmed
   */
  public void setConfirmed(Boolean confirmed) {
    this.confirmed = confirmed;
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

  public boolean isCanceled() {
    return canceled;
  }

  public void setCanceled(boolean canceled) {
    this.canceled = canceled;
  }

  public void setCanceledAt(Date canceledAt) {
    this.canceledAt = canceledAt;
  }
}
