package com.example.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.dto.ResponsData;
import com.example.api.dto.SearchDto;
import com.example.api.dto.SuplierDto;
import com.example.api.models.entities.Suplier;
import com.example.api.services.SuplierService;

import eye2web.modelmapper.ModelMapper;

@RestController
@RequestMapping(path = "api/suplier")
public class SuplierController {
   
   @Autowired
   private SuplierService suplierService;

   @Autowired
   private ModelMapper modelMapper;

   @PostMapping(path = "")
   public ResponseEntity<ResponsData<Suplier>> create(@RequestBody @Valid SuplierDto suplierDto, Errors errors){
      ResponsData<Suplier> responseHttp = new ResponsData<Suplier>();
      if(errors.hasErrors()){
         for (ObjectError error : errors.getAllErrors()) {
            responseHttp.getMessagesList().add(error.getDefaultMessage());
         }
         responseHttp.setPaylooad(null);
         responseHttp.setStatus(false);
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseHttp);
      }else{
         responseHttp.getMessagesList().add("Suplier has successed to add");

         Suplier suplier = modelMapper.map(suplierDto, Suplier.class);

         // Suplier suplier = new Suplier();
         // suplier.setAddress(suplierDto.getAddress());
         // suplier.setName(suplierDto.getName());
         // suplier.setEmail(suplierDto.getEmail());
         responseHttp.setStatus(true);
         responseHttp.setPaylooad(suplierService.save(suplier));
         return ResponseEntity.status(HttpStatus.OK).body(responseHttp);
      }
   }

   @GetMapping(path = "/{id}")
   public Suplier findOne(@PathVariable("id") long id){
      return suplierService.findById(id);
   }

   @GetMapping  
   public Iterable<Suplier> findAll(){
      return suplierService.findAll();
   }

   @DeleteMapping(path = "/{id}")
   public String delete(@PathVariable("id") long id){
      return suplierService.destroyById(id);
   }

   @PutMapping(path = "")
   public ResponseEntity<ResponsData<Suplier>> update(@RequestBody @Valid SuplierDto suplierDto, Errors errors){
      ResponsData<Suplier> responseHttp = new ResponsData<Suplier>();
      if(errors.hasErrors()){
         for (ObjectError error : errors.getAllErrors()) {
            responseHttp.getMessagesList().add(error.getDefaultMessage());
         }
         responseHttp.setPaylooad(null);
         responseHttp.setStatus(false);
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseHttp);
      }else{
         responseHttp.setStatus(true);
         responseHttp.getMessagesList().add("data succes update");
         Suplier suplier = modelMapper.map(suplierDto, Suplier.class);
         responseHttp.setPaylooad(suplierService.save(suplier));
         return ResponseEntity.status(HttpStatus.OK).body(responseHttp);
      }
   }

   //findSupplierByEmail
   @PostMapping(path = "/search/byEmail")
   public Suplier findSuplierByEmail(@RequestBody SearchDto keyword){
      return suplierService.findByEmail(keyword.getKeyword());
   }

   //findByNameContains
   @PostMapping(path = "/search/byName")
   public List<Suplier> findByNameContains(@RequestBody SearchDto keyword){
      return suplierService.findByNameContains(keyword.getKeyword());
   }

   //findByNameStartingWith
   @PostMapping(path = "/search/nameStartigWith")
   public List<Suplier> findByNameStartingWith(@RequestBody SearchDto prefix){
      return suplierService.findByNameStartingWith(prefix.getKeyword());
   }

   @PostMapping(path = "/search/nameOrEmail")
   public List<Suplier> findByNameContaininfOrEmailContaining(@RequestBody SearchDto prefix){
      return suplierService.findByNameContainingOrEmailContaining(prefix.getKeyword(), prefix.getSecondKeyword());
   }

  
}
