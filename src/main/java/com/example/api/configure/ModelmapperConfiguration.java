package com.example.api.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import eye2web.modelmapper.ModelMapper;
@Configuration
public class ModelmapperConfiguration {
   
   @Scope(value = "singleton")
   @Bean(value = "modelMapper")
   public ModelMapper modelMapper(){
      return new ModelMapper();
   }
}
