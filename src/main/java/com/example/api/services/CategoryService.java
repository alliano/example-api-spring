package com.example.api.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

// import com.example.api.configure.BeanAuditorAwareConf;
import com.example.api.models.entities.Category;
import com.example.api.models.repositories.CategoryRepository;

@Service
@Transactional
public class CategoryService {

   // @Autowired
   // private BeanAuditorAwareConf auditorAwareConf;
   
   @Autowired
   private CategoryRepository categoryRepository;

   public Category save(Category entity){
      //proses insert
      if(entity.getId() == null){
         entity.setUpdateBy(null);
         entity.setCreatedDate(null);
         return categoryRepository.save(entity);
      }
      else{
         //proses update
         Category curentCategory = categoryRepository.findById(entity.getId()).get();
         entity.setCreatedBy(curentCategory.getCreatedBy());
         entity.setCreatedDate(curentCategory.getCreatedDate());
         return categoryRepository.save(entity);
      }
   }

   public Category findById(long id){
      Optional<Category> result = categoryRepository.findById(id);
      if(!result.isPresent())return null;
      else
      return result.get();
   }

   public Iterable<Category> findAll(){
      return categoryRepository.findAll();
   }

   public String destroyById(long id){
      categoryRepository.deleteById(id);
      return "data has been deleted";
   }

   public Iterable<Category> findByName(String name, Pageable pageable){
      return this.categoryRepository.findByNameContaining(name, pageable);
   }

   public Iterable<Category> saveBatch(Iterable<Category> category ){
      return this.categoryRepository.saveAll(category);
   }
}
