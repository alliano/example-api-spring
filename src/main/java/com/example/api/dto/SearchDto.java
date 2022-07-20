package com.example.api.dto;

public class SearchDto {
   
   private String keyword;

   private String secondKeyword;

   public String getSecondKeyword() {
      return secondKeyword;
   }
   public void setSecondKeyword(String secondKeyword) {
      this.secondKeyword = secondKeyword;
   }
   public void setKeyword(String keyword) {
      this.keyword = keyword;
   }
   public String getKeyword() {
      return keyword;
   }
}
