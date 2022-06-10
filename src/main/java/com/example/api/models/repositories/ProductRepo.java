package com.example.api.models.repositories;

import java.util.List;

import com.example.api.models.entities.Products;

import org.springframework.data.repository.CrudRepository;

/**
 * interface ini secara default memiliki
 * method crud karnam meng extend class CrudRepository 
 * maka interface ProductRepo akan mewarisi method2 yang di miliki oleh class CrudRepository
 */
public interface ProductRepo extends CrudRepository<Products,Long> {

    /**
     * ini adalah abstraction
     * costom repository
     * jpa secara default tau maksud 
     * method ini dari nama method
     * @param name
     * @return
     */
    List<Products> findByNameContains(String name);
}
