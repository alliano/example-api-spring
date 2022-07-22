package com.example.api.controllers;


import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import com.example.api.dto.CategoryDto;
import com.example.api.dto.ResponsData;
import com.example.api.dto.SearchDto;
import com.example.api.models.entities.Category;
import com.example.api.services.CategoryService;

import eye2web.modelmapper.ModelMapper;

@RestController
@RequestMapping(path = "/api/category")
public class CategoryController {
   
   @Autowired
   private CategoryService categoryService;

   @Autowired
   private ModelMapper modelMapper;

   //create
   @PostMapping(path = "")
   public ResponseEntity<ResponsData<Category>> create(@RequestBody @Valid CategoryDto categoryDto,Errors errors){
      ResponsData<Category> responseHttp = new ResponsData<Category>();
      if(errors.hasErrors()){
         for (ObjectError error : errors.getAllErrors()) {
            responseHttp.getMessagesList().add(error.getDefaultMessage());
         }
         responseHttp.setPaylooad(null);
         responseHttp.setStatus(false);
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseHttp);
      }else{
         responseHttp.getMessagesList().add("data success add");
         responseHttp.setStatus(true);
         Category category = modelMapper.map(categoryDto, Category.class);
         responseHttp.setPaylooad(categoryService.save(category));
         return ResponseEntity.status(HttpStatus.OK).body(responseHttp);
      }
   }

   //find one
   @GetMapping(path = "/{id}")
   public Category findOne(@PathVariable("id") long id){
      return categoryService.findById(id);
   }

   //delete one
   @DeleteMapping(path = "/{id}")
   public String delete(@PathVariable("id") long id){
      return categoryService.destroyById(id);
   }

   //find all
   @GetMapping(path = "")
   public Iterable<Category> findAll(){
      categoryService.findAll().forEach(arg -> System.err.println(arg));
      return categoryService.findAll();
   }

   //update
   @PutMapping(path = "")
   public ResponseEntity<ResponsData<Category>> update(@RequestBody @Valid CategoryDto categoryDto,Errors errors){
      ResponsData<Category> responseHttp = new ResponsData<Category>();
      if(errors.hasErrors()){
         for (ObjectError error : errors.getAllErrors()) {
            responseHttp.getMessagesList().add(error.getDefaultMessage());
         }
         responseHttp.setPaylooad(null);
         responseHttp.setStatus(false);
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseHttp);
      }else{
         responseHttp.getMessagesList().add("data success update!");
         responseHttp.setStatus(true);
         Category category = modelMapper.map(categoryDto, Category.class);
         responseHttp.setPaylooad(categoryService.save(category));
         return ResponseEntity.status(HttpStatus.OK).body(responseHttp);
      }
   }

   @PostMapping(path = "/search/{page}/{size}")
   public Iterable<Category> findByName(@RequestBody SearchDto search,@PathVariable("size") int size, @PathVariable("page") int page){
      Pageable pageable = PageRequest.of(page, size);
      return categoryService.findByName(search.getKeyword(), pageable);
   }

   @PostMapping(path = "/search/{page}/{size}/{sort}")
   public Iterable<Category> findByName(@PathVariable("size") int size, @PathVariable("page") int page,@RequestBody SearchDto search,@PathVariable("sort") String sort){
      /**
       * untuk melakukan paging kita bisa tambhkan parameter page dan size
       * dan untuk melakukan sorting kita bisa tambahkan parameter sort dan mengakses method by dan 
       * method by kita beri parameter berdasarkan nama field yang mau di sorting dan setelah itu kita bisa 
       * chaining method by dengan ascending atau descending 
       * jika kita tidak chaining maka by default ascending
       */
      Pageable pageable = PageRequest.of(page, size,Sort.by("id").ascending());
      //jika sort nya itu adalah desc maka variable pageable akan di assigen dengan PageRequest.of(page, size,Sort.by("id").descending()
      if(sort.equalsIgnoreCase("desc"))pageable = PageRequest.of(page, size,Sort.by("id").descending());
      return categoryService.findByName(search.getKeyword(), pageable);
   }

   @PostMapping(path = "/saveBatch")
   public ResponseEntity<ResponsData<Iterable<Category>>> saveBatch(@RequestBody Category[] categories){
      
      ResponsData<Iterable<Category>> responseHttp = new ResponsData<Iterable<Category>>();

      responseHttp.getMessagesList().add("data success add");
      responseHttp.setPaylooad(categoryService.saveBatch(Arrays.asList(categories)));
      responseHttp.setStatus(true);
      return ResponseEntity.status(HttpStatus.OK).body(responseHttp);
   }
   
}
