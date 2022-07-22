package com.example.api.models.entities;

public enum AppUserRole {
   USER("USER"),ADMIN("ADMIN");

   String value;
   private AppUserRole(String value){
      this.value = value;
   }
}
