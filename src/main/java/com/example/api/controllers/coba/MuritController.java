package com.example.api.controllers.coba;



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
import com.example.api.dto.coba.MuritDto;
import com.example.api.models.entities.tryning.Murit;
import com.example.api.services.coba.MuritService;

import eye2web.modelmapper.ModelMapper;

@RestController
@RequestMapping(path = "api/school")
public class MuritController {

   @Autowired
   private MuritService muritService;

   @Autowired
   private ModelMapper modelMapper;

   //create 
   @PostMapping(path = "")
   public ResponseEntity<ResponsData<Murit>> create(@RequestBody @Valid MuritDto muritDto ,Errors errors){
      ResponsData<Murit> responseHttp = new ResponsData<Murit>();
      if (errors.hasErrors()){
         for (ObjectError error : errors.getAllErrors()) {
            responseHttp.getMessagesList().add(error.getDefaultMessage());
         }
         responseHttp.setPaylooad(null);
         responseHttp.setStatus(false);
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseHttp);
      }else{
         Murit murit = modelMapper.map(muritDto, Murit.class);
         // murit.setAlamat(muritDto.getAlamat());
         // murit.setNama(muritDto.getNama());
         responseHttp.getMessagesList().add("data murit berhasil di buat");
         responseHttp.setStatus(true);
         responseHttp.setPaylooad(muritService.saveMurit(murit));
         return ResponseEntity.status(HttpStatus.OK).body(responseHttp);
      }
   }

   //delete
   @DeleteMapping(path = "/{id}")
   public String delete(@PathVariable("id") long id){
      return muritService.destroy(id);
   }

   //find one
   @GetMapping(path = "/{id}")
   public Murit findOne(@PathVariable("id") long id){
      return muritService.findOne(id);
   }

   //find all
   @GetMapping(path = "")
   public Iterable<Murit> findAll(){
      return muritService.findAll();
   }

   //update
   @PutMapping(path = "")
   public ResponseEntity<ResponsData<Murit>> update(@RequestBody @Valid MuritDto muritDto,Errors errors){
      ResponsData<Murit> responseHttp = new ResponsData<Murit>(); 
      if (errors.hasErrors()){
         for (ObjectError error : errors.getAllErrors()) {
            responseHttp.getMessagesList().add(error.getDefaultMessage());
         }
         responseHttp.setPaylooad(null);
         responseHttp.setStatus(false);
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseHttp);
      }else{
         responseHttp.getMessagesList().add("data murit berhasil di tambahkan");
         responseHttp.setStatus(true);
         Murit murit = modelMapper.map(muritDto, Murit.class);
         responseHttp.setPaylooad(muritService.saveMurit(murit));
         return ResponseEntity.status(HttpStatus.OK).body(responseHttp);
      }
   }

}
