package com.example.api;

import org.junit.jupiter.api.BeforeEach;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.api.configure.BeanAuditorAwareConf;

public class GetUserFromSecurityContext {
   
   private ConfigurableApplicationContext configurableApplicationContext;

   @BeforeEach
   void setUp(){
      this.configurableApplicationContext = new AnnotationConfigApplicationContext(BeanAuditorAwareConf.class);
      this.configurableApplicationContext.registerShutdownHook();
   }
}
