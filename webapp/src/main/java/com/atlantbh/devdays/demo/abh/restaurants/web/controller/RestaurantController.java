package com.atlantbh.devdays.demo.abh.restaurants.web.controller;

import com.atlantbh.devdays.demo.abh.restaurants.domain.Reservation;
import com.atlantbh.devdays.demo.abh.restaurants.domain.Restaurant;
import com.atlantbh.devdays.demo.abh.restaurants.repository.RestaurantRepository;
import com.atlantbh.devdays.demo.abh.restaurants.service.ReservationService;
import com.atlantbh.devdays.demo.abh.restaurants.service.RestaurantService;
import com.atlantbh.devdays.demo.abh.restaurants.service.exceptions.EntityNotFoundServiceException;
import com.atlantbh.devdays.demo.abh.restaurants.service.exceptions.NoTablesAvailableServiceException;
import com.atlantbh.devdays.demo.abh.restaurants.service.requests.ReservationRequest;
import com.atlantbh.devdays.demo.abh.restaurants.service.requests.RestaurantRequest;
import com.atlantbh.devdays.demo.abh.restaurants.service.responses.ReservationInquiryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Restaurant controller.
 *
 * @author Kenan Klisura
 */
@RestController
@RequestMapping(path = "/api/v1/restaurant")
public class RestaurantController extends BaseController<RestaurantService, RestaurantRepository, Restaurant> {
    private ReservationService reservationService;

    @Autowired
    public RestaurantController(RestaurantService service, ReservationService reservationService) {
        super(service);
        this.reservationService = reservationService;
    }

    @Transactional(readOnly = true)
    @PostMapping("/{id}/reservation-inquiry")
    public ReservationInquiryResponse reservationInquiry(@PathVariable("id") Long id, @RequestBody ReservationRequest request) {
        return reservationService.reservationInquiry(id, request);
    }

    @Transactional
    @PostMapping("/{id}/reservation")
    public Reservation reservation(@PathVariable("id") Long id, @RequestBody ReservationRequest request) throws NoTablesAvailableServiceException {
        return reservationService.create(id, request);
    }

    @Transactional
    @PostMapping
    public Restaurant create(@RequestBody RestaurantRequest request) throws EntityNotFoundServiceException {
        return service.create(request);
    }

    @Transactional
    @PutMapping("{id}")
    public Restaurant update(@PathVariable("id") Long id, @RequestBody RestaurantRequest request) throws EntityNotFoundServiceException {
        return service.update(id, request);
    }
}
