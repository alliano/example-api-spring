package com.example.api.services.coba;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.models.entities.tryning.Students;
import com.example.api.models.repositories.coba.StudentsRepository;

import java.util.List;



@Service
public class StudentsService {

   @Autowired
   private StudentsRepository studentRepo;

   public Students saveStudents(Students student){
      return studentRepo.save(student);
   }

   public Iterable<Students> findAll(){
      return studentRepo.findAll();
   }

   public Students findOne(long id){
      Optional<Students> data = studentRepo.findById(id);
      if(data.isEmpty()){
         return null;
      }else{
         return studentRepo.findById(id).get();
      }
   }

   public String deleteStudent(long id){
      studentRepo.deleteById(id);
      return "succes deleted student";
   }

   public List<Students> findByName(String name){
      return studentRepo.findByNameContains(name);
   }
}
