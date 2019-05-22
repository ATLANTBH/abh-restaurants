package com.atlantbh.devdays.demo.abh.restaurants.service;

import com.atlantbh.devdays.demo.abh.restaurants.domain.City;
import com.atlantbh.devdays.demo.abh.restaurants.repository.CityRepository;
import com.atlantbh.devdays.demo.abh.restaurants.service.event.EventBus;
import com.atlantbh.devdays.demo.abh.restaurants.service.exceptions.EntityNotFoundServiceException;
import com.atlantbh.devdays.demo.abh.restaurants.service.requests.CityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * City service manages city-related operations: create, deletion and update.
 *
 * @author Kenan Klisura
 */
@Service
public class CityService extends BaseCrudService<City, Long, CityRepository> {
    private static final String DEFAULT_SORT_PROPERTY = "name";

    /**
     * Instantiates a new city service.
     *
     * @param repository Repository which manages city db interaction.
     * @param eventBus   Event bus.
     */
    @Autowired
    public CityService(CityRepository repository, EventBus eventBus) {
        super(repository, eventBus);
    }

    /**
     * Returns all cities sorted by {@link #DEFAULT_SORT_PROPERTY}.
     *
     * @return All cities sorted.
     */
    @Override
    public Iterable<City> findAll() {
        return super.findAll(DEFAULT_SORT_PROPERTY, Sort.Direction.ASC);
    }

    /**
     * Creates a new city given a city request.
     *
     * @param request Request for creation containing all attributes for creation.
     * @return Newly created city.
     */
    public City create(CityRequest request) {
        City city = new City();

        city.setName(request.getName());
        city.setBounds(request.getBounds());

        return repository.save(city);
    }

    /**
     * Update a city given a city id and city request containing attributes to update.
     *
     * @param id      Id of the existing city.
     * @param request Request for update containing all attributes for creation.
     * @return Updated city.
     * @throws EntityNotFoundServiceException If the existing city is not found.
     */
    public City update(Long id, CityRequest request) throws EntityNotFoundServiceException {
        City city = get(id);

        city.setName(request.getName());
        city.setBounds(request.getBounds());

        return repository.save(city);
    }
}
