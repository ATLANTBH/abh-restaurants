package com.atlantbh.devdays.demo.abh.restaurants.domain;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Kenan Klisura on 2019-05-22.
 *
 * @author Kenan Klisura
 */
@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "table_id", referencedColumnName = "id")
    private RestaurantTable table;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "reserved_on")
    private Date reservedOn;

    @Column(name = "is_confirmed")
    private Boolean isConfirmed = false;

    // TODO(kklisura): DTO?
    @Transient
    private String date;

    @Transient
    private String time;

    /**
     * Instantiates a new Reservation.
     */
    public Reservation() { }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() { return id; }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Gets table.
     *
     * @return the table
     */
    public RestaurantTable getTable() { return table; }

    /**
     * Sets table.
     *
     * @param table the table
     */
    public void setTable(RestaurantTable table) { this.table = table; }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() { return user; }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) { this.user = user; }

    /**
     * Gets start time.
     *
     * @return the start time
     */
    public Date getStartTime() { return startTime; }

    /**
     * Sets start time.
     *
     * @param startTime the start time
     */
    public void setStartTime(Long startTime) { this.startTime = new Date(startTime); }

    /**
     * Gets reserved on.
     *
     * @return the reserved on
     */
    public Date getReservedOn() { return reservedOn; }

    /**
     * Sets reserved on.
     *
     * @param reservedOn the reserved on
     */
    public void setReservedOn(Long reservedOn) { this.reservedOn = new Date(reservedOn); }

    /**
     * Gets confirmed.
     *
     * @return the confirmed
     */
    public Boolean getConfirmed() { return isConfirmed; }

    /**
     * Sets confirmed.
     *
     * @param confirmed the confirmed
     */
    public void setConfirmed(Boolean confirmed) { isConfirmed = confirmed; }

    // TODO(kklisura): Remove this.
//    /**
//     * Gets date.
//     *
//     * @return the date
//     */
//    public String getDate() {
//        DateFormat df = new SimpleDateFormat(DATE_PATTERN);
//        return df.format(this.startTime.getTime());
//    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(String date) { this.date = date; }

    // TODO(kklisura): Remove this.
//    /**
//     * Gets time.
//     *
//     * @return the time
//     */
//    public String getTime() {
//        DateFormat df = new SimpleDateFormat(TIME_PATTERN);
//        return df.format(this.startTime.getTime());
//    }

    /**
     * Sets time.
     *
     * @param time the time
     */
    public void setTime(String time) { this.time = time; }
}
