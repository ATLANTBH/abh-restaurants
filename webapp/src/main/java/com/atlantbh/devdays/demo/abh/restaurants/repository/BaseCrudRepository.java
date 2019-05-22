package com.atlantbh.devdays.demo.abh.restaurants.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Base repository.
 *
 * @author Kenan Klisura
 */
@Repository
public interface BaseCrudRepository<T, ID> extends CrudRepository<T, ID>, JpaSpecificationExecutor<T> {}
