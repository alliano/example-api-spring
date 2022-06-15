package com.example.api.controllers;


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

import com.example.api.dto.ResponDataStudent;
import com.example.api.models.entities.Students;
import com.example.api.services.StudentsService;



@RestController
@RequestMapping(path = "/api/student")
public class StudentsController {

   @Autowired
   private StudentsService StudentService;

   @PostMapping(path = "")
   public ResponseEntity<ResponDataStudent<Students>> createStudents(@RequestBody @Valid Students students, Errors errors){
      /**
       * kita menginstansiasi ini karna nanti agar kita bisa menggunkan method2 dari class ini
       * dan class ini adalah class yang untuk mengset status gagal atau berhasilnya
       * suatu data di insert ke dalam database
       */
      ResponDataStudent<Students> responData = new ResponDataStudent<>();
      if (errors.hasErrors()){
        for(ObjectError error : errors.getAllErrors()) {
          responData.getMessage().add(error.getDefaultMessage());
        }
         responData.setStatus(false);
         responData.setPayload(null);
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responData);
      }else{
         responData.setStatus(true);
         responData.setPayload(StudentService.saveStudents(students));
         return ResponseEntity.status(HttpStatus.OK).body(responData);
      }
   }

   @GetMapping(path = "")
   public Iterable<Students> findAll(){
      return StudentService.findAll();
   }

   @GetMapping(path = "{id}")
   public Students findOnStudents(@PathVariable("id") Long id){
      return StudentService.findOne(id); 
   }
   
   @PutMapping(path = "")
   public Students updatStudents(@RequestBody Students student){
      return StudentService.saveStudents(student);
   }

   @DeleteMapping(path = "{id}")
   public String deleteById(@PathVariable("id") Long id){
      StudentService.deleteStudent(id);
      return "success delete";
   }

}
