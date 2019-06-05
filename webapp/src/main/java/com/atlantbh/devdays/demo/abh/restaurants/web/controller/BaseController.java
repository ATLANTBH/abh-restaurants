package com.atlantbh.devdays.demo.abh.restaurants.web.controller;

import com.atlantbh.devdays.demo.abh.restaurants.repository.BaseCrudRepository;
import com.atlantbh.devdays.demo.abh.restaurants.service.BaseCrudService;
import com.atlantbh.devdays.demo.abh.restaurants.service.exceptions.EntityNotFoundServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Base controller for other controllers.
 *
 * @author Kenan Klisura
 */
public class BaseController<T extends BaseCrudService<M, Long, R>, R extends BaseCrudRepository<M, Long>, M> {
    protected T service;

    @Autowired
    public BaseController(T service) {
        this.service = service;
    }

    /**
     * Returns all items.
     *
     * @return All items.
     */
    @Transactional(readOnly = true)
    @GetMapping
    public Iterable<M> findAll() {
        return service.findAll();
    }

    /**
     * Fetches the model with specified id.
     *
     * @param id Id of an entity.
     * @return Model.
     * @throws EntityNotFoundServiceException If no entity found.
     */
    @Transactional(readOnly = true)
    @GetMapping("{id}")
    public M get(@PathVariable("id") Long id) throws EntityNotFoundServiceException {
        return service.get(id);
    }

    /**
     * Deletes an entity.
     *
     * @param id Id of a specific entity.
     */
    @Transactional
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}

