package com.example.api.dto.coba;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import com.example.api.models.entities.tryning.Alamat;
import com.example.api.models.entities.tryning.Kelas;

public class MuritDto {
   
   @NotEmpty()
   private String nama;

   @Column(nullable = true)
   private Alamat alamat;

   @Column(nullable = true)
   private Kelas kelas;

   public String getNama() {
      return nama;
   }

   public void setNama(String name) {
      this.nama = name;
   }

   public Alamat getAlamat() {
      return alamat;
   }

   public void setAlamat(Alamat alamat) {
      this.alamat = alamat;
   }

   public Kelas getKelas() {
      return kelas;
   }

   public void setKelas(Kelas kelas) {
      this.kelas = kelas;
   }

}
