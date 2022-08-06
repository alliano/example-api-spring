package com.example.api.configure.utils;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.api.models.entities.AppUser;

/**
 * disini kita mengimplement AuditorAware untuk meng overide atau meng impolementsi methhod
 * getCurentAuditor() dengan tujuan mengambil user yang sedang login dengan SpringSecurity
 * */
public class AuditorAwareConfiguration implements AuditorAware<String>{

   @Override
   public Optional<String> getCurrentAuditor() {
      //mengambil email user yang sedang login dari springSecuritycontext
      AppUser getUserFromSecuritycontext = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      return Optional.of(getUserFromSecuritycontext.getEmail());
   }
    
}
