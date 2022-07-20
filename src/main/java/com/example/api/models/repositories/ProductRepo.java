package com.example.api.models.repositories;

import java.util.List;
import java.util.Set;

import javax.websocket.server.PathParam;

import com.example.api.models.entities.Products;
import com.example.api.models.entities.Suplier;

import org.springframework.data.jpa.repository.Query;
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

    /**
     * disini kita bukan menggunkan SQL tapi JPAQL 
     * JPAQL itu mirip dengan SQL
     * p => adalah sebagai alias saja kita bebas menggunakan huruf atau kata 
     * p.name => berarti kita mengambil suatu data dari Entity Product yang propertinya namanya name
     * :name => ini parameter/placeholder saja ini kita bebas mau namain apa
     * @PathParam("name") => parameter name pada param harus sama dengan placeholder/parameter di query nya
     */
    @Query("SELECT p FROM Products p WHERE p.name = :name")
    public Products findProductByName(@PathParam("name") String name);

    @Query("SELECT p FROM Products p WHERE p.name LIKE :name")
    public Set<Products> findByNameLike(@PathParam("name") String name);

    @Query("SELECT p FROM Products p WHERE p.category.id = :categoryId")
    public List<Products> findProductByCategoryId(@PathParam("categoryId") long categoryId);

    @Query("SELECT p FROM Products p WHERE :suplier MEMBER OF p.suplier")
    public List<Products> findProductBySupplier(Suplier suplier);
}
