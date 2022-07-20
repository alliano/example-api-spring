package com.example.api.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.models.entities.Suplier;
import com.example.api.models.repositories.SuplierRepository;

import java.util.List;


@Service
@Transactional
public class SuplierService {
   
   @Autowired
   private SuplierRepository suplierRepository;

   public Suplier save(Suplier entity){

      return suplierRepository.save(entity);
   }

   public Suplier findById(long id){
      Optional<Suplier> result = suplierRepository.findById(id);
      if(result.isEmpty())return null;
      else
      return result.get();
   }

   public Iterable<Suplier> findAll(){
      return suplierRepository.findAll();
   }

   public String destroyById(long id){
      suplierRepository.deleteById(id);
      return "data success deleted";
   }

   //find SupplierByEmail
   public Suplier findByEmail(String Email){
      return suplierRepository.findByEmail(Email);
   }

   public List<Suplier> findByNameContains(String name){
      return suplierRepository.findByNameContaining(name);
   }

   //findByNameStartingWIth
   public List<Suplier> findByNameStartingWith(String prefix){
      return suplierRepository.findByNameStartingWith(prefix);
   }

   //findBynameContainingOrEmailContaining
   public List<Suplier> findByNameContainingOrEmailContaining(String name, String email){
      return suplierRepository.findByNameContainingOrEmailContaining(name, email);
   }

   //findByNameDesc
 
}
