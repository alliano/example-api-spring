package com.example.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.api.configure.ModelmapperConfiguration;

import eye2web.modelmapper.ModelMapper;

public class ModelMapperConfigTest {
 
   private ConfigurableApplicationContext context;

   @BeforeEach
   public void setUp(){
      this.context = new AnnotationConfigApplicationContext(ModelmapperConfiguration.class);
   }

   @Test
   public void testModelMapper(){
      ModelMapper modelMapper = this.context.getBean("modelMapper",ModelMapper.class);
      Assertions.assertNotNull(modelMapper);
      
   }
}