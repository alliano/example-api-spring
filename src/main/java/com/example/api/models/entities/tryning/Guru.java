package com.example.api.models.entities.tryning;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "guru")
public class Guru implements Serializable {

   public static final long serialVersionUID = 9L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

   @NotEmpty(message = "nama tidak boleh kosong")
   private String nama;

   @NotNull(message = "nip tidak boleh kosong")
   @Column(unique = true,length = 10)
   private int nip;

   @OneToOne(cascade = CascadeType.ALL)
   private Alamat alamat;

   public Guru(){}

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getNama() {
      return nama;
   }

   public void setNama(String nama) {
      this.nama = nama;
   }

   public int getNip() {
      return nip;
   }

   public void setNip(int nip) {
      this.nip = nip;
   }

   public Alamat getAlamat() {
      return alamat;
   }

   public void setAlamat(Alamat alamat) {
      this.alamat = alamat;
   }

}