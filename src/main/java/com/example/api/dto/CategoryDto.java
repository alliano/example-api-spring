package com.example.api.dto;

import javax.validation.constraints.NotEmpty;

public class CategoryDto {
   
   @NotEmpty(message = "name can't be empty!")
   private String name;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
}
