package com.example.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


public class AppUserDto {
   
   @NotEmpty(message = "full name can't be empty!")
   private String fullName;

   @NotEmpty(message = "email can't be empty!")
   @Email
   private String email;

   @NotEmpty(message = "password can't be empty!")
   private String password;

   @NotEmpty(message = "role can't be empety!")
   private String appUserRole;

   public String getFullName() {
      return fullName;
   }

   public void setFullName(String fullName) {
      this.fullName = fullName;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getAppUserRole() {
      return appUserRole;
   }

   public void setAppUserRole(String appUserRole) {
      this.appUserRole = appUserRole;
   }
}
