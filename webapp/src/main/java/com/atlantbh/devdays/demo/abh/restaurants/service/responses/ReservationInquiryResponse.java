package com.atlantbh.devdays.demo.abh.restaurants.service.responses;

import com.atlantbh.devdays.demo.abh.restaurants.service.requests.ReservationRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by Kenan Klisura on 2019-05-22.
 *
 * @author Kenan Klisura
 */
public class ReservationInquiryResponse {
  private ReservationRequest inquiry;

  private long numberOfTablesLeft;
  private long numberOfReservationsToday;

  private List<Date> timeSuggestions;

  public ReservationRequest getInquiry() {
    return inquiry;
  }

  public long getNumberOfTablesLeft() {
    return numberOfTablesLeft;
  }

  public long getNumberOfReservationsToday() {
    return numberOfReservationsToday;
  }

  public List<Date> getTimeSuggestions() {
    return timeSuggestions;
  }

  public static ReservationInquiryResponseBuilder builder() {
    return new ReservationInquiryResponseBuilder();
  }

  public static class ReservationInquiryResponseBuilder {
    private ReservationInquiryResponse instance;

    private ReservationInquiryResponseBuilder() {
      this.instance = new ReservationInquiryResponse();
    }

    public ReservationInquiryResponseBuilder withInquiry(ReservationRequest request) {
      this.instance.inquiry = request;
      return this;
    }

    public ReservationInquiryResponseBuilder withNumberOfTablesLeft(long count) {
      this.instance.numberOfTablesLeft = count;
      return this;
    }

    public ReservationInquiryResponseBuilder withNumberOfReservationsToday(long count) {
      this.instance.numberOfReservationsToday = count;
      return this;
    }

    public ReservationInquiryResponseBuilder withSuggestedTimes(List<Date> suggestedTimes) {
      this.instance.timeSuggestions = suggestedTimes;
      return this;
    }

    public ReservationInquiryResponse build() {
      ReservationInquiryResponse result = new ReservationInquiryResponse();
      this.instance = new ReservationInquiryResponse();
      return result;
    }
  }
}
