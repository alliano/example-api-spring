package com.example.api.security;

import javax.servlet.Filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

public class CustomDsl extends AbstractHttpConfigurer<CustomDsl,HttpSecurity> {

   @Override
   public void configure(HttpSecurity builder) throws Exception {
      AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
      builder.addFilter((Filter) authenticationManager);
      super.configure(builder);
   }
   
}
