package com.example.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class SuplierDto {
   
   @NotEmpty(message = "name can't be empty!")
   private String name;

   @NotEmpty(message = "address can't be empty!")
   private String address;

   @NotEmpty(message = "email can't be empty!")
   @Email(message = "email doesn't valid!")
   private String email;

   public SuplierDto(String name, String address, String email) {
      this.name = name;
      this.address = address;
      this.email = email;
   }
   public SuplierDto(){}

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }
}
