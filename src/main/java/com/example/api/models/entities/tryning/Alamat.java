package com.example.api.models.entities.tryning;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "alamat")
public class Alamat implements Serializable {
   
   public static final long serialVersionUID = 11L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

   @NotEmpty(message = "nama desa tidak boleh kosong")
   private String desa;

   @Min(value = 1L,message = "rt tidak boleh kosong")
   private int rt;

   @Min(value = 1L,message = "rw can't be empty")
   private int rw;

   @NotEmpty(message = "provinsi harus diisi")
   private String provinsi;

   @NotEmpty(message = "negara tidak boleh kosong")
   private String negara;

   public Alamat(){}

   public void setId(long id) {
      this.id = id;
   }

   public String getDesa() {
      return desa;
   }

   public void setDesa(String desa) {
      this.desa = desa;
   }

   public int getRt() {
      return rt;
   }

   public void setRt(int rt) {
      this.rt = rt;
   }

   public int getRw() {
      return rw;
   }

   public void setRw(int rw) {
      this.rw = rw;
   }

   public String getProvinsi() {
      return provinsi;
   }

   public void setProvinsi(String provinsi) {
      this.provinsi = provinsi;
   }

   public String getNegara() {
      return negara;
   }

   public void setNegara(String negara) {
      this.negara = negara;
   }
   
}
