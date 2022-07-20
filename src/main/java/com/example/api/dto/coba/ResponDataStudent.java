package com.example.api.dto.coba;

import java.util.ArrayList;
import java.util.List;

public class ResponDataStudent<T> {

   private boolean status;
   private List<String> message = new ArrayList<>();
   private T payload;

 

   public void setMessage(List<String> message) {
      this.message = message;
   }
   public List<String> getMessage() {
      return this.message;
   }
   public void setPayload(T payload) {
      this.payload = payload;
   }
   public T getPayload() {
      return payload;
   }
   public void setStatus(boolean status) {
      this.status = status;
   }
   
   public boolean getStatus(){
      return this.status;
   }
}
