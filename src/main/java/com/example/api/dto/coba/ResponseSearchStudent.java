package com.example.api.dto.coba;

import java.util.List;

import com.example.api.models.entities.tryning.Students;

public class ResponseSearchStudent {
   private String message;
   private boolean status;
   private List<Students> payload;

   public void setMessage(String message) {
      this.message = message;
   }
   public String getMessage() {
      return message;
   }
   public void setStatus(boolean status) {
      this.status = status;
   }
   public boolean getStatus(){
      return this.status;
   }
   public void setPayload(List<Students> payload) {
      this.payload = payload;
   }
   public List<Students> getPayload() {
      return this.payload;
   }
}
