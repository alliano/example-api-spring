package com.example.api.services.coba;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.models.entities.tryning.Murit;
import com.example.api.models.repositories.coba.MuritRepository;

@Service
public class MuritService {
   
   @Autowired
   private MuritRepository muritRepository;

   public Murit saveMurit(Murit murit){
      if(murit.getNama() == "")return null;
      else
      return muritRepository.save(murit);
   }

   public Iterable<Murit> findAll(){
      return muritRepository.findAll();
   }

   public Murit findOne(long id){
      Murit murit = muritRepository.findById(id).orElse(null);
      return murit;
   }

   public String destroy(long id){
      muritRepository.deleteById(id);
      return "success delete";
   }

}
