package com.example.api.dto.coba;

import java.util.List;

public class ResponseDataSekolah<Entity> {

   private List<String> message;

   private boolean status;

   private Entity payload;

   public List<String> getMessage() {
      return message;
   }

   public void setMessage(List<String> message) {
      this.message = message;
   }

   public boolean isStatus() {
      return status;
   }

   public void setStatus(boolean status) {
      this.status = status;
   }

   public Entity getPayload() {
      return payload;
   }

   public void setPayload(Entity payload) {
      this.payload = payload;
   }

}
