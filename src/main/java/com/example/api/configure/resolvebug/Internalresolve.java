package com.example.api.configure.resolvebug;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class Internalresolve {
   
   @Bean(name = "InternalViewresolver")
   public InternalResourceViewResolver internalResourceViewResolver(){
      return new InternalResourceViewResolver();
   }
}
