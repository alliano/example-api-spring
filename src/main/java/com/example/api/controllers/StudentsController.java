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

import com.example.api.dto.ResponDataStudent;
import com.example.api.dto.ResponseSearchStudent;
import com.example.api.models.entities.Students;
import com.example.api.services.StudentsService;



@RestController
@RequestMapping(path = "/api/student")
public class StudentsController {

   @Autowired
   private StudentsService StudentService;

   /**
    * @valid adalah anotasi yang di letakan sebagai parameter dari suatu method 
    * method ini berfungsi untuk memvalidasi apakah data yang dimasukan user/client itu sesuai 
    * dengan apa yang diinginkan atau tidak .
    * jikalau data yang dimasukan user/client itu tidak sesuai maka dengan anotasi @valid ini akan 
    * meng generate error 
    * @param students
    * @param errors
    * @return
    */
   @PostMapping(path = "")
   public ResponseEntity<ResponDataStudent<Students>> createStudents(@RequestBody @Valid Students students, Errors errors){
      /**
       * kita menginstansiasi ini karna nanti agar kita bisa menggunkan method2 dari class ini
       * dan class ini adalah class yang untuk mengset status gagal atau berhasilnya
       * suatu data di insert ke dalam database
       * 
       * ResponseEntity dimaksudkan untuk mewakili seluruh respons HTTP. Anda dapat mengontrol 
       * apa pun yang masuk ke dalamnya: kode status, header, dan tubuh. @ResponseBody adalah 
       * penanda untuk badan respons HTTP dan @ResponseStatus menyatakan kode status respons HTTP.
       */

       //ini kelas dari
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
   
   /**
    * @param student
    * @param error
    * @return
    */
   @PutMapping(path = "")
   public ResponseEntity<ResponDataStudent<Students>> updatStudents(@RequestBody @Valid Students student, Errors error){
      
      ResponDataStudent<Students> resStundet = new ResponDataStudent<>();

      if(error.hasErrors()){
      for (ObjectError err : error.getAllErrors()) {
         resStundet.getMessage().add(err.getDefaultMessage());
      }
         resStundet.setStatus(false);
         resStundet.setPayload(null);
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resStundet);
      }else{
         resStundet.setStatus(true);
         resStundet.setPayload(StudentService.saveStudents(student));
         return ResponseEntity.status(HttpStatus.OK).body(resStundet);
      }
   }

   @DeleteMapping(path = "{id}")
   public String deleteById(@PathVariable("id") Long id){
      StudentService.deleteStudent(id);
      return "success delete";
   }

   @PostMapping(path = "{name}")
   public ResponseEntity<ResponseSearchStudent> search(@PathVariable("name") String name){
      ResponseSearchStudent status = new ResponseSearchStudent();
      List<Students> result = StudentService.findByName(name);
      if (result.size() == 0){
         status.setMessage(name + " data not found");
         status.setStatus(false);
         status.setPayload(null);
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(status);
      }else{
         status.setMessage("data available");
         status.setStatus(true);
         status.setPayload(StudentService.findByName(name));
         return ResponseEntity.status(HttpStatus.OK).body(status);
      }
   }

}
