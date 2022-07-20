package com.example.api.models.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.api.models.entities.Suplier;

import java.util.List;

public interface SuplierRepository extends CrudRepository<Suplier,Long> {

   /**
    * JPA Derived query adalah method query untuk database tampa kita devinisikan QL nya 
    * JPA Derived query mengetahui query yang di inginkan berdasarkan nama methodnya untuk info
    * lebih lanjut bisa kunjkungi di dokumentasi https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
    * @param email
    * @return
    */

   public Suplier findByEmail(String email); 

   public List<Suplier> findByNameContaining(String name);

   public List<Suplier> findByNameStartingWith(String prefix);

   public List<Suplier> findByNameContainingOrEmailContaining(String name, String email);

}
