package com.example.api.configure.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordEncode {
   
   @Bean(name = "bcryptPass")
   public BCryptPasswordEncoder bCryptPasswordEncoder(){
      return new BCryptPasswordEncoder();
   }
}
